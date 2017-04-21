package com.huiwanpeng.ppcg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * 操作文件帮助对象
 * @version 1.0  
 */
public class FileUtil
{
    /**
     * 得到正确的文件路径
     * @param directPath文件夹路径
     * @param fileName文件名
     * @return返回完整文件的路径
     */
    public static String getFilePath(String directPath, String fileName){
        // 处理C盘根目录以/结尾的情况
        directPath = directPath.replace("/", File.separator);
        if(directPath.endsWith(File.separator)){
            return directPath + fileName;
        }
        return directPath + File.separator + fileName;
    }
    
    /**
     * 把字符写到指定文件
     * @param filePath文件路径
     * @param content写入路径
     */
    public static void writeFile(String filePath, String content){
        writeFile(filePath, "UTF-8", content);
    }
    
    /**
     * 把字符写到指定文件
     * @param filePath文件路径
     * @param charactorEncoding编码字符集
     * @param content写入路径
     */
    public static void writeFile(String filePath, String charactorEncoding, String content)
    {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try
        {
            File file = new File(filePath);
            fos = new FileOutputStream(file);
            osw = new OutputStreamWriter(fos, charactorEncoding);
            osw.write(content);
        }
        catch (Exception e)
        {
            throw new ComRuntimeException("A9", "save file " + filePath + " error.", e);
        }
        finally
        {
            if (osw != null)
            {
                try
                {
                    osw.close();
                }
                catch (Exception ex)
                {}
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception ex)
                {}
            }
        }
    }
}
