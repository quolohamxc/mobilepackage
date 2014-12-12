package com.mp.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mp.util.UtilData;

public class TimerManager implements ServletContextListener {
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

		// init database
		// MysqlForPT mFPT = new MysqlForPT();
		// mFPT.initdb();
		// SAEsqlForPT sFPT = new SAEsqlForPT();
		// sFPT.initdb();

		// Ϊ��ȷ����������ʼ��ʱ�ܹ��������ʺ����У������Ҫ��֤���ݶ�ȡ���
		new InitServer().init();
		// ���ö�ʱ����
		timer.schedule(new QueryTask(), UtilData.MILLIS_IN_WEEK,
				UtilData.MILLIS_IN_WEEK);

	}

}
