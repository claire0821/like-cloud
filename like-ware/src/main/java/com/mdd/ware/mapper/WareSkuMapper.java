package com.mdd.ware.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.ware.entity.WareSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品库存Mapper
 */
@Mapper
public interface WareSkuMapper extends IBaseMapper<WareSku> {
    List<Long> listWareIdHasSkuStock(@Param("skuId") Long skuId);
    /**
     * 锁定库存
     * @param skuId
     * @param wareId
     * @param num
     * @return
     */
    Long lockSkuStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);

    /**
     * 解锁库存
     * @param skuId
     * @param wareId
     * @param num
     */
    void unLockStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("num") Integer num);
}
