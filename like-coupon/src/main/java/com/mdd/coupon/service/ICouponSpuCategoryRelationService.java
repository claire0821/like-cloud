package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.CouponSpuCategoryRelation;
import com.mdd.coupon.validate.CouponSpuCategoryRelationParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponSpuCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券分类关联服务接口类
 */
public interface ICouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelation> {

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
    CouponSpuCategoryRelationDetailVo detail(Long id);

    /**
     * 优惠券分类关联新增
     *
     * @param couponSpuCategoryRelationParam 参数
     */
    void add(CouponSpuCategoryRelationParam couponSpuCategoryRelationParam);

    /**
     * 优惠券分类关联编辑
     *
     * @param couponSpuCategoryRelationParam 参数
     */
    void edit(CouponSpuCategoryRelationParam couponSpuCategoryRelationParam);

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
