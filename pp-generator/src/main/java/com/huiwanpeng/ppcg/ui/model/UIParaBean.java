package com.huiwanpeng.ppcg.ui.model;

import java.util.ArrayList;
import java.util.List;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;

/**
 * UI 参数配置
 * @version 1.0  
 */
public class UIParaBean
{
    // 全局参数 -----------------------------------------
    private int language = 0; // 语言
    private int skinStyle = 1; // 系统的风格
    private String charSetEncoding = ""; //字符集
    private boolean useCame1Naming = true; // 是否使用驼峰命名法
    
    // po 参数
    private String poFltVarNm; // po flt变量名
    private String poPkClassSuffix; // 复合主键的PK类后缀名
    private String poClassSuffix; //po类后缀
    
    // dao参数
    private String daoFltVarNm;  // dao ftl变量
    private String mappingFltVarNm; // dao mapping flt 变量
    private String ibatisDaoInterfaceSuffix; // batis接口后缀
    private String ibatisDaoClassSuffix; // ibatis实现类后缀
    private String mybatisDaoInterfaceSuffix; // mybatis接口后缀
    private String mybatisDaoClassSuffix; // mybatis实现类后缀
    private String commDaoInterfaceSuffix; // 通用接口后缀
    private String commDaoClassSuffix; // 通用实现类后缀
    
    // 数据源面板, database datasource -------------------
    private int dataSourceType = 0; // 0:database, 1:excel
    private GlbCfgDbModel dbModel = new GlbCfgDbModel(); //数据库模型
    private String dbDriver = ""; // 数据库驱动
    private String dbUrl = ""; // 数据库链接
    private String dbUserName = ""; // 用户名
    private String dbPassword = ""; //密码
    private String exFilePath = "/"; // excel文件路径
    private GlbCfgDbModel exDbModel = new GlbCfgDbModel(); // excel的文件的数据库模型
    
    // 数据查询面板 ---------------------------------------
    private String cdnCbxItems=""; // 查询下拉列表初始化值
    private String queryTableCondtion = ""; // 查询条件(不保存在UI参数文件之中)
    
    // 生成代码面板 ---------------------------------------
    private boolean sameSaveFilePath = true; // pojo/mapping/dao三个文件的位置是否保持相同
    private boolean genPojoCode=true; //是否生成pojo
    private GlbCfgPoTmpModel glbCfgPoTmpModel = new GlbCfgPoTmpModel(); // po模板对象
    private boolean wapperType=true; // 是否使用包装类
    private String pojoPackageName=""; //pojo包名
    private String savePojoFilePath=""; // pojo保存位置
    
    private boolean genDaoCode = true; //是否生成dao代码
    private int xbatisType = 1; // 是ibatis还是MyIbatis
    private GlbCfgDaoTmpModel glbCfgDaoTmpModel= new GlbCfgDaoTmpModel(); // dao模板
    private String daoInterfacePackageName="";
    private String daoClassPackageName = "";//dao包名
    private String saveDaoFilePath=""; // 保存存dao文件的路径
    
    // 选中要生成的表的表名集合, *这个参数不会保存在参数xml文件中
    private List<TableRowBean> selTableNameLst = new ArrayList<TableRowBean>();

    public int getLanguage()
    {
        return language;
    }

    public void setLanguage(int language)
    {
        this.language = language;
    }

    public int getSkinStyle()
    {
        return skinStyle;
    }

    public void setSkinStyle(int skinStyle)
    {
        this.skinStyle = skinStyle;
    }

    public String getCharSetEncoding()
    {
        return charSetEncoding;
    }

    public void setCharSetEncoding(String charSetEncoding)
    {
        this.charSetEncoding = charSetEncoding;
    }

    public boolean isUseCame1Naming()
    {
        return useCame1Naming;
    }

    public void setUseCame1Naming(boolean useCame1Naming)
    {
        this.useCame1Naming = useCame1Naming;
    }

    public String getPoFltVarNm()
    {
        return poFltVarNm;
    }

    public void setPoFltVarNm(String poFltVarNm)
    {
        this.poFltVarNm = poFltVarNm;
    }

    public String getPoPkClassSuffix()
    {
        return poPkClassSuffix;
    }

    public void setPoPkClassSuffix(String poPkClassSuffix)
    {
        this.poPkClassSuffix = poPkClassSuffix;
    }

    public String getPoClassSuffix()
    {
        return poClassSuffix;
    }

    public void setPoClassSuffix(String poClassSuffix)
    {
        this.poClassSuffix = poClassSuffix;
    }

    public String getDaoFltVarNm()
    {
        return daoFltVarNm;
    }

    public void setDaoFltVarNm(String daoFltVarNm)
    {
        this.daoFltVarNm = daoFltVarNm;
    }

    public String getMappingFltVarNm()
    {
        return mappingFltVarNm;
    }

    public void setMappingFltVarNm(String mappingFltVarNm)
    {
        this.mappingFltVarNm = mappingFltVarNm;
    }

    public String getIbatisDaoInterfaceSuffix()
    {
        return ibatisDaoInterfaceSuffix;
    }

    public void setIbatisDaoInterfaceSuffix(String ibatisDaoInterfaceSuffix)
    {
        this.ibatisDaoInterfaceSuffix = ibatisDaoInterfaceSuffix;
    }

    public String getIbatisDaoClassSuffix()
    {
        return ibatisDaoClassSuffix;
    }

    public void setIbatisDaoClassSuffix(String ibatisDaoClassSuffix)
    {
        this.ibatisDaoClassSuffix = ibatisDaoClassSuffix;
    }

