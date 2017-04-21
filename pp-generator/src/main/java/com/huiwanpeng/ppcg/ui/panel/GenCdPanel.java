package com.huiwanpeng.ppcg.ui.panel;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.huiwanpeng.ppcg.logic.config.glbcfg.GlbCfgHelper;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;
import com.huiwanpeng.ppcg.logic.generator.XbatisGenarator;
import com.huiwanpeng.ppcg.ui.UIParaBeanChecker;
import com.huiwanpeng.ppcg.ui.UIParaParser;
import com.huiwanpeng.ppcg.ui.lang.ILang;
import com.huiwanpeng.ppcg.ui.lang.LangFactory;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.ui.util.ComboxUtil;
import com.huiwanpeng.ppcg.ui.util.MsgUtil;
import com.huiwanpeng.ppcg.ui.util.ToolTipTextUtil;
import com.huiwanpeng.ppcg.util.DirecUtil;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

/**
 * 数据生成面机板
 * @version 1.0  
 */
public class GenCdPanel extends JPanel
{
    private static final long serialVersionUID = 4930114346686791350L;
    
    private SettingPanel settingPanel; // 参数面板
    private DbCfgPanel dbCfgPanel; // 数据源面板
    private SltcTbslPanel sltcTbslPl; //选择标表面板
    
    private JCheckBox genPojoCkbx;
    private JCheckBox usingWrapTypeCkbx;
    private JComboBox<GlbCfgPoTmpModel> pojoFltCbx;
    private JTextField pojoPackageNameTf;
    private JTextField pojoSaveLocatTf;
    private JButton pojoSavePathBtn;
    private JCheckBox sameFileLocatCkbx;
    
    private JCheckBox genDaoCkbx;
    private JComboBox<GlbCfgDaoTmpModel> daoFltCbx;
    private JTextField daoItfsPackageNameTf;
    private JTextField daoImplPackageNameTf;
    private JTextField dosSaveLocatTf;
    private JButton daoSavePathBtn;
    
    private JButton genCodeBtn;
    
    private ILang lang;
    private GlbCfgModel glbCfgModel;
    
    
    
