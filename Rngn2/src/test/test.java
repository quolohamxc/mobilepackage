package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mp.model.PackageTariff;
import com.mp.util.place.PlaceNameAbbr;
import com.mp.util.place.PlaceNameChinese;
import com.mp.util.place.PlaceNameFull;

//                   _ooOoo_
//                  o8888888o
//                  88" . "88
//                  (| -_- |)
//                  O\  =  /O
//               ____/`---'\____
//             .'  \\|     |//  `.
//            /  \\|||  :  |||//  \
//           /  _||||| -:- |||||-  \
//           |   | \\\  -  /// |   |
//           | \_|  ''\---/''  |   |
//           \  .-\__  `-`  ___/-. /
//         ___`. .'  /--.--\  `. . __
//      ."" '<  `.___\_<|>_/___.'  >'"".
//     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//     \  \ `-.   \_ __\ /__ _/   .-` /  /
//======`-.____`-.___\_____/___.-`____.-'======
//                   `=---='
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//         ���汣��       ����BUG

/**
 * 
 * @author Administrator
 *
 */
public class test {
	public static void main(String args[]) {

		HashMap<String, String> h = new HashMap<String, String>();
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
			if (flag) {
				Element ele = document
						.select("div[class=list list2 quick_nav]").first();
				// System.out.println(ele.toString());
				if (ele == null) {
					/*
					 * gd sc yn �������ط�����վ��͸¶��һ�������Ϣ
					 */
					// System.out.println(placeAbbr);
					continue;
				}
				Elements euls = ele.select("ul[class=left_nav_tit]");
				// if (euls == null) {
				// /*
				// * �����ⲽû����
				// */
				// System.out.println(placeAbbr);
				// continue;
				// }
				for (Element eul : euls) {
					String nav_tel_txt = eul.select("li[class=nav_teleft_txt]")
							.text();
					if (nav_tel_txt.indexOf("�ײ�") < 0) {
						continue;
					}
					// System.out.println("ok!findit");
					Element eft = eul.select("li[class=nav_teleft_txt]")
							.first();
					// if(eft == null) {
					// System.out.println(placeAbbr);
					// continue;
					// }
					Elements eas = eft.select("a[href]");
					for (Element ea : eas) {
						s1 = ea.text();
						// if (!h.containsKey(s1)) {
						s2 = ea.attr("href");
						h.put(s1, s2);
						// }
					}
					break;
				}
			}
		}
		int count = 0;
		for (Entry<String, String> entry : h.entrySet()) {
			String key = entry.getKey();

			// ��young�ײͣ�ȫ����
			if (key.indexOf("��Young") >= 0 || key.indexOf("�ƿ�") >= 0
					|| key.indexOf("��young") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// ����3G/4G��ȫ����
			// �Ѿ����йش���ȡ������
			if (key.indexOf("����") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// ��ľ�ײͣ�ȫ����
			if (key.indexOf("��ľ") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// ��ͨ����ȫ����
			if (key.indexOf("��ͨ") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// e8/e9/e6�̻��ײͣ�����
			if (Pattern.matches(".*e[0-9].*", key) && key.indexOf("hone") < 0) {
				// avoid iphone5s
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// ������������
			if (key.indexOf("������") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// ���˶���/���˶��ƣ�ȫ����
			if (key.indexOf("���˶���") >= 0 || key.indexOf("���˶���") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			// 4G������ȡ��������
			if (key.indexOf("4G") >= 0 && key.indexOf("����") < 0
					&& key.indexOf("���˶���") < 0 && key.indexOf("���˶���") < 0
					&& key.indexOf("��Young") < 0 && key.indexOf("��young") < 0
					&& key.indexOf("�ƿ�") < 0 && key.indexOf("������") < 0
					&& key.indexOf("һ�廯") < 0 || key.indexOf("��") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			//���࣬��¼�Ƿ����
			if (key.indexOf("����") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			//iphone5s�ײͣ�����
			if (key.indexOf("hone") >= 0) {
//				System.out.println(key + " " + entry.getValue());
				continue;
			}
			
			//3G/4Gһ�廯�ײͣ�ɽ����Ӫ
//			if (key.indexOf("һ�廯") >= 0) {
////				System.out.println(key + " " + entry.getValue());
//				continue;
//			}
			count++;
			// System.out.println(key);
			System.out.println(key + " " + entry.getValue());
//			 System.out.println(count + ": " + key + " "
//			 + entry.getValue());
		}
		// System.out.println(h.size());
		/*
		 * 127�ְ���ıɱ�׵�����
		 */
		/*
		 * for(int i = 0; i < PlaceNameFull._placeList.size(); i++) { String s1,
		 * s2, s3; s1 = PlaceNameFull._placeList.get(i); s2 =
		 * PlaceNameAbbr._placeList.get(i); s3 =
		 * PlaceNameChinese._placeList.get(i);
		 * System.out.println(s1+" "+s2+" "+s3); }
		 */
	}
}