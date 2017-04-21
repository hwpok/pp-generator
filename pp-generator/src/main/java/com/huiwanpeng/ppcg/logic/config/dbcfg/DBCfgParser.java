package com.huiwanpeng.ppcg.logic.config.dbcfg;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.huiwanpeng.ppcg.logic.config.dbcfg.model.ColumnMappingModel;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.DBCfgModel;
import com.huiwanpeng.ppcg.util.FilePathUtil;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * 适配器
 * 
 * @version 1.0
 */
public class DBCfgParser
{
    /**
     * 根据数据库类型得到数据库配置
     * @param dbName数据库名
     * @return
     */
    public static DBCfgModel getDBCfgModelNew(String configFilePath)
    {
        String filePath = FilePathUtil.getBasePath() + configFilePath;
        DBCfgModel dbCfgModel = parseDBCfgModel(filePath);
        dbCfgModel = convertDBCfgModel(dbCfgModel);
        return dbCfgModel;
    }
    
    /**
     * 解析数据库配置
     * 
     * @param filePath数据库配置文件路径
     * @return
     */
    @SuppressWarnings("unchecked")
    private  static DBCfgModel parseDBCfgModel(String filePath)
    {
        DBCfgModel cbCfgModel = new DBCfgModel();
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
                return cbCfgModel;
            }

            
            Element databaseElement = doc.getRootElement().element("database");
            String tempStr = "";
            
            // 数据库描述信息
            tempStr = databaseElement.elementText("remark");
            cbCfgModel.setDiscription(StrUtil.trim2empty(tempStr));
            
            // 数据库类型
            tempStr = databaseElement.elementText("dbType");
            checkTagEmpty(tempStr, "dbType", filePath);
            cbCfgModel.setDbType(StrUtil.trim2empty(tempStr));
            
            // 数据库驱动
            tempStr = databaseElement.elementText("driver");
            checkTagEmpty(tempStr, "driver", filePath);
            cbCfgModel.setDriver(StrUtil.trim2empty(tempStr));
            
            // 数据库连接样例
            tempStr = databaseElement.elementText("connectUrlExample");
            checkTagEmpty(tempStr, "connectUrlExample", filePath);
            cbCfgModel.setConnectUrlExample(StrUtil.trim2empty(tempStr));
            
            
            // 查找表SQL
            tempStr = databaseElement.element("sqls").element("selectTableSql").elementText("mainSql");
            checkTagEmpty(tempStr, "mainSql", filePath);
            cbCfgModel.setSelectTableMainSql(StrUtil.trim2empty(tempStr));
            
            
            tempStr = databaseElement.element("sqls").element("selectTableSql").elementText("conditionEqual");
            checkTagEmpty(tempStr, "conditionEqual", filePath);
            cbCfgModel.setSelectTableConditionEqualSql(tempStr);
            
            
            tempStr = databaseElement.element("sqls").element("selectTableSql").elementText("conditionLike");
            checkTagEmpty(tempStr, "conditionLike", filePath);
            cbCfgModel.setSelectTableConditionLikeSql(tempStr);
            
            
            // 查找列信息SQL
            tempStr = databaseElement.element("sqls").elementText("selectColumnSql");
            checkTagEmpty(tempStr, "selectColumnSql", filePath);
            cbCfgModel.setSelectColumnSql(StrUtil.trim2empty(tempStr));
            
            
            // 分页信息SQL
            tempStr = databaseElement.element("sqls").element("pageSql").elementText("startPageSql");
            cbCfgModel.setStartPageSql(tempStr);
            tempStr = databaseElement.element("sqls").element("pageSql").elementText("endPageSql");
            cbCfgModel.setEndPageSql(tempStr);
            
            
            // 是否主键
            tempStr = databaseElement.element("columnValueMapping").elementText("primaryKeyToBoolean");
            checkTagEmpty(tempStr, "primaryKeyToBoolean", filePath);
            cbCfgModel.setPrimaryKeyToBoolean(StrUtil.trim2empty(tempStr));
            
            
            // 是否空值
            tempStr = databaseElement.element("columnValueMapping").elementText("nullableToBoolean");
            checkTagEmpty(tempStr, "nullableToBoolean", filePath);
            cbCfgModel.setNullableToBoolean(StrUtil.trim2empty(tempStr));
            
            
            List<ColumnMappingModel> columnMappingModelLst = new ArrayList<ColumnMappingModel>();
            Element columnTypeMappingElement = databaseElement.element("columnTypeMapping");
            Iterator<Element> it = columnTypeMappingElement.elementIterator();
            
