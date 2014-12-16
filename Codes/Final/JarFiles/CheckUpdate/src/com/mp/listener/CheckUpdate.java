package com.mp.listener;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.util.UtilData;
import com.mp.util.placedata.province.ProvNameAbbr;

public class CheckUpdate {
	public static HashMap<String, String> _recordCT;
	public static boolean hasUpdate = false;
	
	static {
		_recordCT = new HashMap<String, String>();
	}

	public boolean check(int mType) {
		boolean changed = false;
		if (mType == UtilData.CTELECOM) {
			String s1, s2;
			for (String placeAbbr : ProvNameAbbr._placeList) {
				String mEntry = "http://www.189.cn/" + placeAbbr + "/";
				boolean flag = true;
				Document document = null;
				try {
					document = Jsoup.connect(mEntry).timeout(5000).get();
				} catch (IOException e) {
					flag = false;
				}
				if (!flag) {
					continue;
				}
				Element ele = document
						.select("div[class=list list2 quick_nav]").first();
				if (ele == null) {
					/*
					 * gd sc yn 这三个地方连主站都透露着一股乡村气息
					 */
					// System.out.println(placeAbbr);
					continue;
				}
				Elements euls = ele.select("ul[class=left_nav_tit]");
				if (euls == null) {
					continue;
				}
				for (Element eul : euls) {
					String nav_tel_txt = eul.select("li[class=nav_teleft_txt]")
							.text();
					if (nav_tel_txt.indexOf("套餐") < 0) {
						continue;
					}
					Element eft = eul.select("li[class=nav_teleft_txt]")
							.first();
					if (eft == null) {
						continue;
					}
					Elements eas = eft.select("a[href]");
					for (Element ea : eas) {
						s1 = analyzeName(mType, ea.text());
						if (!_recordCT.containsKey(s1)) {
							s2 = ea.attr("href");
							_recordCT.put(s1, s2);
							changed = true;
						}
					}
					break;
				}
			}
		}
		return changed;
	}

	public String analyzeName(int mType, String key) {
		if (mType == UtilData.CTELECOM) {
			// 飞young套餐（全国）
			if (key.indexOf("飞Young") >= 0 || key.indexOf("云卡") >= 0
					|| key.indexOf("飞young") >= 0) {
				return UtilData.FEIYONG;
			}
			// 乐享3G/4G（全国）
			if (key.indexOf("乐享") >= 0) {
				return UtilData.LEXIANG;
			}
			// 积木套餐（全国）
			if (key.indexOf("积木") >= 0) {
				return UtilData.JIMU;
			}
			// 易通卡（全国）
			if (key.indexOf("易通") >= 0) {
				return UtilData.YITONG;
			}
			// e8/e9/e6固话套餐，不用
			if (Pattern.matches(".*e[0-9].*", key) && key.indexOf("hone") < 0) {
				// avoid iphone5s
				return UtilData.USELESS;
			}
			// 上网卡，不用
			if (key.indexOf("上网卡") >= 0) {
				return UtilData.USELESS;
			}
			// 个人定制/个人订制（全国）
			if (key.indexOf("个人定制") >= 0 || key.indexOf("个人订制") >= 0) {
				return UtilData.DINGZHI;
			}
			// 4G（已爬取），不用
			if (key.indexOf("4G") >= 0 && key.indexOf("乐享") < 0
					&& key.indexOf("个人定制") < 0 && key.indexOf("个人订制") < 0
					&& key.indexOf("飞Young") < 0 && key.indexOf("飞young") < 0
					&& key.indexOf("云卡") < 0 && key.indexOf("上网卡") < 0
					&& key.indexOf("一体化") < 0 || key.indexOf("升") >= 0) {
				return UtilData.USELESS;
			}
			// 更多，记录是否更新
			if (key.indexOf("更多") >= 0) {
				return UtilData.USELESS;
			}
			// iphone5s套餐，不用
			if (key.indexOf("hone") >= 0) {
				return UtilData.USELESS;
			}
			return key;
		}
		return key;
	}
	
	public void checkAndCallUpdate() {
		hasUpdate = check(UtilData.CTELECOM);
		if(hasUpdate) {
			// TODO callUpdate 
			new InitServer().parse();
			//System.out.println("saf");
		}
	}
	
	public void update(int mType) {
		if (mType == UtilData.CTELECOM) {
		
		}
	}
	public static void main(String[] args) {
		new CheckUpdate().checkAndCallUpdate();
		new CheckUpdate().checkAndCallUpdate();
		return;
	}
}
