package com.huiwanpeng.ppcg.logic.flt.model;

/**
 * mapping字段数据模型
 * 
 * @version 1.0
 */
public class MappingColumnModel
{
    private String column; // 数据库表的列名
    private String columnComment; // 注释说明
    private String jdbcType; // jdbc数据类型
    private String property; // po的属性名
    private String javaType; // java数据类型
    private String typeHandler; // 类型处理器
    private boolean primaryKey; // 是否主键
    private boolean nullable; // 是否可以为空
    
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
    public String getJdbcType()
    {
        return jdbcType;
    }
    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }
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
    public String getTypeHandler()
    {
        return typeHandler;
    }
    public void setTypeHandler(String typeHandler)
    {
        this.typeHandler = typeHandler;
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
