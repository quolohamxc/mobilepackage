package com.mp.bean;

import java.io.Serializable;

public class QueryBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5198476695364258324L;
	private int call;
	private int msg;
	private int info;
	
	QueryBean() {
		
	}
	QueryBean(int call, int msg, int info) {
		this.call = call;
		this.msg = msg;
		this.info = info;
	}
	QueryBean(final QueryBean q) {
		this.call = q.call;
		this.info = q.info;
		this.msg = q.msg;
	}
	public int getCall() {
		return call;
	}
	public void setCall(int call) {
		this.call = call;
	}
	public int getMsg() {
		return msg;
	}
	public void setMsg(int msg) {
		this.msg = msg;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	
}
