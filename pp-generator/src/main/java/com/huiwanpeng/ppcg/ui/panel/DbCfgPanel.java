package com.huiwanpeng.ppcg.ui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.huiwanpeng.ppcg.logic.config.dbcfg.DBCfgParser;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.DBCfgModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.GlbCfgHelper;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.logic.generator.XbatisGenarator;
import com.huiwanpeng.ppcg.logic.tblinfo.xls.util.ExcelParser;
import com.huiwanpeng.ppcg.ui.UIParaBeanChecker;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.ui.util.ComboxUtil;
import com.huiwanpeng.ppcg.ui.util.ExcelFileFilter;
import com.huiwanpeng.ppcg.ui.util.MsgUtil;
import com.huiwanpeng.ppcg.ui.util.ToolTipTextUtil;
import com.huiwanpeng.ppcg.util.DirecUtil;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

/**
 * 数据源面版
 * @version 1.0  
 */
public class DbCfgPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    
    JFrame frmXbatis;
    
    // 数据库数据源
    private JRadioButton dbSourceRb;
    private JRadioButton excelSourceRb;
    private JComboBox<GlbCfgDbModel> dbTypeCmbx;
    private JTextField dbDriverTF;
    private JTextField dbAdressTF;
    private JTextField dbUserNameTF;
    private JPasswordField dbPasswordTF;
    private JButton testCntBtn;
    
    // EXCEl数据源
    private JTextField excelFileLocatTf;
    private JComboBox<GlbCfgDbModel> excelDbTypeCb;
    private JButton excelFileLocatBtn;
    private JButton excelCheckBtn;
    
    // 语言
    private ILang lang;
    
    /**
     * 初始化
     * @param frmXbatis
     * @param uiParaBean
     */
    public void init(final JFrame frmXbatis, GlbCfgModel glbCfgModel, UIParaBean uiParaBean){
        this.frmXbatis = frmXbatis;
        List<GlbCfgDbModel> glbCfgDbModelLst = GlbCfgHelper.getEnableGlbCfgDbModelLst(glbCfgModel);
        
        // 数据源选项
        boolean dbSource = (0==uiParaBean.getDataSourceType());
        this.dbSourceRb.setSelected(dbSource);
        this.excelSourceRb.setSelected(!dbSource);
        this.setDataSourceConfigEnabled(dbSource,!dbSource); // 设置控件是否可用
        ComboxUtil.initDBComboxWithArray(dbTypeCmbx, glbCfgDbModelLst, true);// 初始化下拉列表框
        ComboxUtil.selDBComboxWithId(dbTypeCmbx, uiParaBean.getDbModel()); //初始化DBID
        dbTypeCmbx.setFocusable(false); // 不让获得焦点,不然选中后底色为蓝色
        if(null != dbTypeCmbx.getSelectedItem()){
            GlbCfgDbModel tempObj = (GlbCfgDbModel)dbTypeCmbx.getSelectedItem();
            dbTypeCmbx.setToolTipText(ToolTipTextUtil.getCfgDbToolTipText(tempObj));
        }
        this.dbDriverTF.setText(uiParaBean.getDbDriver());
        this.dbAdressTF.setText(uiParaBean.getDbUrl()); //dbUrl
        this.dbUserNameTF.setText(uiParaBean.getDbUserName()); //dbUserName
        this.dbPasswordTF.setText(uiParaBean.getDbPassword()); //dbPassword
        this.excelFileLocatTf.setText(uiParaBean.getExFilePath()); //excel文件位置
        ComboxUtil.initDBComboxWithArray(excelDbTypeCb, glbCfgDbModelLst, true);// 初始化下拉列表框
        ComboxUtil.selDBComboxWithId(excelDbTypeCb, uiParaBean.getExDbModel()); //初始化DBID
        excelDbTypeCb.setFocusable(false); // 不让获得焦点,不然选中后底色为蓝色
        if(null != excelDbTypeCb.getSelectedItem()){
            GlbCfgDbModel tempObj = (GlbCfgDbModel)excelDbTypeCb.getSelectedItem();
            excelDbTypeCb.setToolTipText(ToolTipTextUtil.getCfgDbToolTipText(tempObj));
        }
        
        //当数据库数据源按钮选中时, 数据库数据源面板中的控件可用
        dbSourceRb.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent arg0)
                {
                   if(dbSourceRb.isSelected()){
                       setDataSourceConfigEnabled(true,false);
                   }
                }
            });
        
       //当Excel数据源按钮选中时, Excel数据源面板中的控件可用
        excelSourceRb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
               if(excelSourceRb.isSelected()){
                   setDataSourceConfigEnabled(false,true);
               }
            }
        });
        
        // DB数据源, 数据库类型选中事件
        excelDbTypeCb.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent arg0)
            {
                if(null != excelDbTypeCb.getSelectedItem()){
                    GlbCfgDbModel tempObj = (GlbCfgDbModel)excelDbTypeCb.getSelectedItem();
                    excelDbTypeCb.setToolTipText(ToolTipTextUtil.getCfgDbToolTipText(tempObj));
                }
            }
        });
        
        //选择Excel的所在路径
        excelFileLocatBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                // 默认当前目录
                String currentPath = DirecUtil.getFileParentDirect(excelFileLocatTf.getText());
                
                ExcelFileFilter excelFileFilter = new ExcelFileFilter();
                JFileChooser chooser = new JFileChooser();
                if(null != currentPath){
                    chooser.setCurrentDirectory(new File(currentPath));
                }
                chooser.setDialogTitle("Choose File");
                chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                chooser.addChoosableFileFilter(excelFileFilter);
                chooser.setFileFilter(excelFileFilter);
                chooser.showOpenDialog(frmXbatis);
                File file = chooser.getSelectedFile();
                if(null != file && !file.isDirectory()){
                    excelFileLocatTf.setText(file.getAbsolutePath());
                    matchExcepDbType();
                }
            }
        });
        
        // 测试连接
        testCntBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                canConnectDataBase(false);
            }
        });
        
        //Excel数据源, 数据库类型选中事件
        dbTypeCmbx.addItemListener(new ItemListener(){
                
                @Override
                public void itemStateChanged(ItemEvent arg0)
                {
                    dbTypeCmbxChangeEven();
                }
        });
        
        // 检查Excel是否正确
        excelCheckBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                checkExcelFile(false);
            }
        });
    }
    
    /**
     * 设置数据源面板里的控件是否可用
     * @param dbEnabled
     * @param excelEndabled
     */
    private void setDataSourceConfigEnabled(boolean dbEnabled, boolean excelEndabled){
        // 设置数据库数据源
        dbTypeCmbx.setEnabled(dbEnabled);
        dbDriverTF.setEnabled(dbEnabled);
        dbAdressTF.setEnabled(dbEnabled); 
        dbUserNameTF.setEnabled(dbEnabled);
        dbPasswordTF.setEnabled(dbEnabled);
        testCntBtn.setEnabled(dbEnabled);
        
        // 设置Excel数据源
        excelFileLocatTf.setEnabled(excelEndabled);
        excelDbTypeCb.setEnabled(excelEndabled);
        excelFileLocatBtn.setEnabled(excelEndabled);
        excelCheckBtn.setEnabled(excelEndabled);
    }
    
    /**
     * 数据库类型改变事件
     */
    private void dbTypeCmbxChangeEven(){
        
        try{
            GlbCfgDbModel glbCfgDbModel = (GlbCfgDbModel)dbTypeCmbx.getSelectedItem();
            DBCfgModel dbCfgModel = DBCfgParser.getDBCfgModelNew(glbCfgDbModel.getConfigFilePath());
            if(dbCfgModel != null){
                dbTypeCmbx.setToolTipText(ToolTipTextUtil.getCfgDbToolTipText(glbCfgDbModel));
                dbDriverTF.setText(dbCfgModel.getDriver());
                dbAdressTF.setText(dbCfgModel.getConnectUrlExample());
            }
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("DB Type change event error", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("DB Type change event error", null);
        }
    }
    
    /**
     * 测试数据库是否可连接
     */
    public boolean canConnectDataBase(boolean sclience){
        try{
            UIParaBean uiParaBean = new UIParaBean();
            this.getValues(uiParaBean);
            boolean isRight = UIParaBeanChecker.checkDataSourcePanel(uiParaBean);
            if(isRight){
                XbatisGenarator.canConnectDatabase(uiParaBean);
                if(!sclience){
                    //Object[] objs = new Object[]{"Confirm"};
                    //JOptionPane.showOptionDialog(null, "connecte database successful", "Information",JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, objs, objs[0]);
                    JOptionPane.showMessageDialog(null, "connecte database successful", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            }
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("connecte database fail", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("connecte database fail", null);
        }
        return false;
    }
    
    /**
     * 检查Excel文件是否有效
     */
    public boolean checkExcelFile(boolean sclience){
        try{
            UIParaBean uiParaBean = new UIParaBean();
            this.getValues(uiParaBean);
            boolean isRight = UIParaBeanChecker.checkExcelDataSourcePanel(uiParaBean);
            if(isRight){
                boolean match = matchExcepDbType();
                // 如果没有匹配的报数据类型不正确
                if(!match){
                    JOptionPane.showMessageDialog(null, "excel database type is correct", "Information", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
                        
                XbatisGenarator.checkExcelFile(uiParaBean.getExFilePath(), uiParaBean.getExDbModel().getName(), uiParaBean.getExDbModel().getConfigFilePath());
                if(!sclience){
                    JOptionPane.showMessageDialog(null, "The excel file format is correct", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            }
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("The excel file format is not correct ", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("The excel file format is not correct", null);
        }
        return false;
    }
    
    /**
     * 配置Excel数据库类型
     * @return
     */
    private boolean matchExcepDbType(){
        // 得到Excel文件的数据库类型, 和下拉框的比较, 如果有相同的,就自动选中,否则报Excel数据库类型不正确
        boolean match = false;
        String excelDbName = ExcelParser.getDatabaseType(excelFileLocatTf.getText());
        int itemCount = excelDbTypeCb.getItemCount();
        GlbCfgDbModel tempGlbCfgDbModel = null;
        for(int i=0;i<itemCount; i++){
            tempGlbCfgDbModel = excelDbTypeCb.getItemAt(i);
            if(tempGlbCfgDbModel.getName().equals(excelDbName)){
                excelDbTypeCb.setSelectedIndex(i);
                match = true;
            }
        }
        return match;
    }
    
    /**
     * 得到面片的值
     * @param uiParaBean
     */
    public void getValues(UIParaBean uiParaBean){
        // 数据库数据源面板
        uiParaBean.setDataSourceType(dbSourceRb.isSelected() ? 0 : 1); // 0:database, 1:excel
        uiParaBean.setDbModel(ComboxUtil.getDBComboxSelectedItem(dbTypeCmbx)); // 数据库类型
        uiParaBean.setDbDriver(StrUtil.trim2empty(dbDriverTF.getText())); // 数据库驱动
        uiParaBean.setDbUrl(StrUtil.trim2empty(dbAdressTF.getText())); // 数据URL
        uiParaBean.setDbUserName(StrUtil.trim2empty(dbUserNameTF.getText())); // 用户名
        uiParaBean.setDbPassword(StrUtil.trim2empty(new String(dbPasswordTF.getPassword()))); // 密码
        //Excel 数据源
        uiParaBean.setExFilePath(StrUtil.trim2empty(excelFileLocatTf.getText())); // excel文件路径
        uiParaBean.setExDbModel(ComboxUtil.getDBComboxSelectedItem(excelDbTypeCb)); // excel的文件的数据库类型
    }
    
    
    /**
     * Create the panel
     */
    public DbCfgPanel(UIParaBean uiParaBean)
    {
        this.lang = LangFactory.getLang(uiParaBean.getLanguage());
        setLayout(null);
        JPanel dbSourcePl = new JPanel();
        dbSourcePl.setBorder(new TitledBorder(null, lang.getDatabaseDataSource(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
        dbSourcePl.setBounds(14, 28, 440, 173);
        this.add(dbSourcePl);
        dbSourcePl.setLayout(null);
        
        JLabel dbTypeLb = new JLabel(lang.getDbType());
        dbTypeLb.setBounds(9, 28, 81, 15);
        dbSourcePl.add(dbTypeLb);
        dbTypeLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        
        dbTypeCmbx = new JComboBox<GlbCfgDbModel>();
        dbTypeCmbx.setBounds(100, 25, 320, 21);
        dbSourcePl.add(dbTypeCmbx);
        
        JLabel dbAddressLb = new JLabel(lang.getDbUrl());
        dbAddressLb.setBounds(19, 85, 71, 15);
        dbSourcePl.add(dbAddressLb);
        dbAddressLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        dbAdressTF = new JTextField();
        dbAdressTF.setBounds(100, 82, 320, 21);
        dbSourcePl.add(dbAdressTF);
        dbAdressTF.setColumns(10);
        
        dbUserNameTF = new JTextField();
        dbUserNameTF.setBounds(100, 110, 320, 21);
        dbSourcePl.add(dbUserNameTF);
        dbUserNameTF.setColumns(10);
        
        JLabel dbUserNameLb = new JLabel(lang.getDbUserName());
        dbUserNameLb.setBounds(10, 114, 80, 15);
        dbSourcePl.add(dbUserNameLb);
        dbUserNameLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        JLabel dbPasswordLb = new JLabel(lang.getDbPassword());
        dbPasswordLb.setBounds(9, 142, 80, 15);
        dbSourcePl.add(dbPasswordLb);
        dbPasswordLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        dbPasswordTF = new JPasswordField();
        dbPasswordTF.setEchoChar('*');
        dbPasswordTF.setBounds(99, 139, 320, 21);
        dbSourcePl.add(dbPasswordTF);
        dbPasswordTF.setColumns(10);
        
        JLabel dbDriverLb = new JLabel(lang.getDbDriver());
        dbDriverLb.setHorizontalAlignment(SwingConstants.RIGHT);
        dbDriverLb.setBounds(19, 56, 71, 15);
        dbSourcePl.add(dbDriverLb);
        
        dbDriverTF = new JTextField();
        dbDriverTF.setEditable(false);
        dbDriverTF.setBounds(99, 53, 320, 21);
        dbSourcePl.add(dbDriverTF);
        dbDriverTF.setColumns(10);
        
        JPanel excelSourcePl = new JPanel();
        excelSourcePl.setBorder(new TitledBorder(null, lang.getExcelDataSource(), TitledBorder.LEFT, TitledBorder.TOP, null, null));
        excelSourcePl.setBounds(14, 244, 440, 90);
        this.add(excelSourcePl);
        excelSourcePl.setLayout(null);
        
        JLabel excelFileLocatLb = new JLabel(lang.getExlFilePath());
        excelFileLocatLb.setHorizontalAlignment(SwingConstants.RIGHT);
        excelFileLocatLb.setBounds(10, 24, 82, 15);
        excelSourcePl.add(excelFileLocatLb);
        
        excelFileLocatTf = new JTextField();
        excelFileLocatTf.setBounds(100, 21, 241, 21);
        excelSourcePl.add(excelFileLocatTf);
        excelFileLocatTf.setColumns(10);
        
        excelFileLocatBtn = new JButton(lang.getExlOpenFile());
        excelFileLocatBtn.setBounds(351, 20, 69, 23);
        excelFileLocatBtn.setFocusPainted(false);
        excelSourcePl.add(excelFileLocatBtn);
        
        excelDbTypeCb = new JComboBox<GlbCfgDbModel>();
        excelDbTypeCb.setBounds(100, 52, 320, 21);
        excelSourcePl.add(excelDbTypeCb);
        
        JLabel excelDbTypeLb = new JLabel(lang.getExlDbType());
        excelDbTypeLb.setHorizontalAlignment(SwingConstants.RIGHT);
        excelDbTypeLb.setBounds(10, 56, 82, 15);
        excelSourcePl.add(excelDbTypeLb);
        
        ButtonGroup dataSourceBg = new ButtonGroup();
        dbSourceRb = new JRadioButton(lang.getDatabaseDataSource());
        dbSourceRb.setBounds(111, 6, 152, 23);
        dbSourceRb.setFocusPainted(false);
        dataSourceBg.add(dbSourceRb);
        this.add(dbSourceRb);
        
        excelSourceRb = new JRadioButton(lang.getExcelDataSource());
        excelSourceRb.setBounds(265, 6, 143, 23);
        excelSourceRb.setFocusPainted(false);
        dataSourceBg.add(excelSourceRb);
        this.add(excelSourceRb);
        
        testCntBtn = new JButton(lang.getDbTestConnection());
        testCntBtn.setBounds(368, 211, 86, 23);
        testCntBtn.setFocusPainted(false);
        this.add(testCntBtn);
        
        excelCheckBtn = new JButton(lang.getExlCheckFile());
        excelCheckBtn.setBounds(366, 339, 86, 23);
        excelCheckBtn.setFocusPainted(false);
        this.add(excelCheckBtn);
    }
}
