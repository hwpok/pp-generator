package com.huiwanpeng.ppcg.logic.config.dbcfg.model;

/**
 * 数据库列类型
 * @version 1.0
 */
public class ColumnMappingModel implements Cloneable
{
    /** 数据库字段类型 */
    private String columnType="";
    /** jdbc数据类型 */
    private String jdbcType="";
    /** java数据类型 */
    private String javaType="";
    /** java包装类型 */
    private String javaTypeWrapped="";
    /** 处理类型的Hanlder */
    private String typeHandler="";
    /** 全局默认映射类型 */
    private boolean globalDefault = false;
    /** 类型默认映射类型 */
    private boolean typeDefault = false;
    /** 小数精度最小 */
    private int numericPrecisionMin=0;
    /** 小数精度最大 */
    private int numericPrecisionMax=0;
    /** 小数标度最小 */
    private int numericScaleMax=0;
    /** 小数标度最大 */
    private int numericScaleMin=0;
    
    /**
     * 构造方法
     */
    public ColumnMappingModel(){}
    
    @Override
    public Object clone(){
        Object obj = null;
        try{
            obj = super.clone();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }

    public String getColumnType()
    {
        return columnType;
    }

    public void setColumnType(String columnType)
    {
        this.columnType = columnType;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    public String getJavaType()
    {
        return javaType;
    }

    public void setJavaType(String javaType)
    {
        this.javaType = javaType;
    }

    public String getJavaTypeWrapped()
    {
        return javaTypeWrapped;
    }

    public void setJavaTypeWrapped(String javaTypeWrapped)
    {
        this.javaTypeWrapped = javaTypeWrapped;
    }

    public String getTypeHandler()
    {
        return typeHandler;
    }

    public void setTypeHandler(String typeHandler)
    {
        this.typeHandler = typeHandler;
    }

    public boolean isGlobalDefault()
    {
        return globalDefault;
    }

    public void setGlobalDefault(boolean globalDefault)
    {
        this.globalDefault = globalDefault;
    }

    public boolean isTypeDefault()
    {
        return typeDefault;
    }

    public void setTypeDefault(boolean typeDefault)
    {
        this.typeDefault = typeDefault;
    }

    public int getNumericPrecisionMin()
    {
        return numericPrecisionMin;
    }

    public void setNumericPrecisionMin(int numericPrecisionMin)
    {
        this.numericPrecisionMin = numericPrecisionMin;
    }

    public int getNumericPrecisionMax()
    {
        return numericPrecisionMax;
    }

    public void setNumericPrecisionMax(int numericPrecisionMax)
    {
        this.numericPrecisionMax = numericPrecisionMax;
    }

    public int getNumericScaleMax()
    {
        return numericScaleMax;
    }

    public void setNumericScaleMax(int numericScaleMax)
    {
        this.numericScaleMax = numericScaleMax;
    }

    public int getNumericScaleMin()
    {
        return numericScaleMin;
    }

    public void setNumericScaleMin(int numericScaleMin)
    {
        this.numericScaleMin = numericScaleMin;
    }

}
