<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div id="head">
		<img src="sources/logo.jpg" style="display:inline;width:65px;height:50px;"><p>最省钱的手机套餐</p><br/>
		<p>尊敬的电信用户：</p>
		<p>&emsp;您好，欢迎使用手机套餐推荐，请输入需求：</p>
	</div>
	<div id="query">
		<form method="post" action="solverequest">
			<input type="hidden" name="action" value="query">
			<p>&emsp;通话时长/月</p>
			<input type="text" name="call"><br>
			<p>&emsp;短信数量/月</p>
			<input type="text" name="msg"><br>
			<p>&emsp;上网流量/月</p>
			<input type="text" name="info"><br>
			<input type="reset" value="重填">
			<input type="submit" value="查询">
		</form>
	</div>
</body>
</html>