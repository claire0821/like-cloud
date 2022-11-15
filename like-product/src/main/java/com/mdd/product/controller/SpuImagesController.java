package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.product.service.ISpuImagesService;
import com.mdd.product.validate.SpuImagesParam;
import com.mdd.common.validate.PageParam;
import com.mdd.product.vo.SpuImagesListVo;
import com.mdd.product.vo.SpuImagesDetailVo;
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
 * spu图片管理
 */
@RestController
@RequestMapping("api/product/spuimages")
public class SpuImagesController {

    @Resource
    ISpuImagesService iSpuImagesService;

    /**
     * spu图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SpuImagesListVo> list = iSpuImagesService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu图片详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SpuImagesDetailVo detail = iSpuImagesService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu图片新增
     *
     * @param spuImagesParam 参数
     * @return Object
     */
    @Log(title = "spu图片新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SpuImagesParam.create.class) @RequestBody SpuImagesParam spuImagesParam) {
        iSpuImagesService.add(spuImagesParam);
        return AjaxResult.success();
    }

    /**
     * spu图片编辑
     *
     * @param spuImagesParam 参数
     * @return Object
     */
    @Log(title = "spu图片编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SpuImagesParam.update.class) @RequestBody SpuImagesParam spuImagesParam) {
        iSpuImagesService.edit(spuImagesParam);
        return AjaxResult.success();
    }


    /**
     * spu图片批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "spu图片批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSpuImagesService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

}
