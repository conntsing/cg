<?xml version="1.0" encoding="${cg.getCharSet()}"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO">
	
	<cache flushInterval="600000" eviction="LRU" type="org.mybatis.caches.oscache.OSCache"/>
	
	<sql id="${cg.getObjectName()}LikeWhere">
		<where>
		    <#list cg.getFieldList() as field>
	        <#if field.getType() != "java.util.Date">
            <#if field.getType() == "String">
	        <if test ="${field.getName()} != null and ${field.getName()} != ''">
			    and `${field.getDbName()}` like '%${"$" + "{"+field.getName()+"}"}%'
		    </if>
	        <#else>
            <if test ="${field.getName()} != null and ${field.getName()} > -1">
				and `${field.getDbName()}` = #${"{"+field.getName()+"}"}
			</if>
	        </#if>
	        </#if>
		    </#list>
		</where>
	</sql>
	
	<sql id="${cg.getObjectName()}Page">
		<if test="null != start">
		  limit ${"#"+"{start}"},${"#"+"{pageSize}"}
		</if>
	</sql>
	
	<sql id="${cg.getObjectName()}Field">
		<#list cg.getFieldList() as field>`${field.getDbName()}`<#if field_has_next>, </#if></#list>
	</sql>
	
	<resultMap id="${cg.getObjectName()}Map" type="${cg.getObjectName()}">
		<#list cg.getFieldList() as field>
			<#if field.getType() == "Integer">
	    	<result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.Integer" jdbcType="INTEGER" />
		    <#elseif field.getType() == "Double">
	    	<result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.Double" jdbcType="DOUBLE" />
		    <#elseif field.getType() == "java.util.Date">
	    	<result property="${field.getName()}" column="${field.getDbName()}" javaType="java.util.Date" jdbcType="TIMESTAMP" />
		    <#else>
	    	<result property="${field.getName()}" column="${field.getDbName()}" javaType="java.lang.String" jdbcType="VARCHAR" />
		    </#if>
	    </#list>
	</resultMap>
	
	<insert id="addSave${cg.getClassName()}" parameterType="${cg.getObjectName()}">
		insert into `${cg.getTableName()}` (
           <#list cg.getFieldList() as field>
		<#if field.getName()!= "id" >
           `${field.getDbName()}`<#if field_has_next>, </#if>
		</#if>
           </#list>
		) values 
		(
            <#list cg.getFieldList() as field>
		<#if field.getName()!= "id" >
            ${"#"+"{"+field.getName()+"}"}<#if field_has_next>, </#if>
		</#if>
            </#list>
		)
		<selectKey keyProperty="id"  resultType="java.lang.Integer">
			select last_insert_id() as id from `${cg.getTableName()}` limit 1
		</selectKey>
	</insert>
	
	<delete id="deleteByIds" parameterType="String">
		delete from `${cg.getTableName()}` ${"$"+"{_parameter}"}
	</delete>
	
	<update id="editSave${cg.getClassName()}" parameterType="${cg.getObjectName()}">
		update `${cg.getTableName()}` set 
        <#list cg.getFieldList() as field>
          <#if field.getName()!= "id" >
              `${field.getDbName()}` = #${"{"+field.getName()+"}"}<#if field_has_next>, </#if>
          </#if>
        </#list>
        	where ID = ${"#"+"{id}"}
	</update>
	
	<select id="select${cg.getClassName()}ById" parameterType="${cg.getObjectName()}" resultMap="${cg.getObjectName()}Map"  >
		SELECT * FROM `${cg.getTableName()}` where ID = ${"#"+"{id}"}
	</select>
	
	<select id="get${cg.getClassName()}Count" parameterType="${cg.getObjectName()}" resultType="Long" >
		select count(*) from `${cg.getTableName()}`
		<include refid="${cg.getObjectName()}LikeWhere" />
	</select>
	
	<select id="selectAll" parameterType="${cg.getObjectName()}" resultMap="${cg.getObjectName()}Map" >
		select * from `${cg.getTableName()}`
	</select>
	
	<select id="select${cg.getClassName()}Like" parameterType="${cg.getObjectName()}" resultMap="${cg.getObjectName()}Map" >
		SELECT <include refid="${cg.getObjectName()}Field" /> FROM `${cg.getTableName()}`
	        <include refid="${cg.getObjectName()}LikeWhere" />
			order by ID desc
	        <include refid="${cg.getObjectName()}Page" /> 
	</select>
</mapper>