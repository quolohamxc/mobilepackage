package com.mp.telecom.parser;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.telecom.AppPkgInfo;
import com.mp.telecom.model.AppPkgFuel;
import com.mp.util.UtilMethod;

/**
 * 增值套餐。
 * @author Floion Z
 *
 */
public class AppPkgParser {
	/**
	 * 被爬取网页使用js获取内部数据，因此能更好的支持实时更新。
	 */
	private ArrayList<String> defaultUrl = null;

	public AppPkgParser() {
		addDefaultUrl();
	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl
				.add("http://kzone.zhidao.189.cn/basetypeShow.do?bid=10069&typeid=10070&id=1938&name=");
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
		Document doc = Jsoup.parse(UtilMethod.getHtmlUnit(url));
		parse(doc);
		addDefaultAppPkg();
	}

	private void addDefaultAppPkg() {

	}
	

	private void parse(Document doc) {
		Element itemE = doc.select("div[class=itembox]").first();
		if (itemE == null) {
			return;
		}
		Elements tableE = doc.select("table");
		for (Element table : tableE) {
			String type = "";
			double fee = 0.0, involve = 0.0;
			Elements lineE = table.select("tr");
			int cnt = 1;
			for(Element line : lineE) {
				if(cnt++ == 1) {
					String text = line.text();
					if (text.contains("流量")) {
						type = AppPkgInfo.INFO;
					} else if (text.contains("语音") || text.contains("通话")) {
						type = AppPkgInfo.CALL;
					} else if (text.contains("短信") || text.contains("彩信")) {
						type = AppPkgInfo.MSG;
					}
				} else {
					String[] text = line.text().split(" ");
					if(text.length < 2) {
						continue;
					}
					fee = parseAnalyze(text[0]);
					involve = parseAnalyze(text[1]);
					new AppPkgInfo().addAppPkg(type, fee, involve);
					System.out.println(new AppPkgFuel(type, fee, involve).toString());
//					System.out.println(type + " " + fee + " " + involve);
				}
			}
		}
	}
	
	/**
	 * 分析单句
	 * 
	 * @param text
	 * @return value
	 */
	private double parseAnalyze(String text) {
		double value = 0.0;
		try {
			if (UtilMethod.isNumeric(text)) {
				value = Double.valueOf(text);
			} else {
				if (text.contains("MB") || text.contains("M")) {
					value = Double.valueOf(UtilMethod.elimPostfix(text, "M"));
				} else if (text.contains("GB") || text.contains("G")) {
					value = Double.valueOf(UtilMethod.elimPostfix(text, "G")) * 1000;
				} else if (text.contains("元")) {
					value = Double.valueOf(UtilMethod.elimPostfix(text, "元"));
				} else if (text.contains("分钟")) {
					value = Double.valueOf(UtilMethod.elimPostfix(text, "分"));
				} else if (text.contains("条")) {
					value = Double.valueOf(UtilMethod.elimPostfix(text, "条"));
				} else {
					value = Double.valueOf(text);
				}
			}
		} catch (NumberFormatException e) {
			return 0.0;
		}
		return value;
	}
	
	public static void main(String[] args) {
		new AppPkgParser().parse();
	}
}
