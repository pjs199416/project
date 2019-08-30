<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>家家乐粮油商城</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/normalize.css"/>
	<!-- base里面只写公共样式 这对网页头部 尾部样式-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/front/base.css" />
	<!-- 提交订单信息 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/trade.css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
	<style type="text/css">
		#event_addr_list .current{
			border:2px solid red;
		}
		#event_addr_list i .current{
			border:none;
			background: #999999;
		}
	</style>
</head>
<body>
	<div id="addr">
			<div class="tit">
				<span>编辑收获信息</span>
				<i style="cursor: pointer;" onclick="closeAddr();">x</i>
				<script type="text/javascript">
					function closeAddr() {
						//location.href="${pageContext.request.contextPath}/afterLogin/addr.action?method=addrUI";
						history.back();
					}
				</script>
			</div>
			<div class="content">
				<div class="addr_input">
					<span>所在地区</span>
					<select id="provinceId" onchange="getCity()">
						<option value="${addr.province }"></option>
					</select>
					<select id="cityId" onchange="getDistrict()">
						<option  value="${addr.city }"></option>
					</select>
					<select id="districtId">
						<option value="${addr.district }"></option>
					</select>
				</div>
				<script type="text/javascript">
					$(function() {
						$.ajax({
							url:"${pageContext.request.contextPath}/afterLogin/addr.action",
							type:"post",
							data:{"method":"getProvinceAll"},
							dataType:"json",
							success:function(data){
								//alert(data)
								var options = [];
								for (var i = 0; i < data.length; i++) {
									options.push("<option value=\""+data[i][0]+"\">"+data[i][1]+"</option>")
								}
								$("#provinceId").html(options.join(""));
								getCity();
							}
						})
					});
					function getCity() {
						$(function() {
							$.ajax({
								url:"${pageContext.request.contextPath}/afterLogin/addr.action",
								type:"post",
								data:{"method":"getCityAll","provinceId":$("#provinceId").val()},
								dataType:"json",
								success:function(data){
									//alert(data)
									var options = [];
									for (var i = 0; i < data.length; i++) {
										options.push("<option value=\""+data[i][0]+"\">"+data[i][1]+"</option>")
									}
									$("#cityId").html(options.join(""));
									getDistrict()
								}
							})
						});
					}
					
					function getDistrict() {
						$.ajax({
							url:"${pageContext.request.contextPath}/afterLogin/addr.action",
							type:"post",
							data:{"method":"getDistrict","cityId":$("#cityId").val()},
							dataType:"json",
							success:function(data){
								var options = [];
								for (var i = 0; i < data.length; i++) {
									options.push("<option value=\""+data[i][0]+"\">"+data[i][1]+"</option>")
								}
								$("#districtId").html(options.join(""));
							}
						})
					}
				</script>
				
					<div class="addr_input">
						<input type="hidden" name="id" value="${addr.id }">
						<span>详细地址</span>
						<input class="addr_xxx" type="text" name="description" value="${addr.description }"/>
					</div>
					<div class="addr_input">
						<span>收货人</span>
						<input class="addr_xxx" type="text" name="shName" value="${addr.shName }"/>
					</div>
					<div class="addr_input">
						<span>手机号</span>
						<input class="addr_xxx" type="text" name="shPhone" value="${addr.shPhone }" />
					</div>
				</div>
				<input onclick="saveAddr();" type="submit"  value="保存收货人信息"/>
			<script type="text/javascript">
				function saveAddr() {
					$.ajax({
						url:"${pageContext.request.contextPath}/afterLogin/addr.action",
						type:"post",
						data:{"method":"editAddr","id":$("input[name='id']").val(),
							"province":$("#provinceId option:selected").html(),
							"city":$("#cityId option:selected").html(),
							"district":$("#districtId option:selected").html(),
							"description":$("input[name='description']").val(),
							"shName":$("input[name='shName']").val(),
							"shPhone":$("input[name='shPhone']").val()
							},
						success:function(data){
							closeAddr();
						}
					});
				}
			</script>
		</div> 
	
</body>
</html>