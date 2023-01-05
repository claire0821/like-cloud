package com.mdd.order.to;

import com.mdd.common.vo.CartItemVo;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.common.vo.MemberVo;
import com.mdd.order.entity.Order;
import com.mdd.order.entity.OrderItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2022-12-14 15:19
 **/
@Data
public class OrderCreateTo {
    /**
     * 会员详情
     */
    private MemberVo member;

    /**
     * 收货地址
     */
    private MemberReceiveAddressVo address;
    /**
     * 购物车选中的商品
     */
    private List<CartItemVo> cartItems;
    /**
     * 购物车选中的商品数量
     */
    private Integer productCount;
    /** 订单备注 **/
    private String note;

    /**
     * 订单总额
     */
    private BigDecimal totalAmount;
    /** 订单计算的应付价格 **/
    private BigDecimal payAmount;

    /** 运费 **/
    private BigDecimal freightAmount;

    /** 促销优化金额（促销价、满减、阶梯价） **/
    private BigDecimal promotionAmount;

    /** 积分抵扣金额 **/
    private BigDecimal integrationAmount;

    /** 优惠券抵扣金额 **/
    private BigDecimal couponAmount;

    /** 后台调整订单使用的折扣金额 **/
    private BigDecimal discountAmount;

    /** 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】 **/
    private Integer payType;

    /** 订单来源[0->PC订单；1->app订单] **/
    private Integer sourceType;

    /** 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】 **/
    private Integer status;
    private String deliveryCompany;  // 物流公司(配送方式)
    private String deliverySn;  // 物流单号

    private Integer integration;  // 可以获得的积分
    private Integer growth;  // 可以获得的成长值
    private Integer billType;  // 发票类型[0->不开发票；1->电子发票；2->纸质发票]
    private String billHeader;  // 发票抬头
    private String billContent;  // 发票内容


    private Integer useIntegration;  // 下单时使用的积分

    /**
     * 能否使用积分
     */
    private boolean integralUse;

    /** 防止重复提交的令牌 **/
    private String orderToken;

    private String orderSn;  // 订单号


}
