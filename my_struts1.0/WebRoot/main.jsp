<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <center>
    	MAIN<hr>
    	<h3>${userForm.username }</h3>
    	<h3>${userForm.password }</h3>
    	<h3>${userForm.age }</h3>
    	<h3>${userForm.utilDate }</h3>
    	<h3>${userForm.money }</h3>
    </center>
  </body>
</html>
