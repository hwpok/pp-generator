package com.huiwanpeng.ppcg.logic.config.glbcfg;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDaoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgDbModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgPoTmpModel;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgUiModel;
import com.huiwanpeng.ppcg.util.DirecUtil;
import com.huiwanpeng.ppcg.util.FilePathUtil;
import com.huiwanpeng.ppcg.util.FileScanner;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

public class GlbCfgXmlParser
{
    /**
     * 解析配置文件
     * 
     * @return
     */
    public static GlbCfgModel parseConfigXml(){
        GlbCfgModel glbCfgModel = new GlbCfgModel();
        RepeatFilter repeatFilter = new RepeatFilter();
        
        // 解析全局配置文件 ---------------------------
        String globalConfigFilePath = getConfigFilePath();
        parseConfigXml(globalConfigFilePath, glbCfgModel, repeatFilter);
        
        // 解析全局文件 -------------------------------
        String configDirectPath = getConfigDirectPath(); // 得到config文件的路径
        List<String> xmlFilePaths = FileScanner.getXmlFiles(configDirectPath);
        for(String filePath : xmlFilePaths){
            try{
                // 解析局部模板文件
                boolean fileProcessed = parseConfigXml(filePath, glbCfgModel, repeatFilter);
                
                // 如果没有被上一个处理器处理, 那么继续处理
                if(!fileProcessed){
                    // 从数据库配置文件中解析出数据库配置
                    parseGlbCfgDbModelForDbCofigFile(filePath, glbCfgModel, repeatFilter);
                }
            }catch(Exception ex){
                Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            }
        }
        
        return glbCfgModel;
    }
    
    /**
     * 解析配置文件, 一个文件会可能被一个
     * 
     * @return 返回是否已经处理
     */
    public static boolean parseConfigXml(String filePath, GlbCfgModel glbCfgModel, RepeatFilter repeatFilter)
    {
        boolean fileProcessed = false;
        try
        {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(filePath));
            
            
            // 检查根元素
            Element rootElement = doc.getRootElement();
            if(!"huiWanpengRoot".equals(rootElement.getName())){
                throw new ComRuntimeException("A1", "the root tag of the xml file " + filePath + " is error");
            }
            
            // 如果是全局解析器和局部解析器, 继续向下解析, 否则返回
            String parserName = StrUtil.trim2empty(rootElement.attributeValue("parserName"));
            if(!("globalConfigParser".equals(parserName) || "partConfigParser".equals(parserName))){
                fileProcessed = false;
                return fileProcessed;
            }
            
            // 已被解析器处理
            fileProcessed = true;
            
            
            // 设置UI参数对象
            GlbCfgUiModel glbCfgUiModel = parseGlbCfgUiModel(rootElement, filePath); // 解析UI配置
            // 如果解析出为的不为null, glbCfgModel的glbCfgUiModel还未设值
            if(null != glbCfgUiModel){
                if(null == glbCfgModel.getGlbCfgUiModel()){
                    glbCfgModel.setGlbCfgUiModel(glbCfgUiModel);
                }
            }
            
            
            // 设置DB配置
            List<GlbCfgDbModel> glbCfgDbModelLst = parseGlbCfgDbModel(rootElement, filePath); // 数据库配置
            if(null != glbCfgDbModelLst){
                // 使用循环, 加入到列表之中
                for(GlbCfgDbModel glbCfgDbModel : glbCfgDbModelLst){
                    if(null == repeatFilter.glbCfgDbModelMap.get(glbCfgDbModel.getId())){
                        glbCfgModel.getGlbCfgDbModelLst().add(glbCfgDbModel);
                        repeatFilter.glbCfgDbModelMap.put(glbCfgDbModel.getId(), "e");
                    }
                }
            }
            
            
            // 设置PO模板
            List<GlbCfgPoTmpModel> glbCfgPoTmpModelLst = parseGlbCfgPoTmpModel(rootElement, filePath); // 解析PO配置
            if(null != glbCfgPoTmpModelLst){
                // 使用循环, 加入到列表之中
                for(GlbCfgPoTmpModel glbCfgPoTmpModel : glbCfgPoTmpModelLst){
                    if(null == repeatFilter.glbCfgDbModelMap.get(glbCfgPoTmpModel.getId())){
                        glbCfgModel.getGlbCfgPoTmpModelLst().add(glbCfgPoTmpModel);
                        repeatFilter.glbCfgPoTmpModelMap.put(glbCfgPoTmpModel.getId(), "e");
                    }
                }
            }
            
            
            List<GlbCfgDaoTmpModel> glbCfgDaoMappingTmpModelLst = parseGlbCfgDaoTmpModel(rootElement, filePath); // 解析DAO,MAPPING配置
            if(null != glbCfgDaoMappingTmpModelLst){
                // 使用循环, 加入到列表之中
                for(GlbCfgDaoTmpModel glbCfgDaoMappingTmpModel : glbCfgDaoMappingTmpModelLst){
                    if(null == repeatFilter.glbCfgDaoMappingTmpModelMap.get(glbCfgDaoMappingTmpModel.getId())){
                        glbCfgModel.getGlbCfgDaoTmpModelLst().add(glbCfgDaoMappingTmpModel);
                        repeatFilter.glbCfgDaoMappingTmpModelMap.put(glbCfgDaoMappingTmpModel.getId(), "e");
                    }
                }
            }
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A2", "parse the xml file " + filePath + " error", ex);
        }
        
