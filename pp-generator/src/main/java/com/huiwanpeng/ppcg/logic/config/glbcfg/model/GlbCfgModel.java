package com.huiwanpeng.ppcg.logic.config.glbcfg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局对象配置模型
 * @version 1.0  
 */
public class GlbCfgModel
{
    private GlbCfgUiModel glbCfgUiModel; // UI配置
    private List<GlbCfgDbModel> glbCfgDbModelLst = new ArrayList<GlbCfgDbModel>(); //数据库配置模型
    private List<GlbCfgPoTmpModel> glbCfgPoTmpModelLst = new ArrayList<GlbCfgPoTmpModel>(); //PO模板配置模型
    private List<GlbCfgDaoTmpModel> glbCfgDaoTmpModelLst = new ArrayList<GlbCfgDaoTmpModel>(); //PO模板配置模型
    
    public GlbCfgUiModel getGlbCfgUiModel()
    {
        return glbCfgUiModel;
    }

    public void setGlbCfgUiModel(GlbCfgUiModel glbCfgUiModel)
    {
        this.glbCfgUiModel = glbCfgUiModel;
    }

    public List<GlbCfgDbModel> getGlbCfgDbModelLst()
    {
        return glbCfgDbModelLst;
    }

    public void setGlbCfgDbModelLst(List<GlbCfgDbModel> glbCfgDbModelLst)
    {
        this.glbCfgDbModelLst = glbCfgDbModelLst;
    }

    public List<GlbCfgPoTmpModel> getGlbCfgPoTmpModelLst()
    {
        return glbCfgPoTmpModelLst;
    }

    public void setGlbCfgPoTmpModelLst(List<GlbCfgPoTmpModel> glbCfgPoTmpModelLst)
    {
        this.glbCfgPoTmpModelLst = glbCfgPoTmpModelLst;
    }

    public List<GlbCfgDaoTmpModel> getGlbCfgDaoTmpModelLst()
    {
        return glbCfgDaoTmpModelLst;
    }

    public void setGlbCfgDaoTmpModelLst(List<GlbCfgDaoTmpModel> glbCfgDaoTmpModelLst)
    {
        this.glbCfgDaoTmpModelLst = glbCfgDaoTmpModelLst;
    }
}
