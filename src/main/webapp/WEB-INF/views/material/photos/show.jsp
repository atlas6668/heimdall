<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="izy" uri="/WEB-INF/tags/pagination.tld"%>
<%@ taglib prefix="fnc" uri="/WEB-INF/tags/fnc.tld" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link
	href="<spring:url value='/webjars/bootstrap/2.3.0/css/bootstrap.min.css' />"
	rel="stylesheet" />
</head>
<body>
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand">彩虹桥</a>
			<ul class="nav">
				<li><a href="<spring:url value='/'/>">欢迎</a></li>
				<li class="active"><a
					href="<spring:url value='/material/photos'/>">素材管理</a></li>
			</ul>
		</div>
	</div>

<div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="<spring:url value='/material/photos'/>" data-toggle="tab">图片库</a></li>
		<li><a href="<spring:url value='/material/photos'/>" data-toggle="tab">图文消息</a></li>
		<li><a data-toggle="tab">语音库</a></li>
		<li><a data-toggle="tab">视频库</a></li>
	</ul>
	
    <!-- 搜索form -->
    <spring:url value="/material/photos" var="search" />
	<form id="f_search" action="${search}" method="post" 
		modelAttribute="model" class="breadcrumb form-search">
		<input type="hidden" id="pageNum" name="pageNum"/>
		<input type="hidden" id="pageSize" name="pageSize"/>
	</form>
		&nbsp;&nbsp;<label class="btn btn-success"><input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))"/>&nbsp;全选</label>
	    &nbsp;&nbsp;<a id="add" class="btn btn-info" href="<spring:url value='/material/photos/upload/view'/>">上传图片</a>
	    &nbsp;&nbsp;<input id="delete-button" class="btn btn-danger" type="button" value="删除图片"/>
    
	<!-- 删除form -->
    <form id="delete_form" action="<spring:url value='/material/photos/delete'/>" method="post">
	    <input type="hidden" name="ids" id="delete_model_ids">
    </form>
	
	<c:forEach items="${photos}" var="photo" varStatus="stat">
		<c:if test="${stat.index%4==0}">
			<div class="row-fluid">
				<ul class="thumbnails">
		</c:if>
				<li class="span3">
					<input type="checkbox" name="modelId" value="${photo.id}" />
					
					<a href="<spring:url value='/material/photos/edit/${photo.id}'/>" class="thumbnail">
						<spring:url value="${photo.path}" var="path" />
						<img src="${path}">
						<div class="caption">
							<h3 align="center">${photo.name}</h3>
						</div>
					</a>
				</li>
		<c:if test="${stat.index%4==3}">
				</ul>
			</div>
		</c:if>
	</c:forEach>
</div>	
	<br />
	<!-- 分页DIV -->
	<izy:pagination pager="${pager}" pageTagClass="pagination pagination-centered"/>
	${fnc:getPhotoList()}
	
	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
</body>
</html>

