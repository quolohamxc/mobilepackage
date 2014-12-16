package com.mp.telecom.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.mp.telecom.MultiPkgInfo;

/**
 * 默认type为CTELECOM。
 * 
 * @author Administrator
 *
 */
public class MultiPkgJuggle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4518819262709108903L;
	private String mType = "";
	private double cTime, cost, beyond;

	public MultiPkgJuggle(String mType, double cTime, double cost, double beyond) {
		this.setmType(mType);
		this.setcTime(cTime);
		this.setCost(cost);
		this.setBeyond(beyond);
	}

	public String getmType() {
		return mType;
	}

	public void setmType(String mType) {
		this.mType = mType;
	}

	public double getcTime() {
		return cTime;
	}

	public void setcTime(double cTime) {
		this.cTime = cTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getBeyond() {
		return beyond;
	}

	public void setBeyond(double beyond) {
		this.beyond = beyond;
	}

	public double solve(double call, double msg, double info) {
		double op = 0.0, rs = 0.00;
		if (mType == MultiPkgInfo.CALL) {
			op = call;
		} else if (mType == MultiPkgInfo.MSG) {
			op = msg;
		} else if (mType == MultiPkgInfo.INFO) {
			op = info;
		}
		if (op > cTime) {
			rs = cost + (op - cTime) * beyond;
		} else {
			rs = cost;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs));
	}

	@Override
	public String toString() {
		if (this.mType == MultiPkgInfo.CALL) {
			return "[中国电信]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost))
					+ "元"
					+ "语音"
					+ "积木套餐");
		} else if (this.mType == MultiPkgInfo.MSG) {
			return "[中国电信]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost))
					+ "元"
					+ "短信"
					+ "积木套餐");
		} else if (this.mType == MultiPkgInfo.INFO) {
			return "[中国电信]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost))
					+ "元"
					+ "流量"
					+ "积木套餐");
		} else {
			return "";
		}
	}
}
