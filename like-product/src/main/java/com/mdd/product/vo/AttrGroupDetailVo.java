package com.mdd.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mdd.product.entity.Attr;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PmsAttrGroupVo
 */
@Data
public class AttrGroupDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long attrGroupId;  // 分组id
    private String attrGroupName;  // 组名
    private Long sort;  // 排序
    private String descript;  // 描述
    private String icon;  // 组图标
    private Long catelogId;  // 所属分类id

    @JsonInclude(JsonInclude.Include.NON_EMPTY)//不为空才返回
    private List<Attr> attrs;

}
