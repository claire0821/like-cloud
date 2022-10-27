package com.mdd.common.entity.notice_user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户系统通知实体
 * @author Claire
 */
@Data
public class UserSystemNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Integer id; // 列主键
    private Integer readState; // 读取状态 1-已读 0-未读
    private Integer pushState; // 推送状态1-已推送 0-未推送
    private Integer systemNoticeId; // 系统通知ID
    private Long readTime; // 读取时间
    private Long pullTime; // 拉取通知时间
    private Long createTime; // 创建时间
    private Long updateTime; // 更新时间
    private Long deleteTime; // 删除时间

}