package com.mdd.coupon.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.coupon.entity.SmsHomeSubject;
import org.apache.ibatis.annotations.Mapper;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】Mapper
 */
@Mapper
public interface SmsHomeSubjectMapper extends IBaseMapper<SmsHomeSubject> {
}
