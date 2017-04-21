package com.huiwanpeng.ppcg.logic.tblinfo.model;

/**
 * 列模型
 * 
 * @version 1.0
 */
public class ColumnModel
{
    /** 列名 */
    private String columnName;
    /** 列注释 */
    private String columnComment;
    /** 数据类型, 数据库原始数据类型 */
    private String dataType;
    /** jdbc类型(每种数据库不一样, 要经过翻译) */
    private String jdbcType;
    /** jdbc类型(每种数据库不一样, 要经过翻译) */
    private String javaType;
    /** 类型处理器 */
    private String typeHandler;
    /** 数据长度 */
    private int dataLength;
    /** 小数精度 */
    private int numericPrecision;
    /** 小数标度 */
    private int numericScale;
    /** 主键字符串MYSQL为PRI，ORACLE为P */
    private String primaryKeyDBStr;
    /** 是否为主键,各数据库转换成统一的 */
    private boolean primaryKey;
    /** 是否可以为NULL,MYSQL,ORACLE都为为YN */
    private String nullableDBStr;
    /** 是否可以为NULL,各数据统一转换 */
    private boolean nullable;
    
    public String getColumnName()
    {
        return columnName;
    }



    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }


    public String getColumnComment()
    {
        return columnComment;
    }



    public void setColumnComment(String columnComment)
    {
        this.columnComment = columnComment;
    }

    public int getDataLength()
    {
        return dataLength;
    }

    public void setDataLength(int dataLength)
    {
        this.dataLength = dataLength;
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

    


    public String getTypeHandler()
    {
        return typeHandler;
    }



    public void setTypeHandler(String typeHandler)
    {
        this.typeHandler = typeHandler;
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



    public String getPrimaryKeyDBStr()
    {
        return primaryKeyDBStr;
    }



    public void setPrimaryKeyDBStr(String primaryKeyDBStr)
    {
        this.primaryKeyDBStr = primaryKeyDBStr;
    }



    public boolean isPrimaryKey()
    {
        return primaryKey;
    }



    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }



    public String getNullableDBStr()
    {
        return nullableDBStr;
    }



    public void setNullableDBStr(String nullableDBStr)
    {
        this.nullableDBStr = nullableDBStr;
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
