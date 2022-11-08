package com.mdd.admin.controller.system;

import com.mdd.common.config.aop.Log;
import com.mdd.admin.service.system.ISystemAuthRoleService;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.system.SystemAuthRoleParam;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统角色管理
 */
@RestController
@RequestMapping("api/system/role")
public class AuthRoleController {

    @Resource
    ISystemAuthRoleService iSystemAuthRoleService;

    /**
     * 角色所有
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/all")
    public AjaxResult all() {
        List<Map<String, Object>> list = iSystemAuthRoleService.all();
        return AjaxResult.success(list);
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return AjaxResult
     */
    @Log(title = "角色列表")
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam) {
        PageResult<SystemAuthRoleVo> lists = iSystemAuthRoleService.list(pageParam);
        return AjaxResult.success(lists);
    }

    /**
     * 角色详情
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "角色详情")
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SystemAuthRoleVo vo = iSystemAuthRoleService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return AjaxResult
     */
    @Log(title = "角色新增")
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = SystemAuthRoleParam.create.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.add(systemAuthRoleParam);
        return AjaxResult.success();
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return AjaxResult
     */
    @Log(title = "角色编辑")
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthRoleParam.create.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.edit(systemAuthRoleParam);
        return AjaxResult.success();
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param systemAuthRoleParam 角色参数
     * @return AjaxResult
     */
    @Log(title = "角色删除")
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthRoleParam.delete.class) @RequestBody SystemAuthRoleParam systemAuthRoleParam) {
        iSystemAuthRoleService.del(systemAuthRoleParam.getId());
        return AjaxResult.success();
    }

}
