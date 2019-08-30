<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/base.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/login.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}

/* #login-top span{
	vertical-align: top;
	font-size: 31px;
	padding:7px;
	line-height: 50px;
	color:rgba(0,0,0,0.6);
	letter-spacing: 5px;
	font-family: "楷体";
	font-style: italic;
} */


 </style>
</head>
<body>
	
	
	
	
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<div id="login-top" class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/imgs/front/logo.jpg" />
					<span>家家乐商城</span>
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/imgs/front/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						<li><a href="login.html">登录</a></li>
						<li><a href="register.html">注册</a></li>
						<li><a href="cart.html">购物车</a></li>
					</ol>
				</div>
			</div>
			<!--
            	时间：2015-12-30
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav style="width: 1871px;height: 50px;background: green;" class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<!-- <li class="active"><a href="#">全部商品<span class="sr-only">(current)</span></a></li> -->
								<li><a href="#">全部商品<span class="sr-only">(current)</span></a></li>
								<li><a href="#">精致粮油</a></li>
								<li><a href="#">上等米面</a></li>
								<li><a href="#">绝味调料</a></li>
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>

	<%
		//1.获取request中携带的cookie
		Cookie[] cookies = request.getCookies();
		//2.如果没有找到,则用户名就为空	
		String name = "";
		String pwd = "";
		if(cookies != null){
			for(Cookie cookie : cookies){
	    		if("name".equals(cookie.getName())){
	    			name = URLDecoder.decode(cookie.getValue(), "utf-8");
	    			
	    		}
	    		if("pwd".equals(cookie.getName())){
	    			pwd = cookie.getValue();
	    		}
	    	}
		}
		
		 String str = "checked='checked'";
	    if("".equals(name) && "".equals(pwd)){
	    	str = "";
	    }
	%>
<div class="container"  style="width:100%;height:460px;background:goldenrod url('${pageContext.request.contextPath}/imgs/front/lb22.jpg') no-repeat;">

<div class="row"> 
	<div class="col-md-7">
		
	</div>
		
<div class="col-md-5">
	<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
<!-- 	<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;"> -->
	<font>会员登录</font>USER LOGIN<div style="color: red;">${msg }</div>
	
		<div>&nbsp;</div>
		<form class="form-horizontal" action="${pageContext.request.contextPath}/loginAndregist.action" method="post">
		  <input type="hidden" name="method" value="showlogin">
		 <div class="form-group">
		    <label for="username" class="col-sm-2 control-label">用户名</label>
		    <div class="col-sm-6">
		     <input type="text" class="form-control" name="name" id="username" placeholder="请输入用户名" value="<%=name %>">
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
		    <div class="col-sm-6">
		      <input type="password" class="form-control" name="pwd" id="inputPassword3" placeholder="请输入密码" value="<%=pwd %>">
		    </div>
		  </div>
		   <div class="form-group">
		        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
		    <div class="col-sm-3">
		      <input type="text" class="form-control" name="vcode" id="inputPassword3" placeholder="请输入验证码">
		    </div>
		    <div class="col-sm-3">
		      <img src="vcode.action" onclick="fn1(this)"/>
		      <%-- <img src="${pageContext.request.contextPath}/imgs/front/captcha.jhtml"/> --%>
		    </div>
		  <!--验证码  -->
		  <script type="text/javascript">
		  	function fn1(obj) {
				obj.src="vcode.action?time="+new Date;
			}
		  </script>
		    
		  </div>
		   <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <div class="checkbox">
		        <label>
		          <input type="checkbox" name="auto" value="au"> 自动登录
		        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        <label>
		          <input type="checkbox" name="rename" value="on" <%=str %>>记住用户名
		        </label>
		      </div>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		    <input type="submit"  width="100" value="登录" name="submit" border="0"
		    style="background: url('${pageContext.request.contextPath}/imgs/front/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
		    height:35px;width:100px;color:white;">
		    </div>
		  </div>
		</form>
		<span>没有账号?</span><a href="${pageContext.request.contextPath }/loginAndregist.action?method=registUI">立即注册</a>
	</div>			
</div>
</div>
</div>	

	

		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
		
		
	</body>
</html>