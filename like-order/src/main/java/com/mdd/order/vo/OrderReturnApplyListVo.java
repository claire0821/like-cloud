package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OrderReturnApplyVo
 */
@Data
public class OrderReturnApplyListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long orderId;  // order_id
    private Long skuId;  // 退货商品id
    private String orderSn;  // 订单编号
    private String createTime; // 申请时间
    private String memberUsername;  // 会员用户名
    private BigDecimal returnAmount;  // 退款金额
    private String returnName;  // 退货人姓名
    private String returnPhone;  // 退货人电话
    private Integer status;  // 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
    private Date handleTime;  // 处理时间
    private String skuImg;  // 商品图片
    private String skuName;  // 商品名称
    private String skuBrand;  // 商品品牌
    private String skuAttrsVals;  // 商品销售属性(JSON)
    private Integer skuCount;  // 退货数量
    private BigDecimal skuPrice;  // 商品单价
    private BigDecimal skuRealPrice;  // 商品实际支付单价
    private String reason;  // 原因
    private String description述;  // 描述
    private String descPics;  // 凭证图片，以逗号隔开
    private String handleNote;  // 处理备注
    private String handleMan;  // 处理人员
    private String receiveMan;  // 收货人
    private Date receiveTime;  // 收货时间
    private String receiveNote;  // 收货备注
    private String receivePhone;  // 收货电话
    private String companyAddress;  // 公司收货地址

}
