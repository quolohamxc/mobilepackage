<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page
	import="java.io.*,java.util.*,com.mp.model.*,com.mp.telecom.model.*,com.mp.telecom.*,com.mp.util.placedata.city.*,com.mp.util.placedata.province.*"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<meta name="Description" content="too tiring." />
<meta name="Keywords" content="best mobile packages" />
<meta name="Copyright" content="floion zeppit" />
<meta name="Designed By" content="floion zeppit" />
<meta name="Language" content="English, Chinese" />
<title>Insert title here</title>
<style type="text/css" title="layout" media="screen">
@import url("style.css");
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
		<h1>请估算需求后输入</h1>
		<form method="post" action="SolveSingleRequest">
				<input type="radio" checked="checked" name="carrieroperator"
				value="ctelecom" onClick="showHide(1)"/> 中国电信 <br/>
				<div id="ctelecom">
		<p>
			请选择所在地： <select name="prov">
						<option value="1"></option>
						<%
							int zter = 2;
							for (String str : ProvNameChn._placeList) {
								if(str == ProvNameChn.HEILONGJIANG) {
									out.println("<option value=\"" + (zter++) + "\">" + str + "</option>");
								} else {
									out.println("<option value=\"" + (zter++) + "\" disabled=\"disabled\">" + str + "</option>");
								}
							}
						%>
					</select>
					<select name="city">
						<option value="1"></option>
						<%
							zter = 2;
							for (String str : HeiLongJiangChn._placeList) {
								out.println("<option value=\"" + (zter++) + "\">" + str + "</option>");
							}
						%>
					</select>
				</p>
		<input type="hidden" name="action" value="SingleQuery" />
		<hr/>
			&emsp;通话时长/月
			<input type="text" name="call" maxlength="5"/>分钟<br/>
			&emsp;短信数量/月
			<input type="text" name="msg" maxlength="5">条<br/>
			&emsp;上网流量/月
			<input type="text" name="info" maxlength="5"/>MB<br/> <input
				type="reset" value="重填"/> <input type="submit" value="提交"/>
		</div>
		</form>
</body>
</html>