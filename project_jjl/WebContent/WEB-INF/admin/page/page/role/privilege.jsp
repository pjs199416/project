<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>权限管理页面</title>
		<link href="${pageContext.request.contextPath }/css/admin/right.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath }/css/admin/privilege.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/z-tree/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/z-tree/jquery.ztree.all-3.5.min.js"></script>
	</head>
	<body>
		<div id="right_font">
			<div></div> 
			您现在所在的位置：权限信息 &rarr; <span>权限管理</span>
		</div>
		<div id="privilege">
			
			<ul id="treeDemo" class="ztree"></ul>
			<div class="add-page-btn" style="margin:100px auto 0;">
				<a class="save" href="javascript:save()">保存</a>
				<a class="back" href="javascript:back()">返回</a>
				<script type="text/javascript">
					function save() {
						var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
						var nodes = treeObj.getCheckedNodes(true);
						var privilegeIds = [];
						for (var i = 0; i < nodes.length; i++) {
							privilegeIds.push(nodes[i].id);
						}
						//console.log(privilegeIds);
						location.href = "${pageContext.request.contextPath}/role.action?method=privilegeSave&roleId="+${id}+"&privilegeIds="+privilegeIds.join(",");
					} 
					
					function back() {
						location.href="${pageContext.request.contextPath}/role.action";
					}
				</script>
			</div>
		</div>
	
	</body>
</html>
<SCRIPT type="text/javascript">

		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		/* var zNodes =[
			{ id:1, pId:0, name:"系统管理", open:true},
			{ id:2, pId:0, name:"数据字典管理", open:true},
			{ id:3, pId:0, name:"个人信息", open:true},
			{ id:4, pId:0, name:"首页内容管理", open:true},
			{ id:5, pId:0, name:"商品管理", open:true},
			{ id:6, pId:0, name:"订单管理", open:true},
			{ id:7, pId:1, name:"用户管理"},
			{ id:8, pId:1, name:"角色管理"},
			{ id:9, pId:2, name:"数据字典管理"},
			{ id:10, pId:3, name:"个人信息查看"},
			{ id:11, pId:3, name:"修改密码"}
		]; */
		$(document).ready(function(){
			$.ajax({
				url:'${pageContext.request.contextPath}/role.action',//访问路径
				type:'post',//提交方式
				data:{"method":"privilege",id:${id}},
				dataType:'json',//以什么格式
				success:function(data){
					
					$.fn.zTree.init($("#treeDemo"), setting, data);
				}
			})
			
			
			
		});
	
</SCRIPT>