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
		<!-- 订单列表 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/front/orderlist.css"/>
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
					<a href="${pageContext.request.contextPath}/cart.action?method=cartUI">购物车</a>
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
		
		<div id="order_list_tit" class="w"><span>我的订单</span></div>
		<div id="order_list" class="w">
			<div class="list_tit">
				<span>订单详情</span>
				<span>收货人</span>
				<span>金额</span>
				<span>全部状态</span>
				<span>操作</span>
			</div>
			<c:forEach items="${sessionScope.ordersList }" var="orders">
				<div class="list_item">
					<div class="list_item_tit">
						<span>${orders.createdate }</span>
						<span>订单号:${orders.id }</span>
						<!-- <i>&#xe792;</i> -->
						<a href="javascript:delOrders('${orders.id }')"><i>&#xe792;</i></a>
						<script type="text/javascript">
							function delOrders(id) {
								//alert("获取了"+id)
								location.href="${pageContext.request.contextPath}/afterLogin/orders.action?method=deleteOrders&id="+id;
							}
						</script>
					</div>
					<div class="list_item_content">
						<div class="pro">
							<c:forEach items="${orders.list }" var="ordersItem">
								<div >
									<img src="${ordersItem.item.pic }"/>
									<span>${ordersItem.item.title }</span>
									<i>X ${ordersItem.num }</i>
								</div>
							</c:forEach>
						</div>
						<div class="shouhuoren"><span>${orders.shName }</span></div>
						<div class="shouhuojine"><span>在线支付<br/>金额:￥${orders.total }元</span></div>
						<div class="state">
							<span>
								<c:choose>
									<c:when test="${orders.status==0 }">
										未付款
									</c:when>
									<c:otherwise>
										已付款
									</c:otherwise>
								</c:choose>
								
							</span>
							<a href="#">订单详情</a>
						</div>
					</div>	
				</div>
			</c:forEach>
		</div>
		
		
		
		
		
		
		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
