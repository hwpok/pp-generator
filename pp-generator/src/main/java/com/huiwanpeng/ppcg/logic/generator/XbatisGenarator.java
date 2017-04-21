package com.huiwanpeng.ppcg.logic.generator;

import java.util.ArrayList;
import java.util.List;

import com.huiwanpeng.ppcg.logic.generator.dao.DaoGenPara;
import com.huiwanpeng.ppcg.logic.generator.dao.DaoGenerator;
import com.huiwanpeng.ppcg.logic.generator.dao.MappingGenPara;
import com.huiwanpeng.ppcg.logic.generator.dao.MappingGenerator;
import com.huiwanpeng.ppcg.logic.generator.po.PoGenPara;
import com.huiwanpeng.ppcg.logic.generator.po.PoGenerator;
import com.huiwanpeng.ppcg.logic.tblinfo.TableInfoService;
import com.huiwanpeng.ppcg.logic.tblinfo.db.DBTableInfoServiceImpl;
import com.huiwanpeng.ppcg.logic.tblinfo.db.DBTool;
import com.huiwanpeng.ppcg.logic.tblinfo.model.DBCnctCfgBean;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.logic.tblinfo.xls.XLSTableInfoServiceImpl;
import com.huiwanpeng.ppcg.ui.model.TableRowBean;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * xBatis代码生成器
 * 
 * @version 1.0
 */
