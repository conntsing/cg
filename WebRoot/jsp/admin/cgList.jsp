<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/common/meta.jsp"%>
<title>已生成代码列表</title>
</head>
<body>
已生成代码<br/>
${fileStr }
<br /><br />
<a href="<%=Property.BASE %>">回首页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="admin/codeGenerator/codeGeneratorAction!add.${actionExt}" >创建代码</a>
</body>
</html>