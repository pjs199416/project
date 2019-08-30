<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品添加页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
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
		您现在所在的位置：商品信息 &rarr; <span>商品添加</span>
	</div>
		<div class="add-page">
			<div class="add-page-top">添加商品</div>
			<button id="upload">上传图</button>
			<form id="formId" action="${pageContext.request.contextPath }/item.action" method="post" >
				<input id="form1" type="hidden" name="method" value="add">
				<div class="add-page-form">
					<div class="add-page-input">
						<span>商品分类</span>
						<select name="category_id">
							<c:forEach items="${list }" var="category">
								<option value="${category.id }">${category.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="add-page-input">
						<span>商品标题</span><input type="text" name="title"/>
					</div>
					<div class="add-page-input">
						<span>商品描述</span><input type="text" name="description"/>
					</div>
					<div class="add-page-input">
						<span>商品图片</span>
						
						<img id="upload-img" style="width:100px;height:100px;" />
						<input type="hidden" name="pic" value=""/>
						
					</div>
					<div class="add-page-input">
						<span>商品价格</span><input type="text" name="price"/>
					</div>
					<div class="add-page-input">
						<span>商品重量</span><input type="text" name="weight" />
					</div>
					<div class="add-page-input">
						<span>商品库存</span><input type="text" name="num" />
					</div>
					<div class="add-page-input">
						<span>使用状态</span>
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
