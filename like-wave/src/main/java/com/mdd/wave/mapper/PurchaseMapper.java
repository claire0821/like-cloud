package com.mdd.wave.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.wave.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 采购信息Mapper
 */
@Mapper
public interface PurchaseMapper extends IBaseMapper<Purchase> {
}
