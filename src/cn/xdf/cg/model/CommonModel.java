package cn.xdf.cg.model;

import java.io.Serializable;


/**
 * 与数据库表有对应关系的Model类的基类
 * 
 * @author <a href="mailto:HL_Qu@hotmail.com">Along</a>
 * 
 * @version $Revision$
 * 
 * @since 2008-11-19
 */
/**
 * @author qiusen
 * 
 * @date 2010-01-20
 *
 */
public abstract class CommonModel implements Serializable {

	private static final long serialVersionUID = 6437114463749744698L;

	// 数据库主键
	private Integer id;

	//分页查询时用的开始记录数
	private Integer start = 0;

	//分页查询时用的截止记录数
	private Integer end = 30;
	
	//当前所在页数
	private Integer pageNo;
	
	//每页显示的条目数
	private Integer pageSize;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
