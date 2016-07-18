package com.felink.guihubcode.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import android.graphics.Bitmap;
import android.util.Log;

public class FileFun
{

	private static final String	TAG	= "FileFun";

	/**
	 * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
	 * 
	 * @param res
	 *            原字符串
	 * @param filePath
	 *            文件路径
	 * @return 成功标记
	 */
	public static boolean string2File(String res, File distFile)
	{
		boolean flag = true;
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try
		{
			if (!distFile.getParentFile().exists())
				distFile.getParentFile().mkdirs();
			bufferedReader = new BufferedReader(new StringReader(res));
			bufferedWriter = new BufferedWriter(new FileWriter(distFile));
			char buf[] = new char[1024]; // 字符缓冲区
			int len;
			while ((len = bufferedReader.read(buf)) != -1)
			{
				bufferedWriter.write(buf, 0, len);
			}
			bufferedWriter.flush();
			bufferedReader.close();
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			flag = false;
			return flag;
		}
		finally
		{
			if (bufferedReader != null)
			{
				try
				{
					bufferedReader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static void DeleteFile(String filePath)
	{
		File distFile = new File(filePath);
		if (distFile.exists())
			distFile.delete();
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 */
	public static void DeleteFile(File file)
	{
		if (file.exists())
			file.delete();
	}

	/**
	 * 删除文件夹
	 * 
	 * @param f
	 * @return
	 */
	public static boolean DeleteDir(File f)
	{
		// if (f!=null && f.exists() && f.isDirectory())
		if (f != null && f.exists())
		{
			File[] files = f.listFiles();
			for (int i = 0; i < files.length; i++)
			{
				if (files[i].isDirectory())
					DeleteDir(files[i]);
				else
					DeleteFile(files[i]);
			}
			f.delete();
			return true;
		}
		else
			return false;

	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * 
	 * @param filePath
	 * @return
	 */
	public static String readStrFromFile(File file)
	{
		StringBuffer sb = new StringBuffer();
		if (file.exists())
		{
			BufferedReader reader = null;
			try
			{
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null)
				{
					if (!"".equals(tempString))
						sb.append(tempString);
				}
			}
			catch (IOException e)
			{
				Log.e(TAG, "readStrFromFile IOException");
			}
			finally
			{
				if (reader != null)
				{
					try
					{
						reader.close();
					}
					catch (IOException e1)
					{
						Log.e(TAG, "readStrFromFile close IOException");
					}
				}
			}
		}
		return sb.toString();
	}

	public static void writeFileData(File file, String message)
			throws IOException
	{
		FileOutputStream os = null;
		if (file.exists())
			file.delete();
		os = new FileOutputStream(file);
		os.write(message.getBytes());
		os.flush();
		os.close();
	}

	/**
	 * 不含点的扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExt(File file)
	{
		if (file != null)
		{
			String fileName = file.getName();
			if (fileName.length() > 0)
			{
				int i = fileName.lastIndexOf('.');
				if ((i > -1) && (i < (fileName.length() - 1)))
				{
					return fileName.substring(i + 1);
				}
			}
		}
		return null;

	}
	
	
	public static  byte[] Bitmap2Bytes(Bitmap bm)
	{  
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  
		return baos.toByteArray();  
	}  

	/**
	 * 读文件字节数组
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] GetFileBytes(File file)
	{
		byte[] buffer = null;
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(file);
			int size = fis.available();
			int byteread = 0;
			int offset = 0;
			buffer = new byte[size];
			while (offset < size)
			{
				if ((size - offset) >= 1024)
					byteread = fis.read(buffer, offset, 1024);
				else
					byteread = fis.read(buffer, offset, size - offset);
				offset += byteread;
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (fis != null)
					fis.close();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
		return buffer;
	}
}
