package com.huiwanpeng.ppcg.ui.lang;

import com.huiwanpeng.ppcg.ui.lang.specific.ENLang;
import com.huiwanpeng.ppcg.ui.lang.specific.ZHSLang;
import com.huiwanpeng.ppcg.ui.lang.specific.ZHTLang;

/**
 * 语言工厂
 * @version 1.0  
 */
public class LangFactory
{
    public static ILang getLang(int type){
        if(1 == type){
            return new ENLang();
        }
        else if(2 == type){
            return new ZHTLang();
        }
        return new ZHSLang();
    }
}
