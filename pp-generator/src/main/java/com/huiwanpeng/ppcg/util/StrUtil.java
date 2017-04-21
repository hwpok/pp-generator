package com.huiwanpeng.ppcg.util;

public class StrUtil
{
    public static boolean isEmpty(String str){
        return (null == str || 0 == str.trim().length());
    }
    
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    
    public static String trim2null(String s){
        if(s == null || 0 == s.trim().length()){
            return null;
        }
        return s.trim();
    }
    
    public static String trim2empty(String s){
        if(s != null){
            return s.trim();
        }
        return "";
    }
    
    public static int str2int(String s){
        return str2integer(s, 0);
    }
    
    public static int str2int(String s, int def){
        return str2integer(s, def);
    }
    
    public static Integer str2integer(String s){
        return str2integer(s, null);
    }
    
    public static Integer str2integer(String s, Integer def){
        try{
            return Integer.parseInt(s);
        }
        catch(Exception ex){
        }
        return def;
    }
}
