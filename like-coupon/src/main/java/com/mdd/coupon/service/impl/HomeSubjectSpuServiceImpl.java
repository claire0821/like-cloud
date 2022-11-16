package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.IHomeSubjectSpuService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.HomeSubjectSpuParam;
import com.mdd.coupon.vo.HomeSubjectSpuListVo;
import com.mdd.coupon.vo.HomeSubjectSpuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.HomeSubjectSpu;
import com.mdd.coupon.mapper.HomeSubjectSpuMapper;
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
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuMapper,HomeSubjectSpu> implements IHomeSubjectSpuService {
        
    @Resource
    HomeSubjectSpuMapper homeSubjectSpuMapper;

    /**
     * 专题商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<HomeSubjectSpuListVo>
     */
    @Override
    public PageResult<HomeSubjectSpuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<HomeSubjectSpu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        homeSubjectSpuMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:subjectId@subject_id:long",
            "=:spuId@spu_id:long",
            "=:sort:int",
        });

        IPage<HomeSubjectSpu> iPage = homeSubjectSpuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<HomeSubjectSpuListVo> list = new LinkedList<>();
        for(HomeSubjectSpu item : iPage.getRecords()) {
            HomeSubjectSpuListVo vo = new HomeSubjectSpuListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 专题商品详情
     *
     * @param id 主键参数
     * @return HomeSubjectSpu
     */
    @Override
    public HomeSubjectSpuDetailVo detail(Long id) {
        HomeSubjectSpu model = homeSubjectSpuMapper.selectOne(
                new QueryWrapper<HomeSubjectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        HomeSubjectSpuDetailVo vo = new HomeSubjectSpuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 专题商品新增
     *
     * @param homeSubjectSpuParam 参数
     */
    @Override
    public void add(HomeSubjectSpuParam homeSubjectSpuParam) {
        HomeSubjectSpu model = new HomeSubjectSpu();
        model.setName(homeSubjectSpuParam.getName());
        model.setSubjectId(homeSubjectSpuParam.getSubjectId());
        model.setSpuId(homeSubjectSpuParam.getSpuId());
        model.setSort(homeSubjectSpuParam.getSort());
        homeSubjectSpuMapper.insert(model);
    }

    /**
     * 专题商品编辑
     *
     * @param homeSubjectSpuParam 参数
     */
    @Override
    public void edit(HomeSubjectSpuParam homeSubjectSpuParam) {
        HomeSubjectSpu model = homeSubjectSpuMapper.selectOne(
                new QueryWrapper<HomeSubjectSpu>()
                    .eq("id",  homeSubjectSpuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(homeSubjectSpuParam.getId());
        model.setName(homeSubjectSpuParam.getName());
        model.setSubjectId(homeSubjectSpuParam.getSubjectId());
        model.setSpuId(homeSubjectSpuParam.getSpuId());
        model.setSort(homeSubjectSpuParam.getSort());
        homeSubjectSpuMapper.updateById(model);
    }

    /**
     * 专题商品删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        HomeSubjectSpu model = homeSubjectSpuMapper.selectOne(
                new QueryWrapper<HomeSubjectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        homeSubjectSpuMapper.delete(new QueryWrapper<HomeSubjectSpu>().eq("id", id));
    }

}
