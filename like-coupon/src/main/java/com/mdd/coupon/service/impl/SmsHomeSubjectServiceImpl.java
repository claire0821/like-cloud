package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsHomeSubjectService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsHomeSubjectParam;
import com.mdd.coupon.vo.SmsHomeSubjectListVo;
import com.mdd.coupon.vo.SmsHomeSubjectDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsHomeSubject;
import com.mdd.coupon.mapper.SmsHomeSubjectMapper;
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
 * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】实现类
 */
@Service
public class SmsHomeSubjectServiceImpl implements ISmsHomeSubjectService {
        
    @Resource
    SmsHomeSubjectMapper smsHomeSubjectMapper;

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeSubjectListVo>
     */
    @Override
    public PageResult<SmsHomeSubjectListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsHomeSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        smsHomeSubjectMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "like:title:str",
            "=:subTitle@sub_title:str",
            "=:status:int",
            "=:url:str",
            "=:sort:long",
            "=:img:str",
        });

        IPage<SmsHomeSubject> iPage = smsHomeSubjectMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsHomeSubjectListVo> list = new LinkedList<>();
        for(SmsHomeSubject item : iPage.getRecords()) {
            SmsHomeSubjectListVo vo = new SmsHomeSubjectListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setImg(UrlUtil.toAbsoluteUrl(item.getImg()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
     *
     * @param id 主键参数
     * @return SmsHomeSubject
     */
    @Override
    public SmsHomeSubjectDetailVo detail(Long id) {
        SmsHomeSubject model = smsHomeSubjectMapper.selectOne(
                new QueryWrapper<SmsHomeSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsHomeSubjectDetailVo vo = new SmsHomeSubjectDetailVo();
        BeanUtils.copyProperties(model, vo);
        vo.setImg(UrlUtil.toAbsoluteUrl(model.getImg()));
        return vo;
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param smsHomeSubjectParam 参数
     */
    @Override
    public void add(SmsHomeSubjectParam smsHomeSubjectParam) {
        SmsHomeSubject model = new SmsHomeSubject();
        model.setName(smsHomeSubjectParam.getName());
        model.setTitle(smsHomeSubjectParam.getTitle());
        model.setSubTitle(smsHomeSubjectParam.getSubTitle());
        model.setStatus(smsHomeSubjectParam.getStatus());
        model.setUrl(smsHomeSubjectParam.getUrl());
        model.setSort(smsHomeSubjectParam.getSort());
        model.setImg(UrlUtil.toRelativeUrl(smsHomeSubjectParam.getImg()));
        smsHomeSubjectMapper.insert(model);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param smsHomeSubjectParam 参数
     */
    @Override
    public void edit(SmsHomeSubjectParam smsHomeSubjectParam) {
        SmsHomeSubject model = smsHomeSubjectMapper.selectOne(
                new QueryWrapper<SmsHomeSubject>()
                    .eq("id",  smsHomeSubjectParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsHomeSubjectParam.getId());
        model.setName(smsHomeSubjectParam.getName());
        model.setTitle(smsHomeSubjectParam.getTitle());
        model.setSubTitle(smsHomeSubjectParam.getSubTitle());
        model.setStatus(smsHomeSubjectParam.getStatus());
        model.setUrl(smsHomeSubjectParam.getUrl());
        model.setSort(smsHomeSubjectParam.getSort());
        model.setImg(UrlUtil.toRelativeUrl(smsHomeSubjectParam.getImg()));
        smsHomeSubjectMapper.updateById(model);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsHomeSubject model = smsHomeSubjectMapper.selectOne(
                new QueryWrapper<SmsHomeSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsHomeSubjectMapper.delete(new QueryWrapper<SmsHomeSubject>().eq("id", id));
    }

}
