package com.mp.bean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import com.mp.model.PackageTariff;
import com.mp.model.SAEsqlForPT;
import com.mp.model.Tariff;
import com.mp.util.UtilData;

public class SolveBean implements Serializable {
	/**
	 * H:hashmap ZC:zol controller T:tariff Q:query RP:result price RPT:result
	 * package tariff
	 */
	private static final long serialVersionUID = 4641405399409191854L;
	private HashMap<String, PackageTariff> mTelecomH;
	private Date changeDate;
	private Tariff mTelecomT;
	private QueryBean mTelecomQ;
	public double mTelecomRP;
	public PackageTariff mTelecomRPT;
	public String mTelecomRSP;
	public String mTelecomRSPT;

	public SolveBean() {
		mTelecomH = new SAEsqlForPT().getPT();
		setChangeDate(new Date());
		mTelecomT = new Tariff(UtilData.CTELECOM);
		mTelecomQ = new QueryBean();
		mTelecomRPT = new PackageTariff();
		mTelecomRSP = new String();
		mTelecomRSPT = new String();
	}

	public synchronized void solve() {
		double mTemp1, mTemp2, mTemp3;
		QueryBean mTempQ;
		mTelecomRP = 999999.99;
		PackageTariff mTempPT = new PackageTariff();
		for (Entry<String, PackageTariff> entry : mTelecomH.entrySet()) {

			mTempPT = entry.getValue();
			if (null == mTempPT) {
				continue;
			}
			if (mTempPT.getmType() == UtilData.CTELECOM) {
				if (mTempPT.getmMonthlyFee() == UtilData.INVALID
						&& mTempPT.getmMonthlyCons() == UtilData.INVALID) {
					continue;
				}
				mTempQ = new QueryBean(mTelecomQ);

				mTemp1 = mTempPT.getmMonthlyFee();
				mTemp2 = mTempPT.getmMonthlyCons();
				mTemp3 = 0.0;

				// count call
				if (mTempQ.getCall() <= mTempPT.getmDomCallDur()) {
					mTempQ.setCall(0);
				} else {
					mTempQ.setCall((int) (mTempQ.getCall() - mTempPT
							.getmDomCallDur()));
					if (mTempPT.getmDomCallBey() != UtilData.UNUSED) {
						mTemp3 += mTempQ.getCall() * mTempPT.getmDomCallBey();
						mTempQ.setCall(0);
					}
				}
				// count msg
				if (mTempQ.getMsg() <= mTempPT.getmDomMsg()) {
					mTempQ.setMsg(0);
				} else {
					mTempQ.setMsg((int) (mTempQ.getMsg() - mTempPT.getmDomMsg()));
				}
				// count info
				if (mTempQ.getInfo() <= mTempPT.getmDomInfoFlow()) {
					mTempQ.setInfo(0);
				} else {
					mTempQ.setInfo((int) (mTempQ.getInfo() - mTempPT
							.getmDomInfoFlow()));
					if (mTempPT.getmDomInfoBey() != UtilData.UNUSED) {
						mTemp3 += mTempQ.getInfo() * mTempPT.getmDomInfoBey();
						mTempQ.setInfo(0);
					}
				}
				// count extra
				mTemp3 += mTelecomT.solve(mTempQ);
				if (mTemp2 >= mTemp3 && mTemp2 != UtilData.UNUSED
						&& mTemp2 != UtilData.INVALID) {
					mTemp1 += mTemp2;
				} else {
					mTemp1 += mTemp3;
				}
				if (mTelecomRP > mTemp1) {
					mTelecomRP = mTemp1;
					mTelecomRPT = mTempPT;
				}
			}
		}
		DecimalFormat df = new DecimalFormat("#####0.00");
		mTelecomRSP = df.format(mTelecomRP);
		mTelecomRSPT = mTelecomRPT.getmTitle();
	}

	public synchronized void solve(String call, String msg, String info) {
		mTelecomQ.setCall(Integer.valueOf(call));
		mTelecomQ.setMsg(Integer.valueOf(msg));
		mTelecomQ.setInfo(Integer.valueOf(info));
		solve();
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	

	public String getmTelecomRSP() {
		return mTelecomRSP;
	}

	public void setmTelecomRSP(String mTelecomRSP) {
		this.mTelecomRSP = mTelecomRSP;
	}

	public String getmTelecomRSPT() {
		return mTelecomRSPT;
	}

	public void setmTelecomRSPT(String mTelecomRSPT) {
		this.mTelecomRSPT = mTelecomRSPT;
	}

	public static void main(String[] args) {
		SolveBean sB = new SolveBean();
		sB.solve("1000", "100", "100");
		System.out.println(sB.mTelecomRP);
		System.out.println(sB.mTelecomRPT.getmTitle());
	}
}
