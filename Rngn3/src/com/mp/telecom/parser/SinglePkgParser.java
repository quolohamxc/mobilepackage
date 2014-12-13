package com.mp.telecom.parser;

import com.mp.telecom.SinglePkgInfo;

public class SinglePkgParser {
	public void parse() throws ClassNotFoundException, NullPointerException {
		int oldSize = SinglePkgInfo.singlePkgList.size(), newSize;

		new SinglePkgParserA().parse();
		newSize = SinglePkgInfo.singlePkgList.size();
		if (oldSize == newSize) {
			new SinglePkgInfo().addDefaultPkg(SinglePkgInfo.LEXIANG);
		}
		oldSize = SinglePkgInfo.singlePkgList.size();

		new SinglePkgParserB().parse();
		newSize = SinglePkgInfo.singlePkgList.size();
		if (oldSize == newSize) {
			new SinglePkgInfo().addDefaultPkg(SinglePkgInfo.YITONG);
		}
		oldSize = SinglePkgInfo.singlePkgList.size();
		
		new SinglePkgParserC().parse();
		newSize = SinglePkgInfo.singlePkgList.size();
		if (oldSize == newSize) {
			new SinglePkgInfo().addDefaultPkg(SinglePkgInfo.FEIYOUNG);
		}
		oldSize = SinglePkgInfo.singlePkgList.size();
	}
}
