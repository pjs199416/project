<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>数据字典添加页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：数据字典信息 &rarr; <span>添加数据字典类别信息</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加数据字典类别信息</div>
			<form id="formId" action="${pageContext.request.contextPath }/dataDic.action" method="post">
				<input type="hidden" name="method" value="add">
				<input type="hidden" name="dataDicTypeId" value="${dataDicType.id }">
				<div class="add-page-form">
					<div class="add-page-input">
						<span>类别项目名称</span><input type="text" disabled value="${dataDicType.typeName }" />
					</div>
					<div class="add-page-input">
						<span>类别项目值</span><input type="text" name="item_name" value="" />
					</div>
					<div class="add-page-input">
						<span>状态</span>
						<i>启用</i><input type="radio" name="status" value="1">
						<i>禁用</i><input type="radio" name="status" value="0">
					</div>
				</div>
			</form>
			<div class="add-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function save() {
						$("#formId").submit();
					}
					function back() {
						location.href = "${pageContext.request.contextPath }/dataDic.action";
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
