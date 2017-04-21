package com.huiwanpeng.ppcg.logic.generator.po;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.flt.model.PoModel;
import com.huiwanpeng.ppcg.logic.flt.model.PoPropertyModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.util.DirecUtil;
import com.huiwanpeng.ppcg.util.FilePathUtil;
import com.huiwanpeng.ppcg.util.FileUtil;
import com.huiwanpeng.ppcg.util.StrTool;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 生成pojo
 */
public class PoGenerator
{
    public static String gen(PoGenPara para){
        boolean hasPkTemplateFile = StrUtil.isNotEmpty(para.getPoTmpModel().getPkTemplateFilePath()); // 是否存在PK模板
        PoModel poModel = getPoClassModel(para.getTableModel(), para.getPackageName(), para.getPkClassSuffix(), para.getPoClassSuffix(), para.isUseCame1Naming(), hasPkTemplateFile);
        
        genPO(para, poModel); // 生成PO类
        
        // 如果是复合主键, 那么生成PO的PK类, 而且存在PK模板
        if(poModel.isMultiPrimaryKey() && hasPkTemplateFile){
            genPK(para, poModel); // 生成PO的PK类
        }
        
        // 返回生成类名的全路径
        return poModel.getPoClass();
    }
 
    /**
     * 生成PO
     * @param para
     * @param poModel
     */
    public static void genPO(PoGenPara para, PoModel poModel){
        // 构建POJO模型, 模板文件所在文件夹
        String fltDirector = FilePathUtil.getBasePath() + DirecUtil.getFilePathNoFileName(para.getPoTmpModel().getPoTemplateFilePath()); // 只得到文件名
        String fltFileName = DirecUtil.getFileName(para.getPoTmpModel().getPoTemplateFilePath());
        
        //生成pojo字符
        String resStr = generatePoStr(fltDirector, fltFileName, para.getVariableName(), poModel); 
        
        // 构造生成文件的路径
        StringBuilder fileName = new StringBuilder();
        fileName.append(StrTool.toCamelUpper1(para.getTableModel().getTableName(), para.isUseCame1Naming()));
        fileName.append(DirecUtil.getSuffixAndType(para.getPoClassSuffix(), ".java"));
        String resfilePath = FileUtil.getFilePath(para.getResFilePath(), fileName.toString());
        
        // 将POJO字符串写入到文件中
        FileUtil.writeFile(resfilePath.toString(), para.getResFileEncoding(), resStr);
    }
    
    /**
     * 生成PO的PK
     * @param para
     * @param poModel
     */
    public static void genPK(PoGenPara para, PoModel poModel){
        // 构件模板和模板文件名
        String fltDirector = FilePathUtil.getBasePath() + DirecUtil.getFilePathNoFileName(para.getPoTmpModel().getPkTemplateFilePath());
        String fltFileName = DirecUtil.getFileName(para.getPoTmpModel().getPkTemplateFilePath());
        
        //生成po的pk字符
        String resStr = generatePoStr(fltDirector, fltFileName, para.getVariableName(), poModel); 
        
        // 构造生成文件的路径
        StringBuilder fileName = new StringBuilder();
        fileName.append(StrTool.toCamelUpper1(para.getTableModel().getTableName(), para.isUseCame1Naming()));
        fileName.append(DirecUtil.getSuffixAndType(para.getPkClassSuffix(), ".java"));
        String resfilePath = FileUtil.getFilePath(para.getResFilePath(), fileName.toString());
        
        // 将po的PK字符串写入到文件中
        FileUtil.writeFile(resfilePath.toString(), para.getResFileEncoding(), resStr);
    }
    
