package com.mdd.wave.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 仓库信息实体
 */
@Data
public class WareInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
    private Long id; // id
    private String name; // 仓库名
    private String address; // 仓库地址
    private String areacode; // 区域编码

}