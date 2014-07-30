package ${cg.getPackagePath()}.service;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xdf.util.Page;
import cn.xdf.util.DateUtil;
import cn.xdf.BaseTestCase;
import cn.xdf.model.${cg.getClassName()};
/**
 * ${cg.getModelName()} Service 接口测试
 * 
 * @author cg
 * @since ${cg.getCreateTime()}
 */
public class I${cg.getClassName()}ServiceTest extends BaseTestCase{
	@Autowired
	private I${cg.getClassName()}Service ${cg.getObjectName()}Service;
	private ${cg.getClassName()} buildTestModel(){
	
		${cg.getClassName()} ${cg.getObjectName()} = new ${cg.getClassName()}();
		<#list cg.getFieldList() as field>
		<#if field.getName() != "id">
        <#if field.getType() != "java.util.Date">
        <#if field.getType() == "String">
		${cg.getObjectName()}.set${field.getName()?cap_first}("测试");
        <#else>
		${cg.getObjectName()}.set${field.getName()?cap_first}(123);
        </#if>
        <#else>
        ${cg.getObjectName()}.set${field.getName()?cap_first}(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        </#if>
        </#if>
	    </#list>
	    return ${cg.getObjectName()};
	}
	private ${cg.getClassName()} addTest${cg.getClassName()}(){
		${cg.getClassName()} ${cg.getObjectName()} = buildTestModel();
	    ${cg.getObjectName()}Service.addSave(${cg.getObjectName()});
	    return ${cg.getObjectName()};
	}
	
	@Test
	public void select${cg.getClassName()}Page(){
		addTest${cg.getClassName()}();
	    Page p = ${cg.getObjectName()}Service.select${cg.getClassName()}(new ${cg.getClassName()}(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void select${cg.getClassName()}PageList(){
		
		addTest${cg.getClassName()}();
		
		${cg.getClassName()} obj = new ${cg.getClassName()}();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<${cg.getClassName()}> ${cg.getObjectName()}List = ${cg.getObjectName()}Service.select${cg.getClassName()}(obj, page);
		Assert.assertTrue(${cg.getObjectName()}List.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTest${cg.getClassName()}();
		
		Assert.assertTrue("fail: ${cg.getObjectName()}`s total is bad res!", ${cg.getObjectName()}Service.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		${cg.getClassName()} ${cg.getObjectName()} = buildTestModel();
		int pr = ${cg.getObjectName()}Service.selectAll().size();
		int res = ${cg.getObjectName()}Service.addSave(${cg.getObjectName()});
		int s = ${cg.getObjectName()}Service.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}));
		Assert.assertTrue("Fail:call ${cg.getObjectName()}Service insert fail!", s==pr+1);
	}

	@Test
	public void select${cg.getClassName()}ById(){
		
		${cg.getClassName()} ${cg.getObjectName()} = addTest${cg.getClassName()}();
		
		${cg.getClassName()} tmp = new ${cg.getClassName()}();
		tmp.setId(${cg.getObjectName()}.getId());
		Assert.assertNotNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(tmp));
	}

	@Test
	public void editSave(){
		
		${cg.getClassName()} ${cg.getObjectName()}1 = addTest${cg.getClassName()}();
		
	    ${cg.getClassName()} ${cg.getObjectName()}2 = ${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}1);
		<#list cg.getFieldList() as field>
		<#if field.getName() != "id">
        <#if field.getType() != "java.util.Date">
        <#if field.getType() == "String">
		${cg.getObjectName()}2.set${field.getName()?cap_first}("测试2");
		<#assign anyStringField=field.getName()/>
        <#else>
		${cg.getObjectName()}2.set${field.getName()?cap_first}(21);
		<#assign anyNumField=field.getName()/>
        </#if>
        <#else>
        ${cg.getObjectName()}2.set${field.getName()?cap_first}(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        </#if>
        </#if>
	    </#list>
		${cg.getObjectName()}Service.editSave(${cg.getObjectName()}2);
		
		${cg.getClassName()} tmp = ${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}2);
		<#if anyStringField??>
		Assert.assertNotEquals(${cg.getObjectName()}1.get${anyStringField?cap_first}(), tmp.get${anyStringField?cap_first}());
		Assert.assertEquals(${cg.getObjectName()}2.get${anyStringField?cap_first}(), tmp.get${anyStringField?cap_first}());
		Assert.assertEquals("测试2", tmp.get${anyStringField?cap_first}());
		</#if>
		<#if anyNumField??>
		Assert.assertThat(${cg.getObjectName()}2.get${anyNumField?cap_first}(),equalTo(tmp.get${anyNumField?cap_first}()));
		</#if>
	}
	
	@Test
	public void deleteByIds(){
		
		${cg.getClassName()} ${cg.getObjectName()} = addTest${cg.getClassName()}();
		
		${cg.getClassName()} obj = new ${cg.getClassName()}();
		obj.setId(${cg.getObjectName()}.getId());
		Assert.assertNotNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(obj));
		int res = ${cg.getObjectName()}Service.deleteByIds("where id = "+${cg.getObjectName()}.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(obj));
	}
}