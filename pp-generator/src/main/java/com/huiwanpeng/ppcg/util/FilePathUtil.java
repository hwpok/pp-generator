package com.huiwanpeng.ppcg.util;

import java.io.File;

public class FilePathUtil
{
    public static void main(String[] args)
    {
        System.out.println(getBasePath());
    }
    
    /**
     * 得到jar文件所在的目录, 不以\结尾
     * 
     * @return
     */
    private static String getJarDirPath()
    {
        if(Constant.DEV){
            return Constant.TEST_CONFIG_DIRECT;
        }else{
            String path = System.getProperty("java.class.path");
            int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = path.lastIndexOf(File.separator);
            return path.substring(firstIndex, lastIndex);
        }
    }
    
    /**
     * 得到基本目录
     * 
     * @return
     */
    public static String getBasePath()
    {
        if(Constant.DEV){
            return Constant.TEST_CONFIG_DIRECT;
        }
        
        String jarDirPath = getJarDirPath();
        int lastIndex = jarDirPath.lastIndexOf(File.separator);
        return jarDirPath.substring(0, lastIndex);
    }
}
