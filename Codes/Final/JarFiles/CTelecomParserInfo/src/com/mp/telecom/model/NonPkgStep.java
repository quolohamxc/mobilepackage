package com.mp.telecom.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.mp.telecom.NonPkgInfo;
/**
 * Ä¬ÈÏtypeÎªCTELECOM¡£
 * @author Administrator
 *
 */
public class NonPkgStep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8090864251277580071L;
	private String mType = "";
	private double sTime, eTime, cost;

	public NonPkgStep(String mType, double sTime, double eTime, double cost) {
		this.setmType(mType);
		this.setsTime(sTime);
		this.seteTime(eTime);
		this.setCost(cost);
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public double getsTime() {
		return sTime;
	}

	public void setsTime(double sTime) {
		this.sTime = sTime;
	}

	public double geteTime() {
		return eTime;
	}

	public void seteTime(double eTime) {
		this.eTime = eTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double solve(double call, double msg, double info) {
		double op = 0.0, rs = 0.00;
		if (mType == NonPkgInfo.CALL) {
			op = call;
		} else if (mType == NonPkgInfo.MSG) {
			op = msg;
		} else if (mType == NonPkgInfo.INFO) {
			op = info;
		}
		if (op > eTime) {
			rs = cost * (eTime - sTime + 1);
		} else if (op > sTime) {
			rs = cost * (op - sTime + 1);
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs) );
	}
}
