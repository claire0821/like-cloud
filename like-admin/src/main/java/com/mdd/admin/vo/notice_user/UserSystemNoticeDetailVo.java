package com.mdd.admin.vo.notice_user;

import lombok.Data;

import java.io.Serializable;

/**
 * UserSystemNoticeVo
 * @author Claire
 */
@Data
public class UserSystemNoticeDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // 列主键
    private Integer readState;  // 读取状态 1-已读 0-未读
    private Integer pushState;  // 推送状态1-已推送 0-未推送
    private Integer systemNoticeId;  // 系统通知ID
    private Long readTime;  // 读取时间
    private Long pullTime;  // 拉取通知时间

}
