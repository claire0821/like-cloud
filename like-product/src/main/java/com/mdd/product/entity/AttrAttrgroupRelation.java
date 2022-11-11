package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 属性&属性分组关联实体
 */
@Data
public class AttrAttrgroupRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long attrId; // 属性id
    private Long attrGroupId; // 属性分组id
    private Integer attrSort; // 属性组内排序

}