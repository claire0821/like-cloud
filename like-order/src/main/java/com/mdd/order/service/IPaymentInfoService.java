package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.PaymentInfo;
import com.mdd.order.validate.PaymentInfoParam;
import com.mdd.order.vo.PaymentInfoListVo;
import com.mdd.order.vo.PaymentInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 支付信息服务接口类
 */
public interface IPaymentInfoService extends IService<PaymentInfo> {

    /**
     * 支付信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PaymentInfoVo>
     */
    PageResult<PaymentInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 支付信息详情
     *
     * @param id 主键ID
     * @return PaymentInfo
     */
    PaymentInfoDetailVo detail(Long id);

    /**
     * 支付信息新增
     *
     * @param paymentInfoParam 参数
     */
    void add(PaymentInfoParam paymentInfoParam);

    /**
     * 支付信息编辑
     *
     * @param paymentInfoParam 参数
     */
    void edit(PaymentInfoParam paymentInfoParam);

    /**
     * 支付信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
