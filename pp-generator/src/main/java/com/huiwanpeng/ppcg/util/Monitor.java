package com.huiwanpeng.ppcg.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 监控器
 * @version 1.0  
 */
public class Monitor
{
    /** 是否可用 */
    private static boolean enabled = true;
    
    /** 监控点集合 */
    private static List<MonitorBean> monitorBeanLst = new ArrayList<MonitorBean>();
    
    
    /**
     * 初始化
     */
    public static void init(){
        MonitorBean monitorBean = new MonitorBean("init", System.currentTimeMillis());
        monitorBeanLst.add(monitorBean);
    }
    
    /**
     *总耗时
     */
    public static void getTotalTime(){
        if(monitorBeanLst.size()>1){
            System.out.println("total consume: " + (monitorBeanLst.get(monitorBeanLst.size() -1).getEndTime() - monitorBeanLst.get(0).getStartTime()));
        }
    }
    
    /**
     * 放置监控点
     * @param name
     */
    public static void putMonitorPoint(String name){
        if(enabled){
            long startTime = System.currentTimeMillis();
            if(monitorBeanLst.size()>0){
                startTime = monitorBeanLst.get(monitorBeanLst.size() -1).getEndTime();
            }
            MonitorBean monitorBean = new MonitorBean(name, startTime);
            monitorBeanLst.add(monitorBean);
            System.out.println(monitorBean.getInfo());
        }
    }
    
    /**
     * 监控类
     * @version 1.0  
     */
    public static class MonitorBean
    {
        private static int stepCounter = -1; // 步记数器
        
        private int stepNum; // 第几步
        private String name; // 名称
        private long startTime;; // 上一步
        private long endTime; // 当前时间
        
        public MonitorBean(String name, long startTime)
        {
            stepCounter++;
            this.stepNum = stepCounter;
            this.name = name;
            this.startTime = startTime;
            this.endTime = System.currentTimeMillis();
        }
        
        public String getInfo(){
           StringBuilder info = new StringBuilder();
           info.append("step: ").append(stepNum).append(", ");
           info.append("startTime: ").append(startTime).append(", ");
           info.append("endTime: ").append(endTime).append(", ");
           info.append("consume: ").append(endTime - startTime).append(", ");
           info.append(name).append("");
           return info.toString();
        }

        public int getStepNum()
        {
            return stepNum;
        }

        public void setStepNum(int stepNum)
        {
            this.stepNum = stepNum;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public long getStartTime()
        {
            return startTime;
        }

        public void setStartTime(long startTime)
        {
            this.startTime = startTime;
        }

        public long getEndTime()
        {
            return endTime;
        }

        public void setEndTime(long endTime)
        {
            this.endTime = endTime;
        }
    }
}
