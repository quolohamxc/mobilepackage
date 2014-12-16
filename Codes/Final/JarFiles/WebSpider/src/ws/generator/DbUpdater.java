/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.generator;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import ws.model.AvroModel;
import ws.model.CrawlDatum;
import ws.model.Page;
import ws.util.Config;
import ws.util.FileUtils;
import ws.util.Task;

/**
 *
 * @author floion z
 */
public class DbUpdater extends Task{
    String crawl_path;
    public DbUpdater(String crawl_path){
        this.crawl_path=crawl_path;
    }
    

    public boolean isLocked() throws IOException {
        File lockfile = new File(crawl_path + "/" + Config.lock_path);        
        if (!lockfile.exists()) {
            return false;
        }
        String lock = new String(FileUtils.readFile(lockfile), "utf-8");
        return lock.equals("1");
    }

    public void lock() throws IOException {
        FileUtils.writeFile(crawl_path + "/" + Config.lock_path, "1".getBytes("utf-8"));
    }

    public void unlock() throws IOException {
        FileUtils.writeFile(crawl_path + "/" + Config.lock_path, "0".getBytes("utf-8"));
    }
    DataFileWriter<CrawlDatum> dataFileWriter;
    int updaterCount;
    
    
    public void updateAll(ArrayList<CrawlDatum> datums) throws IOException{
         File currentfile = new File(crawl_path, Config.current_info_path);
        if (!currentfile.getParentFile().exists()) {
            currentfile.getParentFile().mkdirs();
        }
        DatumWriter<CrawlDatum> datumWriter = new ReflectDatumWriter<CrawlDatum>(CrawlDatum.class);
        DataFileWriter<CrawlDatum> alldataFileWriter = new DataFileWriter<CrawlDatum>(datumWriter);
        alldataFileWriter.create(AvroModel.getPageSchema(), currentfile);
        for(CrawlDatum crawldatum:datums){
            alldataFileWriter.append(crawldatum);
            //System.out.println(crawldatum.url);
        }
        alldataFileWriter.close();
    }

    public void initUpdater() throws UnsupportedEncodingException, IOException {
        File currentfile = new File(crawl_path, Config.current_info_path);
        if (!currentfile.getParentFile().exists()) {
            currentfile.getParentFile().mkdirs();
        }
        DatumWriter<CrawlDatum> datumWriter = new ReflectDatumWriter<CrawlDatum>(CrawlDatum.class);
        dataFileWriter = new DataFileWriter<CrawlDatum>(datumWriter);
        //dataFileWriter.create(AvroModel.getPageSchema(), currentfile);
        dataFileWriter.appendTo(currentfile);
        updaterCount = 0;
    }

    public synchronized void append(CrawlDatum crawldatum) throws IOException {
       
        dataFileWriter.append(crawldatum);
         if (updaterCount % 200 == 0) {
            dataFileWriter.flush();
           
        }
        updaterCount++;
    }

    public void closeUpdater() throws IOException {
        dataFileWriter.close();
    }
    
    public void merge() throws IOException{
        
        File oldfile=new File(crawl_path, Config.current_info_path);
        DatumReader<CrawlDatum> datumReader = new ReflectDatumReader<CrawlDatum>(CrawlDatum.class);
        DataFileReader<CrawlDatum> dataFileReader = new DataFileReader<CrawlDatum>(oldfile, datumReader);
        HashMap<String,Integer> indexmap=new HashMap<String, Integer>();
        ArrayList<CrawlDatum> origin_datums=new ArrayList<CrawlDatum>();
        
 
        for(CrawlDatum crawldatum:dataFileReader){
            String url=crawldatum.url;
            if(indexmap.containsKey(crawldatum.url)){
                int preindex=indexmap.get(url);
                CrawlDatum pre_datum=origin_datums.get(preindex);
                if(crawldatum.status==Page.UNFETCHED){
                    continue;
                }else if(pre_datum.fetchtime>=crawldatum.fetchtime){
                    continue;
                }else{
                    origin_datums.set(preindex, crawldatum);
                    indexmap.put(url, preindex);
                }
                
            }else{
                origin_datums.add(crawldatum);
                indexmap.put(url, origin_datums.size()-1);
            }
            
        }
       
        dataFileReader.close();
        updateAll(origin_datums);

    }
    
}
