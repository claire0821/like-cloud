package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.OrderSetting;
import com.mdd.order.validate.OrderSettingParam;
import com.mdd.order.vo.OrderSettingListVo;
import com.mdd.order.vo.OrderSettingDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 订单配置信息服务接口类
 */
public interface IOrderSettingService extends IService<OrderSetting> {

    /**
     * 订单配置信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderSettingVo>
     */
    PageResult<OrderSettingListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 订单配置信息详情
     *
     * @param id 主键ID
     * @return OrderSetting
     */
    OrderSettingDetailVo detail(Long id);

    /**
     * 订单配置信息新增
     *
     * @param orderSettingParam 参数
     */
    void add(OrderSettingParam orderSettingParam);

    /**
     * 订单配置信息编辑
     *
     * @param orderSettingParam 参数
     */
    void edit(OrderSettingParam orderSettingParam);

    /**
     * 订单配置信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
