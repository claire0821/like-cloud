package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * PmsCommentReplayVo
 */
@Data
public class PmsCommentReplayListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long commentId;  // 评论id
    private Long replyId;  // 回复id

}
