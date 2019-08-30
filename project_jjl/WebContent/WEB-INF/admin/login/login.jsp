<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<link href="${pageContext.request.contextPath }/css/admin/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div id="login">
	     <div id="top">
		      <div id="top_left"><img src="${pageContext.request.contextPath }/imgs/admin/login_03.gif" /></div>
			  <div id="top_center"></div>
		 </div>
		 <form id="form" action="#">
			 <input type="hidden" name="method" value="login" />
			 <div id="center">
			      <div id="center_left"></div>
				  <div id="center_middle">
				       <div id="user">手机号：
				         <input type="text" autocomplete="off" value="" name="email" />
				       </div>
					   <div id="password">密 码：
					     <input type="password" name="pwd" value="" />
					   </div>
					   <div id="code">验证码：
					     <input type="text" autocomplete="off" name="code" id="cod" onFocus="showCode();" onclick="showCode()" />
					     
					   </div>	   
					   <div id="btn">
							<img title="看不清请刷新"
							 src="${pageContext.request.contextPath }/imgs/admin/img.png"
							 onclick=""/>
							<a href="../page/main/main.jsp">登录</a>
					   </div>
						  
				  </div>
				  <div id="center_right"></div>		 
			 </div>
		 </form>
		 <div id="down">
		      <div id="down_left">
			      <div id="inf">
                       <span class="inf_text">版本信息</span>
					   <span class="copyright">家家乐综合管理系统 2019 v1.0</span>
			      </div>
			  </div>
			  <div id="down_center">
			  	<a href="forget.html">忘记密码！</a>
			  </div>		 
		 </div>
	</div>
</body>
</html>
