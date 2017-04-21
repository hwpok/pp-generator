package com.huiwanpeng.ppcg.logic.generator.po;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;

/**
 * 生成pojo参数
 * @version 1.0  
 */
public class PoGenPara
{
    private String resFileEncoding = "UTF-8"; // 生成文件的编码
    private String packageName; //生成pojo的包名
    private String resFilePath; // 生成文件的路径
    private String pkClassSuffix; // pk类文件路径
    private String poClassSuffix; // po类文件后缀名
    private GlbCfgPoTmpModel poTmpModel; // 模板对象
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
    public String getPackageName()
    {
        return packageName;
    }
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    public String getResFilePath()
    {
        return resFilePath;
    }
    public void setResFilePath(String resFilePath)
    {
        this.resFilePath = resFilePath;
    }
    public String getPkClassSuffix()
    {
        return pkClassSuffix;
    }
    public void setPkClassSuffix(String pkClassSuffix)
    {
        this.pkClassSuffix = pkClassSuffix;
    }
    public String getPoClassSuffix()
    {
        return poClassSuffix;
    }
    public void setPoClassSuffix(String poClassSuffix)
    {
        this.poClassSuffix = poClassSuffix;
    }
    public GlbCfgPoTmpModel getPoTmpModel()
    {
        return poTmpModel;
    }
    public void setPoTmpModel(GlbCfgPoTmpModel poTmpModel)
    {
        this.poTmpModel = poTmpModel;
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
