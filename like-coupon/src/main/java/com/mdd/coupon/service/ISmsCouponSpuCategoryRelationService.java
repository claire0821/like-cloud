package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsCouponSpuCategoryRelationParam;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 优惠券分类关联服务接口类
 */
public interface ISmsCouponSpuCategoryRelationService {

    /**
     * 优惠券分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponSpuCategoryRelationVo>
     */
    PageResult<SmsCouponSpuCategoryRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 优惠券分类关联详情
     *
     * @param id 主键ID
     * @return SmsCouponSpuCategoryRelation
     */
    SmsCouponSpuCategoryRelationDetailVo detail(Long id);

    /**
     * 优惠券分类关联新增
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     */
    void add(SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam);

    /**
     * 优惠券分类关联编辑
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     */
    void edit(SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam);

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
