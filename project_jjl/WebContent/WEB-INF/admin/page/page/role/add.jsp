<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色添加页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：角色信息 &rarr; <span>添加角色</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加角色</div>
			<form id="formId" action="${pageContext.request.contextPath}/role.action" method="post">
				<input type="hidden" name="method" value="add">
				<div class="add-page-form">
					<div class="add-page-input">
						<span>角色名称</span><input type="text" name="name"/>
					</div>
					
					<div class="add-page-input">
						<span>角色说明</span><textarea name="description"></textarea>
					</div>
				</div>
			</form>
			<div class="add-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function back() {
						location.href = "${pageContext.request.contextPath}/role.action";
					}
					function save() {
						$("#formId").submit();
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
