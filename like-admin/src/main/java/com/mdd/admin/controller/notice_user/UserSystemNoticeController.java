package com.mdd.admin.controller.notice_user;

import com.mdd.common.config.aop.Log;
import com.mdd.admin.service.notice_user.IUserSystemNoticeService;
import com.mdd.admin.validate.notice_user.UserSystemNoticeParam;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.vo.notice_user.UserSystemNoticeListVo;
import com.mdd.admin.vo.notice_user.UserSystemNoticeDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户系统通知管理
 * @author Claire
 */
@RestController
@RequestMapping("api/notice_user")
public class UserSystemNoticeController {

    @Resource
    IUserSystemNoticeService iUserSystemNoticeService;

    /**
     * 用户系统通知列表
     *
     * @author Claire
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<UserSystemNoticeListVo> list = iUserSystemNoticeService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 用户系统通知详情
     *
     * @author Claire
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        UserSystemNoticeDetailVo detail = iUserSystemNoticeService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 用户系统通知新增
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     * @return Object
     */
    @Log(title = "用户系统通知新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UserSystemNoticeParam.create.class) @RequestBody UserSystemNoticeParam userSystemNoticeParam) {
        iUserSystemNoticeService.add(userSystemNoticeParam);
        return AjaxResult.success();
    }

    /**
     * 用户系统通知编辑
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     * @return Object
     */
    @Log(title = "用户系统通知编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UserSystemNoticeParam.update.class) @RequestBody UserSystemNoticeParam userSystemNoticeParam) {
        iUserSystemNoticeService.edit(userSystemNoticeParam);
        return AjaxResult.success();
    }

    /**
     * 用户系统通知删除
     *
     * @author Claire
     * @param userSystemNoticeParam 参数
     * @return Object
     */
    @Log(title = "用户系统通知删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UserSystemNoticeParam.delete.class) @RequestBody UserSystemNoticeParam userSystemNoticeParam) {
        iUserSystemNoticeService.del(userSystemNoticeParam.getId());
        return AjaxResult.success();
    }

}
