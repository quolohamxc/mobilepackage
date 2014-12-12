package com.mp.telecom;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.mp.model.PackageTariff;
import com.mp.telecom.model.SinglePkgTar;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class SinglePkgInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4286275381724430047L;
	private int mType;
	private static double mDisCount;
	// public ArrayList<String> singleCrawlerUrl = null;
	public static final String CALL = "call";
	public static final String MSG = "msg";
	public static final String INFO = "info";
	public static final String LEXIANG = "lexiang";
	public static final String YITONG = "yitong";
	public static final String FEIYOUNG = "feiyoung";
	public ArrayList<SinglePkgTar> singlePkgTar = null;
	public static ArrayList<PackageTariff> singlePkgList = null;

	public static ArrayList<PackageTariff> getSinglePkgList() {
		if (singlePkgList.size() == 0) {
			new SinglePkgInfo().addDefaultPkg();
		}
		Collections.sort(singlePkgList, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {

				return ((PackageTariff) arg0).getmTitle().compareTo(
						((PackageTariff) arg1).getmTitle());
			}
		});
		return singlePkgList;
	}

	public static void setSinglePkgList(ArrayList<PackageTariff> singlePkgList) {
		SinglePkgInfo.singlePkgList = singlePkgList;
	}

	static {
		singlePkgList = new ArrayList<PackageTariff>();
	}

	public SinglePkgInfo() {
		this.setmType(UtilData.CTELECOM);
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public SinglePkgInfo(int mType) {
		if (mType == UtilData.CMCC || mType == UtilData.CTELECOM
				|| mType == UtilData.CUNICOM) {
			this.setmType(mType);
		} else {
			this.setmType(UtilData.CTELECOM);
		}
		if (mDisCount == 0.0) {
			this.setDefaultDiscount();
		}
	}

	public void addSinglePkg(PackageTariff pT) {
		if (!singlePkgList.contains(pT)) {
			singlePkgList.add(pT);
		}
	}

	public double solve(double call, double msg, double info) {
		if (singlePkgList == null || singlePkgList.size() == 0) {
			addDefaultPkg();
		}
		singlePkgTar = new ArrayList<SinglePkgTar>();
		for (PackageTariff pT : singlePkgList) {
			SinglePkgTar sPT = new SinglePkgTar(pT);
			if ((sPT.solve(call, msg, info) || sPT.solveApp(call, msg, info))
					&& sPT.getPrice() != UtilData.INVALID
					&& sPT.getPrice() != 0.0 && !singlePkgTar.contains(sPT)) {
				singlePkgTar.add(sPT);
			}

		}
		Collections.sort(singlePkgTar, new Comparator<Object>() {
			@Override
			public int compare(Object arg0, Object arg1) {
				return (int) (((SinglePkgTar) arg0).getPrice() - ((SinglePkgTar) arg1)
						.getPrice());
			}
		});
		double rs = 0.0;
		if (singlePkgTar.size() != 0) {
			rs = singlePkgTar.get(0).getPrice();
		}
		if (mDisCount > 0 && mDisCount <= 1) {
			rs *= mDisCount;
		}
		return Double.valueOf(new DecimalFormat("#.00").format(rs));
	}

	private void addDefaultPkg() {
		addDefaultPkg("");
	}

	// parser 可以以String调用
	public void addDefaultPkg(String cate) {
		this.setDefaultDiscount();
		// add lexiang乐享
		if (cate == "" || cate == LEXIANG) {
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版49元合约", 49.0, 9989.0,
					100, 200.0, 30, 6, 30, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐59元合约", 59.0, 9989.0,
					100, 500.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版59元合约", 59.0, 9989.0,
					160, 60.0, 0, 0, 10, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版69元合约", 69.0, 9989.0,
					150, 300.0, 30, 6, 30, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐79元合约", 79.0, 9989.0,
					200, 700.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版89元合约", 89.0, 9989.0,
					240, 400.0, 30, 6, 30, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版89元合约", 89.0, 9989.0,
					360, 120.0, 0, 0, 30, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐99元合约", 99.0, 9989.0,
					300, 1000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3, "", "",
					true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版129元合约", 129.0,
					9989.0, 330, 600.0, 60, 12, 60, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐129元合约", 129.0,
					9989.0, 500, 1000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版129元合约", 129.0,
					9989.0, 660, 120.0, 0, 0, 30, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版159元合约", 159.0,
					9989.0, 450, 750.0, 60, 12, 60, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版159元合约", 159.0,
					9989.0, 900, 120.0, 0, 0, 30, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐169元合约", 169.0,
					9989.0, 700, 2000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版189元合约", 189.0,
					9989.0, 600, 1000.0, 60, 12, 60, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版189元合约", 189.0,
					9989.0, 1200, 120.0, 0, 0, 30, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐199元合约", 199.0,
					9989.0, 700, 3000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版229元合约", 229.0,
					9989.0, 800, 1200.0, 60, 12, 60, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版289元合约", 289.0,
					9989.0, 990, 1500.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐299元合约", 299.0,
					9989.0, 1500, 4000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版329元合约", 329.0,
					9989.0, 1100, 1700.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版389元合约", 389.0,
					9989.0, 1290, 2000.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G聊天版389元合约", 389.0,
					9989.0, 2600, 120.0, 0, 0, 30, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐399元合约", 399.0,
					9989.0, 2000, 6000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版489元合约", 489.0,
					9989.0, 1590, 3000.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版589元合约", 589.0,
					9989.0, 2000, 4000.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐599元合约", 599.0,
					9989.0, 3000, 11000.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享3G上网版889元合约", 889.0,
					9989.0, 3000, 5000.0, 180, 18, 120, -1.0, -1.0, 0.15, -1.0,
					0.3, "", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "乐享4G套餐999元合约", 999.0,
					9989.0, 9999, 9900.0, 0, 0, 0, -1.0, -1.0, 0.15, -1.0, 0.3,
					"", "", true, "", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0));
		}
		if (cate == "" || cate == YITONG) {
			addSinglePkg(new PackageTariff(10000, "天翼易通卡城市版55元", 55.0, -60.0,
					-1, 820.0, -1, -1, -1, 0.1, -1.0, -1.0, -1.0, 0.2, "", "",
					false, "hl", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "天翼易通卡城市版25元", 25.0, -30.0,
					-1, 170.0, -1, -1, -1, 0.1, -1.0, -1.0, -1.0, 0.2, "", "",
					false, "hl", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "天翼易通卡城市版35元", 35.0, -40.0,
					-1, 320.0, -1, -1, -1, 0.1, -1.0, -1.0, -1.0, 0.2, "", "",
					false, "hl", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "天翼易通卡农村版5元", 5.0, 9989.0,
					-1, 20.0, -1, -1, -1, 0.1, -1.0, -1.0, -1.0, 0.2, "", "",
					false, "hl", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
			addSinglePkg(new PackageTariff(10000, "天翼易通卡城市版5元", 5.0, -10.0, -1,
					20.0, -1, -1, -1, 0.15, -1.0, -1.0, -1.0, 0.2, "", "",
					false, "hl", "null", false, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

		}
		if (cate == "" || cate == FEIYOUNG) {
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（哈尔滨）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"haerbin", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（齐齐哈尔）", 19.0,
					9989.0, 80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"qiqihaer", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（牡丹江）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"mudanjiang", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7,
					0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（佳木斯）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"jiamusi", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（绥化）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"suihua", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（黑河）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"heihe", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（大兴安岭）", 19.0,
					9989.0, 80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"daxinganling", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7,
					0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（伊春）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"yichun", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（大庆）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"daqing", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（七台河）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"qitaihe", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（鸡西）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"jixi", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（双鸭山）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"shuangyashan", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7,
					0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐19元（鹤岗）", 19.0, 9989.0,
					80, 20.0, 80, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"hegang", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（哈尔滨）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"haerbin", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（齐齐哈尔）", 39.0,
					9989.0, 240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0,
					-1.0, "，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false,
					"hl", "qiqihaer", true, 20.0, 0.0, 200.0, 0.15,
					9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（牡丹江）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"mudanjiang", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7,
					0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（佳木斯）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"jiamusi", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（绥化）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"suihua", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（黑河）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"heihe", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（大兴安岭）", 39.0,
					9989.0, 240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0,
					-1.0, "，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false,
					"hl", "daxinganling", true, 20.0, 0.0, 200.0, 0.15,
					9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（伊春）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"yichun", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（大庆）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"daqing", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（七台河）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"qitaihe", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（鸡西）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"jixi", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（双鸭山）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"shuangyashan", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7,
					0.06));
			addSinglePkg(new PackageTariff(10000, "飞Young套餐39元（鹤岗）", 39.0, 9989.0,
					240, 20.0, 240, -1, -1, 0.1, -1.0, -1.0, -1.0, -1.0,
					"，国内点对点彩信0.5元/条。流量不足1M按1M计，其他执行标准资费。国内上网", "", false, "hl",
					"hegang", true, 20.0, 0.0, 200.0, 0.15, 9.9999891E7, 0.06));
		}
	}

	private void setDefaultDiscount() {
		setmDisCount(1.0);
	}

	public static double getmDisCount() {
		return mDisCount;
	}

	public static void setmDisCount(double mDisCount) {
		SinglePkgInfo.mDisCount = mDisCount;
	}

	public int getmType() {
		return mType;
	}

	public void setmType(int mType) {
		this.mType = mType;
	}

	public static String getShowName(int cell, String type) {
		int zter = 0;
		for (PackageTariff pT : SinglePkgInfo.getSinglePkgList()) {
			String s = pT.getmTitle();
			if (type == SinglePkgInfo.YITONG && s.contains("易通")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else if (type == SinglePkgInfo.FEIYOUNG && s.contains("飞Young")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else if (type == SinglePkgInfo.LEXIANG && s.contains("乐享")) {
				if (zter++ == cell) {
					return s;
				} else {
					continue;
				}
			} else {
				// TODO for other packages
			}
		}
		return null;
	}

	public static double[] queryFee(String single, double callAve, double msgAve, double infoAve) {
		double[] rs = {0,0,0,0};
		// use default data
		double callBey = 0.2, msgBey = 0.1, infoBey = 0.3;
		for (PackageTariff pT : SinglePkgInfo.getSinglePkgList()) {
			if(pT.getmTitle() != single) {
				continue;
			} else {
				double temp;
				if(callAve > 0) {
					temp = pT.getmMonthlyCons();
					if(temp != UtilData.INVALID && temp != 0) {
						rs[0] = Math.abs(temp);
					} else {
						rs[0] = pT.getmMonthlyFee();
					}
					temp = pT.getmDomCall();
					if(temp != UtilData.UNUSED && temp != 0) {
						rs[1] += callAve / temp;
					} else {
						temp = pT.getmDomCallBey();
						if(temp != UtilData.UNUSED && temp != 0) {
							rs[1] += callAve / temp;
						} else {
							rs[1] += callAve / callBey;
						}
					}
					temp = pT.getmDomCallDur();
					if(temp != UtilData.UNUSED && temp != 0) {
						rs[1] += temp;
					}
				}
				if(msgAve > 0) {
					rs[2] += msgAve / msgBey;
					temp = pT.getmDomMsg();
					if(temp != UtilData.UNUSED && temp != 0) {
						rs[2] += temp;
					}
				}
				if(infoAve > 0) {
					if(single.contains("飞Young")) {
						double low = 0, high = UtilData.MAXLIMIT, mid = (low + high) / 2;
						while(low < high - 1) {
							mid = (low + high) / 2;
							if(pT.solveStep(mid) > infoAve) {
								high = mid;
							} else {
								low = mid;
							}
						}
						rs[3] += mid;
					} else {
						temp = pT.getmDomInfoBey();
						if(temp != UtilData.UNUSED && temp != 0) {
							rs[3] += infoAve / temp;
						} else {
							rs[3] += infoAve / infoBey;
						}
					}
					temp = pT.getmDomInfoFlow();
					if(temp != UtilData.UNUSED && temp != 0) {
						rs[3] += temp;
					}
				}
				break;
			}
		}
		for(int iter = 0; iter < rs.length; iter++) {
			rs[iter] = UtilMethod.formatMoney(rs[iter]);
		}
		return rs;
	}

	public static void main(String[] args) {
		SinglePkgInfo sPI = new SinglePkgInfo();
		System.out.println(sPI.solve(100, 100, 100));
		for (SinglePkgTar sPT : sPI.singlePkgTar) {
			System.out.println(sPT.getPrice() + " " + sPT.getpT().toString());
		}
	}

}
