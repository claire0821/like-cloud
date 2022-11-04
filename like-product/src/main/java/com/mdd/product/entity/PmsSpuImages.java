package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * spu图片实体
 */
@Data
public class PmsSpuImages implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long spuId; // spu_id
    private String imgName; // 图片名
    private String imgUrl; // 图片地址
    private Long imgSort; // 顺序
    private Long defaultImg; // 是否默认图

}