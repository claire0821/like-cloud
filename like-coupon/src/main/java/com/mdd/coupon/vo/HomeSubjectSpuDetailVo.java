package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * HomeSubjectSpuVo
 */
@Data
public class HomeSubjectSpuDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 专题名字
    private Long subjectId;  // 专题id
    private Long spuId;  // spu_id
    private Integer sort;  // 排序

}
