package com.huiwanpeng.ppcg.logic.config.dbcfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiwanpeng.ppcg.logic.config.dbcfg.model.ColumnMappingModel;
import com.huiwanpeng.ppcg.logic.config.dbcfg.model.DBCfgModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * 本类主要提供对映射字段进行设值
 * 
 * @version 1.0
 */
public class ColumnModelSetter
{
    
    /**
     * 把列模型集合, 分割成以列类型为key值的Map
     * 
     * @param columnMappingModelLst列映射模型集合
     * @return
     */
    public static Map<String, List<ColumnMappingModel>> mappingType2subLst(List<ColumnMappingModel> columnMappingModelLst)
    {
        // 以列类型为Key值的Map
        Map<String, List<ColumnMappingModel>> subMap = new HashMap<String, List<ColumnMappingModel>>();
        
        String columnType = ""; // 列类型
        List<ColumnMappingModel> tmepLst = null; // 列映射类型集合
        for (ColumnMappingModel columnMappingModel : columnMappingModelLst)
        {
            columnType = columnMappingModel.getColumnType();
            // 如果列类型无值, 结束对本条的处理, 处理下一条
            if (null == columnType || columnType.trim().equals(""))
            {
                continue;
            }
            
            tmepLst = subMap.get(columnType);
            
            // 查看列对应的Map是否存在, 如果不存在,创建一个
            if (tmepLst == null)
            {
                tmepLst = new ArrayList<ColumnMappingModel>();
                subMap.put(columnType, tmepLst);
            }
            tmepLst.add(columnMappingModel);
        }
        return subMap;
    }
    
    /**
     * 对列进行映射处理
     * 
     * @param colunmTypeMap类型所对应的列映射Map
     * @param globalDefaultColumnMappingModel全局默认映射类型,当找不到可对应的映射时, 将使用globalDefaultColumnMappingModel
     * @param columnModel列模型
     * @param usingWrappedType是否使用包装类
     */
    public static void settingColumnModel(final Map<String, List<ColumnMappingModel>> colunmTypeMap, ColumnMappingModel globalDefault, ColumnModel columnModel, boolean usingWrappedType)
    {
        // 根据列模型里的dataType去找映射模型ColumnMappingModel集合
        List<ColumnMappingModel> typeMappingModelLst = colunmTypeMap.get(columnModel.getDataType());
        
        // 如果找不到对应的映射模型ColumnMappingModel集合
        if (null == typeMappingModelLst || 0 == typeMappingModelLst.size())
        {
            // 如果集合为空,创建一个集合
            if(null == typeMappingModelLst){
                typeMappingModelLst = new ArrayList<ColumnMappingModel>();
            }
            // 如果没有全局默认值, 那么初始化一个
            if(null == globalDefault){
                globalDefault = new ColumnMappingModel();
                globalDefault.setJdbcType("VARCHAR");
                globalDefault.setJavaType("java.lang.String");
                globalDefault.setNumericPrecisionMin(0);
                globalDefault.setNumericPrecisionMax(0);
                globalDefault.setNumericScaleMin(0);
                globalDefault.setNumericScaleMax(0);
            }
            // 每次处理需要把列的值重置成本列的类型, 这句代码不能放在if内
            globalDefault.setColumnType(columnModel.getDataType());
            // 把全局默认对象加入到集合中
            typeMappingModelLst.add(globalDefault);
        }
        
        
        // 如果列映射对象里只有一个元素, 无需要对小数精度和标度作任何处理, 直接用它对列模型赋值
        if (typeMappingModelLst.size() == 1)
        {
            ColumnMappingModel columnMappingModel = typeMappingModelLst.get(0);
            columnModel.setJdbcType(columnMappingModel.getJdbcType());
            columnModel.setJavaType(usingWrappedType ? columnMappingModel.getJavaTypeWrapped() : columnMappingModel.getJavaType());
            columnModel.setTypeHandler(columnMappingModel.getTypeHandler());
            return;
        }
        
        // 如果列映射元素有多个元素, 那么对小数精度和标度进行匹配处理
        if (typeMappingModelLst.size() > 1)
        {
            boolean matched = false; // 是否已配置过
            for (ColumnMappingModel columnMappingModel : typeMappingModelLst)
            {
                // 如果精度和标度在指定范围内, 则匹配成功
                if (columnModel.getNumericPrecision() >= columnMappingModel.getNumericPrecisionMin() && columnModel.getNumericPrecision() <= columnMappingModel.getNumericPrecisionMax() 
                        && columnModel.getNumericScale() >= columnMappingModel.getNumericScaleMin() && columnModel.getNumericScale() <= columnMappingModel.getNumericScaleMax())
                {
                    matched = true; // 设置成已处理成功
                    columnModel.setJdbcType(columnMappingModel.getJdbcType());
                    columnModel.setJavaType(usingWrappedType ? columnMappingModel.getJavaTypeWrapped() : columnMappingModel.getJavaType());
                    columnModel.setTypeHandler(columnMappingModel.getTypeHandler());
                    break;
                }
            }
            
            // 如果没有匹配成功, 使用typeDefault, 如果仍然没有成功, 那么使用全局globalDefault
            if (false == matched)
            {
                // 先找type的默认值, 如果找不到, 返回此类型集合中的第一个映射类型
                ColumnMappingModel columnMappingModel = getTypeDefaultColumnMappingModel(typeMappingModelLst);
                if(null == columnMappingModel){
                    columnMappingModel = typeMappingModelLst.get(0);
                }
                columnModel.setJdbcType(columnMappingModel.getJdbcType());
                columnModel.setJavaType(usingWrappedType ? columnMappingModel.getJavaTypeWrapped() : columnMappingModel.getJavaType());
                columnModel.setTypeHandler(columnMappingModel.getTypeHandler());
                return;
            }
        }
        
    }
    
