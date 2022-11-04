package com.mdd.product.controller;

import com.mdd.admin.config.aop.Log;
import com.mdd.product.service.IPmsSpuImagesService;
import com.mdd.product.validate.PmsSpuImagesParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.vo.PmsSpuImagesListVo;
import com.mdd.product.vo.PmsSpuImagesDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * spu图片管理
 */
@RestController
@RequestMapping("api/spuimages")
public class PmsSpuImagesController {

    @Resource
    IPmsSpuImagesService iPmsSpuImagesService;

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
        PageResult<PmsSpuImagesListVo> list = iPmsSpuImagesService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu图片详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust() @RequestParam("id") Long id) {
        PmsSpuImagesDetailVo detail = iPmsSpuImagesService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu图片新增
     *
     * @param pmsSpuImagesParam 参数
     * @return Object
     */
    @Log(title = "spu图片新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PmsSpuImagesParam.create.class) @RequestBody PmsSpuImagesParam pmsSpuImagesParam) {
        iPmsSpuImagesService.add(pmsSpuImagesParam);
        return AjaxResult.success();
    }

    /**
     * spu图片编辑
     *
     * @param pmsSpuImagesParam 参数
     * @return Object
     */
    @Log(title = "spu图片编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PmsSpuImagesParam.update.class) @RequestBody PmsSpuImagesParam pmsSpuImagesParam) {
        iPmsSpuImagesService.edit(pmsSpuImagesParam);
        return AjaxResult.success();
    }

    /**
     * spu图片删除
     *
     * @param pmsSpuImagesParam 参数
     * @return Object
     */
    @Log(title = "spu图片删除")
    @PostMapping("/del")
    public Object del(@Validated(value = PmsSpuImagesParam.delete.class) @RequestBody PmsSpuImagesParam pmsSpuImagesParam) {
        iPmsSpuImagesService.del(pmsSpuImagesParam.getId());
        return AjaxResult.success();
    }

}
