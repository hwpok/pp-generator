package com.huiwanpeng.ppcg.ui;

import javax.swing.JOptionPane;

import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.util.StrUtil;

public class UIParaBeanChecker
{
    /**
     * 检查所有控件, 在生成时调用
     * 
     * @param uiParaBean
     * @param checkDataSourcePanel
     * @param checkGenCodePanel
     * @return
     */
    public static boolean checkUIParaBean(UIParaBean uiParaBean, boolean checkDataSourcePanel, boolean checkGenCodePanel)
    {
        // 检查数据源面板
        if (checkDataSourcePanel)
        {
            // 如果是数据库数据类型选中
            if (uiParaBean.getDataSourceType() == 1)
            {
                // 检查数据库面板的控件是否合法, 如果不合法, 那么退出
                if (!checkDataSourcePanel(uiParaBean))
                {
                    return false;
                }
            }
            
            // 如果Excel数据库选中
            else
            {
                // 检查excel数据源面板, 如果不合法,那么退出
                if (!checkExcelDataSourcePanel(uiParaBean))
                {
                    return false;
                }
            }
        }
        
        // 检查代码生成面板
        if (checkGenCodePanel)
        {
            // 检查代码生成面板, 如果不合法,那么退出
            if (!checkGenCodePanel(uiParaBean))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 检查数据库的数据源面板里的所有控件
     * 
     * @param uiParaBean
     * @return
     */
    public static boolean checkDataSourcePanel(UIParaBean uiParaBean)
    {
        // 数据库类型 必输
        if (uiParaBean.getDbModel() == null)
        {
            JOptionPane.showMessageDialog(null, "dbId can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // 数据库驱动 必输
        if (StrUtil.isEmpty(uiParaBean.getDbDriver()))
        {
            JOptionPane.showMessageDialog(null, "Driver can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // 数据库地址 必输
        if (StrUtil.isEmpty(uiParaBean.getDbUrl()))
        {
            JOptionPane.showMessageDialog(null, "URL can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // 数据库用户名 必输
        if (StrUtil.isEmpty(uiParaBean.getDbUserName()))
        {
            JOptionPane.showMessageDialog(null, "User Name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // 数据库密码 必输
        if (StrUtil.isEmpty(uiParaBean.getDbPassword()))
        {
            JOptionPane.showMessageDialog(null, "Password can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * 检查Excel数据源
     * 
     * @param uiParaBean
     * @return
     */
    public static boolean checkExcelDataSourcePanel(UIParaBean uiParaBean)
    {
        // 数据库类型 必输
        if (uiParaBean.getExDbModel() == null)
        {
            JOptionPane.showMessageDialog(null, "Excel DB Type can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // excel保存地址必输
        if (StrUtil.isEmpty(uiParaBean.getExFilePath()))
        {
            JOptionPane.showMessageDialog(null, "Excel File Path  can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * 检查代码生成面板的参数
     * </ul>
     * 
     * @param uiParaBean
     * @return
     */
    public static boolean checkGenCodePanel(UIParaBean uiParaBean)
    {
        // 如果生成pojo选中
        if (uiParaBean.isGenPojoCode())
        {
            // pojo包名 必输
            if (StrUtil.isEmpty(uiParaBean.getPojoPackageName()))
            {
                JOptionPane.showMessageDialog(null, "Pojo Package can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // pojo保存路径 必输
            if (StrUtil.isEmpty(uiParaBean.getSavePojoFilePath()))
            {
                JOptionPane.showMessageDialog(null, "Pojo Save Path can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        // 如果生成dao选中
        if (uiParaBean.isGenDaoCode())
        {
            // 如果DAO接口的模板文件不为空, 那么接口包路径不为能空
            if (StrUtil.isNotEmpty(uiParaBean.getGlbCfgDaoTmpModel().getDaoInterfaceTemplateFilePath()))
            {
                if (StrUtil.isEmpty(uiParaBean.getDaoInterfacePackageName()))
                {
                    JOptionPane.showMessageDialog(null, "Dao interface package name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
            if (StrUtil.isNotEmpty(uiParaBean.getGlbCfgDaoTmpModel().getDaoClassTemplateFilePath()))
            {
                // dao包名 必输
                if (StrUtil.isEmpty(uiParaBean.getDaoClassPackageName()))
                {
                    JOptionPane.showMessageDialog(null, "Dao implement package name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            
            // dao保存路径 必输
            if (StrUtil.isEmpty(uiParaBean.getSaveDaoFilePath()))
            {
                JOptionPane.showMessageDialog(null, "Dao save path can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
