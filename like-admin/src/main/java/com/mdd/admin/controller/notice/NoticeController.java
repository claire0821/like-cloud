package com.mdd.admin.controller.notice;

import com.mdd.admin.service.notice.INoticeRecordService;
import com.mdd.admin.vo.notice.NoticeRecordListVo;
import com.mdd.admin.vo.notice_user.UserSystemNoticeListVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.validate.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-09 17:41
 **/
@RestController
@RequestMapping("api/notice")
public class NoticeController {

    @Autowired
    INoticeRecordService iNoticeRecordService;
    /**
     * 获取通知列表
     *
     * @author Claire
     * @return Object
     */
    @GetMapping("/listNotice")
    public AjaxResult<List<NoticeRecordListVo>> listNotice() {
        List<NoticeRecordListVo> list = iNoticeRecordService.listNotice();
        return AjaxResult.success(list);
    }

    /**
     * 获取通知列表
     *
     * @author Claire
     * @return Object
     */
    @GetMapping("/listNoticeByType")
    public AjaxResult<PageResult<NoticeRecord>> listNoticeByType(@Validated PageParam pageParam,
                                                     @RequestParam("type") Integer type) {
        PageResult<NoticeRecord> list = iNoticeRecordService.listNoticeByType(pageParam,type);
        return AjaxResult.success(list);
    }
}
