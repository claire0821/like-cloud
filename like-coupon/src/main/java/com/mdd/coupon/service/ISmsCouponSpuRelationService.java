package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsCouponSpuRelationParam;
import com.mdd.coupon.vo.SmsCouponSpuRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券与产品关联服务接口类
 */
public interface ISmsCouponSpuRelationService {

    /**
     * 优惠券与产品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponSpuRelationVo>
     */
    PageResult<SmsCouponSpuRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键ID
     * @return SmsCouponSpuRelation
     */
    SmsCouponSpuRelationDetailVo detail(Long id);

    /**
     * 优惠券与产品关联新增
     *
     * @param smsCouponSpuRelationParam 参数
     */
    void add(SmsCouponSpuRelationParam smsCouponSpuRelationParam);

    /**
     * 优惠券与产品关联编辑
     *
     * @param smsCouponSpuRelationParam 参数
     */
    void edit(SmsCouponSpuRelationParam smsCouponSpuRelationParam);

    /**
     * 优惠券与产品关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
