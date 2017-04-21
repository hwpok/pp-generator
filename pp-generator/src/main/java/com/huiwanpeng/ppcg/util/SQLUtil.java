package com.huiwanpeng.ppcg.util;

/**
 * SQL处理
 * @version 1.0  
 */
public class SQLUtil
{
    /**
     * 得到正确的分页SQL
     * @param pageSql 分页SQL
     * @param xbatisType ibatis还是Mybatis
     * @return 正确的分页面SQL
     */
    public static String getPageSQL(String pageSql, int xbatisType){
        if(null != pageSql){
            if(0 == xbatisType){
                pageSql = pageSql.replaceAll("(?i)@offsetIndex@", "\\$offsetIndex\\$");
                pageSql = pageSql.replaceAll("(?i)@pageSize@", "\\$pageSize\\$");
            }
            else{
                pageSql = pageSql.replaceAll("(?i)@offsetIndex@", "\\${offsetIndex}");
                pageSql = pageSql.replaceAll("(?i)@pageSize@", "\\${pageSize}");
            }
            return pageSql;
        }
        return pageSql;
    }
}
