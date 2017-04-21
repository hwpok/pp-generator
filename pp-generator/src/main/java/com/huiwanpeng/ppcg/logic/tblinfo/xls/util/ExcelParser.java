package com.huiwanpeng.ppcg.logic.tblinfo.xls.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.huiwanpeng.ppcg.logic.tblinfo.model.ColumnModel;
import com.huiwanpeng.ppcg.logic.tblinfo.model.TableModel;
import com.huiwanpeng.ppcg.util.StrUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;

/**
 * Excel解析器
 * 
 * @version 1.0
 */
public class ExcelParser
{
    /**
     * 得到workBook
     * 
     * @return
     */
    private static Workbook getWorkBook(String filePath)
    {
        Workbook workBook = null;
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(new File(filePath));
            workBook = WorkbookFactory.create(fileInputStream);
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("B1", "parse excel " + filePath + " error." , ex);
        }
        finally
        {
            try
            {
                if (fileInputStream != null)
                {
                    fileInputStream.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return workBook;
    }
    
    /**
     * 得到数据库类型
     * 
     * @param filePath
     * @return
     */
    public static String getDatabaseType(String filePath)
    {
        String databaseType = "";
        try
        {
            Workbook workbook = getWorkBook(filePath);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            
            Sheet sheet = null;
            while (sheetIterator.hasNext())
            {
                sheet = sheetIterator.next();
                databaseType = DatabaseTypeGetter.getDatabaseType(sheet);
                if (!StrUtil.isEmpty(databaseType))
                {
                    return databaseType;
                }
            }
        }
        catch(ComRuntimeException cex){
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("B1", "get database type from excel file  " + filePath + " error." , ex);
        }
        return databaseType;
    }
    
    
    /**
     * 得到表信息
     * 
     * @param filePath
     * @return
     */
    public static List<TableModel> getTableModelLst(String filePath, String tableName, boolean like)
    {
        // 表模型集合
        List<TableModel> tableModelLst = new ArrayList<TableModel>();
        
        Workbook workbook = getWorkBook(filePath);
        try
        {
            Sheet sheet = null;
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext())
            {
                sheet = sheetIterator.next();
                List<TableModel> tempLst = TableModelGetter.getTabeNameLst(sheet, tableName, like);
                tableModelLst.addAll(tempLst);
            }
        }
        catch(ComRuntimeException cex){
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("B1", "get table name from excel file  " + filePath + " error." ,ex);
        }
        return tableModelLst;
    }
    
    /**
     * 得到列信息
     * 
     * @param filePath
     * @return
     */
    public static List<ColumnModel> getTableColumnModelLst(String filePath, String tableName)
    {
        // 表模型集合
        List<ColumnModel> columnModelLst = new ArrayList<ColumnModel>();
        
        Workbook workbook = getWorkBook(filePath);
        try
        {
            Sheet sheet = null;
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext())
            {
                sheet = sheetIterator.next();
                List<ColumnModel> tempLst = TableColumnModelGetter.getTableColumnModelLst(sheet, tableName);
                // 找到一个后就不再继续找了
                if(tempLst.size() > 0){
                    columnModelLst.addAll(tempLst);
                    break;
                }
            }
        }
        catch(ComRuntimeException cex){
            throw cex;
        }
        catch (Exception ex)
        {
            throw new ComRuntimeException("B1", "get colum model from excel file  " + filePath + " error. the tableName is " + tableName , ex);
        }
        return columnModelLst;
    }
}