package com.mdd.order.to;

import com.mdd.common.vo.CartItemVo;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.order.entity.Order;
import com.mdd.order.entity.OrderItem;
import lombok.Data;

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
     * 收货地址
     */
    private MemberReceiveAddressVo address;
    /**
     * 购物车选中的商品
     */
    private List<CartItemVo> cartItemVos;

    private List<OrderItem> orderItems;

    /** 订单计算的应付价格 **/
    private BigDecimal payPrice;

    /** 运费 **/
    private BigDecimal fare;
}
