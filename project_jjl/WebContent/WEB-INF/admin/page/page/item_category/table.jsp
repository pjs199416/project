<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>商品分类</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：商品类别信息 &rarr; <span>商品类别查看</span>
	</div>
	
	<table class="table" style="margin-top: 20px;">
		<tr>
        	
			<th style="width:11%;">序号</th>
			<th style="width:15%;">分类名称</th>
			<th style="width:11%;">使用状态</th>
			<th style="width:18%;">创建时间</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${list }" var="category" varStatus="in">
			<tr>
				<td align="center">${in.index+1 }</td>
				<td align="center">${category.getName() }</td>
				<td align="center">${category.getStatus()==1?'正常':'下架' }</td>
				<td align="center">
					<j:dateFormat date="${category.getCreatedate() }"/>
				</td>
				<td align="center">
	                 <j:a href="${pageContext.request.contextPath }/category.action?method=editUI&id=${category.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/category.action?method=enable&id=${category.id}"><div class="modify" title="启用">&#xe63b;启用</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/category.action?method=disable&id=${category.id}"><div class="modify" title="禁用">&#xe63b;禁用</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/category.action?method=moveUp&id=${category.id}"><div class="moveUp" title="上移">&#xe611;上移</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/category.action?method=moveDown&id=${category.id}"><div class="moveDown" title="下移">&#xe613;下移</div></j:a>
	                <j:a href="javascript:del(${category.id },'${pageContext.request.contextPath }/category.action?method=delete')"><div class="del" title="删除">&#xe792;删除</div></j:a>
	                
	            </td>
			<tr>
		</c:forEach>
        <!-- <tr>
			<td align="center">2</td>
			<td align="center">水果</td>
			<td align="center">正常</td>
			<td align="center">2019-12-12</td>
			<td align="center">
                <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;修改</div></a>
                <div class="del" title="删除">&#xe792;删除</div>
                <div class="moveUp" title="上移">&#xe611;上移</div>
                <div class="moveDown" title="下移">&#xe613;下移</div>
            </td>
		<tr> -->
		<tr>
			<td colspan="6" valign="middle" style="background:#F6F6F6">
				<j:a href="${pageContext.request.contextPath}/category.action?method=addUI"><div class="add" title="添加">&#xe607;添加</div></j:a>
			</td>
		</tr>
	</table>
</body>
</html>
