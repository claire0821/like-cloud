package com.mdd.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品库存实体
 */
@Data
public class WareSku implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long skuId; // sku_id
    private Long wareId; // 仓库id
    private Integer stock; // 库存数
    private String skuName; // sku_name
    private Integer stockLocked; // 锁定库存

}