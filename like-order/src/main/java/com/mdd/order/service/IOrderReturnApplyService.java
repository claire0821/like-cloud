package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.OrderReturnApply;
import com.mdd.order.validate.OrderReturnApplyParam;
import com.mdd.order.vo.OrderReturnApplyListVo;
import com.mdd.order.vo.OrderReturnApplyDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 订单退货申请服务接口类
 */
public interface IOrderReturnApplyService extends IService<OrderReturnApply> {

    /**
     * 订单退货申请列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderReturnApplyVo>
     */
    PageResult<OrderReturnApplyListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 订单退货申请详情
     *
     * @param id 主键ID
     * @return OrderReturnApply
     */
    OrderReturnApplyDetailVo detail(Long id);

    /**
     * 订单退货申请新增
     *
     * @param orderReturnApplyParam 参数
     */
    void add(OrderReturnApplyParam orderReturnApplyParam);

    /**
     * 订单退货申请编辑
     *
     * @param orderReturnApplyParam 参数
     */
    void edit(OrderReturnApplyParam orderReturnApplyParam);

    /**
     * 订单退货申请删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
