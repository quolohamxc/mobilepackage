package com.mp.listener;

import java.util.Date;
import java.util.TimerTask;

public class QueryTask extends TimerTask {

	@SuppressWarnings("unused")
	private static boolean isRunning = false;
	public static Date updateDate;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("success"); //change to log.info
		if (isRunning = false) {
			isRunning = true;
			// TODO something
			isRunning = false;
		} else {
			new CheckUpdate().checkAndCallUpdate();
			updateDate = new Date();
		}
		// 由于使用timertask，此处有一定延迟。因此初次打开JVM需要等待几秒钟再行访问。
		// System.out.println("success2");
	}
}
