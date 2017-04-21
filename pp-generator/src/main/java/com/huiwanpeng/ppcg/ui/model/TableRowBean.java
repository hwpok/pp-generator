package com.huiwanpeng.ppcg.ui.model;

import java.util.Vector;

/**
 * 表行对象
 * 
 * @version 1.0
 */
public class TableRowBean
{
    private boolean checked; // 是否选中
    
    private String tableName = ""; // 表名
    
    private String tableComment = ""; // 表注释
    
    /**
     * 构造方法1
     * 
     * @param vector
     */
    public TableRowBean()
    {}
    
    /**
     * 构造方法2
     * 
     * @param vector
     */
    public TableRowBean(Vector<?> vector)
    {
        if (null != vector && 3 == vector.size())
        {
            checked = (Boolean) vector.get(0);
            tableName = (String) vector.get(1);
            tableComment = (String) vector.get(2);
        }
    }
    
    public String getObjInfo(){
        StringBuilder builder = new StringBuilder();
        builder.append("checked:").append(checked).append(", ");
        builder.append("tableName:").append(tableName).append(", ");
        builder.append("tableComment:").append(tableComment).append(", ");
        return builder.toString();
    }
    
    /**
     * 对象转Vector
     * 
     * @return
     */
    public Vector<?> toVector()
    {
        Vector<Object> vector = new Vector<Object>();
        vector.add(checked);
        vector.add(tableName);
        vector.add(tableComment);
        return vector;
    }
    
    public boolean isChecked()
    {
        return checked;
    }
    
    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }
    
    public String getTableName()
    {
        return tableName;
    }
    
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }
    
    public String getTableComment()
    {
        return tableComment;
    }
    
    public void setTableComment(String tableComment)
    {
        this.tableComment = tableComment;
    }
    
}
