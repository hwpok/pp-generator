package com.huiwanpeng.ppcg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static String getTimeStr(Date date){
        String res = "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            res = sdf.format(date);
        }
        catch(Exception ex){}
        return res;
    }
}
