<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户添加</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
		<style type="text/css">
			.error{color: red;}
		</style>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：用户管理 &rarr; <span>添加用户信息</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加用户</div>
			<form id="formId" action="${pageContext.request.contextPath}/user.action" method="post">
				<input type="hidden" name="method" value="add">
				<div class="add-page-form">
					<div class="add-page-input">
						<span>用户名</span><input type="text" name="name"/>
					</div>
					<div class="add-page-input">
						<span>角色</span>
						<c:forEach items="${list }" var="role" >
							<i>${role.name }</i><input type="checkbox" name="roleId" value="${role.id }"  />
						</c:forEach>
					</div>
					<div class="add-page-input">
						<span>手机号</span><input type="text" name="phone"/>
					</div>
					<div class="add-page-input">
						<span>密码</span><input id="pwd" type="password" name="pwd"/>
					</div>
					<div class="add-page-input">
						<span>密码确认</span><input type="password" name="password"/>
					</div>
					<div class="add-page-input">
						<span>状态</span>
						<i>正常</i><input type="radio" name="status" value="1"/>
						<i>废弃</i><input type="radio" name="status" value="0"/>
					</div>
				</div>
			</form>
			<div class="add-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function back() {
						location.href="${pageContext.request.contextPath}/user.action";
					}
					function save(){
						$("#formId").submit();
					}
				</script>
			</div>
		</div>
		
		<!-- 信息校验 -->
		<script type="text/javascript">
		jQuery.validator.addMethod("phone",function(value,element,param){
			var reg = new RegExp(/^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/);
			if (reg.test(value)) {
				return true;
			}else {
				return false;
			}
		}) 
			$(function() {
				//绑定校验插件
				$("#formId").validate({
					//校验规则
					rules:{
						name:{
							required:true
						},
						phone:{
							phone:true
						},
						pwd:{
							required:true
						},
						password:{
							required:true,
							equalTo: "#pwd"
						}
					},
					//校验信息
					messages:{
						name:{
							required:"姓名不能为空"
						},
						phone:{
							phone:"手机号格式不正确"
						},
						pwd:{
							required:"密码不能为空"
						},
						password:{
							required:"密码不能为空",
							equalTo: "两次密码输入不一致"
						}
					}
				});
			});
		</script>
		
	</body>
</html>
