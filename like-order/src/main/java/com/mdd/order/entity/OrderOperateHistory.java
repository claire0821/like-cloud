package com.mdd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单操作历史记录实体
 */
@Data
public class OrderOperateHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String orderSn; // 订单编号
    private Integer operateManType; // 操作人操作人[1用户；2系统；3后台管理员;4商家]
    private Long operateManId; // 操作人id
    private Integer orderStatus; // 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
    private Integer actionType; // 订单操作
    private String note; // 备注
    private Date createTime; // 操作时间
}