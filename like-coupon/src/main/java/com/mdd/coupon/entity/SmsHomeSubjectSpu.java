package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 专题商品实体
 */
@Data
public class SmsHomeSubjectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 专题名字
    private Long subjectId; // 专题id
    private Long spuId; // spu_id
    private Long sort; // 排序

}