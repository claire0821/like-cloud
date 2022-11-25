package com.mdd.member.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigDecimal;

/**
 * MemberStatisticsInfoVo
 */
@Data
public class MemberStatisticsInfoListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private Long memberId;  // 会员id
    private BigDecimal consumeAmount;  // 累计消费金额
    private BigDecimal couponAmount;  // 累计优惠金额
    private Integer orderCount;  // 订单数量
    private Integer couponCount;  // 优惠券数量
    private Integer commentCount;  // 评价数
    private Integer returnOrderCount;  // 退货数量
    private Integer loginCount;  // 登录次数
    private Integer attendCount;  // 关注数量
    private Integer fansCount;  // 粉丝数量
    private Integer collectProductCount;  // 收藏的商品数量
    private Integer collectSubjectCount;  // 收藏的专题活动数量
    private Integer collectCommentCount;  // 收藏的评论数量
    private Integer inviteFriendCount;  // 邀请的朋友数量

}
