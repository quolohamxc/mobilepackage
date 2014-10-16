package com.mp.listener;

import java.util.TimerTask;

public class QueryTask extends TimerTask{

	@SuppressWarnings("unused")
	private static boolean isRunning = false;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(isRunning = false) {
			isRunning = true;
			// TODO something
			isRunning = false;
		} else {
			
		}
	}

}
