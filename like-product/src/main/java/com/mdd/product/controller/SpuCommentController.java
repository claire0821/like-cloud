package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ISpuCommentService;
import com.mdd.product.validate.SpuCommentParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.SpuCommentListVo;
import com.mdd.product.vo.SpuCommentDetailVo;
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
 * 商品评价管理
 */
@RestController
@RequestMapping("api/product/comment")
public class SpuCommentController {

    @Resource
    ISpuCommentService iSpuCommentService;

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
        PageResult<SpuCommentListVo> list = iSpuCommentService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品评价详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SpuCommentDetailVo detail = iSpuCommentService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品评价新增
     *
     * @param spuCommentParam 参数
     * @return Object
     */
    @Log(title = "商品评价新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SpuCommentParam.create.class) @RequestBody SpuCommentParam spuCommentParam) {
        iSpuCommentService.add(spuCommentParam);
        return AjaxResult.success();
    }

    /**
     * 商品评价编辑
     *
     * @param spuCommentParam 参数
     * @return Object
     */
    @Log(title = "商品评价编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SpuCommentParam.update.class) @RequestBody SpuCommentParam spuCommentParam) {
        iSpuCommentService.edit(spuCommentParam);
        return AjaxResult.success();
    }


    /**
     * 商品评价批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品评价批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSpuCommentService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
//TODO 代码生成 生成delBatch和detail方法有误