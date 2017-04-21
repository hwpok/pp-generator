package com.huiwanpeng.ppcg.ui.lang.specific;

import com.huiwanpeng.ppcg.ui.lang.ILang;

public class ZHTLang implements ILang
{
    /************** Frame相关 **************  */
    public String getFrameTile() { return "PP持久層代碼生成器 - V0.0.1"; }
    public String getTabDataBaseSource(){ return "數據源配置"; }
    public String getTabSelTables(){ return "選擇目標表"; }
    public String getTabGenCode(){ return "生成代碼"; }
    public String getTabSysPara(){ return "系統參數"; }
    
    /************** 选择数据源面版 **************  */
    public String getDatabaseDataSource(){ return "數據庫數據源"; };
    public String getExcelDataSource(){ return "Excel數據源"; };
    public String getDbType(){ return "數據庫名稱:"; };
    public String getDbCfg(){ return "數據庫配置:"; };
    public String getDbDriver(){ return "數據庫驅動:"; };
    public String getDbUrl(){ return "數據庫地址:"; };
    public String getDbUserName(){ return "用戶名:"; };
    public String getDbPassword(){ return "密碼:"; };
    public String getDbTestConnection(){ return "測試連接"; };
    
    public String getExlFilePath(){ return "Excel路徑:"; };
    public String getExlOpenFile(){ return "打開"; };
    public String getExlDbType(){ return "數據庫名稱:"; };
    public String getExlCheckFile(){ return "校驗文件"; };
    
    /************** 选择目标表面版 **************  */
    public String getQueryTableName(){ return "表名:"; };
    public String getQueryTable(){ return "查找"; };
    public String getTableColumnTableName(){ return "表名"; };
    public String getTableColumnComment(){ return "註釋"; };
    
    /************** 生成代码面版 **************  */
    public String getGCOpenFile(){ return "選擇"; }; // 公共
    public String getGCFltFile(){ return "模板:"; }; // 公共
    public String getGCSavePath(){ return "保存路徑:"; }; // 公共
    
    public String getGCPoTitle(){ return "PO生成器"; };
    public String getGCPoGenCode(){ return "生成PO代碼"; };
    public String getGCPoUseWrappedType(){ return "使用包裝類"; };
    public String getGCPoPackageName(){ return "PO包名:"; };
    public String getGCPoSamePath(){ return "關聯"; };
    
    public String getGCDaoTitle(){ return "DAO生成器"; };
    public String getGCDaoGenCode(){ return "生成DAO代碼"; };
    public String getGCDaoInterfacePackageName(){ return "接口包名:"; };
    public String getGCDaoClassPackageName(){ return "类包名:"; };
    
    public String getGCMappingTitle(){ return "MAPPING生成器"; };
    public String getGCMapppingGenCode(){ return "生成Mapping代碼"; };
    
    public String getGCGenCode(){ return "生成代碼"; };
    
    /************** 系统参数设置面版 **************  */
    public String getGPTitle(){ return "全局參數配置"; };
    public String getGPLang(){ return "語言(重啟生效):"; };
    public String[] getGPLangTypes(){ return new String[]{"中文簡體", "英文", "中文繁體"}; };
    public String getGPEncoding(){ return "字符集:"; };
    public String getGPSkinStyle(){ return "皮膚(重啟生效):"; };
    public String[] getGPSkinStyleTypes(){ return new String[]{"跟隨系統", "Windows經典", "Swing默認"}; };
    public String getGPUseCame1Naming(){ return "使用駝峰命名法"; };
    
    public String getPCTitle(){ return "PO生成器配置"; };
    public String getPCFltVarName(){ return "PO Flt變量名:"; };
    public String getPCPkSuffix(){ return "PK後綴:"; };
    public String getPCPoSuffix(){ return "PO後綴:"; };
    
    public String getDCTitle(){ return "DAO生成器配置"; };
    public String getDCDaoFltVarNm(){ return "Dao Flt變量名:";}
    public String getDCMappingFltVarNm(){ return "Mapping Flt變量名:";}
    public String getDCIbatisInterfaceSuffix(){return "ibatis接口後綴:";}
    public String getDCIbatisClassSuffix(){return "ibatis類后綴:";}
    public String getDCMybatisInterfaceSuffix(){return "Mybatis接口後綴:";}
    public String getDCMybatisClassSuffix(){return "Mybatis類後綴:";}
    public String getDCCommInterfaceSuffix(){return "通用接口後綴:";}
    public String getDCCommClassSuffix(){return "通用類後綴:";}
    
    public String getMCSavePara(){ return "保存參數"; };
    
    /******* about **************/
    public String getATPTile(){return "關於";}
    public String getATPSoftName(){return "PP持久層代碼生成器 - V0.0.1";}
    
    public String getATPDonateTile(){return "捐助建站";}
    public String getATPDonateAliScan(){return "支付寶掃壹掃";}
    public String getATPDonateAliAccount(){return "hwpok@163.com";}
    public String getATPDonateWXScan(){return "微信掃壹掃";}
    public String getATPDonateWXAccount(){return "hwp789";}
    public String getATPDonateWarm(){return "微信二維碼付款無法獲知付款人, 勞煩優先使用支付寶賬戶, 謝謝.";}
    
    
    public String getATPSoftInfoWebSite(){return " 網站: http://x.xbatis.com (建設中)";}
    public String getATPSoftInfoQQGroup(){return " QQ群: 3687328  模板交流";}
    public String getATPSoftInfoEmail(){return "Email: hwpok@163.com";}
    public String getATPSoftInfoCopyRight(){return "Copyright © 2016 Wanpeng.Hui All rights reserved. Always free.";}
    
    /**************  提示信息 **************  */
    public String getChooseOpenToolTipTxt(){return "鼠標左鍵選擇目錄, 右鍵打開目錄";}
    public String getPayWarnToolTipTxt(){return "請註意支付安全, 謹防假冒";}
}
