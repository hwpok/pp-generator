package com.huiwanpeng.ppcg.logic.tblinfo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.huiwanpeng.ppcg.logic.tblinfo.model.DBCnctCfgBean;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * 数据库操作工具
 * 
 * @version 1.0
 */
public class DBTool
{
    /**
     * 判断数据库是否可连接
     * @param dbCfgBean
     * @return
     */
    public static boolean isCanConnectDB(DBCnctCfgBean dbCnctCfgBean)
    {
        boolean canConnect = false;
        Connection conn = null;
        try
        {
            conn = getConnection(dbCnctCfgBean);
            canConnect = (conn != null) ? true : false;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A3", "can not get database connection.", ex);
        }
        finally
        {
            freeResource(conn, null, null);
        }
        return canConnect;
    }
    
    /**
     * 得到连接
     */
    public static Connection getConnection(DBCnctCfgBean dbCnctCfgBean)
    {
        Connection conn = null;
        try
        {
            Class.forName(dbCnctCfgBean.getDriverName());
            conn = DriverManager.getConnection(dbCnctCfgBean.getConnectUrl(), dbCnctCfgBean.getUserName(), dbCnctCfgBean.getPassword());
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("A3", "can not get database connection.", ex);
        }
        return conn;
    }
    
    /**
     * 释放资源
     */
    public static void freeResource(Connection conn, Statement stmt, ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
