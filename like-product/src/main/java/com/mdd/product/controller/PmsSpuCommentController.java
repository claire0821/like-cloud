package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsSpuCommentService;
import com.mdd.product.validate.PmsSpuCommentParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsSpuCommentListVo;
import com.mdd.product.vo.PmsSpuCommentDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商品评价管理
 */
@RestController
@RequestMapping("api/comment")
public class PmsSpuCommentController {

    @Resource
    IPmsSpuCommentService iPmsSpuCommentService;

    /**
     * 商品评价列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PmsSpuCommentListVo> list = iPmsSpuCommentService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品评价详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSpuCommentDetailVo detail = iPmsSpuCommentService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品评价新增
     *
     * @param pmsSpuCommentParam 参数
     * @return Object
     */
    @Log(title = "商品评价新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSpuCommentParam.create.class) @RequestBody PmsSpuCommentParam pmsSpuCommentParam) {
        iPmsSpuCommentService.add(pmsSpuCommentParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价编辑
     *
     * @param pmsSpuCommentParam 参数
     * @return Object
     */
    @Log(title = "商品评价编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSpuCommentParam.update.class) @RequestBody PmsSpuCommentParam pmsSpuCommentParam) {
        iPmsSpuCommentService.edit(pmsSpuCommentParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价删除
     *
     * @param pmsSpuCommentParam 参数
     * @return Object
     */
    @Log(title = "商品评价删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSpuCommentParam.delete.class) @RequestBody PmsSpuCommentParam pmsSpuCommentParam) {
        iPmsSpuCommentService.del(pmsSpuCommentParam.getId());
        return AjaxResult.success();
    }

}
