package com.huiwanpeng.ppcg.logic.generator.dao;

import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;

/**
 * 生成Mapping文件的参数
 */
public class MappingGenPara
{
    private int xbatisType = 0; // 0:ibatis, 1:myBatis
    private String resFileEncoding = "UTF-8"; // 生成文件的编码
    private String nameSpace; // 命名空间
    private String pojoType; // pojo类型
    private String resFilePath; // 生成文件的路径
    private String resultFileNameSuffix; // 生成文件的后缀名
    private String fltFilePath; // 生成文件名
    private String variableName; // flt文件变量名
    private TableModel tableModel; // table模型
    private boolean useCame1Naming; //是否使用驼峰命名法;
    
    public int getXbatisType()
    {
        return xbatisType;
    }
    public void setXbatisType(int xbatisType)
    {
        this.xbatisType = xbatisType;
    }
    public String getResFileEncoding()
    {
        return resFileEncoding;
    }
    public void setResFileEncoding(String resFileEncoding)
    {
        this.resFileEncoding = resFileEncoding;
    }
    public String getNameSpace()
    {
        return nameSpace;
    }
    public void setNameSpace(String nameSpace)
    {
        this.nameSpace = nameSpace;
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
    public String getResultFileNameSuffix()
    {
        return resultFileNameSuffix;
    }
    public void setResultFileNameSuffix(String resultFileNameSuffix)
    {
        this.resultFileNameSuffix = resultFileNameSuffix;
    }
    public String getFltFilePath()
    {
        return fltFilePath;
    }
    public void setFltFilePath(String fltFilePath)
    {
        this.fltFilePath = fltFilePath;
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
