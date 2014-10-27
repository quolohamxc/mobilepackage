package com.mobilepackage.telecom;

import java.io.IOException;
import java.net.URL;







import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import ws.model.Page;

import com.mp.model.PackageTariff;
import com.mp.util.UtilMethod;
/**
 * 
 * @author Administrator
 *
 */
public class TelecomPackage extends PackageTariff{
	/**
	 * analyze htmls from Zol
	 * @param page
	 */
	public void analyzeZol(Page page) {
		/**
		 * note: use jsoup.
		 * class Page contains document "doc", so don't new another document.
		 * here is the defination:
		 * public class Page{
		 *  	public String url=null;
		 * 		public byte[] content=null;
		 *  	public Map<String,List<String>> headers;
		 *   	public String html=null;
		 *  	public Document doc=null;
		 *   	public int status;
		 *  	public long fetchtime;
		 *
		 *  	public static final int UNFETCHED=1;
		 *  	public static final int FETCHED=2;
		 * }
		 */
		//TODO analyze htmls from Zol, and save the elements in parent class.
		//use get and set method.
		try{
			Document doc = page.doc;
			URL url = new URL(page.url);
			doc=Jsoup.connect(page.url).get();
			Element mTitle=page.doc.select("div.mTitle").first();
			//String s1="<b>";
			//String s2=mTitle.toString();
			//String s3="</b>";
			//String s4=UtilMethod.elimFix(s1, s2, s3);
			this.setmTitle(UtilMethod.elimFix("", mTitle.text(), ""));
			
			
			Element mMonthlyFee=page.doc.select("div.mMonthlyFee").first();
			this.setmMonthlyFee(Double.valueOf(UtilMethod.elimFix("", mMonthlyFee.text(), "元合约")));
			
			
			Element mMonthlyCons=page.doc.select("div.mMonthlyCons").first();
			this.setmMonthlyCons(Double.valueOf(UtilMethod.elimFix("月最低消费", mMonthlyCons.text(), "元")));
			
			
			Element mDomCallDur=page.doc.select("div.mDomCallDur").first();
			this.setmDomCallDur(Double.valueOf(UtilMethod.elimFix("", mDomCallDur.text(), "分钟")));
			
			
			Element mDomInfoFlow=page.doc.select("div.mDomInfoFlow").first();
			String s1=mDomInfoFlow.text();
			char[] ch=s1.toCharArray();
			char[] ch2 = new char[10];
			for ( int i=0; i<10; i++ ) {
				if ( ch[i] >= 'M' && ch[i] <= 'G') {
					break;
				}else {
					ch2[i] = ch[i];
				}
			}
			String s2=String.valueOf(ch2);
			this.setmDomInfoFlow(Double.valueOf(s2));
			
			
			Element mDomMsg=page.doc.select("div.mDomMsg").first();
			this.setmDomMsg(Integer.parseInt(UtilMethod.elimFix("", mDomMsg.text(), "条")));
			
			
			Element mDomMmMsg=page.doc.select("div.mDomMmMsg").first();
			this.setmDomMmMsg(Integer.parseInt(UtilMethod.elimFix("", mDomMmMsg.text(), "条")));
			
			
			Element mWifi=page.doc.select("div.mWifi").first();
			this.setmWifi(Double.valueOf(UtilMethod.elimFix("", mWifi.text(), "小时")));
			
			
			//Element mDomCall=page.doc.select("div.mDomCall").first();
			this.setmDomCall('0');
			
			
			//Element mDomListen=page.doc.select("div.mDomListen").first();
			this.setmDomListen('0');
			
			
			Element mDomCallBey=page.doc.select("div.mDomCallBey").first();
			this.setmDomCallBey(Double.valueOf(UtilMethod.elimFix("国内语音拨打", mDomCallBey.text(), "元/分钟")));
			
			
			Element mDomLisBey=page.doc.select("div.mDomLisBey").first();
			this.setmDomLisBey('0');
			
			
			Element mAddition=page.doc.select("div.mAddition").first();
			this.setmAddition(UtilMethod.elimFix("国内流量", mAddition.text(), "元/10KB"));
			
			
			Element mDescription=page.doc.select("div.mDescription").first();
			this.setmDescription(UtilMethod.elimFix("其他业务", mDescription.text(), "。"));
			
			
			Element mDomMmMsgmAutoUpdateIF=page.doc.select("div.mAutoUpdateIF").first();
			this.setmAutoUpdateIF(Boolean.parseBoolean("true"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
