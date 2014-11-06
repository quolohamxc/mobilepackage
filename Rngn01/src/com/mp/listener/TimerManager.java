package com.mp.listener;

import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mp.model.PackageTariff;
import com.mp.model.SAEsqlForPT;
import com.mp.telecomzol.TControllerAbridged;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;

public class TimerManager implements ServletContextListener{
	//服务器用TimerTask常量
	private Timer timer;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		timer = new Timer(true);
		
		//init database
		SAEsqlForPT sFPT = new SAEsqlForPT();
		sFPT.initdb();
		
		//fetch packages
		//timer.schedule(new QueryTask(), UtilData.MILLIS_IN_WEEK, UtilData.MILLIS_IN_WEEK);
		TControllerAbridged tC = new TControllerAbridged();
		tC.fetchZol();
		@SuppressWarnings({ "unchecked" })
		HashMap<String, PackageTariff> tmp1 = (HashMap<String, PackageTariff>) tC.getpHashZol().clone();
		HashMap<String, PackageTariff> tmp2 = sFPT.getPT();
		if (!UtilMethod.compareMapByEntrySet(tmp1, tmp2)) {
			sFPT.initdb();
			sFPT.insertPT(tmp1);
		} else {
			sFPT.debug();
		}
	}

}

