package com.huiwanpeng.ppcg.logic.generator.dao;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.flt.model.MappingColumnModel;
import com.huiwanpeng.ppcg.logic.flt.model.MappingModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.util.DirecUtil;
import com.huiwanpeng.ppcg.util.FilePathUtil;
import com.huiwanpeng.ppcg.util.FileUtil;
import com.huiwanpeng.ppcg.util.SQLUtil;
import com.huiwanpeng.ppcg.util.StrTool;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成pojo
 */
public class MappingGenerator
{
    /**
     * 传入包名,表名, 是否使用java包装类, 生成POJO类文件
     * 
     * @param tableModel
     * @param columnModelLst
     * @param namespaceO
     * @param type
     * @return
     */
    public static void gen(MappingGenPara para)
    {
        String fltDirector = FilePathUtil.getBasePath() + DirecUtil.getFilePathNoFileName(para.getFltFilePath()); // 只得到文件名
        String fltFileName = DirecUtil.getFileName(para.getFltFilePath());
        
        // 生成flt文件需要的Model
        MappingModel mappingModel = getDosXmlModel(para.getTableModel(), para.getNameSpace(), para.getPojoType(), para.isUseCame1Naming(), para.getXbatisType());
        // 生成mapping文件字符串
        String resStr = generateMappingStr(fltDirector, fltFileName, para.getVariableName(), mappingModel);
        System.out.println(resStr);
        
        // 构造生成文件的路径
        StringBuilder fileName = new StringBuilder();
        fileName.append(StrTool.toCamelUpper1(para.getTableModel().getTableName(), para.isUseCame1Naming()));
        fileName.append(DirecUtil.getSuffixAndType(para.getResultFileNameSuffix(), ".xml"));
        String filePath = FileUtil.getFilePath(para.getResFilePath(), fileName.toString());
        
        // 将POJO字符串写入到文件中
        FileUtil.writeFile(filePath.toString(), para.getResFileEncoding(), resStr);
    }
    
    /**
     * 构建DosXmlModel模型
     * 
     * @param tableModel
     * @param columnModelLst
     * @param namespace
     * @param type
     * @return
     */
    private static MappingModel getDosXmlModel(TableModel tableModel, String namespace, String pojoType, boolean useCame1Naming, int xbatisType)
    {
        // 开始设置DosXml模型
        MappingModel mappingModel = new MappingModel();
        mappingModel.setDbType(tableModel.getDbType()); // 数据库类型
        mappingModel.setDbName(tableModel.getDbName()); // 设置数据库名称
        mappingModel.setStartPageSql(StrUtil.trim2null(SQLUtil.getPageSQL(tableModel.getStartPageSql(), xbatisType)));
        mappingModel.setEndPageSql(StrUtil.trim2null(SQLUtil.getPageSQL(tableModel.getEndPageSql(), xbatisType)));
        mappingModel.setTableName(tableModel.getTableName().toUpperCase());
        mappingModel.setTableComment(StrUtil.trim2null(tableModel.getTableComment()));
        mappingModel.setNamespace(namespace); // 命名空间
        mappingModel.setPoClass(pojoType); // pojo类型
        mappingModel.setPoClassShort(StrTool.getClassShort(pojoType));//类名不包含包路径
        mappingModel.setMappingColumnModelLst(new ArrayList<MappingColumnModel>());
        
        // 设置字段类型
        for (ColumnModel columnModel : tableModel.getColumnModelLst())
        {
            MappingColumnModel mappingFieldModel = new MappingColumnModel();
            mappingFieldModel.setProperty(StrTool.toCamelLower1(columnModel.getColumnName(), useCame1Naming)); // 属性名
            mappingFieldModel.setJavaType(columnModel.getJavaType()); // 设置属性的java类型
            mappingFieldModel.setColumn(columnModel.getColumnName()); // 设置列名
            mappingFieldModel.setJdbcType(columnModel.getJdbcType()); // 设置jdbc类型
            mappingFieldModel.setTypeHandler(StrUtil.trim2null(columnModel.getTypeHandler())); //设置类型的处理器
            mappingFieldModel.setPrimaryKey(columnModel.isPrimaryKey()); // 是否主键
            mappingFieldModel.setNullable(columnModel.isNullable()); // 是否可以为空
            mappingFieldModel.setColumnComment(StrUtil.trim2null(columnModel.getColumnComment())); // 设置属性注释
            
            mappingModel.getMappingColumnModelLst().add(mappingFieldModel);
        }
        return mappingModel;
    }
    
    /**
     * 通过freemark模板, 生成pojo
     * 
     * @param dosXmlModel
     * @return
     */
    private static String generateMappingStr(String fltDirector, String fltFileName, String variableName, MappingModel mappingModel)
    {
        String resStr = "";
        try
        {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(fltDirector));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(variableName, mappingModel);
            Template template = cfg.getTemplate(fltFileName);
            Writer out = new StringWriter();
            template.process(map, out);
            resStr = out.toString();
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A5", "generate by  file " + fltDirector + fltFileName + " error", ex);
        }
        return resStr;
    }
}
