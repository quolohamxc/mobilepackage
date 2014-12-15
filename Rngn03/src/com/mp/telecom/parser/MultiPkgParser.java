package com.mp.telecom.parser;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.telecom.MultiPkgInfo;
import com.mp.util.UtilMethod;
/**
 * ��ľ�ײ�
 * @author Administrator
 *
 */
public class MultiPkgParser {
	/**
	 * ����ȡ��ҳʹ��js��ȡ�ڲ����ݣ�����ܸ��õ�֧��ʵʱ���¡�
	 */
	private ArrayList<String> defaultUrl = null;

	public MultiPkgParser() {
		addDefaultUrl();

	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl
				.add("http://kzone.zhidao.189.cn/basetypeShow.do?bid=10069&typeid=10073&id=1928&name=%25CC%25D7%25B2%25CD%25BD%25E9%25C9%25DC");
		defaultUrl
				.add("http://kzone.zhidao.189.cn/basetypeShow.do?bid=10069&typeid=10073&id=1931&name=%25CC%25D7%25B2%25CD%25BD%25E9%25C9%25DC");
		defaultUrl
				.add("http://kzone.zhidao.189.cn/basetypeShow.do?bid=10069&typeid=10073&id=1930&name=%25CC%25D7%25B2%25CD%25BD%25E9%25C9%25DC");
	}

	public void addUrl(String url) {
		defaultUrl.add(url);
	}

	/**
	 * Ĭ����ڡ�
	 */
	public void parse() {
		if (defaultUrl == null) {
			addDefaultUrl();
		}
		boolean flag = true;
		String url = defaultUrl.get(0);
		Document doc = Jsoup.parse(UtilMethod.getHtmlUnit(url));
		flag &= parse(doc);

		url = defaultUrl.get(1);
		doc = Jsoup.parse(UtilMethod.getHtmlUnit(url));
		flag &= parse(doc);

		url = defaultUrl.get(2);
		doc = Jsoup.parse(UtilMethod.getHtmlUnit(url));
		flag &= parse(doc);

		if (flag) {
			addDefaultMultiPkg();
		} else {
			MultiPkgInfo.multiPkgList = null;
		}
		// TODO ����������ҳ��֤
	}

	private void addDefaultMultiPkg() {
		new MultiPkgInfo().addMultiPkg(MultiPkgInfo.MSG, 0, 0, 0.10);
		new MultiPkgInfo().addMultiPkg(MultiPkgInfo.INFO, 0, 0, 0.30);
	}

	/**
	 * ����jsִ�к����ҳ
	 * 
	 * @param doc
	 */
	public boolean parse(Document doc) {
		Element itemE = doc.select("div[class=itembox]").first();
		if (itemE == null) {
			return false;
		}

		Elements tableE = doc.select("table");
		for (Element table : tableE) {
			String type = "";
			double time = 0.00, cost = 0.00, beyond = 0.00;
			Elements lineE = table.select("tr");
			int cnt = 1;
			for (Element line : lineE) {
				if (cnt == 1) {
					cnt++;
					continue;
				}
				if (cnt++ == 2) {
					String text = line.text();
					if (text.contains("����")) {
						type = MultiPkgInfo.INFO;
						beyond = parseAnalyze(line.text());
					} else if (text.contains("����") || text.contains("ͨ��")) {
						type = MultiPkgInfo.CALL;
						beyond = parseAnalyze(line.text());
					} else if (text.contains("����") || text.contains("����")) {
						type = MultiPkgInfo.MSG;
						beyond = parseAnalyze(line.text());
					}
				} else {
					// System.out.println(line.text());
					String[] text = line.text().split(" ");
					if (text.length != 2) {

					}
					cost = parseAnalyze(text[0]);
					time = parseAnalyze(text[1]);
					if (cost * time != 0) {
						new MultiPkgInfo()
								.addMultiPkg(type, time, cost, beyond);
						// System.out.println(type + " " + time + " " + cost +
						// " "
						// + beyond);
					}
				}
			}
			// System.out.println(table.toString());
		}
		return true;
	}

	/**
	 * ��������
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
				if (text.length() >= 10) {
					if (text.contains("����")) {
						value = Double.valueOf(UtilMethod.elimFix("��������", text,
								"Ԫ/KB")) * 1000;
					} else if (text.contains("����") || text.contains("ͨ��")) {
						value = Double.valueOf(UtilMethod.elimFix("���ز������",
								text, "Ԫ/���ӣ�"));
					} else if (text.contains("����") || text.contains("����")) {
						value = Double.valueOf(UtilMethod.elimFix("���ڶ���", text,
								"Ԫ/��"));
					} else {
						value = Double.valueOf(text);
					}
				} else {
					if (text.contains("Ԫ")) {
						value = Double.valueOf(UtilMethod
								.elimPostfix(text, "Ԫ"));
					} else if (text.contains("����")) {
						value = Double.valueOf(UtilMethod.elimPostfix(text,
								"����"));
					} else if (text.contains("��")) {
						value = Double.valueOf(UtilMethod
								.elimPostfix(text, "��"));
					} else if (text.contains("MB")) {
						value = Double.valueOf(UtilMethod.elimPostfix(text,
								"MB"));
					} else if (text.contains("GB")) {
						value = Double.valueOf(UtilMethod.elimPostfix(text,
								"GB")) * 1000;
					} else {
						value = Double.valueOf(text);
					}
				}

			}
		} catch (NumberFormatException e) {
			return 0.0;
		}
		return value;
	}

	public static void main(String[] args) {
		new MultiPkgParser().parse();
		System.out.println(MultiPkgInfo.multiPkgList.size());
		System.out.println(new MultiPkgInfo().solve(300, 300, 1100));
	}
}
