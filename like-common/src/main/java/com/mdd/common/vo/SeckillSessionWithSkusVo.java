package com.mdd.common.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-05 16:22
 **/
@Data
public class SeckillSessionWithSkusVo {
    private Long id;
    /**
     * 场次名称
     */
    private String name;
    /**
     * 每日开始时间
     */
    private Date startTime;
    /**
     * 每日结束时间
     */
    private Date endTime;
    /**
     * 启用状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    private List<SeckillSkuVo> relationSkus;
}
