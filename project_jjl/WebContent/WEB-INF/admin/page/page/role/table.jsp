<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
	<title>角色页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：角色信息 &rarr; <span>角色信息查看</span>
	</div>
	
	<table class="table" style="margin-top: 20px;">
		<tr>
  
			<th style="width:10%;">序号</th>
			<th style="width:15%">角色名称</th>
			<th style="width:30%">角色说明</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${list }" var="role" varStatus="in">
			<tr>
				<td align="center">${in.count }</td>
				<td align="center">${role.name }</td>
				<td align="center">${role.description }</td>
				<td align="center">
	                 <j:a href="${pageContext.request.contextPath}/role.action?method=editUI&id=${role.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
	                 <j:a href="javascript:del(${role.id },'${pageContext.request.contextPath }/role.action?method=delete')" ><div class="del" title="删除">&#xe792;删除</div></j:a>
	                 <j:a href="${pageContext.request.contextPath}/role.action?method=privliegeUI&id=${role.id}"><div class="privilege" title="删除">&#xe641;权限管理</div></j:a>
	            </td>
			</tr>
		</c:forEach>
		<!-- <tr>
			<td align="center">1</td>
			<td align="center">管理员</td>
			<td align="center">拥有一切功能</td>
			<td align="center">
                 <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;修改</div></a>
                 <div class="del" title="删除">&#xe792;删除</div>
                 <a href="privilege.jsp"><div class="privilege" title="删除">&#xe641;权限管理</div></a>
            </td>
		</tr> -->
		<tr>
			<td colspan="6" valign="middle" style="background:#F6F6F6">
				<j:a href="${pageContext.request.contextPath}/role.action?method=addUI"><div class="add" title="添加">&#xe607;添加</div></j:a>
			</td>
		</tr>
	</table>
</body>
</html>
