<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>数据字典修改页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：数据字典信息 &rarr; <span>数据字典修改</span>
	</div>
		<div class="modify-page">
			<div class="modify-page-top">数据字典修改</div>
			<form id="formId" action="${pageContext.request.contextPath }/dataDic.action" method="post">
				<input type="hidden" name="method" value="edit">
				<input type="hidden" name="id" value="${dataItem.id }">
				<input type="hidden" name="dataDicTypeId" value="${dataDicType.id }">
				<div class="modify-page-form">
					<div class="modify-page-input">
						<span>类别项目名称</span>
						<input type="text" disabled value="${dataDicType.typeName }" />
					</div>
					
					<div class="modify-page-input">
						<span>类别项目值</span><input type="text" name="item_name" value="${dataItem.item_name }" />
					</div>
					<div class="modify-page-input">
						<span>状态</span>
						<c:if test="${dataItem.status==1 }">
							<i>启用</i><input type="radio" checked name="status" value="1">
							<i>禁用</i><input type="radio" name="status" value="0">
						</c:if>
						<c:if test="${dataItem.status==0 }">
							<i>启用</i><input type="radio"  name="status" value="1">
							<i>禁用</i><input type="radio" checked name="status" value="0">
						</c:if>
					</div>
				</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function save() {
						$("#formId").submit();
					}
					function back() {
						location.href="${pageContext.request.contextPath }/dataDic.action"
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
