package com.mp.model;
/**
 * 
 * @author Administrator
 *
 */
public class PackageTariff {
	/**
	 * @param mTitle		: name of mobile-package���ײ�����
	 * @param mMouthlyFee	: monthly rental fee/RMB������ѣ�
	 * @param mMonthlyCons	: monthly consumption fee/RMB����������ѣ�
	 * @param mDomCallDur	: domestic phone call duration/minute�����ں��У�
	 * @param mDomInfoFlow	: domestic infomation flow/MB������������
	 * @param mDomMsg		: domestic text message/each�����ڶ��ţ�
	 * @param mDomMmMsg		: domestic multimedia message/each�����ڲ��ţ�
	 * @param mWifi			: wifi time/hour��WIFIʱ����
	 * @param mDomCall		: domestic phone call fee RMB/min���ײ��ڹ��ں��з��ã�
	 * @param mDomListen	: domestic phone listen fee RMB/min���ײ��ڹ��ڽ������ã�
	 * @param mDomCallBey	: domestic phone call fee beyond package RMB/min���ײ�����ں��з��ã�
	 * @param mDomLisBey	: domestic phone listen fee beyond package RMB/min���ײ�����ڽ������ã�
	 * @param mAddition		: additional service description����ֵ����
	 * @param mDescription	: other descriptions��������
	 * @param mAutoUpdateIF	: automatically update information flow addition package�������Զ�������
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
