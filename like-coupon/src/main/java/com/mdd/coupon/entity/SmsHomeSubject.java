package com.mdd.coupon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】实体
 */
@Data
public class SmsHomeSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 专题名字
    private String title; // 专题标题
    private String subTitle; // 专题副标题
    private Integer status; // 显示状态
    private String url; // 详情连接
    private Long sort; // 排序
    private String img; // 专题图片地址

}