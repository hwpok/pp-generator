package com.huiwanpeng.ppcg.ui.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.huiwanpeng.ppcg.logic.config.glbcfg.GlbCfgHelper;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.ui.UIParaParser;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.ui.util.MsgUtil;
import com.huiwanpeng.ppcg.util.CharaterSetUtil;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

import javax.swing.JCheckBox;

public class SettingPanel extends JPanel
{
    private static final long serialVersionUID = 4949912725070866060L;
    private  JComboBox<String> charSetEncodingCb;
    private JCheckBox useCame1NamingCb;
    private JComboBox<String> languageCbx;
    private JComboBox<String> sysSkinCbx;
    
    // pojo 参数
    private JTextField poFltVarNmTf;
    private JTextField poPkClassSuffixTf;
    private JTextField poClassSuffixTf;
    
    // dao参数
    private JTextField daoFltVarNmTf;
    private JTextField mappingFltVarNmTf;
    private JTextField ibatisDaoInterfaceSuffixTf;
    private JTextField ibatisDaoClassSuffixTf;
    private JTextField mybatisDaoInterfaceSuffixTf;
    private JTextField mybatisDaoClassSuffixTf;
    private JTextField commDaoInterfaceSuffixTf;
    private JTextField commDaoClassSuffixTf;
    
    // 保存参数
    private JButton saveParaBtn;
    
