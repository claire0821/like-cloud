package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * OrderItemVo
 */
@Data
public class OrderItemDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long orderId;  // order_id
    private String orderSn;  // order_sn
    private Long spuId;  // spu_id
    private String spuName;  // spu_name
    private String spuPic;  // spu_pic
    private String spuBrand;  // 品牌
    private Long categoryId;  // 商品分类id
    private Long skuId;  // 商品sku编号
    private String skuName;  // 商品sku名字
    private String skuPic;  // 商品sku图片
    private BigDecimal skuPrice;  // 商品sku价格
    private Integer skuQuantity;  // 商品购买的数量
    private String skuAttrsVals;  // 商品销售属性组合（JSON）
    private BigDecimal promotionAmount;  // 商品促销分解金额
    private BigDecimal couponAmount;  // 优惠券优惠分解金额
    private BigDecimal integrationAmount;  // 积分优惠分解金额
    private BigDecimal realAmount;  // 该商品经过优惠后的分解金额
    private Integer giftIntegration;  // 赠送积分
    private Integer giftGrowth;  // 赠送成长值

}
