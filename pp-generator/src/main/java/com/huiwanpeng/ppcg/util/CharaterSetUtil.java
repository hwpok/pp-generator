package com.huiwanpeng.ppcg.util;

import java.util.ArrayList;
import java.util.List;

public class CharaterSetUtil
{
    /**
     * 根据字符集名, 判断是否包含某些字符集
     * @param charaterName
     * @return
     */
    public static boolean containCharater(String charaterName){
        if(null == charaterName || 0 == charaterName.length()){
            return false;
        }
        return getCharaterLst().contains(charaterName);
    }
    
    /**
     * 得到字符集数组
     * @return
     */
    public static String[] getCharaterArray()
    {
        return getCharaterLst().toArray(new String[]{});
    }
    
    /**
     * 得到字符集集合
     * @return
     */
    public static List<String> getCharaterLst()
    {
        List<String> c = new ArrayList<String>();
        c.add("IBM037");
        c.add("IBM273");
        c.add("IBM277");
        c.add("IBM278");
        c.add("IBM280");
        c.add("IBM284");
        c.add("IBM285");
        c.add("IBM297");
        c.add("US-ASCII");
        c.add("IBM420");
        c.add("IBM423");
        c.add("IBM424");
        c.add("IBM437");
        c.add("IBM500");
        c.add("IBM808");
        c.add("ISO-8859-7");
        c.add("ISO-8859-1");
        c.add("IBM-Thai");
        c.add("IBM850");
        c.add("IBM852");
        c.add("IBM855");
        c.add("IBM857");
        c.add("IBM00858");
        c.add("IBM862");
        c.add("IBM863");
        c.add("IBM864");
        c.add("IBM866");
        c.add("IBM867");
        c.add("IBM869");
        c.add("IBM870");
        c.add("IBM871");
        c.add("IBM872");
        c.add("TIS-620");
        c.add("KOI8-R");
        c.add("ISO-8859-13");
        c.add("IBM902");
        c.add("IBM904");
        c.add("ISO-8859-2");
        c.add("ISO-8859-5");
        c.add("ISO-8859-8-I");
        c.add("ISO-8859-9");
        c.add("IBM921");
        c.add("IBM922");
        c.add("ISO-8859-15");
        c.add("IBM00924");
        c.add("Shift_JIS");
        c.add("Windows-31J");
        c.add("EUC-KR");
        c.add("Big5");
        c.add("EUC-JP");
        c.add("EUC-TW");
        c.add("EUC-KR");
        c.add("Microsoft-Publish");
        c.add("IBM1026");
        c.add("IBM1043");
        c.add("IBM1047");
        c.add("hp-roman8");
        c.add("ISO-8859-6");
        c.add("VISCII");
        c.add("IBM01140");
        c.add("IBM01141");
        c.add("IBM01142");
        c.add("IBM01143");
        c.add("IBM01144");
        c.add("IBM01145");
        c.add("IBM01146");
        c.add("IBM01147");
        c.add("IBM01148");
        c.add("IBM01149");
        c.add("IBM01153");
        c.add("IBM01155");
        c.add("IBM-Thai");
        c.add("TIS-620");
        c.add("TIS-620");
        c.add("VISCII");
        c.add("KOI8-U");
        c.add("UTF-16BE");
        c.add("UTF-16LE");
        c.add("UTF-16");
        c.add("UTF-8");
        c.add("UTF-32BE");
        c.add("UTF-32LE");
        c.add("UTF-32");
        c.add("windows-1250");
        c.add("windows-1251");
        c.add("windows-1252");
        c.add("windows-1253");
        c.add("windows-1254");
        c.add("windows-1255");
        c.add("windows-1256");
        c.add("windows-1257");
        c.add("windows-1258");
        c.add("MACINTOSH");
        c.add("KSC_5601");
        c.add("Big5");
        c.add("GB2312");
        c.add("GB2312");
        c.add("GBK");
        c.add("GB18030");
        c.add("ISO-8859-7");
        c.add("Shift_JIS");
        c.add("windows-1250");
        c.add("windows-1251");
        c.add("windows-1252");
        c.add("windows-1253");
        c.add("windows-1254");
        c.add("windows-1255");
        c.add("windows-1256");
        c.add("windows-1257");
        c.add("windows-1258");
        c.add("GB18030");
        c.add("IBM420");
        c.add("IBM424");
        c.add("ISO-8859-7");
        c.add("IBM424");
        c.add("UTF-16BE");
        c.add("UTF-16LE");
        c.add("IBM420");
        c.add("IBM864");
        c.add("UTF-16BE");
        c.add("UTF-16LE");
        c.add("IBM862");
        c.add("ISO-8859-8");
        c.add("IBM424");
        c.add("IBM862");
        c.add("ISO-8859-8");
        c.add("IBM864");
        c.add("IBM862");
        c.add("ISO-8859-8");
        c.add("windows-1255");
        c.add("IBM420");
        c.add("IBM864");
        c.add("ISO-8859-6");
        c.add("windows-1256");
        c.add("IBM424");
        c.add("IBM862");
        c.add("ISO-8859-8");
        c.add("IBM420");
        c.add("IBM420");
        c.add("IBM424");
        c.add("windows-1255");
        c.add("ISO-8859-8-I");
        c.add("windows-1255");
        c.add("IBM424");
        c.add("IBM862");
        c.add("ISO-8859-8-I");
        c.add("windows-1255");
        c.add("IBM424");
        c.add("IBM420");
        return c;
    }
    
}
