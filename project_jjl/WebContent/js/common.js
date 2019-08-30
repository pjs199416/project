function save(formId){
	document.getElementById(formId).submit();
}

function del(id,url){
	if(confirm("你确定删除吗？")){
		location.href=url+"&id="+id
	}
}