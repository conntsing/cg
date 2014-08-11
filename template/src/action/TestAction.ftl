package ${cg.getPackagePath()}.controller.action.${cg.getObjectName()};

import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import org.junit.Test;

import cn.xdf.util.DateUtil;
import cn.xdf.model.${cg.getClassName()};
import cn.xdf.service.I${cg.getClassName()}Service;
import cn.xdf.controller.action.CommonTestAction;

/**
 * ${cg.getModelName()} Action 测试
 * 
 * @author cg
 * @since ${cg.getCreateTime()}
 */
public class Test${cg.getClassName()}Action extends CommonTestAction {
	private ${cg.getClassName()}Action test;
	private I${cg.getClassName()}Service ${cg.getObjectName()}Service;
	
	@Override
	public String getNameSpace() {
		return "/admin/${cg.getObjectName()}/${cg.getObjectName()}Action";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (${cg.getClassName()}Action) proxy.getAction();
		if(null == ${cg.getObjectName()}Service)
			${cg.getObjectName()}Service =(I${cg.getClassName()}Service) applicationContext.getBean("${cg.getObjectName()}Service");
	}
	
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
	public void testExecute() throws Exception {
		String result = null;
		if (null != proxy)
			result = proxy.execute();

		Assert.assertEquals("success", result);

	}
	
	@Test
	public void testAdd(){
		String res = test.add();

		Assert.assertEquals("add", res);
	}

	@Test
	public void testAddSave() {
		${cg.getClassName()} ${cg.getObjectName()} = buildTestModel();
	    test.set${cg.getClassName()}(${cg.getObjectName()});
		String res = test.addSave();
		${cg.getObjectName()}Service.deleteByIds(" where id = "+${cg.getObjectName()}.getId());
		Assert.assertNotNull(test.get${cg.getClassName()}());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		${cg.getClassName()} ${cg.getObjectName()} = addTest${cg.getClassName()}();
		request.setParameter("id", ""+${cg.getObjectName()}.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		${cg.getClassName()} a = (${cg.getClassName()}) request.getAttribute("${cg.getObjectName()}");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(${cg.getObjectName()}.getId(), a.getId());
		${cg.getObjectName()}Service.deleteByIds(" where id = "+${cg.getObjectName()}.getId());
	}
	@Test
	public void testEditSave(){
		
		${cg.getClassName()} ${cg.getObjectName()}1 = addTest${cg.getClassName()}();
	    ${cg.getClassName()} ${cg.getObjectName()}2 = buildTestModel();
	    ${cg.getObjectName()}2.setId(${cg.getObjectName()}1.getId());
		test.set${cg.getClassName()}(${cg.getObjectName()}2);
		String edit_save_res = test.editSave();
		${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}1);
		<#if anyStringField??>
		Assert.assertNotEquals(${cg.getObjectName()}1.get${anyStringField?cap_first}(), tmp.get${anyStringField?cap_first}());
		Assert.assertEquals("测试2", tmp.get${anyStringField?cap_first}());
		Assert.assertEquals(${cg.getObjectName()}2.get${anyStringField?cap_first}(), tmp.get${anyStringField?cap_first}());
		</#if>
		<#if anyNumField??>
		Assert.assertThat(${cg.getObjectName()}2.get${anyNumField?cap_first}(),equalTo(tmp.get${anyNumField?cap_first}()));
		</#if>
		Assert.assertEquals("editSave", edit_save_res);
		
		${cg.getObjectName()}Service.deleteByIds(" where id = "+${cg.getObjectName()}1.getId());
	}
	@Test
	public void testDelete(){
		${cg.getClassName()} ${cg.getObjectName()} = addTest${cg.getClassName()}();
		request.setParameter("id", ${cg.getObjectName()}.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		<#list 0..2 as t>
		${cg.getClassName()} ${cg.getObjectName()}${t} = addTest${cg.getClassName()}();
		ids[${t}]=${cg.getObjectName()}${t}.getId()+"";
		</#list>
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()}0));
	}
}