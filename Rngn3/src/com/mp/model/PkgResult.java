package com.mp.model;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.mp.util.placedata.city.HeiLongJiangChn;
import com.mp.util.placedata.city.HeiLongJiangFull;
import com.mp.util.placedata.province.ProvNameAbbr;
import com.mp.util.placedata.province.ProvNameChn;

public class PkgResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2703967560456778152L;
	private double price;
	private String name;
	private String prov;
	private String city;
	public PkgResult(double price, String name, String prov, String city) {
		super();
		this.price = price;
		this.name = name;
		this.prov = prov;
		this.city = city;
	}
	public PkgResult() {
		// TODO Auto-generated constructor stub
	}
	public double getPrice() {
		return Double.valueOf(new DecimalFormat("#.00").format(price));
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "PkgResult [name=" + name + ", price=" + price + ", prov="
				+ prov + ", city=" + city + "]";
	}

	public String getProvChn() {
		int index = ProvNameAbbr._placeList.indexOf(prov);
		if(index >= 0) {
			return ProvNameChn._placeList.get(index);
		}
		return "";
	}
	
	public String getCityChn() {
		int index = HeiLongJiangFull._placeList.indexOf(city);
		if(index >= 0) {
			return HeiLongJiangChn._placeList.get(index);
		}
		return "";
	}
}
