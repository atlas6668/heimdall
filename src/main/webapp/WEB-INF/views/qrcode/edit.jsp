<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet" />
</head>
<body>
	<div class="navbar">
	    <div class="navbar-inner">
			<a class="brand">彩虹桥</a>
		    <ul class="nav">
		    <li><a href="<spring:url value='/'/>">欢迎</a></li>
		    <li><a href="<spring:url value='/account/show'/>">微信公众平台账号管理</a></li>
		    <li><a href="<spring:url value='/autoresponse/subscribe'/>">自动回复</a></li>
		    <li><a href="<spring:url value='/wxmenu/show'/>">自定义菜单</a></li>
		    <li class="active"><a href="<spring:url value='/wxqrcode/show'/>">微信二维码管理</a></li>
		    </ul>
	    </div>
    </div>

	<ul class="nav nav-tabs">
		<li><a href="<spring:url value='/wxqrcode/show'/>" data-toggle="tab">二维码库</a></li>
		<li class="active"><a data-toggle="tab">编辑二维码</a></li>
	</ul>
	
	<spring:url value="/wxqrcode/save" var="save" />
	<form:form id="s_form" action="${save}" modelAttribute="model" method="post" 
		class="form-horizontal" enctype="multipart/form-data">
	    <div class="control-group">
	    	<div class="controls">
			    <form:hidden path="id"/>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">公众号</label>
			<div class="controls">
				<form:input path="app"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称</label>
			<div class="controls">
				<form:input path="name"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">场景</label>
			<div class="controls">
				<form:input path="scene"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">场景KEY值</label>
			<div class="controls">
				<form:input path="wkey"/>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="submit" class="btn btn-primary" value="保存" >
			</div>
		</div>
	</form:form>
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
</body>
</html>

