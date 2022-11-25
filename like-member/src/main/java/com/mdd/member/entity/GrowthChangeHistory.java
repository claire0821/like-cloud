package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 成长值变化历史记录实体
 */
@Data
public class GrowthChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // member_id
    private Date createTime; // create_time
    private Integer changeCount; // 改变的值（正负计数）
    private String note; // 备注
    private Integer sourceType; // 积分来源[0-购物，1-管理员修改]

}