package com.mdd.admin.service.notice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.service.notice.INoticeRecordService;
import com.mdd.admin.vo.article.ArticleListVo;
import com.mdd.admin.vo.notice.NoticeRecordListVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.NoticeConstant;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.article.Article;
import com.mdd.common.entity.notice.NoticeRecord;
import com.mdd.common.mapper.notice.NoticeRecordMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.validate.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: server
 * @description:
 * @author: Claire
 * @create: 2023-03-09 17:44
 **/
@Service
public class NoticeRecordServiceRecordImpl implements INoticeRecordService {
    @Autowired
    NoticeRecordMapper noticeRecordMapper;
    @Override
    public List<NoticeRecordListVo> listNotice() {
        int recipientType = -1;
        final Long userId = LikeAdminThreadLocal.getUserId();
        if(userId > 0) {
            recipientType = NoticeConstant.RecipientTypeEnum.MEMBER.getCode();
        }
        final Long adminId = LikeAdminThreadLocal.getAdminId();
        //TODO 区分商家和平台
        if(adminId > 0) {
            recipientType = NoticeConstant.RecipientTypeEnum.BUSINESS.getCode();
        }

        List<NoticeRecordListVo> noticeRecordListVos = new ArrayList<>();
        for (NoticeConstant.SendTypeEnum value : NoticeConstant.SendTypeEnum.values()) {
            NoticeRecordListVo noticeRecordListVo = new NoticeRecordListVo();
            noticeRecordListVo.setTitle(value.getMsg());
            noticeRecordListVo.setType(value.getCode());
//            noticeRecordListVo.setImg();
            final QueryWrapper<NoticeRecord> objectQueryWrapper = new QueryWrapper<>();
            objectQueryWrapper.eq("recipient",recipientType).eq("send_type",value.getCode())
                    .orderByDesc("create_time").last("limit 1");
            final List<NoticeRecord> noticeRecords = noticeRecordMapper.selectList(objectQueryWrapper);
            if (noticeRecords == null || noticeRecords.size() <= 0) {
                noticeRecordListVo.setContent("暂无" + value.getMsg().replace("通知","消息"));
            }
            else {
                noticeRecordListVo.setContent(noticeRecords.get(0).getContent());
            }
            noticeRecordListVos.add(noticeRecordListVo);
        }
        return noticeRecordListVos;
    }

    @Override
    public PageResult<NoticeRecord> listNoticeByType(PageParam pageParam, Integer type) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        int recipientType = -1;
        final Long userId = LikeAdminThreadLocal.getUserId();
        if(userId > 0) {
            recipientType = NoticeConstant.RecipientTypeEnum.MEMBER.getCode();
        }
        final Long adminId = LikeAdminThreadLocal.getAdminId();
        //TODO 区分商家和平台
        if(adminId > 0) {
            recipientType = NoticeConstant.RecipientTypeEnum.BUSINESS.getCode();
        }

        MPJQueryWrapper<NoticeRecord> mpjQueryWrapper = new MPJQueryWrapper<NoticeRecord>()
                .selectAll(NoticeRecord.class)
                .eq("recipient",recipientType).eq("send_type",type)
                .orderByDesc("create_time");

        IPage<NoticeRecord> iPage = noticeRecordMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                NoticeRecord.class,
                mpjQueryWrapper);


        return PageResult.iPageHandle(iPage);
    }
}
