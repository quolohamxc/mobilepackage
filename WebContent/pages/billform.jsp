<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.io.*,java.util.*,com.mp.model.*,com.mp.telecom.model.*,com.mp.telecom.*,com.mp.util.placedata.city.*,com.mp.util.placedata.province.*"%>
<%!String[] fee = {"","","","",""};
	String[] call = {"","","","",""};
	String[] msg = {"","","","",""};
	String[] info = {"","","","",""};
	int formNum = 0;
	int zter;
	String prov = "1";
	String city = "1";
	String pkgcate = "1";
	String cellba = "1";
	String cellbb = "1";
	String cellbc = "1";
	String cellca = "1";
	String cellda = "1";
	String cellea = "1";
	%>
<%
	try {
		formNum = Integer.valueOf(request.getParameter("form_num"));
		for (int iter = 0; iter < formNum; iter++) {
			fee[iter] = request.getParameter("fee_" + iter);
			call[iter] = request.getParameter("call_" + iter);
			msg[iter] = request.getParameter("msg_" + iter);
			info[iter] = request.getParameter("info_" + iter);
		}

	} catch (NumberFormatException e) {
		formNum = 0;
	}
	if(formNum > 1) {
		prov = request.getParameter("prov");
		city = request.getParameter("city");
		pkgcate = request.getParameter("pkg_cate");
		cellba = request.getParameter("cell-2-1");
		cellbb = request.getParameter("cell-2-2");
		cellbc = request.getParameter("cell-2-3");
		cellca = request.getParameter("cell-3-1");
		cellda = request.getParameter("cell-4-1");
		cellea = request.getParameter("cell-5-1");
	}	
	if (formNum >= 5) {
		//out.println("max form number is five");
	} else {
		formNum++;
	}
%>

<html>
<script type="text/javascript">
	function direct(value) {
		if (value == 1) {
			document.queryForm.action = "bill.jsp";
		} else if (value == 2) {
			document.queryForm.action = "SolveBillRequest";
		} else {
			document.queryForm.action = "error.jsp";
		}
		document.queryForm.submit();
	}
	function showOption(value) {
		document.getElementById('mainform').style.display = "";
		document.getElementById('cell-1').style.display = "none";
		document.getElementById('cell-2').style.display = "none";
		document.getElementById('cell-3').style.display = "none";
		document.getElementById('cell-4').style.display = "none";
		document.getElementById('cell-5').style.display = "none";
		var s = 'cell-' + value.toString();
		if (s == 'cell-0'){
			return;
		} else if (s == 'cell-1'){
			document.getElementById('mainform').style.display = "none";
		}
		document.getElementById(s).style.display = "";
	}
	function showHide(value) {
		if(value == 1) {
			document.getElementById('ctelecom').style.display = "";
		}
	}
</script>

