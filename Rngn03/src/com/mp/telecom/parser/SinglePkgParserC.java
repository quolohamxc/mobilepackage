package com.mp.telecom.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;
import com.mp.telecom.SinglePkgInfo;
import com.mp.telecom.model.SinglePkgTar;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;
import com.mp.util.placedata.city.HeiLongJiangChn;
import com.mp.util.placedata.city.HeiLongJiangFull;
import com.mp.util.placedata.province.ProvNameAbbr;

/**
 * 飞Young
 * 
 * @author Administrator
 *
 */
public class SinglePkgParserC {
	/**
	 * 被爬取网页使用js获取内部数据，因此能更好的支持实时更新。
	 */
	private ArrayList<String> defaultUrl = null;

	public SinglePkgParserC() {
		addDefaultUrl();
	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl
				.add("http://www.189.cn/hl/support/tariff/package/20689.html");
	}

	public void addUrl(String url) {
		defaultUrl.add(url);
	}

	/**
	 * 默认入口。
	 */
	public void parse() {
		if (defaultUrl == null) {
			addDefaultUrl();
		}
		String url = defaultUrl.get(0);
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			return;
		}
		parse(doc);
		addDefaultSinglePkg();
	}

	private void addDefaultSinglePkg() {

	}

	/**
	 * 
	 * @param doc
	 */
	public void parse(Document doc) {
		Elements itemES = doc.select("table[class=setcn_tb]");
		if (itemES == null) {
			return;
		}
		for (Element itemE : itemES) {
			Elements trES = itemE.select("tr");
			String title = "飞Young套餐";
			for (Element trE : trES) {
				String tdF = trE.select("td").first().text();
				String tdL = trE.select("td").last().text();
				if (tdF == tdL) {
					continue;
				}
				int index = HeiLongJiangChn._placeList.indexOf(tdF);

				if (index >= 0) {
					parseAnalyze(title, tdL, index);
				}
			}
		}
		// System.out.println(itemE.toString());
	}

	/**
	 * 分析语句
	 * 
	 * @param index
	 * @param title
	 * @param text
	 */
	private void parseAnalyze(String title, String text, int index) {
		if (index < 0 || title == null || title == "" || text == "") {
			return;
		}
		String optt = new String(title);
		String opt = new String(text);
		double opd = 0.0;
		try {
			PackageTariff pT = new PackageTariff();

			opd = Double.valueOf(UtilMethod.elimFix("基本费", opt, "元"));
			pT.setmMonthlyFee(opd);
			optt += String.valueOf((int) (opd)) + "元";
			pT.setmTitle(optt);
			opt = UtilMethod.elimPrefix("元", opt);

			opd = Double.valueOf(UtilMethod.elimFix("主叫", opt, "分钟"));
			pT.setmDomCallDur((int) opd);
			opt = UtilMethod.elimPrefix("分钟", opt);

			opd = Double.valueOf(UtilMethod.elimFix("短信", opt, "条"));
			pT.setmDomMsg((int) opd);
			opt = UtilMethod.elimPrefix("条", opt);

			opd = Double.valueOf(UtilMethod.elimFix("流量", opt, "M"));
			pT.setmDomInfoFlow(opd);
			opt = UtilMethod.elimPrefix("M", opt);

			opd = Double.valueOf(UtilMethod.elimFix("国内长途", opt, "元/分钟"));
			pT.setmDomCall(opd);
			opt = UtilMethod.elimPrefix("元/分钟", opt);

			pT.setmLocation(ProvNameAbbr.HEILONGJIANG);
			pT.setmCity(HeiLongJiangFull._placeList.get(index));

			pT.setStep(true);
			pT.setSteps(20, 0, 200, 0.15, UtilData.MAXLIMIT, 0.06);
			new SinglePkgInfo().addSinglePkg(pT);
			pT.setmAddition(UtilMethod.elimPrefix("元/条", opt)
					+ UtilMethod.elimPrefix(opt, "国内上网"));
//			System.out.println(pT.toString());

		} catch (NumberFormatException e) {
			return;
		}
		return;
	}

	public static void main(String[] args) {
		new SinglePkgParserC().parse();
		SinglePkgInfo sPI = new SinglePkgInfo();
		System.out.println(sPI.solve(500, 10, 200));
		for (SinglePkgTar sPT : sPI.singlePkgTar) {
			System.out.println(sPT.getpT().toString());
//			System.out.println(sPT.getPrice() + " " + sPT.getpT().toString());
		}
	}
}
