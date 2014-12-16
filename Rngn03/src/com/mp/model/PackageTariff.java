package com.mp.model;


import java.io.Serializable;
import java.text.DecimalFormat;

import com.mp.util.PlaceData;
import com.mp.util.UtilData;

/**
 * �ײ���Ϣ�� solve������ʹ����Ĭ�ϱ�׼�ʷѡ�
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
	/**
	 * �ײ���
	 */
	private String mTitle;
	/**
	 * �����/Ԫ
	 */
	private double mMonthlyFee;
	/**
	 * ���������/Ԫ ���������Ϊ����ʾ�������ѡ�
	 */
	private double mMonthlyCons;
	/**
	 * ��������/����
	 */
	private int mDomCallDur;
	/**
	 * ��������/MB
	 */
	private double mDomInfoFlow;
	/**
	 * ���ڶ���/��
	 */
	private int mDomMsg;
	/**
	 * ���ڲ���/��
	 */
	private int mDomMmMsg;
	/**
	 * ʹ��wifiʱ��/Сʱ
	 */
	private int mWifi;
	/**
	 * �ײ��ڹ��ں��� Ԫ/����
	 */
	private double mDomCall;
	/**
	 * �ײ��ڹ��ڽ��� Ԫ/����
	 */
	private double mDomListen;
	/**
	 * �ײ�����ں��� Ԫ/����
	 */
	private double mDomCallBey;
	/**
	 * �ײ�����ڽ��� Ԫ/����
	 */
	private double mDomLisBey;
	/**
	 * �ײ���������� Ԫ/MB
	 */
	private double mDomInfoBey;
	/**
	 * ��ֵ����
	 */
	private String mAddition;
	/**
	 * ����
	 */
	private String mDescription;
	/**
	 * �����Զ�����
	 */
	private boolean mAutoUpdateIF;
	/**
	 * ʡ
	 */
	private String mLocation;
	/**
	 * ��
	 */
	private String mCity;

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
		mCity = new String();
	}

	public PackageTariff(int mType) {
		init();
		this.setmType(mType);
	}

	public PackageTariff(int mType, String mTitle, double mMonthlyFee,
			double mMonthlyCons, int mDomCallDur, double mDomInfoFlow,
			int mDomMsg, int mDomMmMsg, int mWifi, double mDomCall,
			double mDomListen, double mDomCallBey, double mDomLisBey,
			double mDomInfoBey, String mAddition, String mDescription,
			boolean mAutoUpdateIF, String mLocation, String mCity) {
		super();
		this.mType = mType;
		this.mTitle = mTitle;
		this.mMonthlyFee = mMonthlyFee;
		this.mMonthlyCons = mMonthlyCons;
		this.mDomCallDur = mDomCallDur;
		this.mDomInfoFlow = mDomInfoFlow;
		this.mDomMsg = mDomMsg;
		this.mDomMmMsg = mDomMmMsg;
		this.mWifi = mWifi;
		this.mDomCall = mDomCall;
		this.mDomListen = mDomListen;
		this.mDomCallBey = mDomCallBey;
		this.mDomLisBey = mDomLisBey;
		this.mDomInfoBey = mDomInfoBey;
		this.mAddition = mAddition;
		this.mDescription = mDescription;
		this.mAutoUpdateIF = mAutoUpdateIF;
		this.mLocation = mLocation;
		this.mCity = mCity;
	}

	public PackageTariff(int mType, String mTitle, double mMonthlyFee,
			double mMonthlyCons, int mDomCallDur, double mDomInfoFlow,
			int mDomMsg, int mDomMmMsg, int mWifi, double mDomCall,
			double mDomListen, double mDomCallBey, double mDomLisBey,
			double mDomInfoBey, String mAddition, String mDescription,
			boolean mAutoUpdateIF, String mLocation, String mCity,
			boolean step, double step1, double fee1, double step2, double fee2,
			double step3, double fee3) {
		super();
		this.mType = mType;
		this.mTitle = mTitle;
		this.mMonthlyFee = mMonthlyFee;
		this.mMonthlyCons = mMonthlyCons;
		this.mDomCallDur = mDomCallDur;
		this.mDomInfoFlow = mDomInfoFlow;
		this.mDomMsg = mDomMsg;
		this.mDomMmMsg = mDomMmMsg;
		this.mWifi = mWifi;
		this.mDomCall = mDomCall;
		this.mDomListen = mDomListen;
		this.mDomCallBey = mDomCallBey;
		this.mDomLisBey = mDomLisBey;
		this.mDomInfoBey = mDomInfoBey;
		this.mAddition = mAddition;
		this.mDescription = mDescription;
		this.mAutoUpdateIF = mAutoUpdateIF;
		this.mLocation = mLocation;
		this.mCity = mCity;
		this.step = step;
		this.step1 = step1;
		this.fee1 = fee1;
		this.step2 = step2;
		this.fee2 = fee2;
		this.step3 = step3;
		this.fee3 = fee3;
	}

	public PackageTariff(int mType, String mTitle, double mMonthlyFee,
			double mMonthlyCons, int mDomCallDur, double mDomInfoFlow,
			int mDomMsg, int mDomMmMsg, int mWifi, double mDomCall,
			double mDomListen, double mDomCallBey, double mDomLisBey,
			double mDomInfoBey, String mAddition, String mDescription,
			int mAutoUpdateIF, String mLocation, String mCity) {
		super();
		this.mType = mType;
		this.mTitle = mTitle;
		this.mMonthlyFee = mMonthlyFee;
		this.mMonthlyCons = mMonthlyCons;
		this.mDomCallDur = mDomCallDur;
		this.mDomInfoFlow = mDomInfoFlow;
		this.mDomMsg = mDomMsg;
		this.mDomMmMsg = mDomMmMsg;
		this.mWifi = mWifi;
		this.mDomCall = mDomCall;
		this.mDomListen = mDomListen;
		this.mDomCallBey = mDomCallBey;
		this.mDomLisBey = mDomLisBey;
		this.mDomInfoBey = mDomInfoBey;
		this.mAddition = mAddition;
		this.mDescription = mDescription;
		this.mAutoUpdateIF = mAutoUpdateIF == 0 ? false : true;
		this.mLocation = mLocation;
		this.mCity = mCity;
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

	public String getmCity() {
		return mCity;
	}

	public void setmCity(String mCity) {
		this.mCity = mCity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageTariff other = (PackageTariff) obj;
		if (mAddition == null) {
			if (other.mAddition != null)
				return false;
		} else if (!mAddition.equals(other.mAddition))
			return false;
		if (mAutoUpdateIF != other.mAutoUpdateIF)
			return false;
		if (mDescription == null) {
			if (other.mDescription != null)
				return false;
		} else if (!mDescription.equals(other.mDescription))
			return false;
		if (Double.doubleToLongBits(mDomCall) != Double
				.doubleToLongBits(other.mDomCall))
			return false;
		if (Double.doubleToLongBits(mDomCallBey) != Double
				.doubleToLongBits(other.mDomCallBey))
			return false;
		if (mDomCallDur != other.mDomCallDur)
			return false;
		if (Double.doubleToLongBits(mDomInfoBey) != Double
				.doubleToLongBits(other.mDomInfoBey))
			return false;
		if (Double.doubleToLongBits(mDomInfoFlow) != Double
				.doubleToLongBits(other.mDomInfoFlow))
			return false;
		if (Double.doubleToLongBits(mDomLisBey) != Double
				.doubleToLongBits(other.mDomLisBey))
			return false;
		if (Double.doubleToLongBits(mDomListen) != Double
				.doubleToLongBits(other.mDomListen))
			return false;
		if (mDomMmMsg != other.mDomMmMsg)
			return false;
		if (mDomMsg != other.mDomMsg)
			return false;
		if (mLocation == null) {
			if (other.mLocation != null)
				return false;
		} else if (!mLocation.equals(other.mLocation))
			return false;
		if (Double.doubleToLongBits(mMonthlyCons) != Double
				.doubleToLongBits(other.mMonthlyCons))
			return false;
		if (Double.doubleToLongBits(mMonthlyFee) != Double
				.doubleToLongBits(other.mMonthlyFee))
			return false;
		if (mTitle == null) {
			if (other.mTitle != null)
				return false;
		} else if (!mTitle.equals(other.mTitle))
			return false;
		if (mType != other.mType)
			return false;
		if (mWifi != other.mWifi)
			return false;
		return true;
	}

	@Override
//	 public String toString() {
//	 return "PackageTariff [mType=" + mType + ", mTitle=" + mTitle
//	 + ", mMonthlyFee=" + mMonthlyFee + ", mMonthlyCons="
//	 + mMonthlyCons + ", mDomCallDur=" + mDomCallDur
//	 + ", mDomInfoFlow=" + mDomInfoFlow + ", mDomMsg=" + mDomMsg
//	 + ", mDomMmMsg=" + mDomMmMsg + ", mWifi=" + mWifi
//	 + ", mDomCall=" + mDomCall + ", mDomListen=" + mDomListen
//	 + ", mDomCallBey=" + mDomCallBey + ", mDomLisBey=" + mDomLisBey
//	 + ", mDomInfoBey=" + mDomInfoBey + ", mAddition=" + mAddition
//	 + ", mDescription=" + mDescription + ", mAutoUpdateIF="
//	 + mAutoUpdateIF + ", mLocation=" + mLocation + ", mCity="
//	 + mCity + ", step=" + step + ", step1=" + step1 + ", fee1="
//	 + fee1 + ", step2=" + step2 + ", fee2=" + fee2 + ", step3="
//	 + step3 + ", fee3=" + fee3 + "]";
//	 }
	public String toString() {
		return "addSinglePkg(new PackageTariff (" + mType + ", \"" + mTitle
				+ "\", " + mMonthlyFee + ", " + mMonthlyCons + ", "
				+ mDomCallDur + ", " + mDomInfoFlow + ", " + mDomMsg + ", "
				+ mDomMmMsg + ", " + mWifi + ", " + mDomCall + ", "
				+ mDomListen + ", " + mDomCallBey + ", " + mDomLisBey + ", "
				+ mDomInfoBey + ", \"" + mAddition + "\", \"" + mDescription
				+ "\", " + mAutoUpdateIF + ", \"" + mLocation + "\", \""
				+ mCity + "\", " + step + ", " + step1 + ", " + fee1 + ", "
				+ step2 + ", " + fee2 + ", " + step3 + ", " + fee3 + "));";
	}

	public double solve(double call, double msg, double info) {
		if (isStep() && info != 0.0) {
			return solve(call, msg, 0.0) + solveStep(info);
		}
		double rs = 0.0;
		if (this.mMonthlyFee == UtilData.INVALID
				&& this.mMonthlyCons == UtilData.INVALID) {
			return rs;
		}
		double opc = call, opm = msg, opi = info;
		// use default data
		double callBey = 0.2, msgBey = 0.1, infoBey = 0.3;
		// operate on call
		if (this.mDomCallDur != UtilData.UNUSED) {
			if (opc < this.mDomCallDur) {
				opc = 0;
			} else {
				opc -= this.mDomCallDur;
			}
		}
		if (this.mDomCall != UtilData.UNUSED) {
			rs += opc * this.mDomCall;
		} else if (this.mDomCallBey != UtilData.UNUSED) {
			rs += opc * this.mDomCallBey;
		} else {
			rs += opc * callBey;
		}
		// operate on msg
		if (this.mDomMsg != UtilData.UNUSED) {
			if (opm < this.mDomMsg) {
				opm = 0;
			} else {
				opm -= this.mDomMsg;
			}
		}
		rs += opm * msgBey;

		// operate on info
		if (this.mDomInfoFlow != UtilData.UNUSED) {
			if (opi < this.mDomInfoFlow) {
				opi = 0;
			} else {
				opi -= this.mDomInfoFlow;
			}
		}
		if (this.mDomInfoBey != UtilData.UNUSED) {
			rs += opi * this.mDomInfoBey;
		} else {
			rs += opi * infoBey;
		}

		// check
		if (!(this.mMonthlyCons == UtilData.INVALID)) {
			if (this.mMonthlyCons < 0) {
				if(rs < Math.abs(this.mMonthlyCons)) {
					rs = 0;
				} else {
					rs -= Math.abs(this.mMonthlyCons);
				}
			} else {
				rs = this.mMonthlyCons;
			}
		}

		if (!(this.mMonthlyFee == UtilData.INVALID)) {
			rs += this.mMonthlyFee;
		}

		return Double.valueOf(new DecimalFormat("#.00").format(rs) );
	}

	// �ֶμƷ�
	private boolean step = false;
	private double step1, fee1, step2, fee2, step3, fee3;

	public boolean isStep() {
		return step;
	}

	public void setStep(boolean step) {
		this.step = step;
	}

	public double solveStep(double info) {
		double rs = 0.0;
		if (step1 != 0.0) {
			if (info < step1) {
				rs += info * fee1;
			} else {
				rs += step1 * fee1;
				if (step2 != 0.0) {
					if (info < step2) {
						rs += (info - step1) * fee2;
					} else {
						rs += (step2 - step1) * fee2;
						if (step3 != 0.0) {
							rs += (info - step2) * fee3;
						}
					}
				}
			}
		}
		return rs;
	}

	public void setSteps(double step1, double fee1) {
		this.step1 = step1;
		this.fee1 = fee1;
	}

	public void setSteps(double step1, double fee1, double step2, double fee2) {
		this.step1 = step1;
		this.fee1 = fee1;
		this.step2 = step2;
		this.fee2 = fee2;
	}

	public void setSteps(double step1, double fee1, double step2, double fee2,
			double step3, double fee3) {
		this.step1 = step1;
		this.fee1 = fee1;
		this.step2 = step2;
		this.fee2 = fee2;
		this.step3 = step3;
		this.fee3 = fee3;
	}

}
