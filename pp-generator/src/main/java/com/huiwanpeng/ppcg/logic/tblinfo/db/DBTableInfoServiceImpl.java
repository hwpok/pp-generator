package com.huiwanpeng.ppcg.logic.tblinfo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.config.dbcfg.ColumnModelSetter;
import com.huiwanpeng.ppcg.logic.config.dbcfg.DBCfgParser;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.ColumnMappingModel;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.DBCfgModel;
import com.huiwanpeng.ppcg.logic.tblinfo.TableInfoService;
import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.DBCnctCfgBean;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.util.StrTool;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.Logger;

/**
 * MYSQL表信息
 * 
 * @version 1.0
 */
public class DBTableInfoServiceImpl implements TableInfoService
{
    /** 适配器 */
    private DBCfgModel dbCfgModel;
    
    /** 数据库连接配置bean */
    private DBCnctCfgBean dbCnctCfgBean;
    
    
    /**
     *构造方法
     * @param dbCnctCfgBean数据库连接对象
     */
    public DBTableInfoServiceImpl(DBCnctCfgBean dbCnctCfgBean)
    {
        this.dbCfgModel = DBCfgParser.getDBCfgModelNew(dbCnctCfgBean.getDbConfigFilePath());
        dbCnctCfgBean.setDriverName(this.dbCfgModel.getDriver()); // 把驱动设置到数据库连接对象之中
        this.dbCnctCfgBean = dbCnctCfgBean;
    }
    
    /**
     * 得到单个表信息
     */
    @Override
    public TableModel getTableInfo(String tableName, boolean initColumnModel, boolean useWrapJavaType)
    {
        List<TableModel> tableModelLst = getTableInfoLst(tableName, false, initColumnModel, useWrapJavaType);
        if (tableModelLst.size() != 0)
        {
            return tableModelLst.get(0);
        }
        return null;
    }
    
    /**
     * 得到多个表信息
     */
    @Override
    public List<TableModel> getTableInfoLst(String tableName, boolean initColumnModel, boolean useWrapJavaType)
    {
        return getTableInfoLst(tableName, true, initColumnModel, useWrapJavaType);
        
    }
    
    /**
     * 统一处理表注释
     */
    private List<TableModel> getTableInfoLst(String tableName, boolean like, boolean initColumnModel, boolean useWrapJavaType)
    {
        String sql = getQryTableInfoSql(tableName, like);
        Logger.i(sql); //打印日志
        List<TableModel> tableModelLst = this.getTableInfoLst(dbCnctCfgBean, sql, tableName);
        for (TableModel tableModel : tableModelLst)
        {
            tableModel.setDbType(dbCfgModel.getDbType()); // 设置数据库类型
            tableModel.setDbName(dbCnctCfgBean.getDbName());
            tableModel.setStartPageSql(dbCfgModel.getStartPageSql());
            tableModel.setEndPageSql(dbCfgModel.getEndPageSql());
            // 初始化表的列信息
            if (initColumnModel)
            {
                List<ColumnModel> columnModelLst = this.getColumnModelLst(tableModel.getTableName(), useWrapJavaType);
                tableModel.setColumnModelLst(columnModelLst);
                ColumnModelSetter.settingMultiPrimaryKey(tableModel); // 设置是否是复合主键, 这一步应该放在列信息被初始化之后
            }
        }
        return tableModelLst;
    }
    
    /**
     * 得到列信息
     */
    private List<ColumnModel> getColumnModelLst(String tableName, boolean useWrapJavaType)
    {
        String sql = getQryColumnInfoSql(tableName);
        Logger.i(sql); //打印日志
        List<ColumnModel> columnModelLst = this.getColumnModelLst(dbCnctCfgBean, sql, tableName);
        ColumnMappingModel globalDefault = ColumnModelSetter.getGlobalDefaultColumnMappingModel(dbCfgModel.getColumnMappingModelLst());
        Map<String, List<ColumnMappingModel>> subLstMap = ColumnModelSetter.mappingType2subLst(dbCfgModel.getColumnMappingModelLst());
        for (ColumnModel columnModel : columnModelLst)
        {
            ColumnModelSetter.settingColumnModel(columnModel, dbCfgModel);
            ColumnModelSetter.settingColumnModel(subLstMap, globalDefault,columnModel, useWrapJavaType);
        }
        return columnModelLst;
    }
    
    /**
     * 得到列sql
     */
    private String getQryColumnInfoSql(String tableName)
    {
        String selectColumnSql = dbCfgModel.getSelectColumnSql();
        return selectColumnSql.replace("?", tableName);
    }
    
    /**
     * 得到查表信息SQL
     */
    private String getQryTableInfoSql(String tableNameLike, boolean like)
    {
        String selectTableSql = dbCfgModel.getSelectTableMainSql();
        // 如果查询条件有值
        if(null != tableNameLike && tableNameLike.length()>0){
            String whereSql = like ? dbCfgModel.getSelectTableConditionLikeSql() : dbCfgModel.getSelectTableConditionEqualSql();
            whereSql = whereSql.replace("?", StrUtil.trim2empty(tableNameLike));
            return selectTableSql + " " + whereSql;
        }
        return selectTableSql;
    }
    
    /**
     * 得到table信息， sql类似为: SELECT 表名(String),表注释(String) FROM 表信息表 where
     * 表名(String)=?
     */
    private List<TableModel> getTableInfoLst(DBCnctCfgBean dbCnctCfgBean, String sql, String tableNameLike)
    {
        List<TableModel> tableModelLst = new ArrayList<TableModel>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = DBTool.getConnection(dbCnctCfgBean);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            TableModel tableModel;
            while (rs.next())
            {
                tableModel = new TableModel();
                tableModel.setTableName(StrUtil.trim2empty(rs.getString(1))); // 表名
                tableModel.setTableComment(StrUtil.trim2empty(rs.getString(2))); // 表注释
                tableModelLst.add(tableModel);
            }
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A4", "query " + tableNameLike + " table info error.", ex);
        }
        finally
        {
            DBTool.freeResource(conn, stmt, rs);
        }
        return tableModelLst;
    }
    
    /**
     * 得到table信息， sql为: SELECT
     * 列名(String),列注释(String),数据类型(String),精度(long),标度(long),是否主键(String),是否可以为空
     * (String) FROM 列信息表 WHERE 表名(String)=?
     */
    private List<ColumnModel> getColumnModelLst(DBCnctCfgBean dbCnctCfgBean, String sql, String tableName)
    {
        List<ColumnModel> columnModelLst = new ArrayList<ColumnModel>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = DBTool.getConnection(dbCnctCfgBean);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            ColumnModel columnModel = null;
            while (rs.next())
            {
                columnModel = new ColumnModel();
                columnModel.setColumnName(StrUtil.trim2empty(rs.getString(1))); // 列名
                columnModel.setColumnComment(StrUtil.trim2empty(rs.getString(2))); // 列注释
                columnModel.setDataType(StrTool.toUpper(rs.getString(3))); // 数据类型
                columnModel.setDataLength(rs.getInt(4));
                columnModel.setNumericPrecision(rs.getInt(5)); // 精度
                columnModel.setNumericScale(rs.getInt(6)); // 标度
                columnModel.setPrimaryKeyDBStr(StrTool.toUpper(rs.getString(7))); // 是否主键
                columnModel.setNullableDBStr(StrTool.toUpper(rs.getString(8))); // 是否可以为空
                
                columnModelLst.add(columnModel);
            }
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A4", "query table "+ tableName +" column info error.");
        }
        finally
        {
            DBTool.freeResource(conn, stmt, rs);
        }
        return columnModelLst;
    }
    
}
