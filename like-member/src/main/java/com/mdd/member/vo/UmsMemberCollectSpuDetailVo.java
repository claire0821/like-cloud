package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * UmsMemberCollectSpuVo
 */
@Data
public class UmsMemberCollectSpuDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // 会员id
    private Long spuId;  // spu_id
    private String spuName;  // spu_name
    private String spuImg;  // spu_img

}
