<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/common/meta.jsp"%>
<title>创建代码</title>
</head>
<body>
<form method="post" action="${base }/admin/codeGenerator/codeGeneratorAction!addSave.${actionExt}">
<table>
<tr><td>模块名称(中文)</td><td><input type="text" id="cg.modelName" name="cg.modelName" /></td></tr>
<tr><td>模块包路径</td><td><input type="text" id="cg.packagePath" name="cg.packagePath" /></td></tr>
<tr><td>数据库 </td><td>Oracle<input type="radio" id="cg.dbType" name="cg.dbType" value="ORACLE"/> / 
Mysql<input type="radio" id="cg.dbType" name="cg.dbType" value="MYSQL" checked="true"/></td></tr>
<tr><td>Oracle序列名(其他类型可不填)</td><td><input type="text" id="cg.dbSeq" name="cg.dbSeq" /></td></tr>
<tr><td>生成文件编码 </td><td>GBK<input type="radio" id="cg.charSet" name="cg.charSet" value="GBK"  checked="true" /> / 
UTF-8<input type="radio" id="cg.charSet" name="cg.charSet" value="UTF-8"/></td></tr>
<tr><td>表SQL </td><td><textarea id="cg.tableSql" name="cg.tableSql" rows="15" cols="50" ></textarea></td></tr>
<tr><td></td><td><input type="submit" value="创建" /></td></tr>
</table>
</form>
<br /><br />
<a href="<%=Property.BASE %>">回首页</a>
</body>
</html>