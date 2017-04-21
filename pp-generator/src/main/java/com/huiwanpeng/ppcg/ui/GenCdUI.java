package com.huiwanpeng.ppcg.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.logic.tblinfo.xls.util.MyDecode;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.ui.panel.AboutPanel;
import com.huiwanpeng.ppcg.ui.panel.DbCfgPanel;
import com.huiwanpeng.ppcg.ui.panel.GenCdPanel;
import com.huiwanpeng.ppcg.ui.panel.SettingPanel;
import com.huiwanpeng.ppcg.ui.panel.SltcTbslPanel;
import com.huiwanpeng.ppcg.util.Monitor;

public class GenCdUI
{
    public JFrame frmXbatis;
    private SettingPanel settingPanel;
    private DbCfgPanel dbCfgPanel;
    private SltcTbslPanel sltcTbslPl;
    private GenCdPanel genCdPl;
    private AboutPanel aboutPl;
    private ILang lang;
    private UIParaBean uiParaBean;
    
    /**
     * Create the application.
     */
    public GenCdUI(GlbCfgModel glbCfgModel, UIParaBean uiParaBean)
    {
        this.uiParaBean = uiParaBean; // 全局配置参数
        lang = LangFactory.getLang(uiParaBean.getLanguage());
        initialize();
        
        settingPanel.init(glbCfgModel, uiParaBean); // 初始化全局参数面版
        Monitor.putMonitorPoint("init settingPanel");
        
        dbCfgPanel.init(frmXbatis, glbCfgModel, uiParaBean); // 初始经数据源面版
        Monitor.putMonitorPoint("init dataSourcePanel");
        
        sltcTbslPl.init(frmXbatis, dbCfgPanel, uiParaBean); //初始化选择目标表面版
        Monitor.putMonitorPoint("init sltcTablPanel");
        
        genCdPl.init(frmXbatis, settingPanel, dbCfgPanel, sltcTbslPl, glbCfgModel, uiParaBean); // 初始化生成代码面版
        Monitor.putMonitorPoint("init genCdPanel");
        
    }
    
    /**
     * 初始化
     */
    private void initialize()
    {
        frmXbatis = new JFrame();
        Monitor.putMonitorPoint("create main Frame");
        
        frmXbatis.setTitle(lang.getFrameTile());
        frmXbatis.setBounds(100, 100, 489, 447);
        frmXbatis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((screenSize.getWidth() - frmXbatis.getWidth())/2);
        int y = (int)((screenSize.getHeight() - frmXbatis.getHeight())/2);
        frmXbatis.setResizable(false);
        frmXbatis.setLocation(x,y);
        frmXbatis.getContentPane().setLayout(null);
        frmXbatis.setIconImage(MyDecode.getImageLogo().getImage());
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 489, 420);
        tabbedPane.setFocusable(false);
        frmXbatis.getContentPane().add(tabbedPane);
        
        settingPanel = new SettingPanel(uiParaBean);
        
        dbCfgPanel = new DbCfgPanel(uiParaBean);
        tabbedPane.addTab(lang.getTabDataBaseSource(), null, dbCfgPanel, null);
        
        sltcTbslPl = new SltcTbslPanel(uiParaBean);
        tabbedPane.addTab(lang.getTabSelTables(), null, sltcTbslPl, null);
        
        genCdPl = new GenCdPanel(uiParaBean);
        tabbedPane.addTab(lang.getTabGenCode(), null, genCdPl, null);
        
        tabbedPane.addTab(lang.getTabSysPara(), null, settingPanel, null);
        
        aboutPl = new AboutPanel(uiParaBean);
        tabbedPane.addTab(lang.getATPTile(), null, aboutPl, null);
        
        /**
        // 状态栏
        JToolBar toolBar = new JToolBar();
        toolBar.add(new JLabel("state"));
        toolBar.add(new JProgressBar());
        toolBar.setFloatable(false);
        toolBar.setBounds(0, 400, 460, 30);
        
        frmXbatis.getContentPane().add(toolBar);
        */
    }
}
