package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * 单元格辅助
 * @version 1.0  
 */
public class CellValueUtil
{
    public static String getCellValue(Cell cell)
    {
        String cellValue = "";
        if (cell != null)
        {
            // 单元格类型
            switch (cell.getCellType())
            {
                // 小数型
                case Cell.CELL_TYPE_NUMERIC:
                    // 日期型
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        cellValue = new DataFormatter().formatCellValue(cell);
                    }
                    // 数值型
                    else
                    {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = (value - intValue == 0) ? String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                // 字符串型
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                // 布尔型
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                // 空白
                case Cell.CELL_TYPE_BLANK:
                    cellValue = "";
                    break;
                // 错误的单元格
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "";
                    break;
                // 其它
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();
    }
    
}
