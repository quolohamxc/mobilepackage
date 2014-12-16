package com.mp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mp.bean.SolveBean;
import com.mp.util.UtilMethod;

public class SolveRequestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5584780026882471300L;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//response.setContentType("text/html;charset=UTF-8");
		
		String action = request.getParameter("action");
		if("query".equals(action)) {
			String call = request.getParameter("call");
			String msg = request.getParameter("msg");
			String info = request.getParameter("info");
			SolveBean sol = new SolveBean();
			if(call != null && !"".equals(call) && UtilMethod.isNumeric(call)
					&& msg != null && !"".equals(msg) && UtilMethod.isNumeric(msg)
					&& info != null && !"".equals(info) && UtilMethod.isNumeric(info)) {
				sol.solve(call, msg, info);
				//System.out.println(sol.mTelecomRP);
				//System.out.println(sol.mTelecomRPT.getmTitle());
				HttpSession session = request.getSession();
				session.setAttribute("sol", sol);
				gotoPage("/result.jsp", request, response);
				//response.sendRedirect("result.jsp");
			}
			else {
				System.out.println("wrong input");
				gotoPage("/error.jsp", request, response);
				//response.sendRedirect("error.jsp");
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
