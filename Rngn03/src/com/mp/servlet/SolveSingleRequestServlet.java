package com.mp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mp.bean.SingleRequestBean;
import com.mp.util.PlaceData;
import com.mp.util.UtilData;
import com.mp.util.UtilMethod;
import com.mp.util.placedata.city.HeiLongJiangFull;
import com.mp.util.placedata.province.ProvNameAbbr;

/**
 * FormSingleRequest.jspµÄºóÌ¨¡£
 * 
 * @author Administrator
 *
 */
public class SolveSingleRequestServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1774989838554544752L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html;charset=UTF-8");

		String action = request.getParameter("action");
		if ("SingleQuery".equals(action)) {
			String co = request.getParameter("carrieroperator");
			String call = request.getParameter("call");
			String msg = request.getParameter("msg");
			String info = request.getParameter("info");
			int provid = Integer.valueOf(request.getParameter("prov")) - 2;
			int cityid = Integer.valueOf(request.getParameter("city")) - 2;
			String prov = provid < 0 ? PlaceData.QUANGUO : ProvNameAbbr._placeList.get(provid);
			String city = cityid < 0 ? PlaceData.QUANGUO : HeiLongJiangFull._placeList.get(cityid);
			if(call != null && !"".equals(call) && UtilMethod.isNumeric(call)
					&& msg != null && !"".equals(msg) && UtilMethod.isNumeric(msg)
					&& info != null && !"".equals(info) && UtilMethod.isNumeric(info)) {
				SingleRequestBean sRB = new SingleRequestBean();
				if ("ctelecom".equals(co)) {
					sRB.solve(UtilData.CTELECOM, call, msg, info, prov, city);
					HttpSession session = request.getSession();
					session.setAttribute("sRB", sRB);
					gotoPage("/reqresult.jsp", request, response);
				} else if ("cunicom".equals(co)) {
					sRB.solve(UtilData.CUNICOM, call, msg, info);
					HttpSession session = request.getSession();
					session.setAttribute("sRB", sRB);
					gotoPage("/reqresult.jsp", request, response);
				} else if ("cmcc".equals(co)) {

				} else {
					gotoPage("/error.jsp", request, response);
				}
			} else {
				gotoPage("/error.jsp", request, response);
			}
		} else {
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
