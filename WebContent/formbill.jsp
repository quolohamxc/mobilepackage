<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.io.*,java.util.*"%>
<%!String[] call = {"", "", "", "", ""};
	String[] msg = {"", "", "", "", ""};
	String[] info = {"", "", "", "", ""};
	int formNum = 0;%>

<%
	try {
		formNum = Integer.valueOf(request.getParameter("form_num"));
		for (int iter = 0; iter < formNum; iter++) {
			call[iter] = request.getParameter("call_" + iter);
			msg[iter] = request.getParameter("message_" + iter);
			info[iter] = request.getParameter("infoflow_" + iter);
		}

	} catch (NumberFormatException e) {
		formNum = 0;
	}
	if (formNum >= 5) {
		out.println("max form number is five");
	} else {
		formNum++;
	}
%>

<html>
<script type="text/javascript">
	function direct(value) {
		if (value == 1) {
			document.queryForm.action = "formbill.jsp";
		} else if (value == 2){
			document.queryForm.action = "error.jsp";
		} else {
			document.queryForm.action = "error.jsp";
		}
		document.queryForm.submit();
	}
</script>

<body>
	<div id="head">
		<img src="sources/logo.jpg"
			style="display: inline; width: 65px; height: 50px;">
		最省钱的手机套餐
		<br />
		<p>尊敬的电信用户：</p>
		<p>&emsp;您好，欢迎使用手机套餐推荐，请输入账单数据：</p>
	</div>
	<div id=bill>
		<form name="queryForm" method="GET">
			<input type="hidden" name="action" value="query">
			<input type="hidden" name="form_num" value="<%=formNum%>">
			<%
				for (int iter = formNum - 1; iter >= 0; iter--) {
					out.println("<div>");
					out.println("<hr/>");
					//out.println("<p>call:</p>");
					out.println("通话时长/月 :");
					out.println("<input type=\"text\" name=\"call_" + iter
							+ "\" value=\"" + call[iter] + "\" /> 分钟 <br />");
					//out.println("<p>message:</p>");
					out.println("短信数量/月 :");
					out.println("<input type=\"text\" name=\"message_" + iter
							+ "\" value=\"" + msg[iter] + "\" /> 条 <br />");
					//out.println("<p>info:</p>");
					out.println("上网流量/月 :");
					out.println("<input type=\"text\" name=\"infoflow_" + iter
							+ "\" value=\"" + info[iter] + "\" /> MB <br />");
					out.println("</div>");
				}
			%>
			<input type="button" name="submit1" onclick="direct(1)"
				value="添加更多表单"><br> <input type="button" name="submit2"
				onclick="direct(2)" value="提交"><br>
		</form>
	</div>
</body>
</html>