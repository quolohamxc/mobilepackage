package com.mp.unicom.model;

import java.io.Serializable;

import com.mp.model.PackageTariff;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;

public class USinglePkgTar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1966649142093405752L;
	private double price;
	private PackageTariff pT = null;
	
	public USinglePkgTar(PackageTariff pT) {
		this.price = UtilData.INVALID;
		this.pT = pT;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean solve(double call, double msg, double info) {
		double fee = pT.solve(call, msg, info);
		if(price != 0 && price != UtilData.UNUSED && price > fee) {
			this.setPrice(fee);
		}
		return true;
	}

	public PackageTariff getpT() {
		return pT;
	}

	public void setpT(PackageTariff pT) {
		this.pT = pT;
	}

	public String getDescription() {
		return "[中国联通]" + this.pT.getmTitle();
	}
	
	public boolean solve(double call, double msg, double info, String location) {
		String loc = pT.getmLocation();
		if (loc == PlaceData.QUANGUO || loc == location) {
			solve(call, msg, info);
			return true;
		}
		return false;
	}

	public boolean solveApp(double call, double msg, double info) {
//		double opc = 0, opm = 0, opi = 0, fee = 0;
//		if(pT.getmDomCallDur() != UtilData.UNUSED) {
//			opc = pT.getmDomCallDur();
//		}
//		if(pT.getmDomMsg() != UtilData.UNUSED) {
//			opm = pT.getmDomMsg();
//		}
//		if(pT.getmDomInfoFlow() != UtilData.UNUSED) {
//			opi = pT.getmDomInfoFlow();
//		}
//		fee = pT.getmMonthlyFee() + new AppPkgInfo().solve(call - opc, msg - opm, info - opi);
//		if(price != 0 && price != UtilData.UNUSED && price > fee) {
//			this.setPrice(fee);
//		}
//		return true;
		return true;
	}

	public boolean solveApp(double call, double msg, double info, String location) {
//		String loc = pT.getmLocation();
//		if (loc == PlaceData.QUANGUO || loc == location) {
//			solveApp(call, msg, info);
//			return true;
//		}
//		return false;
		return true;
	}
}
