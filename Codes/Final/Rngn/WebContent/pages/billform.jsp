<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="java.io.*,java.util.*,com.mp.model.*,com.mp.telecom.model.*,com.mp.telecom.*,com.mp.unicom.model.*,com.mp.unicom.*,com.mp.util.placedata.city.*,com.mp.util.placedata.province.*"%>
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
		div1=document.getElementById("cell-1");
		div2=document.getElementById("cell-2");
		div3=document.getElementById("cell-3");
		div4=document.getElementById("cell-4");
		div5=document.getElementById("cell-5");
		main=document.getElementById("mainform");
		if(value == 1 || value == 7) {
			main.style.display="";
			div1.style.display="none";
			div2.style.display="none";
			div3.style.display="none";
			div4.style.display="none";
			div5.style.display="none";
		}
		if(value == 2) {
			main.style.display="none";
			div1.style.display="";
			div2.style.display="none";
			div3.style.display="none";
			div4.style.display="none";
			div5.style.display="none";
		}
		if(value == 3) {
			main.style.display="";
			div1.style.display="none";
			div2.style.display="";
			div3.style.display="none";
			div4.style.display="none";
			div5.style.display="none";
		}
		if(value == 4) {
			main.style.display="";
			div1.style.display="none";
			div2.style.display="none";
			div3.style.display="";
			div4.style.display="none";
			div5.style.display="none";
		}
		if(value == 5) {
			main.style.display="";
			div1.style.display="none";
			div2.style.display="none";
			div3.style.display="none";
			div4.style.display="";
			div5.style.display="none";
		}
		if(value == 6) {
			main.style.display="";
			div1.style.display="none";
			div2.style.display="none";
			div3.style.display="none";
			div4.style.display="none";
			div5.style.display="";
		}
	}
	function showUOption(value) {
		divb=document.getElementById("cell-b");
		divc=document.getElementById("cell-c");
		divd=document.getElementById("cell-d");
		dive=document.getElementById("cell-e");
		divf=document.getElementById("cell-f");
		divg=document.getElementById("cell-g");
		main=document.getElementById("mainform");
		if(value == 1 || value == 8) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="none";
			divd.style.display="none";
			dive.style.display="none";
			divf.style.display="none";
			divg.style.display="none";
		}
		if(value == 2) {
			main.style.display="";
			divb.style.display="";
			divc.style.display="none";
			divd.style.display="none";
			dive.style.display="none";
			divf.style.display="none";
			divg.style.display="none";
		}
		if(value == 3) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="";
			divd.style.display="none";
			dive.style.display="none";
			divf.style.display="none";
			divg.style.display="none";
		}
		if(value == 4) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="none";
			divd.style.display="";
			dive.style.display="none";
			divf.style.display="none";
			divg.style.display="none";
		}
		if(value == 5) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="none";
			divd.style.display="none";
			dive.style.display="";
			divf.style.display="none";
			divg.style.display="none";
		}
		if(value == 6) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="none";
			divd.style.display="none";
			dive.style.display="none";
			divf.style.display="";
			divg.style.display="none";
		}
		if(value == 7) {
			main.style.display="";
			divb.style.display="none";
			divc.style.display="none";
			divd.style.display="none";
			dive.style.display="none";
			divf.style.display="none";
			divg.style.display="";
		}
	}
	function changeMana(value) {
		div1 = document.getElementById("ctelecom");
		div2 = document.getElementById("cunicom");
		carrier = document.getElementById("carrier");
		main=document.getElementById("mainform");
		if (value == 1) {
			div1.style.display = "";
			div2.style.display = "none";
			carrier.value = "ctelecom";
		}
		if (value == 2) {
			div1.style.display = "none";
			div2.style.display = "";
			carrier.value = "cunicom";
			main.style.display="";
		}

	}
</script>

