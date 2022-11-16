package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SeckillPromotion;
import com.mdd.coupon.validate.SeckillPromotionParam;
import com.mdd.coupon.vo.SeckillPromotionListVo;
import com.mdd.coupon.vo.SeckillPromotionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀活动服务接口类
 */
public interface ISeckillPromotionService extends IService<SeckillPromotion> {

    /**
     * 秒杀活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillPromotionVo>
     */
    PageResult<SeckillPromotionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动详情
     *
     * @param id 主键ID
     * @return SeckillPromotion
     */
    SeckillPromotionDetailVo detail(Long id);

    /**
     * 秒杀活动新增
     *
     * @param seckillPromotionParam 参数
     */
    void add(SeckillPromotionParam seckillPromotionParam);

    /**
     * 秒杀活动编辑
     *
     * @param seckillPromotionParam 参数
     */
    void edit(SeckillPromotionParam seckillPromotionParam);

    /**
     * 秒杀活动删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
