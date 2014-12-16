package com.mp.bean;

import java.io.Serializable;

import com.mp.telecom.MultiPkgInfo;
import com.mp.telecom.SinglePkgInfo;
import com.mp.unicom.UMultiPkgInfo;
import com.mp.unicom.USinglePkgInfo;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;

/**
 * 多月账单分析。
 * 
 * @author Floion Z
 *
 */
public class MultiBillBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -34992626767115643L;
	public double[] feeFee = { 0, 0, 0, 0, 0 };
	public double[] callFee = { 0, 0, 0, 0, 0 };
	public double[] msgFee = { 0, 0, 0, 0, 0 };
	public double[] infoFee = { 0, 0, 0, 0, 0 };
	private final double thirst = 1.3;
	public SingleRequestBean sRB = null;
	/**
	 * 除去最高月份的平均花销
	 */
	public double feeAve = 0, callAve = 0, msgAve = 0, infoAve = 0;

	public double feeApp = 0, callApp = 0, msgApp = 0, infoApp = 0;
	/**
	 * 反向计算出的需求
	 */
	public double feeDur = 0, callDur = 0, msgDur = 0, infoDur = 0;
	int count = 0;
	boolean hasError = false;
	public boolean callDrunked = false, msgDrunked = false,
			infoDrunked = false;

	public SingleRequestBean getsRB() {
		return sRB;
	}

	public void setsRB(SingleRequestBean sRB) {
		this.sRB = sRB;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public boolean isCallDrunked() {
		return callDrunked;
	}

	public void setCallDrunked(boolean callDrunked) {
		this.callDrunked = callDrunked;
	}

	public boolean isMsgDrunked() {
		return msgDrunked;
	}

	public void setMsgDrunked(boolean msgDrunked) {
		this.msgDrunked = msgDrunked;
	}

	public boolean isInfoDrunked() {
		return infoDrunked;
	}

	public void setInfoDrunked(boolean infoDrunked) {
		this.infoDrunked = infoDrunked;
	}

	public MultiBillBean() {
		callDrunked = false;
		msgDrunked = false;
		infoDrunked = false;
		hasError = false;
	}

	public MultiBillBean(String[] fee, String[] call, String[] msg,
			String[] info, int formNum) {
		callDrunked = false;
		msgDrunked = false;
		infoDrunked = false;
		hasError = false;
		this.count = formNum;
		for (int iter = 0; iter < count; iter++) {
			try {
				if (fee[iter] != "") {
					feeFee[iter] = Double.valueOf(fee[iter]);
				}
				callFee[iter] = Double.valueOf(call[iter]);
				msgFee[iter] = Double.valueOf(msg[iter]);
				infoFee[iter] = Double.valueOf(info[iter]);
			} catch (NumberFormatException e) {
				hasError = true;
			}
		}
		drinkFuel();
	}

	/**
	 * 使用趋势分析法分析用户消费情况，通过计算定基动态比率判断是否需要附加套餐。 需要改进。
	 */
	private void drinkFuel() {
		if (count < 1) {
			hasError |= true;
		}
		if (hasError) {
			return;
		}
//		System.out.println(count);
		if (count == 1) {
			callDrunked = false;
			msgDrunked = false;
			infoDrunked = false;
			callAve = callFee[0];
			msgAve = msgFee[0];
			infoAve = infoFee[0];
			return;
		}
		int maxCall = callFee[0] <= callFee[1] ? 1 : 0;
		int minCall = callFee[0] > callFee[1] ? 1 : 0;
		int maxMsg = msgFee[0] <= msgFee[1] ? 1 : 0;
		int minMsg = msgFee[0] > msgFee[1] ? 1 : 0;
		int maxInfo = infoFee[0] <= infoFee[1] ? 1 : 0;
		int minInfo = infoFee[0] > infoFee[1] ? 1 : 0;

		for (int iter = 2, jter = count % 2 == 0 ? 3 : 1; iter < count
				&& jter < count; iter += 2, jter += 2) {
			int tempMax, tempMin;
			tempMax = callFee[iter] >= callFee[jter] ? iter : jter;
			tempMin = callFee[iter] < callFee[jter] ? iter : jter;
			maxCall = callFee[tempMax] >= callFee[maxCall] ? tempMax : maxCall;
			minCall = callFee[tempMin] < callFee[minCall] ? tempMin : minCall;

			tempMax = msgFee[iter] >= msgFee[jter] ? iter : jter;
			tempMin = msgFee[iter] < msgFee[jter] ? iter : jter;
			maxMsg = msgFee[tempMax] >= msgFee[maxMsg] ? tempMax : maxMsg;
			minMsg = msgFee[tempMin] < msgFee[minMsg] ? tempMin : minMsg;

			tempMax = infoFee[iter] >= infoFee[jter] ? iter : jter;
			tempMin = infoFee[iter] < infoFee[jter] ? iter : jter;
			maxInfo = infoFee[tempMax] >= infoFee[maxInfo] ? tempMax : maxInfo;
			minInfo = infoFee[tempMin] < infoFee[minInfo] ? tempMin : minInfo;
		}
		if (callFee[minCall] < 0 || msgFee[minMsg] < 0 || infoFee[minInfo] < 0) {
			hasError |= true;
			return;
		}
		callAve = 0;
		msgAve = 0;
		infoAve = 0;
		for (int iter = 0; iter < count; iter++) {
			if (iter != maxCall) {
				callAve += callFee[iter];
			}
			if (iter != maxMsg) {
				msgAve += msgFee[iter];
			}
			if (iter != maxInfo) {
				infoAve += infoFee[iter];
			}
		}
		callAve /= (count - 1);
		msgAve /= (count - 1);
		infoAve /= (count - 1);
		double rs = 0.0;
		try {
			rs = callFee[maxCall] / callAve;
			if (rs >= thirst) {
				callDrunked = true;
				callApp = callFee[maxCall] - callAve;
			} else {
				callDrunked = false;
				callAve = (callAve * (count - 1) + callFee[maxCall]) / count;
			}
		} catch (ArithmeticException e) {
			callDrunked = false;
		}
		try {
			rs = msgFee[maxMsg] / msgAve;
			if (rs >= thirst) {
				msgDrunked = true;
				msgApp = msgFee[maxMsg] - msgAve;
			} else {
				msgDrunked = false;
				msgAve = (msgAve * (count - 1) + msgFee[maxMsg]) / count;
			}
		} catch (ArithmeticException e) {
			msgDrunked = false;
		}
		try {
			rs = infoFee[maxInfo] / infoAve;
			if (rs >= thirst) {
				infoDrunked = true;
				infoApp = infoFee[maxInfo] - infoAve;
			} else {
				infoDrunked = false;
				infoAve = (infoAve * (count - 1) + infoFee[maxInfo]) / count;
			}
		} catch (ArithmeticException e) {
			infoDrunked = false;
		}
	}

	public void solve(int type, String prov, String city, int pkgcate, String jugglea,
			String juggleb, String jugglec, String singlea, String singleb,
			String singlec) {
		drinkFuel();
		if (hasError) {
			return;
		}
//		System.out.println(this.isInfoDrunked());
//		System.out.println(prov);
//		System.out.println(city);
//		System.out.println(pkgcate);
//		System.out.println(jugglea);
//		System.out.println(juggleb);
//		System.out.println(jugglec);
//		System.out.println(singlea);
//		System.out.println(singleb);
//		System.out.println(singlec);
		if (pkgcate == 2) {
			hasError |= true;
		} else if (pkgcate == 3) {
			// 积木套餐
			solveMulti(type, prov, city, jugglea, juggleb, jugglec);
		} else if (pkgcate == 4) {
			// 独立套餐
			solveSingle(type, prov, city, singlea);
		} else if (pkgcate == 5) {
			// 独立套餐
			solveSingle(type, prov, city, singleb);
		} else if (pkgcate == 6) {
			// 独立套餐
			solveSingle(type, prov, city, singlec);
		} else {
			// 未选择（其它）
		}

//		System.out.println(this.isInfoDrunked());
	}

	public void solveMulti(int type, String prov, String city, String jugglea,
			String juggleb, String jugglec) {
		if (hasError) {
			return;
		}
		if(type == UtilData.CTELECOM) {
			double[] smfee = MultiPkgInfo.queryFee(jugglea, callAve, juggleb,
					msgAve, jugglec, infoAve);
			sRB = new SingleRequestBean();
			sRB.solve(type, smfee[1], smfee[2], smfee[3], prov, city);
		} else if (type == UtilData.CUNICOM) {
			double[] smfee = UMultiPkgInfo.queryFee(jugglea, callAve, juggleb,
					msgAve, jugglec, infoAve);
			sRB = new SingleRequestBean();
			sRB.solve(type, smfee[1], smfee[2], smfee[3], prov, city);
		} else if (type == UtilData.CUNICOM) {
			
		} else {
			
		}
	}

	public void solveSingle(int type, String prov, String city, String single) {
		if (hasError) {
			return;
		}
		if(type == UtilData.CTELECOM) {
			double[] smfee = SinglePkgInfo.queryFee(single, callAve, msgAve,
					infoAve);
			sRB = new SingleRequestBean();
			sRB.solve(type, smfee[1], smfee[2], smfee[3], prov, city);
		} else if (type == UtilData.CUNICOM) {
			if(prov == PlaceData.QUANGUO && city == PlaceData.QUANGUO) {
				double[] smfee = USinglePkgInfo.queryFee(single, callAve, msgAve,
						infoAve);
				sRB = new SingleRequestBean();
				sRB.solve(type, smfee[1], smfee[2], smfee[3], prov, city);
			}
		} else if (type == UtilData.CUNICOM) {
			
		} else {
			
		}

	}

	public static void main(String[] args) {
		int i = 0;
		try {
			i /= 0;
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
			i /= 0;
		}
		Double.valueOf(i);
	}

}