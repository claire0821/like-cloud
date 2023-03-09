package com.mdd.common.vo;

import lombok.Data;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-01-11 10:34
 **/
@Data
public class UserVo {
    Long id;
    Integer type;//OperateManTypeEnum 用户类型
}
