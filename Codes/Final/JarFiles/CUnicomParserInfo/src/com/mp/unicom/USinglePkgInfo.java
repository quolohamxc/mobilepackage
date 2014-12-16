package com.mp.unicom;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mp.model.PackageTariff;
import com.mp.unicom.model.USinglePkgTar;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class USinglePkgInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4543429372039917114L;
	private int mType;
	private static double mDisCount;
	// public ArrayList<String> singleCrawlerUrl = null;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static final String FOURG = "4G";
	public static final String THREEG = "3G";
	public static final String THREEGA = "3GPLANA";
	public static final String THREEGB = "3GPLANB";
	public static final String THREEGC = "3GPLANC";
	public static final String THREEGI = "3GIPHONE";
	public static final String THREEGY = "3GYUPLAN";
	public static final String THREEGW = "3GWIRELESS";
	public ArrayList<USinglePkgTar> singlePkgTar = null;
	public static ArrayList<PackageTariff> singlePkgList = null;

	public static ArrayList<PackageTariff> getSinglePkgList() {
		if (singlePkgList.size() == 0) {
			new USinglePkgInfo().addDefaultPkg();
		}
		Collections.sort(singlePkgList, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {

				return ((PackageTariff) arg0).getmTitle().compareTo(
						((PackageTariff) arg1).getmTitle());
			}
		});
		return singlePkgList;
	}

	public static void setSinglePkgList(ArrayList<PackageTariff> singlePkgList) {
		USinglePkgInfo.singlePkgList = singlePkgList;
	}

	static {
		singlePkgList = new ArrayList<PackageTariff>();
	}

	public USinglePkgInfo() {
		this.setmType(UtilData.CUNICOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public USinglePkgInfo(int mType) {
		if (mType == UtilData.CMCC || mType == UtilData.CTELECOM
				|| mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CUNICOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public void addSinglePkg(PackageTariff pT) {
		if (!singlePkgList.contains(pT)) {
			singlePkgList.add(pT);
		}
	}

	private void setDefaultDiscount() {
		setmDisCount(1.0);
	}

	public double solve(double call, double msg, double info) {
		if (singlePkgList == null || singlePkgList.size() == 0) {
			addDefaultPkg();
		}
		singlePkgTar = new ArrayList<USinglePkgTar>();
		for (PackageTariff pT : singlePkgList) {
			USinglePkgTar sPT = new USinglePkgTar(pT);
			if (sPT.solve(call, msg, info)
					&& sPT.getPrice() != UtilData.INVALID
					&& sPT.getPrice() != 0.0 && !singlePkgTar.contains(sPT)) {
				singlePkgTar.add(sPT);
			}

		}
		Collections.sort(singlePkgTar, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				return (int) (((USinglePkgTar) arg0).getPrice() - ((USinglePkgTar) arg1)
						.getPrice());
			}
		});
		double rs = 0.0;
		if (singlePkgTar.size() != 0) {
			rs = singlePkgTar.get(0).getPrice();
		}
		if (mDisCount > 0 && mDisCount <= 1) {
			rs *= mDisCount;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs));
	}

	public void addDefaultPkg() {
		setDefaultDiscount();
		addDefaultPkg("");
	}

	public void addDefaultPkg(String cate) {
		if (cate == "" || cate == THREEG || cate == THREEGA) {
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划126元", 126.0, 9989.0,
					320, 400.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划156元", 156.0, 9989.0,
					420, 500.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划186元", 186.0, 9989.0,
					510, 650.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划226元", 226.0, 9989.0,
					700, 750.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划286元", 286.0, 9989.0,
					900, 950.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划386元", 386.0, 9989.0,
					1250, 1300.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "",
					"", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划46元", 46.0, 9989.0,
					50, 150.0, 0, -1, -1, 0.25, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划586元", 586.0, 9989.0,
					1950, 2000.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "",
					"", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划66元", 66.0, 9989.0,
					50, 300.0, 240, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划886元", 886.0, 9989.0,
					3000, 3000.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "",
					"", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐A计划96元", 96.0, 9989.0,
					240, 300.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		if (cate == "" || cate == THREEG || cate == THREEGB) {
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划126元", 126.0, 9989.0,
					680, 100.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划156元", 156.0, 9989.0,
					920, 120.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划186元", 186.0, 9989.0,
					1180, 150.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "",
					"", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划46元", 46.0, 9989.0,
					120, 40.0, -1, -1, -1, 0.25, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划66元", 66.0, 9989.0,
					200, 60.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐B计划96元", 96.0, 9989.0,
					450, 80.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		if (cate == "" || cate == THREEG || cate == THREEGC) {
			addSinglePkg(new PackageTariff(10010, "3G套餐C计划46元", 46.0, 9989.0,
					-1, 40.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐C计划66元", 66.0, 9989.0,
					-1, 60.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G套餐C计划96元", 96.0, 9989.0,
					-1, 80.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		}
		if (cate == "" || cate == THREEG || cate == THREEGI) {
			addSinglePkg(new PackageTariff(10010, "iPhone套餐126元", 126.0,
					9989.0, 320, 450.0, 120, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐156元", 156.0,
					9989.0, 420, 570.0, 150, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐186元", 186.0,
					9989.0, 510, 720.0, 180, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐226元", 226.0,
					9989.0, 700, 850.0, 220, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐286元", 286.0,
					9989.0, 900, 1100.0, 280, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐386元", 386.0,
					9989.0, 1250, 1600.0, 380, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐586元", 586.0,
					9989.0, 1950, 2500.0, 580, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐66元", 66.0, 9989.0,
					160, 220.0, 50, -1, -1, 0.36, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐886元", 886.0,
					9989.0, 3000, 4000.0, 880, -1, -1, 0.36, -1.0, -1.0, 0.0,
					0.3, "", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10010, "iPhone套餐96元", 96.0, 9989.0,
					240, 340.0, 80, -1, -1, 0.36, -1.0, -1.0, 0.0, 0.3, "", "",
					false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

		}
		if (cate == "" || cate == FOURG) {
			addSinglePkg(new PackageTariff(10010, "4G全国套餐106元", 106.0, 9989.0,
					300, 800.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐136元", 136.0, 9989.0,
					500, 1000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐166元", 166.0, 9989.0,
					500, 2000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐196元", 196.0, 9989.0,
					500, 3000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐296元", 296.0, 9989.0,
					1000, 4000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐396元", 396.0, 9989.0,
					2000, 6000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐596元", 596.0, 9989.0,
					3000, 11000.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "4G全国套餐76元", 76.0, 9989.0,
					200, 400.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, -1.0, "",
					"", false, "", "", true, 200.0, 0.3, 9.9999891E7, 0.06,
					0.0, 0.0));

		}
		if (cate == "" || cate == THREEG || cate == THREEGY) {
			addSinglePkg(new PackageTariff(10010, "3G预付费36元套餐36元", 36.0,
					9989.0, 150, 150.0, -1, -1, -1, 0.3, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费A套餐――更多数据流量46元", 46.0,
					9989.0, 50, 150.0, 0, -1, -1, 0.25, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费A套餐――更多数据流量66元", 66.0,
					9989.0, 50, 300.0, 240, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费A套餐――更多数据流量96元", 96.0,
					9989.0, 240, 300.0, 0, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费B套餐――更多语音通话46元", 46.0,
					9989.0, 120, 40.0, -1, -1, -1, 0.25, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费B套餐――更多语音通话66元", 66.0,
					9989.0, 200, 60.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费B套餐――更多语音通话96元", 96.0,
					9989.0, 450, 80.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费C套餐――更多本地通话46元", 46.0,
					9989.0, 260, 40.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费C套餐――更多本地通话66元", 66.0,
					9989.0, 380, 60.0, -1, -1, -1, 0.2, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "3G预付费C套餐――更多本地通话96元", 96.0,
					9989.0, 550, 80.0, -1, -1, -1, 0.15, -1.0, -1.0, 0.0, 0.3,
					"", "", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10010, "沃3G预付费20元套餐20元", 20.0,
					-20.0, -1, -1.0, -1, -1, -1, 0.1, -1.0, -1.0, 0.0, 0.2, "",
					"", false, "", "", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
		}
	}

	public static String getShowName(int cell, String type) {
		int zter = 0;
		for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
			String s = pT.getmTitle();
			if (type == USinglePkgInfo.THREEG && s.contains("计划")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else if (type == USinglePkgInfo.THREEGY && s.contains("预付")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else if (type == USinglePkgInfo.THREEGI && s.contains("hone")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else if (type == USinglePkgInfo.FOURG && s.contains("4G")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else {
				// TODO for other packages
			}
		}
		return null;
	}

	public static double[] queryFee(String single, double callAve,
			double msgAve, double infoAve) {
		double[] rs = { 0, 0, 0, 0 };
		// use default data
		double callBey = 0.2, msgBey = 0.1, infoBey = 0.3;
		for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
			if (pT.getmTitle() != single) {
				continue;
			} else {
				double temp;
				if (callAve > 0) {
					temp = pT.getmMonthlyCons();
					if (temp != UtilData.INVALID && temp != 0) {
						rs[0] = Math.abs(temp);
					} else {
						rs[0] = pT.getmMonthlyFee();
					}
					temp = pT.getmDomCall();
					if (temp != UtilData.UNUSED && temp != 0) {
						rs[1] += callAve / temp;
					} else {
						temp = pT.getmDomCallBey();
						if (temp != UtilData.UNUSED && temp != 0) {
							rs[1] += callAve / temp;
						} else {
							rs[1] += callAve / callBey;
						}
					}
					temp = pT.getmDomCallDur();
					if (temp != UtilData.UNUSED && temp != 0) {
						rs[1] += temp;
					}
				}
				if (msgAve > 0) {
					rs[2] += msgAve / msgBey;
					temp = pT.getmDomMsg();
					if (temp != UtilData.UNUSED && temp != 0) {
						rs[2] += temp;
					}
				}
				if (infoAve > 0) {
					if (single.contains("飞Young")) {
						double low = 0, high = UtilData.MAXLIMIT, mid = (low + high) / 2;
						while (low < high - 1) {
							mid = (low + high) / 2;
							if (pT.solveStep(mid) > infoAve) {
								high = mid;
							} else {
								low = mid;
							}
						}
						rs[3] += mid;
					} else {
						temp = pT.getmDomInfoBey();
						if (temp != UtilData.UNUSED && temp != 0) {
							rs[3] += infoAve / temp;
						} else {
							rs[3] += infoAve / infoBey;
						}
					}
					temp = pT.getmDomInfoFlow();
					if (temp != UtilData.UNUSED && temp != 0) {
						rs[3] += temp;
					}
				}
				break;
			}
		}
		for (int iter = 0; iter < rs.length; iter++) {
			rs[iter] = UtilMethod.formatMoney(rs[iter]);
		}
		return rs;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public static double getmDisCount() {
		return mDisCount;
	}

	public static void setmDisCount(double mDisCount) {
		USinglePkgInfo.mDisCount = mDisCount;
	}

	public ArrayList<USinglePkgTar> getSinglePkgTar() {
		return singlePkgTar;
	}

	public void setSinglePkgTar(ArrayList<USinglePkgTar> singlePkgTar) {
		this.singlePkgTar = singlePkgTar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getCall() {
		return CALL;
	}

	public static String getMsg() {
		return MSG;
	}

	public static String getInfo() {
		return INFO;
	}

	public static String getFourg() {
		return FOURG;
	}

	public static String getThreeg() {
		return THREEG;
	}

	public static void main(String[] args) {
		USinglePkgInfo sPI = new USinglePkgInfo();
		System.out.println(sPI.solve(100, 100, 100));
		for (USinglePkgTar sPT : sPI.getSinglePkgTar()) {
			System.out.println(sPT.getPrice() + " " + sPT.getpT().toString());
		}
	}
}
