package com.mdd.order.vo;

import com.mdd.common.vo.CartItemVo;
import com.mdd.order.entity.OrderItem;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

/**
 * OrderVo
 */
@Data
public class OrderListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private String memberUsername;  // 用户名
    private String memberAvatar;  // 头像
    private Long memberLevelId;  // 会员等级
    private String memberLevelName;  // 会员等级
    private String orderSn;  // 订单号
    private Long couponId;  // 使用的优惠券
    private String createTime; // create_time

    private BigDecimal totalAmount;  // 订单总额
    private BigDecimal payAmount;  // 应付总额
    private BigDecimal freightAmount;  // 运费金额
    private BigDecimal promotionAmount;  // 促销优化金额（促销价、满减、阶梯价）
    private BigDecimal integrationAmount;  // 积分抵扣金额
    private BigDecimal couponAmount;  // 优惠券抵扣金额
    private BigDecimal discountAmount;  // 后台调整订单使用的折扣金额
    private Integer payType;  // 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】
    private String strPayType;  // 支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】
    private Integer sourceType;  // 订单来源[0->PC订单；1->app订单]
    private String strSourceType;  // 订单来源[0->PC订单；1->app订单]
    private Integer status;  // OrderStatusEnum 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
    private String strStatus;  // 订单状态
    private String deliveryCompany;  // 物流公司(配送方式)
    private String deliverySn;  // 物流单号
    private Integer autoConfirmDay;  // 自动确认时间（天）
    private Integer integration;  // 可以获得的积分
    private Integer growth;  // 可以获得的成长值
    private Integer billType;  // 发票类型[0->不开发票；1->电子发票；2->纸质发票]
    private String billHeader;  // 发票抬头
    private String billContent;  // 发票内容
    private String billReceiverPhone;  // 收票人电话
    private String billReceiverEmail;  // 收票人邮箱
    private String receiverName;  // 收货人姓名
    private String receiverPhone;  // 收货人电话
    private String receiverPostCode;  // 收货人邮编
    private String receiverProvince;  // 省份/直辖市
    private String receiverCity;  // 城市
    private String receiverRegion;  // 区
    private String receiverDetailAddress;  // 详细地址
    private String note;  // 订单备注
    private Integer confirmStatus;  // 确认收货状态[0->未确认；1->已确认]
    private String strConfirmStatus;  // 确认收货状态
    private Integer deleteStatus;  // 删除状态【0->未删除；1->已删除】
    private Integer useIntegration;  // 下单时使用的积分
    private Date paymentTime;  // 支付时间
    private Date deliveryTime;  // 发货时间
    private Date receiveTime;  // 确认收货时间
    private Date commentTime;  // 评价时间
    private Date modifyTime;  // 修改时间

    private Integer productCount;//商品个数
    private List<CartItemVo> orderItems;  // 商品
    private Integer type;  //TypeEnum 订单类型
    private String strType;  //TypeEnum 订单类型
    private Integer deliveryType;  //DeliveryTypeEnum 配送方式
    private Integer strDeliveryType;  //DeliveryTypeEnum 配送方式
}
