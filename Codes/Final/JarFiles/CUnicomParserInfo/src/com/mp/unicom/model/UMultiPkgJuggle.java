package com.mp.unicom.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.mp.unicom.UMultiPkgInfo;

public class UMultiPkgJuggle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3157650429887108945L;
	private String mType = "";
	private String ptType = "";
	private double cTime = 0, cost = 0, beyond = 0, price = 0;

	public UMultiPkgJuggle(String mType, String ptType, double cTime, double cost, double beyond) {
		this.setmType(mType);
		this.setPtType(ptType);
		this.setcTime(cTime);
		this.setCost(cost);
		this.setBeyond(beyond);
	}

	public UMultiPkgJuggle(String mType, String ptType, double cTime,
			double cost, double beyond, double price) {
		super();
		this.mType = mType;
		this.ptType = ptType;
		this.cTime = cTime;
		this.cost = cost;
		this.beyond = beyond;
		this.price = price;
	}

	public UMultiPkgJuggle() {
		
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

	public double solve(String type, double call, double msg, double info) {
		if(type == ptType) {
			this.setPrice(solve(call, msg, info));
		} else {
			this.setPrice(0.0);
		}
		return this.getPrice();
	}
	
	public double solve(double call, double msg, double info) {
		double op = 0.0, rs = 0.00;
		if (mType == UMultiPkgInfo.CALL) {
			op = call;
		} else if (mType == UMultiPkgInfo.MSG) {
			op = msg;
		} else if (mType == UMultiPkgInfo.INFO) {
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
		String rs;
		if (this.mType == UMultiPkgInfo.CALL) {
			rs = "[中国联通]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost)))
					+ "元"
					+ "语音";
		} else if (this.mType == UMultiPkgInfo.MSG) {
			rs = "[中国联通]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost)))
					+ "元"
					+ "短信";
		} else if (this.mType == UMultiPkgInfo.INFO) {
			rs = "[中国联通]"
					+ String.valueOf(Double.valueOf(new DecimalFormat("#.00")
					.format(cost)))
					+ "元"
					+ "流量";
		} else {
			rs =  "";
		}
		if(this.ptType == UMultiPkgInfo.QGZH) {
			rs += "全国组合套餐";
		} else if(this.ptType == UMultiPkgInfo.GXZH) {
			rs += "共享组合套餐";
		} else if(this.ptType == UMultiPkgInfo.BDZH) {
			rs += "本地组合套餐";
		} else {
			
		}
		return rs;
	}

	public String getPtType() {
		return ptType;
	}

	public void setPtType(String ptType) {
		this.ptType = ptType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
