package com.huiwanpeng.ppcg.logic.tblinfo.xls;

import java.util.List;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.config.dbcfg.ColumnModelSetter;
import com.huiwanpeng.ppcg.logic.config.dbcfg.DBCfgParser;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.ColumnMappingModel;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.DBCfgModel;
import com.huiwanpeng.ppcg.logic.tblinfo.TableInfoService;
import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.logic.tblinfo.xls.util.ExcelParser;

/**
 * Excel表信息实现
 * 
 * @version 1.0
 */
public class XLSTableInfoServiceImpl implements TableInfoService
{
    /** 数据库名称 */
    private String dbName;
    
    /** excel文件路径 */
    private String excelFilePath;
    
    /** 适配器 */
    private DBCfgModel dbCfgModel;
    
    /**
     * 构造方法
     * 
     * @param excelFilePath
     */
    public XLSTableInfoServiceImpl(String excelFilePath, String dbName, String dbConfigFilePath)
    {
        this.dbName = dbName;
        this.excelFilePath = excelFilePath;
        this.dbCfgModel = DBCfgParser.getDBCfgModelNew(dbConfigFilePath);
    }
    
    @Override
    public TableModel getTableInfo(String fullTableName, boolean initColumnModel, boolean useWrapJavaType)
    {
        TableModel tableModel = null;
        List<TableModel> tableModelLst = ExcelParser.getTableModelLst(excelFilePath, fullTableName, false);
        if (tableModelLst.size() > 0)
        {
            // 得到集合中的第一个表
            tableModel = tableModelLst.get(0);
            tableModel.setDbType(dbCfgModel.getDbType()); // 设置表所对应的数据库类型
            tableModel.setStartPageSql(dbCfgModel.getStartPageSql());
            tableModel.setEndPageSql(dbCfgModel.getEndPageSql());
            tableModel.setDbName(this.dbName); // 设置表所对应的数据库名称
            if (initColumnModel)
            {
                // 初始化表模型
                intiTableModel(tableModel, useWrapJavaType);
            }
        }
        return tableModel;
    }
    
    @Override
    public List<TableModel> getTableInfoLst(String partTableName, boolean initColumnModel, boolean useWrapJavaType)
    {
        List<TableModel> tableModelLst = ExcelParser.getTableModelLst(excelFilePath, partTableName, true);
        for (TableModel tableModel : tableModelLst)
        {
            tableModel.setDbType(dbCfgModel.getDbType()); // 设置表所对应的数据库类型
            tableModel.setStartPageSql(dbCfgModel.getStartPageSql());
            tableModel.setEndPageSql(dbCfgModel.getEndPageSql());
            tableModel.setDbName(this.dbName); // 设置表所对应的数据库名称
            if (initColumnModel)
            {
                // 初始化表模型
                intiTableModel(tableModel, useWrapJavaType);
            }
        }
        return tableModelLst;
    }
    
    
    private void intiTableModel(TableModel tableModel, boolean useWrapJavaType)
    {
        // 得到表的列信息
        List<ColumnModel> columnModelLst = ExcelParser.getTableColumnModelLst(excelFilePath, tableModel.getTableName());
        tableModel.setColumnModelLst(columnModelLst);
        
        // 对表的列信息进行处理
        ColumnMappingModel globalDefault = ColumnModelSetter.getGlobalDefaultColumnMappingModel(dbCfgModel.getColumnMappingModelLst());
        Map<String, List<ColumnMappingModel>> subLstMap = ColumnModelSetter.mappingType2subLst(dbCfgModel.getColumnMappingModelLst());
        for (ColumnModel columnModel : columnModelLst)
        {
            ColumnModelSetter.settingColumnModel(columnModel, dbCfgModel);
            ColumnModelSetter.settingColumnModel(subLstMap, globalDefault, columnModel, useWrapJavaType);
        }
        
        // 设置表是否是复合主键, 这一步应该放在所有的列信息被设置之后
        ColumnModelSetter.settingMultiPrimaryKey(tableModel);
    }
    
}
