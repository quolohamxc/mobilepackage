package com.mp.unicom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mp.unicom.model.UMultiPkgJuggle;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class UMultiPkgInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5786042272182594926L;
	private int mType;
	private static double mDisCount;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static final String QGZH = "qgzh";
	public static final String GXZH = "gxzh";
	public static final String BDZH = "bdzh";
	public ArrayList<UMultiPkgJuggle> multiPkgJug = null;
	public static ArrayList<UMultiPkgJuggle> multiPkgList = null;
	public UMultiPkgJuggle mPJ1 = null;
	public UMultiPkgJuggle mPJ2 = null;
	public UMultiPkgJuggle mPJ3 = null;
	private double price, price1, price2, price3;
	private String desc1, desc2, desc3;

	public static ArrayList<UMultiPkgJuggle> getMultiPkgList() {
		if (multiPkgList.size() == 0) {
			new UMultiPkgInfo().addDefaultPkg();
		}
		return multiPkgList;
	}

	public static void setMultiPkgList(ArrayList<UMultiPkgJuggle> multiPkgList) {
		UMultiPkgInfo.multiPkgList = multiPkgList;
	}

	static {
		multiPkgList = new ArrayList<UMultiPkgJuggle>();
	}

	public UMultiPkgInfo() {
		this.setmType(UtilData.CUNICOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public UMultiPkgInfo(int mType) {
		if (mType == UtilData.CMCC || mType == UtilData.CUNICOM
				|| mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CUNICOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	private void setDefaultDiscount() {
		setmDisCount(1.0);
	}

	public void addMultiPkg(String mType, String ptType, double cTime,
			double cost, double beyond) {
		UMultiPkgJuggle mPJ = new UMultiPkgJuggle(mType, ptType, cTime, cost,
				beyond);
		if (!multiPkgList.contains(mPJ)) {
			multiPkgList.add(mPJ);
		}
	}

	public double solve(String type, double call, double msg, double info) {
		if (multiPkgList == null || multiPkgList.size() == 0) {
			addDefaultPkg();
		}
		multiPkgJug = new ArrayList<UMultiPkgJuggle>();
		for (UMultiPkgJuggle mPJ : getMultiPkgList()) {
			mPJ.solve(type, call, msg, info);
			multiPkgJug.add(mPJ);
		}
		Collections.sort(multiPkgJug, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				return (int) (((UMultiPkgJuggle) arg0).getPrice() - ((UMultiPkgJuggle) arg1)
						.getPrice());
			}
		});
		double rs = 0.0;
		if(mPJ1 == null) {
			mPJ1 = new UMultiPkgJuggle();
		}
		if(mPJ2 == null) {
			mPJ2 = new UMultiPkgJuggle();
		}
		if(mPJ3 == null) {
			mPJ3 = new UMultiPkgJuggle();
		}
		
		for (UMultiPkgJuggle mPJ : multiPkgJug) {
			if (mPJ.getPtType() != type) {
				continue;
			}
			if (mPJ.getmType() == CALL
					&& (mPJ1.getPrice() == 0.0 || mPJ1.getPrice() > mPJ
							.getPrice())) {
				mPJ1 = mPJ;
			}
			if (mPJ.getmType() == MSG
					&& (mPJ2.getPrice() == 0.0 || mPJ2.getPrice() > mPJ
							.getPrice())) {
				mPJ2 = mPJ;
			}
			if (mPJ.getmType() == INFO
					&& (mPJ3.getPrice() == 0.0 || mPJ3.getPrice() > mPJ
							.getPrice())) {
				mPJ3 = mPJ;
			}
		}
		rs = mPJ1.getPrice() + mPJ2.getPrice() + mPJ3.getPrice();
		return rs;
	}

	public double solve(double call, double msg, double info) {
		double rs = UtilData.MAXLIMIT, temp = 0.0;
		temp = solve(QGZH, call, msg, info);
		setDesc1(getDescription());
		setPrice1(temp);
		if(rs > temp && temp != 0.0) {
			rs = temp;
		}
		temp = solve(GXZH, call, msg, info);
		if(rs > temp && temp != 0.0) {
			rs = temp;
		}
		setPrice2(temp);
		setDesc2(getDescription());
		temp = solve(BDZH, call, msg, info);
		if(rs > temp && temp != 0.0) {
			rs = temp;
		}
		setPrice3(temp);
		setDesc3(getDescription());
		return rs;
	}

	private void addDefaultPkg() {
		this.setDefaultDiscount();
		addDefaultPkg("");
	}

	public void addDefaultPkg(String cate) {
		if (cate == "" || cate == QGZH) {
			this.addMultiPkg(INFO, QGZH, 100, 8, 0.2);
			this.addMultiPkg(INFO, QGZH, 300, 16, 0.2);
			this.addMultiPkg(INFO, QGZH, 500, 24, 0.2);
			this.addMultiPkg(INFO, QGZH, 1000, 48, 0.2);
			this.addMultiPkg(INFO, QGZH, 2000, 72, 0.2);
			this.addMultiPkg(INFO, QGZH, 3000, 96, 0.2);
			this.addMultiPkg(INFO, QGZH, 4000, 120, 0.2);
			this.addMultiPkg(INFO, QGZH, 6000, 152, 0.2);
			this.addMultiPkg(INFO, QGZH, 11000, 232, 0.2);

			this.addMultiPkg(CALL, QGZH, 0, 0, 0.20);
			this.addMultiPkg(CALL, QGZH, 200, 32, 0.15);
			this.addMultiPkg(CALL, QGZH, 300, 40, 0.15);
			this.addMultiPkg(CALL, QGZH, 500, 56, 0.15);
			this.addMultiPkg(CALL, QGZH, 1000, 112, 0.15);
			this.addMultiPkg(CALL, QGZH, 2000, 160, 0.15);
			this.addMultiPkg(CALL, QGZH, 3000, 240, 0.15);

			this.addMultiPkg(MSG, QGZH, 200, 10, 0.1);
			this.addMultiPkg(MSG, QGZH, 400, 20, 0.1);
			this.addMultiPkg(MSG, QGZH, 600, 30, 0.1);
		}
		if (cate == "" || cate == GXZH) {
			this.addMultiPkg(INFO, GXZH, 4000, 119.9, 0.06);
			this.addMultiPkg(INFO, GXZH, 6000, 159.9, 0.06);
			this.addMultiPkg(INFO, GXZH, 8000, 199.9, 0.06);
			this.addMultiPkg(INFO, GXZH, 12000, 239.9, 0.06);

			this.addMultiPkg(CALL, GXZH, 0, 0, 0.20);
			this.addMultiPkg(CALL, GXZH, 500, 35, 0.15);
			this.addMultiPkg(CALL, GXZH, 1000, 60, 0.15);
			this.addMultiPkg(CALL, GXZH, 2000, 90, 0.15);

			this.addMultiPkg(MSG, GXZH, 400, 20, 0.1);
			this.addMultiPkg(MSG, GXZH, 600, 30, 0.1);
		}
		if (cate == "" || cate == BDZH) {
			this.addMultiPkg(INFO, BDZH, 150, 8, 0.30);
			this.addMultiPkg(INFO, BDZH, 450, 16, 0.30);
			this.addMultiPkg(INFO, BDZH, 750, 24, 0.30);
			this.addMultiPkg(INFO, BDZH, 1500, 48, 0.30);

			this.addMultiPkg(CALL, BDZH, 0, 0, 0.20);
			this.addMultiPkg(CALL, BDZH, 60, 6, 0.15);
			this.addMultiPkg(CALL, BDZH, 100, 10, 0.15);
			this.addMultiPkg(CALL, BDZH, 200, 20, 0.15);

			this.addMultiPkg(MSG, BDZH, 200, 10, 0.1);
			this.addMultiPkg(MSG, BDZH, 400, 20, 0.1);
			this.addMultiPkg(MSG, BDZH, 600, 30, 0.1);
		}
	}

	public String getDescription() throws NullPointerException {
		return getmPJ1().toString() + "+" + getmPJ2().toString() + "+"
				+ getmPJ3().toString();
	}

	public static String getShowName(int cellba) {
		return UMultiPkgInfo.getMultiPkgList().get(cellba).toString();
	}

	public static String getShowName(int cell, String pttype, String mtype) {
		int zter = 0;
		for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
			String s = mPJ.toString();
			if (mPJ.getmType() == mtype && mPJ.getPtType() == pttype) {
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

	public static double[] queryFee(String jugglea, double callAve,
			String juggleb, double msgAve, String jugglec, double infoAve) {
		double[] rs = { 0, 0, 0, 0 };
		for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
			if (mPJ.getmType() == UMultiPkgInfo.CALL
					&& mPJ.toString().equals(jugglea)) {
				rs[0] += mPJ.getCost();
				rs[1] += mPJ.getcTime();
				rs[1] += callAve / mPJ.getBeyond();
			}
			if (mPJ.getmType() == UMultiPkgInfo.MSG
					&& mPJ.toString().equals(juggleb)) {
				rs[0] += mPJ.getCost();
				rs[2] += mPJ.getcTime();
				rs[2] = callAve / mPJ.getBeyond();
			}
			if (mPJ.getmType() == UMultiPkgInfo.INFO
					&& mPJ.toString().equals(jugglec)) {
				rs[0] += mPJ.getCost();
				rs[3] += mPJ.getcTime();
				rs[3] = callAve / mPJ.getBeyond();
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
		UMultiPkgInfo.mDisCount = mDisCount;
	}

	public UMultiPkgJuggle getmPJ1() {
		return mPJ1;
	}

	public void setmPJ1(UMultiPkgJuggle mPJ1) {
		this.mPJ1 = mPJ1;
	}

	public UMultiPkgJuggle getmPJ2() {
		return mPJ2;
	}

	public void setmPJ2(UMultiPkgJuggle mPJ2) {
		this.mPJ2 = mPJ2;
	}

	public UMultiPkgJuggle getmPJ3() {
		return mPJ3;
	}

	public void setmPJ3(UMultiPkgJuggle mPJ3) {
		this.mPJ3 = mPJ3;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice1() {
		return price1;
	}

	public void setPrice1(double price1) {
		this.price1 = price1;
	}

	public double getPrice2() {
		return price2;
	}

	public void setPrice2(double price2) {
		this.price2 = price2;
	}

	public double getPrice3() {
		return price3;
	}

	public void setPrice3(double price3) {
		this.price3 = price3;
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

	public ArrayList<UMultiPkgJuggle> getMultiPkgJug() {
		return multiPkgJug;
	}

	public void setMultiPkgJug(ArrayList<UMultiPkgJuggle> multiPkgJug) {
		this.multiPkgJug = multiPkgJug;
	}
	

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	
	public static void main(String[] args) {
		UMultiPkgInfo mPI = new UMultiPkgInfo();
		System.out.println(mPI.solve(100, 100, 100));
		System.out.println(mPI.getPrice1() + ": " + mPI.getDesc1());
		System.out.println(mPI.getPrice2() + ": " + mPI.getDesc2());
		System.out.println(mPI.getPrice3() + ": " + mPI.getDesc3());
		for (UMultiPkgJuggle mpj : mPI.getMultiPkgJug()) {
			System.out.println(mpj.getPrice() + " : " + mpj.toString());
		}
	}

}
