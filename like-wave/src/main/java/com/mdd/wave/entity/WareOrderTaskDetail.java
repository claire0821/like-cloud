package com.mdd.wave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 库存工作单实体
 */
@Data
public class WareOrderTaskDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // sku_id
    private String skuName; // sku_name
    private Integer skuNum; // 购买个数
    private Long taskId; // 工作单id
    private Long wareId; // 仓库id
    private Integer lockStatus; // 1-已锁定  2-已解锁  3-扣减

}