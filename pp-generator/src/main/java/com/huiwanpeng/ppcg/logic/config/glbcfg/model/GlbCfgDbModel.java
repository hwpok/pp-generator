package com.huiwanpeng.ppcg.logic.config.glbcfg.model;

/**
 * 数据库配置模型
 * @version 1.0  
 */
public class GlbCfgDbModel
{
    private String id; // id
    private String name; // 显示名称
    private String configFilePath; //配置文件路径
    private boolean enable; // 是否可用
    private String remark; // 备注信息
    
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

    public String getConfigFilePath()
    {
        return configFilePath;
    }

    public void setConfigFilePath(String configFilePath)
    {
        this.configFilePath = configFilePath;
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
