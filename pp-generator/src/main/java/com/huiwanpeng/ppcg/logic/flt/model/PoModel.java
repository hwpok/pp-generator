package com.huiwanpeng.ppcg.logic.flt.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * PO模型
 * @version 1.0
 */
public class PoModel
{
    private String packageName; //po包名
    private HashSet<String> importClassSet; //需要import的class
    private HashSet<String> importPkClassSet; //Pk需要import的class
    private HashSet<String> importCmClassSet; //除开Pk需要import的class
    private String pkClass; // po的PK类
    private String pkClassShort; //po的PK类不包含包路径
    private String poClass; //类名包含包路径
    private String poClassShort; //类名不包含包路径
    private List<PoPropertyModel> poPropertyModelLst; // 所有属性集合
    private List<PoPropertyModel> poPkPropertyModelLst; //po主键属性集合
    private List<PoPropertyModel> poCmPropertyModelLst; //po非主键属性集合
    private String dbType; // 数据库类型
    private String dbName; // 数据库名称
    private String tableName; //表名称
    private String tableComment; // 表注释
    private boolean multiPrimaryKey; //是否复合主键
    private Date createTime = new Date(); //创建时间
    
    public String getPackageName()
    {
        return packageName;
    }
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    public HashSet<String> getImportClassSet()
    {
        return importClassSet;
    }
    public void setImportClassSet(HashSet<String> importClassSet)
    {
        this.importClassSet = importClassSet;
    }
    public HashSet<String> getImportPkClassSet()
    {
        return importPkClassSet;
    }
    public void setImportPkClassSet(HashSet<String> importPkClassSet)
    {
        this.importPkClassSet = importPkClassSet;
    }
    public HashSet<String> getImportCmClassSet()
    {
        return importCmClassSet;
    }
    public void setImportCmClassSet(HashSet<String> importCmClassSet)
    {
        this.importCmClassSet = importCmClassSet;
    }
    public String getPkClass()
    {
        return pkClass;
    }
    public void setPkClass(String pkClass)
    {
        this.pkClass = pkClass;
    }
    public String getPkClassShort()
    {
        return pkClassShort;
    }
    public void setPkClassShort(String pkClassShort)
    {
        this.pkClassShort = pkClassShort;
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
    public List<PoPropertyModel> getPoPropertyModelLst()
    {
        return poPropertyModelLst;
    }
    public void setPoPropertyModelLst(List<PoPropertyModel> poPropertyModelLst)
    {
        this.poPropertyModelLst = poPropertyModelLst;
    }
    public List<PoPropertyModel> getPoPkPropertyModelLst()
    {
        return poPkPropertyModelLst;
    }
    public void setPoPkPropertyModelLst(List<PoPropertyModel> poPkPropertyModelLst)
    {
        this.poPkPropertyModelLst = poPkPropertyModelLst;
    }
    public List<PoPropertyModel> getPoCmPropertyModelLst()
    {
        return poCmPropertyModelLst;
    }
    public void setPoCmPropertyModelLst(List<PoPropertyModel> poCmPropertyModelLst)
    {
        this.poCmPropertyModelLst = poCmPropertyModelLst;
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
    public boolean isMultiPrimaryKey()
    {
        return multiPrimaryKey;
    }
    public void setMultiPrimaryKey(boolean multiPrimaryKey)
    {
        this.multiPrimaryKey = multiPrimaryKey;
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
