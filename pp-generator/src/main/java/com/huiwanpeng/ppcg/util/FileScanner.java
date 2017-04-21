package com.huiwanpeng.ppcg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner
{
    /**
     * 得到指定文件夹下的Xml文件
     * @param baseDirectPath
     * @return
     */
    public static List<String> getXmlFiles(String baseDirectPath){
        List<String> filePathLst = new ArrayList<String>();
        scanFile(baseDirectPath, "xml", filePathLst);
        return filePathLst;
    }
    
    /**
     * 递归扫描找出指定文件夹下的指定类型的文件
     * @param baseDirectPath基本文件夹
     * @param fileSuffix文件后缀
     * @param filePathLst保存列表
     */
    public static void scanFile(String baseDirectPath, String fileSuffix,  List<String> filePathLst){
        File baseFile = new File(baseDirectPath);
        if(baseFile.exists() && baseFile.isFile()){
            if(baseFile.getAbsolutePath().toLowerCase().endsWith(fileSuffix)){
                filePathLst.add(baseFile.getAbsolutePath());
            }
        }
        else{
           File[] files =  baseFile.listFiles();
           for(File tempFile : files){
               scanFile(tempFile.getAbsolutePath(), fileSuffix, filePathLst);
           }
        }
    }
}
