package com.mdd.common.mapper.notice;

import com.mdd.common.core.basics.IBaseMapper;
import com.mdd.common.entity.notice.NoticeRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息通知记录表Mapper
 */
@Mapper
public interface NoticeRecordMapper extends IBaseMapper<NoticeRecord> {
}
