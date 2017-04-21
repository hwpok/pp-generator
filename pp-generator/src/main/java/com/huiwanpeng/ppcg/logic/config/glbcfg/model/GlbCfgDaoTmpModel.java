package com.huiwanpeng.ppcg.logic.config.glbcfg.model;


/**
 * 映射文件配置模型
 * @version 1.0  
 */
public class GlbCfgDaoTmpModel
{
    private String id; // ID
    private String type; //ibatis, mybatis, comm
    private String name; //名称
    private String daoInterfaceTemplateFilePath; // dao接口类模板文件路径
    private String daoClassTemplateFilePath; // dao实现类模板文件路径
    private String daoMappingTemplateFilePath; // mapping文件配置路径,权xbatis会用到
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDaoInterfaceTemplateFilePath()
    {
        return daoInterfaceTemplateFilePath;
    }

    public void setDaoInterfaceTemplateFilePath(String daoInterfaceTemplateFilePath)
    {
        this.daoInterfaceTemplateFilePath = daoInterfaceTemplateFilePath;
    }

    public String getDaoClassTemplateFilePath()
    {
        return daoClassTemplateFilePath;
    }

    public void setDaoClassTemplateFilePath(String daoClassTemplateFilePath)
    {
        this.daoClassTemplateFilePath = daoClassTemplateFilePath;
    }

    public String getDaoMappingTemplateFilePath()
    {
        return daoMappingTemplateFilePath;
    }

    public void setDaoMappingTemplateFilePath(String daoMappingTemplateFilePath)
    {
        this.daoMappingTemplateFilePath = daoMappingTemplateFilePath;
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
