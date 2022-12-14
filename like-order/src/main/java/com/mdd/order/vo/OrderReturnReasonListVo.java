package com.mdd.order.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OrderReturnReasonVo
 */
@Data
public class OrderReturnReasonListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 退货原因名
    private Integer sort;  // 排序
    private Integer status;  // 启用状态
    private String createTime; // create_time

}
