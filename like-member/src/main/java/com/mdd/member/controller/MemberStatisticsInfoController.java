package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IMemberStatisticsInfoService;
import com.mdd.member.validate.MemberStatisticsInfoParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberStatisticsInfoListVo;
import com.mdd.member.vo.MemberStatisticsInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 会员统计信息管理
 */
@RestController
@RequestMapping("api/member/info")
public class MemberStatisticsInfoController {

    @Resource
    IMemberStatisticsInfoService iMemberStatisticsInfoService;

    /**
     * 会员统计信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<MemberStatisticsInfoListVo> list = iMemberStatisticsInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员统计信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberStatisticsInfoDetailVo detail = iMemberStatisticsInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员统计信息新增
     *
     * @param memberStatisticsInfoParam 参数
     * @return Object
     */
    @Log(title = "会员统计信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberStatisticsInfoParam.create.class) @RequestBody MemberStatisticsInfoParam memberStatisticsInfoParam) {
        iMemberStatisticsInfoService.add(memberStatisticsInfoParam);
        return AjaxResult.success();
    }

    /**
     * 会员统计信息编辑
     *
     * @param memberStatisticsInfoParam 参数
     * @return Object
     */
    @Log(title = "会员统计信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberStatisticsInfoParam.update.class) @RequestBody MemberStatisticsInfoParam memberStatisticsInfoParam) {
        iMemberStatisticsInfoService.edit(memberStatisticsInfoParam);
        return AjaxResult.success();
    }


    /**
     * 会员统计信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员统计信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberStatisticsInfoService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
