package cn.xdf.cg.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.xdf.cg.model.Cg;
import cn.xdf.cg.model.Field;

/**
 * Mysql SQL处理工具类 (show create table 表名)
 * 
 * @author qiusen
 *
 * Jan 25, 2010
 */
public class MysqlSqlUtil {
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
				
				String[] strs = tableSql.split("\r\n");
				StringBuffer strbuf = new StringBuffer();
				int k=0;
				if(strs!=null && strs.length>0){
					for(int i=0;i<strs.length;i++){
						if(strs[i].trim().toLowerCase().startsWith("create table")){
							k=i+1;
						}
						if(k!=0 && k<=i){	
							if(strs[i].trim().startsWith(")")){
								break;
							}
							strbuf.append(strs[i].trim());
						}
						
					}
				}
				str = strbuf.toString();
				
				System.out.println("str: " + str);
				if(str!=null && str.trim().length()>0){
					str = str.trim().toUpperCase();	//转换为大写					
					
					String ch = ",";
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
					String[] fieldComments = null;
					if(fieldTemps!=null && fieldTemps.length>0){
						fields = new String[fieldTemps.length];
						fieldTypes = new String[fieldTemps.length];
						fieldComments = new String[fieldTemps.length];
						int j = 0;
						
						for(int i=0;i<fieldTemps.length;i++){
							System.out.println("fieldTemps[i]: " + fieldTemps[i]);
							if(fieldTemps[i].trim().split("\\s+").length>=2){
								String regStr1 = "`([^`]*)`\\s*([^\\s]*)\\s*.*COMMENT\\s*'([^']*)'";//有注释情况								
								Pattern p = Pattern.compile(regStr1);
								Matcher m = p.matcher(fieldTemps[i].trim());
								if(m.matches()){
									fields[j] = m.group(1);
									fieldTypes[j] = m.group(2);
									fieldComments[j] = m.group(3);
//									System.out.println("fieldComments[j]: " + fieldComments[j]);
									j++;
								}else{
									String regStr2 = "`([^`]*)`\\s*([^\\s]*)\\s*.*"; //无注释情况
									p = Pattern.compile(regStr2);
									m = p.matcher(fieldTemps[i].trim());
									if(m.matches()){
										fields[j] = m.group(1);
										fieldTypes[j] = m.group(2);
										fieldComments[j] = m.group(1);
										j++;
									}
								}
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
							String comment = fieldComments[i];
							if(name!=null && name.trim().length()>0 && type!=null && type.trim().length()>0){
								name = name.trim();
								field = new Field();
								field.setDbName(name);
								field.setName(getJavaFieldNameByDBField(name));
								field.setDbType(type);
								field.setType(TypeUtil.getJavaFieldMysqlTypeByDBType(type));
								field.setComment(comment);
								
								orderList.add(name);
								fieldMap.put(name, field);
							}
						}
//						fieldMap = commontField(cg, fieldMap);
					}
					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return fieldMap;
	}
//	public static void main(String args[]){
//		String str = "`activityname` VARCHAR(40) COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '动作名称'";
//		String regStr = "`([^`]*)`\\s*([^\\s]*)\\s*.*COMMENT\\s*'([^']*)'"; //`activityname` VARCHAR(40) COLLATE gbk_chinese_ci DEFAULT NULL COMMENT '动作名称'
//		Pattern p = Pattern.compile(regStr);
//		Matcher m = p.matcher(str);
//		m.matches();
//		System.out.println("m.group(1):" + m.group(1));
//		System.out.println("m.group(2):" + m.group(2));
//		System.out.println("m.group(3):" + m.group(3));
//	}
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
		System.out.println("getJavaFieldNameByDBField:" + str);
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
			try{
//				tableSql=tableSql.replaceAll("--.*\r\n","\r\n");
				String[] strs = tableSql.split("\r\n");
				
				if(strs!=null && strs.length>0){
					for(int i=0;i<strs.length;i++){
						if(strs[i].trim().toLowerCase().startsWith("create table")){
							Pattern p = Pattern.compile("create table\\s+`([^`]*)`.*");
							Matcher m = p.matcher(strs[i].trim().toLowerCase());
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
	
}
