<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE   html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>家家乐粮油商城首页</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/front/normalize.css"/>
		<!-- base里面只写公共样式 这对网页头部 尾部样式-->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/front/base.css" />
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
		
		<div id="search-wrap"  class="w">
			<div class="left fl">高级搜索</div>
			<div class="right fl">
					<div class="search-item fl">
						<span>价格区间</span>
						<select id="event-price" name="price">
							<option>全部</option>
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
							<option>全部</option>
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
		
		<!-- 家家乐轮播图部分  -->
		<div id="lunbotu">
			<div class="jjl-lunbo w">
				<!--左右按钮-->
				<a class="left-btn btn" href="javascript:left();"><i>&#xe628;</i></a>
				<a class="right-btn btn" href="javascript:right();"><i>&#xe625;</i></a>
				<!-- 轮播圆圈 -->
				<ol id="event-circle" class="bottom_circle">
					<!-- <li ></li>
					<li ></li>
					<li class="current"></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li> -->
				</ol>
				<!-- 轮播图片 -->
				<ul id="event-imgs-hd" class="imgs-hd">
						<%-- <li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb6.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb2.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb3.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb1.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb2.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb3.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb1.jpg" alt="" /></a></li>
						<li><a href="#"><img src="${pageContext.request.contextPath}/imgs/front/lb2.jpg" alt="" /></a></li> --%>
				</ul>
			</div>
		</div>
		<!-- 轮播图jquery实现 -->
		<script type="text/javascript">
			var length;
			$.ajax({
				async:false,
				url:'${pageContext.request.contextPath }/content.action',
				type:'post',
				data:{'method':'showContent','name':'轮播图'},
				dataType:'json',
				success:function(data){
					length = data.length;
					var event_circle = $('#event-circle');
					var event_imgs_hd = $('#event-imgs-hd');
					for(var i=0;i<data.length;i++){
						var obj = data[i];
						var li = $("<li ></li>");
						event_circle.append(li);
						var imgli = $("<li><a href=\""+obj.url+"\"><img src=\""+obj.pic+"\"  /></a></li>");
						event_imgs_hd.append(imgli);
					}
				}
			});
		
			//1.定义全局变量 圆圈和图片的下标对应 
			var index = 0;
			/* 
			 * 1.当鼠标放到圆圈按钮上， 当前圆圈样式为实心白色 ，其他的为空心圆 
			 * 2.获取当前实心圆下标 赋值给全局变量index  
			 * 3.通过index 让对应index下标的图片显示。
			 * 4.1秒钟渐入该图片
			 * 5.1秒钟淡出其他图片 
			 * 
			 * */
			//1.获得圆圈元素对象
			var $circle = $("#lunbotu .bottom_circle li");
			var $liImgs = $("#lunbotu .imgs-hd li");
			//2.给圆圈加移动事件
			$circle.mousemove(function(){
				//3.当鼠标放到圆圈按钮上， 当前圆圈样式为实心白色 ，其他的为空心圆 
				$(this).addClass("current").siblings().removeClass("current"); 
				//4.获取当前实心圆下标 赋值给全局变量index  
				index = $(this).index();
				//5.通过index 让对应index下标的图片显示。 
				$liImgs.eq(index).stop().fadeIn(2500).siblings().fadeOut(2500);
			});
			
			/*
			 1.设置1s中自动播放
			 2.获取当前下标， ，自增操作  当下标超过最大下标， 设置下标值为 0 
			 3.设置下标顺序按钮 及图片显示
			 * */
			var time = setInterval(move,5000);
			function move(){
				index++;
				if(index==length){
					index=0;
				}
				//3.让当前下标的原型为实心圆 其他下标为空心圆
				$circle.eq(index).addClass("current").siblings().removeClass("current"); 
				//5.通过index 让对应index下标的图片显示。 
				$liImgs.eq(index).stop().fadeIn(2500).siblings().fadeOut(2500);
			}
			/*
			 1.当鼠标移入图片区是，停止自动播放 
			 2.当鼠标移出图片区  ，恢复自动播放
			 * */
			$("#lunbotu .imgs-hd li,#lunbotu .btn").hover(
				function(){
					clearInterval(time)
				},
				function(){
					time = setInterval(move,5000);
				}
			);
			/*
			 1.点击左边按钮  ，改变下标 
			 2.改变圆和图片样式
			 * */
			function left(){
				index--;
				if(index==-1){
					index=length-1;
				}
				//3.让当前下标的原型为实心圆 其他下标为空心圆
				$circle.eq(index).addClass("current").siblings().removeClass("current"); 
				//5.通过index 让对应index下标的图片显示。 
				$liImgs.eq(index).stop().fadeIn(2500).siblings().fadeOut(2500);
			};
			function right(){
				index++;
				if(index==length){
					index=0;
				}
				//3.让当前下标的原型为实心圆 其他下标为空心圆
				$circle.eq(index).addClass("current").siblings().removeClass("current"); 
				//5.通过index 让对应index下标的图片显示。 
				$liImgs.eq(index).stop().fadeIn(2500).siblings().fadeOut(2500);
			};
		</script>
		
		
		
		
		<div id="product-module">
			<!--精选有机蔬菜-->
			<div class="product-shucai w">
				<h2>精选有机蔬菜</h2>
				<div class="shucai-list">
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai2.jpg"/></a>
						<p>有机蔬菜套装</p>
						<span>￥188元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai3.jpg"/></a>
						<p>南野田园 贝贝南瓜</p>
						<span>￥29.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai4.jpg"/></a>
						<p>蔬果哆 新鲜蔬菜 湖北特产 洪湖藕尖 藕带 2.5斤装</p>
						<span>￥78.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai5.jpg"/></a>
						<p>有机汇 有机罗马生菜 新鲜蔬菜 沙拉菜 孕产食材 火锅食材 宝宝辅食 500g</p>
						<span>￥23.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai6.jpg"/></a>
						<p>小竹笋新鲜保鲜野生春笋 四川火锅笋子笋片 2kg</p>
						<span>￥49.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai7.jpg"/></a>
						<p>有机汇 有机西葫芦 茭瓜 角瓜 新鲜蔬菜 供港澳蔬菜基地</p>
						<span>￥12.5元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai8.jpg"/></a>
						<p>米乐果 洋葱 农家自种紫皮洋葱5斤 现挖大葱 新鲜蔬菜 </p>
						<span>￥12.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai9.jpg"/></a>
						<p>农大姐妹5斤现摘水果小黄瓜 迷你黄瓜水果小青瓜</p>
						<span>￥19.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai10.jpg"/></a>
						<p>板栗小南瓜 净重2.3kg-2.5kg 2-3粒新鲜蔬菜宝宝辅食粉糯香甜农家自种 精选5斤装 </p>
						<span>￥25.9元</span>
					</div>
				</div>
			</div>
			
			
			
			<!--放价热卖榜--> 
			<div class="product-hot w">
				<h2>放价热卖榜</h2>
				<div class="shucai-list">
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/hot1.jpg"/></a>
						<p>小牛凯西牛排套餐10片 澳洲进口手工微腌牛肉西冷菲</p>
						<span>￥209元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/hot2.jpg"/></a>
						<p>南野田园 杨梅 当季新鲜水果 现摘现发 顺丰空运 家家乐生鲜 福建东魁杨梅礼盒3斤装</p>
						<span>￥85.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/hot3.jpg"/></a>
						<p>长生花生油 特香压榨一级食用油植物油粮油8L特惠装 4Lx2桶 </p>
						<span>￥179元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/hot4.jpg"/></a>
						<p>碱不落 胚芽米 有机宝大米辅助食宝米东北大米香米新大米500g 618直降</p>
						<span>￥30.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/hot5.jpg"/></a>
						<p>米全 2018新米长粒香5kg 抓饭米有嚼劲 绿色生态大米 原产新疆米泉 真空大包装</p>
						<span>￥50.9元</span>
					</div>
					
				</div>
			</div>
			
			
			<!--优质粮油-->
			<div class="product-liangyou w">
				<h2>新鲜蔬菜</h2>
				<div class="shucai-list">
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
					<div class="item">
						<a href="#"><img src="${pageContext.request.contextPath}/imgs/front/shucai1.jpg"/></a>
						<p>青萝卜新鲜蔬菜 精品装2.5斤装</p>
						<span>￥14.9元</span>
					</div>
				</div>
			</div>
			
			
		</div>
		
		
		<!-- 页面尾部 -->
		<div class="copyright w">
			<!-- 版权链接 -->
			<p class="copyright-link">
				<a href="#">关于我们</a>
				<span>|</span>
				<a href="#">联系我们</a>
				<span>|</span>
				<a href="#">联系客服</a>
				<span>|</span>
				<a href="#">合作招商</a>
				<span>|</span>
				<a href="#">商家帮助</a>
				<span>|</span>
				<a href="#">营销中心</a>
				<span>|</span>
				<a href="#">手机粮油</a>
				<span>|</span>
				<a href="#">友情链接</a>
				<span>|</span>
				<a href="#">销售联盟</a>
				<span>|</span>
				<a href="#">风险监测</a>
				<span>|</span>
				<a href="#">隐私政策</a>
				<span>|</span>
				<a href="#">English Site</a>
				<span>|</span>
				<a href="#">Media  &amp; IR</a>
			</p>
			<!--版权详细信息-->
			<div class="copyright-info">
				<!--版权证书部分-->
				<div class="copyright-cert">
					<p>
						<a href="#">京公网安备 11000002000088号</a>
						<span class="mod_copyright_split">|</span>
						<span>京ICP证070359号</span>
						<span class="mod_copyright_split">|</span>
						<a href="#">互联网药品信息服务资格证编号(京)-经营性-2014-0008</a>
						<span class="mod_copyright_split">|</span>
						<span>新出发京零 字第大120007号</span>
					</p>
					<p>
						<span>互联网出版许可证编号新出网证(京)字150号</span>
						<span class="mod_copyright_split">|</span>
						<a href="#" >出版物经营许可证</a>
						<span class="mod_copyright_split">|</span>
						<a href="#" >网络文化经营许可证京网文[2014]2148-348号</a>
						<span class="mod_copyright_split">|</span>
						<span>违法和不良信息举报电话：4006561155</span>
					</p>
					<p>
						<span>Copyright&nbsp;©&nbsp;2004&nbsp;-&nbsp;2019&nbsp;&nbsp;家家乐JD.com&nbsp;版权所有</span>
						<span class="mod_copyright_split">|</span>
						<span>消费者维权热线：4006067733</span>
						<a href="#" class="mod_business_license" >经营证照</a>
						<span class="mod_copyright_split">|</span>
						<span>(京)网械平台备字(2018)第00003号</span>
						<span class="mod_copyright_split">|</span>
						<a href="#"  class="mod_business_license">营业执照</a>
					</p>
				</div>
				<div class="copyright-country">
					<p>
						<a href="#" ><i class="i1"></i>Global Site</a>
						<span>|</span>
						<a href="#" ><i class="i2"></i>Situs Indonesia</a>
						<span>|</span>
						<a href="#"><i class="i3"></i>Sitio de España</a>
						<span>|</span>
						<a href="#"><i class="i4"></i>เว็บไซต์ประเทศไทย</a>
					</p>
				</div>
				<!--子站-->
				<div class="copyright-subsites">
					<p>
						<span>家家乐旗下网站：</span>
						<a href="#">家家乐钱包</a>
						<span>|</span>
						<a href="#" >家家乐云</a>
					</p>
				</div>
			</div>
			
			<!--版权作家模块-->
			<p class="copyright-auth">
				<a class="auth_1" href="#">可信网站信用评估</a>
				<a class="auth_2" href="#">网络警察提醒你</a>
				<a class="auth_3" href="#">诚信网站</a>
				<a class="auth_4" href="#">中国互联网举报中心</a>
				<a class="auth_5" href="#">网络举报APP下载</a>
			</p>
		</div>
		
		
		<div class="adv">
			<a id="event-adv-a" href="#">
				<%-- <img id="event-img-con" src="${pageContext.request.contextPath}/imgs/front/hot1.jpg"/> --%>
				<img id="event-img-con" src="${pageContext.request.contextPath}/imgs/front/hot1.jpg"/>
			</a>
			<div id="event-close" class="close"></div>
		</div>
		<script>
			$(function(){
				$("#event-close").click(function(){
					$(".adv").hide();
				});
				
				$.ajax({
					url:'${pageContext.request.contextPath}/content.action',
					type:'post',
					data:{'method':'showContent','name':'广告图'},
					dataType:'json',
					success:function(data){
						var obj = data[0];
						var event_adv_a = $("#event-adv-a");
						var event_img_con =  $("#event-img-con");
						event_adv_a.attr({"href":obj.url});
						event_img_con.attr({"src":obj.pic});
					}
				});
			});
			
		</script>
	</body>
</html>
