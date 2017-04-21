package com.huiwanpeng.ppcg.ui.lang;

public interface ILang
{
    /************** Frame相关 **************  */
    public String getFrameTile();
    public String getTabDataBaseSource();
    public String getTabSelTables();
    public String getTabGenCode();
    public String getTabSysPara();
    
    /************** 选择数据源面版 **************  */
    public String getDatabaseDataSource();
    public String getExcelDataSource();
    public String getDbType();
    public String getDbCfg();
    public String getDbDriver();
    public String getDbUrl();
    public String getDbUserName();
    public String getDbPassword();
    public String getDbTestConnection();
    
    public String getExlFilePath();
    public String getExlOpenFile();
    public String getExlDbType();
    public String getExlCheckFile();
    
    /************** 选择目标表面版 **************  */
    public String getQueryTableName();
    public String getQueryTable();
    public String getTableColumnTableName();
    public String getTableColumnComment();
    
    /************** 生成代码面版 **************  */
    public String getGCOpenFile();
    public String getGCFltFile();
    public String getGCSavePath();
    
    public String getGCPoTitle();
    public String getGCPoGenCode();
    public String getGCPoUseWrappedType();
    public String getGCPoPackageName();
    public String getGCPoSamePath();
    
    public String getGCDaoTitle();
    public String getGCDaoGenCode();
    public String getGCDaoInterfacePackageName();
    public String getGCDaoClassPackageName();
    
    public String getGCMappingTitle();
    public String getGCMapppingGenCode();
    public String getGCGenCode();
    
    /************** 系统参数设置面版 **************  */
    public String getGPTitle();
    public String getGPLang();
    public String[] getGPLangTypes();
    public String getGPEncoding();
    public String getGPSkinStyle();
    public String[] getGPSkinStyleTypes();
    public String getGPUseCame1Naming();
    
    public String getPCTitle();
    public String getPCFltVarName();
    public String getPCPkSuffix();
    public String getPCPoSuffix();
    
    public String getDCTitle();
    public String getDCDaoFltVarNm();
    public String getDCMappingFltVarNm();
    public String getDCIbatisInterfaceSuffix();
    public String getDCIbatisClassSuffix();
    public String getDCMybatisInterfaceSuffix();
    public String getDCMybatisClassSuffix();
    public String getDCCommInterfaceSuffix();
    public String getDCCommClassSuffix();
    
    public String getMCSavePara();
    
    /******* about tab panel **************/
    public String getATPTile();
    public String getATPSoftName();
    
    public String getATPDonateTile();
    public String getATPDonateAliScan();
    public String getATPDonateAliAccount();
    public String getATPDonateWXScan();
    public String getATPDonateWXAccount();
    public String getATPDonateWarm();
    
    
    public String getATPSoftInfoWebSite();
    public String getATPSoftInfoQQGroup();
    public String getATPSoftInfoEmail();
    public String getATPSoftInfoCopyRight();
    
    /************* tool tip text ***********/
    public String getChooseOpenToolTipTxt();
    public String getPayWarnToolTipTxt();
}
