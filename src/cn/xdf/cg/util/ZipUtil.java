package cn.xdf.cg.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import cn.xdf.cg.Property;
import cn.xdf.cg.model.Cg;

/**
 * ZIP工具包
 * 
 * @author qiusen
 *
 * Jan 25, 2010
 */
public class ZipUtil {

	/**
	 * 压缩
	 * @param cg
	 */
	public static void doZip(Cg cg) {
		ZipOutputStream zipOut = null;
		File zipDir = new File(Property.OUTPUT_FILE_PATH + "/" + cg.getPackagePath() + "." + cg.getClassName());
		// 压缩后生成的zip文件名
//		String zipFileName = cg.getPackagePath().replaceAll("\\.", "_") + "_" + cg.getClassName()+".zip";
		String zipFileName = cg.getPackagePath() + "." + cg.getClassName()+".zip";
		try {
			File file = new File(Property.OUTPUT_FILE_PATH );
			if(!file.isDirectory())
			{
				file.mkdirs();
			}
			zipOut = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(Property.OUTPUT_FILE_PATH+File.separator+File.separator+zipFileName)));
			handleDir(zipDir, zipOut);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}finally{
			if( zipOut!=null){
				try {
					zipOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 由doZip调用,递归完成目录文件读取
	 * @param dir
	 * @param zipOut
	 */
	private static void handleDir(File dir, ZipOutputStream zipOut) throws IOException {
		
//		System.out.println("-----------dir: " + dir.getAbsolutePath());
//		System.out.println("-----------dir.isDirectory: " + dir.isDirectory());
		FileInputStream fileIn=null;
		int readedBytes = 0;
		byte[] buf = new byte[512];
		File[] files;
		files = dir.listFiles();
//		System.out.println("-----------files.length: " + files.length);
		if (files==null || files.length == 0) {// 如果目录为空,则单独创建之.
			// ZipEntry的isDirectory()方法中,目录以"/"结尾.
			zipOut.putNextEntry(new ZipEntry(dir.toString() + "/"));
			zipOut.closeEntry();
		}else {// 如果目录不为空,则分别处理目录和文件.
			for (File file : files) {
				
//				System.out.println("-----------file: " + file.getName());
				
				if (file.isDirectory()) {
					handleDir(file, zipOut);
				}else{
					try{
						fileIn = new FileInputStream(file);
						if(!file.toString().endsWith(".zip")){
							String temp="";
							if(file.toString().indexOf("com")>0){
								temp=file.toString().substring(file.toString().indexOf("com"), file.toString().length());
							}else{
								temp=file.getName();
							}
//							System.out.println("-----------temp: " + temp);
							zipOut.putNextEntry(new ZipEntry(temp));
							while ((readedBytes = fileIn.read(buf)) > 0) {
								zipOut.write(buf,0,readedBytes);
							}
							zipOut.closeEntry();
						}
					}catch(Exception e){
						
					}finally{
						if(fileIn!=null){
							fileIn.close();
						}
					}
				}
			}
		}
	}
	
}
