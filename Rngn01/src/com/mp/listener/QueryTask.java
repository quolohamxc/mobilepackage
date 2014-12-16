package com.mp.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

import com.mp.model.PackageTariff;
import com.mp.model.SAEsqlForPT;
import com.mp.telecomzol.TControllerAbridged;
import com.mp.util.UtilMethod;

public class QueryTask extends TimerTask{

	@SuppressWarnings("unused")
	private static boolean isRunning = false;
	public static Date updateDate;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//System.out.println("success"); //change to log.info
		if(isRunning = false) {
			isRunning = true;
			// TODO something
			isRunning = false;
		} else {
			//fetch packages
			TControllerAbridged tC = new TControllerAbridged();
			tC.fetchZol();
			SAEsqlForPT sFPT = new SAEsqlForPT();
			@SuppressWarnings({ "unchecked" })
			HashMap<String, PackageTariff> tmp1 = (HashMap<String, PackageTariff>) tC.getpHashZol().clone();
			HashMap<String, PackageTariff> tmp2 = sFPT.getPT();
			if (!UtilMethod.compareMapByEntrySet(tmp1, tmp2)) {
				sFPT.initdb();
				sFPT.insertPT(tmp1);
			}
			updateDate = new Date();
		}
		//由于使用timertask，此处有一定延迟。因此初次打开JVM需要等待几秒钟再行访问。
		//System.out.println("success2");
	}
}
