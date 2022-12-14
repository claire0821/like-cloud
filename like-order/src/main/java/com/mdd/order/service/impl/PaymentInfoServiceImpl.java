package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IPaymentInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.PaymentInfoParam;
import com.mdd.order.vo.PaymentInfoListVo;
import com.mdd.order.vo.PaymentInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.PaymentInfo;
import com.mdd.order.mapper.PaymentInfoMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 支付信息实现类
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper,PaymentInfo> implements IPaymentInfoService {
        
    @Resource
    PaymentInfoMapper paymentInfoMapper;

    /**
     * 支付信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PaymentInfoListVo>
     */
    @Override
    public PageResult<PaymentInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        paymentInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderSn@order_sn:str",
            "=:orderId@order_id:long",
            "=:alipayTradeNo@alipay_trade_no:str",
            "=:totalAmount@total_amount:str",
            "=:subject:str",
            "=:paymentStatus@payment_status:str",
            "=:confirmTime@confirm_time:str",
            "=:callbackContent@callback_content:str",
            "=:callbackTime@callback_time:str",
        });

        IPage<PaymentInfo> iPage = paymentInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PaymentInfoListVo> list = new LinkedList<>();
        for(PaymentInfo item : iPage.getRecords()) {
            PaymentInfoListVo vo = new PaymentInfoListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 支付信息详情
     *
     * @param id 主键参数
     * @return PaymentInfo
     */
    @Override
    public PaymentInfoDetailVo detail(Long id) {
        PaymentInfo model = paymentInfoMapper.selectOne(
                new QueryWrapper<PaymentInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PaymentInfoDetailVo vo = new PaymentInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 支付信息新增
     *
     * @param paymentInfoParam 参数
     */
    @Override
    public void add(PaymentInfoParam paymentInfoParam) {
        PaymentInfo model = new PaymentInfo();
        model.setOrderSn(paymentInfoParam.getOrderSn());
        model.setOrderId(paymentInfoParam.getOrderId());
        model.setAlipayTradeNo(paymentInfoParam.getAlipayTradeNo());
        model.setTotalAmount(paymentInfoParam.getTotalAmount());
        model.setSubject(paymentInfoParam.getSubject());
        model.setPaymentStatus(paymentInfoParam.getPaymentStatus());
        model.setCreateTime(new Date());
        model.setConfirmTime(paymentInfoParam.getConfirmTime());
        model.setCallbackContent(paymentInfoParam.getCallbackContent());
        model.setCallbackTime(paymentInfoParam.getCallbackTime());
        paymentInfoMapper.insert(model);
    }

    /**
     * 支付信息编辑
     *
     * @param paymentInfoParam 参数
     */
    @Override
    public void edit(PaymentInfoParam paymentInfoParam) {
        PaymentInfo model = paymentInfoMapper.selectOne(
                new QueryWrapper<PaymentInfo>()
                    .eq("id",  paymentInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(paymentInfoParam.getId());
        model.setOrderSn(paymentInfoParam.getOrderSn());
        model.setOrderId(paymentInfoParam.getOrderId());
        model.setAlipayTradeNo(paymentInfoParam.getAlipayTradeNo());
        model.setTotalAmount(paymentInfoParam.getTotalAmount());
        model.setSubject(paymentInfoParam.getSubject());
        model.setPaymentStatus(paymentInfoParam.getPaymentStatus());
        model.setConfirmTime(paymentInfoParam.getConfirmTime());
        model.setCallbackContent(paymentInfoParam.getCallbackContent());
        model.setCallbackTime(paymentInfoParam.getCallbackTime());
        paymentInfoMapper.updateById(model);
    }

    /**
     * 支付信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PaymentInfo model = paymentInfoMapper.selectOne(
                new QueryWrapper<PaymentInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        paymentInfoMapper.delete(new QueryWrapper<PaymentInfo>().eq("id", id));
    }

}
