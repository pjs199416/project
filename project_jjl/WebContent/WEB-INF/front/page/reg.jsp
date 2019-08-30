<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册页面</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/normalize.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/base.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/reg.css"/>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
		<style type="text/css">
			.error{
			color: red;
			float: left;
			}
		</style>
	</head>
	<body>
		
		<div id="reg">
			<img src="${pageContext.request.contextPath}/imgs/front/logo.jpg" class="logo" />
			<h1>注册家家乐账号</h1>
			<form id="formId" action="${pageContext.request.contextPath}/loginAndregist.action" method="post">
				<input type="hidden" name="method" value="showregist">
				<input type="text" name="name" placeholder="请输入用户名"/>
				<input type="text" name="phone" placeholder="请输入手机号"/>
				<input id="pwdId" type="password" name="pwd" placeholder="请输入密码"/>
				<input type="password" name="password" placeholder="再次确认密码"/>
				<input id="submitId" type="submit" value="注册"  />
			</form>
		</div>
		
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
							equalTo: "#pwdId"
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
		<!-- <script type="text/javascript">
			$(function() {
				$("#submitId").click(function() {
					location.href="${pageContext.request.contextPath}/loginAndregist.action?method=regist"
				});
			});
		</script> -->
		
		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
