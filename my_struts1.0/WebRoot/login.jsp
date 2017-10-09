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
	
  </head>
  
  <body>
    <center>
    	<form action="login.do?command=login" method="post"> 
    	username:<input type="text" name="username"/><br/>
    	password:<input type="text" name="password"/><br/>
    	age:<input type="text" name="age"/><br/><br/>
    	money:<input type="text" name="money"/><br/>
    	<input type="submit" value="login"/>
    	</form>
    </center>
  </body>
</html>
