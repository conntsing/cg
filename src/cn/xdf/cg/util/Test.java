package cn.xdf.cg.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.qiusen.cg.model.Field;

public class Test {
	public static void main(String[] args){
//		String str = "_user_right_hehe";
//		if(str!=null && str.trim().length()>0){
//			str = str.trim().toLowerCase();
//			while(str.indexOf("_")>-1){
//				int i=0;
//				i=str.indexOf("_");
//				str = str.substring(0,i)+str.substring(i+1, i+2).toUpperCase()+str.substring(i+2, str.length());
//				System.out.println(str);
//			}
//			str = str.substring(0,1).toUpperCase() + str.substring(1,str.length());
//			System.out.println(str);
//		}
		
		String sql = "-- Create table\r\n" +
"create table LOG_DETAIL\r\n" +
"(\r\n" +
"  ID           NUMBER not null,\r\n" +
"  LOGDATE      NUMBER,\r\n" +
"  LOGFILE      VARCHAR2(200),\r\n" +
"  REFERRER     VARCHAR2(200),\r\n" +
"  URL          VARCHAR2(200) not null,\r\n" +
"  ARTICLEID    NUMBER default 0,\r\n" +
"  TYPE         NUMBER(2) default 0,\r\n" +
"  VSCOOKIEID   VARCHAR2(200),\r\n" +
"  UVCOOKIEID   VARCHAR2(200),\r\n" +
"  IP           VARCHAR2(20),\r\n" +
"  STATUS       NUMBER(1) default 0,\r\n" +
"  OS           VARCHAR2(50),\r\n" +
"  BROWSER      VARCHAR2(50),\r\n" +
"  RESOLUTION   VARCHAR2(30),\r\n" +
"  COLOR        VARCHAR2(20),\r\n" +
"  FLASHVERSION VARCHAR2(5),\r\n" +
"  INPUTDATE    DATE default SYSDATE,\r\n" +
"  PARM1        VARCHAR2(50),\r\n" +
"  PARM2        VARCHAR2(50),\r\n" +
"  PARM3        VARCHAR2(50)\r\n" +
")\r\n" +
"tablespace COUNT2010DATA\r\n" +
"  pctfree 10\r\n" +
"  initrans 1\r\n" +
"  maxtrans 255\r\n" +
"  storage\r\n" +
"  (\r\n" +
"    initial 64K\r\n" +
"    minextents 1\r\n" +
"    maxextents unlimited\r\n" +
"  );\r\n" +
"-- Add comments to the table \r\n" +
"comment on table LOG_DETAIL  is '详细日志表，每天一张日志表';\r\n" +
"-- Add comments to the columns \r\n" +
"comment on column LOG_DETAIL.ID  is '通过sequence添加,LOGDETAILID';\r\n" +
"comment on column LOG_DETAIL.LOGDATE  is '整形为 如：2010011713';\r\n" +
"comment on column LOG_DETAIL.LOGFILE  is 'log文件';\r\n" +
"comment on column LOG_DETAIL.REFERRER  is 'url的referrer';\r\n" +
"comment on column LOG_DETAIL.URL  is '访问的url地址';\r\n" +
"comment on column LOG_DETAIL.ARTICLEID  is '和type配合使用，以确定articleid';\r\n" +
"comment on column LOG_DETAIL.TYPE  is '1 cms     2 product     3 群乐     4 52pk  5 图片 6 电影和各个系统配合使用，以确定articleid';\r\n" +
"comment on column LOG_DETAIL.VSCOOKIEID  is 'cookie产生的用户30分钟id';\r\n" +
"comment on column LOG_DETAIL.UVCOOKIEID  is 'cookie产生的用户唯一id';\r\n" +
"comment on column LOG_DETAIL.IP  is '用户访问的ip地址';\r\n" +
"comment on column LOG_DETAIL.STATUS  is '0 未被统计     1 被统计';\r\n" +
"comment on column LOG_DETAIL.OS  is '操作系统';\r\n" +
"comment on column LOG_DETAIL.BROWSER  is '浏览器';\r\n" +
"comment on column LOG_DETAIL.RESOLUTION  is '分辨率';\r\n" +
"comment on column LOG_DETAIL.COLOR  is '颜色位数，比如32';\r\n" +
"comment on column LOG_DETAIL.FLASHVERSION  is 'falash的版本号';\r\n" +
"comment on column LOG_DETAIL.INPUTDATE  is '插入记录时间';\r\n" +
"comment on column LOG_DETAIL.PARM1  is '比如产品库 传291，294，想应有对应表';\r\n" +
"comment on column LOG_DETAIL.PARM2  is 'parm2';\r\n" +
"comment on column LOG_DETAIL.PARM3  is 'parm3';\r\n" +
"-- Create/Recreate primary, unique and foreign key constraints \r\n" +
"alter table LOG_DETAIL\r\n" +
"  add constraint PK_LOGDETAIL$ primary key (ID)\r\n" +
"  using index \r\n" +
"  tablespace COUNT2010DATA\r\n" +
"  pctfree 10\r\n" +
"  initrans 2\r\n" +
"  maxtrans 255\r\n" +
"  storage\r\n" +
"  (\r\n" +
"    initial 64K\r\n" +
"    minextents 1\r\n" +
"    maxextents unlimited\r\n" +
"  );\r\n";
		
//		sql=sql.replaceAll("--.*\r\n","\r\n");
//		String tempString=sql;//.replaceAll("\r\n"," ");
//		String[] strs = sql.split("\r\n");
//		StringBuffer strbuf = new StringBuffer();
//		int k=0;
//		if(strs!=null && strs.length>0){
//			for(int i=0;i<strs.length;i++){
//				if(strs[i].trim().startsWith("create table")){
//					System.out.println(strs[i].trim());
//					Pattern p = Pattern.compile("create table\\s+(.*)");
//					Matcher m = p.matcher(strs[i].trim());
//					m.matches();
//					System.out.println(m.group(1));
//					break;
//				}
//				
//			}
//		}
		Pattern commentPattern = Pattern.compile("\\s*COMMENT\\s+?ON\\s+?COLUMN\\s+?.*\\.(.*) IS '(.*)'\\s*;");
		String[] temps=sql.split("\r|\n");
		for(int i=0;i<temps.length;i++){
			Matcher commentMatcher = commentPattern.matcher(temps[i].trim().toUpperCase());
			if(commentMatcher.matches()){
				System.out.println(commentMatcher.group(1) + "	" + commentMatcher.group(2));
			}
		}
//		
////		String regexStr = ".*create table ([^\\(]*)\\(((\\s*[A-Z0-9\\_]*\\s*.*\\,){1,}\\s*[A-Z0-9\\_]*\\s*[A-Z0-9]*\\s*)\\).*";
////		Pattern p = Pattern.compile(regexStr);
////		Matcher m = p.matcher(sql.replaceAll("--.*\r\n"," ").replaceAll("\r\n"," "));
////		m.matches();
////		String str1 = m.group(1);
////		String str2 = m.group(2);
////		System.out.println(str1);
////		System.out.println(str2);
		
		
		
//		Pattern commentPattern = Pattern.compile("COMMENT\\s+?ON\\s+?COLUMN\\s+?.*\\.(.*) IS '(.*)'\\s*;");
////		Pattern commentPattern = Pattern.compile("COMMENT\\s+?ON\\s+?COLUMN\\s+?.*\\.(.*)");
//		String[] temps=sql.split("\r|\n");
//		
//		for(int i=0;i<temps.length;i++){
//			System.out.println(temps[i]);
//			Matcher matcher = commentPattern.matcher(temps[i].trim().toUpperCase());
//			if(matcher.matches()){
//				String name = matcher.group(1);
//				String comment = matcher.group(2);
//				System.out.println(name + "---" + comment);
////				System.out.println("=============");
//			}
//		}
		
	}
}
