package com.mdd.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * SpuCommentVo
 */
@Data
public class SpuCommentListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long skuId;  // sku_id
    private Long spuId;  // spu_id
    private String spuName;  // 商品名字
    private String memberNickName;  // 会员昵称
    private Integer star;  // 星级
    private String memberIp;  // 会员ip
    private String createTime; // 创建时间
    private Integer showStatus;  // 显示状态[0-不显示，1-显示]
    private String spuAttributes;  // 购买时属性组合
    private Integer likesCount;  // 点赞数
    private Integer replyCount;  // 回复数
    private String resources;  // 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
    private String memberIcon;  // 用户头像
    private Integer commentType;  // 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]

}
