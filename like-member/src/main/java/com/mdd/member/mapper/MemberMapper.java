package com.mdd.member.mapper;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员Mapper
 */
@Mapper
public interface MemberMapper extends IBaseMapper<Member> {
}
