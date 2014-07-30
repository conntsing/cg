package ${cg.getPackagePath()}.service;

import java.util.List;

import ${cg.getPackagePath()}.util.Page;
import ${cg.getPackagePath()}.model.${cg.getClassName()};

/**
 * ${cg.getModelName()} 业务接口
 * 
 * @author cg
 *
 * @date ${cg.getCreateTime()}
 */
public interface I${cg.getClassName()}Service {
	/**
	 * 查询 ${cg.getClassName()} Page 对象
	 * @param ${cg.getObjectName()}
	 * @param pageSize
	 * @return
	 */
	public Page select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, int pageSize);

	/**
	 * 分页查找 ${cg.getModelName()}
	 * @param ${cg.getObjectName()}
	 * @param page
	 * @return
	 */
	public List<${cg.getClassName()}> select${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}, Page page);
	
	/**
	 * 查询所有 ${cg.getModelName()}
	 * @return
	 */
	public List<${cg.getClassName()}> selectAll();
	
	/**
	 * 根据 ID 查找 ${cg.getModelName()} 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public ${cg.getClassName()} select${cg.getClassName()}ById(${cg.getClassName()} ${cg.getObjectName()});
	
	/**
	 * 添加 ${cg.getModelName()} 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public int addSave(${cg.getClassName()} ${cg.getObjectName()});
	
	/**
	 * 修改 ${cg.getModelName()} 
	 * @param ${cg.getObjectName()}
	 * @return
	 */
	public int editSave(${cg.getClassName()} ${cg.getObjectName()});
	
	/**
	 * 根据 ID 删除 ${cg.getModelName()} 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
