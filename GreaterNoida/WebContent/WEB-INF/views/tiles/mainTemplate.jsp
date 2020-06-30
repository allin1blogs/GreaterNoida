<%-- 
    Document   : Main Template
    Created on : 29 Mar, 2018, 11:45:32 AM
    Author     : Swapril Tyagi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><tiles:getAsString name="title" /></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/dms.css'/>"/>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/base.css'/>"/>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/bootstrap.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/bootstrap-theme.min.css'/>"/>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/modelSheet.css'/>"/>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/msc-style.css'/>"/>
		<script type="text/javascript" src="<c:url value='/staticResources/scripts/msc-script.js' />"></script>
		<script type="text/javascript" src="<c:url value='/staticResources/scripts/modelScript.js' />"></script>
	</head>
	<body style="background-color: #F2F2F2; " oncontextmenu="return false">
		<header id="header">
			<tiles:insertAttribute name="header"/>
		</header>
		<menu style="padding: 0px; margin: 0px;" id="menu">
			<tiles:insertAttribute name="menu"/>
		</menu>
		<section id="body" style="background-image: url(${pageContext.request.contextPath}/staticResources/images/backgroundLogo.jpg); background-repeat:no-repeat; background-position: center;">
			<tiles:insertAttribute name="body"/>
		</section>
		<footer id="footer">
			<tiles:insertAttribute name="footer"/>
		</footer>
	</body>
</html>