package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.IPmsCommentReplayService;
import com.mdd.product.validate.PmsCommentReplayParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.PmsCommentReplayListVo;
import com.mdd.product.vo.PmsCommentReplayDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品评价回复关系管理
 */
@RestController
@RequestMapping("api/replay")
public class PmsCommentReplayController {

    @Resource
    IPmsCommentReplayService iPmsCommentReplayService;

    /**
     * 商品评价回复关系列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsCommentReplayListVo> list = iPmsCommentReplayService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品评价回复关系详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsCommentReplayDetailVo detail = iPmsCommentReplayService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品评价回复关系新增
     *
     * @param pmsCommentReplayParam 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsCommentReplayParam.create.class) @RequestBody PmsCommentReplayParam pmsCommentReplayParam) {
        iPmsCommentReplayService.add(pmsCommentReplayParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价回复关系编辑
     *
     * @param pmsCommentReplayParam 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsCommentReplayParam.update.class) @RequestBody PmsCommentReplayParam pmsCommentReplayParam) {
        iPmsCommentReplayService.edit(pmsCommentReplayParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价回复关系删除
     *
     * @param pmsCommentReplayParam 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsCommentReplayParam.delete.class) @RequestBody PmsCommentReplayParam pmsCommentReplayParam) {
        iPmsCommentReplayService.del(pmsCommentReplayParam.getId());
        return AjaxResult.success();
    }

}
