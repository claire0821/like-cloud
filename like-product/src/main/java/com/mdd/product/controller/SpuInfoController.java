package com.mdd.product.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.vo.ProductDetaliSpuVo;
import com.mdd.product.service.ISpuInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuInfoParam;
import com.mdd.product.vo.SpuInfoListVo;
import com.mdd.product.vo.SpuInfoDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import com.mdd.product.vo.SpuSaveVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * spu信息管理
 */
@RestController
@RequestMapping("api/product/spuinfo")
public class SpuInfoController {

    @Resource
    ISpuInfoService iSpuInfoService;

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SpuInfoListVo> list = iSpuInfoService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * spu信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        SpuInfoDetailVo detail = iSpuInfoService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * spu信息新增
     *
     * @param spuInfoParam 参数
     * @return Object
     */
    @Log(title = "spu信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = SpuInfoParam.create.class) @RequestBody SpuInfoParam spuInfoParam) {
        iSpuInfoService.add(spuInfoParam);
        return AjaxResult.success();
    }

    /**
     * spu信息新增
     *
     * @param vo 参数
     * @return Object
     */
    @Log(title = "spu信息新增")
    @PostMapping("/save")
    public Object save(@RequestBody SpuSaveVo vo) {
        iSpuInfoService.saveSpuInfo(vo);
        return AjaxResult.success();
    }


    /**
     * spu信息编辑
     *
     * @param spuInfoParam 参数
     * @return Object
     */
    @Log(title = "spu信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = SpuInfoParam.update.class) @RequestBody SpuInfoParam spuInfoParam) {
        iSpuInfoService.edit(spuInfoParam);
        return AjaxResult.success();
    }


    /**
     * spu信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "spu信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iSpuInfoService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    //TODO 上架
    @Log(title = "商品上架")
    @GetMapping(value = "/up")
    public Object spuUp(@RequestParam("id") Long id) {
        iSpuInfoService.up(id);
        return AjaxResult.success();
    }

    @Log(title = "根据skuId获取spu详情")
    @RequestMapping("/getDetialBySkuId")
    public AjaxResult<ProductDetaliSpuVo> getDetialBySkuId(@RequestParam("skuId") Long skuId) {
        ProductDetaliSpuVo productDetaliSpuVo = iSpuInfoService.getDetialBySkuId(skuId);
        return AjaxResult.success(productDetaliSpuVo);
    }
}
