package cn.xdf.cg.controller.action.codeGenerator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import cn.xdf.cg.Property;
import cn.xdf.cg.controller.action.BaseAction;
import cn.xdf.cg.model.Cg;
import cn.xdf.cg.model.Field;
import cn.xdf.cg.service.ICodeGeneratorService;
import cn.xdf.cg.util.FileUtil;
import cn.xdf.cg.util.MysqlSqlUtil;
import cn.xdf.cg.util.OracleSqlUtil;
import cn.xdf.cg.util.ZipUtil;

/**
 * 代码生成器入口Action
 * 
 * @author qiusen
 * 
 * @date 2010-01-20
 *
 */
public class CodeGeneratorAction extends BaseAction{

	private static final long serialVersionUID = 8350060536858182920L;
	
	private ICodeGeneratorService codeGeneratorService;
	
	private Cg cg = new Cg();
	
	public ICodeGeneratorService getCodeGeneratorService() {
		return codeGeneratorService;
	}

	public void setCodeGeneratorService(ICodeGeneratorService codeGeneratorService) {
		this.codeGeneratorService = codeGeneratorService;
	}

	public Cg getCg() {
		return cg;
	}

	public void setCg(Cg cg) {
		this.cg = cg;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		File file = new File(Property.OUTPUT_FILE_PATH);
		File[] subFiles = file.listFiles();
		StringBuffer fileStr = new StringBuffer();
		if(subFiles!=null && subFiles.length>0){
			for(int i=0;i<subFiles.length;i++){
				if(subFiles[i].getName().endsWith("zip")){
					fileStr.append("<a href='").append(Property.BASE).append(Property.RELATIVE_FILE_PATH).append(subFiles[i].getName()).append("'>").append(subFiles[i].getName()).append("</a>").append("<br/>");
				}
			}
			
		}
		this.getRequest().setAttribute("fileStr", fileStr);
		return SUCCESS;
	}
	
	/**
	 * 创建
	 * @return
	 */
	public String add(){
		System.out.println("add...");
		return "add";
	}
	
