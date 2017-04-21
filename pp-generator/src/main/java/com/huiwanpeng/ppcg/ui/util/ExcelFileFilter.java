package com.huiwanpeng.ppcg.ui.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * excel文件
 * @version 1.0  
 */
public class ExcelFileFilter extends FileFilter
{
    @Override
    public boolean accept(File file)
    {
        String fileName = file.getName();
        // 允许文件夹通过, 允许以.xls的文件通过, 允许以.xlsx的文件通过
        return (file.isDirectory() || fileName.toLowerCase().endsWith(".xls") || fileName.toLowerCase().endsWith(".xlsx"));
    }

    @Override
    public String getDescription()
    {
        return "*.xls, *.xlsx";
    }
    
}