    /**
     * 构建POJO模型
     * @param tableModel#数据模型
     * @param tableModel#数据表模型
     * @param packageName#pojo包名
     * @return
     */
    private static PoModel getPoClassModel(TableModel tableModel, String packageName, String pkClassSuffix, String poClassSuffix, boolean useCame1Naming, boolean hasPkTemplateFile){
        
        PoModel poModel = new PoModel();
        poModel.setTableName(tableModel.getTableName());
        poModel.setTableComment(StrUtil.trim2null(tableModel.getTableComment()));
        poModel.setPackageName(packageName); //包名
        poModel.setImportClassSet(new HashSet<String>()); //import类型
        poModel.setImportPkClassSet(new HashSet<String>()); //import类型
        poModel.setImportCmClassSet(new HashSet<String>()); //import类型
        poModel.setPoClassShort(StrTool.toCamelUpper1(tableModel.getTableName(), useCame1Naming) + DirecUtil.getSuffix(poClassSuffix)); //类名
        poModel.setPoClass(StrTool.getClassFull(poModel.getPackageName(), poModel.getPoClassShort())); // 完整类路径
        poModel.setPoPropertyModelLst(new ArrayList<PoPropertyModel>()); // 主键属性的集合
        poModel.setPoPkPropertyModelLst(new ArrayList<PoPropertyModel>()); // 主键属性的集合
        poModel.setPoCmPropertyModelLst(new ArrayList<PoPropertyModel>()); // 非主键属性集合
        poModel.setDbType(tableModel.getDbType()); // 数据库类型
        poModel.setDbName(tableModel.getDbName()); // 数据库名称
        poModel.setTableName(tableModel.getTableName()); // 表名
        poModel.setMultiPrimaryKey(tableModel.isMultiPrimaryKey());
        
        // 如果是复合主键, 且模板存在的情况下, 设置PK类
        if(poModel.isMultiPrimaryKey() && hasPkTemplateFile){
            poModel.setPkClassShort(StrTool.toCamelUpper1(tableModel.getTableName(), useCame1Naming) + DirecUtil.getSuffix(pkClassSuffix)); //类名
            poModel.setPkClass(StrTool.getClassFull(poModel.getPackageName(), poModel.getPkClassShort())); // 完整类路径
        }
        
        // 设置字段类型
        for(ColumnModel columnModel : tableModel.getColumnModelLst()){
            
            PoPropertyModel poProperty = new PoPropertyModel();
            poProperty.setColumn(columnModel.getColumnName());
            poProperty.setColumnComment(StrUtil.trim2null(columnModel.getColumnComment()));
            poProperty.setDataType(columnModel.getDataType());
            poProperty.setJdbcType(columnModel.getJdbcType());
            poProperty.setJavaType(columnModel.getJavaType());
            poProperty.setDataLength(columnModel.getDataLength());
            poProperty.setNumericPrecision(columnModel.getNumericPrecision());
            poProperty.setNumericScale(columnModel.getNumericScale());
            poProperty.setPrimaryKey(columnModel.isPrimaryKey());
            poProperty.setNullable(columnModel.isNullable());
            poProperty.setProperty(StrTool.toCamelLower1(columnModel.getColumnName(), useCame1Naming));
            poProperty.setJavaTypeShort(StrTool.getClassShort(columnModel.getJavaType()));
            
            poModel.getPoPropertyModelLst().add(poProperty); // 添加所有的属性
            if(poProperty.isPrimaryKey()){
                poModel.getPoPkPropertyModelLst().add(poProperty); // 如果是主键, 那么加到主键的list中,
            } else{
                poModel.getPoCmPropertyModelLst().add(poProperty); // 如果不是主键, 那么加到非主键的list中
            }
            
            // 需要import的类
            if(columnModel.getJavaType() != null && columnModel.getJavaType().indexOf(".")>0){
                poModel.getImportClassSet().add(columnModel.getJavaType()); // 设置import的类
                if(columnModel.isPrimaryKey()){
                    poModel.getImportPkClassSet().add(columnModel.getJavaType()); // 主键java类型
                } else{
                    poModel.getImportCmClassSet().add(columnModel.getJavaType()); // 非主键需要import的
                }
            }
        }
        
        return poModel;
    }
    
    
    /**
     * 通过freemark模板, 生成pojo
     * @param fltDirector#flt模板文件所在文件夹路径
     * @param fltFileName#flt文件夹名
     * @param pojoClassModel#flt数据模型对象
     * @param variableName#flt数据模型变量名
     * @return
     */
    private static String generatePoStr(String fltDirector, String fltFileName, String variableName, PoModel pojoModel)
    {
        String resStr = "";
        try
        {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(fltDirector));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(variableName, pojoModel);
            Template template = cfg.getTemplate(fltFileName);
            
            Writer out = new StringWriter();
            template.process(map, out);
            resStr = out.toString();
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A10", "generate pojo file by flt file: " + fltFileName + " error.", ex);
        }
        return resStr;
    }
}
