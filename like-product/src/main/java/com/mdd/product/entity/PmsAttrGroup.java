package com.mdd.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 属性分组实体
 */
@Data
public class PmsAttrGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="attr_group_id", type= IdType.AUTO)
    private Long attrGroupId; // 分组id
    private String attrGroupName; // 组名
    private Long sort; // 排序
    private String descript; // 描述
    private String icon; // 组图标
    private Long catelogId; // 所属分类id

}