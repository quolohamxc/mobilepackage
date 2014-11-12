package com.mp.telecom.model;

import java.io.Serializable;

import com.mp.model.PackageTariff;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;
/**
 * Ä¬ÈÏtypeÎªCTELECOM¡£
 * @author Administrator
 *
 */
public class SinglePkgTar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8770405202734206801L;
	private double price;
	private PackageTariff pT = null;

	public SinglePkgTar(PackageTariff pT) {
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
		this.setPrice(pT.solve(call, msg, info));
		return true;
	}

	public PackageTariff getpT() {
		return pT;
	}

	public void setpT(PackageTariff pT) {
		this.pT = pT;
	}

	public boolean solve(double call, double msg, double info, String location) {
		String loc = pT.getmLocation();
		if (loc == PlaceData.QUANGUO || loc == location) {
			solve(call, msg, info);
			return true;
		}
		return false;
	}

}
