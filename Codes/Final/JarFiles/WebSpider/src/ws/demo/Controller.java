package ws.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

import ws.crawler.BreadthCrawler;
import ws.generator.Generator;
import ws.generator.filter.Filter;
import ws.item.Item;
import ws.item.JingdongItem;
import ws.item.TaobaoItem;
import ws.model.CrawlDatum;
import ws.model.Page;
import ws.output.FileSystemOutput;
import ws.util.FileUtils;
import ws.util.Log;
import ws.util.RandomUtils;

public class Controller {
	
	public static class CtrlCrawler extends BreadthCrawler {
		public CtrlCrawler(){
	        taskname=RandomUtils.getTimeString();
	    }
		@Override
		public void visit (Page page) {
			
			//System.out.println("******************");
			//System.out.println(page.content);
			
			CtrlFileSystemOutput cfsoutput = new CtrlFileSystemOutput(root);
	        Log.Infos("visit",this.taskname,page.url);
	        
	        cfsoutput.output(page);
		}
	}
	private static CtrlCrawler crawler = new CtrlCrawler();  
	
	/*public static class CtrlFilter extends Filter {

		public HashSet<String> hashset=new HashSet<String>();
	    
	    public void addUrl(String url){
	         hashset.add(url);
	    }

	    public CtrlFilter(Generator generator) {
	        super(generator);
	    }

	    
	    @Override
	    public CrawlDatum next() {
	        CrawlDatum crawldatum=generator.next();
	        if(crawldatum==null){
	            return null;
	        }
	        String url=crawldatum.url;
	        if(hashset.contains(url)){
	            return next();
	        }
	        else{
	            addUrl(url);
	            return crawldatum;
	        }
	    }
	}*/
	
	public static class CtrlFileSystemOutput extends FileSystemOutput {

		public CtrlFileSystemOutput(String root) {
			super(root);
		}
		
		@Override
		public void output(Page page) {
			try {
	            URL _URL = new URL(page.url);
	            File domain_path = new File(root, "sourcepages");
	            File index_path = new File(root, "index");
	            File info_path = new File(root, "info");
	            //File domain_path = new File(root, null);
	            Log.Infos("output",null, page.url);
	            boolean flag = TaobaoItem.isTaobaoItem(page);
	            //System.out.println("flag = :" + flag);
	            if(flag) {
	            	TaobaoItem item = new TaobaoItem(page);
	            	//System.out.println(item.toString());
	            	CtrlFileUtils.writeFile(domain_path, item, index_path, info_path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
	}
	
	public static class CtrlFileUtils extends FileUtils {
		
		public static int filenum = 1;
		
		/*public static void writeFile(File file, Item item) throws IOException {
			File parent=file.getParentFile();
	        if(!parent.exists()){
	            parent.mkdirs();
	        }
	        File f = new File(file, Integer.toString(filenum));
	        FileOutputStream fos=new FileOutputStream(f);
	        fos.write(item.html.getBytes());
	        fos.close();
	        filenum++;
		}*/
		
		public static void WriteInfo(File file, TaobaoItem item, String filename) throws IOException {
			if(!file.exists())
				file.mkdirs();
			
			File parent=file.getParentFile();
	        if(!parent.exists()){
	            parent.mkdirs();
	        }
			File f = new File(file, filename);
			
			FileOutputStream fos=new FileOutputStream(f);
	        fos.write(item.toString().getBytes());
	        fos.close();
		}
		
		public static void WriteIndex(File file, TaobaoItem item, String filename) throws IOException {
			if(!file.exists())
				file.mkdirs();
			
			File parent=file.getParentFile();
	        if(!parent.exists()){
	            parent.mkdirs();
	        }
			File f = new File(file, filename);
			
			FileOutputStream fos=new FileOutputStream(f);
	        fos.write(item.getTitle().getBytes());
	        fos.close();
		}
		
		public static void writeFile(File file, TaobaoItem item, File fileindex, File fileinfo) throws IOException {
			if(!file.exists())
				file.mkdirs();
			
			File parent=file.getParentFile();
	        if(!parent.exists()){
	            parent.mkdirs();
	        }
			File f = new File(file, Integer.toString(filenum)+".html");
			
			item.setFileFolder(file.getAbsolutePath());
			item.setFileName(Integer.toString(filenum)+".html");
			
			//System.out.println(item.toString());
			//System.out.println(item.iscomplete());
			
			if(item.iscomplete()) {
				
				WriteIndex(fileindex ,item, Integer.toString(filenum)+".txt");
				WriteInfo(fileinfo, item, Integer.toString(filenum)+".txt");
				
				FileOutputStream fos=new FileOutputStream(f);
		        fos.write(item.getHtml().getBytes());
		        fos.close();
		        filenum++;
			}
		}
		
		/*public static void writeFile(String domain_path, JingdongItem item) {
			
		}*/
	}
	
	public static void main (String args[]) throws IOException {
		//String text = "http://item.taobao.com/item.htm?id=22128012671";
		//link_crawldatum.url = "http://meal" + page.url.substring(11,page.url.length());
		//String xx = "http://meal" + text.substring(11,text.length());
		//System.out.println(xx);
		
		
        //crawler.addSeed("http://chi.taobao.com");
        crawler.addSeed("http://item.taobao.com/item.htm?id=12280852941&cm_cat=50038139");
        /*网页、图片、文件被存储在download文件夹中*/  
        crawler.setRoot("download");  
        crawler.addRegex("+^http://chi.taobao.com/.*");
        crawler.addRegex("+.*item.htm.*");
        crawler.addRegex("+^http://s.taobao.com/.*");
        crawler.addRegex("+^http://list.taobao.com/.*");
        /*进行深度为5的爬取*/  
        crawler.start(5);
	}
}
