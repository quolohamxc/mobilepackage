package ws.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import ws.crawler.BreadthCrawler;
import ws.item.TaobaoItem;
import ws.model.Page;
import ws.output.FileSystemOutput;
import ws.util.FileUtils;
import ws.util.Log;
import ws.util.RandomUtils;
import static ws.item.TaobaoItem.isTaobaoItem;

public class Controller {
	
	public static class CtrlCrawler extends BreadthCrawler {
		public CtrlCrawler(){
	        taskname=RandomUtils.getTimeString();
	    }
		@Override
		public void visit (Page page) {
			CtrlFileSystemOutput cfsoutput = new CtrlFileSystemOutput(root);
	        Log.Infos("visit",this.taskname,page.url);
	        cfsoutput.output(page);
		}
	}
	private static CtrlCrawler crawler = new CtrlCrawler();  

	public static class CtrlFileSystemOutput extends FileSystemOutput {

		public CtrlFileSystemOutput(String root) {
			super(root);
		}

		@Override
		public void output(Page page) {
			try {
	            File domain_path = new File(root, "sourcepages");
	            File index_path = new File(root, "index");
	            File info_path = new File(root, "info");
	            //File domain_path = new File(root, null);
	            Log.Infos("output",null, page.url);
	            boolean flag = isTaobaoItem(page);
	            //System.out.println("flag = :" + flag);
	            if(flag) {
	            	TaobaoItem item = new TaobaoItem(page);
	            	CtrlFileUtils.writeFile(domain_path, item, index_path, info_path);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	public static class CtrlFileUtils extends FileUtils {
		
		public static int filenum = 1;
		
		private static void WriteInfo(File file, TaobaoItem item, String filename) throws IOException {
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
		
		private static void WriteIndex(File file, TaobaoItem item, String filename) throws IOException {
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
		
		public static void writeFile(File file, TaobaoItem item, File fileindex, File fileinfo) throws IOException{
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
				
				/*
				try {
					WriteIndex(fileindex ,item, Integer.toString(filenum)+".txt");
					WriteInfo(fileinfo, item, Integer.toString(filenum)+".txt");
					FileOutputStream fos=new FileOutputStream(f);
			        fos.write(item.getHtml().getBytes());
			        fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					filenum--;
				} finally {
					filenum++;
				}*/
				WriteIndex(fileindex ,item, Integer.toString(filenum)+".txt");
				WriteInfo(fileinfo, item, Integer.toString(filenum)+".txt");
				FileOutputStream fos=new FileOutputStream(f);
		        fos.write(item.getHtml().getBytes());
		        fos.close();
		        filenum++;
			}
		}
		/*public static void writeFilewithPath(String filename, File file, byte[] content) throws IOException {
			if(!file.exists())
				file.mkdirs();
			File parent=file.getParentFile();
	        if(!parent.exists()){
	            parent.mkdirs();
	        }
			File f = new File(file, filename);
			FileOutputStream fos=new FileOutputStream(f);
			fos.write(content);
			fos.close();
		}*/

		/*public static void writeFile(File domain_path, TaobaoItem item, File index_path, File info_path) throws IOException {
			item.setFileFolder(domain_path.getAbsolutePath());
			item.setFileName(Integer.toString(filenum)+".html");
			if(item.iscomplete()) {*/
				/*try {
					writeFilewithPath(Integer.toString(filenum)+".html", domain_path, item.getHtml().getBytes());
					writeFilewithPath(Integer.toString(filenum)+".txt", index_path, item.getTitle().getBytes());
					writeFilewithPath(Integer.toString(filenum)+".txt", info_path, item.toString().getBytes());
				} catch (IOException e) {
					e.printStackTrace();
					filenum--;
				} finally {
					filenum++;
				}*//*
				writeFilewithPath(Integer.toString(filenum)+".html", domain_path, item.getHtml().getBytes());
				//writeFilewithPath(Integer.toString(filenum)+".txt", index_path, item.getTitle().getBytes());
				writeFilewithPath(Integer.toString(filenum)+".txt", info_path, item.toString().getBytes());
				filenum++;
			}
		}*/
		/*public static void writeFile(String domain_path, JingdongItem item) {
			
		}*/
	}
	
	public static void main (String args[]) throws IOException {
        crawler.addSeed("http://chi.taobao.com/");
        //crawler.addSeed("http://item.taobao.com/item.htm?id=12280852941&cm_cat=50038139");
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
