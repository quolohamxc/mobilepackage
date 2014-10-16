package com.mp.telecomzol;

import ws.model.Page;

import com.mp.model.PackageTariff;
/**
 * 
 * @author Administrator
 *
 */
public class Package extends PackageTariff{
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
	}
}