<body>
	<div id="bill">
		<form name="queryForm" method="GET">
			<input type="radio" checked="checked" name="carrieroperator"
				value="ctelecom" onClick="showHide(1)"/> 中国电信 <br/>
			<div id="ctelecom">
			<div id="package">
				<p>
					请选择所在地： <select name="prov">
						<option value="1"></option>
						<%
							zter = 2;
							for (String str : ProvNameChn._placeList) {
								if (zter == Integer.valueOf(prov)) {
									out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + str + "</option>");
								} else if(str == ProvNameChn.HEILONGJIANG) {
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
								if (zter == Integer.valueOf(city)) {
									out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + str + "</option>");
								} else {
									out.println("<option value=\"" + (zter++) + "\">" + str + "</option>");
								}
							}
						%>
					</select>
				</p>
				<p>请选择您现在所使用的套餐：</p>
				<select name="pkg_cate">
					<option value="1" onClick="showOption(0)"></option>
					<optgroup label="自由定制套餐">
						<option value="2" onClick="showOption(1)">自由定制</option>
					</optgroup>
					<optgroup label="组合套餐">
						<option value="3" onClick="showOption(2)">积木套餐</option>
					</optgroup>
					<optgroup label="独立套餐">
						<option value="4" onClick="showOption(3)">易通卡套餐</option>
						<option value="5" onClick="showOption(4)">飞young纯流量</option>
						<option value="6" onClick="showOption(5)">乐享3G/4G</option>
					</optgroup>
					<optgroup label="其它">
						<option value="7" onClick="showOption(0)">其它</option>
					</optgroup>
				</select>

				<div id="cell-1" style="display: none">
					<p>自由定制暂不支持附加套餐。</p>
					<p>自由定制套餐的修改可以通过登陆网上营业厅完成。用户可以随时根据使用情况更改套餐涵盖范围。</p>
					<p>如果希望更换其它套餐，请选择空白项。</p>
				</div>
				<div id="cell-2" style="display: none">
					<select name="cell-2-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (MultiPkgJuggle mPJ : MultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getmType() == MultiPkgInfo.CALL) {
									String s = mPJ.toString();
									if (zter == Integer.valueOf(cellba)) {
										out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
									} else {
										out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
									}
								}
							}
						%>
					</select> <select name="cell-2-2">
						<option value="1"></option>
						<%
							for (MultiPkgJuggle mPJ : MultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getmType() == MultiPkgInfo.MSG) {
									String s = mPJ.toString();
									if (zter == Integer.valueOf(cellbb)) {
										out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
									} else {
										out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
									}
								}
							}
						%>
					</select> <select name="cell-2-3">
						<option value="1"></option>
						<%
							for (MultiPkgJuggle mPJ : MultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getmType() == MultiPkgInfo.INFO) {
									String s = mPJ.toString();
									if (zter == Integer.valueOf(cellbc)) {
										out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
									} else {
										out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
									}
								}
							}
						%>
					</select>
				</div>
				<div id="cell-3" style="display: none">
					<select name="cell-3-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (PackageTariff pT : SinglePkgInfo.getSinglePkgList()) {
								String s = pT.getmTitle();
								if(s.contains("易通"))
								{
									if (zter == Integer.valueOf(cellca)) {
										out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
									} else {
										out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
									}
								}
							}
						%>
					</select>
				</div>
				<div id="cell-4" style="display: none">
					<select name="cell-4-1">
						<option value="1"></option>
						<%
							zter = 2;
						for (PackageTariff pT : SinglePkgInfo.getSinglePkgList()) {
							String s = pT.getmTitle();
							if(s.contains("飞Young"))
							{
								if (zter == Integer.valueOf(cellda)) {
									out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
								} else {
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						}
						%>
					</select>
				</div>
				<div id="cell-5" style="display: none">
					<select name="cell-5-1">
						<option value="1"></option>
						<%
							zter = 2;
						for (PackageTariff pT : SinglePkgInfo.getSinglePkgList()) {
							String s = pT.getmTitle();
							if(s.contains("乐享"))
							{
								if (zter == Integer.valueOf(cellea)) {
									out.println("<option value=\"" + (zter++) + "\"selected=\"selected" + "\">" + s + "</option>");
								} else {
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						}
						%>
					</select>
				</div>
			</div>
			<div id="mainform">
				<input type="button" name="submit1"
					onclick="direct(1)" value="添加更多表单（最大为5）"><br> <input type="hidden"
					name="action" value="MultiQuery"> <input type="hidden"
					name="form_num" value="<%=formNum%>">
				<%
					for (int iter = formNum - 1; iter >= 0; iter--) {
						out.println("<hr/>");
						out.println("<div><table>");
						out.println("<tr><td>套餐费及固定费（元/月）:</td>");
						out.println("<td><input type=\"text\" name=\"fee_" + iter
								+ "\" value=\"" + fee[iter] + "\" /> </td></tr>");
						//out.println("<p>call:</p>");
						out.println("<tr><td>语音通信费（元/月）:</td>");
						out.println("<td><input type=\"text\" name=\"call_" + iter
								+ "\" value=\"" + call[iter] + "\" /> </td></tr>");
						//out.println("<p>message:</p>");
						out.println("<tr><td>短信及彩信费（元/月）:</td>");
						out.println("<td><input type=\"text\" name=\"msg_" + iter
								+ "\" value=\"" + msg[iter] + "\" /> </td></tr>");
						//out.println("<p>info:</p>");
						out.println("<tr><td>上网费（元/月） :</td>");
						out.println("<td><input type=\"text\" name=\"info_" + iter
								+ "\" value=\"" + info[iter] + "\" /> </td></tr>");
						out.println("</table></div>");
					}
				%>
				<input type="reset" value="重填"/> <input type="button"
					name="submit2" onclick="direct(2)" value="提交">
			</div>
			</div>
		</form>
	</div>
</body>
</html>