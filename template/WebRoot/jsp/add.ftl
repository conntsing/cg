<%@ page contentType="text/html;charset=${cg.getCharSet()}"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${cg.getModelName()} 添加</title>
<%@ include file="/jsp/common/meta.jsp"%>
<script language="javascript">
function checkForm(){
	return true;
}
</script>
<style type="text/css">
    body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-reset{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
</head>
<body>
<form name="${cg.getObjectName()}Form" id="${cg.getObjectName()}Form" method="post" action="${cg.getObjectName()}Action!addSave.${r"${actionExt}"}" onsubmit="return checkForm();">
<table cellpadding="0" cellspacing="0" class="l-table-edit" style="margin-top:50px;margin-left:50px;">
<#list cg.getFieldList() as field>
	<#if field.getName() != "id">
    <tr>
    	<td align="right" class="l-table-edit-td">${field.getComment()}：</td>
    	<td align="left" class="l-table-edit-td"><input name="${cg.getObjectName()}.${field.getName()}" type="text" id="${cg.getObjectName()}.${field.getName()}" ltype="text" /></td>
    	<td align="left"></td>
    </tr>
    </#if>
</#list>
    <tr>
    	<td align="center" colspan="3">
    	<input type="submit" value="保存" id="Button1" class="l-button l-button-submit" /> 
    	<input type="button" value="取消" class="l-button l-button-reset" onclick="javascript: history.back(-1);"/>
    	</td>
    </tr>
</table>
</form>
</body>
</html>