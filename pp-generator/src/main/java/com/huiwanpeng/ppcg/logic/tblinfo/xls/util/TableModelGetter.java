package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;

public class TableModelGetter
{
    /**
     * 得到数据库的类型
     * 
     * @param sheet
     * @return
     */
    public static List<TableModel> getTabeNameLst(Sheet sheet, String tableName, boolean like)
    {
        // 表模型集合
        List<TableModel> tableModelLst = new ArrayList<TableModel>();
        
        // 定义sheet的行数, 定义row的列数
        int rowCount = sheet.getLastRowNum(), cellCount = 0;
        Row row = null;
        TableModel tableModel = null;
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
                    // 表名
                    tempStr = CellValueUtil.getCellValue(row.getCell(1));
                    
                    // 如果传入的表名为空
                    if (null == tableName || "".equals(tableName.trim()))
                    {
                        tableModel = new TableModel();
                        tableModel.setTableName(tempStr);
                        tableModel.setTableComment(CellValueUtil.getCellValue(row.getCell(2)));
                        tableModelLst.add(tableModel);
                    }
                    // 如果不为空
                    else
                    {
                        // 如果是模糊匹配
                        if (like)
                        {
                            if (tempStr.toUpperCase().indexOf(tableName.toUpperCase()) > -1)
                            {
                                tableModel = new TableModel();
                                tableModel.setTableName(tempStr);
                                tableModel.setTableComment(CellValueUtil.getCellValue(row.getCell(2)));
                                tableModelLst.add(tableModel);
                            }
                        }
                        // 如果是相等匹配
                        else
                        {
                            if (tempStr.equalsIgnoreCase(tableName))
                            {
                                tableModel = new TableModel();
                                tableModel.setTableName(tempStr);
                                tableModel.setTableComment(CellValueUtil.getCellValue(row.getCell(2)));
                                tableModelLst.add(tableModel);
                            }
                        }
                    }
                }
            }
        }
        return tableModelLst;
    }
}
