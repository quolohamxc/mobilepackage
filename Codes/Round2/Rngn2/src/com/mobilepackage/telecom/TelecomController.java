package com.mobilepackage.telecom;

import java.io.File;
import java.net.URL;

import ws.crawler.BreadthCrawler;
import ws.model.Page;
import ws.output.FileSystemOutput;
import ws.util.FileUtils;
import ws.util.RandomUtils;
//import com.mobilepackage.telecom.TelecomPackage;

/**
 * 
 * @author Administrator
 *
 */
public class TelecomController {

	public static class CtrlCrawler extends BreadthCrawler {
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

	public static class CtrlFileSystemOutput extends FileSystemOutput {

		public CtrlFileSystemOutput(String root) {
			super(root);
		}

		@Override
		public void output(Page page) {
			
			try {
				URL _URL = new URL(page.url);
				String query = "";
				if (_URL.getQuery() != null) {
					query = "_" + _URL.getQuery();
				}
				String path = _URL.getPath();
				if (path.length() == 0) {
					path = "index.html";
				} else {
					if (path.charAt(path.length() - 1) == '/') {
						path = path + "index.html";
					} else {

						for (int i = path.length() - 1; i >= 0; i--) {
							if (path.charAt(i) == '/') {
								if (!path.substring(i + 1).contains(".")) {
									path = path + ".html";
								}
							}
						}
					}
				}
				path += query;
				File domain_path = new File(root, _URL.getHost());
				File f = new File(domain_path, path);
				// Log.Infos("output",null, f.getAbsolutePath());
				//FileUtils.writeFileWithParent(f, page.content);
				TelecomPackage telecom = new TelecomPackage () ;
				telecom.analyzeZol(page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

//	public static class CtrlFileUtils extends FileUtils {
//
//	}

}
