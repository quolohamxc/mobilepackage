package com.mp.listener;

import java.util.HashMap;

import com.mp.model.PackageTariff;
import com.mp.model.mysql.MysqlForPT;
import com.mp.telecomzol.TControllerAbridged;
import com.mp.util.UtilMethod;

public class InitServer {
	public void init() {
		initZol();
	}
	
	private void initZol () {
		//fetch packages
		TControllerAbridged tC = new TControllerAbridged();
		tC.fetchZol();
		// TODO Auto-generated catch block
		MysqlForPT mFPT = new MysqlForPT();
//		SAEsqlForPT sFPT = new SAEsqlForPT();
		@SuppressWarnings({ "unchecked" })
		HashMap<String, PackageTariff> tmp1 = (HashMap<String, PackageTariff>) tC.getpHashZol().clone();
		HashMap<String, PackageTariff> tmp2 = mFPT.getPT();
		if (!UtilMethod.compareMapByEntrySet(tmp1, tmp2)) {
			mFPT.initdb();
			mFPT.insertPT(tmp1);
		}
	}
}
