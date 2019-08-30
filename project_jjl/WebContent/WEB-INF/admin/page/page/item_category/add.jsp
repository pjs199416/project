<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品类别添加页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
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
		您现在所在的位置：商品类别信息 &rarr; <span>商品类别添加</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加商品类别</div>
			<form id="formId" action="${pageContext.request.contextPath}/category.action" method="post">
			<input type="hidden" name="method" value="add" />
				<div class="add-page-form">
					<div class="add-page-input">
						<span>分类名称</span><input type="text" name="name" />
					</div>
					<div class="add-page-input">
						<span>分类状态</span>
						<i>正常</i><input type="radio" name="status" value="1" />
						<i>废弃</i><input type="radio" name="status" value="0" />
					</div>
					<div class="add-page-input">
						<span>创建时间</span><input class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="createdate"/>
					</div>
				</div>
			</form>
			<div class="add-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				
				<script type="text/javascript">
					$(function() {
						 $("#formId").validate({
							//校验规则
							rules:{
								name:{
									required:true
								},
								createdate:{
									required:true
								}
							},
							//校验信息
							messages:{
								name:{
									required:'商品名称必填'
								},
								createdate:{
									required:'创建时间必填'
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
						location.href="${pageContext.request.contextPath }/category.action";
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