    private ILang lang;
    private GlbCfgModel glbCfgModel;
    
    
    
    
    public void init(GlbCfgModel glbCfgModel, UIParaBean para){
        this.glbCfgModel = glbCfgModel;
        initComboxWithArray(languageCbx, lang.getGPLangTypes(), true); // 先初始化语言
        initComboxWithArray(charSetEncodingCb, CharaterSetUtil.getCharaterArray(), true); // 先初始化字符集
        initComboxWithArray(sysSkinCbx, lang.getGPSkinStyleTypes(), true); // 先初系统皮肤
        
        // 三个下拉选择框取消获得焦点, 不然选中后颜色为蓝色
        languageCbx.setFocusable(false);
        sysSkinCbx.setFocusable(false);
        charSetEncodingCb.setFocusable(false);
        
        // 设置语言
        if(para.getLanguage() < languageCbx.getItemCount()){
            languageCbx.setSelectedIndex(para.getLanguage());
        }
        else{
            languageCbx.setSelectedIndex(2);
        }
        // 由于下拉选择太长, 不能完全显示, 所以设置ToolTipText
        if(null != languageCbx.getSelectedItem()){
            languageCbx.setToolTipText((String)languageCbx.getSelectedItem());
        }
        
        // 设置界面风格
        if(para.getSkinStyle() < sysSkinCbx.getItemCount()){
            sysSkinCbx.setSelectedIndex(para.getSkinStyle());
        }
        else{
            sysSkinCbx.setSelectedIndex(0);
        }
        
        // 由于下拉选择太长, 不能完全显示, 所以设置ToolTipText
        if(null != sysSkinCbx.getSelectedItem()){
            sysSkinCbx.setToolTipText((String)sysSkinCbx.getSelectedItem());
        }
        
        // 设置字符集
        if(CharaterSetUtil.containCharater(para.getCharSetEncoding())){
            charSetEncodingCb.setSelectedItem(para.getCharSetEncoding());
        }
        else{
            charSetEncodingCb.setSelectedItem("UTF-8");
        }
        
        // 由于下拉选择太长, 不能完全显示, 所以设置ToolTipText
        if(null != charSetEncodingCb.getSelectedItem()){
            charSetEncodingCb.setToolTipText((String)charSetEncodingCb.getSelectedItem());
        }
        
        // 是否使用驼峰命名法
        useCame1NamingCb.setSelected(para.isUseCame1Naming()); 
        
        // 语言下拉选择框, 数据库类型选中事件
        languageCbx.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent arg0)
            {
                if(null != languageCbx.getSelectedItem()){
                    languageCbx.setToolTipText((String)languageCbx.getSelectedItem());
                }
            }
        });
        
        // 字符集下拉选择框, 数据库类型选中事件
        charSetEncodingCb.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent arg0)
            {
                if(null != charSetEncodingCb.getSelectedItem()){
                    charSetEncodingCb.setToolTipText((String)charSetEncodingCb.getSelectedItem());
                }
            }
        });
        
        // 界面风格下拉选择框, 数据库类型选中事件
        sysSkinCbx.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent arg0)
            {
                if(null != sysSkinCbx.getSelectedItem()){
                    sysSkinCbx.setToolTipText((String)sysSkinCbx.getSelectedItem());
                }
            }
        });
        
        saveParaBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                savePara();
            }
        });
        
        // pojo 参数
        poFltVarNmTf.setText(StrUtil.trim2empty(para.getPoFltVarNm()));
        poPkClassSuffixTf.setText(StrUtil.trim2empty(para.getPoPkClassSuffix()));
        poClassSuffixTf.setText(StrUtil.trim2empty(para.getPoClassSuffix()));
        
        // dao参数
        daoFltVarNmTf.setText(StrUtil.trim2empty(para.getDaoFltVarNm()));
        mappingFltVarNmTf.setText(StrUtil.trim2empty(para.getMappingFltVarNm()));
        ibatisDaoInterfaceSuffixTf.setText(StrUtil.trim2empty(para.getIbatisDaoInterfaceSuffix()));
        ibatisDaoClassSuffixTf.setText(StrUtil.trim2empty(para.getIbatisDaoClassSuffix()));
        mybatisDaoInterfaceSuffixTf.setText(StrUtil.trim2empty(para.getMybatisDaoInterfaceSuffix()));
        mybatisDaoClassSuffixTf.setText(StrUtil.trim2empty(para.getMybatisDaoClassSuffix()));
        commDaoInterfaceSuffixTf.setText(StrUtil.trim2empty(para.getCommDaoInterfaceSuffix()));
        commDaoClassSuffixTf.setText(StrUtil.trim2empty(para.getCommDaoClassSuffix()));
        
    }
    
    /**
     * 得到面版的参数值
     * @param para
     */
    public void getValues(UIParaBean para){
        // ------------ 全局参数-------------------------------------
        para.setLanguage(languageCbx.getSelectedIndex()); // 中文选中0,其它1
        para.setCharSetEncoding((String)charSetEncodingCb.getSelectedItem()); //字符集
        para.setSkinStyle(sysSkinCbx.getSelectedIndex()); // 设置系统皮肤
        para.setUseCame1Naming(useCame1NamingCb.isSelected()); // 是否使用驼峰命名法
        
        // po 参数
        para.setPoFltVarNm(StrUtil.trim2empty(poFltVarNmTf.getText())); // po flt变量名
        para.setPoPkClassSuffix(StrUtil.trim2empty(poPkClassSuffixTf.getText())); // 复合主键的PK类后缀名
        para.setPoClassSuffix(StrUtil.trim2empty(poClassSuffixTf.getText())); //po类后缀
        
        // dao参数
        para.setDaoFltVarNm(StrUtil.trim2empty(daoFltVarNmTf.getText()));  // dao ftl变量
        para.setMappingFltVarNm(StrUtil.trim2empty(mappingFltVarNmTf.getText())); // dao mapping flt 变量
        para.setIbatisDaoInterfaceSuffix(StrUtil.trim2empty(ibatisDaoInterfaceSuffixTf.getText())); // batis接口后缀
        para.setIbatisDaoClassSuffix(StrUtil.trim2empty(ibatisDaoClassSuffixTf.getText())); // ibatis实现类后缀
        para.setMybatisDaoInterfaceSuffix(StrUtil.trim2empty(mybatisDaoInterfaceSuffixTf.getText())); // mybatis接口后缀
        para.setMybatisDaoClassSuffix(StrUtil.trim2empty(mybatisDaoClassSuffixTf.getText())); // mybatis实现类后缀
        para.setCommDaoInterfaceSuffix(StrUtil.trim2empty(commDaoInterfaceSuffixTf.getText())); // 通用接口后缀
        para.setCommDaoClassSuffix(StrUtil.trim2empty(commDaoClassSuffixTf.getText())); // 通用实现类后缀
    }
    
    /**
     * 初始化combox
     * @param combox
     * @param items
     * @param isDeleteOldItem
     */
    private void initComboxWithArray(JComboBox<String> combox, String[] items, boolean isDeleteOldItem){
        // 删除原来的
        if(isDeleteOldItem){
            combox.removeAllItems();
        }
        
        // 添加新的
        for(String item : items){
            combox.addItem(item);
        }
    }
    
    private void savePara(){
        try
        {
            UIParaBean uiParaBean = UIParaParser.getUIParaBean(GlbCfgHelper.getUiParamFilePath(glbCfgModel));
            getValues(uiParaBean);
            UIParaParser.saveUIParaBean(GlbCfgHelper.getUiParamFilePath(glbCfgModel), uiParaBean);
            JOptionPane.showMessageDialog(null, "Save parameters successful", "Information", JOptionPane.INFORMATION_MESSAGE);
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("Save parameters error", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("Save parameters error", null);
        }
    }

    public SettingPanel(UIParaBean uiParaBean)
    {
        this.lang = LangFactory.getLang(uiParaBean.getLanguage());
        setLayout(null);
        
        JPanel gloabCfgPl = new JPanel();
        gloabCfgPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getGPTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        gloabCfgPl.setBounds(14, 10, 440, 78);
        add(gloabCfgPl);
        gloabCfgPl.setLayout(null);
        
        JLabel langLb = new JLabel(lang.getGPLang());
        langLb.setHorizontalAlignment(SwingConstants.RIGHT);
        langLb.setBounds(10, 20, 109, 15);
        gloabCfgPl.add(langLb);
        
        JLabel skinStyleLb = new JLabel(lang.getGPSkinStyle());
        skinStyleLb.setHorizontalAlignment(SwingConstants.RIGHT);
        skinStyleLb.setBounds(0, 49, 119, 15);
        gloabCfgPl.add(skinStyleLb);
        
        JLabel characterLb = new JLabel(lang.getGPEncoding());
        characterLb.setHorizontalAlignment(SwingConstants.RIGHT);
        characterLb.setBounds(279, 20, 54, 15);
        gloabCfgPl.add(characterLb);
        
        charSetEncodingCb = new JComboBox<String>();
        charSetEncodingCb.setBounds(340, 17, 86, 21);
        gloabCfgPl.add(charSetEncodingCb);
        
        useCame1NamingCb = new JCheckBox(lang.getGPUseCame1Naming());
        useCame1NamingCb.setBounds(285, 45, 141, 23);
        useCame1NamingCb.setFocusPainted(false);
        gloabCfgPl.add(useCame1NamingCb);
        
        languageCbx = new JComboBox<String>();
        languageCbx.setBounds(125, 17, 86, 21);
        gloabCfgPl.add(languageCbx);
        
        sysSkinCbx = new JComboBox<String>();
        sysSkinCbx.setBounds(125, 46, 86, 21);
        gloabCfgPl.add(sysSkinCbx);
        
        JPanel pojoCfgPl = new JPanel();
        pojoCfgPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getPCTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pojoCfgPl.setBounds(14, 100, 440, 80);
        add(pojoCfgPl);
        pojoCfgPl.setLayout(null);
        
        JLabel poFltVarNmLb = new JLabel(lang.getPCFltVarName());
        poFltVarNmLb.setHorizontalAlignment(SwingConstants.RIGHT);
        poFltVarNmLb.setBounds(30, 20, 89, 15);
        pojoCfgPl.add(poFltVarNmLb);
        
        poFltVarNmTf = new JTextField();
        poFltVarNmTf.setEditable(false);
        poFltVarNmTf.setText("poModel");
        poFltVarNmTf.setBounds(125, 17, 86, 21);
        pojoCfgPl.add(poFltVarNmTf);
        poFltVarNmTf.setColumns(10);
        
        JLabel poClassSuffixLb = new JLabel(lang.getPCPoSuffix());
        poClassSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        poClassSuffixLb.setBounds(244, 49, 89, 15);
        pojoCfgPl.add(poClassSuffixLb);
        
        poClassSuffixTf = new JTextField();
        poClassSuffixTf.setText("PO");
        poClassSuffixTf.setBounds(340, 46, 86, 21);
        pojoCfgPl.add(poClassSuffixTf);
        poClassSuffixTf.setColumns(10);
        
        JLabel poPkSuffixLb = new JLabel(lang.getPCPkSuffix());
        poPkSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        poPkSuffixLb.setBounds(10, 49, 109, 15);
        pojoCfgPl.add(poPkSuffixLb);
        
        poPkClassSuffixTf = new JTextField();
        poPkClassSuffixTf.setText("PK");
        poPkClassSuffixTf.setBounds(125, 46, 86, 21);
        pojoCfgPl.add(poPkClassSuffixTf);
        poPkClassSuffixTf.setColumns(10);
        
        saveParaBtn = new JButton(lang.getMCSavePara());
        saveParaBtn.setBounds(353, 341, 101, 23);
        saveParaBtn.setFocusPainted(false);
        add(saveParaBtn);
        
        JPanel daoCfgPl = new JPanel();
        daoCfgPl.setBounds(14, 191, 440, 140);
        add(daoCfgPl);
        daoCfgPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getDCTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        daoCfgPl.setLayout(null);
        
        JLabel daoFltVarNmLb = new JLabel(lang.getDCDaoFltVarNm());
        daoFltVarNmLb.setHorizontalAlignment(SwingConstants.RIGHT);
        daoFltVarNmLb.setBounds(10, 22, 109, 15);
        daoCfgPl.add(daoFltVarNmLb);
        
        daoFltVarNmTf = new JTextField();
        daoFltVarNmTf.setEditable(false);
        daoFltVarNmTf.setText("daoModel");
        daoFltVarNmTf.setBounds(125, 19, 86, 21);
        daoCfgPl.add(daoFltVarNmTf);
        daoFltVarNmTf.setColumns(10);
        
        JLabel ibatisDaoInterfaceSuffixLb = new JLabel(lang.getDCIbatisInterfaceSuffix());
        ibatisDaoInterfaceSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        ibatisDaoInterfaceSuffixLb.setBounds(0, 50, 119, 15);
        daoCfgPl.add(ibatisDaoInterfaceSuffixLb);
        
        ibatisDaoInterfaceSuffixTf = new JTextField();
        ibatisDaoInterfaceSuffixTf.setText("DAO");
        ibatisDaoInterfaceSuffixTf.setBounds(125, 47, 86, 21);
        daoCfgPl.add(ibatisDaoInterfaceSuffixTf);
        ibatisDaoInterfaceSuffixTf.setColumns(10);
        
        JLabel mybatisDaoInterfaceSuffixLb = new JLabel(lang.getDCMybatisInterfaceSuffix());
        mybatisDaoInterfaceSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        mybatisDaoInterfaceSuffixLb.setBounds(0, 78, 118, 15);
        daoCfgPl.add(mybatisDaoInterfaceSuffixLb);
        
        ibatisDaoClassSuffixTf = new JTextField();
        ibatisDaoClassSuffixTf.setText("Mapper");
        ibatisDaoClassSuffixTf.setBounds(340, 47, 86, 21);
        daoCfgPl.add(ibatisDaoClassSuffixTf);
        ibatisDaoClassSuffixTf.setColumns(10);
        
        JLabel mappingFltVarNmLb = new JLabel(lang.getDCMappingFltVarNm());
        mappingFltVarNmLb.setBounds(214, 22, 119, 15);
        daoCfgPl.add(mappingFltVarNmLb);
        mappingFltVarNmLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        mappingFltVarNmTf = new JTextField();
        mappingFltVarNmTf.setBounds(340, 19, 86, 21);
        daoCfgPl.add(mappingFltVarNmTf);
        mappingFltVarNmTf.setText("mappingModel");
        mappingFltVarNmTf.setEditable(false);
        mappingFltVarNmTf.setColumns(10);
        
        JLabel ibatisDaoClassSuffixLb = new JLabel(lang.getDCIbatisClassSuffix());
        ibatisDaoClassSuffixLb.setBounds(214, 50, 119, 15);
        daoCfgPl.add(ibatisDaoClassSuffixLb);
        ibatisDaoClassSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        mybatisDaoInterfaceSuffixTf = new JTextField();
        mybatisDaoInterfaceSuffixTf.setBounds(125, 75, 86, 21);
        daoCfgPl.add(mybatisDaoInterfaceSuffixTf);
        mybatisDaoInterfaceSuffixTf.setText("DAO");
        mybatisDaoInterfaceSuffixTf.setColumns(10);
        
        JLabel mybatisDaoClassSuffixLb = new JLabel(lang.getDCMybatisClassSuffix());
        mybatisDaoClassSuffixLb.setBounds(204, 78, 129, 15);
        daoCfgPl.add(mybatisDaoClassSuffixLb);
        mybatisDaoClassSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        mybatisDaoClassSuffixTf = new JTextField();
        mybatisDaoClassSuffixTf.setBounds(340, 75, 86, 21);
        daoCfgPl.add(mybatisDaoClassSuffixTf);
        mybatisDaoClassSuffixTf.setText("Mapper");
        mybatisDaoClassSuffixTf.setColumns(10);
        
        JLabel commDaoInterfaceSuffixLb = new JLabel(lang.getDCCommInterfaceSuffix());
        commDaoInterfaceSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        commDaoInterfaceSuffixLb.setBounds(10, 106, 108, 15);
        daoCfgPl.add(commDaoInterfaceSuffixLb);
        
        commDaoInterfaceSuffixTf = new JTextField();
        commDaoInterfaceSuffixTf.setBounds(124, 103, 87, 21);
        daoCfgPl.add(commDaoInterfaceSuffixTf);
        commDaoInterfaceSuffixTf.setColumns(10);
        
        JLabel commDaoClassSuffixLb = new JLabel(lang.getDCCommClassSuffix());
        commDaoClassSuffixLb.setHorizontalAlignment(SwingConstants.RIGHT);
        commDaoClassSuffixLb.setBounds(214, 106, 119, 15);
        daoCfgPl.add(commDaoClassSuffixLb);
        
        commDaoClassSuffixTf = new JTextField();
        commDaoClassSuffixTf.setBounds(340, 103, 86, 21);
        daoCfgPl.add(commDaoClassSuffixTf);
        commDaoClassSuffixTf.setColumns(10);
    }
}
