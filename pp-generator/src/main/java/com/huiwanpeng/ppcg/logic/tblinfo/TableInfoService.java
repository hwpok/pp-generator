package com.huiwanpeng.ppcg.logic.tblinfo;

import java.util.List;

import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;

/**
 * 得到表信息服务, 所有的数据必须实现该服务
 * 
 * @version 1.0
 */
public interface TableInfoService
{
    /**
     * 得到表信息,返回单个表,可选择是否初始化列模型
     * 
     * @param dbCfgBean数据库配置
     * @param fullTableName数据库表全名
     * @param initColumnModel是否初始化TableModel
     * @param useWrapJavaType初始化列时是否使用包装类,initColumnModel为true时生效
     * @return
     */
    public TableModel getTableInfo(String fullTableName, boolean initColumnModel, boolean useWrapJavaType);
    
    /**
     * 得到表信息,返回多个表,可选择是否初始化列模型
     * 
     * @param dbCfgBean数据库配置
     * @param fullTableName数据库表部份名
     * @param initColumnModel是否初始化TableModel
     * @param useWrapJavaType初始化列时是否使用包装类,initColumnModel为true时生效
     * @return
     */
    public List<TableModel> getTableInfoLst(String partTableName, boolean initColumnModel, boolean useWrapJavaType);
}
