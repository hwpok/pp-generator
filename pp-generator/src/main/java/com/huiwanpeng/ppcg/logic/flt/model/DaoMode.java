package com.huiwanpeng.ppcg.logic.flt.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/** 
 *生成DAO的模型
 */
public class DaoMode
{
    private String daoInterfacePackageName; // dao接口包名
    private String daoClassPackageName; // dao实现类包名
    private HashSet<String> importClassSet;//本类需要引入的类型
    private String daoInterface; // dao接口
    private String daoInterfaceShort; // dao接口不包含包路径
    private String daoClass;//dao类名
    private String daoClassShort;//dao类型不包含包路径
    private String poClass; //po类型 
    private String poClassShort; //po类型不包含包路径
    private String dbType; // 数据库类型
    private String dbName; // 数据库名称
    private String tableName; //表名
    private String tableComment; //表注释
    private List<DaoPkModel> daoPkModelLst; //主键列表
    private Date createTime = new Date(); //创建时间
    
    public String getDaoInterfacePackageName()
    {
        return daoInterfacePackageName;
    }
    public void setDaoInterfacePackageName(String daoInterfacePackageName)
    {
        this.daoInterfacePackageName = daoInterfacePackageName;
    }
    public String getDaoClassPackageName()
    {
        return daoClassPackageName;
    }
    public void setDaoClassPackageName(String daoClassPackageName)
    {
        this.daoClassPackageName = daoClassPackageName;
    }
    public HashSet<String> getImportClassSet()
    {
        return importClassSet;
    }
    public void setImportClassSet(HashSet<String> importClassSet)
    {
        this.importClassSet = importClassSet;
    }
    public String getDaoInterface()
    {
        return daoInterface;
    }
    public void setDaoInterface(String daoInterface)
    {
        this.daoInterface = daoInterface;
    }
    public String getDaoInterfaceShort()
    {
        return daoInterfaceShort;
    }
    public void setDaoInterfaceShort(String daoInterfaceShort)
    {
        this.daoInterfaceShort = daoInterfaceShort;
    }
    public String getDaoClass()
    {
        return daoClass;
    }
    public void setDaoClass(String daoClass)
    {
        this.daoClass = daoClass;
    }
    public String getDaoClassShort()
    {
        return daoClassShort;
    }
    public void setDaoClassShort(String daoClassShort)
    {
        this.daoClassShort = daoClassShort;
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
    public List<DaoPkModel> getDaoPkModelLst()
    {
        return daoPkModelLst;
    }
    public void setDaoPkModelLst(List<DaoPkModel> daoPkModelLst)
    {
        this.daoPkModelLst = daoPkModelLst;
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
