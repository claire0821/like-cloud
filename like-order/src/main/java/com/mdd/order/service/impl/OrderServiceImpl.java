package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderParam;
import com.mdd.order.vo.OrderListVo;
import com.mdd.order.vo.OrderDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.Order;
import com.mdd.order.mapper.OrderMapper;
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
 * 订单实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {
        
    @Resource
    OrderMapper orderMapper;

    /**
     * 订单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderListVo>
     */
    @Override
    public PageResult<OrderListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:orderSn@order_sn:str",
            "=:couponId@coupon_id:long",
            "like:memberUsername@member_username:str",
            "=:totalAmount@total_amount:str",
            "=:payAmount@pay_amount:str",
            "=:freightAmount@freight_amount:str",
            "=:promotionAmount@promotion_amount:str",
            "=:integrationAmount@integration_amount:str",
            "=:couponAmount@coupon_amount:str",
            "=:discountAmount@discount_amount:str",
            "=:payType@pay_type:int",
            "=:sourceType@source_type:int",
            "=:status:int",
            "=:deliveryCompany@delivery_company:str",
            "=:deliverySn@delivery_sn:str",
            "=:autoConfirmDay@auto_confirm_day:int",
            "=:integration:int",
            "=:growth:int",
            "=:billType@bill_type:int",
            "=:billHeader@bill_header:str",
            "=:billContent@bill_content:str",
            "=:billReceiverPhone@bill_receiver_phone:str",
            "=:billReceiverEmail@bill_receiver_email:str",
            "like:receiverName@receiver_name:str",
            "=:receiverPhone@receiver_phone:str",
            "=:receiverPostCode@receiver_post_code:str",
            "=:receiverProvince@receiver_province:str",
            "=:receiverCity@receiver_city:str",
            "=:receiverRegion@receiver_region:str",
            "=:receiverDetailAddress@receiver_detail_address:str",
            "=:note:str",
            "=:confirmStatus@confirm_status:int",
            "=:deleteStatus@delete_status:int",
            "=:useIntegration@use_integration:int",
            "=:paymentTime@payment_time:str",
            "=:deliveryTime@delivery_time:str",
            "=:receiveTime@receive_time:str",
            "=:commentTime@comment_time:str",
            "=:modifyTime@modify_time:str",
        });

        IPage<Order> iPage = orderMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderListVo> list = new LinkedList<>();
        for(Order item : iPage.getRecords()) {
            OrderListVo vo = new OrderListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单详情
     *
     * @param id 主键参数
     * @return Order
     */
    @Override
    public OrderDetailVo detail(Long id) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderDetailVo vo = new OrderDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单新增
     *
     * @param orderParam 参数
     */
    @Override
    public void add(OrderParam orderParam) {
        Order model = new Order();
        model.setMemberId(orderParam.getMemberId());
        model.setOrderSn(orderParam.getOrderSn());
        model.setCouponId(orderParam.getCouponId());
        model.setCreateTime(new Date());
        model.setMemberUsername(orderParam.getMemberUsername());
        model.setTotalAmount(orderParam.getTotalAmount());
        model.setPayAmount(orderParam.getPayAmount());
        model.setFreightAmount(orderParam.getFreightAmount());
        model.setPromotionAmount(orderParam.getPromotionAmount());
        model.setIntegrationAmount(orderParam.getIntegrationAmount());
        model.setCouponAmount(orderParam.getCouponAmount());
        model.setDiscountAmount(orderParam.getDiscountAmount());
        model.setPayType(orderParam.getPayType());
        model.setSourceType(orderParam.getSourceType());
        model.setStatus(orderParam.getStatus());
        model.setDeliveryCompany(orderParam.getDeliveryCompany());
        model.setDeliverySn(orderParam.getDeliverySn());
        model.setAutoConfirmDay(orderParam.getAutoConfirmDay());
        model.setIntegration(orderParam.getIntegration());
        model.setGrowth(orderParam.getGrowth());
        model.setBillType(orderParam.getBillType());
        model.setBillHeader(orderParam.getBillHeader());
        model.setBillContent(orderParam.getBillContent());
        model.setBillReceiverPhone(orderParam.getBillReceiverPhone());
        model.setBillReceiverEmail(orderParam.getBillReceiverEmail());
        model.setReceiverName(orderParam.getReceiverName());
        model.setReceiverPhone(orderParam.getReceiverPhone());
        model.setReceiverPostCode(orderParam.getReceiverPostCode());
        model.setReceiverProvince(orderParam.getReceiverProvince());
        model.setReceiverCity(orderParam.getReceiverCity());
        model.setReceiverRegion(orderParam.getReceiverRegion());
        model.setReceiverDetailAddress(orderParam.getReceiverDetailAddress());
        model.setNote(orderParam.getNote());
        model.setConfirmStatus(orderParam.getConfirmStatus());
        model.setDeleteStatus(orderParam.getDeleteStatus());
        model.setUseIntegration(orderParam.getUseIntegration());
        model.setPaymentTime(orderParam.getPaymentTime());
        model.setDeliveryTime(orderParam.getDeliveryTime());
        model.setReceiveTime(orderParam.getReceiveTime());
        model.setCommentTime(orderParam.getCommentTime());
        model.setModifyTime(orderParam.getModifyTime());
        orderMapper.insert(model);
    }

    /**
     * 订单编辑
     *
     * @param orderParam 参数
     */
    @Override
    public void edit(OrderParam orderParam) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id",  orderParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderParam.getId());
        model.setMemberId(orderParam.getMemberId());
        model.setOrderSn(orderParam.getOrderSn());
        model.setCouponId(orderParam.getCouponId());
        model.setMemberUsername(orderParam.getMemberUsername());
        model.setTotalAmount(orderParam.getTotalAmount());
        model.setPayAmount(orderParam.getPayAmount());
        model.setFreightAmount(orderParam.getFreightAmount());
        model.setPromotionAmount(orderParam.getPromotionAmount());
        model.setIntegrationAmount(orderParam.getIntegrationAmount());
        model.setCouponAmount(orderParam.getCouponAmount());
        model.setDiscountAmount(orderParam.getDiscountAmount());
        model.setPayType(orderParam.getPayType());
        model.setSourceType(orderParam.getSourceType());
        model.setStatus(orderParam.getStatus());
        model.setDeliveryCompany(orderParam.getDeliveryCompany());
        model.setDeliverySn(orderParam.getDeliverySn());
        model.setAutoConfirmDay(orderParam.getAutoConfirmDay());
        model.setIntegration(orderParam.getIntegration());
        model.setGrowth(orderParam.getGrowth());
        model.setBillType(orderParam.getBillType());
        model.setBillHeader(orderParam.getBillHeader());
        model.setBillContent(orderParam.getBillContent());
        model.setBillReceiverPhone(orderParam.getBillReceiverPhone());
        model.setBillReceiverEmail(orderParam.getBillReceiverEmail());
        model.setReceiverName(orderParam.getReceiverName());
        model.setReceiverPhone(orderParam.getReceiverPhone());
        model.setReceiverPostCode(orderParam.getReceiverPostCode());
        model.setReceiverProvince(orderParam.getReceiverProvince());
        model.setReceiverCity(orderParam.getReceiverCity());
        model.setReceiverRegion(orderParam.getReceiverRegion());
        model.setReceiverDetailAddress(orderParam.getReceiverDetailAddress());
        model.setNote(orderParam.getNote());
        model.setConfirmStatus(orderParam.getConfirmStatus());
        model.setDeleteStatus(orderParam.getDeleteStatus());
        model.setUseIntegration(orderParam.getUseIntegration());
        model.setPaymentTime(orderParam.getPaymentTime());
        model.setDeliveryTime(orderParam.getDeliveryTime());
        model.setReceiveTime(orderParam.getReceiveTime());
        model.setCommentTime(orderParam.getCommentTime());
        model.setModifyTime(orderParam.getModifyTime());
        orderMapper.updateById(model);
    }

    /**
     * 订单删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderMapper.delete(new QueryWrapper<Order>().eq("id", id));
    }

}
