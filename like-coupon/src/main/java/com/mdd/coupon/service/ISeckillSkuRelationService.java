package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SeckillSkuRelation;
import com.mdd.coupon.validate.SeckillSkuRelationParam;
import com.mdd.coupon.vo.SeckillSkuRelationListVo;
import com.mdd.coupon.vo.SeckillSkuRelationDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀活动商品关联服务接口类
 */
public interface ISeckillSkuRelationService extends IService<SeckillSkuRelation> {

    /**
     * 秒杀活动商品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSkuRelationVo>
     */
    PageResult<SeckillSkuRelationListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动商品关联详情
     *
     * @param id 主键ID
     * @return SeckillSkuRelation
     */
    SeckillSkuRelationDetailVo detail(Long id);

    /**
     * 秒杀活动商品关联新增
     *
     * @param seckillSkuRelationParam 参数
     */
    void add(SeckillSkuRelationParam seckillSkuRelationParam);

    /**
     * 秒杀活动商品关联编辑
     *
     * @param seckillSkuRelationParam 参数
     */
    void edit(SeckillSkuRelationParam seckillSkuRelationParam);

    /**
     * 秒杀活动商品关联删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
