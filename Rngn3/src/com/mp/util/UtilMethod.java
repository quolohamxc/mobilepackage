package com.mp.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mp.model.PackageTariff;

/**
 * 
 * @author Administrator
 *
 */
public class UtilMethod {
	/**
	 * eliminate prefix and postfix from string s
	 * 
	 * @param mPrefix
	 * @param s
	 * @param mPostfix
	 * @return
	 */
	public static String elimFix(String mPrefix, String s, String mPostfix) {
		return elimPrefix(mPrefix, elimPostfix(s, mPostfix));
	}

	/**
	 * eliminate prefix from s
	 * 
	 * @param mPrefix
	 * @param s
	 * @return
	 */
	public static String elimPrefix(String mPrefix, String s) {
		int temp = s.indexOf(mPrefix);
		if (temp >= 0) {
			return s.substring(temp + mPrefix.length(), s.length());
		}
		return s;
	}

	/**
	 * eliminate postfix from s
	 * 
	 * @param s
	 * @param mPostfix
	 * @return
	 */
	public static String elimPostfix(String s, String mPostfix) {
		int temp = s.indexOf(mPostfix);
		if (temp >= 0) {
			return s.substring(0, temp);
		}
		return s;
	}

	public static boolean compareMapByEntrySet(Map<String, PackageTariff> map1,
			Map<String, PackageTariff> map2) {

		if (map1.size() != map2.size()) {
			return false;
		}
		PackageTariff tmp1 = new PackageTariff();
		PackageTariff tmp2 = new PackageTariff();
		boolean b = false;
		for (Entry<String, PackageTariff> entry : map1.entrySet()) {
			if (map2.containsKey(entry.getKey())) {
				tmp1 = entry.getValue();
				tmp2 = map2.get(entry.getKey());

				if (null != tmp1 && null != tmp1) { // ����Ϊnull
					if (tmp1.equals(tmp2)) {
						b = true;
						continue;
					} else {
						b = false;
						break;
					}
				} else if (null == tmp1 && null == tmp2) { // ��Ϊnull
					b = true;
					continue;
				} else {
					b = false;
					break;
				}
			} else {
				b = false;
				break;
			}
		}

		return b;
	}

	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	public static double formatMoney(double fee) {
		return Double.valueOf(new DecimalFormat("#.00").format(fee));
	}

	public static String getHtmlUnit(String url) throws FailingHttpStatusCodeException, IOException {
		// �������־
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
		/** HtmlUnit����webҳ�� */
		WebClient wc = new WebClient(BrowserVersion.CHROME);
		wc.getOptions().setUseInsecureSSL(true);
		wc.getOptions().setJavaScriptEnabled(true); // ����JS��������Ĭ��Ϊtrue
		wc.getOptions().setCssEnabled(false); // ����css֧��
		wc.getOptions().setThrowExceptionOnScriptError(false); // js���д���ʱ���Ƿ��׳��쳣
		wc.getOptions().setTimeout(5000); // �������ӳ�ʱʱ�� ��������10S�����Ϊ0���������ڵȴ�
		wc.getOptions().setDoNotTrackEnabled(false);
		HtmlPage page = null;
		try {
			page = wc
					.getPage(url);
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			throw(e);
		}
//		System.out.println("begin to wait");
		wc.waitForBackgroundJavaScript(5000);
//		System.out.println(page.getBody().asXml());
		return page.getBody().asXml();
	}
}
