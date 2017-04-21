package com.huiwanpeng.ppcg.ui.util;

import java.util.List;

import javax.swing.JComboBox;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;

/**
 * combox加强类, 统一处理下拉框的初始化,选中, 取值
 * @version 1.0  
 */
public class ComboxUtil
{
    /**
     * 初始化数据库下拉框
     * @param combox
     * @param items
     * @param isDeleteOldItem
     */
    public static void initDBComboxWithArray(JComboBox<GlbCfgDbModel> combox, List<GlbCfgDbModel> modelLst, boolean isDeleteOldItem){
        // 删除原来的
        if(isDeleteOldItem){
            combox.removeAllItems();
        }
        
        // 添加新的
        for(GlbCfgDbModel model : modelLst){
            combox.addItem(model);
        }
    }
    
    /**
     * 选中数据库下拉框的项目
     * @param combox
     * @param model
     */
    public static void selDBComboxWithId(JComboBox<GlbCfgDbModel> combox, GlbCfgDbModel model){
        // 如果传入参数为空,退出程序
        if(null == model){
            return;
        }
        
        // 如果传入对象的Id与选中框的对象相等, 设置为选中状态
        GlbCfgDbModel tempModel = null;
        for(int i=0;i< combox.getItemCount(); i++){
            tempModel = combox.getItemAt(i);
            if(null != tempModel && tempModel.getId().equals(model.getId())){
                combox.setSelectedIndex(i);
                break;
            }
        }
    }
    
    /**
     * 得到选中项目的对象
     * @param combox
     * @return
     */
    public static GlbCfgDbModel getDBComboxSelectedItem(JComboBox<GlbCfgDbModel> combox){
        return (GlbCfgDbModel)combox.getSelectedItem();
    }
    
    /**
     * 初始化数据库下拉框
     * @param combox
     * @param items
     * @param isDeleteOldItem
     */
    public static void initPOComboxWithArray(JComboBox<GlbCfgPoTmpModel> combox, List<GlbCfgPoTmpModel> modelLst, boolean isDeleteOldItem){
        // 删除原来的
        if(isDeleteOldItem){
            combox.removeAllItems();
        }
        
        // 添加新的
        for(GlbCfgPoTmpModel model : modelLst){
            combox.addItem(model);
        }
    }
    
    /**
     * 选中数据库下拉框的项目
     * @param combox
     * @param model
     */
    public static void selPOComboxWithObj(JComboBox<GlbCfgPoTmpModel> combox, GlbCfgPoTmpModel model){
        // 如果传入参数为空,退出程序
        if(null == model){
            return;
        }
        
        // 如果传入对象的Id与选中框的对象相等, 设置为选中状态
        GlbCfgPoTmpModel tempModel = null;
        for(int i=0;i< combox.getItemCount(); i++){
            tempModel = combox.getItemAt(i);
            if(null != tempModel && tempModel.getId().equals(model.getId())){
                combox.setSelectedIndex(i);
                break;
            }
        }
    }
    
    /**
     * 得到选中项目的对象
     * @param combox
     * @return
     */
    public static GlbCfgPoTmpModel getPOComboxSelectedItem(JComboBox<GlbCfgPoTmpModel> combox){
        return (GlbCfgPoTmpModel)combox.getSelectedItem();
    }
    
    /**
     * 初始化数据库下拉框
     * @param combox
     * @param items
     * @param isDeleteOldItem
     */
    public static void initDAOComboxWithArray(JComboBox<GlbCfgDaoTmpModel> combox, List<GlbCfgDaoTmpModel> modelLst, boolean isDeleteOldItem){
        // 删除原来的
        if(isDeleteOldItem){
            combox.removeAllItems();
        }
        
        // 添加新的
        for(GlbCfgDaoTmpModel model : modelLst){
            combox.addItem(model);
        }
    }
    