	public String addSave(){
		System.out.println("addSave...");
		
		//获取包文件夹形式路径
		String packageFloderPath = cg.getPackagePath().replaceAll("\\.", "\\/");
		cg.setPackageFloderPath(packageFloderPath);
		
		//获取字段集合
		Map<String, Field> fieldMap = null;
		//字段排序集合
		List<String> orderList = new ArrayList<String>();
		if(cg.getDbType().equalsIgnoreCase("ORACLE")){
			System.out.println("-----------ORACLE-------------");
			fieldMap = OracleSqlUtil.getFieldMapBySql(cg, orderList);
		}else if(cg.getDbType().equalsIgnoreCase("MYSQL")){
			System.out.println("-----------MYSQL-------------");			
			fieldMap = MysqlSqlUtil.getFieldMapBySql(cg, orderList);
		}
		cg.setFieldMap(fieldMap);
		
		List<Field> fieldList = new ArrayList<Field>();
//		Iterator<String> it = fieldMap.keySet().iterator();
		Field field = null;
		for(int i=0;i<orderList.size();i++){
			field = fieldMap.get(orderList.get(i));
			fieldList.add(field);
			System.out.println("DbName: " + field.getDbName() + 
					"; Name: " + field.getName() + 
					"; DbType: " + field.getDbType() + 
					"; Type: " + field.getType() + 
					"; Comment: " + field.getComment());
		}
//		while(it.hasNext()){
//			String key = it.next();
//			field = fieldMap.get(key);
//			fieldList.add(field);
//			System.out.println("key: " + key + "; DbName: " + field.getDbName() + 
//								"; Name: " + field.getName() + 
//								"; DbType: " + field.getDbType() + 
//								"; Type: " + field.getType() + 
//								"; Comment: " + field.getComment());
//		}
		cg.setFieldList(fieldList);
		
		//获取数据库表名
		String tableName = "";
		if(cg.getDbType().equalsIgnoreCase("ORACLE")){
			tableName = OracleSqlUtil.getTableNameBySql(cg);
		}else if(cg.getDbType().equalsIgnoreCase("MYSQL")){
			tableName = MysqlSqlUtil.getTableNameBySql(cg);
		}
		cg.setTableName(tableName);
		System.out.println("tableName: " + tableName);
		
		//获取Model类名
//		String className = SqlUtil.getClassNameBySql(cg);
		String className = "";
		if(tableName!=null && tableName.trim().length()>0){
			tableName = tableName.trim().toLowerCase();
			while(tableName.indexOf("_")>-1){
				int i=0;
				i=tableName.indexOf("_");
				tableName = tableName.substring(0,i)+tableName.substring(i+1, i+2).toUpperCase()+tableName.substring(i+2, tableName.length());
			}
			tableName = tableName.substring(0,1).toUpperCase() + tableName.substring(1,tableName.length());

			className = tableName;
		}
		cg.setClassName(className);
		System.out.println("className: " + className);
		
		
		//设置类对象名
		String objectName = className.substring(0,1).toLowerCase() + className.substring(1, className.length());
		cg.setObjectName(objectName);
		System.out.println("objectName: " + objectName);
		
		String createTime=null;		
		Calendar time=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		createTime=format.format(time.getTime());
		cg.setCreateTime(createTime);
		System.out.println("createTime: " + createTime);
//		PrintWriter pw = null;
//		try {
//			pw = this.getResponse().getWriter();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//创建Model文件
		if(FileUtil.createModelFile(cg)){
//			pw.println("创建Model文件完成...");
			System.out.println("创建Model文件完成...");
		}
		//创建Action文件
		if(FileUtil.createActionFile(cg)){
			System.out.println("创建Action文件完成...");
		}
		//创建TestAction文件
		if(FileUtil.createTestActionFile(cg)){
			System.out.println("创建Action文件完成...");
		}
		//创建IService文件
		if(FileUtil.createIServiceFile(cg)){
			System.out.println("创建IService文件完成...");
		}
		//创建IServiceTest文件
		if(FileUtil.createIServiceTestFile(cg)){
			System.out.println("创建IService文件完成...");
		}
		//创建ServiceImpl文件
		if(FileUtil.createServiceImplFile(cg)){
			System.out.println("创建ServiceImpl文件完成...");
		}
		//创建IDao文件
		if(FileUtil.createIDaoFile(cg)){
			System.out.println("创建IDao文件完成...");
		}
		//创建IDaoTest文件
		if(FileUtil.createIDaoTestFile(cg)){
			System.out.println("创建IDaoTest文件完成...");
		}
//		//创建DaoImpl文件
//		if(FileUtil.createDaoImplFile(cg)){
//			System.out.println("创建DaoImpl文件完成...");
//		}
		//创建DaoIbatisXml文件
		if(FileUtil.createDaoIbatisXmlFile(cg)){
			System.out.println("创建DaomybatisXml文件完成...");
		}
		//创建struts-action.xml文件
		if(FileUtil.createStrutsActionXmlFile(cg)){
			System.out.println("创建struts-action.xml文件完成...");
		}
		//创建actionContext.xml文件
		if(FileUtil.createActionContextXmlFile(cg)){
			System.out.println("创建actionContext.xml文件完成...");
		}
		//创建serviceContext.xml文件
		if(FileUtil.createServiceContextXmlFile(cg)){
			System.out.println("创建serviceContext.xml文件完成...");
		}
		//创建daoContext.xml文件
		if(FileUtil.createDaoContextXmlFile(cg)){
			System.out.println("创建daoContext.xml文件完成...");
		}
		//创建sqlMapConfig.xml文件
		if(FileUtil.createSqlMapConfigXmlFile(cg)){
			System.out.println("创建sqlMapConfig.xml文件完成...");
		}
		//创建list.jsp文件
		if(FileUtil.createListJspFile(cg)){
			System.out.println("创建list.jsp文件完成...");
		}
		//创建add.jsp文件
		if(FileUtil.createAddJspFile(cg)){
			System.out.println("创建add.jsp文件完成...");
		}
		//创建edit.jsp文件
		if(FileUtil.createEditJspFile(cg)){
			System.out.println("创建edit.jsp文件完成...");
		}
		
		//压缩
		ZipUtil.doZip(cg);
		
		return "addSave";
	}
}
