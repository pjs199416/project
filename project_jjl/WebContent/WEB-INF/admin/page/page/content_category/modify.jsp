<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页内容分类修改</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
		<style type="text/css">
			.error{color: red;}
		</style>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：首页内容分类 &rarr; <span>修改分类信息</span>
	</div>
		<div class="modify-page">
			<div class="modify-page-top">修改分类信息</div>
			<form id="formId" action="${pageContext.request.contextPath }/contentCategory.action" method="post">
				<input type="hidden" name="method" value="edit" />
				<input type="hidden" name="id" value="${contentCategory.id }" />
			<div class="modify-page-form">
				<div class="modify-page-input">
					<span>分类模块</span><input type="text" name="name" value="${contentCategory.name}" />
				</div>
				
				<div class="modify-page-input">
					<span>描述</span><textarea name="description">${contentCategory.description}</textarea>
				</div>
				
				<div class="add-page-input">
					<span>分类状态</span>
					<c:if test="${contentCategory.status==1 }">
						<i>正常</i><input type="radio" checked name="status" value="1" />
						<i>废弃</i><input type="radio" name="status" value="0" />
					</c:if>
					<c:if test="${contentCategory.status==0 }">
						<i>正常</i><input type="radio"  name="status" value="1" />
						<i>废弃</i><input type="radio" checked name="status" value="0" />
					</c:if>
				</div>
			</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back();">返回</a>
				
				<script type="text/javascript">
					$(function() {
						 $("#formId").validate({
							//校验规则
							rules:{
								name:{
									required:true
								},
								description:{
									required:true
								}
							},
							//校验信息
							messages:{
								name:{
									required:'内容名字必填'
								},
								description:{
									required:'内容描述必填'
								}
							}
						 })
					});
				</script>
				
				<script type="text/javascript">
					function save() {
						$("#formId").submit();
					}
					function back(){
						location.href="${pageContext.request.contextPath }/contentCategory.action";
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
