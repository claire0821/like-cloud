package com.mdd.order.feign;

import com.mdd.common.core.AjaxResult;
import com.mdd.common.vo.LockStockResult;
import com.mdd.common.vo.WareSkuLockVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-22 16:37
 **/
public interface IWareFeignService {
    /**
     * 锁定库存
     * @param vo
     * @return
     */
    @PostMapping(value = "/api/ware/sku/orderLockStock")
    AjaxResult<List<LockStockResult>> orderLockStock(@RequestBody WareSkuLockVo vo);
}
