package com.mdd.member.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.member.service.IUmsMemberCollectSpuService;
import com.mdd.member.validate.UmsMemberCollectSpuParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.vo.UmsMemberCollectSpuListVo;
import com.mdd.member.vo.UmsMemberCollectSpuDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 会员收藏的商品管理
 */
@RestController
@RequestMapping("api/membercollectspu")
public class UmsMemberCollectSpuController {

    @Resource
    IUmsMemberCollectSpuService iUmsMemberCollectSpuService;

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
        PageResult<UmsMemberCollectSpuListVo> list = iUmsMemberCollectSpuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 会员收藏的商品详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        UmsMemberCollectSpuDetailVo detail = iUmsMemberCollectSpuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 会员收藏的商品新增
     *
     * @param umsMemberCollectSpuParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品新增")
    @PostMapping("/add")
    public Object add(@Validated(value = UmsMemberCollectSpuParam.create.class) @RequestBody UmsMemberCollectSpuParam umsMemberCollectSpuParam) {
        iUmsMemberCollectSpuService.add(umsMemberCollectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的商品编辑
     *
     * @param umsMemberCollectSpuParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = UmsMemberCollectSpuParam.update.class) @RequestBody UmsMemberCollectSpuParam umsMemberCollectSpuParam) {
        iUmsMemberCollectSpuService.edit(umsMemberCollectSpuParam);
        return AjaxResult.success();
    }

    /**
     * 会员收藏的商品删除
     *
     * @param umsMemberCollectSpuParam 参数
     * @return Object
     */
    @Log(title = "会员收藏的商品删除")
    @PostMapping("/del")
    public Object del(@Validated(value = UmsMemberCollectSpuParam.delete.class) @RequestBody UmsMemberCollectSpuParam umsMemberCollectSpuParam) {
        iUmsMemberCollectSpuService.del(umsMemberCollectSpuParam.getId());
        return AjaxResult.success();
    }

}
