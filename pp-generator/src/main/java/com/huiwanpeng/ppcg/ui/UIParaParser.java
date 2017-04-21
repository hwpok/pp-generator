package com.huiwanpeng.ppcg.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.util.DateUtil;
import com.huiwanpeng.ppcg.util.FilePathUtil;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * UI 参数转换器
 * 
 * @version 1.0
 */
public class UIParaParser
{
    public static UIParaBean getUIParaBean(String filePath)
    {
        String uiParafilePath = FilePathUtil.getBasePath() + filePath;
        return readXML(uiParafilePath);
    }
    
    public static void saveUIParaBean(String filePath, UIParaBean uiParaBean)
    {
        String uiParafilePath = FilePathUtil.getBasePath() + filePath;
        writeDoc(uiParafilePath, uiParaBean);
    }
    
    private static UIParaBean readXML(String filePath)
    {
        UIParaBean uiCfgBean = new UIParaBean();
        try
        {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(filePath));
            // 检查根元素
            Element rootElement = doc.getRootElement();
            if(!"huiWanpengRoot".equals(rootElement.getName())){
                throw new ComRuntimeException("A1", "the root tag of the xml file " + filePath + " is error");
            }
            
            // 如果是uiParameter解析器, 继续向下解析, 否则返回
            String parserName = StrUtil.trim2empty(rootElement.attributeValue("parserName"));
            if(!"uiParameterParser".equals(parserName)){
                return uiCfgBean;
            }
            
            
            
            Element uiParametersElement = doc.getRootElement().element("uiParameters");
            
            // 数据源开始 ---------------------------------------
            Element dataSourceElement = uiParametersElement.element("dataSource");
            
            // 数据库数据源
            Element ddsElement = dataSourceElement.element("databaseDataSource");
            uiCfgBean.setDataSourceType(str2int(ddsElement.elementText("dataSourceType"))); // 0:database1:excel
            
            GlbCfgDbModel glbCfgDbModel = new GlbCfgDbModel();
            glbCfgDbModel.setId(StrUtil.trim2empty(ddsElement.elementText("dbId")));
            uiCfgBean.setDbModel(glbCfgDbModel); // 数据库ID
            
            uiCfgBean.setDbDriver(StrUtil.trim2empty(ddsElement.elementText("dbDriver"))); //数据库驱动
            uiCfgBean.setDbUrl(StrUtil.trim2empty(ddsElement.elementText("dbUrl"))); // 数据库连接URL
            uiCfgBean.setDbUserName(StrUtil.trim2empty(ddsElement.elementText("dbUserName"))); // 数据库用户名
            uiCfgBean.setDbPassword(StrUtil.trim2empty(ddsElement.elementText("dbPassword"))); // 数据库密码
            
            // excel数据源
            Element excelElement = dataSourceElement.element("excelDataSource");
            uiCfgBean.setExFilePath(StrUtil.trim2empty(excelElement.elementText("exFilePath"))); // excel文件路径
            
            GlbCfgDbModel excelGlbCfgDbModel = new GlbCfgDbModel();
            excelGlbCfgDbModel.setId(StrUtil.trim2empty(ddsElement.elementText("exDbId")));
            uiCfgBean.setExDbModel(excelGlbCfgDbModel); // 数据库ID
            
            // 查询数据库表的条件-------------------------------
            Element queryElement = uiParametersElement.element("queryTable");
            uiCfgBean.setCdnCbxItems(StrUtil.trim2empty(queryElement.elementText("cdnCbxItems")));
            
            // 生成文件开始 ------------------------------------
            Element generateElement = uiParametersElement.element("generate");
            
            // po
            Element poElement = generateElement.element("pojo");
            uiCfgBean.setSameSaveFilePath(n2b(poElement.elementText("sameSaveFilePath"))); // pojo/mapping/dao三个文件的位置是否保持相同
            uiCfgBean.setGenPojoCode(n2b(poElement.elementText("genPojoCode"))); // 是否生成pojo
            uiCfgBean.setWapperType(n2b(poElement.elementText("wapperType"))); // 是否使用包装类
            
            GlbCfgPoTmpModel glbCfgPoTmpModel = new GlbCfgPoTmpModel();
            glbCfgPoTmpModel.setId(StrUtil.trim2empty(poElement.elementText("poTmpId")));
            uiCfgBean.setGlbCfgPoTmpModel(glbCfgPoTmpModel); // PO模板对象
            
            uiCfgBean.setPojoPackageName(StrUtil.trim2empty(poElement.elementText("pojoPackageName"))); // pojo包名
            uiCfgBean.setSavePojoFilePath(StrUtil.trim2empty(poElement.elementText("savePojoFilePath"))); // pojo保存位置
            
            
            // dao
            Element daoElement = generateElement.element("dao");
            uiCfgBean.setGenDaoCode(n2b(daoElement.elementText("genDaoCode"))); // 是否生成dao代码
            
            GlbCfgDaoTmpModel glbCfgDaoMappingTmpModel = new GlbCfgDaoTmpModel();
            glbCfgDaoMappingTmpModel.setId(StrUtil.trim2empty(daoElement.elementText("daoTmpId")));
            uiCfgBean.setGlbCfgDaoTmpModel(glbCfgDaoMappingTmpModel); // 设置DAO模板对象
   
            uiCfgBean.setDaoClassPackageName(StrUtil.trim2empty(daoElement.elementText("daoClassPackageName")));// dao包名
            uiCfgBean.setDaoInterfacePackageName(StrUtil.trim2empty(daoElement.elementText("daoInterfacePackageName")));
            uiCfgBean.setSaveDaoFilePath(StrUtil.trim2empty(daoElement.elementText("saveDaoFilePath"))); // 保存存dao文件的路径
            
            
            // 设置参数配置 -------------------------------------
            // 全局参数
            Element settingElement = uiParametersElement.element("setting");
            uiCfgBean.setLanguage(str2int(settingElement.elementText("language")));
            uiCfgBean.setSkinStyle(str2int(settingElement.elementText("skinStyle")));
            uiCfgBean.setCharSetEncoding(StrUtil.trim2empty(settingElement.elementText("charSetEncoding")));
            uiCfgBean.setUseCame1Naming(n2b(settingElement.elementText("useCame1Naming")));
            
            // po参数
            uiCfgBean.setPoFltVarNm(StrUtil.trim2empty(settingElement.elementText("poFltVarNm"))); // po flt变量名
            uiCfgBean.setPoPkClassSuffix(StrUtil.trim2empty(settingElement.elementText("poPkClassSuffix"))); // 复合主键的PK类后缀名
            uiCfgBean.setPoClassSuffix(StrUtil.trim2empty(settingElement.elementText("poClassSuffix"))); //po类后缀
            
            // dao参数
            uiCfgBean.setDaoFltVarNm(StrUtil.trim2empty(settingElement.elementText("daoFltVarNm"))); // dao ftl变量
            uiCfgBean.setMappingFltVarNm(StrUtil.trim2empty(settingElement.elementText("mappingFltVarNm"))); // dao mapping flt 变量
            uiCfgBean.setIbatisDaoInterfaceSuffix(StrUtil.trim2empty(settingElement.elementText("ibatisDaoInterfaceSuffix"))); // batis接口后缀
            uiCfgBean.setIbatisDaoClassSuffix(StrUtil.trim2empty(settingElement.elementText("ibatisDaoClassSuffix"))); // ibatis实现类后缀
            uiCfgBean.setMybatisDaoInterfaceSuffix(StrUtil.trim2empty(settingElement.elementText("mybatisDaoInterfaceSuffix"))); // mybatis接口后缀
            uiCfgBean.setMybatisDaoClassSuffix(StrUtil.trim2empty(settingElement.elementText("mybatisDaoClassSuffix"))); // mybatis实现类后缀
            uiCfgBean.setCommDaoInterfaceSuffix(StrUtil.trim2empty(settingElement.elementText("commDaoInterfaceSuffix"))); // 通用接口后缀
            uiCfgBean.setCommDaoClassSuffix(StrUtil.trim2empty(settingElement.elementText("commDaoClassSuffix"))); // 通用实现类后缀
        }
        catch (Exception ex)
        {
            ComRuntimeException comRuntimeException = new ComRuntimeException("A2", "parse the xml file " + filePath + " error", ex);
            throw comRuntimeException;
        }
        return uiCfgBean;
    }
    
    private static Document createDoc(UIParaBean uiCfgBean)
    {
        Document doc = DocumentHelper.createDocument();
        doc.addComment("This xml file created by program, the last fresh time is " + DateUtil.getTimeStr(new Date()));
        Element rootElement = doc.addElement("huiWanpengRoot"); //添加根元素
        rootElement.addAttribute("parserName", "uiParameterParser"); // 为根元素设置解析器名称
        
        Element uiParametersElement = rootElement.addElement("uiParameters");
        
        Element tempElement = null;
        // 数据源开始 ----------------------------------------------
        Element dataSourceElement = uiParametersElement.addElement("dataSource");
        
        // database数据源, 数据库源类型, 数据库类型, 数据库驱动, 数据URL, 用户名, 密码
        Element databaseDataSourceElement = dataSourceElement.addElement("databaseDataSource");
        tempElement = databaseDataSourceElement.addElement("dataSourceType");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDataSourceType() + ""));
        tempElement = databaseDataSourceElement.addElement("dbId");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDbModel().getId() + ""));
        tempElement = databaseDataSourceElement.addElement("dbDriver");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDbDriver()));
        tempElement = databaseDataSourceElement.addElement("dbUrl");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDbUrl()));
        tempElement = databaseDataSourceElement.addElement("dbUserName");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDbUserName()));
        tempElement = databaseDataSourceElement.addElement("dbPassword");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDbPassword()));
        
        // excel数据源, 数据库类型, 要解析的excel的路径
        Element excelDataSourceElement = dataSourceElement.addElement("excelDataSource");
        tempElement = excelDataSourceElement.addElement("exFilePath");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getExFilePath()));
        tempElement = excelDataSourceElement.addElement("exDbId");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getExDbModel().getId()));
        
       // 查询数据库表的条件-------------------------------------------
        Element queryElement = uiParametersElement.addElement("queryTable");
        Element conditionElement = queryElement.addElement("cdnCbxItems");
        conditionElement.setText(StrUtil.trim2empty(uiCfgBean.getCdnCbxItems()));
        
        // 生成面板 --------------------------------------------------
        Element generateElement = uiParametersElement.addElement("generate");
        Element pojoElement = generateElement.addElement("pojo");
        
        // pojo
        tempElement = pojoElement.addElement("genPojoCode");
        tempElement.setText(b2n(uiCfgBean.isGenPojoCode()));
        tempElement = pojoElement.addElement("poTmpId");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getGlbCfgPoTmpModel().getId()));
        tempElement = pojoElement.addElement("wapperType");
        tempElement.setText(b2n(uiCfgBean.isWapperType()));
        tempElement = pojoElement.addElement("pojoPackageName");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getPojoPackageName()));
        tempElement = pojoElement.addElement("savePojoFilePath");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getSavePojoFilePath()));
        tempElement = pojoElement.addElement("sameSaveFilePath");
        tempElement.setText(b2n(uiCfgBean.isSameSaveFilePath()));
        
        // dao
        Element daoElement = generateElement.addElement("dao");
        tempElement = daoElement.addElement("genDaoCode");
        tempElement.setText(b2n(uiCfgBean.isGenDaoCode()));
        tempElement = daoElement.addElement("daoTmpId");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getGlbCfgDaoTmpModel().getId()));
        tempElement = daoElement.addElement("daoInterfacePackageName");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDaoInterfacePackageName()));
        tempElement = daoElement.addElement("daoClassPackageName");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getDaoClassPackageName()));
        tempElement = daoElement.addElement("saveDaoFilePath");
        tempElement.setText(StrUtil.trim2empty(uiCfgBean.getSaveDaoFilePath()));
        
        // 参数设置 --------------------------------------------------------
        // 全局 参数
        Element globalCfgElement = uiParametersElement.addElement("setting");
        tempElement = globalCfgElement.addElement("language");
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getLanguage()+"")));
        tempElement = globalCfgElement.addElement("skinStyle");
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getSkinStyle()+"")));
        tempElement = globalCfgElement.addElement("charSetEncoding");
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getCharSetEncoding())));
        tempElement = globalCfgElement.addElement("useCame1Naming");
        tempElement.setText(b2n(uiCfgBean.isUseCame1Naming()));
        
        // po 参数
        tempElement = globalCfgElement.addElement("poFltVarNm"); // po flt变量名
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getPoFltVarNm())));
        tempElement = globalCfgElement.addElement("poPkClassSuffix"); // 复合主键的PK类后缀名
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getPoPkClassSuffix())));
        tempElement = globalCfgElement.addElement("poClassSuffix"); //po类后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getPoClassSuffix())));
        
        // dao参数
        tempElement = globalCfgElement.addElement("daoFltVarNm"); // dao ftl变量
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getDaoFltVarNm())));
        tempElement = globalCfgElement.addElement("mappingFltVarNm"); // dao mapping flt 变量
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getMappingFltVarNm())));
        tempElement = globalCfgElement.addElement("ibatisDaoInterfaceSuffix"); // ibatis接口后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getIbatisDaoInterfaceSuffix())));
        tempElement = globalCfgElement.addElement("ibatisDaoClassSuffix"); // // ibatis实现类后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getIbatisDaoClassSuffix())));
        tempElement = globalCfgElement.addElement("mybatisDaoInterfaceSuffix"); // mybatis接口后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getMybatisDaoInterfaceSuffix())));
        tempElement = globalCfgElement.addElement("mybatisDaoClassSuffix"); // mybatis实现类后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getMybatisDaoClassSuffix())));
        tempElement = globalCfgElement.addElement("commDaoInterfaceSuffix"); // 通用接口后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getCommDaoInterfaceSuffix())));
        tempElement = globalCfgElement.addElement("commDaoClassSuffix"); // 通用实现类后缀
        tempElement.setText(StrUtil.trim2empty(StrUtil.trim2empty(uiCfgBean.getCommDaoClassSuffix())));
        
        
        return doc;
    }
    
    /**
     * 把dom写入XML文件
     * 
     * @param filePath
     * @param doc
     */
    private static void writeDoc(String filePath, UIParaBean uiCfgBean)
    {
        XMLWriter writer = null;
        try
        {
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setLineSeparator(System.getProperty("line.separator"));
            outputFormat.setTrimText(true);
            outputFormat.setExpandEmptyElements(true);
            
            File file = new File(filePath);
            writer = new XMLWriter(new FileOutputStream(file), outputFormat);
            
            Document doc = createDoc(uiCfgBean);
            writer.write(doc);
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A2", "generater the xml file " + filePath + " error", ex);
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
                }
                catch (Exception e)
                {}
            }
        }
    }
    
    private static String b2n(boolean b)
    {
        return b ? "1" : "0";
    }
    
    private static boolean n2b(String s)
    {
        if (s == null || s.trim().equals("0"))
        {
            return false;
        }
        return true;
    }
    
    private static int str2int(String s)
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
