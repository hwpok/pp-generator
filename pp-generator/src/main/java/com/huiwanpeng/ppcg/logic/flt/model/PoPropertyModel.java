package com.huiwanpeng.ppcg.logic.flt.model;

/**
 * 字段模型
 * @version 1.0
 */
public class PoPropertyModel
{
    
    private String column; //列名
    private String columnComment;//列注释
    private String property;//属性名
    private String dataType;//数据库数据类型
    private String jdbcType;//jdbc类型
    private String javaType;//java类型
    private String javaTypeShort;//java类型不带包路径
    private int dataLength;//数据长度
    private int numericPrecision;//小数精度
    private int numericScale;//小数标度
    private boolean primaryKey;//是否为主键
    private boolean nullable;//是否可以为NULL
    
    
    public String getColumn()
    {
        return column;
    }
    public void setColumn(String column)
    {
        this.column = column;
    }
    public String getColumnComment()
    {
        return columnComment;
    }
    public void setColumnComment(String columnComment)
    {
        this.columnComment = columnComment;
    }
    public String getProperty()
    {
        return property;
    }
    public void setProperty(String property)
    {
        this.property = property;
    }
    public String getDataType()
    {
        return dataType;
    }
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }
    public String getJdbcType()
    {
        return jdbcType;
    }
    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
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
    public int getDataLength()
    {
        return dataLength;
    }
    public void setDataLength(int dataLength)
    {
        this.dataLength = dataLength;
    }
    public int getNumericPrecision()
    {
        return numericPrecision;
    }
    public void setNumericPrecision(int numericPrecision)
    {
        this.numericPrecision = numericPrecision;
    }
    public int getNumericScale()
    {
        return numericScale;
    }
    public void setNumericScale(int numericScale)
    {
        this.numericScale = numericScale;
    }
    public boolean isPrimaryKey()
    {
        return primaryKey;
    }
    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }
    public boolean isNullable()
    {
        return nullable;
    }
    public void setNullable(boolean nullable)
    {
        this.nullable = nullable;
    }
}
