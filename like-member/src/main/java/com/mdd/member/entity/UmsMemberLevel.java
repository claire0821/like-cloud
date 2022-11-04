package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员等级实体
 */
@Data
public class UmsMemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 等级名称
    private Long growthPoint; // 等级需要的成长值
    private Long defaultStatus; // 是否为默认等级[0->不是；1->是]
    private BigDecimal freeFreightPoint; // 免运费标准
    private Long commentGrowthPoint; // 每次评价获取的成长值
    private Long priviledgeFreeFreight; // 是否有免邮特权
    private Long priviledgeMemberPrice; // 是否有会员价格特权
    private Long priviledgeBirthday; // 是否有生日特权
    private String note; // 备注

}