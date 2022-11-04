package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsHomeSubjectSpuService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsHomeSubjectSpuParam;
import com.mdd.coupon.vo.SmsHomeSubjectSpuListVo;
import com.mdd.coupon.vo.SmsHomeSubjectSpuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsHomeSubjectSpu;
import com.mdd.coupon.mapper.SmsHomeSubjectSpuMapper;
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
 * 专题商品实现类
 */
@Service
public class SmsHomeSubjectSpuServiceImpl implements ISmsHomeSubjectSpuService {
        
    @Resource
    SmsHomeSubjectSpuMapper smsHomeSubjectSpuMapper;

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsHomeSubjectSpuListVo>
     */
    @Override
    public PageResult<SmsHomeSubjectSpuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsHomeSubjectSpu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        smsHomeSubjectSpuMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:subjectId@subject_id:long",
            "=:spuId@spu_id:long",
            "=:sort:long",
        });

        IPage<SmsHomeSubjectSpu> iPage = smsHomeSubjectSpuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsHomeSubjectSpuListVo> list = new LinkedList<>();
        for(SmsHomeSubjectSpu item : iPage.getRecords()) {
            SmsHomeSubjectSpuListVo vo = new SmsHomeSubjectSpuListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 专题商品详情
     *
     * @param id 主键参数
     * @return SmsHomeSubjectSpu
     */
    @Override
    public SmsHomeSubjectSpuDetailVo detail(Long id) {
        SmsHomeSubjectSpu model = smsHomeSubjectSpuMapper.selectOne(
                new QueryWrapper<SmsHomeSubjectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsHomeSubjectSpuDetailVo vo = new SmsHomeSubjectSpuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 专题商品新增
     *
     * @param smsHomeSubjectSpuParam 参数
     */
    @Override
    public void add(SmsHomeSubjectSpuParam smsHomeSubjectSpuParam) {
        SmsHomeSubjectSpu model = new SmsHomeSubjectSpu();
        model.setName(smsHomeSubjectSpuParam.getName());
        model.setSubjectId(smsHomeSubjectSpuParam.getSubjectId());
        model.setSpuId(smsHomeSubjectSpuParam.getSpuId());
        model.setSort(smsHomeSubjectSpuParam.getSort());
        smsHomeSubjectSpuMapper.insert(model);
    }

    /**
     * 专题商品编辑
     *
     * @param smsHomeSubjectSpuParam 参数
     */
    @Override
    public void edit(SmsHomeSubjectSpuParam smsHomeSubjectSpuParam) {
        SmsHomeSubjectSpu model = smsHomeSubjectSpuMapper.selectOne(
                new QueryWrapper<SmsHomeSubjectSpu>()
                    .eq("id",  smsHomeSubjectSpuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsHomeSubjectSpuParam.getId());
        model.setName(smsHomeSubjectSpuParam.getName());
        model.setSubjectId(smsHomeSubjectSpuParam.getSubjectId());
        model.setSpuId(smsHomeSubjectSpuParam.getSpuId());
        model.setSort(smsHomeSubjectSpuParam.getSort());
        smsHomeSubjectSpuMapper.updateById(model);
    }

    /**
     * 专题商品删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsHomeSubjectSpu model = smsHomeSubjectSpuMapper.selectOne(
                new QueryWrapper<SmsHomeSubjectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsHomeSubjectSpuMapper.delete(new QueryWrapper<SmsHomeSubjectSpu>().eq("id", id));
    }

}
