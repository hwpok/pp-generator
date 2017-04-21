package com.huiwanpeng.ppcg.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTool
{
    public static String readValue(String filePath, String key)
    {
        Properties prop = new Properties();
        try
        {
            InputStream in = new BufferedInputStream(new FileInputStream(new File(filePath)));
            prop.load(in);
            return prop.getProperty(key);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
