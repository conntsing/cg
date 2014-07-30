<%@ page contentType="text/html;charset=${cg.getCharSet()}"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${cg.getModelName()} 修改</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
</script>
</head>
<body>
<div>
<%@ include file="/jsp/common/top.jsp"%>
<form name="${cg.getObjectName()}Form" id="${cg.getObjectName()}Form" method="post" action="${cg.getObjectName()}Action!editSave.${r"${actionExt}"}" onsubmit="return checkForm();">
<table width="97%" cellspacing="8" cellpadding="0" align="center">
  <tr>
    <td valign="top" width="9%">${cg.getModelName()} 修改</td>
    <td>&nbsp;</td>
  </tr>
  <#list cg.getFieldList() as field>
  	<#if field.getName() == "id">
  	<input type="hidden" id="${cg.getObjectName()}.${field.getName()}" name="${cg.getObjectName()}.${field.getName()}" value="${r"${requestScope"}.${cg.getObjectName()}.${field.getName()}${r"}"}"/>
  	<#else>
	<tr>
		<td valign="top">${field.getComment()}：</td>
	    <td><input type="text" id="${cg.getObjectName()}.${field.getName()}" name="${cg.getObjectName()}.${field.getName()}" value="${r"${requestScope"}.${cg.getObjectName()}.${field.getName()}${r"}"}"/></td>
	</tr>
	</#if>
  </#list>
  <tr>
    <td></td>
    <td><input value="保存" type="submit"/><input value="取消" type="button" onclick="javascript:history.back();"/></td>
  </tr>
</table>
</div>
</form>
<%@ include file="/jsp/common/bottom.jsp"%>
</div>
</body>
</html>