package com.mdd.admin.vo.link;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * LinkVo
 */
@Data
public class LinkListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  //
    private String type;  // 链接类型
    private String name;  // 链接名称
    private String path;  // 链接地址
    private String image;  // 链接图标
    private String createTime; // 创建时间
    private String updateTime; // 修改时间

}
