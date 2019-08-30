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
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/front/itemlist.css" />
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
				<input id="event-title" name="title" type="text" value="${param.title }"  />
				<input id="event-search" type="button" value="搜索"/>
				<script type="text/javascript">
					$(function(){
						$("#event-search").click(function(){
							//1.获得商品分类id 
							var itemCategoryId = ${param.itemCategoryId};
							//2.获得价格区间
							var pricesection = $("#event-price option:selected").val();
							//3.获得重量区间
							var weightsection = $("#event-weight option:selected").val();
							//4.获得商品标题
							var title = $("#event-title").val();
							location.href="${pageContext.request.contextPath }/item.action?method=showItemList&&itemCategoryId="+itemCategoryId+"&&pricesection="+pricesection+"&&weightsection="+weightsection+"&&title="+title;
						});
						
					});
				</script>
			</div>
		</div>
		
		<div id="search-wrap"  class="w">
			<div class="left fl">高级搜索</div>
			<div class="right fl">
					<div class="search-item fl">
						<span>价格区间</span>
						<select id="event-price" name="price">
							<option value="">全部</option>
							<!-- <option>0-15￥</option>
							<option>16-20￥</option>
							<option>21-25￥</option> -->
						</select>
						<script type="text/javascript">
							$(function() {
								//查看数据字典项
								$.ajax({
									url:'${pageContext.request.contextPath }/dataDic.action',
									type:'post',
									data:{'method':'showDataItem','typecode':'1002'},
									dataType:'json',
									success:function(data){
										var price = $("#event-price");
										for(var i=0;i<data.length;i++){
											var obj = data[i];
											var option = $("<option value=\""+obj.item_name+"\">"+obj.item_name+"</option>");
											price.append(option);
										}
									}
								})
							});
						</script>
					</div>
					<div class="search-item fl">
						<span>重量范围</span>
						<select id="event-weight" name="weight">
							<option value="">全部</option>
							<!-- <option>0-15kg</option>
							<option>16-20kg</option>
							<option>21-25kg</option> -->
						</select>
						<script type="text/javascript">
							$(function() {
								//查看数据字典项
								$.ajax({
									url:'${pageContext.request.contextPath }/dataDic.action',
									type:'post',
									data:{'method':'showDataItem','typecode':'1003'},
									dataType:'json',
									success:function(data){
										var price = $("#event-weight");
										for(var i=0;i<data.length;i++){
											var obj = data[i];
											var option = $("<option value=\""+obj.item_name+"\">"+obj.item_name+"</option>");
											price.append(option);
										}
									}
								})
							});
						</script>
					</div>
			</div>
			<div style="clear:both;"></div>
		</div>
		
	
		
		
		<div id="product-module">
			<!--精选有机蔬菜-->
			<div class="product-shucai w">
				<h2>快来选购吧</h2>
				<div class="shucai-list">
					<c:forEach items="${list }" var="item">
						<div class="item">
							<a href="${pageContext.request.contextPath}/item.action?method=showItem&id=${item.id}"><img src="${item.pic }"/></a>
							<p>${item.title }</p>
							<span>￥${item.price }元</span>
						</div>
					</c:forEach>
				</div>
				
				
				<div class="page">
					<a  href="javascript:beforePage()">上一页</a>
					<c:forEach items="${septsList }" var="i">
						<c:choose>
							<c:when test="${currentPage==i }">
								<span style="color: red;">${i }</span>
							</c:when>
							<c:otherwise>
								<a href="javascript:getPage(${i })">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<a  href="javascript:afterPage();">下一页</a>
					第${currentPage }/${totalPage }页 &nbsp; &nbsp; &nbsp;
					总共${totalSize }条记录数   &nbsp; 每页显示:${pageSize }
				</div>
			</div>	
		</div>
		
		<script type="text/javascript">
			//上一页
			function beforePage() {
				//1.获取商品分类id
				var itemCategoryId = ${param.itemCategoryId};
				//2.获取价格区间
				var pricesection = $("#event-price option:selected").val();
				//3.获取重量区间
				var weightsection = $("#event-weight option:selected").val();
				//4.获取商品标题
				var title = $("#event-title").val(); 
				location.href="${pageContext.request.contextPath}/item.action?method=showItemList&currentPage=${currentPage-1}&itemCategoryId="+itemCategoryId+"&pricesection="+pricesection+"&weightsection="+weightsection+"&title="+title;
			}
			//中间页
			function getPage(i) {
				//1.获取商品分类id
				var itemCategoryId = ${param.itemCategoryId};
				//2.获取价格区间
				var pricesection = $("#event-price option:selected").val();
				//3.获取重量区间
				var weightsection = $("#event-weight option:selected").val();
				//4.获取商品标题
				var title = $("#event-title").val(); 
				location.href="${pageContext.request.contextPath}/item.action?method=showItemList&currentPage="+i+"&itemCategoryId="+itemCategoryId+"&pricesection="+pricesection+"&weightsection="+weightsection+"&title="+title;
			}
			//下一页
			function afterPage() {
				//1.获取商品分类id
				var itemCategoryId = ${param.itemCategoryId};
				//2.获取价格区间
				var pricesection = $("#event-price option:selected").val();
				//3.获取重量区间
				var weightsection = $("#event-weight option:selected").val();
				//4.获取商品标题
				var title = $("#event-title").val(); 
				location.href="${pageContext.request.contextPath}/item.action?method=showItemList&currentPage=${currentPage+1}&itemCategoryId="+itemCategoryId+"&pricesection="+pricesection+"&weightsection="+weightsection+"&title="+title;
			}
		</script>
		
		
		
		
		<!-- 页面尾部 -->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>
</html>
