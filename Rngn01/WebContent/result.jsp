<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id = "sol" scope = "session" class="com.mp.bean.SolveBean"/>

<jsp:getProperty name="sol" property="mTelecomRSP"/>
<br/>
<jsp:getProperty name="sol" property="mTelecomRSPT"/>