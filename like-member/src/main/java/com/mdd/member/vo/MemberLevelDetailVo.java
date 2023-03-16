package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MemberLevelVo
 */
@Data
public class MemberLevelDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 等级名称
    private Integer growthPoint;  // 等级需要的成长值
    private Integer defaultStatus;  // 是否为默认等级[0->不是；1->是]
    private BigDecimal freeFreightPoint;  // 免运费标准
    private Integer commentGrowthPoint;  // 每次评价获取的成长值
    private Integer priviledgeFreeFreight;  // 是否有免邮特权
    private Integer priviledgeMemberPrice;  // 是否有会员价格特权
    private Integer priviledgeBirthday;  // 是否有生日特权
    private String note;  // 备注
    private String image;//等级图标
    private String backgroundImage;//背景图片
}
