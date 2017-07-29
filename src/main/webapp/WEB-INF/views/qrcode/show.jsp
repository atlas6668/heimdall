<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

<div>
	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab">二维码库</a></li>
	</ul>
	
	&nbsp;&nbsp;<a id="create" class="btn btn-primary" href="<spring:url value='/wxqrcode/create'/>">新建二维码</a>
	
	<c:forEach items="${qrcodes}" var="qrcode" varStatus="stat">
		<c:if test="${stat.index%4==0}">
			<div class="row-fluid">
				<ul class="thumbnails">
		</c:if>
				<li class="span3">
					<a class="thumbnail" id="edit" href="<spring:url value='/wxqrcode/edit/${qrcode.id}'/>">
						<img style="width: 300px; height: 300px;" src="<spring:url value='${qrcode.path}'/>">
						<div class="caption">
							<h3 align="center">${qrcode.name}-${qrcode.scene}</h3>
						</div>
					</a>
				</li>
		<c:if test="${stat.index%4==3}">
				</ul>
			</div>
		</c:if>
	</c:forEach>
</div>	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
</body>
</html>

