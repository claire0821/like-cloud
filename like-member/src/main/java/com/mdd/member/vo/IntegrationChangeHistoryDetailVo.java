package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * IntegrationChangeHistoryVo
 */
@Data
public class IntegrationChangeHistoryDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // member_id
    private Integer changeCount;  // 变化的值
    private String note;  // 备注
    private Integer sourceTyoe;  // 来源[0->购物；1->管理员修改;2->活动]

}
