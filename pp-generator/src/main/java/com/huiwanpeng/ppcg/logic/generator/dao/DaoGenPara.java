package com.huiwanpeng.ppcg.logic.generator.dao;

import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;

/**
 * 生成dao参数
 * @version 1.0  
 */
public class DaoGenPara
{
    private String resFileEncoding = "UTF-8"; // 生成文件的编码
    private String daoInterfacePackage; // dao接口包名
    private String daoClassPackage; // dao包名
    private String pojoType; // pojo类型, 包含包名
    private String resFilePath; // 生成文件的路径
    private String daoInterfaceSuffix; // 生成接口文件的后缀名
    private String daoClassSuffix; // 生成接口文件的后缀名
    private String daoInterfaceFltFilePath; // dao接口模板文件
    private String daoClassFltFilePath; // dao实现类模板文件
    private String variableName; // flt文件变量名
    private TableModel tableModel; // table模型
    private boolean useCame1Naming; //是否使用驼峰命名法
    
    public String getResFileEncoding()
    {
        return resFileEncoding;
    }
    public void setResFileEncoding(String resFileEncoding)
    {
        this.resFileEncoding = resFileEncoding;
    }
    public String getDaoInterfacePackage()
    {
        return daoInterfacePackage;
    }
    public void setDaoInterfacePackage(String daoInterfacePackage)
    {
        this.daoInterfacePackage = daoInterfacePackage;
    }
    public String getDaoClassPackage()
    {
        return daoClassPackage;
    }
    public void setDaoClassPackage(String daoClassPackage)
    {
        this.daoClassPackage = daoClassPackage;
    }
    public String getPojoType()
    {
        return pojoType;
    }
    public void setPojoType(String pojoType)
    {
        this.pojoType = pojoType;
    }
    public String getResFilePath()
    {
        return resFilePath;
    }
    public void setResFilePath(String resFilePath)
    {
        this.resFilePath = resFilePath;
    }
    public String getDaoInterfaceSuffix()
    {
        return daoInterfaceSuffix;
    }
    public void setDaoInterfaceSuffix(String daoInterfaceSuffix)
    {
        this.daoInterfaceSuffix = daoInterfaceSuffix;
    }
    public String getDaoClassSuffix()
    {
        return daoClassSuffix;
    }
    public void setDaoClassSuffix(String daoClassSuffix)
    {
        this.daoClassSuffix = daoClassSuffix;
    }
    public String getDaoInterfaceFltFilePath()
    {
        return daoInterfaceFltFilePath;
    }
    public void setDaoInterfaceFltFilePath(String daoInterfaceFltFilePath)
    {
        this.daoInterfaceFltFilePath = daoInterfaceFltFilePath;
    }
    public String getDaoClassFltFilePath()
    {
        return daoClassFltFilePath;
    }
    public void setDaoClassFltFilePath(String daoClassFltFilePath)
    {
        this.daoClassFltFilePath = daoClassFltFilePath;
    }
    public String getVariableName()
    {
        return variableName;
    }
    public void setVariableName(String variableName)
    {
        this.variableName = variableName;
    }
    public TableModel getTableModel()
    {
        return tableModel;
    }
    public void setTableModel(TableModel tableModel)
    {
        this.tableModel = tableModel;
    }
    public boolean isUseCame1Naming()
    {
        return useCame1Naming;
    }
    public void setUseCame1Naming(boolean useCame1Naming)
    {
        this.useCame1Naming = useCame1Naming;
    }
}
