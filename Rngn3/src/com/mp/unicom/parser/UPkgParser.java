package com.mp.unicom.parser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;
import com.mp.model.mysql.MysqlForDebug;
import com.mp.unicom.USinglePkgInfo;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class UPkgParser {
	private ArrayList<String> defaultUrl = null;

	public UPkgParser() {
		addDefaultUrl();
	}

	private void addDefaultUrl() {
		defaultUrl = new ArrayList<String>();
		defaultUrl
				.add("http://info.10010.com/database/3gindex/3gfee/A_plan.html");
		defaultUrl
				.add("http://info.10010.com/database/3gindex/3gfee/B_plan.html");
		defaultUrl
				.add("http://info.10010.com/database/3gindex/3gfee/C_plan.html");
//		defaultUrl
//				.add("http://info.10010.com/database/3gindex/3gfee/iPhone_plan.html");
//		defaultUrl
//				.add("http://info.10010.com/database/3gindex/3gfee/yu_plan.html");
//		// defaultUrl
//		// .add("http://info.10010.com/database/3gindex/3gfee/wirelesscard.html");
//		defaultUrl
//				.add("http://info.10010.com/database/4gindex/4gfee/4G_plan.html");
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
		for (String url : defaultUrl) {
			try {
				doc = Jsoup.connect(url).get();
				if (url.contains("3g")) {
					parse(doc, USinglePkgInfo.THREEG);
				} else if (url.contains("4g")) {
					parse(doc, USinglePkgInfo.FOURG);
				} else {
					flag = false;
				}

			} catch (IOException e) {
				flag = false;
			}
			if (!flag) {
				flag = true;
				try {
					new MysqlForDebug().debug("cannot connect " + url);
				} catch (NullPointerException | SQLException e) {
					// flag = false;
				}
				new USinglePkgInfo().addDefaultPkg();
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
	 * @param type
	 */
	public void parse(Document doc, String type) {
		Element nameE = doc.select("li[class=selected]").first();
		Elements titles = doc.select("div[class=title]");
		Elements tables = doc.select("div[class=table_content]");
		if (nameE != null && titles != null && tables != null) {
			String name1 = nameE.text().trim();
			if (tables.size() == 2) {
				Element table = tables.first();
				parse(name1, table, type);
			} else if (name1.contains("预付费")) {
				for (int iter = 0, jter = 0; iter < tables.size() - 1; iter++, jter++) {
					if (tables.get(iter).select("tr").size() == 0) {
						jter--;
						continue;
					}
					Element table = tables.get(iter);

					int tempCount = 0, tempIndex = 0;
					for (; tempCount <= jter; tempIndex++) {
						Element title = titles.get(tempIndex);
						if (!(title.text().contains("温馨提示")
								|| title.text().contains("什么是") || title.text()
								.contains("标准资费"))) {
							tempCount++;
						}
						if (tempCount > jter) {
							break;
						}
					}
					String name2 = titles.get(tempIndex).text();
					// System.out.println(name2);
					// System.out.println(titles.size());
					parse(name2, table, USinglePkgInfo.THREEGY);

				}
			} else if (name1.contains("上网卡")) {
				// for (Element title : titles) {
				// System.out.println(title.text());
				// }
				// System.out.println();
				// for (int iter = 0, jter = 0; iter < tables.size(); iter++,
				// jter++) {
				// if (tables.get(iter).select("tr").size() == 0) {
				// jter--;
				// continue;
				// }
				// Element table = tables.get(iter);
				// Double temp = (double)
				// table.select("tr td").first().text().length();
				// if(temp <= 1 ||
				// table.select("tr td").first().text().contains("区间")) {
				// jter--;
				// continue;
				// }
				// int tempCount = 0, tempIndex = 1;
				// for (; tempCount < jter; tempIndex++) {
				// Element title = titles.get(tempIndex);
				// if (!(title.text().contains("温馨提示")
				// || title.text().contains("什么是") || title.text()
				// .contains("资费"))) {
				// tempCount++;
				// }
				// if (tempCount == jter) {
				// break;
				// }
				// }
				// String name2 = titles.get(tempIndex).text();
				// // System.out.println(name2);
				// // System.out.println(titles.size());
				// parse(name2, table, USinglePkgInfo.THREEGW);
				//
				// }
			} else {

			}
//			System.out.println(name1);
			return;
		}
	}

	private void parse(String title, Element table, String type) {
		if (table == null) {
			return;
		}
		if (type == USinglePkgInfo.THREEG || (type == USinglePkgInfo.THREEGA)
				|| (type == USinglePkgInfo.THREEGB)
				|| type == USinglePkgInfo.THREEGC) {
			int limit = 15;
			PackageTariff pT;
			for (int iter = 0, jter = 0; iter < limit; iter++, jter++) {
				pT = new PackageTariff();
				pT.setmType(UtilData.CUNICOM);
				pT.setmTitle(title);
				pT.setmLocation(PlaceData.QUANGUO);
				pT.setmDomCall(0.36);
				pT.setmDomLisBey(0);
				pT.setmDomInfoBey(0.3);
				boolean tempBeyond = false;
				for (Element tr : table.select("tr")) {
					int tempCount = 0, tempIndex = -1, ptType = 0;
					tempBeyond = (tr.select("td").first().text()
							.contains("其它"))
							|| (tr.select("td").first().text().contains("其他"))
							|| (tr.select("td").first().text().contains("超出")) || tempBeyond;
					for (Element td : tr.select("td")) {
						double pa = parseAnalyze(td.text());
						tempIndex++;
						if (pa == UtilData.INVALID) {
							if (td.text().contains("月费")) {
								ptType = 1;
							} else if (td.text().contains("语音")) {
								ptType = 2;
							} else if (td.text().contains("流量")) {
								ptType = 3;
							} else if (td.text().contains("短信")) {
								ptType = 4;
							}
							if (tempBeyond) {
								ptType += 10;
							}
							continue;
						}
						if (tempCount == jter) {
							break;
						}
						tempCount++;
					}

					Element td = tr.select("td").get(tempIndex);
					double pa = parseAnalyze(td.text());
					if (pa == UtilData.INVALID) {

						if (td.text().contains("月费")) {
							ptType = 1;
						} else if (td.text().contains("语音")) {
							ptType = 2;
						} else if (td.text().contains("流量")) {
							ptType = 3;
						} else if (td.text().contains("短信")) {
							ptType = 4;
						}
						if (tempBeyond) {
							ptType += 10;
						}
						continue;
					} else {
						if (ptType == 1) {
							pT.setmMonthlyFee(pa);
						} else if (ptType == 2) {
							pT.setmDomCallDur((int) pa);
						} else if (ptType == 3) {
							pT.setmDomInfoFlow(pa);
						} else if (ptType == 4) {
							pT.setmDomMsg((int) pa);
						} else if (ptType == 11) {
							// pT.setmMonthlyCons(pa);
						} else if (ptType == 12) {
							pT.setmDomCall(pa);
						} else if (ptType == 13) {
							pT.setmDomInfoBey(pa);
						} else if (ptType == 14) {

						}
					}

				}
				pT.setmTitle(title + String.valueOf((int) pT.getmMonthlyFee())
						+ "元");
				new USinglePkgInfo().addSinglePkg(pT);
			}
		} else if (type == USinglePkgInfo.FOURG) {
			for (Element tr : table.select("tr")) {
				PackageTariff pT = new PackageTariff();
				pT.setmType(UtilData.CUNICOM);
				pT.setmTitle(title);
				pT.setmLocation(PlaceData.QUANGUO);
				pT.setmDomLisBey(0);
				pT.setmDomCall(0.15);
				pT.setStep(true);
				pT.setSteps(200, 0.3, UtilData.MAXLIMIT, 0.06);
				double temp = parseAnalyze(tr.select("td").first().text());
				if (temp == UtilData.INVALID) {
					continue;
				} else {
					double pa;
					pa = parseAnalyze(tr.select("td").get(0).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmMonthlyFee(pa);
					pa = parseAnalyze(tr.select("td").get(1).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmDomCallDur((int) pa);
					pa = parseAnalyze(tr.select("td").get(2).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmDomInfoFlow(pa);
				}
				pT.setmTitle(title + String.valueOf((int) pT.getmMonthlyFee())
						+ "元");
				new USinglePkgInfo().addSinglePkg(pT);
			}

		} else if (type == USinglePkgInfo.THREEGY) {
			int ptType = 0;
			for (Element tr : table.select("tr")) {
				PackageTariff pT = new PackageTariff();
				pT.setmType(UtilData.CUNICOM);
				pT.setmTitle(title);
				pT.setmLocation(PlaceData.QUANGUO);
				pT.setmDomLisBey(0);
				pT.setmDomInfoBey(0.3);
				double temp = parseAnalyze(tr.select("td").first().text());
//				System.out.println(temp);
//				System.out.println(ptType);
				if (temp == UtilData.INVALID) {
					if (tr.select("td").first().text().contains("最低")) {
						ptType = 1;
					} else {
						ptType = 2;
					}
					continue;
				} else if (ptType == 1) {
					double pa;
					pa = parseAnalyze(tr.select("td").get(0).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmMonthlyFee(pa);
					pT.setmMonthlyCons(0 - pa);
					pa = parseAnalyze(tr.select("td").get(1).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmDomCall(pa);
					pa = parseAnalyze(tr.select("td").get(3).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmDomInfoBey(pa * 1000);
				} else if (ptType == 2) {
					double pa;
					pa = parseAnalyze(tr.select("td").get(0).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmMonthlyFee(pa);
					pa = parseAnalyze(tr.select("td").get(1).text());
					if (pa == UtilData.INVALID) {
						continue;
					}
					pT.setmDomCallDur((int) pa);
					pa = parseAnalyze(tr.select("td").get(2).text());
					if (pa != UtilData.INVALID) {
						pT.setmDomInfoFlow(pa);
						pa = parseAnalyze(tr.select("td").get(3).text());
						if (pa != UtilData.INVALID) {
							pT.setmDomMsg((int) pa);
							pa = parseAnalyze(tr.select("td").get(5).text());
							if (pa == UtilData.INVALID) {
								continue;
							}
							pT.setmDomCall(pa);
							pa = parseAnalyze(tr.select("td").get(6).text());
							if (pa == UtilData.INVALID) {
								continue;
							}
							pT.setmDomInfoBey(pa);
						} else {
							pa = parseAnalyze(tr.select("td").get(4).text());
							if (pa == UtilData.INVALID) {
								continue;
							}
							pT.setmDomCall(pa);
							pa = parseAnalyze(tr.select("td").get(5).text());
							if (pa == UtilData.INVALID) {
								continue;
							}
							pT.setmDomInfoBey(pa);
						}
					} else {
						pa = parseAnalyze(tr.select("td").get(1).text());
						if (pa == UtilData.INVALID) {
							continue;
						}
						pT.setmDomInfoFlow(pa);
						pa = parseAnalyze(tr.select("td").get(3).text());
						if (pa == UtilData.INVALID) {
							continue;
						}
						pT.setmDomCall(pa);
					}
				}
				pT.setmTitle(title + String.valueOf((int) pT.getmMonthlyFee())
						+ "元");
				new USinglePkgInfo().addSinglePkg(pT);
			}

		} else {

		}

	}

	private double parseAnalyze(String desc) {
		boolean flag = true;
		double multiplying = 1, rs = 0;
		if (desc.contains("GB")) {
			multiplying *= 1000;
		} else if (desc.contains("元/KB")) {
			multiplying *= 1000;
		} else if ((!desc.contains("元/KB")) && desc.contains("KB")) {
			multiplying /= 1000;
		}
		String descElim = UtilMethod.elimFix("", desc, "MB");
		descElim = UtilMethod.elimFix("", descElim, "GB");
		descElim = UtilMethod.elimFix("", descElim, "元/分钟");
		descElim = UtilMethod.elimFix("", descElim, "元/KB");
		descElim = UtilMethod.elimFix("", descElim, "元/条");
		descElim = UtilMethod.elimFix("", descElim, "元/月");
		descElim = UtilMethod.elimFix("", descElim, "元");
		try {
			rs = Double.valueOf(descElim);
		} catch (NumberFormatException e) {
			flag = false;
		}
		if (flag == true) {
			return rs * multiplying;
		} else {
			return UtilData.INVALID;
		}
	}

	public static void main(String[] args) {
		new UPkgParser().parse();
		// int size = USinglePkgInfo.getSinglePkgList().size();
		for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
			 System.out.println(pT.toString());
		}

		System.out.println(USinglePkgInfo.getSinglePkgList().size());
	}
}
