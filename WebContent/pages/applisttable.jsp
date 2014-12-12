<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.*,com.mp.model.PkgResult, com.mp.telecom.AppPkgInfo, com.mp.telecom.model.AppPkgFuel"%>
<%!
	int count;
%>
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
<script type="text/javascript">
	function showHide(value) {
		if(value == 1) {
			document.getElementById('ctelecom').style.display = "";
		}
	}
</script>
</head>
<body>
	<div id="result">
	<input type="radio" checked="checked" name="carrieroperator"
				value="ctelecom" onClick="showHide(1)"/> 中国电信 <br/>
	<div id="telecom">
		<p>语音可选包（电信）</p>
		<table>
			<tr>
				<th>价格</th>
				<th>套餐名称</th>
				<th>包含语音</th>
			</tr>
			<%
				count = 0;
					for (AppPkgFuel aPF : AppPkgInfo.getAppPkgList()) {
						if(aPF.getmType() != AppPkgInfo.CALL) {
							continue;
						}
						//out.println(pR.toString());
						if((count++%2) == 1) {
							out.println("<tr class=\"alt\">");
						} else {
							out.println("<tr>");
						}
			%>
			<td><%=aPF.getFee()%></td>
			<td><%=aPF.getDescription()%></td>
			<td><%=aPF.getInvolve()%>分钟</td>
			<%
				out.println("</tr>");
					}
			%>
		</table>
		<p>短信可选包（电信）</p>
		<table>
			<tr>
				<th>价格</th>
				<th>套餐名称</th>
				<th>包含短信</th>
			</tr>
			<%
				count = 0;
					for (AppPkgFuel aPF : AppPkgInfo.getAppPkgList()) {
						if(aPF.getmType() != AppPkgInfo.MSG) {
							continue;
						}
						//out.println(pR.toString());
						if((count++%2) == 1) {
							out.println("<tr class=\"alt\">");
						} else {
							out.println("<tr>");
						}
			%>
			<td><%=aPF.getFee()%></td>
			<td><%=aPF.getDescription()%></td>
			<td><%=aPF.getInvolve()%>条</td>
			<%
				out.println("</tr>");
					}
			%>
		</table>
		<p>流量可选包（电信）</p>
		<table>
			<tr>
				<th>价格</th>
				<th>套餐名称</th>
				<th>包含流量</th>
			</tr>
			<%
				count = 0;
					for (AppPkgFuel aPF : AppPkgInfo.getAppPkgList()) {
						if(aPF.getmType() != AppPkgInfo.INFO) {
							continue;
						}
						//out.println(pR.toString());
						if((count++%2) == 1) {
							out.println("<tr class=\"alt\">");
						} else {
							out.println("<tr>");
						}
			%>
			<td><%=aPF.getFee()%></td>
			<td><%=aPF.getDescription()%></td>
			<td><%=aPF.getInvolve()%>MB</td>
			<%
				out.println("</tr>");
					}
			%>
		</table>
	</div>
	</div>
</body>
</html>
