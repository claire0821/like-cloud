package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IMemberCollectSubjectService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberCollectSubjectParam;
import com.mdd.member.vo.MemberCollectSubjectListVo;
import com.mdd.member.vo.MemberCollectSubjectDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberCollectSubject;
import com.mdd.member.mapper.MemberCollectSubjectMapper;
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
public class MemberCollectSubjectServiceImpl extends ServiceImpl<MemberCollectSubjectMapper,MemberCollectSubject> implements IMemberCollectSubjectService {
        
    @Resource
    MemberCollectSubjectMapper memberCollectSubjectMapper;

    /**
     * 会员收藏的专题活动列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberCollectSubjectListVo>
     */
    @Override
    public PageResult<MemberCollectSubjectListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberCollectSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberCollectSubjectMapper.setSearch(queryWrapper, params, new String[]{
            "=:subjectId@subject_id:long",
            "like:subjectName@subject_name:str",
            "=:subjectImg@subject_img:str",
            "=:subjectUrll@subject_urll:str",
        });

        IPage<MemberCollectSubject> iPage = memberCollectSubjectMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberCollectSubjectListVo> list = new LinkedList<>();
        for(MemberCollectSubject item : iPage.getRecords()) {
            MemberCollectSubjectListVo vo = new MemberCollectSubjectListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收藏的专题活动详情
     *
     * @param id 主键参数
     * @return MemberCollectSubject
     */
    @Override
    public MemberCollectSubjectDetailVo detail(Long id) {
        MemberCollectSubject model = memberCollectSubjectMapper.selectOne(
                new QueryWrapper<MemberCollectSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberCollectSubjectDetailVo vo = new MemberCollectSubjectDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收藏的专题活动新增
     *
     * @param memberCollectSubjectParam 参数
     */
    @Override
    public void add(MemberCollectSubjectParam memberCollectSubjectParam) {
        MemberCollectSubject model = new MemberCollectSubject();
        model.setSubjectId(memberCollectSubjectParam.getSubjectId());
        model.setSubjectName(memberCollectSubjectParam.getSubjectName());
        model.setSubjectImg(memberCollectSubjectParam.getSubjectImg());
        model.setSubjectUrll(memberCollectSubjectParam.getSubjectUrll());
        memberCollectSubjectMapper.insert(model);
    }

    /**
     * 会员收藏的专题活动编辑
     *
     * @param memberCollectSubjectParam 参数
     */
    @Override
    public void edit(MemberCollectSubjectParam memberCollectSubjectParam) {
        MemberCollectSubject model = memberCollectSubjectMapper.selectOne(
                new QueryWrapper<MemberCollectSubject>()
                    .eq("id",  memberCollectSubjectParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberCollectSubjectParam.getId());
        model.setSubjectId(memberCollectSubjectParam.getSubjectId());
        model.setSubjectName(memberCollectSubjectParam.getSubjectName());
        model.setSubjectImg(memberCollectSubjectParam.getSubjectImg());
        model.setSubjectUrll(memberCollectSubjectParam.getSubjectUrll());
        memberCollectSubjectMapper.updateById(model);
    }

    /**
     * 会员收藏的专题活动删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberCollectSubject model = memberCollectSubjectMapper.selectOne(
                new QueryWrapper<MemberCollectSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberCollectSubjectMapper.delete(new QueryWrapper<MemberCollectSubject>().eq("id", id));
    }

}
