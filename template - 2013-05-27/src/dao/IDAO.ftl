package ${cg.getPackagePath()}.dao;

import java.util.List;

import ${cg.getPackagePath()}.util.Page;
import ${cg.getPackagePath()}.model.${cg.getClassName()};

/**
 * ${cg.getModelName()} DAO 接口
 * 
 * @author cg
 * 
 * @since ${cg.getCreateTime()}
 */
public interface I${cg.getClassName()}DAO {

	/**
	 * 根据条件查询${cg.getModelName()} 条数
	 * 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public Long get${cg.getClassName()}Count(${cg.getClassName()} ${cg.getObjectName()});

	/**
	 * 分页查找${cg.getModelName()}
	 * 
	 * @param ${cg.getObjectName()}
	 * @param page
	 * @return
	 */
	public List<${cg.getClassName()}> select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, Page page);

	/**
	 * 添加${cg.getModelName()}
	 * 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public int addSave${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()});

	/**
	 * 根据ID获取指定的${cg.getModelName()}信息
	 * 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public ${cg.getClassName()} get${cg.getClassName()}ById(${cg.getClassName()} ${cg.getObjectName()});

	/**
	 * 修改${cg.getModelName()}
	 * 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public int editSave${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()});

	/**
	 * 根据ID删除指定的${cg.getModelName()}
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有${cg.getModelName()}
	 * 
	 * @return
	 */
	public List<${cg.getClassName()}> selectAll();
}
