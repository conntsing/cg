<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE struts PUBLIC
        "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
        "http://struts.apache.org/dtds/struts-2.0.dtd">
<struts>
    <package name="struts-admin-${cg.getObjectName()}" namespace="/admin/${cg.getObjectName()}" extends="struts-interceptor">
		<action name="${cg.getObjectName()}Action" class="${cg.getObjectName()}Action">
			<result name="success" type="dispatcher">/jsp/admin/${cg.getObjectName()}/${cg.getObjectName()}List.jsp</result>
			<result name="add" type="dispatcher">/jsp/admin/${cg.getObjectName()}/${cg.getObjectName()}Add.jsp</result>
			<result name="addSave" type="redirectAction">
				<param name="actionName">${cg.getObjectName()}Action</param>
				<param name="namespace">/admin/${cg.getObjectName()}</param>
			</result>

			<result name="edit" type="dispatcher">/jsp/admin/${cg.getObjectName()}/${cg.getObjectName()}Edit.jsp</result>
			<result name="editSave" type="redirectAction">
				<param name="actionName">${cg.getObjectName()}Action</param>
				<param name="namespace">/admin/${cg.getObjectName()}</param>
			</result>
			
			<result name="deleteSuccess" type="redirectAction">
				<param name="actionName">${cg.getObjectName()}Action</param>
				<param name="namespace">/admin/${cg.getObjectName()}</param>
			</result>
			
			<interceptor-ref name="common-params"/>
        </action>
    </package> 
</struts>