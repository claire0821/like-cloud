package com.mdd.admin.controller.system;


import com.mdd.common.config.aop.Log;
import com.mdd.admin.service.system.ISystemAuthAdminService;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.feign.AuthFeignService;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.system.SystemAuthAdminParam;
import com.mdd.admin.vo.system.SystemAuthAdminVo;
import com.mdd.admin.vo.system.SystemAuthSelfVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统管理员管理
 */
@RestController
@RequestMapping("api/system/admin")
public class AuthAdminController {

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;
    @Autowired
    AuthFeignService authFeignService;
    /**
     * 管理员列表
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam,
                        @RequestParam Map<String, String> params) {
        PageResult<SystemAuthAdminVo> list = iSystemAuthAdminService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    //TODO 采购单分配给管理员还是用户
    /**
     * 用户列表
     *
     * @return AjaxResult
     */
    @GetMapping("/userlists")
    public AjaxResult userlists() {
        List<SystemAuthAdmin> list = iSystemAuthAdminService.list();
        return AjaxResult.success(list);
    }

    /**
     * 管理员信息
     *
     * @author fzr
     * @return AjaxResult
     */
    @GetMapping("/self")
    public AjaxResult self() {
        SystemAuthSelfVo vo = iSystemAuthAdminService.self();
        return AjaxResult.success(vo);
    }

    /**
     * 管理员详情
     *
     * @author fzr
     * @param id 主键ID
     * @return AjaxResult
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SystemAuthAdminVo vo = iSystemAuthAdminService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return AjaxResult
     */
    @Log(title = "管理员新增")
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = SystemAuthAdminParam.create.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.add(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 管理员编辑
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     * @return AjaxResult
     */
    @Log(title = "管理员编辑")
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = SystemAuthAdminParam.update.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.edit(systemAuthAdminParam);
        return AjaxResult.success();
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员更新")
    @PostMapping("/upInfo")
    public AjaxResult upInfo(@Validated(value = SystemAuthAdminParam.upInfo.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        UserVo userInfo = authFeignService.getUserInfo().getData();
        final Long adminId = userInfo.getId();
        iSystemAuthAdminService.upInfo(systemAuthAdminParam, adminId);
        return AjaxResult.success();
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员删除")
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.del(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @return AjaxResult
     */
    @Log(title = "管理员状态切换")
    @PostMapping("/disable")
    public AjaxResult disable(@Validated(value = SystemAuthAdminParam.delete.class) @RequestBody SystemAuthAdminParam systemAuthAdminParam) {
        iSystemAuthAdminService.disable(systemAuthAdminParam.getId());
        return AjaxResult.success();
    }

}
