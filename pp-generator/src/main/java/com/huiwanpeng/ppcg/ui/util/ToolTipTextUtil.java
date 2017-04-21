package com.huiwanpeng.ppcg.ui.util;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;
import com.huiwanpeng.ppcg.util.StrUtil;

public class ToolTipTextUtil
{
    /**
     * 用于得到数据库配置的tool tip text信息
     * @return
     */
    public static String getCfgDbToolTipText(GlbCfgDbModel model){
        StringBuilder txt = new StringBuilder();
        if(null != model){
            txt.append("<html> <table cellspacing='0' cellpadding='0'>");
            // ID
            if(StrUtil.isNotEmpty(model.getId())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("id:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getId()).append("</td>");
                txt.append("</tr>");
            }
            
            // Name
            if(StrUtil.isNotEmpty(model.getName())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("name:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getName()).append("</td>");
                txt.append("</tr>");
            }
            
            // configFilePath
            if(StrUtil.isNotEmpty(model.getConfigFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("configFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getConfigFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
            // remark
            if(StrUtil.isNotEmpty(model.getRemark())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("remark:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getRemark()).append("</td>");
                txt.append("</tr>");
            }
            
            txt.append("</table></html>");
        }
        return txt.toString();
    }

    
    /**
     * 用于提示信息
     * @return
     */
    public static String getCfgPoToolTipText(GlbCfgPoTmpModel model){
        StringBuilder txt = new StringBuilder();
        if(null != model){
            txt.append("<html> <table cellspacing='0' cellpadding='0'>");
            
            // ID
            if(StrUtil.isNotEmpty(model.getId())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("id:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getId()).append("</td>");
                txt.append("</tr>");
            }
            
            // Name
            if(StrUtil.isNotEmpty(model.getName())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("name:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getName()).append("</td>");
                txt.append("</tr>");
            }
            
            // configFilePath
            if(StrUtil.isNotEmpty(model.getPkTemplateFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("pkTemplateFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getPkTemplateFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
            // configFilePath
            if(StrUtil.isNotEmpty(model.getPoTemplateFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("poTemplateFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getPoTemplateFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
            // remark
            if(StrUtil.isNotEmpty(model.getRemark())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("remark:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getRemark()).append("</td>");
                txt.append("</tr>");
            }
            txt.append("</table></html>");
        }
        return txt.toString();
    }
    
    /**
     * 用于提示信息
     * @return
     */
    public static String getCfgDaoToolTipText1(GlbCfgDaoTmpModel model){
        StringBuilder txt = new StringBuilder();
        if(null != model){
            txt.append("<html> <table cellspacing='0' cellpadding='0'>");
            
            // ID
            if(StrUtil.isNotEmpty(model.getId())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("id:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getId()).append("</td>");
                txt.append("</tr>");
            }
            
            // Name
            if(StrUtil.isNotEmpty(model.getName())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("name:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getName()).append("</td>");
                txt.append("</tr>");
            }
            
            // configFilePath
            if(StrUtil.isNotEmpty(model.getDaoInterfaceTemplateFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("daoInterfaceTemplateFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getDaoInterfaceTemplateFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
            // configFilePath
            if(StrUtil.isNotEmpty(model.getDaoClassTemplateFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("daoClassTemplateFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getDaoClassTemplateFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
         // configFilePath
            if(StrUtil.isNotEmpty(model.getDaoMappingTemplateFilePath())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("daoMappingTemplateFilePath:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getDaoMappingTemplateFilePath()).append("</td>");
                txt.append("</tr>");
            }
            
            // remark
            if(StrUtil.isNotEmpty(model.getRemark())){
                txt.append("<tr>");
                txt.append("<td style='text-align:right;color:green;'>").append("remark:&nbsp;&nbsp;").append("</td>");
                txt.append("<td style='text-align:left;color:red;'>").append(model.getRemark()).append("</td>");
                txt.append("</tr>");
            }
            txt.append("</table></html>");
        }
        return txt.toString();
    }
}
