package com.huiwanpeng.ppcg.logic.flt.model;

import java.util.Date;
import java.util.List;

/**
 * Mapping 配置文件模型
 * 
 * @version 1.0
 */
public class MappingModel
{
    private String namespace; // 命名空间名称
    private String poClass; //po类型
    private String poClassShort; //po类型不包含包路径
    private String dbType; // 数据库类型
    private String dbName; // 数据库名称
    private String tableName; // 表名
    private String tableComment; //表注释
    private String startPageSql; // 分页开始SQL
    private String endPageSql; // 分页结束SQL
    private List<MappingColumnModel> mappingColumnModelLst; // 数据操作模型对象集合
    private Date createTime = new Date(); //创建时间
    
    public String getNamespace()
    {
        return namespace;
    }
    public void setNamespace(String namespace)
    {
        this.namespace = namespace;
    }
    public String getPoClass()
    {
        return poClass;
    }
    public void setPoClass(String poClass)
    {
        this.poClass = poClass;
    }
    public String getPoClassShort()
    {
        return poClassShort;
    }
    public void setPoClassShort(String poClassShort)
    {
        this.poClassShort = poClassShort;
    }
    public String getDbType()
    {
        return dbType;
    }
    public void setDbType(String dbType)
    {
        this.dbType = dbType;
    }
    public String getDbName()
    {
        return dbName;
    }
    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    public String getTableName()
    {
        return tableName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
    public String getTableComment()
    {
        return tableComment;
    }
    public void setTableComment(String tableComment)
    {
        this.tableComment = tableComment;
    }
    public String getStartPageSql()
    {
        return startPageSql;
    }
    public void setStartPageSql(String startPageSql)
    {
        this.startPageSql = startPageSql;
    }
    public String getEndPageSql()
    {
        return endPageSql;
    }
    public void setEndPageSql(String endPageSql)
    {
        this.endPageSql = endPageSql;
    }
    public List<MappingColumnModel> getMappingColumnModelLst()
    {
        return mappingColumnModelLst;
    }
    public void setMappingColumnModelLst(List<MappingColumnModel> mappingColumnModelLst)
    {
        this.mappingColumnModelLst = mappingColumnModelLst;
    }
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
}
