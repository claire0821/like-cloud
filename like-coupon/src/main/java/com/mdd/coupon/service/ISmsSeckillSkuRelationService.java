package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillSkuRelationParam;
import com.mdd.coupon.vo.SmsSeckillSkuRelationListVo;
import com.mdd.coupon.vo.SmsSeckillSkuRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀活动商品关联服务接口类
 */
public interface ISmsSeckillSkuRelationService {

    /**
     * 秒杀活动商品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSkuRelationVo>
     */
    PageResult<SmsSeckillSkuRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键ID
     * @return SmsSeckillSkuRelation
     */
    SmsSeckillSkuRelationDetailVo detail(Long id);

    /**
     * 秒杀活动商品关联新增
     *
     * @param smsSeckillSkuRelationParam 参数
     */
    void add(SmsSeckillSkuRelationParam smsSeckillSkuRelationParam);

    /**
     * 秒杀活动商品关联编辑
     *
     * @param smsSeckillSkuRelationParam 参数
     */
    void edit(SmsSeckillSkuRelationParam smsSeckillSkuRelationParam);

    /**
     * 秒杀活动商品关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
