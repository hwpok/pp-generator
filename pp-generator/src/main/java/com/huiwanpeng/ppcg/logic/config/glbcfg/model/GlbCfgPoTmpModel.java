package com.huiwanpeng.ppcg.logic.config.glbcfg.model;

/**
 * po模板配置模型
 * @version 1.0  
 */
public class GlbCfgPoTmpModel
{
    private String id; // id号
    private String name; // 名称
    private String pkTemplateFilePath; // po主键配置文件
    private String poTemplateFilePath; // 配置文件
    private boolean enable; // 是否可用
    private String remark; // 备注信息;
    
    public String toString(){
        return name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPkTemplateFilePath()
    {
        return pkTemplateFilePath;
    }

    public void setPkTemplateFilePath(String pkTemplateFilePath)
    {
        this.pkTemplateFilePath = pkTemplateFilePath;
    }

    public String getPoTemplateFilePath()
    {
        return poTemplateFilePath;
    }

    public void setPoTemplateFilePath(String poTemplateFilePath)
    {
        this.poTemplateFilePath = poTemplateFilePath;
    }

    public boolean isEnable()
    {
        return enable;
    }

    public void setEnable(boolean enable)
    {
        this.enable = enable;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
