/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.crawler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ws.fetcher.Fetcher;
import ws.generator.Generator;
import ws.generator.Injector;
import ws.generator.StandardGenerator;
import ws.generator.filter.IntervalFilter;
import ws.generator.filter.URLRegexFilter;
import ws.generator.filter.UniqueFilter;
import ws.handler.Handler;
import ws.handler.Message;
import ws.model.Page;
import ws.output.FileSystemOutput;
import ws.util.Config;
import ws.util.ConnectionConfig;
import ws.util.Log;
import ws.util.RandomUtils;



public class BreadthCrawler {

	public BreadthCrawler(){
        taskname=RandomUtils.getTimeString();
    }

    protected String taskname;
    private String crawl_path = "crawl";
    protected String root = "data";
    private String cookie = null;
    private String useragent = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:26.0) Gecko/20100101 Firefox/26.0";

    private int threads=10;
    private boolean resumable;

    ArrayList<String> regexs = new ArrayList<String>();
    ArrayList<String> seeds = new ArrayList<String>();

    //�������
    public void addSeed(String seed) {
        seeds.add(seed);
    }

    //�������
    public void addRegex(String regex) {
        regexs.add(regex);
    }
    
    //�Զ����򣨻���ûɶ�ã�
    public void autoRegex() {
        for (String seed : seeds) {
            try {
                URL _URL = new URL(seed);
                String host = _URL.getHost();
                if (host.startsWith("www.")) {
                    host = host.substring(4);
                    host = ".*" + host;
                }
                String autoregex = _URL.getProtocol() + "://" + host + ".*";

                regexs.add(autoregex);
                System.out.println("autoregex:" + autoregex);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ConnectionConfig conconfig = null;

    //����useragent��cookie
    public void configCon(HttpURLConnection con) {
        con.setRequestProperty("User-Agent", useragent);
        if (cookie != null) {
            con.setRequestProperty("Cookie", cookie);
        }

    }

    //���ʲ��浽����
    protected void visit(Page page) {
        FileSystemOutput fsoutput = new FileSystemOutput(root);
        Log.Infos("visit",this.taskname,page.url);
        fsoutput.output(page);
    }
    
    protected void failed(Page page){
       
    }
    

    public final static int RUNNING=1;
    public final static int STOPED=2;
    public int status;
    public void start(int depth) throws IOException {
        if (!resumable) {
            if (seeds.size() == 0) {
                Log.Infos("error:"+"Please add at least one seed");
                return;
            }
            if (regexs.size() == 0) {
                autoRegex();
            }
        }
        inject();
        status=RUNNING;
        for (int i = 0; i < depth; i++) {
           if(status==STOPED){
               break;
           }
            //Log.Infos("info","starting depth "+(i+1));
            Generator generator=getGenerator();
            fetcher=getFecther();
            fetcher.fetchAll(generator);
        }
    }
    
    Fetcher fetcher;

    public void stop() throws IOException{
       fetcher.stop();
       status=STOPED;
    }
    
    //���Ƿ���ʹ�
    private void inject() throws IOException {
        
        Injector injector = new Injector(crawl_path);
        injector.setTaskname(taskname);
        if(resumable && injector.hasInjected()){
            Log.Infos("inject","no need to inject");
            return;
        }
        
        injector.inject(seeds);
        
    }

    
    class CommonConnectionConfig implements ConnectionConfig{
        @Override
            public void config(HttpURLConnection con) {               
                configCon(con);
            }
    }
    
    
    private Fetcher getFecther(){
        
        
         Handler fetch_handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Page page = (Page) msg.obj;
                switch(msg.what){
                    case Fetcher.FETCH_SUCCESS:
                    	visit(page);
                        break;
                    case Fetcher.FETCH_FAILED:
                        failed(page);
                        break;
                    default:
                        break;
                       
                }
            }
        };
        

        
        Fetcher fetcher=new Fetcher(crawl_path);
        fetcher.setHandler(fetch_handler);
        conconfig = new CommonConnectionConfig();
        fetcher.setTaskname(taskname);
        fetcher.setThreads(threads);
        fetcher.setConconfig(conconfig);
        return fetcher;
    }
    
    private Generator getGenerator(){

        Generator generator = new StandardGenerator(crawl_path);
        generator=new UniqueFilter(new IntervalFilter(new URLRegexFilter(generator, regexs)));
        //generator = new UniqueFilter(new URLRegexFilter(generator, regexs));
        generator.setTaskname(taskname);
        return generator;
    }
   

    

    public static void main(String[] args) throws IOException {
        String crawl_path = "/home/hu/data/crawl_hfut1";
        String root = "/home/hu/data/hfut1";
        
        Config.topN=500;

        BreadthCrawler crawler=new BreadthCrawler();
        crawler.taskname=RandomUtils.getTimeString()+"hfut";
        crawler.addSeed("http://news.hfut.edu.cn/");
        crawler.setRoot(root);
        crawler.setCrawl_path(crawl_path);
        crawler.setResumable(false);
        
        crawler.start(4);
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public String getCrawl_path() {
        return crawl_path;
    }

    public void setCrawl_path(String crawl_path) {
        this.crawl_path = crawl_path;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public boolean isResumable() {
        return resumable;
    }

    public void setResumable(boolean resumable) {
        this.resumable = resumable;
    }

    public ConnectionConfig getConconfig() {
        return conconfig;
    }

    public void setConconfig(ConnectionConfig conconfig) {
        this.conconfig = conconfig;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    
    
}
