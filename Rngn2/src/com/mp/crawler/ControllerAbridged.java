package com.mp.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;

public class ControllerAbridged {

	private List<String> urlList;
	private List<Document> docList;
	private Queue<String> urlQueue;
	// private final static String HOST =
	// "http://detail.zol.com.cn/phone_package/dianxin/";
	protected HashMap<String, PackageTariff> pHashZol;

	public ControllerAbridged() {
		super();
		setUrlList(new LinkedList<String>());
		setDocList(new LinkedList<Document>());
		setUrlQueue(new LinkedList<String>());
		pHashZol = new HashMap<String, PackageTariff>();
	}

	// url
	public List<String> getUrlList() {
		return urlList;
	}

	public HashMap<String, PackageTariff> getpHashZol() {
		return pHashZol;
	}

	public void setpHashZol(HashMap<String, PackageTariff> pHashZol) {
		this.pHashZol = pHashZol;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}

	// document
	public List<Document> getDocList() {
		return docList;
	}

	public void setDocList(List<Document> docList) {
		this.docList = docList;
	}

	// cache
	public Queue<String> getUrlQueue() {
		return urlQueue;
	}

	public void setUrlQueue(Queue<String> urlQueue) {
		this.urlQueue = urlQueue;
	}

	// Legal link
	private boolean isURL(String url) {
		// int count = 0;
		for (String pregex : mZolRegex) {
			if (Pattern.matches(pregex, url)) {
				// count++;
				return true;
			}
		}
		// if (count != 0)
		// return true;
		// else
		return false;
	}

	public boolean isHost(String url) {
		// return !url.contains("#");
		return true;
	}

	public void traverse(String seed) {
		if(this.mZolRegex == null) {
			addDefaultRegex();
		}
		for (this.getUrlQueue().add(seed); !this.getUrlQueue().isEmpty();) {
			boolean flag = true;
			Document document = null;
			try {
				document = Jsoup.connect(seed).timeout(5000).get();
			} catch (IOException e) {
				flag = false;
			}
			if (flag) {
				Elements elements = document.select("a[href]");
				for (Element e : elements) {
					String s = e.attr("abs:href");
					if (isURL(s) && isHost(s) && (!getUrlQueue().contains(s))
							&& (!getUrlList().contains(s))) {
						this.getUrlQueue().add(s);
					}
				}
				this.getDocList().add(document);
			}
			seed = this.getUrlQueue().poll();
			this.getUrlList().add(seed);
			// show information
			// System.out.println("SIZE:" + this.getUrlQueue().size() + "---"
			// + seed + " connect!");
		}
	}

	public String mEntry = "http://detail.zol.com.cn/phone_package/dianxin/";
	protected ArrayList<String> mZolRegex;

	protected void addDefaultRegex() {
		mZolRegex = new ArrayList<String>();
		mZolRegex
				.add("http://detail.zol.com.cn/phone_package/dianxin/[0-9]+.*");
		mZolRegex.add("http://detail.zol.com.cn/phone_package/[i-i]+.*");
	}

	public String getmEntry() {
		return mEntry;
	}

	public void setmEntry(String mEntry) {
		this.mEntry = mEntry;
	}
}