    /**
     * 选中数据库下拉框的项目
     * @param combox
     * @param model
     */
    public static void selDAOComboxWithObj(JComboBox<GlbCfgDaoTmpModel> combox, GlbCfgDaoTmpModel model){
        // 如果传入参数为空,退出程序
        if(null == model){
            return;
        }
        
        // 如果传入对象的Id与选中框的对象相等, 设置为选中状态
        GlbCfgDaoTmpModel tempModel = null;
        for(int i=0;i< combox.getItemCount(); i++){
            tempModel = combox.getItemAt(i);
            if(null != tempModel && tempModel.getId().equals(model.getId())){
                combox.setSelectedIndex(i);
                break;
            }
        }
    }
    
    /**
     * 得到选中项目的对象
     * @param combox
     * @return
     */
    public static GlbCfgDaoTmpModel getDAOComboxSelectedItem(JComboBox<GlbCfgDaoTmpModel> combox){
        return (GlbCfgDaoTmpModel)combox.getSelectedItem();
    }
    
    
    /**
     * 初始化下拉框并设置选中值, 
     * resStr为结果字符串格式为:选中值:下拉列表项目1|下拉列表项目2
     * @param cdnCbx
     * @param resStr
     */
    public static void initCdnCbx(JComboBox<String> cdnCbx, String resStr){
        cdnCbx.insertItemAt("", 0); // 首选中下拉列表框插入一个空的字符串, 作为用户不选条件时的输入
        String[] strs = resStr.split("[:]");
        // 如果传来的字符串的格式不存确, 那么直接退出处理
        if(2 != strs.length){
            return;
        }
        
        // 解析出下拉列表框的所有项目
        String[] items = strs[1].split("[|]");
        if(1> items.length){
            return;
        }
        
        // 把解析出的项添加到下拉列表框中
        for(String item : items){
            if(null != item && 0 != item.trim().length()){
                cdnCbx.addItem(item.trim());
            }
        }
        // 从传来字符串中得到选中的项目
        String selectedItem = strs[0];
        cdnCbx.setSelectedItem(selectedItem); // 设置下拉框的选中项
    }
    
    /**
     * 得到下拉框的值,并拼装成字符串 
     * 结果字符串为: 选中值:下拉列表项目1|下拉列表项目2
     * @param myCbx
     * @return
     */
    public static String getCdnCbxVal(JComboBox<String> cdnCbx){
        String itemsStr = "";
        String itemStr = "";
        for(int i=1; i<cdnCbx.getItemCount(); i++){
            
            if(i > 20){
                break;  // 最多只允许20个选项
            }
            
            itemStr = cdnCbx.getItemAt(i);
            if(null != itemStr && 0 != itemStr.trim().length()){
                itemsStr = itemsStr + "|" + itemStr.trim();
            }
        }
        String selectedItemValue = (String)cdnCbx.getSelectedItem();
        return selectedItemValue + ":" + itemsStr.substring(1);
    }
    
    /**
     * 添加和排序条件Combox
     * @param cdnCbx
     */
    public static void appendAndOrderCdnCbx(JComboBox<String> cdnCbx)
    {
        // 得到输入字符
        String inputStr = (String) cdnCbx.getSelectedItem();
        if (null == inputStr || 0 == inputStr.trim().length())
        {
            return;
        }
        
        // 判断输入的字符在下拉列表中是否已经存在, 并得到在combox中对应的序号
        int count = cdnCbx.getItemCount();
        int index = -1;
        for (int i = 0; i < count; i++)
        {
            if (inputStr.equals(cdnCbx.getItemAt(i)))
            {
                index = i;
                break;
            }
        }
        
        // 如果在下拉中没有对应的项目
        if (-1 == index)
        {
            cdnCbx.insertItemAt(inputStr, 1); // 那么添加为下拉中的第一个选项
        }
        // 如果在下拉中有对应的项目
        else
        {
            cdnCbx.removeItemAt(index); // 那么删除对应的选项, 
            cdnCbx.insertItemAt(inputStr, 1); // 再把这个选项对应的添加到第一个
            cdnCbx.setSelectedIndex(1); //并设置为选中状态
        }
    }
}
