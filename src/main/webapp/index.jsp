<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet"/>
</head>
<body>
    <div class="navbar">
	    <div class="navbar-inner">
		    <a class="brand">彩虹桥</a>
			<ul class="nav">
				<li class="active"><a href="<spring:url value='/'/>">欢迎</a></li>
				<li><a href="<spring:url value='/account/show'/>">微信公众平台账号管理</a></li>
				<li><a href="<spring:url value='/autoresponse/subscribe'/>">自动回复</a></li>
				<li><a href="<spring:url value='/wxmenu/show'/>">自定义菜单</a></li>
				<li><a href="<spring:url value='/wxqrcode/show'/>">微信二维码管理</a></li>
			</ul>
	    </div>
    </div>
    
    <div class="hero-unit" align="center">
	    <img src="<spring:url value='/resources/images/heimdall.jpg' />" alt="heimdall" />
    </div>
</body>
</html>

