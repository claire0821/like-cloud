package com.mdd.product.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * PmsAttrGroupVo
 */
@Data
public class AttrGroupListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long attrGroupId;  // 分组id
    private String attrGroupName;  // 组名
    private Integer sort;  // 排序
    private String descript;  // 描述
    private String icon;  // 组图标
    private Long catelogId;  // 所属分类id

    //完整分类路径
    private Long[] catelogPath;
}
