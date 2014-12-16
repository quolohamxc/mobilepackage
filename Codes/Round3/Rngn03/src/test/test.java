package test;

import com.mp.telecom.MultiPkgInfo;
import com.mp.telecom.NonPkgInfo;
import com.mp.telecom.SinglePkgInfo;

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
//         佛祖保佑       永无BUG

/**
 * 
 * @author Administrator
 *
 */
public class test {
	@SuppressWarnings("null")
	public static void main(String[] args) {

		// HashMap<String, String> h = new HashMap<String, String>();
		// String s1, s2;
		// for (String placeAbbr : PlaceNameAbbr._placeList) {
		// String mEntry = "http://www.189.cn/" + placeAbbr + "/";
		// boolean flag = true;
		// Document document = null;
		// try {
		// document = Jsoup.connect(mEntry).timeout(5000).get();
		// } catch (IOException e) {
		// flag = false;
		// }
		// if (flag) {
		// Element ele = document
		// .select("div[class=list list2 quick_nav]").first();
		// // System.out.println(ele.toString());
		// if (ele == null) {
		// /*
		// * gd sc yn 这三个地方连主站都透露着一股乡村气息
		// */
		// // System.out.println(placeAbbr);
		// continue;
		// }
		// Elements euls = ele.select("ul[class=left_nav_tit]");
		// // if (euls == null) {
		// // /*
		// // * 还好这步没出错
		// // */
		// // System.out.println(placeAbbr);
		// // continue;
		// // }
		// for (Element eul : euls) {
		// String nav_tel_txt = eul.select("li[class=nav_teleft_txt]")
		// .text();
		// if (nav_tel_txt.indexOf("套餐") < 0) {
		// continue;
		// }
		// // System.out.println("ok!findit");
		// Element eft = eul.select("li[class=nav_teleft_txt]")
		// .first();
		// // if(eft == null) {
		// // System.out.println(placeAbbr);
		// // continue;
		// // }
		// Elements eas = eft.select("a[href]");
		// for (Element ea : eas) {
		// s1 = ea.text();
		// // if (!h.containsKey(s1)) {
		// s2 = ea.attr("href");
		// h.put(s1, s2);
		// // }
		// }
		// break;
		// }
		// }
		// }
		// int count = 0;
		// for (Entry<String, String> entry : h.entrySet()) {
		// String key = entry.getKey();
		//
		// // 飞young套餐（全国）
		// if (key.indexOf("飞Young") >= 0 || key.indexOf("云卡") >= 0
		// || key.indexOf("飞young") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 乐享3G/4G（全国）
		// // 已经在中关村爬取，放弃
		// if (key.indexOf("乐享") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 积木套餐（全国）
		// if (key.indexOf("积木") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 易通卡（全国）
		// if (key.indexOf("易通") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // e8/e9/e6固话套餐，不用
		// if (Pattern.matches(".*e[0-9].*", key) && key.indexOf("hone") < 0) {
		// // avoid iphone5s
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 上网卡，不用
		// if (key.indexOf("上网卡") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 个人定制/个人订制（全国）
		// if (key.indexOf("个人定制") >= 0 || key.indexOf("个人订制") >= 0) {
		// System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 4G（已爬取），不用
		// if (key.indexOf("4G") >= 0 && key.indexOf("乐享") < 0
		// && key.indexOf("个人定制") < 0 && key.indexOf("个人订制") < 0
		// && key.indexOf("飞Young") < 0 && key.indexOf("飞young") < 0
		// && key.indexOf("云卡") < 0 && key.indexOf("上网卡") < 0
		// && key.indexOf("一体化") < 0 || key.indexOf("升") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // 更多，记录是否更新
		// if (key.indexOf("更多") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		// // iphone5s套餐，不用
		// if (key.indexOf("hone") >= 0) {
		// // System.out.println(key + " " + entry.getValue());
		// continue;
		// }
		//
		// // 3G/4G一体化套餐，山东自营
		// // if (key.indexOf("一体化") >= 0) {
		// // // System.out.println(key + " " + entry.getValue());
		// // continue;
		// // }
		// count++;
		// // System.out.println(key);
		// // System.out.println(key + " " + entry.getValue());
		// // System.out.println(count + ": " + key + " "
		// // + entry.getValue());
		// }
		// System.out.println(h.size());
		/*
		 * 127种啊！谋杀亲爹啊！
		 */

		// for (int i = 0; i < PlaceNameFull._placeList.size(); i++) {
		// String s1, s2, s3;
		// s1 = PlaceNameFull._placeList.get(i);
		// s2 = PlaceNameAbbr._placeList.get(i);
		// s3 = PlaceNameChinese._placeList.get(i);
		// System.out.println(s1 + " " + s2 + " " + s3);
		// }
		// System.out.println(UtilMethod.getHtmlUnit("http://kzone.zhidao.189.cn/baseshow.do?bid=10379&title=&kid=5845&tid=10381"));
		// System.out.println(Double.valueOf(" 1.0"));
//		boolean flag = true;
//		flag &= true;
//		System.out.println(flag);
//		System.out.println(UtilMethod.elimFix(" ",
//				"包邮 易通卡55元/月 首次开卡共需支付60元含60元话费", " ").trim());
//		for (int i = 0; i < HeiLongJiangFull._placeList.size(); i++) {
//			String s1, s2, s3;
//			s1 = HeiLongJiangFull._placeList.get(i);
//			s2 = "";
//			s3 = HeiLongJiangChn._placeList.get(i);
//			System.out.println(s1 + " " + s2 + " " + s3);
//		}
		System.out.println(new SinglePkgInfo().solve(100, 100, 100));
		System.out.println(new MultiPkgInfo().solve(100, 100, 100));
		System.out.println(new NonPkgInfo().solve(100, 100, 100));
		
		String test = null;
		try{
			System.out.println(test.toString());
		} catch (NullPointerException e) {
			System.out.println("nullPointer");
			System.err.println("nullPointer error");
		}
		System.out.println("?");
	}
}
