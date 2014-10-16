package com.mp.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TimerManager implements ServletContextListener{
	//服务器用TimerTask常量
	private static final int MILLIS_IN_DAY = 86400000;
	private static final int MILLIS_IN_WEEK = MILLIS_IN_DAY * 7;
	private static final int DEFAULT_DELAY = 0;
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
		timer.schedule(new QueryTask(), DEFAULT_DELAY, MILLIS_IN_WEEK);
	}

}
