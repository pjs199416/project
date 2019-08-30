<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>用户页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：用户信息 &rarr; <span>用户信息查看</span>
	</div>
	<div class="searchdiv">
		<div>用户名：<input id="name" type="text" value="${param.name }"/></div>
		<div class="btn1" onclick="search()">查 找</div>
		<div class="btn1" onclick="add()">添加</div>
		<script type="text/javascript">
			function add(){
				location.href="${pageContext.request.contextPath}/user.action?method=addUI"
			}
			function search() {
				var name = document.getElementById("name").value;
				location.href = "${pageContext.request.contextPath}/user.action?name="+name;
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:50px;">编号</th>
			<th style="width:150px;">用户名</th>
			<th style="width:100px;">角色</th>
			<th style="width:100px;">手机号</th>
			<th style="width:100px;">状态</th>
			<th style="width:100px;">操作</th>
		</tr>
		<c:forEach items="${list }" var="user" varStatus="in">
		<tr>
			<td align="center">${in.count + (currentPage-1)*pageSize}</td>
			<td align="center">${user.name }</td>
			<td align="center">
				<c:forEach items="${user.list}" var="role">
					${role.name }&nbsp;&nbsp;
				</c:forEach>
			</td>
			<td align="center">${user.phone }</td>
			<td align="center">${user.status==1?'正常':'废弃' }</td>
			<td align="center">
                 <j:a href="${pageContext.request.contextPath}/user.action?method=editUI&id=${user.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
                 <j:a href="${pageContext.request.contextPath}/user.action?method=enable&id=${user.id}"><div class="modify" title="启用">&#xe63b;启用</div></j:a>
                 <j:a href="${pageContext.request.contextPath}/user.action?method=disable&id=${user.id}"><div class="modify" title="禁用">&#xe63b;禁用</div></j:a>
                <j:a clas="reset" href="${pageContext.request.contextPath }/user.action?method=resetPwd&id=${user.id}"><div class="modify" title="修改">&#xe63b;重置密码</div></j:a>
            </td>
		<tr>
		
		</c:forEach>
        <!-- <tr>
			<td align="center">1</td>
			<td align="center">后裔</td>
			<td align="center">管理员</td>
			<td align="center">asdfasdf1212</td>
			<td align="center">
                <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;修改</div></a>
                <div class="del" title="删除">&#xe792;删除</div>
                <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;重置密码</div></a>
            </td>
		<tr> -->
        
		
		<tr>
			<td colspan="11" valign="middle" align="center" style="background:#F6F6F6">
				第${currentPage }/${totalPage }页 &nbsp; &nbsp; &nbsp;
				总共${totalSize }条记录数   &nbsp; 每页显示:${pageSize }
				<c:if test="${ currentPage != 1 }">
					<a href="?title=${param.title }&currentPage=1">首页</a>
					<a href="?title=${param.title }&currentPage=${currentPage-1}">上一页</a>
				</c:if>
				&nbsp; &nbsp;
				<c:forEach var="i" begin="1" end="${totalPage }">
					<c:if test="${currentPage == i }">
						[${ i }]
					</c:if>
					<c:if test="${currentPage != i }">
						<a href="?title=${param.title }&currentPage=${ i}">[${ i }]</a>
					</c:if>
				</c:forEach>
				&nbsp; &nbsp;
				<c:if test="${currentPage !=totalPage }">
					<a href="?title=${param.title }&currentPage=${currentPage+1}">下一页</a>
					<a href="?title=${param.title }&currentPage=${totalPage}">末页</a>
				</c:if>
			</td>
		</tr>
	</table>
	
	<script type="text/javascript">
	$(".reset").click(function(){
		if(confirm("你确定将密码重置为123456吗？")){
			return true;
		}  else {
			return false
		}
	});
	</script>
</body>
</html>
