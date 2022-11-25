package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * GrowthChangeHistoryVo
 */
@Data
public class GrowthChangeHistoryDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private Integer changeCount;  // 改变的值（正负计数）
    private String note;  // 备注
    private Integer sourceType;  // 积分来源[0-购物，1-管理员修改]

}
