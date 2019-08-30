<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页内容修改</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：首页内容&rarr; <span>修改信息</span>
	</div>
		<div class="modify-page">
			<div class="modify-page-top">修改${content.contentCategory.name }</div>
			<form id="form1" action="${pageContext.request.contextPath }/content.action" method="post">
				<input type="hidden" name="method" value="edit" /> 
				<input type="hidden" name="contentCategoryId" value="${content.contentCategory.id }" /> 
				<input type="hidden" name="id" value="${content.id }" /> 
			<div class="modify-page-form">
				<div class="modify-page-input">
					<span>内容标题</span><input type="text" name="title" value="${content.title }"  />
				</div>
				<div class="modify-page-input">
					<span>描述信息</span><textarea name="description">${content.description }</textarea>
				</div>
				
				<div class="modify-page-input">
					<span>链接地址</span><input type="text" name="url" value="${content.url }"  />
				</div>
				<div class="modify-page-input">
						<span>图片</span>
						<a id="upload" >&#xe601;</a>	
						<img style="width:50px;height:50px;" id="upload-img" src="${content.pic }"  />
						<input type="hidden" name="pic" value="${content.pic }" />
						<script>
							$("#upload").click(function(){
								$("#upload").upload({
									/* 上传路径  */
									 action: '${pageContext.request.contextPath }/upload.action',  
								     onComplete:function(data){
								    	if(data!=null && data!=""){
								    		$("#upload-img").attr("src",data);
								    		$("input[name='pic']").val(data);
								    	} else {
								    		alert("上传失败");
								    	}
								     }
								});
							});
						</script>
						
				</div>
				<div class="modify-page-input">
					<span>价格</span><input type="text" name="price" value="${content.price }"  />
				</div>
				<div class="modify-page-input">
					<span>重量</span><input type="text" name="weight" value="${content.weight }"  />
				</div>
				<div class="modify-page-input">
					<span>库存</span><input type="text" name="num" value="${content.num }" />
				</div>
				<div class="modify-page-input">
					<span>使用状态</span>
					<c:if test="${content.status==1 }">
						<i>正常</i><input type="radio" checked="checked" name="status" value="1" />
						<i>废弃</i><input type="radio" name="status" value="0" />
					</c:if>
					<c:if test="${content.status==0 }">
						<i>正常</i><input type="radio"  name="status" value="1" />
						<i>废弃</i><input type="radio" checked="checked" name="status" value="0" />
					</c:if>
				</div>
				<div class="modify-page-input">
					<span>创建日期</span><input value="${content.createdate }" class="Wdate" type="text" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" name="createdate"  />
				</div>
			
			</div>
			</form>
			<div class="modify-page-btn">
				<a class="save" href="javascript:save('form1')">保存</a>
				<a class="back" href="javascript:back();">返回</a>
				<script type="text/javascript">
					function back(){
						location.href="${pageContext.request.contextPath }/content.action";
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
