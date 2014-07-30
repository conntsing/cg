package ${cg.getPackagePath()}.service.impl;

import java.util.List;
import javax.annotation.Resource;
import cn.xdf.util.Page;
import ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO;
import ${cg.getPackagePath()}.model.${cg.getClassName()};
import ${cg.getPackagePath()}.service.I${cg.getClassName()}Service;

/**
 * ${cg.getModelName()} 业务接口 I${cg.getClassName()}Service 实现类
 * 
 * @author cg
 *
 * @date ${cg.getCreateTime()}
 */
public class ${cg.getClassName()}ServiceImpl implements I${cg.getClassName()}Service {

	@Resource
	private I${cg.getClassName()}DAO ${cg.getObjectName()}DAO;

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#addSave(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public int addSave(${cg.getClassName()} ${cg.getObjectName()}) {
		return ${cg.getObjectName()}DAO.addSave${cg.getClassName()}(${cg.getObjectName()});
	}
	
	
	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return ${cg.getObjectName()}DAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#editSave(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public int editSave(${cg.getClassName()} ${cg.getObjectName()}) {
		return ${cg.getObjectName()}DAO.editSave${cg.getClassName()}(${cg.getObjectName()});
	}
	
	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.I${cg.getClassName()}Service#selectAll()
	 */
	public List<${cg.getClassName()}> selectAll() {
		return ${cg.getObjectName()}DAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#select${cg.getClassName()}(${cg.getPackagePath()}.model.${cg.getClassName()}, int)
	 */
	public Page select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, int pageSize) {
		return new Page(${cg.getObjectName()}DAO.get${cg.getClassName()}Count(${cg.getObjectName()}), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#select${cg.getClassName()}(${cg.getPackagePath()}.model.${cg.getClassName()}, ${cg.getPackagePath()}.controller.helper.Page)
	 */
	public List<${cg.getClassName()}> select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, Page page) {
		${cg.getObjectName()}.setStart(page.getFirstItemPos());
		<#if cg.getDbType() == "ORACLE">
		${cg.getObjectName()}.setEnd(page.getFirstItemPos() + page.getPageSize());
		<#else>
		${cg.getObjectName()}.setPageSize(page.getPageSize());
		</#if>
		return ${cg.getObjectName()}DAO.select${cg.getClassName()}Like(${cg.getObjectName()});
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.service.I${cg.getClassName()}Service#select${cg.getClassName()}ById(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public ${cg.getClassName()} select${cg.getClassName()}ById(${cg.getClassName()} ${cg.getObjectName()}) {
		return ${cg.getObjectName()}DAO.select${cg.getClassName()}ById(${cg.getObjectName()});
	}
}
