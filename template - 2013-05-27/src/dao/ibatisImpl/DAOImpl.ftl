package ${cg.getPackagePath()}.dao.ibatisImpl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import ${cg.getPackagePath()}.util.Page;
import ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO;
import ${cg.getPackagePath()}.model.${cg.getClassName()};

/**
 * ${cg.getModelName()} DAO 接口 I${cg.getClassName()}DAO 实现类
 * 
 * @author cg
 *
 * @date ${cg.getCreateTime()}
 */
public class ${cg.getClassName()}DAOImpl extends SqlMapClientDaoSupport implements I${cg.getClassName()}DAO{

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#addSave${cg.getClassName()}(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public int addSave${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}) {
		return (Integer) this.getSqlMapClientTemplate().insert("${cg.getObjectName()}.insert",${cg.getObjectName()});
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return this.getSqlMapClientTemplate().delete("${cg.getObjectName()}.deleteByIds", str);
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#editSave${cg.getClassName()}(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public int editSave${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}) {
		return this.getSqlMapClientTemplate().update("${cg.getObjectName()}.update",${cg.getObjectName()});
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#get${cg.getClassName()}ById(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	 @SuppressWarnings("unchecked") 
	public ${cg.getClassName()} get${cg.getClassName()}ById(${cg.getClassName()} ${cg.getObjectName()}) {
		List<${cg.getClassName()}> resultList = this.getSqlMapClientTemplate().queryForList("${cg.getObjectName()}.select${cg.getClassName()}ById", ${cg.getObjectName()});
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#get${cg.getClassName()}Count(${cg.getPackagePath()}.model.${cg.getClassName()})
	 */
	public Long get${cg.getClassName()}Count(${cg.getClassName()} ${cg.getObjectName()}) {
		return (Long) this.getSqlMapClientTemplate().queryForObject("${cg.getObjectName()}.get${cg.getClassName()}Count", ${cg.getObjectName()});
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#selectAll()
	 */
	 @SuppressWarnings("unchecked")
	public List<${cg.getClassName()}> selectAll() {
		return this.getSqlMapClientTemplate().queryForList("${cg.getObjectName()}.selectAll");
	}

	/* (non-Javadoc)
	 * @see ${cg.getPackagePath()}.dao.I${cg.getClassName()}DAO#select${cg.getClassName()}(${cg.getPackagePath()}.model.${cg.getClassName()},${cg.getPackagePath()}.controller.helper.Page)
	 */
	 @SuppressWarnings("unchecked")
	public List<${cg.getClassName()}> select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, Page page) {
		${cg.getObjectName()}.setStart(page.getFirstItemPos());
		<#if cg.getDbType() == "ORACLE">
		${cg.getObjectName()}.setEnd(page.getFirstItemPos() + page.getPageSize());
		<#else>
		${cg.getObjectName()}.setPageSize(page.getPageSize());
		</#if>
		return this.getSqlMapClientTemplate().queryForList(
				"${cg.getObjectName()}.select${cg.getClassName()}Like",${cg.getObjectName()});
	}
}
