package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mp.model.PackageTariff;
import com.mp.unicom.model.USinglePkgTar;
import com.mp.util.UtilMethod;

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
	public static ArrayList<PackageTariff> singlePkgList = null;

	public static void main(String[] args) {
		PackageTariff pt = new PackageTariff(10010, "3G套餐B计划46元", 46.0, 9989.0,
				120, 0.3, -1, -1, -1, 0.25, -1.0, -1.0, 0.0, 0.3, "", "",
				false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		System.out.println(pt.solve(100, 100, 100));
		System.out.println(pt.solve(100, 0, 0));
		System.out.println(pt.solve(0, 100, 0));
		System.out.println(pt.solve(0, 0, 100));
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
	}

}
