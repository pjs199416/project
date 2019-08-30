<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="/META-INF/my.tld" prefix="j" %>
<!DOCTYPE html >
<html>
<head>
<title>数据字典页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/admin/dataDictionary.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="right_font">
		<div></div> 
		您现在所在的位置：数据字典信息 &rarr; <span>数据字典查看</span>
	</div>
	<div id="data-dic">
		<div class="data-type">
			<div class="data-type-tit">&#xe6b6;<span>基础类别</span></div>
			<ul>
				<c:forEach items="${list }" var="dictType">
					<li><a href="${pageContext.request.contextPath }/dataDic.action?dataDicTypeId=${dictType.id}">${dictType.typeName }</a></li>
				</c:forEach>
				
				<!-- <li><a href="#">支付方式</a></li>
				<li><a href="#">商品重量</a></li>
				<li><a href="#">价格区间</a></li> -->
			</ul>
		</div>
		<div class="type-mes" >
			<div class="data-mes-tit">&#xe6b6;<span>类别信息</span></div>
			<div class="data-mes-btn">
				<button onclick="addUI()">&#xe607;添加</button>
				<script type="text/javascript">
					function addUI(){
						location.href="${pageContext.request.contextPath }/dataDic.action?method=addUI&dataDicTypeId=${dataDicTypeId}";
					}
				</script>
			</div>
			<div class="data-mes-data">
				<table class="table" style="margin:10px;">
					<tr>
						<th style="width:11%;">序号</th>
						<th style="width:15%;">类别</th>
						<th style="width:15%;">名称/值</th>
						<th style="width:10%">状态</th>
						<th >操作</th>
					</tr>
					<c:forEach items="${list2 }" var="dataItem" varStatus="in">
						<tr>
							<td align="center">${in.count }</td>
							<td align="center">${dataDicType.typeName }</td>
							<td align="center">${dataItem.item_name }</td>
							<td align="center">${dataItem.status==1?'启用':'禁用' }</td>
							<td align="center">
				                <j:a href="${pageContext.request.contextPath }/dataDic.action?method=editUI&dataDicTypeId=${dataDicTypeId}&id=${dataItem.id}"><div class="modify" title="修改">&#xe63b;修改</div></j:a>
								<j:a href="${pageContext.request.contextPath }/dataDic.action?method=enable&dataDicTypeId=${dataDicTypeId}&id=${dataItem.id}"><div class="modify" title="启用">&#xe63b;启用</div></j:a>
				               	<j:a href="${pageContext.request.contextPath }/dataDic.action?method=disable&dataDicTypeId=${dataDicTypeId}&id=${dataItem.id}"><div class="modify" title="禁用">&#xe63b;禁用</div></j:a>
				               	
				            </td>
						<tr>
					</c:forEach>
			        <!-- <tr>
						<td align="center">2</td>
						<td align="center">支付方式</td>
						<td align="center">支付宝支付</td>
						<td align="center">禁用</td>
						<td align="center">
							 <div class="enabled" title="启用">&#xe63b;启用</div>
			               	 <div class="disabled" title="废弃">&#xe63b;禁用</div>
			               	 <div class="del" title="删除">&#xe792;删除</div>
			                 <div class="modify" title="修改">&#xe63b;修改</div>
			               	 <div class="moveUp" title="上移">&#xe611;上移</div>
               				 <div class="moveDown" title="下移">&#xe613;上移</div>
			            </td>
					<tr> -->
					
				</table>
			</div>
		</div>
		<div style="clear:both;"></div>
	</div>
	
</body>
</html>
