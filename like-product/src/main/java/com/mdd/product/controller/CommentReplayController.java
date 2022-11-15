package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ICommentReplayService;
import com.mdd.product.validate.CommentReplayParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.CommentReplayListVo;
import com.mdd.product.vo.CommentReplayDetailVo;
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
 * 商品评价回复关系管理
 */
@RestController
@RequestMapping("api/product/replay")
public class CommentReplayController {

    @Resource
    ICommentReplayService iCommentReplayService;

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
        PageResult<CommentReplayListVo> list = iCommentReplayService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品评价回复关系详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        CommentReplayDetailVo detail = iCommentReplayService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品评价回复关系新增
     *
     * @param commentReplayParam 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系新增")
    @PostMapping("/add")
    public Object add(@Validated(value = CommentReplayParam.create.class) @RequestBody CommentReplayParam commentReplayParam) {
        iCommentReplayService.add(commentReplayParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价回复关系编辑
     *
     * @param commentReplayParam 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = CommentReplayParam.update.class) @RequestBody CommentReplayParam commentReplayParam) {
        iCommentReplayService.edit(commentReplayParam);
        return AjaxResult.success();
    }


    /**
     * 商品评价回复关系批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品评价回复关系批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iCommentReplayService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
