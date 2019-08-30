<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>首页内容</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：首页内容管理信息 &rarr; <span>首页内容查询</span>
	</div>
	<div class="searchdiv">
		<div>
			内容模块
			<select id="select1"  name="contentCategoryId" style="width:100px;height:auto;margin-left: 10px;">
				<c:forEach items="${list }" var="contentCategory" >
					<!-- 如果contentCategory的selected属性为true 就选中 否则 不选中  -->
					<c:choose >
						<c:when test="${contentCategory.selected}">
							<option selected value="${contentCategory.id}">${contentCategory.name }</option>
						</c:when>
						<c:otherwise>
							<option  value="${contentCategory.id}">${contentCategory.name }</option>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			</select>
			<script type="text/javascript">
				$(function(){
					$("#select1").change(function(){
						var id = $("#select1 option:selected").val();
						location.href="${pageContext.request.contextPath }/content.action?contentCategoryId="+id;
					});
				});
			</script>
		</div>
		
	</div>
	<table class="table" style="margin-top: 20px;">
		<tr>
			<th style="width:5%;">序号</th>
			<th style="width:5%;">内容标题</th>
			<th style="width:10%;">内容描述</th>
			<th style="width:10%;">内容链接</th>
			<th style="width:10%;">图片</th>
			<th style="width:10%">创建日期</th>
			<th style="width:5%">价格</th>
			<th style="width:5%">重量</th>
			<th style="width:5%">库存</th>
			<th style="width:5%">状态</th>
			<th >操作</th>
		</tr>
		<c:forEach items="${list2 }" var="content" varStatus="in">
			<tr>
				<td align="center">${in.index+1 }</td>
				<td align="center">${content.title }</td>
				<td align="center">${content.description }</td>
				<td align="center">${content.url }</td>
				<td align="center"><a title="点击看原图" href="${content.pic }"><img style="width:50px;height:50px;" src="${content.pic }" /></a></td>
				<td align="center">
					<j:dateFormat date="${content.createdate }"/>
				</td>
				<td align="center">${content.price }</td>
				<td align="center">${content.weight }</td>
				<td align="center">${content.num }</td>
				<td align="center">${content.status==1?"正常":"废弃" }</td>
				<td align="center">
	              	<j:a href="${pageContext.request.contextPath }/content.action?method=editUI&&id=${content.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
	                <j:a href="${pageContext.request.contextPath }/content.action?method=enable&&id=${content.id}&&contentCategoryId=${contentCategoryId}"><div class="modify" title="启用">&#xe63b;启用</div></j:a>
                	<j:a href="${pageContext.request.contextPath }/content.action?method=disable&&id=${content.id}&&contentCategoryId=${contentCategoryId}"><div class="modify" title="禁用">&#xe63b;禁用</div></j:a> 
	            </td>
			<tr>
		</c:forEach>
		<!-- <tr>
			<td align="center">1</td>
			<td align="center">轮播图</td>
			<td align="center"></td>
			<td align="center"></td>
			<td align="center"><img src="#" /></td>
			<td align="center">2019-12-12 12:12:12</td>
			<td align="center">2019-12-12 12:12:12</td>
			<td align="center">2019-12-12 12:12:12</td>
			<td align="center">2019-12-12 12:12:12</td>
			<td align="center">2019-12-12 12:12:12</td>
			<td align="center">
                <a href="modify.jsp"><div class="modify" title="修改">&#xe63b;修改</div></a>
                <div class="del" title="废弃">&#xe63b;废弃</div>
                <div class="del" title="删除">&#xe792;删除</div>
            </td>
		<tr> -->
       <tr>
			<td colspan="11" valign="middle" style="background:#F6F6F6">
				<j:a href="javascript:add()"><div class="add" title="添加">&#xe607;添加</div></j:a>
				<script type="text/javascript">
					function add(){
						var id = $("#select1 option:selected").val();
						location.href="${pageContext.request.contextPath }/content.action?method=addUI&&contentCategoryId="+id;
					}
				</script>
			</td>
		</tr>
	</table>
</body>
</html>
