package com.mp.model;
/**
 * 
 * @author Administrator
 *
 */
public class PackageTariff {
	/**
	 * @param mTitle		: name of mobile-package（套餐名）
	 * @param mMouthlyFee	: monthly rental fee/RMB（月租费）
	 * @param mMonthlyCons	: monthly consumption fee/RMB（月最低消费）
	 * @param mDomCallDur	: domestic phone call duration/minute（国内呼叫）
	 * @param mDomInfoFlow	: domestic infomation flow/MB（国内流量）
	 * @param mDomMsg		: domestic text message/each（国内短信）
	 * @param mDomMmMsg		: domestic multimedia message/each（国内彩信）
	 * @param mWifi			: wifi time/hour（WIFI时长）
	 * @param mDomCall		: domestic phone call fee RMB/min（套餐内国内呼叫费用）
	 * @param mDomListen	: domestic phone listen fee RMB/min（套餐内国内接听费用）
	 * @param mDomCallBey	: domestic phone call fee beyond package RMB/min（套餐外国内呼叫费用）
	 * @param mDomLisBey	: domestic phone listen fee beyond package RMB/min（套餐外国内接听费用）
	 * @param mAddition		: additional service description（增值服务）
	 * @param mDescription	: other descriptions（其它）
	 * @param mAutoUpdateIF	: automatically update information flow addition package（流量自动升档）
	 */
	protected String mTitle;
	protected double mMonthlyFee;
	protected double mMonthlyCons;
	protected double mDomCallDur;
	protected double mDomInfoFlow;
	protected int mDomMsg;
	protected int mDomMmMsg;
	protected double mWifi;
	protected double mDomCall;
	protected double mDomListen;
	protected double mDomCallBey;
	protected double mDomLisBey;
	protected String mAddition;
	protected String mDescription;
	protected boolean mAutoUpdateIF;
	
	protected PackageTariff() {
		mTitle = new String();
		mMonthlyFee = 999;
		mMonthlyCons = 0;
		mDomCallDur = 0;
		mDomInfoFlow = 0;
		mDomMsg = 0;
		mDomMmMsg = 0;
		mWifi = 0;
		mDomCall = 0;
		mDomListen = 0;
		mDomCallBey = 0;
		mDomLisBey = 0;
		mAddition = new String();
		mDescription = new String();
		mAutoUpdateIF = false;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public double getmMonthlyFee() {
		return mMonthlyFee;
	}

	public void setmMonthlyFee(double mMonthlyFee) {
		this.mMonthlyFee = mMonthlyFee;
	}

	public double getmMonthlyCons() {
		return mMonthlyCons;
	}

	public void setmMonthlyCons(double mMonthlyCons) {
		this.mMonthlyCons = mMonthlyCons;
	}

	public double getmDomCallDur() {
		return mDomCallDur;
	}

	public void setmDomCallDur(double mDomCallDur) {
		this.mDomCallDur = mDomCallDur;
	}

	public double getmDomInfoFlow() {
		return mDomInfoFlow;
	}

	public void setmDomInfoFlow(double mDomInfoFlow) {
		this.mDomInfoFlow = mDomInfoFlow;
	}

	public int getmDomMsg() {
		return mDomMsg;
	}

	public void setmDomMsg(int mDomMsg) {
		this.mDomMsg = mDomMsg;
	}

	public int getmDomMmMsg() {
		return mDomMmMsg;
	}

	public void setmDomMmMsg(int mDomMmMsg) {
		this.mDomMmMsg = mDomMmMsg;
	}

	public double getmWifi() {
		return mWifi;
	}

	public void setmWifi(double mWifi) {
		this.mWifi = mWifi;
	}

	public double getmDomCall() {
		return mDomCall;
	}

	public void setmDomCall(double mDomCall) {
		this.mDomCall = mDomCall;
	}

	public double getmDomListen() {
		return mDomListen;
	}

	public void setmDomListen(double mDomListen) {
		this.mDomListen = mDomListen;
	}

	public double getmDomCallBey() {
		return mDomCallBey;
	}

	public void setmDomCallBey(double mDomCallBey) {
		this.mDomCallBey = mDomCallBey;
	}

	public double getmDomLisBey() {
		return mDomLisBey;
	}

	public void setmDomLisBey(double mDomLisBey) {
		this.mDomLisBey = mDomLisBey;
	}

	public String getmAddition() {
		return mAddition;
	}

	public void setmAddition(String mAddition) {
		this.mAddition = mAddition;
	}

	public String getmDescription() {
		return mDescription;
	}

	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}

	public boolean ismAutoUpdateIF() {
		return mAutoUpdateIF;
	}

	public void setmAutoUpdateIF(boolean mAutoUpdateIF) {
		this.mAutoUpdateIF = mAutoUpdateIF;
	}
}
