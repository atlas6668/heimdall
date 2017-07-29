<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />" rel="stylesheet" />
	<link href="<spring:url value='/resources/css/qqFace.css'/>" rel="stylesheet" />
</head>
<body>
	<div class="navbar">
	    <div class="navbar-inner">
			<a class="brand">彩虹桥</a>
		    <ul class="nav">
		    <li><a href="<spring:url value='/'/>">欢迎</a></li>
		    <li><a href="<spring:url value='/account/show'/>">微信公众平台账号管理</a></li>
		    <li class="active"><a href="<spring:url value='/autoresponse/subscribe'/>">自动回复</a></li>
		    <li><a href="<spring:url value='/wxmenu/show'/>">自定义菜单</a></li>
		    <li><a href="<spring:url value='/wxqrcode/show'/>">微信二维码管理</a></li>
		    </ul>
	    </div>
    </div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="<spring:url value='/autoresponse/subscribe'/>" data-toggle="tab">关注自动回复</a></li>
		<li><a href="<spring:url value='/autoresponse/message'/>" data-toggle="tab">消息自动回复</a></li>
		<li><a href="<spring:url value='/autoresponse/keyword'/>" data-toggle="tab">关键词自动回复</a></li>
	</ul>

	<div class="comment">
        <div class="com_form">
        	<div class="btn-toolbar">
				<div class="btn-group">
					<button class="btn btn-success">文字</button>
					<button class="btn disabled">图片</button>
					<button class="btn disabled">语音</button>
					<button class="btn disabled">视频</button>
			    </div>
		    </div>
        	<hr>
			<div class="edit_area" contenteditable="true" id="saytext" style="overflow-y:auto; overflow-x:hidden;">
			</div>
			<hr>
        	<p><span class="emotion">表情</span></p>
        </div>
    </div>
	
	<div class="comment1">
		<div class="com_form">
			<spring:url value="/autoresponse/subscribe/save" var="save" />
			<form:form action="${save}" modelAttribute="model" method="post" enctype="multipart/form-data">
			    <form:hidden path="id"/>
			    <form:hidden path="content"/>
			    <form:hidden path="messageType" value="text"/>
			    <input id="saveBut" type="submit" class="btn btn-primary" value="保存" >
			    <input id="clearBut" type="submit" class="btn btn-warning" value="清空" >
			</form:form>
		</div>
	</div>

	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/resources/js/jquery.min.js'/>"></script>
	<script src="<spring:url value='/resources/js/jquery.qqFace.js'/>"></script>
	<script type="text/javascript">
		$(function(){
			$("#saytext").get(0).innerHTML += $("#content").val();
			$(".emotion").qqFace({
				id : "facebox", 
				assign: "saytext", 
				path: "<spring:url value='/resources/images/smiley/'/>" //表情存放的路径
			});
			$("#saveBut").click(function(e){
				var content = $("#saytext").get(0).innerHTML;
				$("#content").val(content);
			});
			$("#clearBut").click(function(e){
				var content = $("#saytext").get(0).innerHTML = "";
				$("#content").val(content);
			});
		});
	</script>
</body>
</html>

