package com.mp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mp.model.PkgResult;
import com.mp.telecom.MultiPkgInfo;
import com.mp.telecom.NonPkgInfo;
import com.mp.telecom.SinglePkgInfo;
import com.mp.unicom.UMultiPkgInfo;
import com.mp.unicom.USinglePkgInfo;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class SingleRequestBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3501739614532658733L;
	public ArrayList<PkgResult> pRList;
	private final int showSize = 8;

	public SingleRequestBean() {
		pRList = new ArrayList<PkgResult>();
	}

	public synchronized void solve(int type, String call, String msg,
			String info) {
		solve(String.valueOf(type), call, msg, info, PlaceData.QUANGUO, PlaceData.QUANGUO);

	}
	
	public synchronized void solve(String type, String call, String msg,
			String info) {
		solve(type, call, msg, info, PlaceData.QUANGUO, PlaceData.QUANGUO);

	}

	public void solve(int type, String call, String msg, String info,
			String prov, String city) {
		try {
			if (call != null && !"".equals(call)
					&& UtilMethod.isNumeric(call) && msg != null
					&& !"".equals(msg) && UtilMethod.isNumeric(msg)
					&& info != null && !"".equals(info)
					&& UtilMethod.isNumeric(info)) {
				solve(type, Double.valueOf(call),
						Double.valueOf(msg), Double.valueOf(info), prov, city);
			}
		} catch (NumberFormatException e) {
			return;
		}
	}
	
	public synchronized void solve(String type, String call, String msg,
			String info, String prov, String city) {
		try {
			if (type != null && !"".equals(type) && UtilMethod.isNumeric(type)
					&& call != null && !"".equals(call)
					&& UtilMethod.isNumeric(call) && msg != null
					&& !"".equals(msg) && UtilMethod.isNumeric(msg)
					&& info != null && !"".equals(info)
					&& UtilMethod.isNumeric(info)) {
				solve(Integer.valueOf(type), Double.valueOf(call),
						Double.valueOf(msg), Double.valueOf(info), prov, city);
			}
		} catch (NumberFormatException e) {
			return;
		}

	}

	public synchronized void solve(int type, double call, double msg,
			double info) {
		solve(type, call, msg, info, PlaceData.QUANGUO, PlaceData.QUANGUO);
	}

	public synchronized void solve(int type, double call, double msg,
			double info, String prov, String city) {
		if (type == UtilData.CTELECOM) {
			// AppPkgInfo aPI = new AppPkgInfo();
			MultiPkgInfo mPI = new MultiPkgInfo();
			NonPkgInfo nPI = new NonPkgInfo();
			SinglePkgInfo sPI = new SinglePkgInfo();

			mPI.solve(call, msg, info);
			addPR(mPI.getPrice(), mPI.getDescription(), PlaceData.QUANGUO,
					PlaceData.QUANGUO);
			nPI.solve(call, msg, info);
			if(!nPI.isOverPkg()) {
				addPR(nPI.getPrice(), nPI.getDescription(), PlaceData.QUANGUO,
						PlaceData.QUANGUO);
			}
			sPI.solve(call, msg, info);
			for (int iter = 0, jter = 0; jter < showSize
					&& iter < sPI.singlePkgTar.size(); iter++, jter++) {
				double price = sPI.singlePkgTar.get(iter).getPrice();
				String name = sPI.singlePkgTar.get(iter).getDescription();
				String prov1 = sPI.singlePkgTar.get(iter).getpT()
						.getmLocation();
				String city1 = sPI.singlePkgTar.get(iter).getpT().getmCity();
				boolean jmpFlag = false;
				if (prov != PlaceData.QUANGUO) {
					if(prov1 != null && prov1 != "null" && prov1 != prov && prov1 != PlaceData.QUANGUO) {
						jmpFlag = true;
					}
				}
				if (city != PlaceData.QUANGUO) {
					if(city1 != null && city1 != "null" && city1 != city && city1 != PlaceData.QUANGUO) {
						jmpFlag = true;
					}
				}
				if (city1 == null) {
					
				}
				if (jmpFlag) {
					jter--;
					continue;
				}
				addPR(price, name, prov1, city1);
			}
			Collections.sort(pRList, new Comparator<Object>() {

				@Override
				public int compare(Object arg0, Object arg1) {
					return (int) (((PkgResult) arg0).getPrice() - ((PkgResult) arg1)
							.getPrice());
				}
			});
		} else if (type == UtilData.CUNICOM) {
			UMultiPkgInfo mPI = new UMultiPkgInfo();
			USinglePkgInfo sPI = new USinglePkgInfo();

			mPI.solve(call, msg, info);
			addPR(mPI.getPrice1(), mPI.getDesc1(), PlaceData.QUANGUO,
					PlaceData.QUANGUO);
			addPR(mPI.getPrice2(), mPI.getDesc2(), PlaceData.QUANGUO,
					PlaceData.QUANGUO);
			addPR(mPI.getPrice3(), mPI.getDesc3(), PlaceData.QUANGUO,
					PlaceData.QUANGUO);
			sPI.solve(call, msg, info);
			for (int iter = 0, jter = 0; jter < showSize
					&& iter < sPI.singlePkgTar.size(); iter++, jter++) {
				double price = sPI.getSinglePkgTar().get(iter).getPrice();
				String name = sPI.getSinglePkgTar().get(iter).getDescription();
				addPR(price, name, PlaceData.QUANGUO,
						PlaceData.QUANGUO);
			}
			Collections.sort(pRList, new Comparator<Object>() {

				@Override
				public int compare(Object arg0, Object arg1) {
					return (int) (((PkgResult) arg0).getPrice() - ((PkgResult) arg1)
							.getPrice());
				}
			});
		} else if (type == UtilData.CMCC) {
			
		} else {
			
		}
	}

	private void addPR(double price, String name, String prov, String city) {
		PkgResult pR = new PkgResult(price, name, prov, city);
		if (price != 0.0 && !pRList.contains(pR)) {
			pRList.add(pR);
		}
	}

	public ArrayList<PkgResult> getpRList() {
		return pRList;
	}

	public void setpRList(ArrayList<PkgResult> pRList) {
		this.pRList = pRList;
	}

	public static void main(String[] args) {
		SingleRequestBean sRB = new SingleRequestBean();
		sRB.solve(UtilData.CTELECOM, 500, 100, 300, "hl", "haerbin");
		for (PkgResult pR : sRB.pRList) {
			System.out.println(pR.toString());
		}
	}

}
