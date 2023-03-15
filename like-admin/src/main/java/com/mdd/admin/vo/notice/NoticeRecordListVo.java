package com.mdd.admin.vo.notice;

import lombok.Data;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-09 18:00
 **/
@Data
public class NoticeRecordListVo {
    private String title;
    private String content;
    private String img;
    private Integer type;
}
