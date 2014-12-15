package com.mp.telecom.model;
/**
 * Ä¬ÈÏtypeÎªCTELECOM¡£
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
}
