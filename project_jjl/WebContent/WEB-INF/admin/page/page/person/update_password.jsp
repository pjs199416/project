<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
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
		您现在所在的位置：个人信息&rarr; <span>修改个人密码</span>
	</div>
		<div class="modify-page" style="margin:0 auto; width:70%;">
			<div class="modify-page-top">修改用户</div>
			<form id="formId" action="${pageContext.request.contextPath}/preson.action">
			<input type="hidden" name="method" value="edit">
			<div class="modify-page-form">
				<div class="modify-page-input">
					<span>请输入原密码</span><input id="pwd1" type="password" name="oldPwd"/><span id="msg" style="color: red;"></span>
				</div>
				<div class="modify-page-input">
					<span>请填写新密码</span><input id="pwd2" type="password" name="newPwd"/>
				</div>
				<div class="modify-page-input">
					<span>再次填写新密码</span><input type="password" name="newPwd2" />
				</div>
			</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				
				<script type="text/javascript">
					$(function() {
						 $("#formId").validate({
							//校验规则
							rules:{
								/* oldPwd:{
									required:true
								},
								newPwd:{
									required:true
								}, */
								newPwd2:{
									required:true,
									equalTo: "#pwd2"
								}
							},
							//校验信息
							messages:{
								/* oldPwd:{
									required:'必填'
								},
								newPwd:{
									required:'必填'
								}, */
								newPwd2:{
									required:'必填',
									equalTo: "两次密码输入不一致"
								}
							}
						 })
					});
				</script>
				
				<script type="text/javascript">
					
					/* function save() {
						$("#formId").submit();
					} */
				
					function save(){
						$.ajax({
							url:'preson.action',
							type:'post',
							data:{"method":"edit","oldPwd":$("#pwd1").val(),"newPwd":$("#pwd2").val()},
							success:function(data){
								if(data==1){
									window.parent.location.href="${pageContext.request.contextPath }/adminlogin.jsp"
								} else {
									$("#msg").html("原密码错误");
								}
							}
						});
					}
				</script>
				<a class="back" href="#">返回</a>
			</div>
		</div>
	
	</body>
</html>
