package cn.xdf.cg.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import cn.xdf.cg.Property;
import cn.xdf.cg.model.Cg;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 文件工具类
 * 
 * @author qiusen
 *
 * Jan 25, 2010
 */
public class FileUtil {
	
	/**
	 * 创建Model文件
	 * @param cg
	 * @return
	 */
	public static boolean createModelFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/model";
		String template = Property.TEMPLATE_PATH + "/src/model/model.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("Model 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/model";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getClassName()+".java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("model.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建Action文件
	 * @param cg
	 * @return
	 */
	public static boolean createActionFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/action";
		String template = Property.TEMPLATE_PATH + "/src/action/action.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("Action 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + 
						"/controller/action/" + cg.getObjectName();
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getClassName()+"Action.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("action.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		return success;
		
	}

	/**
	 * 创建TestAction文件
	 * @param cg
	 * @return
	 */
	public static boolean createTestActionFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/action";
		String template = Property.TEMPLATE_PATH + "/src/action/TestAction.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("Action 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + 
						"/controller/action/" + cg.getObjectName();
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "Test"+cg.getClassName()+"Action.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("TestAction.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		return success;
		
	}
	/**
	 * 创建IService文件
	 * @param cg
	 * @return
	 */
	public static boolean createIServiceFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/service";
		String template = Property.TEMPLATE_PATH + "/src/service/IService.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("IService 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/service/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "I" + cg.getClassName()+"Service.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("IService.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	/**
	 * 创建IServiceTest文件
	 * @param cg
	 * @return
	 */
	public static boolean createIServiceTestFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/service";
		String template = Property.TEMPLATE_PATH + "/src/service/IServiceTest.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("IService 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/service/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "I" + cg.getClassName()+"ServiceTest.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("IServiceTest.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	/**
	 * 创建ServiceImpl文件
	 * @param cg
	 * @return
	 */
	public static boolean createServiceImplFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/service/impl";
		String template = Property.TEMPLATE_PATH + "/src/service/impl/ServiceImpl.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("ServiceImpl 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/service/impl/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getClassName()+"ServiceImpl.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("ServiceImpl.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建IDao文件
	 * @param cg
	 * @return
	 */
	public static boolean createIDaoFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/dao";
		String template = Property.TEMPLATE_PATH + "/src/dao/IDAO.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("IDAO 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/dao/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "I" + cg.getClassName()+"DAO.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("IDAO.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	/**
	 * 创建IDao文件
	 * @param cg
	 * @return
	 */
	public static boolean createIDaoTestFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/dao";
		String template = Property.TEMPLATE_PATH + "/src/dao/IDAOTest.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("IDAO 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/dao/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "I" + cg.getClassName()+"DAOTest.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("IDAOTest.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建DaoImpl文件
	 * @param cg
	 * @return
	 */
	public static boolean createDaoImplFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/dao/ibatisImpl";
		String template = Property.TEMPLATE_PATH + "/src/dao/ibatisImpl/DAOImpl.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("DAOImpl 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/dao/ibatisImpl/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getClassName()+"DAOImpl.java";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("DAOImpl.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建DaoIbatisXml文件
	 * @param cg
	 * @return
	 */
	public static boolean createDaoIbatisXmlFile(Cg cg){
		boolean success = false;
		
		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src/dao/xml";
		
		String template = Property.TEMPLATE_PATH + "/src/dao/xml/ModelXml.ftl";
		String modelXmlStr = null;
		if(cg.getDbType().equalsIgnoreCase("ORACLE")){
			template = Property.TEMPLATE_PATH + "/src/dao/xml/OracleModelXml.ftl";
			modelXmlStr = "OracleModelXml.ftl";
		}else if(cg.getDbType().equalsIgnoreCase("MYSQL")){
			template = Property.TEMPLATE_PATH + "/src/dao/xml/MysqlModelXml.ftl";
			modelXmlStr = "MysqlModelXml.ftl";
		}
		
		File templateFile = new File(template);
		
		if(!templateFile.exists()){
			System.out.println(cg.getDbType() + " ModelXml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/" +
						packagePath.replaceAll("\\.", "\\/") + "/dao/xml/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getClassName()+".xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate(modelXmlStr);
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建struts-action.xml文件
	 * @param cg
	 * @return
	 */
	public static boolean createStrutsActionXmlFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src";
		String template = Property.TEMPLATE_PATH + "/src/struts-action.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("struts-action.xml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "struts-action.xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("struts-action.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建actionContext.xml文件
	 * @param cg
	 * @return
	 */
	public static boolean createActionContextXmlFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src";
		String template = Property.TEMPLATE_PATH + "/src/actionContext.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("actionContext.xml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "actionContext.xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("actionContext.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建serviceContext.xml文件
	 * @param cg
	 * @return
	 */
	public static boolean createServiceContextXmlFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src";
		String template = Property.TEMPLATE_PATH + "/src/serviceContext.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("serviceContext.xml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "serviceContext.xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("serviceContext.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建daoContext.xml文件
	 * @param cg
	 * @return
	 */
	public static boolean createDaoContextXmlFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src";
		String template = Property.TEMPLATE_PATH + "/src/daoContext.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("daoContext.xml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "daoContext.xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("daoContext.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建sqlMapConfig.xml文件
	 * @param cg
	 * @return
	 */
	public static boolean createSqlMapConfigXmlFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/src";
		String template = Property.TEMPLATE_PATH + "/src/sqlMapConfig.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("sqlMapConfig.xml 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/src/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = "sqlMapConfig.xml";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("sqlMapConfig.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建list.jsp文件
	 * @param cg
	 * @return
	 */
	public static boolean createListJspFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/WebRoot/jsp";
		String template = Property.TEMPLATE_PATH + "/WebRoot/jsp/list.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("list.jsp 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/WebRoot/jsp/admin/" + cg.getObjectName() + "/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getObjectName() + "List.jsp";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("list.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建add.jsp文件
	 * @param cg
	 * @return
	 */
	public static boolean createAddJspFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/WebRoot/jsp";
		String template = Property.TEMPLATE_PATH + "/WebRoot/jsp/add.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("add.jsp 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/WebRoot/jsp/admin/" + cg.getObjectName() + "/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getObjectName() + "Add.jsp";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("add.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
	
	/**
	 * 创建edit.jsp文件
	 * @param cg
	 * @return
	 */
	public static boolean createEditJspFile(Cg cg){
		boolean success = false;

		PrintWriter printWriter = null;
		String templateDir = Property.TEMPLATE_PATH + "/WebRoot/jsp";
		String template = Property.TEMPLATE_PATH + "/WebRoot/jsp/edit.ftl";
		File templateFile = new File(template);
		if(!templateFile.exists()){
			System.out.println("edit.jsp 模版文件不存在...");
			return success;
		}
		try{
		
			String packagePath = cg.getPackagePath();
			String packageFloder = Property.OUTPUT_FILE_PATH + "/" + 
						packagePath + "." + cg.getClassName() + "/WebRoot/jsp/admin/" + cg.getObjectName() + "/";
			System.out.println("packageFloder: " + packageFloder);
			String fileName = cg.getObjectName() + "Edit.jsp";
			File floderFile = new File(packageFloder);
			if(!floderFile.exists()){
				floderFile.mkdirs();
			}
			
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(packageFloder+File.separator+fileName),cg.getCharSet())));
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setDefaultEncoding("UTF-8");
			Template freemarkerTemplate = cfg.getTemplate("edit.ftl");
			Map<String, Cg> rootMap=new HashMap<String, Cg>();
			rootMap.put("cg",cg);
			freemarkerTemplate.process(rootMap, printWriter);
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
	}
}
