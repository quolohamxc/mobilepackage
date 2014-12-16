package com.mp.telecom.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;
import com.mp.model.mysql.MysqlForDebug;
import com.mp.telecom.SinglePkgInfo;
import com.mp.telecom.model.SinglePkgTar;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;
import com.mp.util.placedata.province.ProvNameAbbr;

/**
 * 易通纯流量（黑龙江）+易通城市版（黑龙江）， parseAnalyze方法中城市版手动添加了10元最低消费。
 * 
 * @author Administrator
 *
 */
public class SinglePkgParserB {

	private ArrayList<String> defaultUrl = null;

	public SinglePkgParserB() {
		addDefaultUrl();
	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl.add("http://www.189.cn/hl/support/Tariff/Package/13.html");
		defaultUrl.add("http://www.189.cn/products/101031630.html");
		defaultUrl.add("http://www.189.cn/products/101031600.html");
		defaultUrl.add("http://www.189.cn/products/101031381.html");
		defaultUrl.add("http://www.189.cn/products/101031781.html");
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
		Document doc = null;
		boolean flag = true;
		for(String url : defaultUrl) {
			try {
				doc = Jsoup.connect(url).get();
				parse(doc);
			} catch (IOException e) {
				flag = false;
			}
			if(!flag) {
				flag = true;
				try {
					new MysqlForDebug().debug("cannot connect "+ url);
				} catch (SQLException e) {
//					flag = false;
				} catch (NullPointerException e) {
//					flag = false;
				}
				new SinglePkgInfo().addDefaultPkg(SinglePkgInfo.YITONG);
			}
			
		}
		addDefaultSinglePkg();
	}

	private void addDefaultSinglePkg() {

	}

	/**
	 * 分析js执行后的网页
	 * 
	 * @param doc
	 */
	public void parse(Document doc) {
		Element itemE = doc.select("div[class=notice_content]").first();
		String title = new String();
		if (itemE != null) {
			Element tableE = itemE.select("table").first();
			Elements trES = tableE.select("tr");
			int line = 1;
			for (Element trE : trES) {
				if (line == 1) {

				} else if ((line % 2) == 0) {
					title = trE.select("td").first().text();
				} else if ((line % 2) == 1) {
					parseAnalyze(title, trE.text());
				}
				line++;
			}

			return;
		}

		itemE = doc.select("div[class=xiaoshou_right floatright]").first();
		if (itemE != null) {
			Element aT = itemE.select("span[id=articleshorttitle]").first();
			if (aT != null) {
				if (aT.text().contains("易通")) {
					title = "天翼易通卡城市版";
				}
			}
			Element bT = itemE.select("span[id=subtitle]").first();
			if (bT != null && bT.text() != "") {
				parseAnalyze(title, aT.text() + bT.text());
			}
			return;
		}
		return;
	}

	private void parseAnalyze(String title, String text) {
		if(text == "") {
			return;
		}
		try {
			String[] block = text.split(" ");
			PackageTariff pT = new PackageTariff();
			pT.setmType(UtilData.CTELECOM);

			if (block.length >= 9) {
				pT.setmMonthlyFee(Double.valueOf(UtilMethod.elimFix("来显",
						block[2], "元/月")));
				pT.setmDomInfoFlow(Double.valueOf(UtilMethod.elimFix("",
						block[3], "MB")));
				pT.setmDomCall(Double.valueOf(UtilMethod.elimFix("", block[5],
						"—")));
				pT.setmDomInfoBey(Double.valueOf(UtilMethod.elimFix("标准为",
						block[8], "元/10KB")) * 100);
				if (block[0].contains("城市")) {
					// 城市版5元月租包含10元最低消费（话费）
					// TODO 维护
					pT.setmMonthlyCons(-10);
				}
			} else {
				
				pT.setmMonthlyFee(Double.valueOf(UtilMethod.elimFix("易通卡",
						text, "元/月")));
				pT.setmDomInfoFlow(Double.valueOf(UtilMethod.elimFix("+",
						text, "M")));
				// 城市版本地主叫长市合一最低0.1元/分钟，省内0.002元/10KB
				// TODO 维护
				pT.setmDomCall(0.1);
				pT.setmDomInfoBey(0.2);
				pT.setmMonthlyCons((int) (Double.valueOf(UtilMethod.elimFix("含",
						text, "元话费")) / -1));
			}
			pT.setmTitle(title + String.valueOf((int)pT.getmMonthlyFee()) + "元");
			pT.setmLocation(ProvNameAbbr.HEILONGJIANG);
			new SinglePkgInfo().addSinglePkg(pT);
		} catch (NumberFormatException e) {
			// TODO send error
			return;
		}
	}

	public static void main(String[] args) {
		new SinglePkgParserB().parse();

		SinglePkgInfo sPI = new SinglePkgInfo();
		System.out.println(sPI.solve(500, 10, 200));
		for (SinglePkgTar sPT : sPI.singlePkgTar) {
			System.out.println(sPT.getpT().toString());
//			System.out.println(sPT.getPrice() + " " + sPT.getpT().toString());
		}

	}
}
