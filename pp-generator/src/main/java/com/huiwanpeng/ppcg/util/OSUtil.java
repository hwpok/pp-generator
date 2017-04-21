package com.huiwanpeng.ppcg.util;

public class OSUtil
{
    /**
     * 判断是否windows系统
     * @return
     */
    public static boolean isWindows()
    {
        String os = System.getProperty("os.name");
        if (null != os && os.toLowerCase().startsWith("win"))
        {
            return true;
        }
        return false;
    }
}
