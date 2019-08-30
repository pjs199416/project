<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>您的访问权限不足........</h1>
	<span id="spanId">3</span>s返回上一页
	<script type="text/javascript">
		var intervalId = setInterval(go,1000);
		function go() {
			
			var html = document.getElementById("spanId").innerHTML;
			html--;
			if (html<1) {
				clearInterval(intervalId);
				history.back();
			}else {
				document.getElementById("spanId").innerHTML = html;
			}
			
		}
	</script>
</body>
</html>