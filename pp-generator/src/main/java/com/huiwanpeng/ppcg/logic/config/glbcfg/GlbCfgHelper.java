package com.huiwanpeng.ppcg.logic.config.glbcfg;

import java.util.ArrayList;
import java.util.List;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;

/**
 * 配置文件帮助类
 * @version 1.0  
 */
public class GlbCfgHelper
{
    /**
     * 得到UI参数文件的路径
     * @param glbCfgModel参数对象
     * @return
     */
    public static String getUiParamFilePath(GlbCfgModel glbCfgModel){
        return glbCfgModel.getGlbCfgUiModel().getUiParamFilePath();
    }
    
    /**
     * 得到可用的数据库配置对象
     * @param glbCfgModel
     * @return
     */
    public static List<GlbCfgDbModel> getEnableGlbCfgDbModelLst(GlbCfgModel glbCfgModel){
        List<GlbCfgDbModel> glbCfgDbModelLst = new ArrayList<GlbCfgDbModel>();
        for(GlbCfgDbModel model : glbCfgModel.getGlbCfgDbModelLst()){
            if(model.isEnable()){
                glbCfgDbModelLst.add(model);
            }
        }
        return glbCfgDbModelLst;
    }
    
    /**
     * 得到可用的PO模板对象
     * @param glbCfgModel
     * @return
     */
    public static List<GlbCfgPoTmpModel> getEnableGlbCfgPoTmpModelLst(GlbCfgModel glbCfgModel){
        List<GlbCfgPoTmpModel> glbCfgPoTmpModelLst = new ArrayList<GlbCfgPoTmpModel>();
        for(GlbCfgPoTmpModel model : glbCfgModel.getGlbCfgPoTmpModelLst()){
            if(model.isEnable()){
                glbCfgPoTmpModelLst.add(model);
            }
        }
        return glbCfgPoTmpModelLst;
    }
    
    /**
     * 得到可用的DaoMapping模板对象
     * @param glbCfgModel
     * @return
     */
    public static List<GlbCfgDaoTmpModel> getEnableGlbCfgDaoTmpModelLst(GlbCfgModel glbCfgModel){
        List<GlbCfgDaoTmpModel> glbCfgDaoTmpModelLst = new ArrayList<GlbCfgDaoTmpModel>();
        for(GlbCfgDaoTmpModel model : glbCfgModel.getGlbCfgDaoTmpModelLst()){
                glbCfgDaoTmpModelLst.add(model);
        }
        return glbCfgDaoTmpModelLst;
    }
}
