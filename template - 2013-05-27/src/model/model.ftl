package ${cg.getPackagePath()}.model;

<#list cg.getFieldList() as field>
    <#if field.getType() == "java.util.Date">
import java.util.Date;		            
    </#if>
</#list>

/**
 * ${cg.getModelName()}
 * 
 * @author cg
 *
 * @date ${cg.getCreateTime()}
 */
@SuppressWarnings("serial")
public class ${cg.getClassName()} extends BaseModel{
<#list cg.getFieldList() as field>
	<#if field.getName() == "id">
	<#else>
	
	/**
	 * ${field.getComment()}
	 */
	<#if field.getType() == "java.util.Date">
	private Date ${field.getName()};
	<#else>
	private ${field.getType()} ${field.getName()};
	</#if>
	</#if>
</#list>
<#list cg.getFieldList() as field>
	<#if field.getName() == "id">
	<#else>
	<#if field.getType() == "java.util.Date">
	public Date get${field.getName()?cap_first}() {
		return ${field.getName()};
	}
	public void set${field.getName()?cap_first}(Date ${field.getName()}) {
		this.${field.getName()} = ${field.getName()};
	}
	<#else>
	public ${field.getType()} get${field.getName()?cap_first}() {
		return ${field.getName()};
	}
	public void set${field.getName()?cap_first}(${field.getType()} ${field.getName()}) {
		this.${field.getName()} = ${field.getName()};
	}
	</#if>
	</#if>		
</#list>
}
