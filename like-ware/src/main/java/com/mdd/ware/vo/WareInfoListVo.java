package com.mdd.ware.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * WareInfoVo
 */
@Data
public class WareInfoListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;  // id
    private String name;  // 仓库名
    private String address;  // 仓库地址
    private String areacode;  // 区域编码

}
