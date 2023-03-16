package com.mdd.admin.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.mapper.system.SystemConfigMapper;
import com.mdd.admin.validate.system.SystemAuthRoleParam;
import com.mdd.admin.vo.system.SystemAuthPostVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.system.SystemConfig;
import com.mdd.common.utils.SpringUtil;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: server
 * @description: 系统全局配置
 * @author: Claire
 * @create: 2023-03-15 17:21
 **/
@RestController("systemConfigController")
@RequestMapping("api/system/config")
public class SystemConfigController {
    @Autowired
    SystemConfigMapper systemConfigMapper;


    /**
     *  获取系统全局配置列表
     * @param type
     * @param name
     * @return
     */
    @GetMapping("/list")
    public AjaxResult<List<SystemConfig>> list(@RequestParam(value = "type",required = false) String type,
                             @RequestParam(value = "name",required = false) String name) {
        QueryWrapper<SystemConfig> systemConfigQueryWrapper = new QueryWrapper<>();
        if(type != null && !type.isEmpty()) {
            systemConfigQueryWrapper.eq("type",type);
        }
        if(name != null && !name.isEmpty()) {
            systemConfigQueryWrapper.eq("name",name);
        }
        final List<SystemConfig> systemConfigs = systemConfigMapper.selectList(systemConfigQueryWrapper);
        return AjaxResult.success(systemConfigs);
    }

    /**
     * 获取系统全局配置
     * @param type
     * @param name
     * @return
     */
    @GetMapping("/one")
    public AjaxResult<SystemConfig> one(@RequestParam(value = "type",required = false) String type,
                           @RequestParam(value = "name",required = false) String name) {
        QueryWrapper<SystemConfig> systemConfigQueryWrapper = new QueryWrapper<>();
        if(type != null && !type.isEmpty()) {
            systemConfigQueryWrapper.eq("type",type);
        }
        if(name != null && !name.isEmpty()) {
            systemConfigQueryWrapper.eq("name",name);
        }
        final SystemConfig systemConfig = systemConfigMapper.selectOne(systemConfigQueryWrapper);
        return AjaxResult.success(systemConfig);
    }

    /**
     * 更新
     * @param SystemConfig
     * @return
     */
    @PostMapping("/updateById")
    public AjaxResult<Object> updateById(@RequestBody SystemConfig SystemConfig) {
        systemConfigMapper.updateById(SystemConfig);
        return AjaxResult.success();
    }

    /**
     * 插入数据
     * @param SystemConfig
     * @return
     */
    @PostMapping("/insert")
    public AjaxResult<Object> insert(@RequestBody SystemConfig SystemConfig) {
        systemConfigMapper.insert(SystemConfig);
        return AjaxResult.success();
    }
}
