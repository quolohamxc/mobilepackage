package com.mp.telecom.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;
import com.mp.telecom.SinglePkgInfo;
import com.mp.telecom.model.SinglePkgTar;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

/**
 * 乐享3G/4G
 * @author Administrator
 *
 */
public class SinglePkgParserA {
	private List<String> urlList;
	private List<Document> docList;
	private Queue<String> urlQueue;

	public SinglePkgParserA() {
		super();
		setUrlList(new LinkedList<String>());
		setDocList(new LinkedList<Document>());
		setUrlQueue(new LinkedList<String>());
	}

	// url
	public List<String> getUrlList() {
		return urlList;
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
		}
	}

	public String mEntry = "http://detail.zol.com.cn/phone_package/dianxin/";
	protected ArrayList<String> mZolRegex;

	private void addDefaultRegex() {
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
	
	public void parse() {
		addDefaultRegex();
		this.traverse(this.mEntry);
		for (Document doc : getDocList()) {
			parse(doc);
		}
		//if(pHashZol.size() == 0) { new SAEsqlForPT().debug(); }
	}

	/**
	 * analyze htmls from Zol
	 * 
	 * @param page
	 * @throws IOException
	 */
	private void parse(Document doc) {
		Document mDoc = doc;
		Element mEle1 = mDoc.getElementsByClass("package-infor").first();
		Element mEle2 = mDoc.getElementById("mobile1");
		String name = new String();
		String desc = new String();
		if (mEle1 != null && mEle1 == mEle2) {
			Elements lEle1 = mEle1.getElementsByTag("tr");
			Element lEle2 = mEle1.getElementsByTag("p").first();
			if (lEle2 != null) {
				desc = lEle2.text();
			}
			if (lEle1 != null) {
				for (Element lEle : lEle1) {
					Elements pEle1 = lEle.getElementsByTag("td");
					Element pEle2 = lEle.getElementsByTag("th").first();
					if (pEle2 != null) {
						name = pEle2.text();
					}
					parseAnalyze(pEle1, name, desc);
				}
			}
		}
	}

	private void parseAnalyze(Elements pEle1, String name, String desc) {
		if (pEle1 == null) {
			return;
		}
		PackageTariff pT = new PackageTariff(UtilData.CTELECOM);
		String name2 = new String();
		for (Element pEle : pEle1) {
			String pAttr = pEle.attr("class");

			if (pAttr.equals("cell-1")) {
				name2 = pEle.text();
				pT.setmMonthlyFee(Double.valueOf(UtilMethod.elimFix("",
						pEle.text(), "元")));
			} else if (pAttr.equals("cell-2")) {
				pT.setmDomCallDur(Integer.valueOf(UtilMethod.elimFix("",
						pEle.text(), "分钟")));
			} else if (pAttr.equals("cell-3")) {
				pAttr = pEle.text();
				if (pAttr.indexOf("MB") >= 0) {
					pT.setmDomInfoFlow(Double.valueOf(UtilMethod.elimFix("",
							pEle.text(), "MB")));
				} else if (pAttr.indexOf("GB") >= 0) {
					pT.setmDomInfoFlow(Double.valueOf(UtilMethod.elimFix("",
							pEle.text(), "GB")) * 1000);
				}
			} else if (pAttr.equals("cell-4")) {
				pT.setmDomMsg(Integer.valueOf(UtilMethod.elimFix("",
						pEle.text(), "条")));
			} else if (pAttr.equals("cell-5")) {
				pT.setmDomMmMsg(Integer.valueOf(UtilMethod.elimFix("",
						pEle.text(), "条")));
			} else if (pAttr.equals("cell-6")) {
				pT.setmWifi(Integer.valueOf(UtilMethod.elimFix("", pEle.text(),
						"小时")));
			}
		}
		if (desc.indexOf("国内免费接听") >= 0) {
			pT.setmDomLisBey(0);
		}
		if (desc.indexOf("国内语音拨打") >= 0) {
			pT.setmDomCallBey(Double.valueOf(UtilMethod.elimFix("国内语音拨打", desc,
					"元/分钟")));
		}
		if (desc.indexOf("国内流量") >= 0) {
			if (desc.indexOf("元/10KB") >= 0) {
				pT.setmDomInfoBey(Double.valueOf(UtilMethod.elimFix("国内流量",
						desc, "元/10KB")) * 100);
			}
		}
		if (desc.indexOf("享受自动升级") >= 0) {
			pT.setmAutoUpdateIF(true);
		}
		name2 = name + name2;
		pT.setmTitle(name2);
		new SinglePkgInfo().addSinglePkg(pT);

	}
	
	public static void main(String[] args) {
		new SinglePkgParserA().parse();

		SinglePkgInfo sPI = new SinglePkgInfo();
		System.out.println(sPI.solve(100, 100, 10));
		for (SinglePkgTar sPT : sPI.singlePkgTar) {
			System.out.println(sPT.getpT().toString());
//			System.out.println(sPT.getPrice() + " " + sPT.getpT().toString());
		}
		
	}
}
