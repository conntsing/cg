package ${cg.getPackagePath()}.dao;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import cn.xdf.BaseTestCase;
import cn.xdf.model.${cg.getClassName()};
import cn.xdf.util.DateUtil;
import cn.xdf.util.Page;

/**
 * ${cg.getModelName()} DAO 接口测试
 * 
 * @author cg
 * @since ${cg.getCreateTime()}
 */
public class I${cg.getClassName()}DAOTest extends BaseTestCase {
	@Resource
	private I${cg.getClassName()}DAO ${cg.getObjectName()}DAO;
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
	    ${cg.getObjectName()}DAO.addSave${cg.getClassName()}(${cg.getObjectName()});
	    return ${cg.getObjectName()};
	}
	@Test
	public void get${cg.getClassName()}Count() {
		addTest${cg.getClassName()}();
		${cg.getClassName()} total_obj = new ${cg.getClassName()}();
		Long total = ${cg.getObjectName()}DAO.get${cg.getClassName()}Count(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void select${cg.getClassName()}Like() {
		addTest${cg.getClassName()}();
		${cg.getClassName()} tmp = new ${cg.getClassName()}();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<${cg.getClassName()}> l = ${cg.getObjectName()}DAO.select${cg.getClassName()}Like(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void select${cg.getClassName()}ById() {
		${cg.getClassName()} obj = addTest${cg.getClassName()}();
		${cg.getClassName()} tmp = new ${cg.getClassName()}();
		tmp.setId(obj.getId());
		${cg.getClassName()} res = ${cg.getObjectName()}DAO.select${cg.getClassName()}ById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSave${cg.getClassName()}(){
		${cg.getClassName()} obj = buildTestModel();
		int res = ${cg.getObjectName()}DAO.addSave${cg.getClassName()}(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		${cg.getObjectName()}DAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSave${cg.getClassName()}(){
		${cg.getClassName()} add_obj = addTest${cg.getClassName()}();
		<#list cg.getFieldList() as field>
		<#if field.getName() != "id">
        <#if field.getType() != "java.util.Date">
        <#if field.getType() == "String">
		add_obj.set${field.getName()?cap_first}("测试2");
        <#else>
		add_obj.set${field.getName()?cap_first}(21);
        </#if>
        <#else>
        add_obj.set${field.getName()?cap_first}(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        </#if>
        </#if>
	    </#list>
		
		int edit_res = ${cg.getObjectName()}DAO.editSave${cg.getClassName()}(add_obj);
		Assert.assertTrue(edit_res>0);
		
		${cg.getObjectName()}DAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		${cg.getClassName()} obj = addTest${cg.getClassName()}();
		
		int res = ${cg.getObjectName()}DAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(${cg.getObjectName()}DAO.select${cg.getClassName()}ById(obj));
	}
}