        return fileProcessed;
    }
    
   
    /**
     * 解析UI配置
     * @param rootElement 根元素
     * @param glbCfgModel 配置对象
     */
    private static GlbCfgUiModel parseGlbCfgUiModel(Element rootElement, String filePath){
        // 如果uiConfig节点为空, 那么停止解析
        if(null == rootElement.element("uiConfig")){
            return null;
        }
        
        // 配置UI对象
        GlbCfgUiModel glbCfgUiModel = new GlbCfgUiModel();
        
        // 解析得到UI配置文件的路径
        String tempStr = rootElement.element("uiConfig").elementText("uiParamFilePath");
        tempStr = DirecUtil.rebuildFilePath(tempStr);  // 对路径进行重构, 以适应多操作系统
        glbCfgUiModel.setUiParamFilePath(tempStr);
        validateEmpty(tempStr, filePath, " uiConfig -> uiParamFilePath");
        return glbCfgUiModel;
    }
    
    /**
     * 从全局配置文件中解析出GlbCfgDbModel集合
     * @param rootElement 根元素
     * @param glbCfgModel 配置对象
     */
    @SuppressWarnings("unchecked")
    private static List<GlbCfgDbModel> parseGlbCfgDbModel(Element rootElement, String filePath){
        // 如果databasesConfig为空, 停止解析
        if(null == rootElement.element("databasesConfig")){
            return null;
        }
        
        List<GlbCfgDbModel> glbCfgDbModelLst = new ArrayList<GlbCfgDbModel>();
        
        Iterator<Element> it = rootElement.element("databasesConfig").elementIterator();
        GlbCfgDbModel model = null;
        while (it.hasNext())
        {
            model = parseGlbCfgDbModelSub(it.next(), filePath);
            glbCfgDbModelLst.add(model); // 加入到集合
        }
        return glbCfgDbModelLst;
    }
    
   
    /**
     * 只解析数据配置其中的一个元素
     * @param databaseElement
     * @param filePath
     * @return
     */
    private static  GlbCfgDbModel parseGlbCfgDbModelSub(Element databaseElement, String filePath){
        String tempStr = "";
        
        GlbCfgDbModel model = new GlbCfgDbModel();// 组装对象
        tempStr = StrUtil.trim2empty(databaseElement.attributeValue("enable")).toUpperCase();
        model.setEnable("TRUE".equalsIgnoreCase(tempStr)); // 是否可使用
        
        tempStr = StrUtil.trim2empty(databaseElement.attributeValue("id"));
        validateEmpty(tempStr, filePath, "databasesConfig -> id");
        model.setId(tempStr); //ID
        
        tempStr = StrUtil.trim2empty(databaseElement.elementText("name"));
        validateEmpty(tempStr, filePath, "databasesConfig -> name");
        model.setName(tempStr); // 数据库名称
        
        tempStr = StrUtil.trim2empty(databaseElement.elementText("configFilePath"));
        tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
        validateEmpty(tempStr, filePath, "databasesConfig -> configFilePath");
        model.setConfigFilePath(tempStr); // 数据库配置文件名
        
        tempStr = StrUtil.trim2empty(databaseElement.elementText("remark"));
        model.setRemark(tempStr); // 备注信息
        return model;
    }
    
    /**
     * 从数据库文件中解析出GlbCfgDbModel
     * @param filePath
     * @return
     */
    public static void parseGlbCfgDbModelForDbCofigFile(String filePath, GlbCfgModel glbCfgModel, RepeatFilter repeatFilter){
        try
        {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(filePath));
            
            
            // 检查根元素
            Element rootElement = doc.getRootElement();
            if(!"huiWanpengRoot".equals(rootElement.getName())){
                throw new ComRuntimeException("A1", "the root tag of the xml file " + filePath + " is error");
            }
            
            // 如果是全局解析器和局部解析器, 继续向下解析, 否则返回
            String parserName = StrUtil.trim2empty(rootElement.attributeValue("parserName"));
            if(!"dbConfigParser".equals(parserName)){
                return;
            }
            
            // 判断元素是否已经加入过, 如果没有加入, 那么加入
            Element databaseElement = rootElement.element("database");
            GlbCfgDbModel glbCfgDbModel = parseGlbCfgDbModelSub(databaseElement, filePath);
            if(null == repeatFilter.glbCfgDbModelMap.get(glbCfgDbModel.getId())){
                glbCfgModel.getGlbCfgDbModelLst().add(glbCfgDbModel);
            }
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A2", "parse the xml file " + filePath + " error", ex);
        }
    }
    
    /**
     * 解析PO模板配置
     * @param rootElement 根元素
     * @param glbCfgModel 配置对象
     */
    @SuppressWarnings("unchecked")
    private static List<GlbCfgPoTmpModel> parseGlbCfgPoTmpModel(Element rootElement, String filePath){
        // 如果templateConfig节点为空, 或templateConfig->poTemplates为空, 停止解析
        if(null == rootElement.element("poTemplates")){
            return null;
        }
        
        List<GlbCfgPoTmpModel> glbCfgPoTmpModelLst = new ArrayList<GlbCfgPoTmpModel>();
        
        Iterator<Element> it = rootElement.element("poTemplates").elementIterator();
        Element tempElement = null;
        GlbCfgPoTmpModel model = null;
        while (it.hasNext())
        {
            tempElement = it.next();
            String tempStr = "";
            
            model = new GlbCfgPoTmpModel();// 组装对象
            tempStr = StrUtil.trim2empty(tempElement.attributeValue("enable")).toUpperCase();
            model.setEnable("TRUE".equalsIgnoreCase(tempStr)); // 是否可使用
            
            tempStr = StrUtil.trim2empty(tempElement.attributeValue("id"));
            validateEmpty(tempStr, filePath, "poTemplates -> poTemplate -> id");
            model.setId(tempStr); //ID
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("name"));
            validateEmpty(tempStr, filePath, "poTemplates -> poTemplate -> name");
            model.setName(tempStr); // 数据库名称
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("pkTemplateFilePath"));
            tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
            model.setPkTemplateFilePath(tempStr); // po的pk模板
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("poTemplateFilePath"));
            tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
            validateEmpty(tempStr, filePath, "poTemplates -> poTemplate -> poTemplateFilePath");
            model.setPoTemplateFilePath(tempStr); // po模板
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("remark"));
            model.setRemark(tempStr); // 备注信息
            
            glbCfgPoTmpModelLst.add(model); // 加入到集合
        }
        
        return glbCfgPoTmpModelLst;
    }
    
    /**
     * 解析DAO,MAPPING模板配置
     * @param rootElement 根元素
     * @param glbCfgModel 配置对象
     */
    @SuppressWarnings("unchecked")
    private static List<GlbCfgDaoTmpModel> parseGlbCfgDaoTmpModel(Element rootElement, String filePath){
        // 如果templateConfig节点为空, 或templateConfig->daoMappingTemplates为空, 停止解析
        if(null == rootElement.element("daoMappingTemplates")){
            return null;
        }
        
        List<GlbCfgDaoTmpModel> glbCfgDaoMappingTmpModelLst = new ArrayList<GlbCfgDaoTmpModel>();
        
        Iterator<Element> it = rootElement.element("daoMappingTemplates").elementIterator();
        Element tempElement = null;
        GlbCfgDaoTmpModel model = null;
        while (it.hasNext())
        {
            tempElement = it.next();
            String tempStr = "";
            
            model = new GlbCfgDaoTmpModel();// 组装对象
            tempStr = StrUtil.trim2empty(tempElement.attributeValue("enable")).toUpperCase();
            model.setEnable("TRUE".equalsIgnoreCase(tempStr)); // 是否可使用
            
            tempStr = StrUtil.trim2empty(tempElement.attributeValue("id"));
            validateEmpty(tempStr, filePath, "daoMappingTemplates -> daoMappingTemplate -> id");
            model.setId(tempStr); //ID
            
            tempStr = StrUtil.trim2empty(tempElement.attributeValue("type"));
            validateEmpty(tempStr, filePath, "daoMappingTemplates -> daoMappingTemplate -> type");
            model.setType(tempStr); // ibatis/mybatis类型
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("name"));
            validateEmpty(tempStr, filePath, "daoMappingTemplates -> daoMappingTemplate -> name");
            model.setName(tempStr); // 数据库名称
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("daoInterfaceTemplateFilePath"));
            tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
            model.setDaoInterfaceTemplateFilePath(tempStr); // 配置文件路径
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("daoClassTemplateFilePath"));
            tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
            //validateEmpty(tempStr, filePath, "daoMappingTemplates -> daoMappingTemplate -> daoClassTemplateFilePath");
            model.setDaoClassTemplateFilePath(tempStr); // 配置文件路径
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("daoMappingTemplateFilePath"));
            tempStr = DirecUtil.rebuildFilePath(tempStr); // 对路径进行重构, 以适应多操作系统
            model.setDaoMappingTemplateFilePath(tempStr); // 配置文件路径
            
            tempStr = StrUtil.trim2empty(tempElement.elementText("remark"));
            model.setRemark(tempStr); // 备注信息
            
            glbCfgDaoMappingTmpModelLst.add(model); // 加入到集合
        }
        
        return glbCfgDaoMappingTmpModelLst;
    }
    
    /**
     * 验证是否为空
     * @param str待验证字符串
     * @param filePath文件路径
     * @param tagName标签名
     */
    private static void validateEmpty(String str, String filePath, String tagName){
        if (0 == str.length())
        {
            throw new ComRuntimeException("A2", "the xml file " + filePath + ", " + tagName +" tag cannot be empty.");
        }
    }
    
    /**
     * 得到配置文件的路径
     * 
     * @return
     */
    public static String getConfigFilePath()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getConfigDirectPath());
        builder.append(File.separator);
        builder.append("config.xml");
        return builder.toString();
    }
    public static String getConfigDirectPath(){
        StringBuilder builder = new StringBuilder();
        builder.append(FilePathUtil.getBasePath());
        builder.append(File.separator);
        builder.append("config");
        return builder.toString();
    }
}
