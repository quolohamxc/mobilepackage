package com.mp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mp.bean.MultiBillBean;
import com.mp.telecom.MultiPkgInfo;
import com.mp.telecom.SinglePkgInfo;
import com.mp.unicom.UMultiPkgInfo;
import com.mp.unicom.USinglePkgInfo;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;
import com.mp.util.placedata.city.HeiLongJiangFull;
import com.mp.util.placedata.province.ProvNameAbbr;

public class SolveBillRequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1616808175208439798L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
		if ("MultiQuery".equals(action)) {
			String[] fee = { "", "", "", "", "" };
			String[] call = { "", "", "", "", "" };
			String[] msg = { "", "", "", "", "" };
			String[] info = { "", "", "", "", "" };
			int formNum = 0, provid = 0, cityid = 0, pkgcate = 0, cellba = 0, cellbb = 0, cellbc = 0, cellca = 0, cellda = 0, cellea = 0;
			try {
				formNum = Integer.valueOf(request.getParameter("form_num"));
				for (int iter = 0; iter < formNum; iter++) {
					fee[iter] = request.getParameter("fee_" + iter);
					call[iter] = request.getParameter("call_" + iter);
					msg[iter] = request.getParameter("msg_" + iter);
					info[iter] = request.getParameter("info_" + iter);
				}
				String co = request.getParameter("carrieroperator");
				if ("ctelecom".equals(co)) {
					provid = Integer.valueOf(request.getParameter("prov")) - 2;
					cityid = Integer.valueOf(request.getParameter("city")) - 2;
					pkgcate = Integer.valueOf(request.getParameter("pkg_cate"));
					cellba = Integer.valueOf(request.getParameter("cell-2-1")) - 2;
					cellbb = Integer.valueOf(request.getParameter("cell-2-2")) - 2;
					cellbc = Integer.valueOf(request.getParameter("cell-2-3")) - 2;
					cellca = Integer.valueOf(request.getParameter("cell-3-1")) - 2;
					cellda = Integer.valueOf(request.getParameter("cell-4-1")) - 2;
					cellea = Integer.valueOf(request.getParameter("cell-5-1")) - 2;
					String prov = provid < 0 ? PlaceData.QUANGUO
							: ProvNameAbbr._placeList.get(provid);
					String city = cityid < 0 ? PlaceData.QUANGUO
							: HeiLongJiangFull._placeList.get(cityid);
					String jugglea = cellba < 0 ? "" : MultiPkgInfo
							.getShowName(cellba);
					String juggleb = cellbb < 0 ? "" : MultiPkgInfo
							.getShowName(cellbb);
					String jugglec = cellbc < 0 ? "" : MultiPkgInfo
							.getShowName(cellbc);
					String singlea = cellca < 0 ? "" : SinglePkgInfo
							.getShowName(cellca, SinglePkgInfo.YITONG);
					String singleb = cellda < 0 ? "" : SinglePkgInfo
							.getShowName(cellda, SinglePkgInfo.FEIYOUNG);
					String singlec = cellea < 0 ? "" : SinglePkgInfo
							.getShowName(cellea, SinglePkgInfo.LEXIANG);
					MultiBillBean mBB = new MultiBillBean(fee, call, msg, info,
							formNum);
					mBB.solve(UtilData.CTELECOM, prov, city, pkgcate, jugglea,
							juggleb, jugglec, singlea, singleb, singlec);
					if (mBB.isHasError() == false) {
						HttpSession session = request.getSession();
						session.setAttribute("sRB", mBB.getsRB());
						// TODO 添加加油信息
						session.setAttribute("mBB", mBB);
						gotoPage("/bilresult.jsp", request, response);
					} else {
						// System.out.println(1);
						gotoPage("/error.jsp", request, response);
					}
				} else if ("cunicom".equals(co)) {
					// TODO 修正showname函数，修正multiBillBean
					MultiBillBean mBB = new MultiBillBean(fee, call, msg, info,
							formNum);
					pkgcate = Integer
							.valueOf(request.getParameter("pkg_ucate"));
					String pkgcell = "", pkgcell2 = "", pkgcell3 = "";
					int temp = 0, temp2 = 0, temp3 = 0;
					switch (pkgcate) {
					case 2:
						temp = Integer
								.valueOf(request.getParameter("cell-b-1")) - 2;
						pkgcell = temp < 0 ? "" : USinglePkgInfo.getShowName(
								temp, USinglePkgInfo.THREEG);
						mBB.solveSingle(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell);
						break;
					case 3:
						temp = Integer
								.valueOf(request.getParameter("cell-c-1")) - 2;
						pkgcell = temp < 0 ? "" : USinglePkgInfo.getShowName(
								temp, USinglePkgInfo.FOURG);
						mBB.solveSingle(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell);
						break;
					case 4:
						temp = Integer
								.valueOf(request.getParameter("cell-d-1")) - 2;
						pkgcell = temp < 0 ? "" : USinglePkgInfo.getShowName(
								temp, USinglePkgInfo.THREEGY);
						mBB.solveSingle(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell);
						break;
					case 5:
						temp = Integer
								.valueOf(request.getParameter("cell-e-1")) - 2;
						temp2 = Integer.valueOf(request
								.getParameter("cell-e-2")) - 2;
						temp3 = Integer.valueOf(request
								.getParameter("cell-e-3")) - 2;
						pkgcell = temp < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.QGZH, UMultiPkgInfo.CALL);
						pkgcell2 = temp2 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.QGZH, UMultiPkgInfo.MSG);
						pkgcell3 = temp3 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.QGZH, UMultiPkgInfo.INFO);
						mBB.solveMulti(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell, pkgcell2, pkgcell3);
						break;
					case 6:
						temp = Integer
								.valueOf(request.getParameter("cell-e-1")) - 2;
						temp2 = Integer.valueOf(request
								.getParameter("cell-e-2")) - 2;
						temp3 = Integer.valueOf(request
								.getParameter("cell-e-3")) - 2;
						pkgcell = temp < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.GXZH, UMultiPkgInfo.CALL);
						pkgcell2 = temp2 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.GXZH, UMultiPkgInfo.MSG);
						pkgcell3 = temp3 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.GXZH, UMultiPkgInfo.INFO);
						mBB.solveMulti(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell, pkgcell2, pkgcell3);
						break;
					case 7:
						temp = Integer
								.valueOf(request.getParameter("cell-e-1")) - 2;
						temp2 = Integer.valueOf(request
								.getParameter("cell-e-2")) - 2;
						temp3 = Integer.valueOf(request
								.getParameter("cell-e-3")) - 2;
						pkgcell = temp < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.BDZH, UMultiPkgInfo.CALL);
						pkgcell2 = temp2 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.BDZH, UMultiPkgInfo.MSG);
						pkgcell3 = temp3 < 0 ? "" : UMultiPkgInfo.getShowName(
								temp, UMultiPkgInfo.BDZH, UMultiPkgInfo.INFO);
						mBB.solveMulti(UtilData.CUNICOM, PlaceData.QUANGUO,
								PlaceData.QUANGUO, pkgcell, pkgcell2, pkgcell3);
						break;
					default:
						mBB.setHasError(true);
						break;
					}
					if (mBB.isHasError() == false) {
						HttpSession session = request.getSession();
						session.setAttribute("sRB", mBB.getsRB());
						// TODO 添加加油信息
						session.setAttribute("mBB", mBB);
						gotoPage("/bilresult.jsp", request, response);
					} else {
						// System.out.println(1);
						gotoPage("/error.jsp", request, response);
					}
				} else if ("cmcc".equals(co)) {

				} else {
					// System.out.println(2);
					gotoPage("/error.jsp", request, response);
				}
			} catch (NumberFormatException e) {
				// System.out.println(3);
				gotoPage("/error.jsp", request, response);
			}
		} else {
			// System.out.println(4);
			gotoPage("/error.jsp", request, response);
		}
	}

	private void gotoPage(String targetURL, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(targetURL);
		rd.forward(request, response);
	}
}
