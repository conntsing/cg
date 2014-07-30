package cn.xdf.cg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.xdf.cg.model.Cg;
import cn.xdf.cg.model.Field;

/**
 * Oracle SQL处理工具类 (Plsql )
 * 
 * @author qiusen
 *
 * Jan 25, 2010
 */
public class OracleSqlUtil {
	private static String regexStr = ".*create table ([^\\(]*)\\(((\\s*[A-Z0-9\\_]*\\s*.*\\,){1,}\\s*[A-Z0-9\\_]*\\s*[A-Z0-9]*\\s*)\\).*";
	/**
	 * 根据SQL,获取字段集合
	 * @param cg
	 * @return
	 */
	public static Map<String, Field> getFieldMapBySql(Cg cg, List<String> orderList){
		Map<String, Field> fieldMap = null;
		String str = null;
		try{
			String tableSql = formartSql(cg.getTableSql());
			if(tableSql!=null && tableSql.trim().length()>0){
//				tableSql=tableSql.replaceAll("--.*\r\n"," ");
//				String tempString=tableSql.replaceAll("\r\n"," ");
//				Pattern p = Pattern.compile(regexStr);
//				Matcher m = p.matcher(tempString);
//				m.matches();
//				try{
//					str=m.group(2);
//				}catch(Exception e){
//					System.out.println("匹配字段出错,请检查sql是否按标准格式提交...");
//					e.printStackTrace();
//				}
				
//				tableSql=tableSql.replaceAll("--.*\r\n","\r\n");
//				String tempString=tableSql;//.replaceAll("\r\n"," ");
				String[] strs = tableSql.split("\r\n");
				StringBuffer strbuf = new StringBuffer();
				int k=0;
				if(strs!=null && strs.length>0){
					for(int i=0;i<strs.length;i++){
						if(strs[i].trim().toLowerCase().startsWith("create table")){
							k=i+2;
						}
						if(k!=0 && k<=i){	
							if(strs[i].trim().equals(")")){
								break;
							}
							strbuf.append(strs[i].trim()).append("~");
						}
						
					}
				}
				str = strbuf.toString();
				
				System.out.println("str: " + str);
				if(str!=null && str.trim().length()>0){
					str = str.trim().toUpperCase();	//转换为大写					
					
					String ch = "~";
					StringTokenizer st=new StringTokenizer(str,ch);
					int count = st.countTokens();
					String[] fieldTemps= new String[count];
					int c = 0;
					while (st.hasMoreTokens()) {
						fieldTemps[c] = st.nextToken();
//						System.out.println("fieldTemps[c]: " + fieldTemps[c]);
						c ++;
					}
					String[] fields = null;
					String[] fieldTypes = null;
					if(fieldTemps!=null && fieldTemps.length>0){
						fields = new String[fieldTemps.length];
						fieldTypes = new String[fieldTemps.length];
						int j = 0;
						
						for(int i=0;i<fieldTemps.length;i++){
//							System.out.println("fieldTemps[i]: " + fieldTemps[i]);
							String fieldTemp = "";
							if(fieldTemps[i].trim().endsWith(",")){
								fieldTemp = fieldTemps[i].trim().substring(0, fieldTemps[i].trim().length()-1);
							}else{
								fieldTemp = fieldTemps[i].trim();
							}
							System.out.println("fieldTemp------" + fieldTemp);
							if(fieldTemp.split("\\s+").length>=2){
								fields[j] = fieldTemp.split("\\s+")[0];
								fieldTypes[j] = fieldTemp.split("\\s+")[1];
								j++;
							}
							
							
							
						}
					}
					if(fields!=null && fields.length>0 && fieldTypes!=null && fieldTypes.length>0 
							&& fields.length==fieldTypes.length){
						fieldMap = new HashMap<String, Field>();
						Field field = null;
						for(int i=0;i<fields.length;i++){
							String name = fields[i];
							String type = fieldTypes[i];
							if(name!=null && name.trim().length()>0 && type!=null && type.trim().length()>0){
								name = name.trim();
								field = new Field();
								field.setDbName(name);
								field.setName(getJavaFieldNameByDBField(name));
								field.setDbType(type);
								field.setType(TypeUtil.getJavaFieldOracleTypeByDBType(type));
								field.setComment(name);
								
								orderList.add(name);
								fieldMap.put(name, field);
							}
						}
						fieldMap = commontField(cg, fieldMap);
					}
					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fieldMap;
	}
	
	/**
	 * 格式化SQL
	 * @param tableSql
	 * @return
	 */
	private static String formartSql(String tableSql){
		
		String sql = tableSql.replaceAll("--.*\r\n","\r\n");
		String[] temps = sql.split("\r|\n");
		StringBuffer strBuf = new StringBuffer();
		for(int i=0;i<temps.length;i++){
			if(!(temps[i].trim().length()==0)){
				strBuf.append(temps[i].trim()).append("\r\n");
			}
		}
//		System.out.println("-----formartSql-----" + strBuf.toString());
		return strBuf.toString();
	}
	/**
	 * 注释字段
	 * @param cg
	 * @param fieldMap
	 * @return
	 */
	private static Map<String, Field> commontField(Cg cg, Map<String, Field> fieldMap){
		
		Pattern commentPattern = Pattern.compile("\\s*COMMENT\\s+?ON\\s+?COLUMN\\s+?.*\\.(.*) IS '(.*)'\\s*;");
		String tableSql = formartSql(cg.getTableSql());
		String[] temps=tableSql.split("\r|\n");
		List<String> tempList = new ArrayList<String>();
		for(int i=0;i<temps.length;i++){
			if(!(temps[i].trim().length()==0)){
				tempList.add(temps[i]);
			}
		}
		
		for(int i=0;i<tempList.size();i++){
//			System.out.println(temps[i].trim());
			
			String commentStr = tempList.get(i);
			
			//判断注释是否折行
			String temp = tempList.get(i).trim().toLowerCase();
			if(temp.startsWith("comment")){
				if(!temp.endsWith(";")){
//					System.out.println("tempList.get(i+1)---------" + tempList.get(i+1));
					commentStr = commentStr + " " +tempList.get(i+1);
//					System.out.println("----xxx-----" + commentStr);
					i++;
				}
			}
//			System.out.println("commentStr---------" + commentStr);
			Matcher matcher = commentPattern.matcher(commentStr.trim().toUpperCase());
			if(matcher.matches()){
				String name = matcher.group(1).trim();
				String comment = matcher.group(2);
				System.out.println(name + " -----comment----- " + comment);
				if(fieldMap.containsKey(name)){
					Field field = fieldMap.get(name);
					field.setComment(comment);
					fieldMap.put(name, field);
				}
			}
		}
		return fieldMap;
	}
	
	/**
	 * 根据数据库字段获取java文件属性名
	 * @param name
	 * @return
	 */
	private static String getJavaFieldNameByDBField(String name){
		String str = name;
		str = str.trim().toLowerCase();
		while(str.indexOf("_")>-1){
			int i=0;
			i=str.indexOf("_");
			str = str.substring(0,i)+str.substring(i+1, i+2).toUpperCase()+str.substring(i+2, str.length());
		}
		System.out.println(str);
		return str;
	}
	
	
	/**
	 * 根据SQL,获取数据库表名称
	 * @param cg
	 * @return
	 */
	public static String getTableNameBySql(Cg cg){
		String tableName = null;
		
		String tableSql = formartSql(cg.getTableSql());
		if(tableSql!=null && tableSql.trim().length()>0){
//			tableSql=tableSql.replaceAll("--.*\r\n"," ");
//			String tempString=tableSql.replaceAll("\r\n"," ");
//			Pattern p = Pattern.compile(regexStr);
//			Matcher m = p.matcher(tempString);
//			m.matches();
			
			try{
//				tableSql=tableSql.replaceAll("--.*\r\n","\r\n");
				String[] strs = tableSql.split("\r\n");
				
				if(strs!=null && strs.length>0){
					for(int i=0;i<strs.length;i++){
						if(strs[i].trim().startsWith("create table")){
							Pattern p = Pattern.compile("create table\\s+(.*)");
							Matcher m = p.matcher(strs[i].trim());
							m.matches();
							tableName=m.group(1);
							break;
						}
						
					}
				}
			}catch(Exception e){
				System.out.println("匹配表名出错,请检查sql是否按标准格式提交...");
				e.printStackTrace();
			}
			
		}
		
		return tableName;
	}
	
	/**
	 * 根据SQL,获取Model类名称
	 * @param cg
	 * @return
	 */
	public static String getClassNameBySql(Cg cg){
		String className = null;
		String str = null;
		
		String tableSql = cg.getTableSql();
		if(tableSql!=null && tableSql.trim().length()>0){
			tableSql=tableSql.replaceAll("--.*\r\n"," ");
			String tempString=tableSql.replaceAll("\r\n"," ");
			Pattern p = Pattern.compile(regexStr);
			Matcher m = p.matcher(tempString);
			m.matches();
			try{
				str=m.group(1);
			}catch(Exception e){
				System.out.println("匹配表名出错,请检查sql是否按标准格式提交...");
				e.printStackTrace();
			}
			if(str!=null && str.trim().length()>0){
				str = str.trim().toLowerCase();
				while(str.indexOf("_")>-1){
					int i=0;
					i=str.indexOf("_");
					str = str.substring(0,i)+str.substring(i+1, i+2).toUpperCase()+str.substring(i+2, str.length());
				}
				str = str.substring(0,1).toUpperCase() + str.substring(1,str.length());
//				System.out.println(str);
				
				className = str;
			}
			
		}
		
		return className;
	}
}
