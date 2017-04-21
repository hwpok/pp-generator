package com.huiwanpeng.ppcg.logic.tblinfo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 表模型
 * 
 * @version 1.0
 */
public class TableModel
{
    /** 数据库类型 */
    private String dbType;
    
    /** 数据库类型 */
    private String dbName;
    
    /** 表名称 */
    private String tableName;
    
    /** 表注释 */
    private String tableComment;
    
    /** 是否复合主键 */
    private boolean multiPrimaryKey;
    
    private String startPageSql; // 分页开始SQL
    private String endPageSql; // 分页结束SQL
    
    /** 列模型集合 */
    private List<ColumnModel> columnModelLst = new ArrayList<ColumnModel>();

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

    public boolean isMultiPrimaryKey()
    {
        return multiPrimaryKey;
    }

    public void setMultiPrimaryKey(boolean multiPrimaryKey)
    {
        this.multiPrimaryKey = multiPrimaryKey;
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

    public List<ColumnModel> getColumnModelLst()
    {
        return columnModelLst;
    }

    public void setColumnModelLst(List<ColumnModel> columnModelLst)
    {
        this.columnModelLst = columnModelLst;
    }
}
