package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.OrderReturnReason;
import com.mdd.order.validate.OrderReturnReasonParam;
import com.mdd.order.vo.OrderReturnReasonListVo;
import com.mdd.order.vo.OrderReturnReasonDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 退货原因服务接口类
 */
public interface IOrderReturnReasonService extends IService<OrderReturnReason> {

    /**
     * 退货原因列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderReturnReasonVo>
     */
    PageResult<OrderReturnReasonListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 退货原因详情
     *
     * @param id 主键ID
     * @return OrderReturnReason
     */
    OrderReturnReasonDetailVo detail(Long id);

    /**
     * 退货原因新增
     *
     * @param orderReturnReasonParam 参数
     */
    void add(OrderReturnReasonParam orderReturnReasonParam);

    /**
     * 退货原因编辑
     *
     * @param orderReturnReasonParam 参数
     */
    void edit(OrderReturnReasonParam orderReturnReasonParam);

    /**
     * 退货原因删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
