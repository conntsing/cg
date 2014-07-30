package cn.xdf.cg;

public class Property {
	
	/**
	 * 系统 web 路径 (eg: http://localhost:9090/cg )
	 */
	public static String BASE;
	
	/** Action的扩展名 */
	public static String ACTION_EXT;

	/** request字符集 */
	public static String CHARSET;
	
	public static String PAGESIZE;
	
	/**
	 * 模版位置
	 */
	public static String TEMPLATE_PATH;
	
	/**
	 * 生成文件路径
	 */
	public static String OUTPUT_FILE_PATH;
	
	/**
	 * 生成文件相对路径
	 */
	public static String RELATIVE_FILE_PATH;

	
	public String getBASE() {
		return BASE;
	}

	public void setBASE(String bASE) {
		BASE = bASE;
	}

	public String getACTION_EXT() {
		return ACTION_EXT;
	}

	public void setACTION_EXT(String action_ext) {
		ACTION_EXT = action_ext;
	}

	public String getCHARSET() {
		return CHARSET;
	}

	public void setCHARSET(String charset) {
		CHARSET = charset;
	}

	public String getPAGESIZE() {
		return PAGESIZE;
	}

	public void setPAGESIZE(String pagesize) {
		PAGESIZE = pagesize;
	}

	public String getTEMPLATE_PATH() {
		return TEMPLATE_PATH;
	}

	public void setTEMPLATE_PATH(String template_path) {
		TEMPLATE_PATH = template_path;
	}

	public String getOUTPUT_FILE_PATH() {
		return OUTPUT_FILE_PATH;
	}

	public void setOUTPUT_FILE_PATH(String output_file_path) {
		OUTPUT_FILE_PATH = output_file_path;
	}

	public String getRELATIVE_FILE_PATH() {
		return RELATIVE_FILE_PATH;
	}

	public void setRELATIVE_FILE_PATH(String rELATIVEFILEPATH) {
		RELATIVE_FILE_PATH = rELATIVEFILEPATH;
	}
	
	
}
