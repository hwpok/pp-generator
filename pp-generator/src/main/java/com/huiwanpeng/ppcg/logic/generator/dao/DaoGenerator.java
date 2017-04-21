package com.huiwanpeng.ppcg.logic.generator.dao;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.flt.model.DaoMode;
import com.huiwanpeng.ppcg.logic.flt.model.DaoPkModel;
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

public class DaoGenerator
{
    /**
     * 传入包名,表名, 是否使用java包装类, 生成POJO类文件
     * @param tableModel
     * @param pkg
     * @return返回命令空间
     */
    public static String gen(DaoGenPara para){
        DaoMode dosMode = DaoGenerator.getDaoMode(para.getTableModel(),para.getDaoInterfacePackage(), para.getDaoClassPackage(), para.getDaoInterfaceSuffix(), para.getDaoClassSuffix(), para.getPojoType(), para.isUseCame1Naming());
        
        String nameSpace = "";
        // 生成DAO接口文件
        if(StrUtil.isNotEmpty(para.getDaoInterfaceFltFilePath())){
        	nameSpace = genDaoInterface(para, dosMode);
        }
        
        // 生成DAO实现类文件
        if(StrUtil.isNotEmpty(para.getDaoClassFltFilePath())){
        	nameSpace = genDaoClass(para, dosMode);
        }
        
        /** 这里的namespance在生成xml里会用到 */
        return nameSpace;
    }
    
    /**
     * 生成DAO接口
     * @param tableModel
     * @param pkg
     * @return返回命令空间
     */
    public static String genDaoInterface(DaoGenPara para, DaoMode dosMode){
        String fltDirector = FilePathUtil.getBasePath() + DirecUtil.getFilePathNoFileName(para.getDaoInterfaceFltFilePath()); // 只得到文件名
        String fltFileName = DirecUtil.getFileName(para.getDaoInterfaceFltFilePath());
        
        String resStr = generateJavaDaoStr(fltDirector, fltFileName, para.getVariableName(), dosMode);
        
        StringBuilder fileName = new StringBuilder();
        fileName.append(DirecUtil.getSuffixAndType(dosMode.getDaoInterfaceShort(), ".java"));
        
        String filePath = FileUtil.getFilePath(para.getResFilePath(), fileName.toString());
        
        // 将POJO字符串写入到文件中
        FileUtil.writeFile(filePath.toString(), para.getResFileEncoding(), resStr);
        return dosMode.getDaoInterface(); // 接口
    }
    
    /**
     * 生成DAO实现类
     * @param tableModel
     * @param pkg
     * @return返回命令空间
     */
    public static String genDaoClass(DaoGenPara para, DaoMode dosMode){
        String fltDirector = FilePathUtil.getBasePath() + DirecUtil.getFilePathNoFileName(para.getDaoClassFltFilePath()); // 只得到文件名
        String fltFileName = DirecUtil.getFileName(para.getDaoClassFltFilePath());
        
        String resStr = generateJavaDaoStr(fltDirector, fltFileName, para.getVariableName(), dosMode);
        
        StringBuilder fileName = new StringBuilder();
        fileName.append(DirecUtil.getSuffixAndType(dosMode.getDaoClassShort(), ".java"));
        
        String filePath = FileUtil.getFilePath(para.getResFilePath(), fileName.toString());
        
        // 将POJO字符串写入到文件中
        FileUtil.writeFile(filePath.toString(), para.getResFileEncoding(), resStr);
        return dosMode.getDaoClass();
    }
    
    /**
     * 构建 DosJavaMode4MyBatis
     * @param tableModel
     * @param pkg
     * @return
     */
    private static DaoMode getDaoMode(TableModel tableModel, String daoInterfacePackage, String daoClassPackage, String daoInterfaceSuffix, String daoClassSuffix, String pojoType, boolean useCame1Naming){
        DaoMode daoMode = new DaoMode();
        daoMode.setPoClass(pojoType);// 设置pojo类名
        daoMode.setPoClassShort(StrTool.getClassShort(pojoType)); //设置pojo类名,不包含前面包名
        daoMode.setCreateTime(new Date()); // 设置创建时间
        daoMode.setDbType(tableModel.getDbType()); // 数据库类型
        daoMode.setDbName(tableModel.getDbName()); // 设置数据库名称
        daoMode.setTableName(tableModel.getTableName()); // 设置表名
        daoMode.setTableComment(StrUtil.trim2null(tableModel.getTableComment())); // 设置注释
        daoMode.setImportClassSet(new HashSet<String>()); // 引入的类型
        daoMode.setDaoPkModelLst(new ArrayList<DaoPkModel>()); // 主键列表
        daoMode.getImportClassSet().add(pojoType); // 设置import的类
        
        // 生成DAO接口的相关信息
        if(StrUtil.isNotEmpty(daoInterfacePackage)){
            daoMode.setDaoInterfacePackageName(daoInterfacePackage); // 设置包前缀
            daoMode.setDaoInterfaceShort(StrTool.toCamelUpper1((tableModel.getTableName()), useCame1Naming) + DirecUtil.getSuffix(daoInterfaceSuffix)); // 设置类名= 表名驼峰命名 + 后缀
            daoMode.setDaoInterface(StrTool.getClassFull(daoMode.getDaoInterfacePackageName(), daoMode.getDaoInterfaceShort())); // interface包全路径
        }
        
        // 生成DAO接口实现类的相关信息
        if(StrUtil.isNotEmpty(daoClassPackage)){
            daoMode.setDaoClassPackageName(daoClassPackage); // 设置包前缀
            daoMode.setDaoClassShort(StrTool.toCamelUpper1((tableModel.getTableName()), useCame1Naming) + DirecUtil.getSuffix(daoClassSuffix)); // 设置类名= 表名驼峰命名 + 后缀
            daoMode.setDaoClass(StrTool.getClassFull(daoMode.getDaoClassPackageName(), daoMode.getDaoClassShort())); // interface包全路径
        }
        
        // 以下做两件事, 1. 构造主键列表, 2, 构件主键引入类
        DaoPkModel dosPkModel = null;
        for(ColumnModel columnModel : tableModel.getColumnModelLst()){
            if(columnModel.isPrimaryKey()){
                dosPkModel = new DaoPkModel();
                dosPkModel.setProperty(StrTool.toCamelLower1(columnModel.getColumnName(), useCame1Naming)); // 属性名
                dosPkModel.setJavaType(columnModel.getJavaType()); // java类型
                dosPkModel.setJavaTypeShort(StrTool.getClassShort(columnModel.getJavaType())); //java类型短型
                dosPkModel.setColumnComment(StrUtil.trim2null(columnModel.getColumnComment()));
                if(columnModel.getJavaType() != null && columnModel.getJavaType().indexOf(".")>0){
                    daoMode.getImportClassSet().add(columnModel.getJavaType()); // 设置import的类
                }
                daoMode.getDaoPkModelLst().add(dosPkModel); // 加入主键列表
            }
        }
        
        return daoMode;
    }
    

    
    /**
     * 通过freemark模板, 生成pojo
     * @param fltDirector模板文件所在的目录
     * @param fltFileName模板文件名
     * @param variableName模板文件变量名
     * @param dosMode模型
     * @return返回模板生成的字符串
     */
    private static String generateJavaDaoStr(String fltDirector, String fltFileName, String variableName, DaoMode daoMode)
    {
        String resStr = "";
        try
        {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(new File(fltDirector));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(variableName, daoMode);
            Template template = cfg.getTemplate(fltFileName);
            
            Writer out = new StringWriter();
            // template.process(map, new OutputStreamWriter(System.out));
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
