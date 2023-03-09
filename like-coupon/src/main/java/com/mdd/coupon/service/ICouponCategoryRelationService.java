package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.CouponCategoryRelation;
import com.mdd.coupon.validate.CouponCategoryRelationParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券分类关联服务接口类
 */
public interface ICouponCategoryRelationService extends IService<CouponCategoryRelation> {

    /**
     * 优惠券分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponSpuCategoryRelationVo>
     */
    PageResult<CouponSpuCategoryRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券分类关联详情
     *
     * @param id 主键ID
     * @return CouponSpuCategoryRelation
     */
    CouponCategoryRelationDetailVo detail(Long id);

    /**
     * 优惠券分类关联新增
     *
     * @param couponCategoryRelationParam 参数
     */
    void add(CouponCategoryRelationParam couponCategoryRelationParam);

    /**
     * 优惠券分类关联编辑
     *
     * @param couponCategoryRelationParam 参数
     */
    void edit(CouponCategoryRelationParam couponCategoryRelationParam);

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
