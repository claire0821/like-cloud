package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillPromotionParam;
import com.mdd.coupon.vo.SmsSeckillPromotionListVo;
import com.mdd.coupon.vo.SmsSeckillPromotionDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀活动服务接口类
 */
public interface ISmsSeckillPromotionService {

    /**
     * 秒杀活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillPromotionVo>
     */
    PageResult<SmsSeckillPromotionListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀活动详情
     *
     * @param id 主键ID
     * @return SmsSeckillPromotion
     */
    SmsSeckillPromotionDetailVo detail(Long id);

    /**
     * 秒杀活动新增
     *
     * @param smsSeckillPromotionParam 参数
     */
    void add(SmsSeckillPromotionParam smsSeckillPromotionParam);

    /**
     * 秒杀活动编辑
     *
     * @param smsSeckillPromotionParam 参数
     */
    void edit(SmsSeckillPromotionParam smsSeckillPromotionParam);

    /**
     * 秒杀活动删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
