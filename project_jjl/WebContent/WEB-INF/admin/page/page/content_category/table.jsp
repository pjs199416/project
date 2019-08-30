<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>首页内容分类列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：首页内容分类管理信息 &rarr; <span>首页内容分类列表查询</span>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:50px;">序号</th>
			<th style="width:150px;">内容分类模块</th>
			<th style="width:100px;">描述</th>
			<th style="width:100px;">状态</th>
			<th style="width:100px;">操作</th>
		</tr>
		<c:forEach items="${list }" var="contentCategory" varStatus="in">
		<tr>
			<td align="center">${in.index+1 }</td>
			<td align="center">${contentCategory.name }</td>
			<td align="center">${contentCategory.description }</td>
			<td align="center">${contentCategory.status==1?"正常":"废弃" }</td>
			<td align="center">
                <j:a href="${pageContext.request.contextPath }/contentCategory.action?method=editUI&&id=${contentCategory.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
                <j:a href="${pageContext.request.contextPath }/contentCategory.action?method=enable&&id=${contentCategory.id}"><div class="del" title="启用">&#xe63b;启用</div></j:a>
                <j:a href="${pageContext.request.contextPath }/contentCategory.action?method=disable&&id=${contentCategory.id}"><div class="del" title="废弃">&#xe63b;禁用</div></j:a>
                <j:a href="javascript:del(${contentCategory.id },'${pageContext.request.contextPath }/contentCategory.action?method=delete')"><div class="del" title="删除">&#xe792;删除</div></j:a>
                            
            </td>
		<tr>
		</c:forEach>
		<!-- <tr>
			<td align="center">1</td>
			<td align="center">轮播图</td>
			<td align="center">首页轮播图设计</td>
			<td align="center">正常</td>
			<td align="center">
                <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;修改</div></a>
                <div class="del" title="废弃">&#xe63b;废弃</div>
                <div class="del" title="启用">&#xe63b;启用</div>
            </td>
		<tr> -->
       <tr>
			<td colspan="6" valign="middle" style="background:#F6F6F6">
				<j:a href="${pageContext.request.contextPath }/contentCategory.action?method=addUI"><div class="add" title="添加">&#xe607;添加</div></j:a>
			</td>
		</tr>
	</table>
</body>
</html>
