package cn.xdf.cg.model;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器条件类
 * @author Administrator
 *
 */
public class Cg extends CommonModel{
	
	private static final long serialVersionUID = -1734152978301942730L;
	
	/**
	 * 模块名称
	 */
	private String modelName;
	
	/**
	 * 包路径
	 */
	private String packagePath;
	
	/**
	 * 数据库类型
	 */
	private String dbType;
	
	/**
	 * Oracle 序列
	 */
	private String dbSeq;
	
	/**
	 * 文件编码
	 */
	private String charSet;
	
	/**
	 * 表SQL
	 */
	private String tableSql;
	
	//处理后数据
	/**
	 * 包文件夹形式路径
	 */
	private String packageFloderPath;
	/**
	 * 所有处理后字段Map
	 */
	private Map<String, Field> fieldMap;
	
	/**
	 * 所有处理后字段List
	 */
	private List<Field> fieldList;
	
	/**
	 * 表名
	 */
	private String tableName;
	
	/**
	 * 类名
	 */
	private String className;
	
	/**
	 * 类对象名
	 */
	private String objectName;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbSeq() {
		return dbSeq;
	}

	public void setDbSeq(String dbSeq) {
		this.dbSeq = dbSeq;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getTableSql() {
		return tableSql;
	}

	public void setTableSql(String tableSql) {
		this.tableSql = tableSql;
	}

	public String getPackageFloderPath() {
		return packageFloderPath;
	}

	public void setPackageFloderPath(String packageFloderPath) {
		this.packageFloderPath = packageFloderPath;
	}

	public Map<String, Field> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, Field> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