    /**
     * 初始化
     * @param frmXbatis
     * @param uiParaBean
     */
    public void init(final JFrame frmXbatis, SettingPanel settingPanel, DbCfgPanel dbCfgPanel, SltcTbslPanel sltcTbslPl, GlbCfgModel glbCfgModel, final  UIParaBean uiParaBean){
        
        this.glbCfgModel = glbCfgModel;
        this.settingPanel = settingPanel;
        this.dbCfgPanel = dbCfgPanel;
        this.sltcTbslPl = sltcTbslPl;
        
        this.genPojoCkbx.setSelected(uiParaBean.isGenPojoCode());
        this.usingWrapTypeCkbx.setSelected(uiParaBean.isWapperType());
        ComboxUtil.initPOComboxWithArray(pojoFltCbx, GlbCfgHelper.getEnableGlbCfgPoTmpModelLst(glbCfgModel), true); //初始化
        ComboxUtil.selPOComboxWithObj(pojoFltCbx, uiParaBean.getGlbCfgPoTmpModel()); // 设置选中的模板
        GlbCfgPoTmpModel tempGlbCfgPoTmpModel = ComboxUtil.getPOComboxSelectedItem(pojoFltCbx);
        pojoFltCbx.setToolTipText(ToolTipTextUtil.getCfgPoToolTipText(tempGlbCfgPoTmpModel));
        pojoFltCbx.setFocusable(false); // 不让获得焦点,不然选中后底色为蓝色
        this.pojoPackageNameTf.setText(uiParaBean.getPojoPackageName());
        this.pojoSaveLocatTf.setText(uiParaBean.getSavePojoFilePath());
        this.sameFileLocatCkbx.setSelected(uiParaBean.isSameSaveFilePath());
        
        this.genDaoCkbx.setSelected(uiParaBean.isGenDaoCode());
        ComboxUtil.initDAOComboxWithArray(daoFltCbx, GlbCfgHelper.getEnableGlbCfgDaoTmpModelLst(glbCfgModel), true); //初始化
        ComboxUtil.selDAOComboxWithObj(daoFltCbx, uiParaBean.getGlbCfgDaoTmpModel()); // 设置选中的模板
        daoFltCbx.setFocusable(false); // 不让获得焦点,不然选中后底色为蓝色
        this.dosSaveLocatTf.setText(uiParaBean.getSaveDaoFilePath());
        this.daoImplPackageNameTf.setText(uiParaBean.getDaoClassPackageName());
        
        this.genCodeBtn.setEnabled(uiParaBean.isGenPojoCode()); // 生成pojo按钮是否可用
        
        this.pojoSavePathBtn.setToolTipText(lang.getChooseOpenToolTipTxt());
        this.daoSavePathBtn.setToolTipText(lang.getChooseOpenToolTipTxt());
        this.setGenCodeConfigEnabled(0, uiParaBean.isGenPojoCode()); // 设置控件是否可用
        this.setGenCodeConfigEnabled(1, uiParaBean.isGenDaoCode()); // 设置控件是否可用
        initDaoItfsPackageName(uiParaBean); // 动态设置daoFltCbx的ToolTipText, 和接口包名是否可用, 这一步放在最后, 不然会被上一步覆盖
        
        // 生成代码面板初始化 -----------------------------------------
        // 生成pojo复选框选中时, 面板的控件是否可用
        genPojoCkbx.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
               if(genPojoCkbx.isSelected()){
                   genCodeBtn.setEnabled(true);
                   setGenCodeConfigEnabled(0, true);
               }
               else{
                   genCodeBtn.setEnabled(false);
                   // 生成POJO取消, Mapping,Dao都取消
                   setGenCodeConfigEnabled(0, false);
                   setGenCodeConfigEnabled(1, false);
                   genDaoCkbx.setSelected(false);
               }
            }
        });
        // pojo下拉选择框值改变时, 更改ToolText
        pojoFltCbx.addItemListener(new ItemListener(){
                
                @Override
                public void itemStateChanged(ItemEvent arg0)
                {
                    GlbCfgPoTmpModel tempGlbCfgPoTmpModel = ComboxUtil.getPOComboxSelectedItem(pojoFltCbx);
                    pojoFltCbx.setToolTipText(ToolTipTextUtil.getCfgPoToolTipText(tempGlbCfgPoTmpModel));
                }
        });
        // 生成DAO复选框选中时, 面板的控件是否可用
        genDaoCkbx.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
               if(genDaoCkbx.isSelected()){
                   genCodeBtn.setEnabled(true);
                   // DAO选中, pojo必须选中
                   genPojoCkbx.setSelected(true);
                   setGenCodeConfigEnabled(0, true);
                   setGenCodeConfigEnabled(1, true);
               }
               else{
                   // DAO取消
                   setGenCodeConfigEnabled(1, false);
               }
            }
        });
        
        //鼠标左键选择pojo的所在路径, 右键打开所在目录
        pojoSavePathBtn.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e){
                    if(e.getButton() == MouseEvent.BUTTON1){
                        String currentPath = DirecUtil.getFileParentDirect(pojoSaveLocatTf.getText()); // 默认当前目录设置
                        JFileChooser chooser = new JFileChooser();
                        chooser.setDialogTitle("Choose File");
                        if(null != currentPath){
                            chooser.setCurrentDirectory(new File(currentPath));
                        }
                        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        chooser.showOpenDialog(frmXbatis);
                        File file = chooser.getSelectedFile();
                        if(file != null){
                            pojoSaveLocatTf.setText(file.getAbsolutePath());
                            if(sameFileLocatCkbx.isSelected()){
                                dosSaveLocatTf.setText(file.getAbsolutePath());
                            }
                        }
                    }
                    else {
                        //如果是鼠标其它键, 那么就打开所在的目录
                        try{
                            boolean isExsit = DirecUtil.directExist(pojoSaveLocatTf.getText());
                            if(isExsit){
                                Desktop.getDesktop().open(new File(pojoSaveLocatTf.getText()));
                            }
                        }catch(Throwable t){
                        }
                    }
                    super.mousePressed(e);
                }
            
            });
        
        // dos下拉选择框值改变时, mapping文件也随之改变
        daoFltCbx.addItemListener(new ItemListener(){
                
                @Override
                public void itemStateChanged(ItemEvent arg0)
                {
                    // 动态设置daoFltCbx的ToolTipText, 和接口包名是否可用
                    initDaoItfsPackageName(uiParaBean);
                }
        });
        
        //鼠标左键选择dao的所在路径, 右键打开所在目录
        daoSavePathBtn.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e){
                if(e.getButton() == MouseEvent.BUTTON1){
                    String currentPath = DirecUtil.getFileParentDirect(dosSaveLocatTf.getText()); // 默认当前目录设置
                    JFileChooser chooser = new JFileChooser();
                    chooser.setDialogTitle("Choose File");
                    if(null != currentPath){
                        chooser.setCurrentDirectory(new File(currentPath));
                    }
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.showOpenDialog(frmXbatis);
                    File file = chooser.getSelectedFile();
                    if(file != null){
                        dosSaveLocatTf.setText(file.getAbsolutePath());
                    }
                }
                else {
                    //如果是鼠标其它键, 那么就打开所在的目录
                    try{
                        boolean isExsit = DirecUtil.directExist(dosSaveLocatTf.getText());
                        if(isExsit){
                            Desktop.getDesktop().open(new File(dosSaveLocatTf.getText()));
                        }
                    }catch(Throwable t){
                    }
                }
                super.mousePressed(e);
            }
        });
        // 关联选中
        sameFileLocatCkbx.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
               // 选中时, 如果pojo保存位置有值, 那么把mapping,dos的保存位置设为和pojo保存位置一致
               if(sameFileLocatCkbx.isSelected()){
                   String tempLocatStr = pojoSaveLocatTf.getText();
                   if(tempLocatStr != null && tempLocatStr.trim().length()>0){
                       dosSaveLocatTf.setText(tempLocatStr);
                   }
               }
               // 取消选中时,如果mapping,dos的值和pojo一致, 设置为空
               else{
                   if(dosSaveLocatTf.getText() != null 
                           && dosSaveLocatTf.getText().equals(pojoSaveLocatTf.getText())){
                       dosSaveLocatTf.setText("");
                   }
               }
            }
        });
        // 生成代码
        genCodeBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                genXbatisCode();
            }
        });
    }
    
    
    /**
     * 设置生成代码面板里的控件是否可用
     * @param dbEnabled
     * @param excelEndabled
     */
    private void setGenCodeConfigEnabled(int type, boolean enabled){
        if(0 ==  type){ 
            //是否生成pojo复选框选中时, 面板的控件是否可用
            usingWrapTypeCkbx.setEnabled(enabled);
            pojoFltCbx.setEnabled(enabled);
            pojoPackageNameTf.setEnabled(enabled);
            pojoSaveLocatTf.setEnabled(enabled);
            pojoSavePathBtn.setEnabled(enabled);
            sameFileLocatCkbx.setEnabled(enabled);
        }
        else if(1 == type){
            //是否生成DAO复选框选中时, 面板的控件是否可用
            daoFltCbx.setEnabled(enabled);
            daoItfsPackageNameTf.setEnabled(enabled);
            daoImplPackageNameTf.setEnabled(enabled);
            dosSaveLocatTf.setEnabled(enabled);
            daoSavePathBtn.setEnabled(enabled);
        }
    }
    
    /**
     * 生成代码
     */
    private void genXbatisCode(){
        try{
            UIParaBean uiParaBean = new UIParaBean();
            settingPanel.getValues(uiParaBean); //  得到全局参数
            dbCfgPanel.getValues(uiParaBean); // 得到数据源面版的数据
            sltcTbslPl.getValues(uiParaBean); // 得到表选择面版的数据
            this.getValues(uiParaBean); // 得到生成面版的数据
            
            // 检查要生成的表
            if(0 == uiParaBean.getSelTableNameLst().size()){
                JOptionPane.showMessageDialog(null, "Please select target tables!", "Information", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            boolean isRight = UIParaBeanChecker.checkUIParaBean(uiParaBean, true, true);
            if(isRight){
                XbatisGenarator.genXbatisCodes(uiParaBean);
                saveUIParameters(uiParaBean);
                int result = JOptionPane.showConfirmDialog(null, "Generate Code Successful\n\rWhether open the direct?", "Information",JOptionPane.YES_NO_OPTION);
                if(result == 0){
                    Desktop.getDesktop().open(new File(uiParaBean.getSavePojoFilePath()));
                }
            }
        }catch(ComRuntimeException cex){
            Logger.e(cex.getAllInfo());
            MsgUtil.getShowErrorInfoMessageDialog("Generate Code error", cex);
        }
        catch(Exception ex){
            Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            MsgUtil.getShowErrorInfoMessageDialog("Generate Code error", null);
        }
    }
    
    private void getValues(UIParaBean uiParaBean){
        // pojo
        uiParaBean.setSameSaveFilePath(sameFileLocatCkbx.isSelected()); // pojo/mapping/dao三个文件的位置是否保持相同
        uiParaBean.setGenPojoCode(genPojoCkbx.isSelected()); //是否生成pojo
        uiParaBean.setWapperType(usingWrapTypeCkbx.isSelected()); // 是否使用包装类
        uiParaBean.setGlbCfgPoTmpModel(ComboxUtil.getPOComboxSelectedItem(pojoFltCbx)); //pojo模板
        uiParaBean.setPojoPackageName(StrUtil.trim2empty(pojoPackageNameTf.getText())); //pojo包名
        uiParaBean.setSavePojoFilePath(StrUtil.trim2empty(pojoSaveLocatTf.getText())); // pojo保存位置
        
        // dao
        uiParaBean.setGenDaoCode(genDaoCkbx.isSelected()); //是否生成dao代码
        uiParaBean.setGlbCfgDaoTmpModel(ComboxUtil.getDAOComboxSelectedItem(daoFltCbx)); // dao模板文件
        uiParaBean.setDaoInterfacePackageName(StrUtil.trim2empty(daoItfsPackageNameTf.getText()));
        uiParaBean.setDaoClassPackageName(StrUtil.trim2empty(daoImplPackageNameTf.getText()));//dao包名
        uiParaBean.setSaveDaoFilePath(StrUtil.trim2empty(dosSaveLocatTf.getText())); // 保存存dao文件的路径
    }
    
    /**
     * 保存界面上的参数到xml文件中
     */
    private void saveUIParameters(UIParaBean uiParaBean){
        // 检查参数, 如果正确, 就保存
        boolean right = UIParaBeanChecker.checkUIParaBean(uiParaBean, true, false);
        if(right){
            try{
                UIParaParser.saveUIParaBean(GlbCfgHelper.getUiParamFilePath(glbCfgModel), uiParaBean);
            }catch(ComRuntimeException cex){
                Logger.e(cex.getAllInfo());
                MsgUtil.getShowErrorInfoMessageDialog("保存参数失败", cex);
            }
            catch(Exception ex){
                Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
                MsgUtil.getShowErrorInfoMessageDialog("保存参数失败", null);
            }
        }
    }
    
    /**
     * 初始化DAO接口包名
     * @param uiParaBean
     */
    private void initDaoItfsPackageName(UIParaBean uiParaBean){
        GlbCfgDaoTmpModel glbCfgDaoMappingTmpModel = ComboxUtil.getDAOComboxSelectedItem(daoFltCbx);
        if(null !=  glbCfgDaoMappingTmpModel){
            daoFltCbx.setToolTipText(ToolTipTextUtil.getCfgDaoToolTipText1(glbCfgDaoMappingTmpModel));
            
            // 如果没有接口模板文件, 说明不生需要生成接口, 所以接口包名不可用
            if(StrUtil.isEmpty(glbCfgDaoMappingTmpModel.getDaoInterfaceTemplateFilePath())){
                daoItfsPackageNameTf.setText("");
                daoItfsPackageNameTf.setEnabled(false);
            }
            else{
                daoItfsPackageNameTf.setText(StrUtil.trim2empty(uiParaBean.getDaoInterfacePackageName()));
                daoItfsPackageNameTf.setEnabled(true);
            }
            
            // 如果没有实现类模板文件, 说明不生需要生成接口的实现类, 所以实现类包名不可用
            if(StrUtil.isEmpty(glbCfgDaoMappingTmpModel.getDaoClassTemplateFilePath())){
                daoImplPackageNameTf.setText("");
                daoImplPackageNameTf.setEnabled(false);
            }
            else{
                daoImplPackageNameTf.setText(StrUtil.trim2empty(uiParaBean.getDaoClassPackageName()));
                daoImplPackageNameTf.setEnabled(true);
            }
        }
    }
    
   
    public GenCdPanel(UIParaBean uiParaBean)
    {
        this.lang = LangFactory.getLang(uiParaBean.getLanguage());
        setLayout(null);
        
        genCodeBtn = new JButton(lang.getGCGenCode());
        genCodeBtn.setBounds(360, 340, 93, 23);
        genCodeBtn.setFocusPainted(false);
        this.add(genCodeBtn);
        
        JPanel pojoCfgPl = new JPanel();
        pojoCfgPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getGCPoTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        pojoCfgPl.setBounds(14, 10, 440, 143);
        this.add(pojoCfgPl);
        pojoCfgPl.setLayout(null);
        
        JLabel ThemFileLctLb = new JLabel(lang.getGCPoPackageName());
        ThemFileLctLb.setBounds(10, 80, 80, 15);
        pojoCfgPl.add(ThemFileLctLb);
        ThemFileLctLb.setHorizontalAlignment(SwingConstants.RIGHT);
        
        pojoPackageNameTf = new JTextField();
        pojoPackageNameTf.setBounds(100, 77, 320, 21);
        pojoCfgPl.add(pojoPackageNameTf);
        pojoPackageNameTf.setColumns(10);
        
        genPojoCkbx = new JCheckBox(lang.getGCPoGenCode());
        genPojoCkbx.setBounds(100, 17, 121, 23);
        genPojoCkbx.setFocusPainted(false);
        pojoCfgPl.add(genPojoCkbx);
        
        usingWrapTypeCkbx = new JCheckBox(lang.getGCPoUseWrappedType());
        usingWrapTypeCkbx.setBounds(288, 17, 146, 23);
        usingWrapTypeCkbx.setFocusPainted(false);
        pojoCfgPl.add(usingWrapTypeCkbx);
        
        JLabel saveLocatPath = new JLabel(lang.getGCSavePath());
        saveLocatPath.setHorizontalAlignment(SwingConstants.RIGHT);
        saveLocatPath.setBounds(10, 111, 80, 15);
        pojoCfgPl.add(saveLocatPath);
        
        pojoSaveLocatTf = new JTextField();
        pojoSaveLocatTf.setEditable(false);
        pojoSaveLocatTf.setBounds(100, 108, 182, 21);
        pojoCfgPl.add(pojoSaveLocatTf);
        pojoSaveLocatTf.setColumns(10);
        
        pojoSavePathBtn = new JButton(lang.getGCOpenFile());
        pojoSavePathBtn.setBounds(355, 107, 67, 23);
        pojoSavePathBtn.setFocusPainted(false);
        pojoCfgPl.add(pojoSavePathBtn);
        
        sameFileLocatCkbx = new JCheckBox(lang.getGCPoSamePath());
        sameFileLocatCkbx.setBounds(288, 107, 146, 23);
        sameFileLocatCkbx.setFocusPainted(false);
        pojoCfgPl.add(sameFileLocatCkbx);
        
        JLabel label = new JLabel(lang.getGCFltFile());
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(10, 49, 80, 15);
        pojoCfgPl.add(label);
        
        pojoFltCbx = new JComboBox<GlbCfgPoTmpModel>();
        pojoFltCbx.setBounds(100, 46, 320, 21);
        pojoCfgPl.add(pojoFltCbx);
        
        JPanel daoCfgPl = new JPanel();
        daoCfgPl.setBounds(14, 160, 440, 174);
        this.add(daoCfgPl);
        daoCfgPl.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), lang.getGCDaoTitle(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        daoCfgPl.setLayout(null);
        
        genDaoCkbx = new JCheckBox(lang.getGCDaoGenCode());
        genDaoCkbx.setBounds(100, 15, 115, 23);
        genDaoCkbx.setFocusPainted(false);
        daoCfgPl.add(genDaoCkbx);
        
        JLabel daoSavePathLb = new JLabel(lang.getGCSavePath());
        daoSavePathLb.setHorizontalAlignment(SwingConstants.RIGHT);
        daoSavePathLb.setBounds(0, 139, 90, 15);
        daoCfgPl.add(daoSavePathLb);
        
        dosSaveLocatTf = new JTextField();
        dosSaveLocatTf.setEditable(false);
        dosSaveLocatTf.setBounds(100, 136, 245, 21);
        daoCfgPl.add(dosSaveLocatTf);
        dosSaveLocatTf.setColumns(10);
        
        daoSavePathBtn = new JButton(lang.getGCOpenFile());
        daoSavePathBtn.setBounds(355, 135, 67, 23);
        daoSavePathBtn.setFocusPainted(false);
        daoCfgPl.add(daoSavePathBtn);
        
        JLabel daoImplPackageNameLb = new JLabel(lang.getGCDaoClassPackageName());
        daoImplPackageNameLb.setHorizontalAlignment(SwingConstants.RIGHT);
        daoImplPackageNameLb.setBounds(0, 109, 90, 15);
        daoCfgPl.add(daoImplPackageNameLb);
        
        daoImplPackageNameTf = new JTextField();
        daoImplPackageNameTf.setBounds(100, 106, 320, 21);
        daoCfgPl.add(daoImplPackageNameTf);
        daoImplPackageNameTf.setColumns(10);
        
        JLabel daoFltLb = new JLabel(lang.getGCFltFile());
        daoFltLb.setHorizontalAlignment(SwingConstants.RIGHT);
        daoFltLb.setBounds(29, 46, 61, 15);
        daoCfgPl.add(daoFltLb);
        
        daoFltCbx = new JComboBox<GlbCfgDaoTmpModel>();
        daoFltCbx.setBounds(100, 44, 320, 21);
        daoCfgPl.add(daoFltCbx);
        
        JLabel daoItfsPackageNameLb = new JLabel(lang.getGCDaoInterfacePackageName());
        daoItfsPackageNameLb.setHorizontalAlignment(SwingConstants.RIGHT);
        daoItfsPackageNameLb.setBounds(0, 78, 90, 15);
        daoCfgPl.add(daoItfsPackageNameLb);
        
        daoItfsPackageNameTf = new JTextField();
        daoItfsPackageNameTf.setBounds(100, 75, 320, 21);
        daoCfgPl.add(daoItfsPackageNameTf);
        daoItfsPackageNameTf.setColumns(10);
    }
}
