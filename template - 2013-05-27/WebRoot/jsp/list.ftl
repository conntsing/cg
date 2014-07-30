<%@ page contentType="text/html;charset=${cg.getCharSet()}"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${cg.getModelName()} 列表</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
function goPage(pageNum) {
	document.getElementById("pageNo").value = pageNum;
	document.getElementById("${cg.getObjectName()}Form").submit();
}
//全选
function selectAll(vall,varray){ 
	var all = document.forms[0].elements[vall];
	 
	var array = document.forms[0].elements[varray];  
	if(array == null || typeof(array) == "undefined") return;

	if(all.checked){
	  for(i=0;i<array.length;i++)
		array[i].checked = true;
	}
	else{
	  for(i=0;i<array.length;i++)
		array[i].checked = false;
	}    
	
	if(isNaN(array.length) && all.checked)
	  array.checked = true;
	else if(isNaN(array.length) && !all.checked)
	  array.checked = false;
  }

//不全选
function selectReverse(vall,varray){  
  var sum = 0;
  var all = document.forms[0].elements[vall];
  var array = document.forms[0].elements[varray]; 
  if(array == null || typeof(array) == "undefined") return;
  for(i=0;i<array.length;i++){
	if(array[i].checked == false){
	  all.checked = false;
	}
	if(array[i].checked){
	  sum++;
	}
  }
  if(sum == array.length){
	all.checked = true;
  }
  if(isNaN(array.length) && array.checked)
	  all.checked = true;
	else if(isNaN(array.length) && !array.checked)
	  all.checked = false;
}
function del(){
	var items = document.getElementsByName("id");
	var c = 0;
	
	if(items==null || typeof(items) == "undefined" ){
		return false;
	}else{
		for(var i=0;i<items.length;i++){
			if(items[i].checked){
				c=1;
				break;
			}
		}
	}
	if(c==0){
		alert('请至少选择一项');
		return false;
	}
	if(!confirm('确认删除?')){
		return false;
	}
	document.forms[0].action="${r"${base}"}/admin/${cg.getObjectName()}/${cg.getObjectName()}Action!delete.${r"${actionExt}"}";
	document.forms[0].submit();
	
}
</script>
</head>
<body>
<div>
<%@ include file="/jsp/common/top.jsp"%>
<form name="${cg.getObjectName()}Form" id="${cg.getObjectName()}Form" method="post" action="${cg.getObjectName()}Action.${r"${actionExt}"}" onsubmit="return checkForm();">
<input type="hidden" name="pageNo" id="pageNo" value="${r"${"}requestScope.pageInfo.page${r"}"}" />
<input type="hidden" name="pageSize" id="pageSize" value="${r"${"}requestScope.pageInfo.pageSize${r"}"}" />
<table width="97%" cellspacing="8" cellpadding="0" align="center" border="1">
  <tr>
    <td valign="top" width="9%">${cg.getModelName()} 列表</td>
    <td>&nbsp;</td>
  </tr>
  <tr align="center">
  	<td width="4%"><input type="checkbox" id="ids" name="ids" onclick="selectAll('ids','id');"/></td>
  <#list cg.getFieldList() as field>    
	<#if field.getName() != "id">
	<td width="10%">${field.getComment()}</td>
	</#if>	
  </#list>
  <td width="10%">操作</td>
  </tr>
  <c:forEach items="${r"${requestScope.resultList}"}" var="${cg.getClassName()}">  
  <tr>
  <td><input type="checkbox" name="id" value="${r"${"}${cg.getClassName()}.${r"id}"}" onclick="selectReverse('ids','id');"/></td>
  <#list cg.getFieldList() as field>
  	<#if field.getName() != "id">
	<td>${r"${"}${cg.getClassName()}.${field.getName()}${r"}"}</td>
	</#if>
  </#list>
  <td><a href="${r"${"}base${r"}"}/admin/${cg.getObjectName()}/${cg.getObjectName()}Action!edit.${r"${"}actionExt${r"}"}?id=${r"${"}${cg.getClassName()}.id${r"}"}">修改 </a></td>
  </tr>
  </c:forEach>
  <tr>
  <td colspan="8" align="right" ><a href="${r"${base}"}/admin/${cg.getObjectName()}/${cg.getObjectName()}Action!add.${r"${actionExt}"}">添加</a> 
  <a href="javascript:void(0);" onclick="javascript:del();">删除</a></td>
  </tr>
</table>
</form>
<%@ include file="/jsp/common/page.jsp"%>
<%@ include file="/jsp/common/bottom.jsp"%>
</div>
</body>
</html>