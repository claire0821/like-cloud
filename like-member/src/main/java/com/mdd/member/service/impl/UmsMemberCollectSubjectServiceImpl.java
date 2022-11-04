package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsMemberCollectSubjectService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberCollectSubjectParam;
import com.mdd.member.vo.UmsMemberCollectSubjectListVo;
import com.mdd.member.vo.UmsMemberCollectSubjectDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberCollectSubject;
import com.mdd.member.mapper.UmsMemberCollectSubjectMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 会员收藏的专题活动实现类
 */
@Service
public class UmsMemberCollectSubjectServiceImpl implements IUmsMemberCollectSubjectService {
        
    @Resource
    UmsMemberCollectSubjectMapper umsMemberCollectSubjectMapper;

    /**
     * 会员收藏的专题活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberCollectSubjectListVo>
     */
    @Override
    public PageResult<UmsMemberCollectSubjectListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberCollectSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberCollectSubjectMapper.setSearch(queryWrapper, params, new String[]{
            "=:subjectId@subject_id:long",
            "like:subjectName@subject_name:str",
            "=:subjectImg@subject_img:str",
            "=:subjectUrll@subject_urll:str",
        });

        IPage<UmsMemberCollectSubject> iPage = umsMemberCollectSubjectMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberCollectSubjectListVo> list = new LinkedList<>();
        for(UmsMemberCollectSubject item : iPage.getRecords()) {
            UmsMemberCollectSubjectListVo vo = new UmsMemberCollectSubjectListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键参数
     * @return UmsMemberCollectSubject
     */
    @Override
    public UmsMemberCollectSubjectDetailVo detail(Long id) {
        UmsMemberCollectSubject model = umsMemberCollectSubjectMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberCollectSubjectDetailVo vo = new UmsMemberCollectSubjectDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收藏的专题活动新增
     *
     * @param umsMemberCollectSubjectParam 参数
     */
    @Override
    public void add(UmsMemberCollectSubjectParam umsMemberCollectSubjectParam) {
        UmsMemberCollectSubject model = new UmsMemberCollectSubject();
        model.setSubjectId(umsMemberCollectSubjectParam.getSubjectId());
        model.setSubjectName(umsMemberCollectSubjectParam.getSubjectName());
        model.setSubjectImg(umsMemberCollectSubjectParam.getSubjectImg());
        model.setSubjectUrll(umsMemberCollectSubjectParam.getSubjectUrll());
        umsMemberCollectSubjectMapper.insert(model);
    }

    /**
     * 会员收藏的专题活动编辑
     *
     * @param umsMemberCollectSubjectParam 参数
     */
    @Override
    public void edit(UmsMemberCollectSubjectParam umsMemberCollectSubjectParam) {
        UmsMemberCollectSubject model = umsMemberCollectSubjectMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSubject>()
                    .eq("id",  umsMemberCollectSubjectParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberCollectSubjectParam.getId());
        model.setSubjectId(umsMemberCollectSubjectParam.getSubjectId());
        model.setSubjectName(umsMemberCollectSubjectParam.getSubjectName());
        model.setSubjectImg(umsMemberCollectSubjectParam.getSubjectImg());
        model.setSubjectUrll(umsMemberCollectSubjectParam.getSubjectUrll());
        umsMemberCollectSubjectMapper.updateById(model);
    }

    /**
     * 会员收藏的专题活动删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberCollectSubject model = umsMemberCollectSubjectMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberCollectSubjectMapper.delete(new QueryWrapper<UmsMemberCollectSubject>().eq("id", id));
    }

}
