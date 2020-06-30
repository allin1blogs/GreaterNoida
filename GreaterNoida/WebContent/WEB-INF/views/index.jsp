<%-- 
    Document   : Index
    Created on : 29 Mar, 2018, 10:50:32 AM
    Author     : Swapril Tyagi
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>GNIDA</title>
		<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/modelSheet.css'/>"/>
		<script type="text/javascript" src="<c:url value='/staticResources/scripts/modelScript.js' />"></script>
		<style>
    		/* Form */
    		.form {
        		position: relative; z-index: 1; background: #FFFFFF; max-width: 580px; margin: 0 auto 100px; margin-bottom: 0px; padding: 30px; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px; text-align: center;
    			}
    		.form .thumbnail {
        		width: 250px; height: 250px; margin: 0 auto 30px; padding: 50px 30px; border-top-left-radius: 100%; border-top-right-radius: 100%; border-bottom-left-radius: 100%; border-bottom-right-radius: 100%; box-sizing: border-box; 
        	}
    		.form .thumbnail img { display: block; width: 100%; }
    		.form input {  outline: 0; background: #DCDFD9; width: 100%; border: 0; margin: 0 0 15px; padding: 15px; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px; box-sizing: border-box; font-size: 14px; }
    		.form button {
        		outline: 0; background: GREEN; width: 100%; border: 0; padding: 15px; border-top-left-radius: 3px; border-top-right-radius: 3px; border-bottom-left-radius: 3px; border-bottom-right-radius: 3px; color: #FFFFFF; font-size: 14px; -webkit-transition: all 0.3 ease; transition: all 0.3 ease; cursor: pointer;
    		}
    		.form .message { margin: 15px 0 0; color: #b3b3b3; font-size: 12px; }
    		.form .message a { color: GREEN; text-decoration: none; }
    		.form .register-form { display: none; }
    		.container {
        		position: relative;
        		z-index: 1;
        		max-width: 300px;
        		margin: 0 auto;
    		}
    		.container:before, .container:after {
        		content: "";
        		display: block;
        		clear: both;
    		}
    		.container .info {
        	margin: 50px auto;
        	text-align: center;
    		}
    		.container .info h1 {
        		margin: 0 0 15px;
        		padding: 0;
        		font-size: 36px;
        		font-weight: 300;
        		color: #1a1a1a;
    		}
    		.container .info span {
        		color: #4d4d4d;
        		font-size: 12px;
    		}
    		.container .info span a {
        		color: #000000;
        		text-decoration: none;
    		}
    		.container .info span .fa {
        		color: GREEN;
    		}
    		/* END Form */
		</style>
	</head>
    <body style="background-color: #EFE5E4;">
    	<c:if test="${not empty msg}">
			<div id="myModal" class="modal" style="display: block; z-index: 9999;">
  				<div class="modal-content">
    				<div class="modal-header" style="background-color: #387403;">
      					<span class="close" onclick="document.getElementById('myModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      					<p style="text-align: center; color: #FFFFFF;" class="h2">${msg}</p>
    				</div>
  				</div>
			</div>
		</c:if>
        <br><h1 style="text-align: center; font-family: cambria math; font-size: 38px;">Centralized Document Management Portal</h1>
        <br>
        <div class="form">
        	<img style="max-width: 82%;" src="${pageContext.request.contextPath}/staticResources/images/indexLogo.jpg">
        	<p style="margin-top: 0px; padding-top: 0px; font-size: 26px; font-family: cambria math; text-align: center;"><b>Login Here...</b></p>
        	
        	<form action="${pageContext.request.contextPath}/login" class="login-form" method="post">
            	<input type="text" name="userId" placeholder="Enter UserId"  value="root" autocomplete="off" required/>
            	<input type="password" name="password" placeholder="Enter Password"  value="root" required/>
            	<b><input type="submit" style="background-color: green; color: white; font-size: 14px;" value="Login"></b></input>
        	</form>
        </div>
        <div style="margin-left: 38%; width: 390px; height: 40px; margin-top: 100px;">
			<a href="#" style="color: black; text-decoration: none; align: center;">©<strong>2018 Greater Noida Industrial Development Authority</strong></a>
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QuerySoft Version 1.2
		</div>
    </body>
</html>