    /**
     * 得到全局默认对象
     * @param columnMappingModelLst所有的映射对象
     * @return
     */
    public static ColumnMappingModel getGlobalDefaultColumnMappingModel(List<ColumnMappingModel> columnMappingModelLst){
        ColumnMappingModel globalDefault = null;
        if(null != columnMappingModelLst){
            for(ColumnMappingModel columnMappingModel : columnMappingModelLst){
                if(columnMappingModel.isGlobalDefault()){
                    globalDefault = columnMappingModel;
                    break;
                }
            }
        }
        return globalDefault;
    }
    
    /**
     * 得到类型默认对象
     * @param columnMappingModelLst已经列分类后的对象
     * @return
     */
    public static ColumnMappingModel getTypeDefaultColumnMappingModel(List<ColumnMappingModel> columnMappingModelLst){
        ColumnMappingModel typeDefault = null;
        if(null != columnMappingModelLst){
            for(ColumnMappingModel columnMappingModel : columnMappingModelLst){
                if(columnMappingModel.isTypeDefault()){
                    typeDefault = columnMappingModel;
                    break;
                }
            }
        }
        return typeDefault;
    }
    
    /**
     * 处理是否主键和是否可以为空
     * 
     * @param columnModel
     * @param dbCfgModel
     */
    public static void settingColumnModel(ColumnModel columnModel, DBCfgModel dbCfgModel)
    {
        // 根据是否是主键的字符串，转换通用是否是主键
        columnModel.setPrimaryKey(dbCfgModel.getPrimaryKeyToBoolean().equalsIgnoreCase(columnModel.getPrimaryKeyDBStr()));
        // 根据能否为空，转换通用是否为空
        columnModel.setNullable(dbCfgModel.getNullableToBoolean().equalsIgnoreCase(columnModel.getNullableDBStr()));
    }
    
    /**
     * 表是否是复合主键(主键数大于1)
     * @param tableModel
     */
    public static void settingMultiPrimaryKey(TableModel tableModel){
        
        if(null != tableModel && null != tableModel.getColumnModelLst() && 0 < tableModel.getColumnModelLst().size()){
            int primaryKeyCount = 0;
            
            // 循环计算主键的个数
            for(ColumnModel columnModel : tableModel.getColumnModelLst()){
                if(columnModel.isPrimaryKey()){
                    primaryKeyCount ++;
                }
            }
            
            // 如果一个主键都没有, 那么抛出异常
            if(0 == primaryKeyCount){
                throw new ComRuntimeException("C", "table " + tableModel.getTableName() + " has no primary key.");
            }
            
            tableModel.setMultiPrimaryKey(1 < primaryKeyCount);
        }
    }
}
