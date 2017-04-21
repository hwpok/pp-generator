package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.util.StrTool;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * 数据模型的列信息
 * @version 1.0  
 */
public class TableColumnModelGetter
{
    /**
     * 得到表的列的起始行和截止行
     * 
     * @param sheet
     * @return
     */
    private static int[] getTableColumnBeginRowIndexEndRowIndex(Sheet sheet, String tableName)
    {
        // 定义表的起始行, 和截止行
        int[] beginRowIndexEndRowIndex = new int[] { -1, -1 };
        
        // 定义sheet的行数, 定义row的列数
        int rowCount = sheet.getLastRowNum(), cellCount = 0;
        Row row = null;
        
        String tempStr = null;
        for (int i = 0; i < rowCount; i++)
        {
            row = sheet.getRow(i);
            if (row == null)
            {
                continue;
            }
            
            // 得到单元格的个数
            cellCount = row.getLastCellNum();
            if (cellCount > 3)
            {
                // 如果第一行是以*开头, 那它的下一个单元格就为tableName, 再下一个就是表注释
                tempStr = CellValueUtil.getCellValue(row.getCell(0));
                if (null != tempStr && "*".equals(tempStr.trim()))
                {
                    // 得到表名
                    tempStr = CellValueUtil.getCellValue(row.getCell(1));
                    // 如果表名相等, 列信息的起始行为i+1
                    if (tempStr.equalsIgnoreCase(tableName))
                    {
                        beginRowIndexEndRowIndex[0] = i + 1;
                        continue;
                    }
                    // 如果起始列已经设置, 下次出现*就是本表的行截止位置
                    else if (-1 != beginRowIndexEndRowIndex[0])
                    {
                        beginRowIndexEndRowIndex[1] = i;
                        break;
                    }
                }
            }
        }
        // 如果起始不为-1, 结束为-1, 说明是最后一个表, 那么直接赋总行数
        if(-1 != beginRowIndexEndRowIndex[0] && -1 == beginRowIndexEndRowIndex[1]){
            beginRowIndexEndRowIndex[1] = rowCount;
        }
        return beginRowIndexEndRowIndex;
    }
    
    /**
     * 得到表的列的起始行和截止行
     * 
     * @param sheet
     * @return
     */
    public static List<ColumnModel> getTableColumnModelLst(Sheet sheet, String tableName)
    {
        // 列模型集合
        List<ColumnModel> columnModelLst = new ArrayList<ColumnModel>();
        
        // 定义表的起始行, 和截止行
        int[] beginRowIndexEndRowIndex = getTableColumnBeginRowIndexEndRowIndex(sheet, tableName);
        if(-1 == beginRowIndexEndRowIndex[0] || -1 == beginRowIndexEndRowIndex[1]){
            return columnModelLst;
        }
        
        // 定义row的列数
        int cellCount = 0;
        Row row = null;
        ColumnModel columnModel = null;
        String tempStr = null;
        for (int i = beginRowIndexEndRowIndex[0]; i <= beginRowIndexEndRowIndex[1]; i++)
        {
            row = sheet.getRow(i);
            if (row == null)
            {
                continue;
            }
            
            // 得到单元格的个数
            cellCount = row.getLastCellNum();
            if (cellCount >= 9)
            {
                // 如果第一行是以#开头, 说明此行为列模型
                tempStr = CellValueUtil.getCellValue(row.getCell(0));
                if (null != tempStr && "#".equals(tempStr.trim()))
                {
                    columnModel = new ColumnModel();
                    
                    // 列注释
                    tempStr = CellValueUtil.getCellValue(row.getCell(1));
                    columnModel.setColumnComment(tempStr);
                    
                    // 字段名称,必输
                    tempStr = CellValueUtil.getCellValue(row.getCell(2));
                    validateNotEmpty(tempStr, tableName, i, 2);
                    columnModel.setColumnName(tempStr);
                    
                    // 字段类型, 必输
                    tempStr = CellValueUtil.getCellValue(row.getCell(3));
                    validateNotEmpty(tempStr, tableName, i, 3);
                    columnModel.setDataType(StrTool.toUpper(tempStr));
                    
                    // 数据长度, 要么为空, 要么为数字
                    tempStr = CellValueUtil.getCellValue(row.getCell(4));
                    validateEmptyOrNum(tempStr, tableName, i, 4);
                    columnModel.setDataLength(StrUtil.str2int(tempStr));
                    
                    // 数据精度, 要么为空, 要么为数字
                    tempStr = CellValueUtil.getCellValue(row.getCell(5));
                    validateEmptyOrNum(tempStr, tableName, i, 5);
                    columnModel.setNumericPrecision(StrUtil.str2int(tempStr));
                    
                    // 数据标度, 要么为空, 要么为数字
                    tempStr = CellValueUtil.getCellValue(row.getCell(6));
                    validateEmptyOrNum(tempStr, tableName, i, 6);
                    columnModel.setNumericScale(StrUtil.str2int(tempStr));
                    
                    // 数据主键字符, 如 PRI
                    tempStr = CellValueUtil.getCellValue(row.getCell(7));
                    columnModel.setPrimaryKeyDBStr(StrTool.toUpper(tempStr));
                    
                    // 是否可以为null,如YES
                    tempStr = CellValueUtil.getCellValue(row.getCell(8));
                    columnModel.setNullableDBStr(StrTool.toUpper(tempStr));
                    
                    // 添加到集合中
                    columnModelLst.add(columnModel);
                }
            }
        }
       
        return columnModelLst;
    }
    
    /**
     * 验证是否为空
     * @param str
     * @param tableName
     * @param rowNum
     */
    private static void validateNotEmpty(String str, String tableName, int rowNum, int cellNum){
        if(StrUtil.isEmpty(str)){
            String errorInfo = "in excel," + (rowNum + 1) + "row, " + (cellNum + 1) + " cell can not be empty, table name is " + tableName;
            throw new ComRuntimeException("B1", errorInfo);
        }
    }
    
    /**
     * 验证要么为空, 要么为数字
     * @param str
     * @param tableName
     * @param rowNum
     */
    private static void validateEmptyOrNum(String str, String tableName, int rowNum, int cellNum){
        // 如果为空,通过验证
        if(StrUtil.isEmpty(str)){
            return;
        }
        
        // 如果不为空, 且不是数字, 那么抛出异常
        if(!str.matches("^[1-9][0-9]*$")){
            String errorInfo = "in excel," + (rowNum + 1) + " row, " + (cellNum + 1) + " cell must be empty or number, table name is " + tableName;
            errorInfo += ", it's value is " + str;
            throw new ComRuntimeException("B1", errorInfo);
        }
    }
    
}
