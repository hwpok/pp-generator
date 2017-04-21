package com.huiwanpeng.ppcg.ui.util;

import java.awt.Desktop;
import java.io.File;

import javax.swing.JOptionPane;

import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

public class MsgUtil
{
    public static void getShowErrorInfoMessageDialog(String info, ComRuntimeException cre){
        // 如果出现错误, 那么询问用户是否打开查看错误日志信息
        StringBuilder errorInfo = new StringBuilder();
        errorInfo.append(info);
        
        if(null != cre && null != cre.getErrorMessage()){
            errorInfo.append("\n\r");
            errorInfo.append(cre.getErrorMessage());
        }
        
        errorInfo.append("\n\r");
        errorInfo.append("去日志文件:\n\r");
        errorInfo.append(Logger.getFilePath(1));
        errorInfo.append("\n\r查看异常信息?");
        
        // 让用户选择
        int result = JOptionPane.showConfirmDialog(null, errorInfo.toString(), "错误", JOptionPane.YES_NO_OPTION);
        if(0 == result){
            try{
                //打开错误日志文件
                Desktop.getDesktop().open(new File(Logger.getLogFilePathDirector(1)));
            }
            catch(Exception ex){
                Logger.e(ExceptionInfoUtil.getStackTraceStr(ex));
            }
        }
        
    }
}
