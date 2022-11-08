package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillSkuNoticeParam;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SmsSeckillSkuNoticeDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀商品通知订阅服务接口类
 */
public interface ISmsSeckillSkuNoticeService {

    /**
     * 秒杀商品通知订阅列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSkuNoticeVo>
     */
    PageResult<SmsSeckillSkuNoticeListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键ID
     * @return SmsSeckillSkuNotice
     */
    SmsSeckillSkuNoticeDetailVo detail(Long id);

    /**
     * 秒杀商品通知订阅新增
     *
     * @param smsSeckillSkuNoticeParam 参数
     */
    void add(SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam);

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param smsSeckillSkuNoticeParam 参数
     */
    void edit(SmsSeckillSkuNoticeParam smsSeckillSkuNoticeParam);

    /**
     * 秒杀商品通知订阅删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
