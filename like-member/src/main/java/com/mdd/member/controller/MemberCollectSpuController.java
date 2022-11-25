package com.mdd.member.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.member.service.IMemberCollectSpuService;
import com.mdd.member.validate.MemberCollectSpuParam;
import com.mdd.common.validate.PageParam;
import com.mdd.member.vo.MemberCollectSpuListVo;
import com.mdd.member.vo.MemberCollectSpuDetailVo;
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
 * 会员收藏的商品管理
 */
@RestController
@RequestMapping("api/member/spu")
public class MemberCollectSpuController {

    @Resource
    IMemberCollectSpuService iMemberCollectSpuService;

    /**
     * 会员收藏的商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<MemberCollectSpuListVo> list = iMemberCollectSpuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收藏的商品详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        MemberCollectSpuDetailVo detail = iMemberCollectSpuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收藏的商品新增
     *
     * @param memberCollectSpuParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品新增")
    @PostMapping("/add")
    public Object add(@Validated(value = MemberCollectSpuParam.create.class) @RequestBody MemberCollectSpuParam memberCollectSpuParam) {
        iMemberCollectSpuService.add(memberCollectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的商品编辑
     *
     * @param memberCollectSpuParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = MemberCollectSpuParam.update.class) @RequestBody MemberCollectSpuParam memberCollectSpuParam) {
        iMemberCollectSpuService.edit(memberCollectSpuParam);
        return AjaxResult.success();
    }


    /**
     * 会员收藏的商品批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iMemberCollectSpuService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
