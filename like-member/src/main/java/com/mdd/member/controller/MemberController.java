package com.mdd.member.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.config.aop.Log;
import com.mdd.common.feign.AuthFeignService;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.MemberVo;
import com.mdd.common.vo.UserVo;
import com.mdd.member.service.IMemberService;
import com.mdd.member.validate.MemberParam;
import com.mdd.common.validate.PageParam;
import com.mdd.common.validate.user.RegParam;
import com.mdd.member.vo.MemberListVo;
import com.mdd.member.vo.MemberDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员管理
 */
@RestController
@RequestMapping("api/member/member")
public class MemberController {

    @Resource
    IMemberService iMemberService;
    @Autowired
    AuthFeignService authFeignService;
    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult<PageResult<MemberVo>> list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<MemberVo> list = iMemberService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员详情
     * @return Object
     */
    @GetMapping("/detail")
    public AjaxResult<MemberVo> detail() {
        MemberVo detail = iMemberService.detail();
        return AjaxResult.success(detail);
    }

    /**
     * 会员详情
     * @return Object
     */
    @GetMapping("/detailById")
    public AjaxResult<MemberVo> detailById(@RequestParam("id") Long id) {
        MemberVo detail = iMemberService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员新增
     *
     * @param memberParam 参数
     * @return Object
     */
    @Log(title = "会员新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberParam.create.class) @RequestBody MemberParam memberParam) {
        iMemberService.add(memberParam);
        return AjaxResult.success();
    }

    /**
     * 会员编辑
     *
     * @param memberParam 参数
     * @return Object
     */
//    @Log(title = "会员编辑")
//    @PostMapping("/edit")
//    public Object edit(@Validated(value = MemberParam.update.class) @RequestBody MemberParam memberParam) {
//        iMemberService.edit(memberParam);
//        return AjaxResult.success();
//    }


    /**
     * 会员批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    /**
     * 会员注册
     *
     * @param regParam 参数
     * @return Object
     */
    @Log(title = "会员注册")
    @PostMapping("/register")
    public AjaxResult<Object> register(@Validated @RequestBody RegParam regParam) {
        iMemberService.register(regParam);
        return AjaxResult.success();
    }

    /**
     * 会员登录
     *
     * @param loginParam 参数
     * @return Object
     */
    @Log(title = "会员登录")
    @PostMapping("/login")
    public AjaxResult<UserVo> login(@Validated @RequestBody LoginParam loginParam) {
        final UserVo userVo = iMemberService.login(loginParam);
        return AjaxResult.success(userVo);
    }

    /**
     * 个人中心
     *
     * @author Claire
     * @return Object
     */
    @GetMapping("/center")
    public Object center() {
        final UserVo data = authFeignService.getUserInfo().getData();
        MemberDetailVo vo = iMemberService.center(data.getId());
        return AjaxResult.success(vo);
    }

    /**
     * 编辑信息
     *
     * @author fzr
     * @param params 参数
     * @return Object
     */
    @Log(title = "会员编辑")
    @PostMapping("/edit")
    public Object edit(@RequestBody Map<String, String> params) {
        Assert.notNull(params.get("field"), "field参数缺失");
        Assert.notNull(params.get("value"), "value参数缺失");
        final UserVo data = authFeignService.getUserInfo().getData();
        iMemberService.edit(params, data.getId());
        return AjaxResult.success();
    }


}
