package com.mp.telecomzol;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.crawler.ControllerAbridged;
import com.mp.model.PackageTariff;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class TControllerAbridged extends ControllerAbridged{
	@Override
	protected void addDefaultRegex() {
		mZolRegex = new ArrayList<String>();
		mZolRegex
				.add("http://detail.zol.com.cn/phone_package/dianxin/[0-9]+.*");
		mZolRegex.add("http://detail.zol.com.cn/phone_package/[i-i]+.*");
	}
	
	public void fetchZol() {
		addDefaultRegex();
		this.traverse(this.mEntry);
		for (Document doc : getDocList()) {
			analyzeZol(doc);
		}
		//if(pHashZol.size() == 0) { new SAEsqlForPT().debug(); }
	}

	/**
	 * analyze htmls from Zol
	 * 
	 * @param page
	 * @throws IOException
	 */
	private void analyzeZol(Document doc) {
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
					saveZol(pEle1, name, desc);
				}
			}
		}
	}

	private void saveZol(Elements pEle1, String name, String desc) {
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
		if (!pHashZol.containsKey(name2)) {
			pHashZol.put(name2, pT);
		}

	}

	public static void main(String[] args) {
		
		long a=System.currentTimeMillis();
		//在最好的一行加上:

		TControllerAbridged robot = new TControllerAbridged();
		robot.fetchZol();
		
		System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");

	}
}

// http://detail.zol.com.cn/phone_package/dianxin/
// http://detail.zol.com.cn/phone_package/index374697.shtml
// http://detail.zol.com.cn/phone_package/index384946.shtml
// http://detail.zol.com.cn/phone_package/index374726.shtml
// http://detail.zol.com.cn/phone_package/index384944.shtml
// http://detail.zol.com.cn/phone_package/index384947.shtml
// http://detail.zol.com.cn/phone_package/index384949.shtml
// http://detail.zol.com.cn/phone_package/index384951.shtml
// http://detail.zol.com.cn/phone_package/index374728.shtml
// http://detail.zol.com.cn/phone_package/index374754.shtml
// http://detail.zol.com.cn/phone_package/index374732.shtml
// http://detail.zol.com.cn/phone_package/index374758.shtml
// http://detail.zol.com.cn/phone_package/index374759.shtml
// http://detail.zol.com.cn/phone_package/index374760.shtml
// http://detail.zol.com.cn/phone_package/index384945.shtml
// http://detail.zol.com.cn/phone_package/index384948.shtml
// http://detail.zol.com.cn/phone_package/index384953.shtml
// http://detail.zol.com.cn/phone_package/index384952.shtml
// http://detail.zol.com.cn/phone_package/index384950.shtml
// http://detail.zol.com.cn/phone_package/index374735.shtml
// http://detail.zol.com.cn/phone_package/index374755.shtml
// http://detail.zol.com.cn/phone_package/index374729.shtml
// http://detail.zol.com.cn/phone_package/index374757.shtml
// http://detail.zol.com.cn/phone_package/index374738.shtml
// http://detail.zol.com.cn/phone_package/index374733.shtml
// http://detail.zol.com.cn/phone_package/index374730.shtml
// http://detail.zol.com.cn/phone_package/dianxin/2.html
// http://detail.zol.com.cn/phone_package/index374697.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384946.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374726.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384944.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384947.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384949.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384951.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374728.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374754.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374732.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374758.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374759.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374760.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384945.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384948.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384953.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384952.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index384950.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374735.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374755.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374729.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374757.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374738.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374733.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374730.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374741.shtml
// http://detail.zol.com.cn/phone_package/index374740.shtml
// http://detail.zol.com.cn/phone_package/index374739.shtml
// http://detail.zol.com.cn/phone_package/index374736.shtml
// http://detail.zol.com.cn/phone_package/index374741.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374740.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374739.shtml#phoneNumListDiv
// http://detail.zol.com.cn/phone_package/index374736.shtml#phoneNumListDiv

