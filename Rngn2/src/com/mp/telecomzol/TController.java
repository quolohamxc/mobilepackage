package com.mp.telecomzol;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ws.crawler.BreadthCrawler;
import ws.model.Page;
import ws.output.FileSystemOutput;
import ws.util.RandomUtils;

import com.mp.model.PackageTariff;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

/**
 * 
 * @author Administrator
 *
 */
public class TController {

	private static HashMap<String, PackageTariff> pHashZol;

	public HashMap<String, PackageTariff> getpHashZol() {
		return pHashZol;
	}

	public void setpHashZol(HashMap<String, PackageTariff> pHashZol) {
		TController.pHashZol = pHashZol;
	}

	public TController() {
		super();
		pHashZol = new HashMap<String, PackageTariff>();
	}

	public static class CtrlCrawler extends BreadthCrawler {
		public CtrlCrawler() {
			super.taskname = RandomUtils.getTimeString();
		}

		@Override
		public void visit(Page page) {
			CtrlFileSystemOutput cfsoutput = new CtrlFileSystemOutput(root);
			// Log.Infos("visit",this.taskname,page.url);
			cfsoutput.output(page);
		}
	}

	public static class CtrlFileSystemOutput extends FileSystemOutput {

		public CtrlFileSystemOutput(String root) {
			super(root);
		}

		@Override
		public void output(Page page) {
			analyzeZol(page);
			try {
				URL _URL = new URL(page.url);
				String query = "";
				if (_URL.getQuery() != null) {
					query = "_" + _URL.getQuery();
				}
				String path = _URL.getPath();
				if (path.length() == 0) {
					path = "index.html";
				} else {
					if (path.charAt(path.length() - 1) == '/') {
						path = path + "index.html";
					} else {

						for (int i = path.length() - 1; i >= 0; i--) {
							if (path.charAt(i) == '/') {
								if (!path.substring(i + 1).contains(".")) {
									path = path + ".html";
								}
							}
						}
					}
				}
				path += query;
				// File domain_path = new File(root, _URL.getHost());
				// File f = new File(domain_path, path);
				// Log.Infos("output",null, f.getAbsolutePath());

				// System.out.println(pHashZol.size());
				// FileUtils.writeFileWithParent(f, page.content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * analyze htmls from Zol
	 * 
	 * @param page
	 */
	private static void analyzeZol(Page page) {
		Document mDoc = page.doc;
		Element mEle1 = mDoc.getElementsByClass("package-infor").first();
		Element mEle2 = mDoc.getElementById("mobile1");
		String name = new String();
		String desc = new String();
		if (mEle1 != null && mEle1 == mEle2) {
			Elements lEle1 = mEle1.getElementsByTag("tr");
			Element lEle2 = mEle1.getElementsByTag("p").first();
			if (lEle2 != null) {
				desc = lEle2.text();
				// System.out.println(desc);
			}
			if (lEle1 != null) {
				for (Element lEle : lEle1) {
					Elements pEle1 = lEle.getElementsByTag("td");
					Element pEle2 = lEle.getElementsByTag("th").first();
					if (pEle2 != null) {
						name = pEle2.text();
						// System.out.println(name);
					}
					saveZol(pEle1, name, desc);
				}
			}
		}

	}

	private static void saveZol(Elements pEle1, String name, String desc) {
		if (pEle1 == null) {
			return;
		}
		PackageTariff pT = new PackageTariff(UtilData.CTELECOM);
		String name2 = new String();
		for (Element pEle : pEle1) {
			// TODO save package
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
		if (!pHashZol.containsKey(name2)) {
			pHashZol.put(name2, pT);
		}

	}

	private String mEntry = "http://detail.zol.com.cn/phone_package/dianxin/";
	private ArrayList<String> mZolRegex;

	private void addDefaultRegex() {
		mZolRegex = new ArrayList<String>();
		mZolRegex.add("+^http://detail.zol.com.cn/phone_package/dianxin/");
		mZolRegex
				.add("+http://detail.zol.com.cn/phone_package/dianxin/[0-9]+.*");
		mZolRegex.add("+http://detail.zol.com.cn/phone_package/index.*");
	}

	public void fetchZol() {
		addDefaultRegex();
		CtrlCrawler mCC = new CtrlCrawler();
		mCC.addSeed(this.mEntry);
		mCC.setRoot("download");
		for (int i = 0; i < this.mZolRegex.size(); i++) {
			mCC.addRegex(this.mZolRegex.get(i));
		}
		try {
			mCC.start(3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO change to SAE
		// MysqlForPT mFPT = new MysqlForPT();
		// mFPT.insertPT(pHashZol);
//		SAEsqlForPT sFPT = new SAEsqlForPT();
//		sFPT.insertPT(pHashZol);
	}

}
