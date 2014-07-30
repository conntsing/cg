<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE sqlMap PUBLIC "-//iBATIS.com//DTD SQL Map 2.0//EN" "http://www.ibatis.com/dtd/sql-map-2.dtd">
<sqlMap namespace="${cg.getObjectName()}">
	<typeAlias alias="${cg.getObjectName()}Obj" type="${cg.getPackagePath()}.model.${cg.getClassName()}" />
	
	<cacheModel id="${cg.getObjectName()}Cache" type="OSCACHE">
		<flushInterval minutes="10" />
		<flushOnExecute statement="${cg.getObjectName()}.insert" />
		<flushOnExecute statement="${cg.getObjectName()}.deleteByIds" />
		<flushOnExecute statement="${cg.getObjectName()}.update" />
	</cacheModel>
	
	<sql id="${cg.getObjectName()}LikeWhere">
		<dynamic prepend="where">
	    <#list cg.getFieldList() as field>
	        <#if field.getType() != "java.util.Date">
	            <#if field.getType() == "String">
		        <isNotEmpty prepend="and" property="${field.getName()}">
				    ${field.getDbName()} like '%$${field.getName()}$%'
			    </isNotEmpty>
		        <#else>
		            <isGreaterThan prepend="and" property="${field.getName()}" compareValue="-1">
						${field.getDbName()} = #${field.getName()}#
					</isGreaterThan>
		        </#if>
	        </#if>
	    </#list>
		</dynamic>
	</sql>
	
	<sql id="${cg.getObjectName()}Page">
			<isNotEmpty property="start">
			  limit #start#,#pageSize#
			</isNotEmpty>
	</sql>
	
	<sql id="${cg.getObjectName()}Field">
		<#list cg.getFieldList() as field>${field.getDbName()}<#if field_has_next>, </#if></#list>
	</sql>
	
	<resultMap id="${cg.getObjectName()}Map" class="${cg.getObjectName()}Obj">
		<#list cg.getFieldList() as field>
		<#if field.getType() == "Integer">
	    <result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.Integer" jdbcType="INTEGER" nullValue="0" />
	    <#elseif field.getType() == "Double">
	    <result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.Double" jdbcType="DOUBLE" />
	    <#elseif field.getType() == "java.util.Date">
	    <result property="${field.getName()}" column="${field.getDbName()}" javaType="java.util.Date" jdbcType="TIMESTEMP" />
	    <#else>
	    <result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.String" jdbcType="VARCHAR" nullValue="" />
	    </#if>
	    </#list>
	</resultMap>
	
	<insert id="insert" parameterClass="${cg.getObjectName()}Obj">
		insert into ${cg.getTableName()} (
           <#list cg.getFieldList() as field>
		<#if field.getName()!= "id" >
           ${field.getDbName()}<#if field_has_next>, </#if>
		</#if>
           </#list>
		) values 
		(
            <#list cg.getFieldList() as field>
		<#if field.getName()!= "id" >
            #${field.getName()}#<#if field_has_next>, </#if>
		</#if>
            </#list>
		)
		<selectKey resultClass="java.lang.Integer">
			select last_insert_id() as id from ${cg.getTableName()} limit 1
		</selectKey>
	</insert>
	
	<delete id="deleteByIds" parameterClass="String">
		delete from ${cg.getTableName()} $str$
	</delete>
	
	<update id="update" parameterClass="${cg.getObjectName()}Obj">
		update ${cg.getTableName()} set 
        <#list cg.getFieldList() as field>
          <#if field.getName()!= "id" >
              ${field.getDbName()} = #${field.getName()}#<#if field_has_next>, </#if>
          </#if>
        </#list>
        	where ID = #id#
	</update>
	
	<select id="select${cg.getClassName()}ById" parameterClass="${cg.getObjectName()}Obj" resultMap="${cg.getObjectName()}Map"  cacheModel="${cg.getObjectName()}Cache">
		SELECT * FROM ${cg.getTableName()} where ID = #id#
	</select>
	
	<select id="get${cg.getClassName()}Count" parameterClass="${cg.getObjectName()}Obj" resultClass="Long" cacheModel="${cg.getObjectName()}Cache">
		select count(*) from ${cg.getTableName()}
		<include refid="${cg.getObjectName()}LikeWhere" />
	</select>
	
	<select id="selectAll" parameterClass="${cg.getObjectName()}Obj" resultMap="${cg.getObjectName()}Map" cacheModel="${cg.getObjectName()}Cache">
		select * from ${cg.getTableName()}
	</select>
	
	<select id="select${cg.getClassName()}Like" parameterClass="${cg.getObjectName()}Obj" resultMap="${cg.getObjectName()}Map" cacheModel="${cg.getObjectName()}Cache">
		SELECT <include refid="${cg.getObjectName()}Field" /> FROM ${cg.getTableName()}
	        <include refid="${cg.getObjectName()}LikeWhere" />
			order by ID desc
	        <include refid="${cg.getObjectName()}Page" /> 
	</select>
</sqlMap>