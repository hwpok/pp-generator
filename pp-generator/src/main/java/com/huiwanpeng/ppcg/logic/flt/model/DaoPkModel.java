package com.huiwanpeng.ppcg.logic.flt.model;

/**
 * DAO PK模型
 * 
 * @version 1.0
 */
public class DaoPkModel
{
    private String property; // 属性名
    private String javaType; //java类型
    private String javaTypeShort; //java类型不包括包名
    private String columnComment; //注释说明
    
    public String getProperty()
    {
        return property;
    }
    public void setProperty(String property)
    {
        this.property = property;
    }
    public String getJavaType()
    {
        return javaType;
    }
    public void setJavaType(String javaType)
    {
        this.javaType = javaType;
    }
    public String getJavaTypeShort()
    {
        return javaTypeShort;
    }
    public void setJavaTypeShort(String javaTypeShort)
    {
        this.javaTypeShort = javaTypeShort;
    }
    public String getColumnComment()
    {
        return columnComment;
    }
    public void setColumnComment(String columnComment)
    {
        this.columnComment = columnComment;
    }
}
