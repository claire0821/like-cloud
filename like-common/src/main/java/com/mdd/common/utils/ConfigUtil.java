package com.mdd.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.feign.ISystemConfigFeignService;
import com.mdd.common.vo.CartItemVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库配置操作工具
 */
public class ConfigUtil {

    /**
     * 根据类型获取配置
     *
     * @author fzr
     * @param type 类型
     * @return Map<String, String>
     */
    public static Map<String, String> get(String type) {
        ISystemConfigFeignService iSystemConfigFeignService = SpringUtil.getBean(ISystemConfigFeignService.class);
        AjaxResult<List<SystemConfig>> result = iSystemConfigFeignService.list(type,"");
        if(!result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            return null;
        }
        final List<SystemConfig> configs = result.getData();

        Map<String, String> map = new LinkedHashMap<>();
        for (SystemConfig config : configs) {
            map.put(config.getName(), config.getValue());
        }

        return map;
    }

    /**
     * 根据类型和名称获取配置
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static String get(String type, String name) {
        ISystemConfigFeignService iSystemConfigFeignService = SpringUtil.getBean(ISystemConfigFeignService.class);
        AjaxResult<SystemConfig> result = iSystemConfigFeignService.one(type,name);
        if(!result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            return null;
        }
        SystemConfig config = result.getData();

        return config.getValue();
    }

    /**
     * 根据类型和名称获取配置
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static String get(String type, String name, String defaults) {
        ISystemConfigFeignService iSystemConfigFeignService = SpringUtil.getBean(ISystemConfigFeignService.class);
        AjaxResult<SystemConfig> result = iSystemConfigFeignService.one(type,name);
        if(!result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            return defaults;
        }
        SystemConfig config = result.getData();

        if (config == null) {
            return defaults;
        }

        return config.getValue();
    }

    /**
     * 根据类型和名称获取配置(JSON自定转Map)
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @return String
     */
    public static Map<String, String> getMap(String type, String name) {
        ISystemConfigFeignService iSystemConfigFeignService = SpringUtil.getBean(ISystemConfigFeignService.class);
        AjaxResult<SystemConfig> result = iSystemConfigFeignService.one(type,name);
        if(!result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            return null;
        }
        SystemConfig config = result.getData();
        if (config == null) {
            return null;
        }

        if (config.getValue().equals("") || config.getValue().equals("[]") || config.getValue().equals("{}")) {
            return new LinkedHashMap<>();
        }

        return ToolsUtil.jsonToMap(config.getValue());
    }

    /**
     * 设置配置的值
     *
     * @author fzr
     * @param type 类型
     * @param name 名称
     * @param val 值
     */
    public static void set(String type, String name, String val) {
        ISystemConfigFeignService iSystemConfigFeignService = SpringUtil.getBean(ISystemConfigFeignService.class);
        AjaxResult<SystemConfig> result = iSystemConfigFeignService.one(type,name);
        if(!result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            return;
        }
        SystemConfig config = result.getData();
        if (config != null) {
            config.setValue(val);
            config.setUpdateTime(System.currentTimeMillis() / 1000);
            iSystemConfigFeignService.updateById(config);
        } else {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setType(type);
            systemConfig.setName(name);
            systemConfig.setValue(val);
            systemConfig.setCreateTime(System.currentTimeMillis() / 1000);
            systemConfig.setUpdateTime(System.currentTimeMillis() / 1000);
            iSystemConfigFeignService.insert(systemConfig);
        }
    }
}
