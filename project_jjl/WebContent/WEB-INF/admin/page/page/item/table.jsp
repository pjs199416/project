<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>商品页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：商品信息 &rarr; <span>商品查看</span>
	</div>
	<div class="searchdiv">
		<div>商品名字：<input id="title" type="text" value="${param.title }"/></div>
		<div class="btn1" onclick="search()">查 找</div>
		<script type="text/javascript">
			function search() {
				var title = document.getElementById("title").value;
				location.href = "${pageContext.request.contextPath }/item.action?title="+title;
			}
		</script>
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:5%;">序号</th>
			<th style="width:10%;">商品标题</th>
			<th style="width:15%;">商品描述</th>
			<th style="width:10%;">商品图片</th>
			<th style="width:5%;">商品价格</th>
			<th style="width:5%;">商品重量</th>
			<th style="width:5%;">库存</th>
			<th style="width:5%;">商品状态</th>
			<th style="width:8%;">创建时间</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${list }" var="item" varStatus="in">
			<tr>
				<td align="center">${in.index+1 }</td>
				<td align="center">${item.title }</td>
				<td align="center">${item.description }</td>
				<td align="center"><a title="点击看原图" href="${item.pic }"><img style="width:50px;height:50px;" src="${item.pic }" /></a></td>
				<td align="center">${item.price }￥</td>
				<td align="center">${item.weight }kg</td>
				<td align="center">${item.num }份</td>
				<td align="center">${item.status==0?"下架":"正常" }</td>
				<td align="center">
					<j:dateFormat date="${item.createdate}"/>
				</td>
				<td align="center">
	                <j:a href="${pageContext.request.contextPath }/item.action?method=editUI&id=${item.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/item.action?method=enable&id=${item.id}"><div class="modify" title="启用">&#xe63b;启用</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/item.action?method=disable&id=${item.id}"><div class="modify" title="禁用">&#xe63b;禁用</div></j:a>
	            </td>
			<tr>
       </c:forEach>
       
		<tr>
			<td colspan="11" valign="middle" style="background:#F6F6F6">
				<j:a href="${pageContext.request.contextPath }/item.action?method=addUI"><div class="add" title="添加">&#xe607;添加</div></j:a>
			</td>
		</tr>
		
		<tr >
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
</body>
</html>
