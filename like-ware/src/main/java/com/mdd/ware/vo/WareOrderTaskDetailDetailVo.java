package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * WareOrderTaskDetailVo
 */
@Data
public class WareOrderTaskDetailDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private String skuName;  // sku_name
    private Integer skuNum;  // 购买个数
    private Long taskId;  // 工作单id
    private Long wareId;  // 仓库id
    private Integer lockStatus;  // 1-已锁定  2-已解锁  3-扣减

}
