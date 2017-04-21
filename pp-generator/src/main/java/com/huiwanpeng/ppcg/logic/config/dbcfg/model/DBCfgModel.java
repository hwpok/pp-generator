package com.huiwanpeng.ppcg.logic.config.dbcfg.model;

import java.util.List;
import java.util.ArrayList;

/**
 * 数据库配置
 * 
 * @version 1.0
 */
public class DBCfgModel
{
    /** 数据库描述信息 */
    private String discription;
    
    /** 数据库类型 */
    private String dbType = "";
    
    /** 数据库驱动 */
    private String driver = "";
    
    /** 数据库连接样列 */
    private String connectUrlExample="";
    
    /** 查找数据库表的SQL, 主体部份 */
    private String selectTableMainSql="";
    
    /** 查找数据库表的SQl, 相等条件 */
    private String selectTableConditionEqualSql="";
    
    /** 查找数据库表的SQL, Like条件 */
    private String selectTableConditionLikeSql="";
    
    /** 查找数据库列的SQL */
    private String selectColumnSql="";
    
    /** 主键为何值时转化成boolean值true */
    private String primaryKeyToBoolean="";
    
    /** 是否可以为null转换成何值转化成true */
    private String nullableToBoolean="";
    
    /** 分页SQL开始部份 */
    private String startPageSql = "";
    
    /** 分页SQL结束部份 */
    private String endPageSql = "";
    
    /** 数据类型映射 */
    private List<ColumnMappingModel> columnMappingModelLst = new ArrayList<ColumnMappingModel>();
    
    /**
     * 构造方法
     */
    public DBCfgModel()
    {}

    public String getDiscription()
    {
        return discription;
    }

    public void setDiscription(String discription)
    {
        this.discription = discription;
    }

    public String getDbType()
    {
        return dbType;
    }

    public void setDbType(String dbType)
    {
        this.dbType = dbType;
    }

    public String getDriver()
    {
        return driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public String getConnectUrlExample()
    {
        return connectUrlExample;
    }

    public void setConnectUrlExample(String connectUrlExample)
    {
        this.connectUrlExample = connectUrlExample;
    }

    public String getSelectTableMainSql()
    {
        return selectTableMainSql;
    }

    public void setSelectTableMainSql(String selectTableMainSql)
    {
        this.selectTableMainSql = selectTableMainSql;
    }

    public String getSelectTableConditionEqualSql()
    {
        return selectTableConditionEqualSql;
    }

    public void setSelectTableConditionEqualSql(String selectTableConditionEqualSql)
    {
        this.selectTableConditionEqualSql = selectTableConditionEqualSql;
    }

    public String getSelectTableConditionLikeSql()
    {
        return selectTableConditionLikeSql;
    }

    public void setSelectTableConditionLikeSql(String selectTableConditionLikeSql)
    {
        this.selectTableConditionLikeSql = selectTableConditionLikeSql;
    }

    public String getSelectColumnSql()
    {
        return selectColumnSql;
    }

    public void setSelectColumnSql(String selectColumnSql)
    {
        this.selectColumnSql = selectColumnSql;
    }

    public String getPrimaryKeyToBoolean()
    {
        return primaryKeyToBoolean;
    }

    public void setPrimaryKeyToBoolean(String primaryKeyToBoolean)
    {
        this.primaryKeyToBoolean = primaryKeyToBoolean;
    }

    public String getNullableToBoolean()
    {
        return nullableToBoolean;
    }

    public void setNullableToBoolean(String nullableToBoolean)
    {
        this.nullableToBoolean = nullableToBoolean;
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

    public List<ColumnMappingModel> getColumnMappingModelLst()
    {
        return columnMappingModelLst;
    }

    public void setColumnMappingModelLst(List<ColumnMappingModel> columnMappingModelLst)
    {
        this.columnMappingModelLst = columnMappingModelLst;
    }
}
