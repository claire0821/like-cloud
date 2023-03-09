package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.OrderOperateHistory;
import com.mdd.order.validate.OrderOperateHistoryParam;
import com.mdd.order.vo.OrderOperateHistoryVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 订单操作历史记录服务接口类
 */
public interface IOrderOperateHistoryService extends IService<OrderOperateHistory> {

    /**
     * 订单操作历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderOperateHistoryVo>
     */
    PageResult<OrderOperateHistoryVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 订单操作历史记录详情
     *
     * @param id 主键ID
     * @return OrderOperateHistory
     */
    OrderOperateHistoryVo detail(Long id);

    /**
     * 订单操作历史记录新增
     *
     * @param orderOperateHistoryParam 参数
     */
    void add(OrderOperateHistoryParam orderOperateHistoryParam);

    /**
     * 订单操作历史记录编辑
     *
     * @param orderOperateHistoryParam 参数
     */
    void edit(OrderOperateHistoryParam orderOperateHistoryParam);

    /**
     * 订单操作历史记录删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    List<OrderOperateHistoryVo> listByOrder(Long orderSn);

    void add(String orderSn, Integer actionType, String note);
}
