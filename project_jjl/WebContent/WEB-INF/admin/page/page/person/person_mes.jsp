<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/admin/person.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
		<script language="javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
	</head>
	<body>
		<div id="right_font">
		<div></div> 
		您现在所在的位置：个人信息 &rarr; <span>查看个人信息</span>
	</div>
		<div id="show-mes">
			<div class="left">
				<div class="item">
					<div class="tit">用户名</div>
					<div class="content">${sessionScope.loginUser.name }</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">角色</div>
					<div class="content">
						<c:if test="${sessionScope.loginUser.name.equals('admin') }">
							超级管理员
						</c:if>
						<c:forEach items="${sessionScope.loginUser.list }" var="role">
							${role.name }
						</c:forEach>
					</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">手机号</div>
					<div class="content">${sessionScope.loginUser.phone }</div>
					<div style="clear:both;"></div>
				</div>
				<div class="item">
					<div class="tit">头像</div>
					<div class="content">
						<input type="hidden" name="pic" value="${sessionScope.loginUser.pic }">
						<a id="upload" href="javascript:upload()">&#xe601;<i>上传</i></a>
					</div>
					<div style="clear:both;"></div>
				</div>
			</div>
			<div class="right">
				<c:if test="${sessionScope.loginUser.pic!=null }">
				<img id="upload-img" src="${sessionScope.loginUser.pic }"  />
				</c:if>
				<c:if test="${sessionScope.loginUser.pic==null }">
				<img id="upload-img" src="${pageContext.request.contextPath }/imgs/admin/user.gif"  />
				</c:if>
			</div>
			
			<div style="clear:both;"></div>
			<div class="add-page-btn" style="margin:20px auto 10px;">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="#">返回</a>
				<script type="text/javascript">
					function save() {
						var pic = $("input[name='pic']").val();
						location.href="${pageContext.request.contextPath}/preson.action?method=savePic&pic="+pic;
					}
				</script>
			</div>
		</div>
		
	</body>
</html>
<script>
	function upload(){
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
             
		});
	}
</script>