package ws.item;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ws.model.Link;
import ws.model.Page;
import ws.parser.HtmlParser;
import ws.parser.ParseResult;
import ws.util.Config;

public class TaobaoItem extends Item{

	private String Title = null;	//标题
	private String Subtitle = null;//副标题
	private	String Cost = null;	//价格
	private String Info = null;	//商品信息
	
	private Double Ranking = null;	//网页分数
	
	private String URL = null;		//网址
	private String ImgURL = null;	//图片地址
	
	private String FileFolder = null;	//镜像存储文件夹
	private String FileName = null;	//镜像存储文件名
	
	@Override
	public boolean isItem() {
		return iscomplete();
	}
	
	@Override
	public boolean isItem(String url) {
		return isTaobaoItem(url);
	}

	public static boolean isTaobaoItem(Page page) throws UnsupportedEncodingException {
		HtmlParser htmlparser = new HtmlParser(Config.topN);
		ParseResult parseresult = htmlparser.getParse(page);
        ArrayList<Link> links = parseresult.links;
		if(links.size() == 0)
			return false;
		return isTaobaoItem(page.url);
	}
	
	public static boolean isTaobaoItem(String url) {
		return url.contains("item.htm");
	}

	@Override
	public void buildItem(Page page) {
		
		Document doc = page.doc;
		String title = doc.title();
		
		String source = title.substring(title.length() - 3, title.length());
		
		if(source.equals("淘宝网")) {

			//商品标题
			String Title = "";
			Element mainTitle = doc.getElementsByClass("tb-main-title").first();
			if(mainTitle != null)
				Title += mainTitle.text();
			else {
				Element m = doc.getElementsByClass("item-title").first();
				if(m != null)
					Title += m.text();
			}

			//商品副标题
			String SubTitle = "";
			Element subtitle = doc.getElementsByClass("tb-subtitle").first();
			if(subtitle != null)
				SubTitle += subtitle.text();
			else {
				SubTitle = null;
			}

			//商品价格
			String Cost = "";
			Element Price = doc.getElementsByClass("tb-rmb-num").first();
			if(Price != null)
				Cost += Price.text();
			else {
				Element p = doc.getElementsByClass("tb-spec").first();
				if(p != null)
					Cost += p.text();
			}

			//商品信息
			String Info = "";
			Element info = doc.getElementsByClass("attributes-list").first();
			if(info != null) {
				Elements infos = info.getElementsByTag("li");
				if(infos != null)
				for(Element inf : infos) {
					Info = Info + inf.text()+"\r\n";
				}
			} else {
				Info = null;
			}

			//图片地址
			String ImgURL = "";
			Element imgLink = doc.getElementsByClass("tb-booth").first();
			Element imgUrl = imgLink.getElementsByTag("img").first();
			if(imgUrl != null)
				ImgURL += imgUrl.attr("data-src");
			else {
				ImgURL = null;
			}
			
			this.Title = Title;
			this.Subtitle = SubTitle;
			this.Cost = Cost;
			this.Info = Info;
			
			this.Ranking = 0.0;
			
			this.URL = page.url;
			this.ImgURL = ImgURL;
				
		} else if (source.equals("m天猫")) {

			Element Detail = doc.getElementsByClass("tb-detail-hd").first();

			//商品标题
			String Title = "";
			Element mainTitle = Detail.getElementsByTag("h1").first();
			if(mainTitle != null)
				Title += mainTitle.text();

			//商品副标题
			String SubTitle = "";
			Element subtitle = Detail.getElementsByTag("p").first();
			if(subtitle != null)
				SubTitle += subtitle.text();

			//商品价格
			String Cost = "";
			Element Price = doc.getElementsByClass("tm-price-item").first();
			if(Price != null)
				Cost += Price.text();

			//商品信息
			String Info = "";
			Element info = doc.getElementsByClass("attributes-list").first();
			Elements infos = info.getElementsByTag("li");
			for(Element inf : infos) {
				Info = Info + inf.text()+"\r\n";
			}

			//图片地址
			String ImgURL = "";
			Element imgLink = doc.getElementsByClass("tb-booth").first();
			Element imgUrl = imgLink.getElementsByTag("img").first();
			if(imgUrl != null)
				ImgURL += imgUrl.attr("src");

			this.Title = Title;
			this.Subtitle = SubTitle;
			this.Cost = Cost;
			this.Info = Info;
			
			this.Ranking = 0.0;
			
			this.URL = page.url;
			this.ImgURL = ImgURL;
		}
	}
	
 	public TaobaoItem(Page page) {
		this.buildItem(page);
		this.html = page.html;
	}

	public boolean iscomplete() {
		if(Title == null)
			return false;
		else
			return true;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getSubtitle() {
		return Subtitle;
	}
	public void setSubtitle(String subtitle) {
		Subtitle = subtitle;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	public String getInfo() {
		return Info;
	}
	public void setInfo(String info) {
		Info = info;
	}
	public Double getRanking() {
		return Ranking;
	}
	public void setRanking(Double ranking) {
		Ranking = ranking;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getImgURL() {
		return ImgURL;
	}
	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}
	public String getFileFolder() {
		return FileFolder;
	}
	public void setFileFolder(String fileFolder) {
		FileFolder = fileFolder;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}

	@Override
	public String toString() {
		return "Title=" + Title + ",\r\n Subtitle=" + Subtitle + ",\r\n Cost="
				+ Cost + ",\r\n Info=" + Info + ",\r\n Ranking=" + Ranking + ",\r\n URL="
				+ URL + ",\r\n ImgURL=" + ImgURL + ",\r\n FileFolder=" + FileFolder
				+ ",\r\n FileName=" + FileName;
	}

}
