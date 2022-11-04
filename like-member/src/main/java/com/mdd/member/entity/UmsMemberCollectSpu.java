package com.mdd.member.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员收藏的商品实体
 */
@Data
public class UmsMemberCollectSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private Long memberId; // 会员id
    private Long spuId; // spu_id
    private String spuName; // spu_name
    private String spuImg; // spu_img
    private Date createTime; // create_time

}