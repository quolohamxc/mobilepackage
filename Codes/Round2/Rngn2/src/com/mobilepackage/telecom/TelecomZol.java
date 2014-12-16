package com.mobilepackage.telecom;

import java.io.IOException;
import java.util.ArrayList;

import com.mobilepackage.telecom.TelecomController.CtrlCrawler;
/**
 * 
 * @author Administrator
 *
 */
public class TelecomZol {
	public String mEntry = "http://detail.zol.com.cn/phone_package/dianxin/";
	public ArrayList<String> mZolRegex;
	
	public TelecomZol() {
		super();
		mZolRegex = new ArrayList<String>();
		mZolRegex.add("+^http://detail.zol.com.cn/phone_package/dianxin/");
		mZolRegex.add("+http://detail.zol.com.cn/phone_package/dianxin/[0-9]+.*");
		mZolRegex.add("+http://detail.zol.com.cn/phone_package/index.*");
	}
	
	public void fetchZol() {
		CtrlCrawler mCC = new CtrlCrawler();
		TelecomZol mDXZ = new TelecomZol();
		mCC.addSeed(mDXZ.mEntry);
		mCC.setRoot("download");
		for (int i = 0; i < mDXZ.mZolRegex.size(); i++) {
			mCC.addRegex(mDXZ.mZolRegex.get(i));
		}
		try {
			mCC.start(3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