<body>
	<div id="bill">
		<form name="queryForm" method="GET">
			<div id="check">
				<input type="radio" checked="checked" name="carrieroperators"
					value="ctelecom" onclick="changeMana(1)" /> 中国电信 <input
					type="radio" name="carrieroperators" value="cunicom"
					onclick="changeMana(2)" /> 中国联通
			</div>
			<div id="hidden">
				<br /> <input type="hidden" id="carrier" name="carrieroperator"
					value="ctelecom" />
			</div>
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
						</select> <select name="city">
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
					<select name="pkg_cate" onchange="showOption(this.value)">
						<option value="1" onclick="showOption(1)"></option>
						<optgroup label="自由定制套餐">
							<option value="2" onclick="showOption(2)">自由定制</option>
						</optgroup>
						<optgroup label="组合套餐">
							<option value="3" onclick="showOption(3)">积木套餐</option>
						</optgroup>
						<optgroup label="独立套餐">
							<option value="4" onclick="showOption(4)">易通卡套餐</option>
							<option value="5" onclick="showOption(5)">飞young纯流量</option>
							<option value="6" onclick="showOption(6)">乐享3G/4G</option>
						</optgroup>
						<optgroup label="其它">
							<option value="7" onclick="showOption(1)">其它</option>
						</optgroup>
					</select> <br />

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
			</div>
			<div id="cunicom" style="display: none">
				<div id="package">
					<p>请选择您现在所使用的套餐：</p>
					<select name="pkg_ucate" onchange="showUOption(this.value)">
						<option value="1" onclick="showUOption(1)"></option>
						<optgroup label="全国套餐 ">
							<option value="2" onclick="showUOption(2)">3G全国套餐计划</option>
							<option value="3" onclick="showUOption(3)">4G全国套餐</option>
							<option value="4" onclick="showUOption(4)">3G预付费套餐</option>
						</optgroup>
						<optgroup label="组合套餐 ">
							<option value="5" onclick="showUOption(5)">4G全国组合套餐</option>
							<option value="6" onclick="showUOption(6)">4G共享组合套餐</option>
							<option value="7" onclick="showUOption(7)">4G本地组合套餐</option>
						</optgroup>
						<optgroup label="其它">
							<option value="8" onclick="showUOption(8)">其它</option>
						</optgroup>
					</select> <br />
				</div>
				<div id="cell-b" style="display: none">
					<select name="cell-b-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
								String s = pT.getmTitle();
								if(s.contains("计划"))
								{
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
				</div>
				<div id="cell-c" style="display: none">
					<select name="cell-c-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
								String s = pT.getmTitle();
								if(s.contains("4G"))
								{
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
				</div>
				<div id="cell-d" style="display: none">
					<select name="cell-d-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (PackageTariff pT : USinglePkgInfo.getSinglePkgList()) {
								String s = pT.getmTitle();
								if(s.contains("预付"))
								{
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
				</div>
				<div id="cell-e" style="display: none">
					<select name="cell-e-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.QGZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.CALL) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
					<select name="cell-e-2">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.QGZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.MSG) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
					<select name="cell-e-3">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.QGZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.INFO) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
				</div>
				<div id="cell-f" style="display: none">
					<select name="cell-f-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.GXZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.CALL) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select> <select name="cell-f-2">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.GXZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.MSG) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select> <select name="cell-f-3">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.GXZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.INFO) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select>
				</div>
				<div id="cell-g" style="display: none">
					<select name="cell-g-1">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.BDZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.CALL) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select> <select name="cell-g-2">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.BDZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.MSG) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
								}
							}
						%>
					</select> <select name="cell-g-3">
						<option value="1"></option>
						<%
							zter = 2;
							for (UMultiPkgJuggle mPJ : UMultiPkgInfo.getMultiPkgList()) {
								if(mPJ.getPtType() != UMultiPkgInfo.BDZH) {
									continue;
								}
								if(mPJ.getmType() == UMultiPkgInfo.INFO) {
									String s = mPJ.toString();
									out.println("<option value=\"" + (zter++) + "\">" + s + "</option>");
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

		</form>
	</div>
</body>
</html>