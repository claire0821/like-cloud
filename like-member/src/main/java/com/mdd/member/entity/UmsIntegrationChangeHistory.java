package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分变化历史记录实体
 */
@Data
public class UmsIntegrationChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // member_id
    private Date createTime; // create_time
    private Long changeCount; // 变化的值
    private String note; // 备注
    private Long sourceTyoe; // 来源[0->购物；1->管理员修改;2->活动]

}