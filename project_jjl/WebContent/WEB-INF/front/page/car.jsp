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
		<!-- 购物车 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/car.css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
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
					<a href="${pageContext.request.contextPath}/afterLogin/orders.action?method=ordersList">我的订单</a>
				</div>
			</div>
		</div>
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
		
		
		<div class="car w">
			<h2>购物车</h2>
			<div class="car_list">
				<div class="car_title">
					<input type="checkbox" value=""  />全选 
					<span class="pro_name">商品</span>
					<span class="pro_price">单价</span>
					<span class="pro_num">数量</span>
					<span class="pro_total">小计</span>
					<span>操作</span>
				</div>
				<div class="car_item">
					<c:forEach items="${sessionScope.cart.map }" var="map"> 
						<div class="item">
							<c:choose>
								<c:when test="${map.value.checked==1 }">
									<input class="selectOne" type="checkbox" id="checked${map.value.item.id }"  onclick="updateCarItemChecked(${map.value.item.id })" name="cartItem" checked="checked" value="${map.value.item.id }"  />
								</c:when>
								<c:otherwise>
									<input class="selectOne" type="checkbox" id="checked${map.value.item.id }" onclick="updateCarItemChecked(${map.value.item.id })" name="cartItem"  value=""  />
								</c:otherwise>
							</c:choose>
							<img src="${map.value.item.pic }"  />
							<span class="pro_title">${map.value.item.title }</span>
							<span class="pro_price">${map.value.item.price }￥</span>
							<span class="pro_num">
								<button onclick="subNum(${map.value.item.id })">-</button>
								<input type="text" id="${map.value.item.id }" name="itemNum" onblur="updateCarItemNum(${map.value.item.id });" value="${map.value.num }"  />
								<button onclick="addNum(${map.value.item.id });">+</button>
							</span>
							<span class="pro_total">${map.value.subtotal }￥</span>
							<input onclick="deleCartItem(${map.value.item.id})" type="button" value="删除"  />
							
						</div>
					</c:forEach> 
					<script type="text/javascript">
						//删除购物车项
						function deleCartItem(id) {
							//alert("aaaa")
							location.href="${pageContext.request.contextPath }/cart.action?method=deleCartItem&id="+id;
						}
						//增添购物项数量
						function addNum(id){
							//++ js 
							var value = $("#"+id).val();
							value++;
							if(value>10){
								value=10;
							}
							$("#"+id).val(value);
							updateCarItemNum(id);
						}
						//减少购物项数量
						function subNum(id){
							//-- js
							var value = $("#"+id).val();
							value--;
							if(value<1){
								value=1;
							}
							$("#"+id).val(value);
							updateCarItemNum(id);
						}
						//修改购物项数目
						function updateCarItemNum(id){
							var num = $("#"+id).val();
							location.href="${pageContext.request.contextPath }/cart.action?method=updateCarItemNum&&id="+id+"&&num="+num;
						}
						//修改购物项选中的状态
						function updateCarItemChecked(id){
							var checked = $("#checked"+id).prop("checked");
							//alert("aaaa"+checked)
							if(checked){
								location.href="${pageContext.request.contextPath }/cart.action?method=updateCarItemChecked&&id="+id+"&&checked="+1;
							} else {
								location.href="${pageContext.request.contextPath }/cart.action?method=updateCarItemChecked&&id="+id+"&&checked="+0;
							} 
						}
						
					</script>
				<div class="car_sum">
					<c:choose>
						<c:when test="${sessionScope.checked==1 }">
							<input id="checkAll" checked="checked" type="checkbox" value=""  />全选
						</c:when>
						<c:otherwise >
							<input id="checkAll" type="checkbox" value=""  />全选
						</c:otherwise>
					</c:choose>
					<script type="text/javascript">
						//清空购物车
						function clearCart() {
							location.href="${pageContext.request.contextPath }/cart.action?method=clearCart";
						}
						//批量删除购物项
						function delBatchCarItem() {
							var cartItems =$("input[name='cartItem']:checked");
							var ids = [];
							for (var i = 0; i < cartItems.length; i++) {
								var obj = $(cartItems[i]);
								ids.push(obj.val());
							}
							location.href="${pageContext.request.contextPath }/cart.action?method=delBatchCarItem&ids="+ids
						}	
						
						//全选与全不选
						var $item = $(".selectOne");
						$("#checkAll").click(function(){
							var checked = $(this).prop("checked");
							$item.prop("checked",checked);
							//全选改变服务器状态
							if (checked) {
								location.href="${pageContext.request.contextPath }/cart.action?method=updateCarItemCheckedAll&checked="+1;
							}else {
								location.href="${pageContext.request.contextPath }/cart.action?method=updateCarItemCheckedAll&checked="+0;
							}
						});
						
						/* //function updateCarItemChecked(id) {
							//var checked = $("#checked"+id).prop("checked");
							$item.click(function() {
								var count= 0;
								//如果子选框没有选中,就让全选框不选中
								if(!this.checked){
									$("#checkAll").removeProp("checked");
								}else{
									
									//当前这个子选框选中,就遍历子选框,看所有子选框是否都被选中
									for(var i=0;i<$item.length;i++){
										//如果有一个子选框选中,就让其计数
										if($item[i].checked){
											count ++;
										}
									}
									//遍历完成后,判断count的大小和是否和自选框的个数一样
									if(count==$item.length){
										$("#checkAll").prop("checked",true);
									}
									
								}
							});
						//} */
					</script>
					
					<button onclick="delBatchCarItem()">删除选中商品</button>
					<button onclick="clearCart()">清理购物车</button>
					<span class="pro_number">已经全选择${cart.cartNum }件商品</span>
					<span class="pro_sum">总价:${cart.total }￥</span>
					<button onclick="toSubmitOrderUI();">去付款</button>
					<script type="text/javascript">
						function toSubmitOrderUI() {
							var cartItems =$("input[name='cartItem']:checked");
							var checkedIds = [];
							for (var i = 0; i < cartItems.length; i++) {
								var item = $(cartItems[i]);
								var id = item.attr("id").replace("checked","");
								checkedIds.push(id);
							}
							location.href="${pageContext.request.contextPath}/afterLogin/orders.action?method=toSubmitOrderUI&cartItemIds="+checkedIds.join(",");
						}
					</script>
				</div>
			</div>
		</div>
		
		
		
		
		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
