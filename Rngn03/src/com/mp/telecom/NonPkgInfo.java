package com.mp.telecom;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mp.telecom.model.NonPkgStep;
import com.mp.util.UtilData;

public class NonPkgInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5540042550712398069L;
	private int mType;
	private static double mDisCount;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static ArrayList<NonPkgStep> nonPkgList = null;

	static {
		nonPkgList = new ArrayList<NonPkgStep>();
	}

	public NonPkgInfo() {
		this.setmType(UtilData.CTELECOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public NonPkgInfo(int mType) {
		if(mType == UtilData.CMCC || mType == UtilData.CTELECOM || mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CTELECOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public void addNonPkg(String mType, double sTime, double eTime, double cost) {
		NonPkgStep nPS = new NonPkgStep(mType, sTime, eTime, cost);
		if(!nonPkgList.contains(nPS)) {
			nonPkgList.add(nPS);
		}
	}

	public double solve(double call, double msg, double info) {
		if (nonPkgList.size() == 0) {
			addDefaultPkg();
		}
		double rs = 0.0;
		for (NonPkgStep nPS : nonPkgList) {
			rs += nPS.solve(call, msg, info);
		}
		if (mDisCount > 0 && mDisCount <= 1) {
			rs *= mDisCount;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs) );
	}

	private void addDefaultPkg() {
		this.setDefaultDiscount();
		this.addNonPkg(CALL, 1, 500, 0.15);
		this.addNonPkg(CALL, 501, 1000, 0.12);
		this.addNonPkg(CALL, 1001, 2000, 0.08);
		this.addNonPkg(MSG, 1, 50, 0.10);
		this.addNonPkg(MSG, 51, 500, 0.06);
		this.addNonPkg(MSG, 501, 1000, 0.05);
		this.addNonPkg(INFO, 1, 100, 0.15);
		this.addNonPkg(INFO, 101, 500, 0.07);
		this.addNonPkg(INFO, 501, 20000, 0.05);
	}

	private void setDefaultDiscount() {
		setmDisCount(0.8);
	}

	public static double getmDisCount() {
		return mDisCount;
	}

	public static void setmDisCount(double mDisCount) {
		NonPkgInfo.mDisCount = mDisCount;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public static void main(String[] args) {
		// http://kzone.zhidao.189.cn/baseshow.do?bid=10379&title=&kid=5845&tid=10381
		System.out.println(new NonPkgInfo().solve(1200, 600, 1024));
	}
}
