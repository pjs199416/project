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
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
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
		
		<!-- 家家乐顶部 -->
		<div id="top">
			<div class="w">
				<div class="fl">
					<span>欢迎${sessionScope.frontUser.name }来家家乐</span>
					<c:choose>
						<c:when test="${sessionScope.frontUser != null }">
							<a href="${pageContext.request.contextPath }/loginAndregist.action?method=showExit">安全退出</a>
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath }/loginAndregist.action?method=loginUI">请登录</a>
						</c:otherwise>
					</c:choose>
					<a href="${pageContext.request.contextPath }/loginAndregist.action?method=registUI">免费注册</a>
				</div>
				<div class="fr">
					<a href="${pageContext.request.contextPath}/cart.action?method=cartUI">购物车</a>
					<a href="${pageContext.request.contextPath}/afterLogin/orders.action?method=ordersList">我的订单</a>
				</div>
			</div>
		</div>
		<!-- 家家乐搜索部分 -->
		
		<!-- 家家乐搜索部分 -->
		
		<div id="search" class="w">
			<div class="logo fl"></div>
			<div id="event-category" class="product-class fl">
				<a href="#">全部商品</a>
				<!-- <a href="#">精致粮油</a>
				<a href="#">上等米面</a>
				<a href="#">绝味调料</a>
				<a href="#">新鲜水果</a>
				<a href="#">天天蔬菜</a> -->
			</div>
			<script type="text/javascript">
				 $(function() {
					$.ajax({
						url:'${pageContext.request.contextPath}/category.action',
						type:'post',
						data:{'method':'showItemCategoryList'},
						dataType:'json',
						success:function(data){
							//把数据绑定到指定的页面
							var eventCategory = $("#event-category");
							//遍历商品分类数据
							for (var i = 0; i < data.length; i++) {
								//获得一个商品分类对象创建商品分类节点
								var itemCategory = data[i];
								var category = $("<a href=\"${pageContext.request.contextPath}/item.action?method=showItemList&itemCategoryId="+itemCategory.id+"\">"+itemCategory.name+"</a>");
								//把节点绑定到分类导航中
								eventCategory.append(category);
							}
						}
					})
				}); 
			</script>
			<div class="product-search fr">
				<input type="text"  />
				<input type="button" value="搜索"/>
			</div>
		</div>
		
		<div class="trade w">
			<h2>填写并核对订单信息</h2>
			<div class="trade_content">
				<div class="trade_minute">
					<div class="tit">
						<h3>收货人信息</h3><a href="javascript:addAddr();">新增地址</a>
					</div>	
					<ul id="event_addr_list">
						<!-- <li class="current">
							<span class="name">陈磊</span>
							<span>&nbsp;陈磊 &nbsp;河南 &nbsp; 郑州市&nbsp; 金水区 &nbsp; 黑朱庄小区1号楼五单元&nbsp; 手机号:13691161423</span>
							<i>默认地址</i>
						</li> -->
					</ul>
					<script type="text/javascript">
						$(function() {
							$.ajax({
								url:"${pageContext.request.contextPath}//afterLogin/addr.action",
								type:"post",
								data:{"method":"getAddrAll"},
								dataType:"json",
								success:function(data){
									var lis = [];
									for (var i = 0; i < data.length; i++) {
										var addr = data[i];
										lis.push("<li>");
										lis.push("<span id=\""+addr.id+"\"  onclick=\"useAddr("+addr.id+")\" class=\"name\">"+addr.shName+"</span>")
										lis.push("<span>&nbsp;"+addr.shName+" &nbsp;<span class=\"pcd\">"+addr.province+" "+addr.city+" "+addr.district+"  "+addr.description+" </span> 手机号:<span class=\"phone\">"+addr.shPhone+"</span></span>");
										lis.push("<i>");
										if (addr.status==1) {
											lis.push("<a class=\"current\" href=\"#\">默认地址</a>");
										}
										if (addr.status==0) {
											//lis.push("<a  href=\"javascript:defaultAddr("+addr.id+")\">设为默认地址</a>");
											lis.push("<a  href=\"javascript:defaultAddr("+addr.id+")\">设为默认地址</a>");
										}
										lis.push("<a href=\"javascript:updateAddr("+addr.id+")\">编辑</a>");
										lis.push("<a href=\"javascript:delAddr("+addr.id+")\">删除</a>");
										lis.push("</i>");
										lis.push("</li>");
									}
									$("#event_addr_list").html(lis.join("")); 
								}
							});
						});
						
						//默认地址设置
						/*  function defaultAddr(id) {
							location.href="${pageContext.request.contextPath}/afterLogin/addr.action?method=defaultAddr&id="+id;
							
						} */
						
						function defaultAddr(id) {
							//alert(id);
							$.ajax({
								url:"${pageContext.request.contextPath}/afterLogin/addr.action",
								type:"post",
								data:{"method":"defaultAddr","id":id},
								success:function(data){
									closeAddr();
									location.reload();
								}
							})
						}
						//修改地址信息
						function updateAddr(id) {
							location.href="${pageContext.request.contextPath}/afterLogin/addr.action?method=updateAddrUI&id="+id;
						}
						
						//删除地址 信息
						function delAddr(id) {
							$.ajax({
								url:"${pageContext.request.contextPath}/afterLogin/addr.action",
								type:"post",
								data:{"method":"deleteAddr","id":id},
								success:function(data){
									closeAddr();
									location.reload();
								}
							})
							//location.href="${pageContext.request.contextPath}/afterLogin/addr.action?method=deleteAddr&id="+id;
							
						}
						
						function useAddr(id) {
							$("#event_addr_list li .name").removeClass("current");
							$("#"+id).addClass("current");
							$("#event_addr_list li").hide();
							$("#event_addr_list span[class='name current']").parent().show();
							//使用地址后将该地址信息写入到下边
							var shName = $("#"+id).html();
							var shAddr = $("#"+id).parent().find(".pcd").html();
							var shPhone = $("#"+id).parent().find(".phone").html();
							$("#orders_Addr").html(shAddr);
							$("#orders_shName").html(shName);
							$("#orders_shPhone").html(shPhone);
						}
						function showAddrAll(){
							$("#event_addr_list li").show();
						}
					</script>
					<a href="javascript:showAddrAll();">更多地址︾</a>
				</div>
				<div class="trade_des">
					<h3>支付方式</h3>
					<div class="pay">
						<span class="current">支付宝</span>
						<span>微信</span>
						<span>银行转账</span>
						<span>货到付款</span>
					</div>
				</div>
				<div class="songhuo_qingdan">
					<h3>送货清单</h3>
					<c:forEach items="${list }" var="ordersItem">
						<div class="qingdan">
							<input type="hidden" name="itemId" value="${ordersItem.item.id }">
							<img src="${ordersItem.item.pic }"  />
							<span class="title">${ordersItem.item.title }</span>
							<span class="price">${ordersItem.item.price }￥</span>
							<span class="num">x${ordersItem.num }</span>
							<span class="num">${ordersItem.subtotal }￥</span>
						</div>
					</c:forEach>
				</div>
				
			</div>
			
			<div class="pay_mes w">
					<div class="des">
						<div class="money">应付金额：<i>￥${total }</i></div>
						<div>寄送至:<i id="orders_Addr"> </i></div>
						<div>收货人:<i id="orders_shName"> </i></div>
						<div>手机号:<i id="orders_shPhone"> </i></div>
					</div>
			</div>
			<button onclick="submitOrder()" class="submit">提交订单</button>
		</div>
		<script type="text/javascript">
				function submitOrder() {
					var ids = [];
					var itemIds = $("input[name='itemId']");
					for (var i = 0; i < itemIds.length; i++) {
						ids.push($(itemIds[i]).val());
					}
					var shAddr = $("#orders_Addr").html()+"";
					var shName = $("#orders_shName").html();
					var shPhone = $("#orders_shPhone").html();
					location.href="${pageContext.request.contextPath}/afterLogin/orders.action?method=submitOrders&&ids="+ids.join(",")+"&&shAddr="+shAddr+"&&shName="+shName+"&&shPhone="+shPhone;
				}
			</script>

		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- 地址 弹框 -->
		<div id="addr" style="display: none;">
			<div class="tit">
				<span>新增收获信息</span>
				<i style="cursor: pointer;" onclick="closeAddr();">x</i>
			</div>
			<div class="content">
				<div class="addr_input">
					<span>所在地区</span>
					<select id="provinceId" onchange="getCity()">
						<!-- <option value="">河南省</option> -->
					</select>
					<select id="cityId" onchange="getDistrict()">
						<!-- <option>郑州市</option> -->
					</select>
					<select id="districtId">
						<!-- <option >金水区</option> -->
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
						<span>详细地址</span>
						<input class="addr_xxx" type="text" name="description" />
					</div>
					<div class="addr_input">
						<span>收货人</span>
						<input class="addr_xxx" type="text" name="shName" />
					</div>
					<div class="addr_input">
						<span>手机号</span>
						<input class="addr_xxx" type="text" name="shPhone" />
					</div>
				</div>
				<input onclick="saveAddr();" type="submit"  value="保存收货人信息"/>
			<script type="text/javascript">
				function saveAddr() {
					$.ajax({
						url:"${pageContext.request.contextPath}/afterLogin/addr.action",
						type:"post",
						data:{"method":"getSaveAddr",
							"province":$("#provinceId option:selected").html(),
							"city":$("#cityId option:selected").html(),
							"district":$("#districtId option:selected").html(),
							"description":$("input[name='description']").val(),
							"shName":$("input[name='shName']").val(),
							"shPhone":$("input[name='shPhone']").val()
							},
						success:function(data){
							if (data == "1") {
								closeAddr();
								location.reload();
							}
						}
					});
				}
			</script>
		</div>
		<!--modal窗口-->
		<div id="modal"></div>
		
		<script type="text/javascript">
			/*
			 1.默认地址隐藏
			 2.点击按钮让地址显示 
			 3.关闭按钮让地址隐藏 
			 4.如果地址显示 窗口为模态窗口
			 * */ 
			function addAddr(){
				$("#addr").show();
				$("#modal").show();
				$("#addr").css("z-index",Number($("#modal").css("z-index"))+1);
				document.body.setAttribute("scroll","no");
				document.body.style.overflow="hidden"
			}
			function closeAddr(){
				$("#addr").hide();
				$("#modal").hide();
				document.body.setAttribute("scroll","yes");
				document.body.style.overflow="visible"
			}
		</script>
		
		
	</body>
</html>
