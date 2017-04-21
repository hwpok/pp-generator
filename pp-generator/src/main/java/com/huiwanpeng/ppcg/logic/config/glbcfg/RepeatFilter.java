package com.huiwanpeng.ppcg.logic.config.glbcfg;

import java.util.HashMap;
import java.util.Map;

/**
 * 重复过滤
 * @version 1.0  
 */
public class RepeatFilter
{
    public Map<String, String> glbCfgDbModelMap = new HashMap<String, String>(); //数据库配置模型重复过滤Map
    public Map<String, String> glbCfgPoTmpModelMap = new HashMap<String, String>(); //PO模板配置模型重复过滤Map
    public Map<String, String> glbCfgDaoMappingTmpModelMap = new HashMap<String, String>(); //PO模板配置模型重复过滤Map
}
