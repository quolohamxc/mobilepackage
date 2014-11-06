package com.mp.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mp.model.mysql.MysqlForPT;
import com.mp.util.UtilData;

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
		MysqlForPT mFPT = new MysqlForPT();
		mFPT.initdb();
//		SAEsqlForPT sFPT = new SAEsqlForPT();
//		sFPT.initdb();
		
		//为了确保服务器初始化时能够正常访问和运行，因此需要保证数据读取完毕
		new InitServer().init();
		
		//fetch packages
		timer.schedule(new QueryTask(), UtilData.MILLIS_IN_WEEK, UtilData.MILLIS_IN_DAY);
		
	}

}
