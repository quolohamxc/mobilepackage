<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.mp.model.PkgResult"%>
<jsp:useBean id="sRB" scope="session"
	class="com.mp.bean.SingleRequestBean" />
<jsp:useBean id="mBB" scope="session"
	class="com.mp.bean.MultiBillBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
#result {
	font-family: UTF-8;
	width: 100%;
	border-collapse: collapse;
}

#result table {
	width: 100%;
}

#result td, #result th {
	font-size: 1em;
	border: 1px solid #B9D3EE;
	padding: 3px 7px 2px 7px;
}

#result th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #ADD8E6;
	color: #ffffff;
}

#result tr.alt td {
	color: #000000;
	background-color: #E0FFFF;
}
</style>
</head>
<body>
	<p>经过计算，最优的备选手机套餐如下：</p>
	<div id="result">
		<table>
			<tr>
				<th>估计价格</th>
				<th>套餐名称</th>
				<th>所在省份</th>
				<th>所在市</th>
			</tr>
			<%
				int count = 0;
					for (PkgResult pR : sRB.getpRList()) {
						//out.println(pR.toString());
						if((count++%2) == 1) {
							out.println("<tr class=\"alt\">");
						} else {
							out.println("<tr>");
						}
			%>
			<td><%=pR.getPrice()%></td>
			<td><%=pR.getName()%></td>
			<td><%=pR.getProvChn()%></td>
			<td><%=pR.getCityChn()%></td>
			<%
				out.println("</tr>");
					}
			%>
		</table>
	</div>
	<div id="app">
		<%
			if(mBB.isCallDrunked()) {
				out.println("<p>您的语音服务使用情况波动较大，建议根据情况附加语音可选包。</p>");
			}
		%>
		<%
			if(mBB.isMsgDrunked()) {
				out.println("<p>您的短信服务使用情况波动较大，建议根据情况附加短信可选包。</p>");
			}
		%>
		<%
			if(mBB.isInfoDrunked()) {
				out.println("<p>您的流量服务使用情况波动较大，建议根据情况附加流量可选包。</p>");
			}
		%>
	</div>
	<p>注：结果中已经包含了部分附加套餐。查看附加套餐列表，请<a href="applist.jsp">点击这里</a></p>
</body>
</html>
