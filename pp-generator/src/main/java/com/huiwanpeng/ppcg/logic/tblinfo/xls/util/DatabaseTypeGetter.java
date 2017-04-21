package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class DatabaseTypeGetter
{
    /**
     * 得到数据库的类型
     * @param sheet
     * @return
     */
    public static String getDatabaseType(Sheet sheet)
    {
        String dataBaseType = ""; // 数据库类型
        
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
            if (cellCount > 2)
            {
                // 如果第一行是以@开头, 那它的下一个单元格就为databaseType
                tempStr = CellValueUtil.getCellValue(row.getCell(0));
                if (null != tempStr && "@".equals(tempStr.trim()))
                {
                    dataBaseType = CellValueUtil.getCellValue(row.getCell(1));
                    break;
                }
            }
        }
        return dataBaseType;
    }
}
