package com.huiwanpeng.ppcg.logic.tblinfo.model;

/**
 * 数据库连接配置Bean
 * @version 1.0  
 */
public class DBCnctCfgBean
{
    private String dbName; // 数据库名称
    
    private String dbConfigFilePath; //数据库类型
    
    private String driverName; // 数据库驱动
    
    private String connectUrl="jdbc:mysql://localhost/hwptest"; // 服务URL
    
    private String userName="root"; // 用户名
    
    private String password="root"; // 密码
    
    public DBCnctCfgBean(){
    }
    

    public String getDbConfigFilePath()
    {
        return dbConfigFilePath;
    }

    public void setDbConfigFilePath(String dbConfigFilePath)
    {
        this.dbConfigFilePath = dbConfigFilePath;
    }

    public String getDriverName()
    {
        return driverName;
    }

    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public String getConnectUrl()
    {
        return connectUrl;
    }

    public void setConnectUrl(String connectUrl)
    {
        this.connectUrl = connectUrl;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDbName()
    {
        return dbName;
    }

    public void setDbName(String dbName)
    {
        this.dbName = dbName;
    }
    
}
