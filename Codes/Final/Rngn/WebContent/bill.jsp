<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
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
</head>
<body>
	<div id="container">
		<div id="top">
			<div id="head">
				<h1>Insert Title Here</h1>
				<h2>--为您寻找最合适的手机套餐--</h2>
			</div>
		</div>
		<div id="header">
			<div id="navcontainer">
				<ul id="menu">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="request.jsp" id="current">使用服务</a></li>
					<li><a href="help.jsp">帮助</a></li>
					<li><a href="aboutus.jsp">关于我们</a></li>
				</ul>
			</div>
		</div>
		<div id="leftnav">
			<h1>服务列表</h1>
			<ul>
				<li><a href="request.jsp" title="通过提出需求使用功能">提出需求</a></li>
				<li><a href="bill.jsp" title="通过录入账单使用功能">->录入账单</a></li>
				<li><a href="applist.jsp" title="查看可选包">查看附加套餐</a></li>
			</ul>
			<h1>小贴士</h1>
			<p>在文本框中只能填写数字。</p>
			<p>需求统一以元为单位计时。</p>
			<p>未选择套餐时，第一项不填。其他情况所有项均为必填。</p>
			<p>相关信息可以在手机运营商网上营业厅通过查询手机账单获得，也可以发送短信查询。</p>
		</div>
		<div id="content" style="margin-right: 0px;">
		<%@ include file="pages/billform.jsp" %>
		</div>

		<div id="footer">
			<p>
				Copyright &copy; 2014, HIT - <a href="#">mzd44202@126.com</a> -
				Designed by <a href="#">HYQ、LTY、ZZN</a>
			</p>
		</div>
	</div>
</body>
</html>