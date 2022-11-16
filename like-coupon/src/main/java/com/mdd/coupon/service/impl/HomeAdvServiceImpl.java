package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.IHomeAdvService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.HomeAdvParam;
import com.mdd.coupon.vo.HomeAdvListVo;
import com.mdd.coupon.vo.HomeAdvDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.HomeAdv;
import com.mdd.coupon.mapper.HomeAdvMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 首页轮播广告实现类
 */
@Service
public class HomeAdvServiceImpl extends ServiceImpl<HomeAdvMapper,HomeAdv> implements IHomeAdvService {
        
    @Resource
    HomeAdvMapper homeAdvMapper;

    /**
     * 首页轮播广告列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeAdvListVo>
     */
    @Override
    public PageResult<HomeAdvListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<HomeAdv> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        homeAdvMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:pic:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:int",
            "=:clickCount@click_count:int",
            "=:url:str",
            "=:note:str",
            "=:sort:int",
            "=:publisherId@publisher_id:long",
            "=:authId@auth_id:long",
        });

        IPage<HomeAdv> iPage = homeAdvMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<HomeAdvListVo> list = new LinkedList<>();
        for(HomeAdv item : iPage.getRecords()) {
            HomeAdvListVo vo = new HomeAdvListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setStartTime(simpleDateFormat.format(item.getStartTime()));
            vo.setEndTime(simpleDateFormat.format(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 首页轮播广告详情
     *
     * @param id 主键参数
     * @return HomeAdv
     */
    @Override
    public HomeAdvDetailVo detail(Long id) {
        HomeAdv model = homeAdvMapper.selectOne(
                new QueryWrapper<HomeAdv>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        HomeAdvDetailVo vo = new HomeAdvDetailVo();
        BeanUtils.copyProperties(model, vo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setStartTime(simpleDateFormat.format(model.getStartTime()));
        vo.setEndTime(simpleDateFormat.format(model.getEndTime()));
        return vo;
    }

    /**
     * 首页轮播广告新增
     *
     * @param homeAdvParam 参数
     */
    @Override
    public void add(HomeAdvParam homeAdvParam) {
        HomeAdv model = new HomeAdv();
        model.setName(homeAdvParam.getName());
        model.setPic(homeAdvParam.getPic());
        model.setStartTime(new Date());
        model.setEndTime(new Date());
        model.setStatus(homeAdvParam.getStatus());
        model.setClickCount(homeAdvParam.getClickCount());
        model.setUrl(homeAdvParam.getUrl());
        model.setNote(homeAdvParam.getNote());
        model.setSort(homeAdvParam.getSort());
        model.setPublisherId(homeAdvParam.getPublisherId());
        model.setAuthId(homeAdvParam.getAuthId());
        homeAdvMapper.insert(model);
    }

    /**
     * 首页轮播广告编辑
     *
     * @param homeAdvParam 参数
     */
    @Override
    public void edit(HomeAdvParam homeAdvParam) {
        HomeAdv model = homeAdvMapper.selectOne(
                new QueryWrapper<HomeAdv>()
                    .eq("id",  homeAdvParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(homeAdvParam.getId());
        model.setName(homeAdvParam.getName());
        model.setPic(homeAdvParam.getPic());
        model.setStatus(homeAdvParam.getStatus());
        model.setClickCount(homeAdvParam.getClickCount());
        model.setUrl(homeAdvParam.getUrl());
        model.setNote(homeAdvParam.getNote());
        model.setSort(homeAdvParam.getSort());
        model.setPublisherId(homeAdvParam.getPublisherId());
        model.setAuthId(homeAdvParam.getAuthId());
        homeAdvMapper.updateById(model);
    }

    /**
     * 首页轮播广告删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        HomeAdv model = homeAdvMapper.selectOne(
                new QueryWrapper<HomeAdv>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        homeAdvMapper.delete(new QueryWrapper<HomeAdv>().eq("id", id));
    }

}
