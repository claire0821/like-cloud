package com.mdd.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 退货原因实体
 */
@Data
public class OrderReturnReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 退货原因名
    private Integer sort; // 排序
    private Integer status; // 启用状态
    private Date createTime; // create_time

}