package com.mdd.ware.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import com.mdd.common.vo.LockStockResult;
import com.mdd.common.vo.WareSkuLockVo;
import com.mdd.ware.service.IWareSkuService;
import com.mdd.ware.validate.WareSkuParam;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.vo.SkuHasStockVo;
import com.mdd.ware.vo.WareSkuListVo;
import com.mdd.ware.vo.WareSkuDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 商品库存管理
 */
@RestController
@RequestMapping("api/ware/sku")
public class WareSkuController {

    @Resource
    IWareSkuService iWareSkuService;

    /**
     * 商品库存列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<WareSkuListVo> list = iWareSkuService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 商品库存详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        WareSkuDetailVo detail = iWareSkuService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 商品库存新增
     *
     * @param wareSkuParam 参数
     * @return Object
     */
    @Log(title = "商品库存新增")
    @PostMapping("/add")
    public Object add(@Validated(value = WareSkuParam.create.class) @RequestBody WareSkuParam wareSkuParam) {
        iWareSkuService.add(wareSkuParam);
        return AjaxResult.success();
    }

    /**
     * 商品库存编辑
     *
     * @param wareSkuParam 参数
     * @return Object
     */
    @Log(title = "商品库存编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = WareSkuParam.update.class) @RequestBody WareSkuParam wareSkuParam) {
        iWareSkuService.edit(wareSkuParam);
        return AjaxResult.success();
    }


    /**
     * 商品库存批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "商品库存批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iWareSkuService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    //TODO 查询sku是否有库存
    /**
     * 查询sku是否有库存
     * @return
     */
    @PostMapping(value = "/hasStock")
    public Object getSkuHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVo> vos = iWareSkuService.getSkuHasStock(skuIds);
        return AjaxResult.success(vos);
    }


    /**
     * 锁库存
     * @return
     */
    @PostMapping(value = "/orderLockStock")
    public AjaxResult<Boolean> orderLockStock(@RequestBody WareSkuLockVo vo) {
        try {
            boolean lockStock = iWareSkuService.orderLockStock(vo);
            return AjaxResult.success(lockStock);
        } catch (BaseException e) {
            throw new BaseException(HttpEnum.ORDER_NO_STOCK.getCode(), HttpEnum.ORDER_NO_STOCK.getMsg());
        }
    }
}