public class XbatisGenarator
{
    /**
     * 是否能连接到数据库
     * 
     * @param uiPara
     */
    public static boolean canConnectDatabase(UIParaBean uiPara)
    {
        try
        {
            DBCnctCfgBean dbCnctCfgBean = getDBCnctCfgBean(uiPara);
            return DBTool.isCanConnectDB(dbCnctCfgBean);
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "connect database error", ex);
        }
    }
    
    /**
     * 检查Excel文件是否有效
     * 
     * @param uiPara
     */
    public static boolean checkExcelFile(String excelFilePath, String dbName, String dbConfigFileName)
    {
        try
        {
            // 解析Excel, 如果没有错误抛出, 说明是正确的Excel
            XLSTableInfoServiceImpl xlsTableInfoServiceImpl = new XLSTableInfoServiceImpl(excelFilePath, dbName, dbConfigFileName);
            xlsTableInfoServiceImpl.getTableInfoLst("", true, false);
            
            return true;
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "excel file is not correct", ex);
        }
    }
    
    /**
     * 查询数据库表
     * 
     * @param uiPara
     * @param condition
     * @return
     */
    public static List<TableRowBean> getTableRowBeanLst(UIParaBean uiPara, String condition)
    {
        List<TableRowBean> tableRowBeanLst = new ArrayList<TableRowBean>();
        try
        {
            TableInfoService tableInfoService = null;
            // 从数据库查
            if(0 == uiPara.getDataSourceType()){
                DBCnctCfgBean dbCnctCfgBean = getDBCnctCfgBean(uiPara);
                tableInfoService = new DBTableInfoServiceImpl(dbCnctCfgBean);
            }
            // 从Excel里查
            else{
                tableInfoService = new XLSTableInfoServiceImpl(uiPara.getExFilePath(), uiPara.getExDbModel().getName(), uiPara.getExDbModel().getConfigFilePath());
            }
            
            // 查询
            List<TableModel> TableModelLst = tableInfoService.getTableInfoLst(condition, false, false);
            for (TableModel tableModel : TableModelLst)
            {
                TableRowBean tableRowBean = new TableRowBean();
                tableRowBean.setChecked(true);
                tableRowBean.setTableName(tableModel.getTableName());
                tableRowBean.setTableComment(tableModel.getTableComment());
                tableRowBeanLst.add(tableRowBean);
            }
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "query tables error", ex);
        }
        return tableRowBeanLst;
    }
    
    public static void genXbatisCodes(UIParaBean uiPara)
    {
        try
        {
            genXbatisCode(uiPara);
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "generator pojo file error", ex);
        }
    }
    
    /**
     * 通过界面参数, 生成pojo, mapping, dao
     * 
     * @param uiPara
     */
    private static void genXbatisCode(UIParaBean uiPara)
    {
        // 如果没有要生成的表名, 退出程序
        if (null == uiPara.getSelTableNameLst())
        {
            return;
        }
        
        // 是需要生成任何代码
        if (!(uiPara.isGenPojoCode() || uiPara.isGenDaoCode()))
        {
            return;
        }
        
        DBCnctCfgBean dbCnctCfgBean = getDBCnctCfgBean(uiPara);
        
        TableInfoService tableInfoService = null;
        // 根据不同的种类生成不同的查询实现类
        if(0 == uiPara.getDataSourceType()){
            tableInfoService = new DBTableInfoServiceImpl(dbCnctCfgBean);
        }
        else{
            tableInfoService = new XLSTableInfoServiceImpl(uiPara.getExFilePath(), uiPara.getExDbModel().getName(), uiPara.getExDbModel().getConfigFilePath());
        }
        
        // 根据选中表, 循环生成结果文件
        TableModel tableModel = null;
        for (TableRowBean tableRowBean : uiPara.getSelTableNameLst())
        {
            tableModel = tableInfoService.getTableInfo(tableRowBean.getTableName(), true, uiPara.isWapperType());
            
            String pojoFullName = "";
            // 如果用户选择需要生成pojo
            if (uiPara.isGenPojoCode())
            {
                pojoFullName = genPojo(uiPara, tableModel);
            }
            
            
            String daoFullName = "";
            // 如果用户选择生成dao
            if (uiPara.isGenDaoCode())
            {
                daoFullName = genDao(uiPara, tableModel, pojoFullName);
                // 如果有Mapping的模板, 才生成mapping文件
                if(StrUtil.isNotEmpty(uiPara.getGlbCfgDaoTmpModel().getDaoMappingTemplateFilePath())){
                    genMappingXml(uiPara, tableModel, pojoFullName, daoFullName);
                }
            }
        }
    }
    
    /**
     * 根据界面参数得到数据连接参数对象
     * 
     * @param uiParaBean
     * @return
     */
    private static DBCnctCfgBean getDBCnctCfgBean(UIParaBean uiPara)
    {
        // 构造数据库连接信息
        DBCnctCfgBean dbCnctCfgBean = new DBCnctCfgBean();
        dbCnctCfgBean.setDbName(uiPara.getDbModel().getName()); // 数据库类型
        dbCnctCfgBean.setDbConfigFilePath(uiPara.getDbModel().getConfigFilePath()); // 设置配置文件路径
        dbCnctCfgBean.setDriverName(uiPara.getDbDriver()); // 数据库驱动
        dbCnctCfgBean.setConnectUrl(uiPara.getDbUrl()); // 服务URL
        dbCnctCfgBean.setUserName(uiPara.getDbUserName()); // 用户名
        dbCnctCfgBean.setPassword(uiPara.getDbPassword()); // 密码
        return dbCnctCfgBean;
    }
    
    /**
     * 生成pojo
     * 
     * @param config
     * @param uiPara
     */
    private static String genPojo(UIParaBean uiPara, TableModel tableModel)
    {
        String pojoTypeFull =  null;
        try
        {
            // 拼装生成参数
            PoGenPara para = new PoGenPara();
            para.setResFileEncoding(uiPara.getCharSetEncoding()); // 生成文件的编码
            para.setPackageName(uiPara.getPojoPackageName()); // 生成pojo的包名
            para.setResFilePath(uiPara.getSavePojoFilePath()); // 生成文件的路径必须以分隔符结束
            para.setPkClassSuffix(uiPara.getPoPkClassSuffix()); // po的pk类文件
            para.setPoClassSuffix(uiPara.getPoClassSuffix()); // po类文件后缀
            para.setPoTmpModel(uiPara.getGlbCfgPoTmpModel()); // 模板对象
            para.setVariableName(uiPara.getPoFltVarNm()); // flt文件变量名
            para.setTableModel(tableModel); // table模型
            para.setUseCame1Naming(uiPara.isUseCame1Naming()); // 是否使用驼峰命名法
            
            // 生成代码
            pojoTypeFull = PoGenerator.gen(para);
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "generator pojo file error", ex);
        }
        return pojoTypeFull;
    }
    
    /**
     * 生成DAO
     * 
     * @param config
     * @param uiPara
     */
    private static String genDao(UIParaBean uiPara, TableModel tableModel, String pojoFullName)
    {
        String daoTypeFull = null;
        try
        {
            String variableName = uiPara.getDaoFltVarNm(); // flt文件中的变量名
            String interfaceSuffix  = ""; // 接口后缀
            String classSuffix = ""; // 类后缀
            
            // batis 接口后缀, 实现类后缀
            if("ibatis".equalsIgnoreCase(uiPara.getGlbCfgDaoTmpModel().getType())){
                interfaceSuffix = uiPara.getIbatisDaoInterfaceSuffix();
                classSuffix = uiPara.getIbatisDaoClassSuffix();
            }
            // mybatis 接口后缀, 实现类后缀
            else if("mybatis".equalsIgnoreCase(uiPara.getGlbCfgDaoTmpModel().getType())){
                interfaceSuffix = uiPara.getMybatisDaoInterfaceSuffix();
                classSuffix = uiPara.getMybatisDaoClassSuffix();
            }
            // 通用 接口后缀, 实现类后缀
            else{
                interfaceSuffix = uiPara.getCommDaoInterfaceSuffix();
                classSuffix = uiPara.getCommDaoClassSuffix();
            }
            // 拼装生成参数
            DaoGenPara para = new DaoGenPara();
            para.setResFileEncoding(uiPara.getCharSetEncoding()); // 生成文件的编码
            para.setDaoInterfacePackage(uiPara.getDaoInterfacePackageName()); // dao接口包名
            para.setDaoClassPackage(uiPara.getDaoClassPackageName()); // dao实现类包名
            para.setPojoType(pojoFullName); // pojo类型, 包含包名
            para.setResFilePath(uiPara.getSaveDaoFilePath()); // 生成文件的路径
            para.setDaoInterfaceFltFilePath(uiPara.getGlbCfgDaoTmpModel().getDaoInterfaceTemplateFilePath()); //设置接口模板
            para.setDaoClassFltFilePath(uiPara.getGlbCfgDaoTmpModel().getDaoClassTemplateFilePath()); // 设置实现类模板
            para.setDaoInterfaceSuffix(interfaceSuffix); // 生成的接口文件的后缀名
            para.setDaoClassSuffix(classSuffix); // 生成的实现类文件的后缀名
            para.setVariableName(variableName); // flt文件变量名
            para.setTableModel(tableModel); // table模型
            para.setUseCame1Naming(uiPara.isUseCame1Naming()); // 是否使用驼峰命名法
            
            daoTypeFull = DaoGenerator.gen(para);  
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "generator pojo file error", ex);
        }
        return daoTypeFull;
    }
    
    /**
     * 生成Mapping
     * 
     * @param config
     * @param uiPara
     */
    private static void genMappingXml(UIParaBean uiPara, TableModel tableModel, String pojoFullName, String mappingNameSpance)
    {
        try
        {
            String variableName = uiPara.getMappingFltVarNm(); // flt文件中的变量名
            String fileNameSuffix = "";
            
            // ibatis文件后缀, 优先使用接口后缀作为mapping文件后缀, 如果为空, 那么使用class类后缀作为mapping文件后缀
            if("ibatis".equalsIgnoreCase(uiPara.getGlbCfgDaoTmpModel().getType())){
                fileNameSuffix =  uiPara.getIbatisDaoInterfaceSuffix();
                if(StrUtil.isEmpty(fileNameSuffix)){
                    fileNameSuffix =  uiPara.getIbatisDaoClassSuffix();
                }
            }
            // mybatis文件后缀, 优先使用接口后缀作为mapping文件后缀, 如果为空, 那么使用class类后缀作为mapping文件后缀
            else{
                fileNameSuffix =  uiPara.getMybatisDaoInterfaceSuffix();
                if(StrUtil.isEmpty(fileNameSuffix)){
                    fileNameSuffix =  uiPara.getMybatisDaoClassSuffix();
                }
            }
                
            // 拼装生成参数
            MappingGenPara para = new MappingGenPara();
            para.setXbatisType(uiPara.getXbatisType()); // 0:ibatis, 1:myBatis
            para.setResFileEncoding(uiPara.getCharSetEncoding()); // 生成文件的编码
            para.setNameSpace(mappingNameSpance); // 得到Mapping的命名空间
            para.setPojoType(pojoFullName); // pojo类型
            para.setResFilePath(uiPara.getSaveDaoFilePath()); // 生成文件的路径
            para.setResultFileNameSuffix(fileNameSuffix); // 生成文件的后缀名
            para.setFltFilePath(uiPara.getGlbCfgDaoTmpModel().getDaoMappingTemplateFilePath()); // flt文件名
            para.setVariableName(variableName); // flt文件变量名
            para.setTableModel(tableModel); // table模型 
            para.setUseCame1Naming(uiPara.isUseCame1Naming()); // 是否使用驼峰命名法
            
            // 生成代码
            MappingGenerator.gen(para);
        }
        catch (ComRuntimeException cex)
        {
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A9", "generator pojo file error", ex);
        }
    }
}
