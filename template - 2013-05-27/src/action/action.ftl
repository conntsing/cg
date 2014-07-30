package ${cg.getPackagePath()}.controller.action.${cg.getObjectName()};

import java.util.List;

import ${cg.getPackagePath()}.Property;
import ${cg.getPackagePath()}.util.Page;
import ${cg.getPackagePath()}.model.${cg.getClassName()};
import ${cg.getPackagePath()}.service.I${cg.getClassName()}Service;
import ${cg.getPackagePath()}.controller.action.BaseAction;

/**
 * ${cg.getModelName()}Action
 * 
 * @author cg
 *
 * @date ${cg.getCreateTime()}
 */
 @SuppressWarnings("serial")
public class ${cg.getClassName()}Action extends BaseAction {
	private ${cg.getClassName()} ${cg.getObjectName()} = new ${cg.getClassName()}();
	private I${cg.getClassName()}Service ${cg.getObjectName()}Service;
	
	public ${cg.getClassName()} get${cg.getClassName()}() {
		return ${cg.getObjectName()};
	}

	public void set${cg.getClassName()}(${cg.getClassName()} ${cg.getObjectName()}) {
		this.${cg.getObjectName()} = ${cg.getObjectName()};
	}
	public I${cg.getClassName()}Service get${cg.getClassName()}Service() {
		return ${cg.getObjectName()}Service;
	}

	public void set${cg.getClassName()}Service(I${cg.getClassName()}Service ${cg.getObjectName()}Service) {
		this.${cg.getObjectName()}Service = ${cg.getObjectName()}Service;
	}
	/* 
	 * ${cg.getModelName()}查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			if (pageSizeStr != null && pageSizeStr.length() > 0) {
				try {
					pageSize = Integer.parseInt(pageSizeStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				pageSize = Property.PAGESIZE;
			}
			if (pageNoStr != null && pageNoStr.length() > 0) {
				try {
					pageNo = Integer.parseInt(pageNoStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(10);
			}

			Page pageInfo = ${cg.getObjectName()}Service.select${cg.getClassName()}(${cg.getObjectName()},this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(1);
			}
			
			List<${cg.getClassName()}> resultList = this.${cg.getObjectName()}Service.select${cg.getClassName()}(${cg.getObjectName()},pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","${cg.getObjectName()}Action");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 ${cg.getModelName()}
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 ${cg.getModelName()}
	 * @return
	 */
	public String addSave(){
		${cg.getObjectName()}Service.addSave(${cg.getObjectName()});
		return "addSave";
	}
	
	/**
	 * 修改 ${cg.getModelName()}
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		if(idStr!=null && idStr.length()>0){
			try{
				id = Integer.parseInt(idStr);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(id>0){
			${cg.getObjectName()}.setId(id);
		}else{
			return null;
		}
		
		${cg.getClassName()} ${cg.getObjectName()}VO = ${cg.getObjectName()}Service.select${cg.getClassName()}ById(${cg.getObjectName()});
		this.getRequest().setAttribute("${cg.getObjectName()}", ${cg.getObjectName()}VO);
		return "edit";
	}
	
	/**
	 * 保存修改 ${cg.getModelName()}
	 * @return
	 */
	public String editSave(){
		${cg.getObjectName()}Service.editSave(${cg.getObjectName()});
		return "editSave";
	}
	
	/**
	 * 删除 ${cg.getModelName()}
	 * @return
	 */
	public String delete(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			${cg.getObjectName()}Service.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}