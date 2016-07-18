package com.felink.guihubcode.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;




public class ZipHelp {
	

	// 输入解压文件路径为空
	public static final int NULL_ZIPPATH = 0;

	// 解压文件不存在
	public static final int NOTEXIST_ZIPFILE = 1;

	// 输入的解压路径已经存在
	public static final int EXIST_UNZIPFILE = 2;

	// 操作成功
	public static final int ZIPOPTION_SUCCESS = 3;

	// 输入的压缩文件已经存在于该路径中
	public static final int EXIST_ZIPFILE = 4;

	// 操作失败
	public static final int ZIPOPTION_FAIL = 5;
	
	/**
	 * 取得压缩包中的 文件列表(文件夹,文件自选)
	 * 
	 * @param zipFileString
	 *            压缩包名字
	 * @param bContainFolder
	 *            是否包括 文件夹
	 * @param bContainFile
	 *            是否包括 文件
	 * @return
	 * @throws Exception
	 */
	public static java.util.List<File> GetFileList(String zipFileString, boolean bContainFolder, boolean bContainFile)
			throws Exception {

		android.util.Log.v("XZip", "GetFileList(String)");

		java.util.List<File> fileList = new java.util.ArrayList<File>();
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipFileString));
		ZipEntry zipEntry;
		String szName = "";

		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();

			if (zipEntry.isDirectory()) {

				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				File folder = new File(szName);
				if (bContainFolder) {
					fileList.add(folder);
				}

			} else {
				File file = new File(szName);
				if (bContainFile) {
					fileList.add(file);
				}
			}
		}// end of while

		inZip.close();

		return fileList;
	}

	/**
	 * 返回压缩包中的文件InputStream
	 * 
	 * @param zipFileString
	 *            压缩文件的名字
	 * @param fileString
	 *            解压文件的名字
	 * @return InputStream
	 * @throws Exception
	 */
	public static InputStream UpZip(String zipFileString, String fileString) throws Exception {
		android.util.Log.v("XZip", "UpZip(String, String)");
		ZipFile zipFile = new ZipFile(zipFileString);
		ZipEntry zipEntry = zipFile.getEntry(fileString);

		return zipFile.getInputStream(zipEntry);

	}

	// public static void unZipFile(String archive, String decompressDir) throws
	// IOException, FileNotFoundException, ZipException {
	// BufferedInputStream bi;
	// ZipFile zf = new ZipFile(archive, "GBK");
	// Enumeration e = zf.getEntries();
	// while (e.hasMoreElements()) {
	// ZipEntry ze2 = (ZipEntry) e.nextElement();
	// String entryName = ze2.getName();
	// String path = decompressDir + "/" + entryName;
	// if (ze2.isDirectory()) {
	// System.out.println("正在创建解压目录 - " + entryName);
	// File decompressDirFile = new File(path);
	// if (!decompressDirFile.exists()) {
	// decompressDirFile.mkdirs();
	// }
	// } else {
	// System.out.println("正在创建解压文件 - " + entryName);
	// String fileDir = path.substring(0, path.lastIndexOf("/"));
	// File fileDirFile = new File(fileDir);
	// if (!fileDirFile.exists()) {
	// fileDirFile.mkdirs();
	// }
	// BufferedOutputStream bos = new BufferedOutputStream(new
	// FileOutputStream(decompressDir + "/" + entryName));
	// bi = new BufferedInputStream(zf.getInputStream(ze2));
	// byte[] readContent = new byte[1024];
	// int readCount = bi.read(readContent);
	// while (readCount != -1) {
	// bos.write(readContent, 0, readCount);
	// readCount = bi.read(readContent);
	// }
	// bos.close();
	// }
	// }
	// zf.close();
	// // bIsUnzipFinsh = true;
	// }

	/**
	 * 解压一个压缩文档 到指定位置
	 * 
	 * @param zipFileString
	 *            压缩包的名字
	 * @param outPathString
	 *            指定的路径
	 * @throws Exception
	 */
	public static void UnZipFolder(String zipFileString, String outPathString) throws Exception {
		android.util.Log.v("XZip", "UnZipFolder(String, String)");
		java.util.zip.ZipInputStream inZip = new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipFileString));
		ZipEntry zipEntry;
		String szName = "";

		while ((zipEntry = inZip.getNextEntry()) != null) {
			szName = zipEntry.getName();

			if (zipEntry.isDirectory()) {

				// get the folder name of the widget
				szName = szName.substring(0, szName.length() - 1);
				File folder = new File(outPathString + File.separator + szName);
				folder.mkdirs();

			} else {

				File file = new File(outPathString + File.separator + szName);
				file.createNewFile();
				// get the output stream of the file
				FileOutputStream out = new FileOutputStream(file);
				int len;
				byte[] buffer = new byte[1024];
				// read (len) bytes into buffer
				while ((len = inZip.read(buffer)) != -1) {
					// write (len) byte from buffer at the position 0
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
			}
		}// end of while

		inZip.close();

	}// end of func

	/**
	 * 压缩文件,文件夹
	 * 
	 * @param srcFileString
	 *            要压缩的文件/文件夹名字
	 * @param zipFileString
	 *            指定压缩的目的和名字
	 * @throws Exception
	 */
	public static void ZipFolder(String srcFileString, String zipFileString) throws Exception {
		android.util.Log.v("XZip", "ZipFolder(String, String)");

		// 创建Zip包
		java.util.zip.ZipOutputStream outZip = new java.util.zip.ZipOutputStream(new FileOutputStream(zipFileString));

		// 打开要输出的文件
		File file = new File(srcFileString);

		// 压缩
		ZipFiles(file.getParent() + File.separator, file.getName(), outZip);

		// 完成,关闭
		outZip.finish();
		outZip.close();

	}// end of func

	/**
	 * 压缩文件
	 * 
	 * @param folderString
	 * @param fileString
	 * @param zipOutputSteam
	 * @throws Exception
	 */
	private static void ZipFiles(String folderString, String fileString, java.util.zip.ZipOutputStream zipOutputSteam) throws Exception {
		android.util.Log.v("XZip", "ZipFiles(String, String, ZipOutputStream)");

		if (zipOutputSteam == null)
			return;

		File file = new File(folderString + fileString);

		// 判断是不是文件
		if (file.isFile()) {

			ZipEntry zipEntry = new ZipEntry(fileString);
			java.io.FileInputStream inputStream = new java.io.FileInputStream(file);
			try {
				zipOutputSteam.putNextEntry(zipEntry);

				int len;
				byte[] buffer = new byte[4096];

				while ((len = inputStream.read(buffer)) != -1) {
					zipOutputSteam.write(buffer, 0, len);
				}

				zipOutputSteam.closeEntry();
			} finally {
				inputStream.close();
			}
		} else {

			// 文件夹的方式,获取文件夹下的子文件
			String fileList[] = file.list();

			// 如果没有子文件, 则添加进去即可
			if (fileList.length <= 0) {
				ZipEntry zipEntry = new ZipEntry(fileString + File.separator);
				zipOutputSteam.putNextEntry(zipEntry);
				zipOutputSteam.closeEntry();
			}

			// 如果有子文件, 遍历子文件
			for (int i = 0; i < fileList.length; i++) {
				ZipFiles(folderString, fileString + File.separator + fileList[i], zipOutputSteam);
			}// end of for

		}// end of if

	}// end of func

	public void finalize() throws Throwable {

	}
	
	/**
	 * <pre>
	 * 解压ZIP文件
	 * </pre>
	 * @param zipFilePath String 需要解压的文件路径
	 * @param unzipPath String 需要解压该文件到哪个路径
	 * @param charset 文件编码
	 * @return 解压结果的状态
	 */
	public static int unzip(String zipFilePath, String unzipPath)
	{
		if (null == zipFilePath || "".equals(zipFilePath.trim()))
		{
			return NULL_ZIPPATH;
		}

		// 需要解压到的目录已经存在时,返回目录已经存在的状态.否则创建目录
		File dirFile = new File(unzipPath);
		if (dirFile.exists())
		{
			return EXIST_UNZIPFILE;
		}
		else
		{
			dirFile.mkdirs();
		}

		// 需要解压的文件不存在时返回解压文件不存在的状态
		File file = new File(zipFilePath);
		if (!file.exists())
		{
			return NOTEXIST_ZIPFILE;
		}

		ZipFile zipFile = null;
		try
		{
			zipFile = new ZipFile(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return ZIPOPTION_FAIL;
		}

		// 获取压缩包中的所有条目
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		ZipEntry entry = null;
		String unzipAbpath = dirFile.getAbsolutePath();

		// 遍历条目,并读取条目流输出到文件流中(即开始解压)
		while (entries.hasMoreElements())
		{
			entry = entries.nextElement();
			unzipEachFile(zipFile, entry, unzipAbpath);
		}
		//String unzipFileMD5 = FileHelp.getFileMD5(dirFile);
		return ZIPOPTION_SUCCESS;
	}

	/**
	 * 对某一条目进行解压
	 * @param zipFile ZipFile 需要解压的文件
	 * @param entry ZipEntry 压缩文件中的条目
	 * @param unzipAbpath String 解压文件的绝对路径
	 */
	private static void unzipEachFile(ZipFile zipFile, ZipEntry entry, String unzipAbpath)
	{
		byte[] buffer = new byte[1024 * 5];
		int readSize = 0;
		String name = entry.getName();
		String fileName = name;
		int index = 0;
		String tempDir = "";

		// 如果条目为目录,则在解压文件路径中创建此目录
		if (entry.isDirectory())
		{
			File tempFile = new File(unzipAbpath + File.separator + name);
			if (!tempFile.exists())
			{
				tempFile.mkdirs();
			}

			return;
		}

		// 不是目录时,由于压缩文件中目录里的文件名为:DIR/DIR/xxxFILE,所以可能文件前的目录并不存在于解压目录中,也需要创建
		if ((index = name.lastIndexOf(File.separator)) != -1)
		{
			fileName = name.substring(index, name.length());
			tempDir = name.substring(0, index);
			File tempDirFile = new File(unzipAbpath + File.separator + tempDir);
			if (!tempDirFile.exists())
			{
				tempDirFile.mkdirs();
			}
		}

		// 使用输入输出流把条目读出并写到解压目录中
		String zipPath = unzipAbpath + File.separator + tempDir + fileName;
		File tempFile = new File(zipPath);
		InputStream is = null;
		FileOutputStream fos = null;
		try
		{
			is = zipFile.getInputStream(entry);
			if (!tempFile.exists())
			{
				tempFile.createNewFile();
			}

			fos = new FileOutputStream(tempFile);
			while ((readSize = is.read(buffer)) > 0)
			{
				fos.write(buffer, 0, readSize);
			}
			fos.flush();
		}
		catch (Exception e)
		{
			new File(unzipAbpath).delete();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				is.close();
				fos.close();
			}
			catch (IOException e)
			{
				new File(unzipAbpath).delete();
				e.printStackTrace();
			}
		}

	}


}
