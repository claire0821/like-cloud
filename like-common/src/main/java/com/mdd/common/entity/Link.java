package com.mdd.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;

/**
 * 导航链接实体
 */
@Data
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; //
    private String type; // 链接类型
    private String name; // 链接名称
    private String path; // 链接地址
    private String image; // 链接图标
    private Date createTime; // 创建时间
    private Date updateTime; // 修改时间

}