    public String getMybatisDaoInterfaceSuffix()
    {
        return mybatisDaoInterfaceSuffix;
    }

    public void setMybatisDaoInterfaceSuffix(String mybatisDaoInterfaceSuffix)
    {
        this.mybatisDaoInterfaceSuffix = mybatisDaoInterfaceSuffix;
    }

    public String getMybatisDaoClassSuffix()
    {
        return mybatisDaoClassSuffix;
    }

    public void setMybatisDaoClassSuffix(String mybatisDaoClassSuffix)
    {
        this.mybatisDaoClassSuffix = mybatisDaoClassSuffix;
    }

    public String getCommDaoInterfaceSuffix()
    {
        return commDaoInterfaceSuffix;
    }

    public void setCommDaoInterfaceSuffix(String commDaoInterfaceSuffix)
    {
        this.commDaoInterfaceSuffix = commDaoInterfaceSuffix;
    }

    public String getCommDaoClassSuffix()
    {
        return commDaoClassSuffix;
    }

    public void setCommDaoClassSuffix(String commDaoClassSuffix)
    {
        this.commDaoClassSuffix = commDaoClassSuffix;
    }

    public int getDataSourceType()
    {
        return dataSourceType;
    }

    public void setDataSourceType(int dataSourceType)
    {
        this.dataSourceType = dataSourceType;
    }

    public GlbCfgDbModel getDbModel()
    {
        return dbModel;
    }

    public void setDbModel(GlbCfgDbModel dbModel)
    {
        this.dbModel = dbModel;
    }

    public String getDbDriver()
    {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver)
    {
        this.dbDriver = dbDriver;
    }

    public String getDbUrl()
    {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl)
    {
        this.dbUrl = dbUrl;
    }

    public String getDbUserName()
    {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName)
    {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword()
    {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword)
    {
        this.dbPassword = dbPassword;
    }

    public String getExFilePath()
    {
        return exFilePath;
    }

    public void setExFilePath(String exFilePath)
    {
        this.exFilePath = exFilePath;
    }

    public GlbCfgDbModel getExDbModel()
    {
        return exDbModel;
    }

    public void setExDbModel(GlbCfgDbModel exDbModel)
    {
        this.exDbModel = exDbModel;
    }

    public String getCdnCbxItems()
    {
        return cdnCbxItems;
    }

    public void setCdnCbxItems(String cdnCbxItems)
    {
        this.cdnCbxItems = cdnCbxItems;
    }

    public String getQueryTableCondtion()
    {
        return queryTableCondtion;
    }

    public void setQueryTableCondtion(String queryTableCondtion)
    {
        this.queryTableCondtion = queryTableCondtion;
    }

    public boolean isSameSaveFilePath()
    {
        return sameSaveFilePath;
    }

    public void setSameSaveFilePath(boolean sameSaveFilePath)
    {
        this.sameSaveFilePath = sameSaveFilePath;
    }

    public boolean isGenPojoCode()
    {
        return genPojoCode;
    }

    public void setGenPojoCode(boolean genPojoCode)
    {
        this.genPojoCode = genPojoCode;
    }

    public GlbCfgPoTmpModel getGlbCfgPoTmpModel()
    {
        return glbCfgPoTmpModel;
    }

    public void setGlbCfgPoTmpModel(GlbCfgPoTmpModel glbCfgPoTmpModel)
    {
        this.glbCfgPoTmpModel = glbCfgPoTmpModel;
    }

    public boolean isWapperType()
    {
        return wapperType;
    }

    public void setWapperType(boolean wapperType)
    {
        this.wapperType = wapperType;
    }

    public String getPojoPackageName()
    {
        return pojoPackageName;
    }

    public void setPojoPackageName(String pojoPackageName)
    {
        this.pojoPackageName = pojoPackageName;
    }

    public String getSavePojoFilePath()
    {
        return savePojoFilePath;
    }

    public void setSavePojoFilePath(String savePojoFilePath)
    {
        this.savePojoFilePath = savePojoFilePath;
    }

    public boolean isGenDaoCode()
    {
        return genDaoCode;
    }

    public void setGenDaoCode(boolean genDaoCode)
    {
        this.genDaoCode = genDaoCode;
    }

    public int getXbatisType()
    {
        return xbatisType;
    }

    public void setXbatisType(int xbatisType)
    {
        this.xbatisType = xbatisType;
    }

    public GlbCfgDaoTmpModel getGlbCfgDaoTmpModel()
    {
        return glbCfgDaoTmpModel;
    }

    public void setGlbCfgDaoTmpModel(GlbCfgDaoTmpModel glbCfgDaoTmpModel)
    {
        this.glbCfgDaoTmpModel = glbCfgDaoTmpModel;
    }

    public String getDaoInterfacePackageName()
    {
        return daoInterfacePackageName;
    }

    public void setDaoInterfacePackageName(String daoInterfacePackageName)
    {
        this.daoInterfacePackageName = daoInterfacePackageName;
    }

    public String getDaoClassPackageName()
    {
        return daoClassPackageName;
    }

    public void setDaoClassPackageName(String daoClassPackageName)
    {
        this.daoClassPackageName = daoClassPackageName;
    }

    public String getSaveDaoFilePath()
    {
        return saveDaoFilePath;
    }

    public void setSaveDaoFilePath(String saveDaoFilePath)
    {
        this.saveDaoFilePath = saveDaoFilePath;
    }

    public List<TableRowBean> getSelTableNameLst()
    {
        return selTableNameLst;
    }

    public void setSelTableNameLst(List<TableRowBean> selTableNameLst)
    {
        this.selTableNameLst = selTableNameLst;
    }

    
}
