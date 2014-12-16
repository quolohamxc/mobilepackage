package com.mp.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.mp.bean.QueryBean;

public class Tariff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1236189291196507604L;
	private int mType;
	private String location;
	ArrayList<msg> msgArr;

	public Tariff() {
		init();
	}

	public Tariff(int mType) {
		init();
		this.setmType(mType);
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<msg> getMsgArr() {
		return msgArr;
	}

	public void setMsgArr(ArrayList<msg> msgArr) {
		this.msgArr = msgArr;
	}

	private void init() {
		// TODO Auto-generated method stub
		msgArr = new ArrayList<msg>();
		msg m = new msg();
		msgArr.add(m);
	}

	public void add(int cate, String title, int number, double price) {
		if (cate == 1) {
			msg m = new msg(title, number, price);
			msgArr.add(m);
		}
	}

	class msg {
		String title;
		int number;
		double price;

		msg() {
			title = new String(location + "短信标准资费");
			number = 1;
			price = 0.1;
		}

		msg(String title, int number, double price) {
			this.title = title;
			this.number = number;
			this.price = price;
		}
	}

	class call {
		String title;
		int minute;
		double price;

		call() {
			title = new String(location + "短信标准资费");
			minute = 1;
			price = 0.4;
		}

		call(String title, int number, double price) {
			this.title = title;
			this.minute = number;
			this.price = price;
		}
	}

	public double solve(QueryBean mTempQ) {
		// TODO Auto-generated method stub
		return mTempQ.getMsg() * 0.1;
	}

}
