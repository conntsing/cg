package cn.xdf.cg.model;

/**
 * 字段
 * 
 * @author qiusen
 *
 * Jan 21, 2010
 */
public class Field extends CommonModel{
	
	private static final long serialVersionUID = 4811106636265947761L;

	/**
	 * 字段名称
	 */
	private String name;
	
	/**
	 * 数据库中字段名称
	 */
	private String dbName;
	
	/**
	 * 字段注释说明
	 */
	private String comment;
	
	/**
	 * 字段类型
	 */
	private String type;
	
	/**
	 * 数据库中字段类型
	 */
	private String dbType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	
}
