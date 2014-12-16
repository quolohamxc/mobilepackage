package com.mp.listener;

import com.mp.telecom.parser.AppPkgParser;
import com.mp.telecom.parser.MultiPkgParser;
import com.mp.telecom.parser.NonPkgParser;
import com.mp.telecom.parser.SinglePkgParser;
import com.mp.unicom.parser.UPkgParser;

public class InitServer {
	public void init() {
//		initZol();
	}
	
	public void parse() {
		new AppPkgParser().parse();
		new MultiPkgParser().parse();
		new NonPkgParser().parse();
		try {
			new SinglePkgParser().parse();
			new UPkgParser().parse();
		} catch (ClassNotFoundException e) {
			return;
		} catch (NullPointerException e) {
			return;
		}
	}

}
