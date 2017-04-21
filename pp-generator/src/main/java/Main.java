import java.awt.EventQueue;

import javax.swing.UIManager;

import com.huiwanpeng.ppcg.logic.config.glbcfg.GlbCfgHelper;
import com.huiwanpeng.ppcg.logic.config.glbcfg.GlbCfgXmlParser;
import com.huiwanpeng.ppcg.logic.config.glbcfg.model.GlbCfgModel;
import com.huiwanpeng.ppcg.ui.GenCdUI;
import com.huiwanpeng.ppcg.ui.UIParaParser;
import com.huiwanpeng.ppcg.ui.model.UIParaBean;
import com.huiwanpeng.ppcg.util.Monitor;
import com.huiwanpeng.ppcg.util.OSUtil;
import com.huiwanpeng.ppcg.util.logs.ComRuntimeException;
import com.huiwanpeng.ppcg.util.logs.ExceptionInfoUtil;
import com.huiwanpeng.ppcg.util.logs.Logger;

public class Main
{
    public static void main(String[] args)
    {
        Monitor.init(); //初始化监控
        
        EventQueue.invokeLater(new Runnable()
            {
                public void run()
                {
                    try
                    {
                        GlbCfgModel glbCfgModel = GlbCfgXmlParser.parseConfigXml(); // 解析全局配置参数
                        Monitor.putMonitorPoint("parse glbCfgXml");
                        
                        UIParaBean uiParaBean = UIParaParser.getUIParaBean(GlbCfgHelper.getUiParamFilePath(glbCfgModel)); // 解析UI参数
                        Monitor.putMonitorPoint("parse uiParameters");
                        
                        // 界面风格根随系统
                        if(0 == uiParaBean.getSkinStyle()){
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        }
                        // windows经典风格
                        else if(1 == uiParaBean.getSkinStyle()){
                            // 如果是windows经典,且操作系统是windows系统，那么才使用windows经典，否则使用跟随系统
                            if(OSUtil.isWindows()){
                                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                            }
                            else{
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            }
                        }
                        // swing默认风格
                        else{
                            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        }
                        GenCdUI window = new GenCdUI(glbCfgModel, uiParaBean);
                        window.frmXbatis.setVisible(true);
                        Monitor.getTotalTime();
                    }
                    catch(ComRuntimeException cre){
                        Logger.e(cre.getAllInfo());
                    }
                    catch (Exception e)
                    {
                        Logger.e(ExceptionInfoUtil.getStackTraceStr(e));
                    }
                }
            });
    }
}
