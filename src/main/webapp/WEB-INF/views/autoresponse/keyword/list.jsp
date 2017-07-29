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
		    <li class="active"><a href="<spring:url value='/autoresponse/subscribe'/>">自动回复</a></li>
		    <li><a href="<spring:url value='/wxmenu/show'/>">自定义菜单</a></li>
		    <li><a href="<spring:url value='/wxqrcode/show'/>">微信二维码管理</a></li>
		    </ul>
	    </div>
    </div>

	<ul class="nav nav-tabs">
		<li><a href="<spring:url value='/autoresponse/subscribe'/>" data-toggle="tab">关注自动回复</a></li>
		<li><a href="<spring:url value='/autoresponse/message'/>" data-toggle="tab">消息自动回复</a></li>
		<li class="active"><a href="<spring:url value='/autoresponse/keyword'/>" data-toggle="tab">关键词自动回复</a></li>
	</ul>
	
	<!-- 搜索form -->
    <spring:url value="/autoresponse/keyword" var="search" />
	<form:form id="f_search" action="${search}" method="post" 
		modelAttribute="model" class="breadcrumb form-search">
		<input type="hidden" id="pageNum" name="pageNum"/>
		<input type="hidden" id="pageSize" name="1"/>
		
		<div style="margin-top:8px;">
			<i class="icon-search"></i>
			&nbsp;<label>关键词(全词匹配)：</label><form:input path="keyword" htmlEscape="false" class="input-medium"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	
	&nbsp;&nbsp;<a id="add" class="btn btn-success" href="<spring:url value='/autoresponse/keyword/add'/>">添加规则</a>
	&nbsp;&nbsp;<input id="delete-button" class="btn btn-danger" type="button" value="删除规则"/>
	
	<!-- 删除form -->
    <form id="delete_form" action="<spring:url value='/autoresponse/keyword/delete'/>" method="post">
	    <input type="hidden" name="ids" id="delete_model_ids">
    </form>
    
	<!-- 列表 -->
   	<table class="table table-hover">
   		<thead>
			<tr>
				<th scope="col">
       				<input type="checkbox" onclick="$('input[name=modelId]:checkbox').prop('checked',$(this).prop('checked'))">
       			</th>
				<th>分类</th>
				<th>规则名称</th>
				<th>关键词(全词匹配)</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${keywords}" var="keyword" varStatus="stat">
				<tr>
				<td><input type="checkbox" name="modelId" value="${keyword.id}" /></td>
				<td>${keyword.category}</td>
				<td>${keyword.ruleName}</td>
				<td>${keyword.keyword}</td>
				<td>
					<a id="edit" class="btn" href="<spring:url value='/autoresponse/keyword/edit/${keyword.id}'/>">编辑规则</a>
				</td>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 分页DIV -->

	<!-- 延迟加载js，提高页面加载速度 -->
	<script src="<spring:url value='/webjars/jquery/2.0.3/jquery.js'/>"></script>
	<script src="<spring:url value='/resources/js/common.js'/>"></script>
</body>
</html>

