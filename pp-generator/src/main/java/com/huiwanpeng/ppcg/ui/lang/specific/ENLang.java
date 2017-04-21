package com.huiwanpeng.ppcg.ui.lang.specific;

import com.huiwanpeng.ppcg.ui.lang.ILang;

public class ENLang implements ILang
{
    /************** Frame相关 **************  */
    public String getFrameTile(){ return "PP Persistence Layer Code Generator"; }
    public String getTabDataBaseSource(){ return "Data Source"; }
    public String getTabSelTables(){ return "Select Target Table"; }
    public String getTabGenCode(){ return "Generate Code"; }
    public String getTabSysPara(){ return "Perferences"; }
    
    /************** 选择数据源面版 **************  */
    public String getDatabaseDataSource(){ return "Database Data Source"; };
    public String getExcelDataSource(){ return "Excel Data Source"; };
    public String getDbType(){ return "DB Name:"; };
    public String getDbCfg(){ return "Config File:"; };
    public String getDbDriver(){ return "Driver:"; };
    public String getDbUrl(){ return "URL:"; };
    public String getDbUserName(){ return "User Name:"; };
    public String getDbPassword(){ return "Password:"; };
    public String getDbTestConnection(){ return "Test"; };
    
    public String getExlFilePath(){ return "File Path:"; };
    public String getExlOpenFile(){ return "Open"; };
    public String getExlDbType(){ return "DB Name:"; };
    public String getExlCheckFile(){ return "Check"; };
    
    /************** 选择目标表面版 **************  */
    public String getQueryTableName(){ return "Table Name:"; };
    public String getQueryTable(){ return "Query"; };
    public String getTableColumnTableName(){ return "Table Name"; };
    public String getTableColumnComment(){ return "Comment"; };
    
    /************** 生成代码面版 **************  */
    public String getGCOpenFile(){ return "Opt"; }; // 公共
    public String getGCFltFile(){ return "Template:"; }; // 公共
    public String getGCSavePath(){ return "Save Path:"; }; // 公共
    
    public String getGCPoTitle(){ return "PO Generator"; };
    public String getGCPoGenCode(){ return "Gen PO Code"; };
    public String getGCPoUseWrappedType(){ return "Using Wrapped Type"; };
    public String getGCPoPackageName(){ return "Package:"; };
    public String getGCPoSamePath(){ return "Same"; };
    
    public String getGCDaoTitle(){ return "DAO Generator"; };
    public String getGCDaoGenCode(){ return "Gen DAO Code"; };
    public String getGCDaoInterfacePackageName(){ return "Int Package:"; };
    public String getGCDaoClassPackageName(){ return "Cls Package:"; };
    
    public String getGCMappingTitle(){ return "Mapping Generator"; };
    public String getGCMapppingGenCode(){ return "Gen Mapping Code"; };
    
    public String getGCGenCode(){ return "Generate"; };
    
    /************** 系统参数设置面版 **************  */
    public String getGPTitle(){ return "Global Setting"; };
    public String getGPLang(){ return "Language(Restart):"; };
    public String[] getGPLangTypes(){ return new String[]{"Chinese Simplified","English","Chinese Traditional"}; };
    public String getGPEncoding(){ return "Char Set:"; };
    public String getGPSkinStyle(){ return "Skin(Restart):"; };
    public String[] getGPSkinStyleTypes(){ return new String[]{"Follow System", "Windows Classic", "Swing Default"}; };
    public String getGPUseCame1Naming(){ return "Using Camel Naming"; };
    
    public String getPCTitle(){ return "PO Generator Setting"; };
    public String getPCFltVarName(){ return "PO Flt Var Nm:"; };
    public String getPCPkSuffix(){ return "PK Suffix:"; };
    public String getPCPoSuffix(){ return "PO Suffix:"; };
    
    public String getDCTitle(){ return "DAO Generator Setting"; };
    public String getDCDaoFltVarNm(){ return "Dao Flt Var Nm:";}
    public String getDCMappingFltVarNm(){ return "Mapping Flt Var Nm:";}
    public String getDCIbatisInterfaceSuffix(){return "ibatis Int Suffix:";}
    public String getDCIbatisClassSuffix(){return "ibatis Cls Suffix:";}
    public String getDCMybatisInterfaceSuffix(){return "Mybatis Int Suffix:";}
    public String getDCMybatisClassSuffix(){return "Mybatis Cls Suffix:";}
    public String getDCCommInterfaceSuffix(){return "Comm Int Suffix:";}
    public String getDCCommClassSuffix(){return "Comm Cls Suffix:";}
    
    
    public String getDCFltVarName(){ return "Flt Var Name:"; };
    public String getDCIbatisResFlSuffix(){ return "ibatis Suffix:"; };
    public String getDCMybatisResFlSuffix(){ return "MyBatis Suffix:"; };
    
    public String getMCTitle(){ return "MAPPING Generator Setting"; };
    public String getMCFltVarName(){ return "Flt Var Name:"; };
    public String getMCIbatisResFlSuffix(){ return "ibatis Suffix:"; };
    public String getMCMybatisResFlSuffix(){ return "MyBatis Suffix:"; };
    
    
    
    public String getMCSavePara(){ return "Save"; };
    
    /******* about **************/
    public String getATPTile(){return "About";}
    public String getATPSoftName(){return "PP Persistence Layer Code Generator Peri.1 Release(0.0.1)";}
    
    public String getATPDonateTile(){return "Donate Building Websit";}
    public String getATPDonateAliScan(){return "Ali Pay Scan";}
    public String getATPDonateAliAccount(){return "hwpok@163.com";}
    public String getATPDonateWXScan(){return "Weixin Scan";}
    public String getATPDonateWXAccount(){return "hwp789";}
    public String getATPDonateWarm(){return "Please use Ali Pay first, Thanks.";}
    
    
    public String getATPSoftInfoWebSite(){return " Web Sit: http://x.xbatis.com";}
    public String getATPSoftInfoQQGroup(){return "QQ Group: 3687328  Template Exchange";}
    public String getATPSoftInfoEmail(){return   "   Email: hwpok@163.com";}
    public String getATPSoftInfoCopyRight(){return "Copyright © 2016 Wanpeng.Hui All rights reserved. Always free.";}
    
    /**************  提示信息 **************  */
    public String getChooseOpenToolTipTxt(){return "Left mouse button to choose directory, right mouse button to open directory";}
    public String getPayWarnToolTipTxt(){return "Please pay attention to the payment of security, to guard against counterfeiting";}
}
