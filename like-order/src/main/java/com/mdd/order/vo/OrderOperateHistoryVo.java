package com.mdd.order.vo;

import com.mdd.common.constant.OrderConstant;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderOperateHistoryVo
 */
@Data
public class OrderOperateHistoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String orderSn; // 订单编号
    private Integer operateManType; // 操作人[1用户；2系统；3后台管理员;4商家]
    private Long operateManId; // 操作人id
    private String operateMan; // 操作人昵称
    private Integer orderStatus; // 订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】
    private Integer actionType; // 订单操作
    private String note; // 备注
    private String createTime; // 操作时间

    public String getTitle() {
        String str = "";
        if(operateManType.equals(OrderConstant.OperateManTypeEnum.USER.getCode())) {
            str = "用户" + operateMan + OrderConstant.getActionTypeEnumMsg(this.actionType) + "(" + createTime + ")";
        } else if(operateManType.equals(OrderConstant.OperateManTypeEnum.SYSTEM.getCode())) {
            str = "系统" + OrderConstant.getActionTypeEnumMsg(this.actionType) + "(" + createTime + ")";
        } else if(operateManType.equals(OrderConstant.OperateManTypeEnum.ADMINISTRATORS.getCode())) {
            str = "管理员" + OrderConstant.getActionTypeEnumMsg(this.actionType) + "(" + createTime + ")";
        } else if(operateManType.equals(OrderConstant.OperateManTypeEnum.BUSINESS.getCode())) {
            str = "商家" + OrderConstant.getActionTypeEnumMsg(this.actionType) + "(" + createTime + ")";
        }
        return str;
    }
}
