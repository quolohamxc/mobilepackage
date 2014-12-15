package com.mp.telecom;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mp.telecom.model.AppPkgFuel;
import com.mp.util.UtilData;

/**
 * 附加套餐。
 * @author Administrator
 *
 */
public class AppPkgInfo implements Serializable {
	/**
	 * 调用solve方法后详细结果存储在appPkgFuel中。
	 */
	private static final long serialVersionUID = -5658714047751332224L;
	private int mType;
	private static double mDisCount;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static ArrayList<AppPkgFuel> appPkgList = null;
	public ArrayList<AppPkgFuel> appPkgFuel = null;

	static {
		appPkgList = new ArrayList<AppPkgFuel>();
	}
	
	public AppPkgInfo() {
		this.setmType(UtilData.CTELECOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public AppPkgInfo(int mType) {
		if(mType == UtilData.CMCC || mType == UtilData.CTELECOM || mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CTELECOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}
	
	public void addAppPkg(String type, double fee, double involve) {
		AppPkgFuel aPF = new AppPkgFuel(type, fee, involve);
		if(!appPkgList.contains(aPF)) {
			appPkgList.add(aPF);
		}
	}
	
	public double solve(double call, double msg, double info) {
		if (appPkgList.size() == 0) {
			addDefaultPkg();
		}
		appPkgFuel = new ArrayList<AppPkgFuel>();
		double rs = 0.0;
		//倒序排序
		Collections.sort(appPkgList, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				if(((AppPkgFuel) arg0).getmType() == ((AppPkgFuel) arg1).getmType()) {
					return (int) (((AppPkgFuel) arg1).getFee() - ((AppPkgFuel) arg0).getFee());
				} else {
					return ((AppPkgFuel) arg0).getmType().compareTo(((AppPkgFuel) arg1).getmType());
				}
			}
		});
		ArrayList<AppPkgFuel> temp = solveSup(CALL, call, 0);
		for(AppPkgFuel t : temp) {
			appPkgFuel.add(t);
		}
		temp = solveSup(MSG, msg, 0);
		for(AppPkgFuel t : temp) {
			appPkgFuel.add(t);
		}
		temp = solveSup(INFO, info, 0);
		for(AppPkgFuel t : temp) {
			appPkgFuel.add(t);
		}
		//正序排序
		Collections.sort(appPkgList, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				if(((AppPkgFuel) arg0).getmType() == ((AppPkgFuel) arg1).getmType()) {
					return (int) (((AppPkgFuel) arg0).getFee() - ((AppPkgFuel) arg1).getFee());
				} else {
					return ((AppPkgFuel) arg0).getmType().compareTo(((AppPkgFuel) arg1).getmType());
				}
			}
		});
		rs = solveCount(appPkgFuel);
		if (mDisCount > 0 && mDisCount <= 1) {
			rs *= mDisCount;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs) );
	}
	
	private ArrayList<AppPkgFuel> solveSup(String type, double req, int index) {
		if(index >= appPkgList.size()) {
			return null;
		}
		AppPkgFuel aPF = appPkgList.get(index);
		if(aPF.getmType() != type) {
			return solveSup(type, req, index + 1);
		}
		double opf = aPF.getFee(), opi = aPF.getInvolve(), opr = req;
		ArrayList<AppPkgFuel> aL = new ArrayList<AppPkgFuel>();
		while(opr >= opi) {
			aL.add(aPF);
			opr -= opi;
		}
		ArrayList<AppPkgFuel> temp = solveSup(type, opr, index + 1);
		if(temp == null) {
			aL.add(aPF);
		} else if(solveCount(temp) < opf) {
			for(AppPkgFuel t : temp) {
				aL.add(t);
			}
		} else {
			aL.add(aPF);
		}
		return aL;
		
	}
	
	private double solveCount(ArrayList<AppPkgFuel> aL) {
		double rs = 0.0;
		if(aL != null) {
			for(AppPkgFuel aPF : aL) {
				rs += aPF.getFee();
			}
		}
		return rs;
	}
	
	private void addDefaultPkg() {
		this.setDefaultDiscount();
		addAppPkg("call", 19.0, 100.0);
		addAppPkg("call", 29.0, 160.0);
		addAppPkg("call", 49.0, 280.0);
		addAppPkg("call", 69.0, 420.0);
		addAppPkg("call", 89.0, 600.0);
		addAppPkg("info", 10.0, 60.0);
		addAppPkg("info", 20.0, 150.0);
		addAppPkg("info", 30.0, 300.0);
		addAppPkg("info", 50.0, 800.0);
		addAppPkg("info", 100.0, 2000.0);
		addAppPkg("msg", 5.0, 80.0);
		addAppPkg("msg", 10.0, 200.0);
		addAppPkg("msg", 20.0, 400.0);
		addAppPkg("msg", 40.0, 800.0);
	}
	
	private void setDefaultDiscount() {
		setmDisCount(1.0);
	}
	
	public static double getmDisCount() {
		return mDisCount;
	}

	public static void setmDisCount(double mDisCount) {
		AppPkgInfo.mDisCount = mDisCount;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}
	
	public static void main(String[] args) {
		AppPkgInfo aPI = new AppPkgInfo();
		System.out.println(aPI.solve(10050, 10032, 1009));
		for (AppPkgFuel aPF : AppPkgInfo.appPkgList) {
			System.out.println(aPF.toString());
		}
		System.out.println("----");
		for (AppPkgFuel aPF : aPI.appPkgFuel) {
			System.out.println(aPF.toString());
		}
	}
}
