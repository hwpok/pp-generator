package com.huiwanpeng.ppcg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

public class DirecUtil
{
    /**
     * 查找指定文件夹的所有文件
     * 
     * @param directPath
     * @return
     */
    public static List<String> getDirectFileNames(String directPath)
    {
        List<String> fileNames = new ArrayList<String>();
        try
        {
            File direct = new File(directPath);
            if (!direct.isDirectory())
            {
                throw new ComRuntimeException("A", "the directory path " + directPath + " is not directory!");
            }
            File[] files = direct.listFiles();
            for (File file : files)
            {
                fileNames.add(file.getName());
            }
        }
        catch (ComRuntimeException erex)
        {
            throw erex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A", "the directory path " + directPath + " is not directory!");
        }
        return fileNames;
    }
    
    /**
     * 得到文件夹里的某一些文件, 并过滤某一些文件, 必须包含Mapping的配套文件
     * @param directPath文件夹路径
     * @param noShowStr包含这些字符串过滤掉
     * @param mustEndWidth必段以这个后缀结尾
     * @return
     */
    public static List<String> fileterDirectFileNames(String directPath, String noShowStr, String mustEndWidth)
    {
        List<String> resFileNames = new ArrayList<String>();
        
        List<String> fileNames = getDirectFileNames(directPath);
        for (String fileName : fileNames)
        {
            // 必须以mustEndWidth字段串结尾
            if (fileName.toUpperCase().endsWith(mustEndWidth.toUpperCase()))
            {
                // 文件名中不能含有这些字符
                if (fileName.toUpperCase().indexOf(noShowStr.toUpperCase()) < 0)
                {
                    String mappingFltFile = getMappingFltFileNameByDaoFltFileName(fileName);
                    String mappingFltPath = directPath + File.separator + mappingFltFile;
                    // dao的Mapping配套文件存在, 才加入到显示文件夹中
                    if(fileExist(mappingFltPath)){
                        resFileNames.add(fileName);
                    }
                }
            }
        }
        return resFileNames;
    }
    
    /**
     * 根据DAO的名称得到Mapping文件的名称
     * @param daoFltFileName
     * @return
     */
    public static String getMappingFltFileNameByDaoFltFileName(String daoFltFileName){
        return daoFltFileName.replace("_dao.flt", "_mapping.flt");
    }
    
    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     */
    public static boolean fileExist(String filePath){
        boolean isExist = false;
        try{
            File file = new File(filePath);
            isExist =  (file.exists() && file.isFile());
        }catch(Exception ex){}
        return isExist;
    }
    
    /**
     * 判断文件夹是否存在
     * @param filePath
     * @return
     */
    public static boolean directExist(String filePath){
        boolean isExist = false;
        try{
            File file = new File(filePath);
            isExist =  (file.exists() && file.isDirectory());
        }catch(Exception ex){}
        return isExist;
    }
    
    /**
     * 得到文件的目录, 如果是目录, 直接返回该目录的地址, 如果是一个文件得到文件所在目录的地址
     * @param filePath
     * @return
     */
    public static String getFileParentDirect(String filePath){
        String resFilePath = null;
        try{
            File file = new File(filePath);
            
            // 如果文件不存在
            if(!file.exists()){
                return resFilePath;
            }
            
            // 如果是目录, 直接返回目录
            if(file.isDirectory()){
                resFilePath =  file.getAbsolutePath();
            }
            
            // 如果是文件, 返回文件的所在目录
            else{
                resFilePath = file.getParentFile().getAbsolutePath();
            }
        }
        catch(Throwable throwable){
        }
        return resFilePath;
    }
    
    /**
     * 从文件后缀得到文件名后缀, 不要以.分割以后的部份
     * @param suffixName文件名
     * @param defaultSuffix默认后缀
     * @return
     */
    public static String getSuffix(String suffixName){
        // 传入为空, 返加""
        if(null == suffixName){
            return "";
        }
        
        // 如果是.分部的值, 只取第一个点前面的部份
        if(suffixName.split("[.]").length>1){
            return suffixName.split("[.]")[0];
        }
        
        return suffixName;
    }
    
    /**
     * 得到文件后缀, 带文件类型, 如果没有文件类型, 那么使用默认的类型
     * @param suffixName
     * @param defaultType
     * @return
     */
    public static String getSuffixAndType(String suffixName, String defaultType){
        // 如果传入是空, 那么返回"".type
        if(null == suffixName){
            return "" + defaultType;
        }
        
        // 如果没有.分隔, suffixName.type
        if(suffixName.indexOf(".")<0){
            return suffixName + defaultType;
        }
        
        return suffixName;
    }
    
    /**
     * 从路径中得到文件名
     * @param filePaht
     * @return
     */
    public static String getFileName(String filePath){
        if(filePath != null){
            String[] tempArray = filePath.split("[/|\\\\]");
            if(0 != tempArray.length){
                return tempArray[tempArray.length -1];
            }
        }
        return "";
    }
    
    /**
     * 从路径中得到文件名
     * @param filePaht
     * @return
     */
    public static String getFilePathNoFileName(String filePath){
        StringBuilder builder = new StringBuilder();
        if(filePath != null){
            String[] tempArray = filePath.split("[/|\\\\]");
            String temp = null;
            for(int i=0;i< tempArray.length - 1; i++){
                temp = tempArray[i];
                // 这个关断可以去掉第一个分隔符
                if(null != temp && temp.length()>0){
                    builder.append(File.separator);
                    builder.append(temp);
                }
            }
        }
        builder.append(File.separator); // 最后再加上文件分隔符
        return builder.toString();
    }
    
    /**
     * 为了适应多操作系统, 重构路径, 路径以分隔符开始
     * @param filePaht
     * @return
     */
    public static String rebuildFilePath(String filePath){
        StringBuilder builder = new StringBuilder();
        if(filePath != null){
            String[] tempArray = filePath.split("[/|\\\\]");
            for(String temp : tempArray){
                // 这个关断可以去掉第一个分隔符
                if(null != temp && temp.length()>0){
                    builder.append(File.separator);
                    builder.append(temp);
                }
            }
        }
        return builder.toString();
    }
    
}
