package com.fd.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

/*  
 * 功能：压缩文件成tar.gz格式  
 */
public class TarUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TarUtil.class);

	private static int BUFFER = 1024 * 4; // 缓冲大小

	private static byte[] B_ARRAY = new byte[BUFFER];

	/*
	 * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
	 */
	public static void compress(File inputFile, File targetFile) {
		tar(inputFile, targetFile);
		compress(targetFile);
		targetFile.delete();
	}

	/*
	 * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
	 */
	public static void tar(File inputFile, File targetFile) {
		// String inputFileName = "F://ServiceManage.war//ServiceManage.war";
		// String targetFileName = "F://ServiceManage.tar";
		String inputFileName = inputFile.getPath();
		String base = inputFileName
				.substring(inputFileName.lastIndexOf(File.separator) + 1);
		System.out.println("base=" + base);
		TarOutputStream out = getTarOutputStream(targetFile);
		tarPack(out, inputFile, base);
		try {
			if (null != out) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 方法功能：打包多个文件或文件夹 参数：inputFileNameList 要打包的文件夹或文件的路径的列表 targetFileName
	 * 打包后的文件路径
	 */
	public static void compress(File [] inputFileList,
			File targetFile) {
		tar(inputFileList, targetFile);
		compress(targetFile);
		targetFile.delete();
	}

	/*
	 * 方法功能：打包多个文件或文件夹 参数：inputFileNameList 要打包的文件夹或文件的路径的列表 targetFileName
	 * 打包后的文件路径
	 */
	public static void tar(File [] inputFileList,
			File targetFile) {
		File floderPath = targetFile.getParentFile();
		if (!floderPath.isDirectory()) {
			floderPath.mkdirs();
		}
		TarOutputStream out = getTarOutputStream(targetFile);

		for (File inputFile : inputFileList) {
			String inputFileName = inputFile.getPath();
			String base = inputFileName.substring(inputFileName
					.lastIndexOf(File.separator) + 1);
			tarPack(out, inputFile, base);
		}

		try {
			if (null != out) {
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 方法功能：打包多个文件或文件夹 参数：inputFileNameList 要打包的文件夹或文件的路径的列表 targetFileName
	 * 打包后的文件路径
	 */
	public static void tar(List<File> inputFileList,
			File targetFile) {
		File[] f = new File[inputFileList.size()];
		for (int i=0; i<inputFileList.size(); i++) {
			f[i] = inputFileList.get(i);
		}
		tar(f, targetFile);
	}

	/*
	 * 方法功能：打包成tar文件 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
	 */

	private static void tarPack(TarOutputStream out, File inputFile, String base) {
		if (inputFile.isDirectory()) // 打包文件夹
		{
			packFolder(out, inputFile, base);
		} else
		// 打包文件
		{
			packFile(out, inputFile, base);
		}
	}

	/*
	 * 方法功能：遍历文件夹下的内容，如果有子文件夹，就调用tarPack方法 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件
	 * base 打包文件中的路径
	 */
	private static void packFolder(TarOutputStream out, File inputFile,
			String base) {
		File[] fileList = inputFile.listFiles();
		try {
			// 在打包文件中添加路径
			out.putNextEntry(new TarEntry(base + "/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		base = base.length() == 0 ? "" : base + "/";
		for (File file : fileList) {
			tarPack(out, file, base + file.getName());
		}
	}

	/*
	 * 方法功能：打包文件 参数：out 压缩后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
	 */
	private static void packFile(TarOutputStream out, File inputFile,
			String base) {
		logger.info("tar file: " + inputFile.getPath());
		TarEntry tarEntry = new TarEntry(base);

		// 设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
		tarEntry.setSize(inputFile.length());
		try {
			out.putNextEntry(tarEntry);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInputStream in = null;
		try {
			in = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int b = 0;

		try {
			while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err
					.println("NullPointerException info ======= [FileInputStream is null]");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.closeEntry();
				}
			} catch (IOException e) {

			}
		}
	}

	/*
	 * 方法功能：把打包的tar文件压缩成gz格式 参数：srcFile 要压缩的tar文件路径
	 */
	public static void compress(File srcFile) {
		String gzname = srcFile.getAbsolutePath() + ".gz";
		System.out.println("compress " + gzname + " ...");
		File target = new File(gzname);
		FileInputStream in = null;
		GZIPOutputStream out = null;
		try {
			in = new FileInputStream(srcFile);
			out = new GZIPOutputStream(new FileOutputStream(target));
			int number = 0;
			while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
				out.write(B_ARRAY, 0, number);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}

				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 方法功能：获得打包后文件的流 参数：targetFileName 打包后文件的路径
	 */
	private static TarOutputStream getTarOutputStream(File targetFile) {
		// 如果打包文件没有.tar后缀名，就自动加上
		String targetFileName = targetFile.getPath();
		targetFileName = targetFileName.endsWith(".tar") ? targetFileName
				: targetFileName + ".tar";
		targetFile = new File(targetFileName);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(targetFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);
		TarOutputStream out = new TarOutputStream(bufferedOutputStream);

		// 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
		out.setLongFileMode(TarOutputStream.LONGFILE_GNU);
		return out;
	}

	/*
	 * 方法功能：复制文件到指定目录
	 */
	public static boolean copyFile(String fileFromPath, String fileToPath) {
		boolean b = false;
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(fileFromPath);
			out = new FileOutputStream(fileToPath);
			int length = in.available();
			int len = (length % 1024 == 0) ? (length / 1024)
					: (length / 1024 + 1);
			byte[] temp = new byte[1024];
			for (int i = 0; i < len; i++) {
				in.read(temp);
				out.write(temp);
			}
			b = true;
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {

			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public static void main(String[] args) {
		try {
			File f = new File("D:/Apache2.2/htdocs/seo/");
			File [] files = f.listFiles();
			TarUtil.tar(f, new File("e:/testgz1.tar"));
			System.out.println("-----------------------");
			TarUtil.tar(files, new File("e:/testgz2.tar"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}