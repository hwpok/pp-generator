package com.huiwanpeng.ppcg.util.logs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.huiwanpeng.ppcg.util.DateUtil;
import com.huiwanpeng.ppcg.util.FilePathUtil;

/**
 * 日志记录器
 * 
 * @version 1.0
 */
public class Logger
{
    /**
     * 正常信息
     * 
     * @param i
     */
    public static void i(String i)
    {
        String filePath = getFilePath(0);
        System.out.println(i);
        write2file(filePath, getContent(i), true);
    }
    
    public static void e(String e)
    {
        String filePath = getFilePath(1);
        System.out.println(e);
        write2file(filePath, getContent(e), true);
    }
    
    /**
     * 错误信息 清空日志文件
     */
    public static void clearLogs()
    {
        String filePath = getFilePath(0);
        write2file(filePath, "", false);
        filePath = getFilePath(1);
        write2file(filePath, "", false);
    }
    
    /**
     * 得到日志内容
     * 
     * @param str
     * @return
     */
    private static String getContent(String str)
    {
        StringBuilder content = new StringBuilder();
        content.append(DateUtil.getTimeStr(new Date()));
        content.append(" Xbatis Generator ");
        content.append(str);
        content.append(System.getProperty("line.separator"));
        return content.toString();
    }
    
    /**
     * 得到日志文件的绝对路径
     * 
     * @param type
     * @return
     */
    public static String getFilePath(int type)
    {
        StringBuilder filePath = new StringBuilder();
        filePath.append(getLogFilePathDirector(type));
        if (0 == type)
        {
            filePath.append("info.log");
        }
        else if (1 == type)
        {
            filePath.append("error.log");
        }
        return filePath.toString();
    }
    
    /**
     * 日志文件所在的文件夹
     * 
     * @param type
     * @return
     */
    public static String getLogFilePathDirector(int type)
    {
        StringBuilder filePath = new StringBuilder();
        filePath.append(FilePathUtil.getBasePath());
        filePath.append(File.separator);
        filePath.append("log").append(File.separator);
        return filePath.toString();
    }
    
    /**
     * 把日志内容写到文件
     * 
     * @param filePath
     * @param content
     * @param append
     */
    private static void write2file(String filePath, String content, boolean append)
    {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try
        {
            File file = new File(filePath);
            fos = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
