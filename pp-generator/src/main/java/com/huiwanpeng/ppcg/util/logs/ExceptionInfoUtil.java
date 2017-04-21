package com.huiwanpeng.ppcg.util.logs;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionInfoUtil
{
    public static String getStackTraceStr(Throwable t)
    {
        PrintWriter pw = null;
        try
        {
            StringWriter sw = new StringWriter();
            pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            return sw.toString();
        }
        catch (Exception ex)
        {}
        finally
        {
            if (pw != null)
            {
                try
                {
                    pw.close();
                }
                catch (Exception ex)
                {}
            }
        }
        return "";
        
    }
    
}
