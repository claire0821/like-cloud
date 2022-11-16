package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SeckillSkuNotice;
import com.mdd.coupon.validate.SeckillSkuNoticeParam;
import com.mdd.coupon.vo.SeckillSkuNoticeListVo;
import com.mdd.coupon.vo.SeckillSkuNoticeDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 秒杀商品通知订阅服务接口类
 */
public interface ISeckillSkuNoticeService extends IService<SeckillSkuNotice> {

    /**
     * 秒杀商品通知订阅列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSkuNoticeVo>
     */
    PageResult<SeckillSkuNoticeListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 秒杀商品通知订阅详情
     *
     * @param id 主键ID
     * @return SeckillSkuNotice
     */
    SeckillSkuNoticeDetailVo detail(Long id);

    /**
     * 秒杀商品通知订阅新增
     *
     * @param seckillSkuNoticeParam 参数
     */
    void add(SeckillSkuNoticeParam seckillSkuNoticeParam);

    /**
     * 秒杀商品通知订阅编辑
     *
     * @param seckillSkuNoticeParam 参数
     */
    void edit(SeckillSkuNoticeParam seckillSkuNoticeParam);

    /**
     * 秒杀商品通知订阅删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
