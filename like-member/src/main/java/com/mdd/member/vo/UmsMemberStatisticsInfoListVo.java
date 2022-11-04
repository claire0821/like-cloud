package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * UmsMemberStatisticsInfoVo
 */
@Data
public class UmsMemberStatisticsInfoListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // 会员id
    private BigDecimal consumeAmount;  // 累计消费金额
    private BigDecimal couponAmount;  // 累计优惠金额
    private Long orderCount;  // 订单数量
    private Long couponCount;  // 优惠券数量
    private Long commentCount;  // 评价数
    private Long returnOrderCount;  // 退货数量
    private Long loginCount;  // 登录次数
    private Long attendCount;  // 关注数量
    private Long fansCount;  // 粉丝数量
    private Long collectProductCount;  // 收藏的商品数量
    private Long collectSubjectCount;  // 收藏的专题活动数量
    private Long collectCommentCount;  // 收藏的评论数量
    private Long inviteFriendCount;  // 邀请的朋友数量

}
