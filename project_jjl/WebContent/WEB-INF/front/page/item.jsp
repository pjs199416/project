<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE   html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>家家乐粮油商城</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/front/normalize.css"/>
		<!-- base里面只写公共样式 这对网页头部 尾部样式-->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/front/base.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/front/item.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/front/index.css"/>
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
		
		
		
		<div id="product-nav">
			<div class="w">
				<a href="#">${item.itemCategory.name }</a>
			</div>
		</div>
		
		<div id="product-content" class="w">
			<div class="product-left fl">
				<img src="${item.pic}" />
			</div>
			<div class="product-r fl">
				<p class="tit">${item.title }</p>
				
				<p class="des">${item.description }</p>
				<span>价格:${item.price }￥</span><br />
				<i>规格:${item.weight }g</i><br />
				<span>库存:${item.num }份</span><br />
				<div class="add-car">
					<button id="btn_event1">-</button><input id="input_event" type="text" value="1"/><button id="btn_event2">+</button>
					<button id="btn_event3" class="addbtn">加入购物车</button>
					<button id="btn_event4" class="addbtn">直接购买</button>
					<script type="text/javascript">
						/*
						 1.input value值默认值1 
						 2.当离开input value输入框时 ，如果不是number 就是 赋值1 
						 3.如果赋值过大 给提示 ，并且赋值最大150 
						 4.如果number<1 赋值1 
						 * */
						var $input = $("#input_event");
						//1.input离开焦点事件 。
						$input.blur(function(){
							//创建正则对象
							var reg = new RegExp(/^[1-9]{1}[0-9]*$/);
							var value = $(this).val();
							//判断数值是否满足3位number 
							var tag = reg.test(value);
							if(tag){
								value = Number(value);
								if(value<1){
									$(this).val(1);
								} else if(value>150){
									//提示
									$(this).val(150);
								}
							}else {
								//错误提示 
								$(this).val(1);
							}
							
						});
						
						/*
						 * 1.当点击btn_event1 按钮时 获取input属性值 并 减1 赋值给inputvalue属性值 。
						 * 2.当input value属性值 <1 的时候就是1
						 * */
						$("#btn_event1").click(function(){
							//1.获得属性值
							var value = $input.val();
							value--;
							if(value<1){
								value=1;
							}
							$input.val(value);
						});
						
						/*
						 * 1.当点击btn_event2按钮时 获取input属性值 并 +1 赋值给inputvalue属性值 。
						 * 2.当input value属性值 >150 的时候就是150
						 * */
						$("#btn_event2").click(function(){
							//1.获得属性值
							var value = $input.val();
							value++;
							if(value>150){
								value=150;
							}
							$input.val(value);
							
						});
						 
						 /*
							点击购物车按钮提交商品id,以及购买的数量
						*/
						$("#btn_event3").click(function() {
							//alert("aaaa");
							//1.获取商品id
							var id = ${item.id};
							//2.获取购物车商品的数量
							var num = $("#input_event").val();
							location.href="${pageContext.request.contextPath}/cart.action?method=addCartUI&id="+id+"&num="+num;
						});
					</script>
				</div>
			</div>
		</div>
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
