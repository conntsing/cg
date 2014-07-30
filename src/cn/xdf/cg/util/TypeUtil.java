package cn.xdf.cg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种类型转换
 * 
 * @author qiusen
 *
 * 2012-1-31
 */
public class TypeUtil {
	/**
	 * 根据数据库字段类型获取java文件属性类型
	 * @param type
	 * @return
	 */
	public static String getJavaFieldOracleTypeByDBType(String type){
		String str = "String";
		String typeUC = type.toUpperCase();
		if(typeUC.equalsIgnoreCase("NUMBER") || typeUC.startsWith("INTEGER")){
			str = "Integer";
		}else if(typeUC.startsWith("NUMBER")){
			str = "Double";
		}else if(typeUC.equalsIgnoreCase("LONG")){
			str = "Long";
		}else if(typeUC.equalsIgnoreCase("DATE") || typeUC.startsWith("TIMESTAMP")){
			str = "java.util.Date";
		}else if(typeUC.startsWith("DECIMAL")){
//			Pattern pattern = Pattern.compile("DECIMAL//(//d+//s.,//s.(//d+)//)");
//			Matcher matcher = pattern.matcher(typeUC);
//			String numStr = "0";
//			if (matcher.find()){
//				numStr = matcher.group(1);
//			}
//			System.out.println("numStr-----------" + numStr);
//			if(numStr.equals("0")){
//				str = "Long";
//			}else{
//				str = "Double";
//			}
			str = "Long";
		}
		System.out.println(type + "----------------" + str);
		return str;
	}
	
	public static String getJavaFieldMysqlTypeByDBType(String type){
		String str = "String";
		String typeUC = type.toUpperCase();
		if(type.toUpperCase().startsWith("INT") ||type.toUpperCase().startsWith("TINYINT")){
			str = "Integer";
		}else if(type.toUpperCase().startsWith("DATETIME")){
			str = "java.util.Date";
		}else if(typeUC.startsWith("DECIMAL")){
			str = "Long";
		}
		System.out.println(type + "----------------" + str);
		return str;
	}
}
