package com.mp.model;

import java.io.Serializable;

import com.mp.util.PlaceData;
import com.mp.util.UtilData;

/**
 * 
 * @author Administrator
 *
 */
public class PackageTariff implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7479837032685887798L;
	private int mType;

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	// TODO: add location descriptor
	/**
	 * mTitle : name of mobile-package���ײ����� mMouthlyFee : monthly rental
	 * fee/RMB������ѣ� mMonthlyCons : monthly consumption fee/RMB����������ѣ�
	 * mDomCallDur : domestic phone call duration/minute�����ں��У� mDomInfoFlow :
	 * domestic infomation flow/MB������������ mDomMsg : domestic text
	 * message/each�����ڶ��ţ� mDomMmMsg : domestic multimedia message/each�����ڲ��ţ�
	 * mWifi : wifi time/hour��WIFIʱ���� mDomCall : domestic phone call fee
	 * RMB/min���ײ��ڹ��ں��з��ã� mDomLis : domestic phone listen fee RMB/min���ײ��ڹ��ڽ������ã�
	 * mDomCallBey : domestic phone call fee beyond package RMB/min���ײ�����ں��з��ã�
	 * mDomLisBey : domestic phone listen fee beyond package RMB/min���ײ�����ڽ������ã�
	 * mDomInfoBey : domestic infomation flow beyond package RMB/MB���ײ�������������ã�
	 * mAddition : additional service description����ֵ���� mDescription : other
	 * descriptions�������� mAutoUpdateIF : automatically update information flow
	 * addition package�������Զ�������
	 */
	private String mTitle;
	private double mMonthlyFee;
	private double mMonthlyCons;
	private int mDomCallDur;
	private double mDomInfoFlow;
	private int mDomMsg;
	private int mDomMmMsg;
	private int mWifi;
	private double mDomCall;
	private double mDomListen;
	private double mDomCallBey;
	private double mDomLisBey;
	private double mDomInfoBey;
	private String mAddition;
	private String mDescription;
	private boolean mAutoUpdateIF;
	private String mLocation;

	public PackageTariff() {
		init();
	}

	private void init() {
		mTitle = new String();
		mMonthlyFee = UtilData.INVALID;
		mMonthlyCons = UtilData.INVALID;
		mDomCallDur = UtilData.UNUSED;
		mDomInfoFlow = UtilData.UNUSED;
		mDomMsg = UtilData.UNUSED;
		mDomMmMsg = UtilData.UNUSED;
		mWifi = UtilData.UNUSED;
		mDomCall = UtilData.UNUSED;
		mDomListen = UtilData.UNUSED;
		mDomCallBey = UtilData.UNUSED;
		mDomLisBey = UtilData.UNUSED;
		mDomInfoBey = UtilData.UNUSED;
		mAddition = new String();
		mDescription = new String();
		mAutoUpdateIF = false;
		mLocation = PlaceData.QUANGUO;
	}

	public PackageTariff(int mType) {
		init();
		this.setmType(mType);
	}

	public PackageTariff(int type, String title, double fee, double cons,
			int calldur, double info, int msg, int mmsg, int wifi,
			double callin, double lisin, double callbey, double lisbey,
			double infobey, String addition, String description, int autoupdate, String location) {
		this.mType = type;
		this.mTitle = title;
		this.mMonthlyFee = fee;
		this.mMonthlyCons = cons;
		this.mDomCallDur = calldur;
		this.mDomInfoFlow = info;
		this.mDomMsg = msg;
		this.mDomMmMsg = mmsg;
		this.mWifi = wifi;
		this.mDomCall = callin;
		this.mDomListen = lisin;
		this.mDomCallBey = callbey;
		this.mDomLisBey = lisbey;
		this.mDomInfoBey = infobey;
		this.mAddition = addition;
		this.mDescription = description;
		this.mAutoUpdateIF = autoupdate == 1 ? true : false;
		this.mLocation = location;
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

	public int getmDomCallDur() {
		return mDomCallDur;
	}

	public void setmDomCallDur(int mDomCallDur) {
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

	public int getmWifi() {
		return mWifi;
	}

	public void setmWifi(int mWifi) {
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

	public double getmDomInfoBey() {
		return mDomInfoBey;
	}

	public void setmDomInfoBey(double mDomInfoBey) {
		this.mDomInfoBey = mDomInfoBey;
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

	public String getmLocation() {
		return mLocation;
	}

	public void setmLocation(String mLocation) {
		this.mLocation = mLocation;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else {
			if (this.getClass() == obj.getClass()) {
				PackageTariff u = (PackageTariff) obj;
				if (this.getmTitle().equals(u.getmTitle())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
}
