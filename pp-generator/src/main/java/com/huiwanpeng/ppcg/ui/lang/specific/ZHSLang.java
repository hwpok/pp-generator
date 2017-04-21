package com.huiwanpeng.ppcg.ui.lang.specific;

import com.huiwanpeng.ppcg.ui.lang.ILang;

public class ZHSLang implements ILang
{
    /************** Frame相关 **************  */
    public String getFrameTile() { return "PP持久层代码生成器 - V0.0.1"; }
    public String getTabDataBaseSource(){ return "数据源配置"; }
    public String getTabSelTables(){ return "选择目标表"; }
    public String getTabGenCode(){ return "生成代码"; }
    public String getTabSysPara(){ return "系统参数"; }
    
    /************** 选择数据源面版 **************  */
    public String getDatabaseDataSource(){ return "数据库数据源"; };
    public String getExcelDataSource(){ return "Excel数据源"; };
    public String getDbType(){ return "数据库名称:"; };
    public String getDbCfg(){ return "数据库配置:"; };
    public String getDbDriver(){ return "数据库驱动:"; };
    public String getDbUrl(){ return "数据库地址:"; };
    public String getDbUserName(){ return "用户名:"; };
    public String getDbPassword(){ return "密码:"; };
    public String getDbTestConnection(){ return "测试连接"; };
    
    public String getExlFilePath(){ return "Excel路径:"; };
    public String getExlOpenFile(){ return "打开"; };
    public String getExlDbType(){ return "数据库名称:"; };
    public String getExlCheckFile(){ return "校验文件"; };
    
    /************** 选择目标表面版 **************  */
    public String getQueryTableName(){ return "表名:"; };
    public String getQueryTable(){ return "查找"; };
    public String getTableColumnTableName(){ return "表名"; };
    public String getTableColumnComment(){ return "注释"; };
    
    /************** 生成代码面版 **************  */
    public String getGCOpenFile(){ return "选择"; }; // 公共
    public String getGCFltFile(){ return "模板:"; }; // 公共
    public String getGCSavePath(){ return "保存路径:"; }; // 公共
    
    public String getGCPoTitle(){ return "PO生成器"; };
    public String getGCPoGenCode(){ return "生成PO代码"; };
    public String getGCPoUseWrappedType(){ return "使用包装类"; };
    public String getGCPoPackageName(){ return "PO包名:"; };
    public String getGCPoSamePath(){ return "关联"; };
    
    public String getGCDaoTitle(){ return "DAO生成器"; };
    public String getGCDaoGenCode(){ return "生成DAO代码"; };
    public String getGCDaoInterfacePackageName(){ return "接口包名:"; };
    public String getGCDaoClassPackageName(){ return "类包名:"; };
    
    public String getGCMappingTitle(){ return "MAPPING生成器"; };
    public String getGCMapppingGenCode(){ return "生成Mapping代码"; };
    
    public String getGCGenCode(){ return "生成代码"; };
    
    /************** 系统参数设置面版 **************  */
    public String getGPTitle(){ return "全局参数配置"; };
    public String getGPLang(){ return "语言(重启生效):"; };
    public String[] getGPLangTypes(){ return new String[]{"中文简体", "英文", "中文繁体"}; };
    public String getGPEncoding(){ return "字符集:"; };
    public String getGPSkinStyle(){ return "皮肤(重启生效):"; };
    public String[] getGPSkinStyleTypes(){ return new String[]{"跟随系统", "Windows经典", "Swing默认"}; };
    public String getGPUseCame1Naming(){ return "使用驼峰命名法"; };
    
    public String getPCTitle(){ return "PO生成器配置"; };
    public String getPCFltVarName(){ return "PO Flt变量名:"; };
    public String getPCPkSuffix(){ return "PK后缀:"; };
    public String getPCPoSuffix(){ return "PO后缀:"; };
    
    public String getDCTitle(){ return "DAO生成器配置"; };
    public String getDCDaoFltVarNm(){ return "Dao Flt变量名:";}
    public String getDCMappingFltVarNm(){ return "Mapping Flt变量名:";}
    public String getDCIbatisInterfaceSuffix(){return "ibatis接口后缀:";}
    public String getDCIbatisClassSuffix(){return "ibatis类后缀:";}
    public String getDCMybatisInterfaceSuffix(){return "Mybatis接口后缀:";}
    public String getDCMybatisClassSuffix(){return "Mybatis类后缀:";}
    public String getDCCommInterfaceSuffix(){return "通用接口后缀:";}
    public String getDCCommClassSuffix(){return "通用类后缀:";}
    
    public String getMCSavePara(){ return "保存参数"; };
    
    /******* about **************/
    public String getATPTile(){return "关于";}
    public String getATPSoftName(){return "PP持久层代码生成器 - V0.0.1";}
    
    public String getATPDonateTile(){return "捐助建站";}
    public String getATPDonateAliScan(){return "支付宝扫一扫";}
    public String getATPDonateAliAccount(){return "hwpok@163.com";}
    public String getATPDonateWXScan(){return "微信扫一扫";}
    public String getATPDonateWXAccount(){return "hwp789";}
    public String getATPDonateWarm(){return "微信二维码付款无法获知付款人, 劳烦优先使用支付宝账户, 谢谢.";}
    
    
    public String getATPSoftInfoWebSite(){return " 网站: http://pp.51xgm.com (建设中)";}
    public String getATPSoftInfoQQGroup(){return " QQ群: 3687328  模板交流";}
    public String getATPSoftInfoEmail(){return "Email: hwpok@163.com";}
    public String getATPSoftInfoCopyRight(){return "Copyright © 2016 Wanpeng.Hui All rights reserved. Always free.";}
    
    /**************  提示信息 **************  */
    public String getChooseOpenToolTipTxt(){return "鼠标左键选择目录, 右键打开目录";}
    public String getPayWarnToolTipTxt(){return "请注意支付安全, 谨防假冒";}
    
}