            Element tempElement = null;
            ColumnMappingModel model = null;
            while (it.hasNext())
            {
                tempElement = it.next();
                
                // 组装对象
                model = new ColumnMappingModel();
                
                model.setColumnType(StrUtil.trim2empty(tempElement.attributeValue("name")).toUpperCase()); // 数据类型
                checkTagPropertyEmpty(model.getColumnType(), "columnType", "name", filePath);
                checkColunmType(model.getColumnType(), filePath); // 验证不能以开始或结尾
                
                model.setJdbcType(StrUtil.trim2empty(tempElement.elementTextTrim("jdbcType")).toUpperCase()); // jdbc类型
                checkTagEmpty(tempStr, "jdbcType", filePath);
                
                model.setJavaType(StrUtil.trim2empty(tempElement.elementTextTrim("javaType"))); // java类型
                checkTagEmpty(model.getJdbcType(), "javaType", filePath);
                
                model.setJavaTypeWrapped(StrUtil.trim2empty(tempElement.elementTextTrim("javaTypeWrapped"))); // 包装类型
                model.setTypeHandler(StrUtil.trim2empty(tempElement.elementTextTrim("typeHandler"))); // 类型处理器
                model.setTypeDefault("TRUE".equals(StrUtil.trim2empty(tempElement.attributeValue("typeDefault")).toUpperCase()));//类型默认
                model.setGlobalDefault("TRUE".equals(StrUtil.trim2empty(tempElement.attributeValue("globalDefault")).toUpperCase()));//全局默认
                model.setNumericPrecisionMin(StrUtil.str2int(tempElement.attributeValue("numericPrecisionMin"))); // 小数长度
                model.setNumericPrecisionMax(StrUtil.str2int(tempElement.attributeValue("numericPrecisionMax"))); // 小数长度
                model.setNumericScaleMin(StrUtil.str2int(tempElement.attributeValue("numericScaleMin"))); // 小数标度
                model.setNumericScaleMax(StrUtil.str2int(tempElement.attributeValue("numericScaleMax"))); // 小数标度
                
                // 对包装类型进行处理
                if ("".equals(model.getJavaTypeWrapped()))
                {
                    model.setJavaTypeWrapped(model.getJavaType());
                }
                
                columnMappingModelLst.add(model);
            }
            cbCfgModel.setColumnMappingModelLst(columnMappingModelLst);
        }
        catch(ComRuntimeException cex){
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A1", "parse xml file " + filePath + " error", ex);
        }
        return cbCfgModel;
    }
    
    /**
     * 转换映射, 如果colunmType为|分隔, 那么分隔的每一个作为一个coumnType, 第一个元素为globalDefault或typeDefault
     * @param dbCfgModel
     * @return
     */
    private static DBCfgModel convertDBCfgModel(DBCfgModel dbCfgModel){
        List<ColumnMappingModel> columnMappingModelLst = new ArrayList<ColumnMappingModel>();
        
        String[] columnTypes = null;
        ColumnMappingModel columnMappingModelTemp = null;
        for(ColumnMappingModel columnMappingModel : dbCfgModel.getColumnMappingModelLst()){
            // 以|分隔类型
            columnTypes = columnMappingModel.getColumnType().split("[|]");
            
            for(int i=0;i<columnTypes.length;i++){
                // 克隆一个对象
                columnMappingModelTemp  = (ColumnMappingModel)columnMappingModel.clone();
                // 把克隆对象的columnType设置为分割后的字符串
                columnMappingModelTemp.setColumnType(StrUtil.trim2empty(columnTypes[i]));
                
                if(0 != i){
                    // 如保留第一个元素的globalDefault和typeDefault
                    columnMappingModelTemp.setGlobalDefault(false);
                    columnMappingModelTemp.setTypeDefault(false);
                }
                
                // 添加到集合
                columnMappingModelLst.add(columnMappingModelTemp);
            }
        }
        // 设置
        dbCfgModel.setColumnMappingModelLst(columnMappingModelLst);
        return dbCfgModel;
    }
    
    
    
    /**
     * 检查xml配置文件的标签是否为空
     * @param str值
     * @param tagName标签名
     * @param filePath文件路径
     */
    private static void checkTagEmpty(String str, String tagName, String filePath){
        if(null == str || 0 == str.trim().length()){
            throw new ComRuntimeException("A1", "the xml file " + filePath + ", <"+tagName+"> tag cannot be empty.");
        }
    }
    
    private static void checkColunmType(String columnType, String filePath){
        if(columnType.startsWith("|") || columnType.endsWith("|")){
            throw new ComRuntimeException("A1", "the xml file " + filePath + ", " + columnType + " can't start widht '|' or end with '|'.");
        }
    }
    
    /**
     * 检查xml配置文件的标签的属性是否为空
     * @param str值
     * @param tagName标签名
     * @param propertyName属性名
     * @param filePath配置文件路径
     */
    private static void checkTagPropertyEmpty(String str, String tagName, String propertyName,  String filePath){
        if(null == str || 0 == str.trim().length()){
            throw new ComRuntimeException("A1", "the xml file " + filePath + ", <"+tagName+"> tag's property \"" + propertyName + "\" cannot be empty.");
        }
    }
    
}
