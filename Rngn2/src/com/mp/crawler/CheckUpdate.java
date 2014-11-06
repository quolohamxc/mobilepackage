package com.mp.crawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.util.UtilData;
import com.mp.util.place.PlaceNameAbbr;

public class CheckUpdate {
	public static HashMap<String, String> _recordCT;
	static {
		_recordCT = new HashMap<String, String>();
	}

	public boolean check(int mType) {
		boolean changed = false;
		if (mType == UtilData.CTELECOM) {
			String s1, s2;
			for (String placeAbbr : PlaceNameAbbr._placeList) {
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
					 * gd sc yn �������ط�����վ��͸¶��һ�������Ϣ
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
					if (nav_tel_txt.indexOf("�ײ�") < 0) {
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
			// ��young�ײͣ�ȫ����
			if (key.indexOf("��Young") >= 0 || key.indexOf("�ƿ�") >= 0
					|| key.indexOf("��young") >= 0) {
				return UtilData.FEIYONG;
			}
			// ����3G/4G��ȫ����
			if (key.indexOf("����") >= 0) {
				return UtilData.LEXIANG;
			}
			// ��ľ�ײͣ�ȫ����
			if (key.indexOf("��ľ") >= 0) {
				return UtilData.JIMU;
			}
			// ��ͨ����ȫ����
			if (key.indexOf("��ͨ") >= 0) {
				return UtilData.YITONG;
			}
			// e8/e9/e6�̻��ײͣ�����
			if (Pattern.matches(".*e[0-9].*", key) && key.indexOf("hone") < 0) {
				// avoid iphone5s
				return UtilData.USELESS;
			}
			// ������������
			if (key.indexOf("������") >= 0) {
				return UtilData.USELESS;
			}
			// ���˶���/���˶��ƣ�ȫ����
			if (key.indexOf("���˶���") >= 0 || key.indexOf("���˶���") >= 0) {
				return UtilData.DINGZHI;
			}
			// 4G������ȡ��������
			if (key.indexOf("4G") >= 0 && key.indexOf("����") < 0
					&& key.indexOf("���˶���") < 0 && key.indexOf("���˶���") < 0
					&& key.indexOf("��Young") < 0 && key.indexOf("��young") < 0
					&& key.indexOf("�ƿ�") < 0 && key.indexOf("������") < 0
					&& key.indexOf("һ�廯") < 0 || key.indexOf("��") >= 0) {
				return UtilData.USELESS;
			}
			// ���࣬��¼�Ƿ����
			if (key.indexOf("����") >= 0) {
				return UtilData.USELESS;
			}
			// iphone5s�ײͣ�����
			if (key.indexOf("hone") >= 0) {
				return UtilData.USELESS;
			}
			return key;
		}
		return key;
	}
	
	public void checkAndUpdate() {
		if(check(UtilData.CTELECOM)) {
			System.out.println("saf");
		}
	}
	
	public void update(int mType) {
		if (mType == UtilData.CTELECOM) {
		
		}
	}
	public static void main(String args[]) {
		new CheckUpdate().checkAndUpdate();
		return;
	}
}
