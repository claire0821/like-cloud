package com.mdd.coupon.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * HomeSubjectVo
 */
@Data
public class HomeSubjectListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 专题名字
    private String title;  // 专题标题
    private String subTitle;  // 专题副标题
    private Integer status;  // 显示状态
    private String url;  // 详情连接
    private Integer sort;  // 排序
    private String img;  // 专题图片地址

}
