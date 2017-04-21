package com.huiwanpeng.ppcg.util;

/**
 * 字符处理工具
 * 
 * @version 1.0
 */
public class StrTool
{
    /** 下划线分隔 */
    private static String SPLIT_STR_REG = "[_]";
    
    /**
     * 如果要求驼峰命名, 那么带下划线的字符, 转驼峰命名法,大写第一个字符, 否则只大写第一个字母
     * 
     * @param str源字符
     * @param useCame1Naming是否使用驼峰命名
     * @return
     */
    public static String toCamelUpper1(String str, boolean useCame1Naming)
    {
        if (null != str)
        {
            if (useCame1Naming)
            {
                String[] subTableNames = str.split(SPLIT_STR_REG);
                StringBuffer resBuf = new StringBuffer();
                for (String subTableName : subTableNames)
                {
                    resBuf.append(toUpperCaseFirstOne(subTableName.toLowerCase()));
                }
                return resBuf.toString();
            }
            else
            {
                return toUpperCaseFirstOne(str);
            }
        }
        return "";
    }
    
    /**
     * 如果要求驼峰命名, 那么带下划线的字符, 转驼峰命名法,小写第一个字符, 否则只小写第一个字母
     * 
     * @param str源字符
     * @param useCame1Naming是否使用驼峰命名
     * @return
     */
    public static String toCamelLower1(String str, boolean useCame1Naming)
    {
        if (null != str)
        {
            if (useCame1Naming)
            {
                String[] subTableFields = str.split(SPLIT_STR_REG);
                StringBuffer resBuf = new StringBuffer();
                for (String subTableField : subTableFields)
                {
                    resBuf.append(toUpperCaseFirstOne(subTableField.toLowerCase()));
                }
                return toLowerCaseFirstOne(resBuf.toString());
            }
            else
            {
                return toLowerCaseFirstOne(str);
            }
        }
        return "";
    }
    
    /**
     * 大写第一个字符,其余不变
     */
    public static String toUpperCaseFirstOne(String str)
    {
        if (Character.isUpperCase(str.charAt(0)))
        {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
    
    /**
     * 小写第一个字符,其余不变
     */
    public static String toLowerCaseFirstOne(String str)
    {
        if (Character.isLowerCase(str.charAt(0)))
        {
            return str;
        }
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
    
    /**
     * 全部转大写
     * 
     * @param str
     * @return
     */
    public static String toUpper(String str)
    {
        if (str != null)
        {
            return str.toUpperCase();
        }
        return str;
    }
    
    /**
     * 取类名
     */
    public static String getClassShort(String fullType)
    {
        if (fullType != null)
        {
            int lastIndex = fullType.lastIndexOf(".") + 1;
            if (lastIndex > 1 && fullType.length() > lastIndex)
            {
                return fullType.substring(lastIndex, fullType.length());
            }
            return fullType;
        }
        return fullType;
    }
    
    /**
     * 得到包的全路径
     * @param packageName包名
     * @param classShort短类名
     * @return
     */
    public static String getClassFull(String packageName, String classShort)
    {
        if (null != packageName && 0 < packageName.trim().length())
        {
            return packageName + "." + classShort;
        }
        return classShort;
    }
    
}
