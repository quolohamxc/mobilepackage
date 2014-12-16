package com.mp.telecom.model;

import com.mp.telecom.AppPkgInfo;

/**
 * Ĭ��typeΪCTELECOM��
 * @author Administrator
 *
 */
public class AppPkgFuel {
	private String mType = "";
	private double fee, involve;
	public AppPkgFuel(String mType, double fee, double involve) {
		super();
		this.mType = mType;
		this.fee = fee;
		this.involve = involve;
	}
	public String getmType() {
		return mType;
	}
	public void setmType(String mType) {
		this.mType = mType;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getInvolve() {
		return involve;
	}
	public void setInvolve(double involve) {
		this.involve = involve;
	}
	@Override
	public String toString() {
		return "AppPkgFuel [mType=" + mType + ", fee=" + fee + ", involve="
				+ involve + "]";
	}
//	public String toString() {
//		return "addAppPkg(\"" + mType + "\", " + fee + ", "
//				+ involve + ");";
//	}
	public String getDescription() {
		if(AppPkgInfo.CALL.equals(mType)){
			return "[�й�����]" + "������ѡ��" + String.valueOf((int)fee) + "Ԫ";
		} else if (AppPkgInfo.MSG.equals(mType)){
			return "[�й�����]" + "���ſ�ѡ��" + String.valueOf((int)fee) + "Ԫ";
		} else if (AppPkgInfo.INFO.equals(mType)){
			return "[�й�����]" + "������ѡ��" + String.valueOf((int)fee) + "Ԫ";
		} else {
			return "";
		}
	}
}
