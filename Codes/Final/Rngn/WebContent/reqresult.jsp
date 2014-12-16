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
				<li><a href="request.jsp" title="通过提出需求使用功能">->提出需求</a></li>
				<li><a href="bill.jsp" title="通过录入账单使用功能">录入账单</a></li>
				<li><a href="applist.jsp" title="查看可选包">查看附加套餐</a></li>
			</ul>
			<h1>小贴士</h1>
			<p>在文本框中只能填写纯数字。</p>
			<p>需求统一以分钟、条、MB为单位计时。</p>
			<p>对手机使用情况的大致估计方法参考如下：</p>
			<ul>
				<li>每天1~2通电话：50~100分钟/月</li>
				<li>每天3~5条短信：100~150条/月</li>
				<li>每天在线浏览新闻50条（不含图片）：20~30MB/月</li>
				<li>每天在线观看视频10分钟（非高清）：150~200MB/月</li>
				<li>每天玩手游1小时（非网页游戏）：50~100MB/月</li>
			</ul>
		</div>
		<div id="content" style="margin-right: 0px;">
		<%@ include file="pages/reqresulttable.jsp" %>
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