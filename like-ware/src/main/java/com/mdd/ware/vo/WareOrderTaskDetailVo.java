package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * WareOrderTaskVo
 */
@Data
public class WareOrderTaskDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long orderId;  // order_id
    private String orderSn;  // order_sn
    private String consignee;  // 收货人
    private String consigneeTel;  // 收货人电话
    private String deliveryAddress;  // 配送地址
    private String orderComment;  // 订单备注
    private Integer paymentWay;  // 付款方式【 1:在线付款 2:货到付款】
    private Integer taskStatus;  // 任务状态
    private String orderBody;  // 订单描述
    private String trackingNo;  // 物流单号
    private Long wareId;  // 仓库id
    private String taskComment;  // 工作单备注

}
