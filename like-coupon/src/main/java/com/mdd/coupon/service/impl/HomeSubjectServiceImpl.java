package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.IHomeSubjectService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.HomeSubjectParam;
import com.mdd.coupon.vo.HomeSubjectListVo;
import com.mdd.coupon.vo.HomeSubjectDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.HomeSubject;
import com.mdd.coupon.mapper.HomeSubjectMapper;
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
public class HomeSubjectServiceImpl extends ServiceImpl<HomeSubjectMapper,HomeSubject> implements IHomeSubjectService {
        
    @Resource
    HomeSubjectMapper homeSubjectMapper;

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeSubjectListVo>
     */
    @Override
    public PageResult<HomeSubjectListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<HomeSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        homeSubjectMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "like:title:str",
            "=:subTitle@sub_title:str",
            "=:status:int",
            "=:url:str",
            "=:sort:int",
            "=:img:str",
        });

        IPage<HomeSubject> iPage = homeSubjectMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<HomeSubjectListVo> list = new LinkedList<>();
        for(HomeSubject item : iPage.getRecords()) {
            HomeSubjectListVo vo = new HomeSubjectListVo();
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
     * @return HomeSubject
     */
    @Override
    public HomeSubjectDetailVo detail(Long id) {
        HomeSubject model = homeSubjectMapper.selectOne(
                new QueryWrapper<HomeSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        HomeSubjectDetailVo vo = new HomeSubjectDetailVo();
        BeanUtils.copyProperties(model, vo);
        vo.setImg(UrlUtil.toAbsoluteUrl(model.getImg()));
        return vo;
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
     *
     * @param homeSubjectParam 参数
     */
    @Override
    public void add(HomeSubjectParam homeSubjectParam) {
        HomeSubject model = new HomeSubject();
        model.setName(homeSubjectParam.getName());
        model.setTitle(homeSubjectParam.getTitle());
        model.setSubTitle(homeSubjectParam.getSubTitle());
        model.setStatus(homeSubjectParam.getStatus());
        model.setUrl(homeSubjectParam.getUrl());
        model.setSort(homeSubjectParam.getSort());
        model.setImg(UrlUtil.toRelativeUrl(homeSubjectParam.getImg()));
        homeSubjectMapper.insert(model);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
     *
     * @param homeSubjectParam 参数
     */
    @Override
    public void edit(HomeSubjectParam homeSubjectParam) {
        HomeSubject model = homeSubjectMapper.selectOne(
                new QueryWrapper<HomeSubject>()
                    .eq("id",  homeSubjectParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(homeSubjectParam.getId());
        model.setName(homeSubjectParam.getName());
        model.setTitle(homeSubjectParam.getTitle());
        model.setSubTitle(homeSubjectParam.getSubTitle());
        model.setStatus(homeSubjectParam.getStatus());
        model.setUrl(homeSubjectParam.getUrl());
        model.setSort(homeSubjectParam.getSort());
        model.setImg(UrlUtil.toRelativeUrl(homeSubjectParam.getImg()));
        homeSubjectMapper.updateById(model);
    }

    /**
     * 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        HomeSubject model = homeSubjectMapper.selectOne(
                new QueryWrapper<HomeSubject>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        homeSubjectMapper.delete(new QueryWrapper<HomeSubject>().eq("id", id));
    }

}
