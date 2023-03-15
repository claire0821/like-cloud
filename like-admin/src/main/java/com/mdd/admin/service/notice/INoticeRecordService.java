package com.mdd.admin.service.notice;

import com.mdd.admin.vo.notice.NoticeRecordListVo;
import com.mdd.admin.vo.notice_user.UserSystemNoticeListVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.validate.PageParam;

import java.util.List;

public interface INoticeRecordService {
    List<NoticeRecordListVo> listNotice();

    PageResult<NoticeRecord> listNoticeByType(PageParam pageParam, Integer type);
}
