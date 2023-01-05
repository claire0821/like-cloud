package com.mdd.order.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.ProductDetaliSpuVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProductFeignService {
    /**
     * 根据skuId查询spu的信息
     * @param skuId
     * @return
     */
    @GetMapping(value = "/api/product/spuinfo/getDetialBySkuId")
    public AjaxResult<ProductDetaliSpuVo> getDetialBySkuId(@RequestParam("skuId") Long skuId);
}
