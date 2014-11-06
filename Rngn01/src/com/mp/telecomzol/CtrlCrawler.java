package com.mp.telecomzol;

import ws.crawler.BreadthCrawler;
import ws.model.Page;
import ws.util.RandomUtils;

import com.mp.telecomzol.TController.CtrlFileSystemOutput;

public class CtrlCrawler extends BreadthCrawler {
	public CtrlCrawler() {
		super.taskname = RandomUtils.getTimeString();
	}

	@Override
	public void visit(Page page) {
		CtrlFileSystemOutput cfsoutput = new CtrlFileSystemOutput(root);
		// Log.Infos("visit",this.taskname,page.url);
		cfsoutput.output(page);
	}
}