<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品修改页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js"></script>
		<style type="text/css">
			.error{color: red;}
		</style>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：商品信息 &rarr; <span>商品修改</span>
	</div>
		<div class="modify-page">
			<div class="modify-page-top">修改商品</div>
			<button id="upload" >上传图片</button>
			<form id="formId" action="${pageContext.request.contextPath }/item.action" method="post">
				<input type="hidden" name="method" value="edit">
				<input type="hidden" name="id" value="${item.id }">
				<div class="modify-page-form">
					<div class="modify-page-input">
						<span>商品分类</span>
						${item.itemCategory.name }
						<input   type="hidden" name="itemCategoryId" value="${item.itemCategory.id }"/>
					</div>
					<div class="modify-page-input">
						<span>商品标题</span><input type="text" name="title" value="${item.title }" />
					</div>
					<div class="modify-page-input">
						<span>商品描述</span><input type="text" name="description" value="${item.description }"/>
					</div>
					<div class="modify-page-input">
						<span>商品图片</span>
						<!-- <a id="upload" >&#xe601;</a> -->
						<img id="upload-img" src="${item.pic }" style="width:100px;height:100px;" />
						<input type="hidden" name="pic" value="${item.pic }" />
						
					</div>
					<div class="modify-page-input">
						<span>商品价格</span><input type="text" name="price" value="${item.price }"/>
					</div>
					<div class="modify-page-input">
						<span>商品重量</span><input type="text" name="weight" value="${item.weight }"/>
					</div>
					<div class="modify-page-input">
						<span>商品库存</span><input type="text" name="num" value="${item.num }"/>
					</div>
					<div class="modify-page-input">
						<span>使用状态</span>
						<c:if test="${item.status==1 }">
							<i>正常</i><input type="radio" checked name="status" value="1" />
							<i>废弃</i><input type="radio" name="status" value="0" />
						</c:if>
						<c:if test="${item.status==0 }">
							<i>正常</i><input type="radio" checked name="status" value="1" />
							<i>废弃</i><input type="radio" name="status" value="0" />
						</c:if>
					</div>
					<div class="modify-page-input">
						<span>创建时间</span><input class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="createdate" value="${item.createdate }"/>
					</div>
				</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
				$(function() {
					$("#formId").validate({
						//校验规则
						rules:{
							title:{
								required:true
							},
							description:{
								required:true
							},
							price:{
								required:true
							},
							weight:{
								required:true
							},
							num:{
								required:true
							},
							createdate:{
								required:true
							}
						},
						//校验信息
						messages:{
							title:{
								required:'标题必填'
							},
							description:{
								required:'描述必填'
							},
							price:{
								required:'价格必填'
							},
							weight:{
								required:'重量必填'
							},
							num:{
								required:'库存数量必填'
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
						location.href="${pageContext.request.contextPath }/item.action";
					}
				</script>
			</div>
		</div>
		<script type="text/javascript">
			$("#upload").click(function() {
				
				$("#upload").upload({
					//上传路径
					action:"${pageContext.request.contextPath }/upload.action",
					onComplete:function(data){
						if (data != null && data != "") {
							$("#upload-img").attr("src",data);
							$("input[name='pic']").val(data);
						}else {
							alert("上传失败")
						}
					}
				})
			})
		</script>
	</body>
</html>
