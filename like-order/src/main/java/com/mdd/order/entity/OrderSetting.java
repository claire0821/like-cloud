package com.mdd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单配置信息实体
 */
@Data
public class OrderSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Integer flashOrderOvertime; // 秒杀订单超时关闭时间(分)
    private Integer normalOrderOvertime; // 正常订单超时时间(分)
    private Integer confirmOvertime; // 发货后自动确认收货时间（天）
    private Integer finishOvertime; // 自动完成交易时间，不能申请退货（天）
    private Integer commentOvertime; // 订单完成后自动好评时间（天）
    private Integer memberLevel; // 会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】

}