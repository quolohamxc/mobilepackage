package com.mp.telecom.parser;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.telecom.NonPkgInfo;
import com.mp.util.UtilMethod;

/**
 * 个人定制
 * @author Administrator
 *
 */
public class NonPkgParser {
	/**
	 * 被爬取网页使用js获取内部数据，因此能更好的支持实时更新。
	 */
	private ArrayList<String> defaultUrl = null;

	public NonPkgParser() {
		addDefaultUrl();
	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl
				.add("http://kzone.zhidao.189.cn/baseshow.do?bid=10379&title=&kid=5845&tid=10381");
		defaultUrl.add("http://www.189.cn/products/9136127953.html");
		defaultUrl.add("http://www.189.cn/products/9136119363.html");
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
		addDefaultNonPkg();
		// TODO 处理其它网页验证
	}

	private void addDefaultNonPkg() {

	}

	/**
	 * 分析js执行后的网页
	 * @param doc
	 */
	public void parse(Document doc) {
		Element itemE = doc.select("div[class=itembox]").first();
		if (itemE == null) {
			return;
		}
		Elements tableE = doc.select("table");
		for (Element table : tableE) {
			String type = "";
			double start = 0.0, end = 0.0, cost = 0.0;
			Elements lineE = table.select("tr");
			int cnt = 1;
			for (Element line : lineE) {
				if (cnt++ == 1) {
					String text = line.text();
					if (text.contains("流量")) {
						type = NonPkgInfo.INFO;
					} else if (text.contains("语音") || text.contains("通话")) {
						type = NonPkgInfo.CALL;
					} else if (text.contains("短信") || text.contains("彩信")) {
						type = NonPkgInfo.MSG;
					}
				} else {
					// System.out.println(line.text());
					String[] text = line.text().split(" ");
					cost = parseAnalyze(text[1]);
					if (text.length != 2) {
						// System.out.println(text[4]);
						cost = parseAnalyze(text[4]);
					}
					String[] first = text[0].split("-");
					if (first.length != 2) {
						continue;
					}
					start = parseAnalyze(first[0]);
					end = parseAnalyze(first[1]);
					if (start * end * cost != 0) {
						new NonPkgInfo().addNonPkg(type, start, end, cost);
						// System.out.println(type + " " + start + " " + end +
						// " "
						// + cost);
					}
				}
			}
			// System.out.println(table.toString());
		}
		// System.out.println(itemE.toString());
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
				} else if (text.contains("）")) {
					value = Double.valueOf(UtilMethod.elimPrefix("）", text));
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
		// new NonPkgParser().parse();
		System.out.println(new NonPkgInfo().solve(1200, 600, 1024));
		System.out.println(new NonPkgInfo().solve(300, 300, 1100));

	}
}
