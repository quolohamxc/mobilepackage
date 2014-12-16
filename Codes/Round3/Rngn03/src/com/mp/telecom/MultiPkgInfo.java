package com.mp.telecom;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mp.telecom.model.MultiPkgJuggle;
import com.mp.util.UtilData;

public class MultiPkgInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7810934109703147063L;
	private int mType;
	private static double mDisCount;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static ArrayList<MultiPkgJuggle> multiPkgList = null;
	// ��¼���ַ��������ŷ�����ȫ������
	// 1-3Ϊ��һ����4-6Ϊ�ڶ���
	public MultiPkgJuggle mPJ1 = null;
	public MultiPkgJuggle mPJ2 = null;
	public MultiPkgJuggle mPJ3 = null;
	public MultiPkgJuggle mPJ4 = null;
	public MultiPkgJuggle mPJ5 = null;
	public MultiPkgJuggle mPJ6 = null;
	
	static {
		multiPkgList = new ArrayList<MultiPkgJuggle>();
	}

	public MultiPkgInfo() {
		this.setmType(UtilData.CTELECOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public MultiPkgInfo(int mType) {
		if(mType == UtilData.CMCC || mType == UtilData.CTELECOM || mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CTELECOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public void addMultiPkg(String mType, double cTime, double cost,
			double beyond) {
		MultiPkgJuggle mPJ = new MultiPkgJuggle(mType, cTime, cost, beyond);
		if(!multiPkgList.contains(mPJ)) {
			multiPkgList.add(mPJ);
		}
	}

	public double solve(double call, double msg, double info) {
		if (multiPkgList.size() == 0) {
			addDefaultPkg();
		}
		double min1 = 9991.0, min2 = 9991.0, min3 = 9991.0;
		double max1 = 9991.0, max2 = 9991.0, max3 = 9991.0;
		double temp;
		for (MultiPkgJuggle mPJ : multiPkgList) {
			temp = mPJ.solve(call, msg, info);
			if (mPJ.getmType() == CALL) {
				if (mPJ.getcTime() == call) {
					if (temp < max1) {
						max1 = temp;
						//  �޸ķ�������
						mPJ4 = mPJ;
					}
					if (temp < min1) {
						min1 = temp;
						//  �޸ķ�������
						mPJ1 = mPJ;
					}
				} else if (mPJ.getcTime() < call) {
					if (temp < max1) {
						max1 = temp;
						//  �޸ķ�������
						mPJ4 = mPJ;
					}
				} else if (mPJ.getcTime() > call) {
					if (temp < min1) {
						min1 = temp;
						//  �޸ķ�������
						mPJ1 = mPJ;
					}
				}
			} else if (mPJ.getmType() == MSG) {
				if (mPJ.getcTime() == msg) {
					if (temp < max2) {
						max2 = temp;
						//  �޸ķ�������
						mPJ5 = mPJ;
					}
					if (temp < min2) {
						min2 = temp;
						//  �޸ķ�������
						mPJ2 = mPJ;
					}
				} else if (mPJ.getcTime() < msg) {
					if (temp < max2) {
						max2 = temp;
						//  �޸ķ�������
						mPJ5 = mPJ;
					}
				} else if (mPJ.getcTime() > msg) {
					if (temp < min2) {
						min2 = temp;
						//  �޸ķ�������
						mPJ2 = mPJ;
					}
				}
			} else if (mPJ.getmType() == INFO) {
				if (mPJ.getcTime() == info) {
					if (temp < max3) {
						max3 = temp;
						//  �޸ķ�������
						mPJ6 = mPJ;
					}
					if (temp < min3) {
						min3 = temp;
						//  �޸ķ�������
						mPJ3 = mPJ;
					}
				} else if (mPJ.getcTime() < info) {
					if (temp < max3) {
						max3 = temp;
						//  �޸ķ�������
						mPJ6 = mPJ;
					}
				} else if (mPJ.getcTime() > info) {
					if (temp < min3) {
						min3 = temp;
						//  �޸ķ�������
						mPJ3 = mPJ;
					}
				}
			}
//			System.out.println(min1 + " " + min2 + " " + min3 + " " + max1
//					+ " " + max2 + " " + max3);
		}

//		double rs1 = min1 + min2 + min3, rs2 = max1 + max2 + max3;
//
//		if (rs1 > rs2) {
//			return rs2;
//		} else {
//			return rs1;
//		}
		double rs = 0.0;
		rs += min1 > max1 ? max1 : min1;
		mPJ1 = min1 > max1 ? mPJ4 : mPJ1;
		rs += min2 > max2 ? max2 : min2;
		mPJ2 = min2 > max2 ? mPJ5 : mPJ2;
		rs += min3 > max3 ? max3 : min3;
		mPJ3 = min3 > max3 ? mPJ6 : mPJ3;
		if (mDisCount > 0 && mDisCount <= 1) {
			rs *= mDisCount;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs) );
	}

	private void addDefaultPkg() {
		this.setDefaultDiscount();
		//add call pkg
		this.addMultiPkg(CALL, 100, 19, 0.20);
		this.addMultiPkg(CALL, 160, 29, 0.20);
		this.addMultiPkg(CALL, 280, 49, 0.20);
		this.addMultiPkg(CALL, 420, 69, 0.20);
		this.addMultiPkg(CALL, 600, 89, 0.20);
		//add msg pkg
		this.addMultiPkg(MSG, 0, 0, 0.10);
		this.addMultiPkg(MSG, 80, 5, 0.10);
		this.addMultiPkg(MSG, 200, 10, 0.10);
		this.addMultiPkg(MSG, 400, 20, 0.10);
		this.addMultiPkg(MSG, 800, 40, 0.10);
		//add info pkg
		this.addMultiPkg(INFO, 0, 0, 0.3);
		this.addMultiPkg(INFO, 60, 10, 0.3);
		this.addMultiPkg(INFO, 150, 20, 0.3);
		this.addMultiPkg(INFO, 300, 30, 0.3);
		this.addMultiPkg(INFO, 800, 50, 0.3);
		this.addMultiPkg(INFO, 2000, 100, 0.3);
	}
	
	public MultiPkgJuggle getmPJ1() {
		return mPJ1;
	}
	public void setmPJ1(MultiPkgJuggle mPJ1) {
		this.mPJ1 = mPJ1;
	}

	public MultiPkgJuggle getmPJ2() {
		return mPJ2;
	}

	public void setmPJ2(MultiPkgJuggle mPJ2) {
		this.mPJ2 = mPJ2;
	}

	public MultiPkgJuggle getmPJ3() {
		return mPJ3;
	}

	public void setmPJ3(MultiPkgJuggle mPJ3) {
		this.mPJ3 = mPJ3;
	}

	public MultiPkgJuggle getmPJ4() {
		return mPJ4;
	}

	public void setmPJ4(MultiPkgJuggle mPJ4) {
		this.mPJ4 = mPJ4;
	}

	public MultiPkgJuggle getmPJ5() {
		return mPJ5;
	}

	public void setmPJ5(MultiPkgJuggle mPJ5) {
		this.mPJ5 = mPJ5;
	}

	public MultiPkgJuggle getmPJ6() {
		return mPJ6;
	}

	public void setmPJ6(MultiPkgJuggle mPJ6) {
		this.mPJ6 = mPJ6;
	}
	
	private void setDefaultDiscount() {
		setmDisCount(1.0);
	}

	public static double getmDisCount() {
		return mDisCount;
	}

	public static void setmDisCount(double mDisCount) {
		MultiPkgInfo.mDisCount = mDisCount;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public static void main(String[] args) {
		// http://kzone.zhidao.189.cn/baseshow.do?bid=10379&title=&kid=5845&tid=10381
		System.out.println(new MultiPkgInfo().solve(300, 300, 1100));

	}
}
