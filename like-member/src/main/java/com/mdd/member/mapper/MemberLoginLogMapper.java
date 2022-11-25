package com.mdd.member.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.member.entity.MemberLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录Mapper
 */
@Mapper
public interface MemberLoginLogMapper extends IBaseMapper<MemberLoginLog> {
}
