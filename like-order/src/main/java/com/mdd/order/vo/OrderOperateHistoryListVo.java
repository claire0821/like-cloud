package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderOperateHistoryVo
 */
@Data
public class OrderOperateHistoryListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long orderId;  // 订单id
    private String operateMan;  // 操作人[用户；系统；后台管理员]
    private String createTime; // 操作时间
    private Integer orderStatus;  // 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
    private String note;  // 备注

}
