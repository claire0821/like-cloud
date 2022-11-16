package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISpuBoundsService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SpuBoundsParam;
import com.mdd.coupon.vo.SpuBoundsListVo;
import com.mdd.coupon.vo.SpuBoundsDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SpuBounds;
import com.mdd.coupon.mapper.SpuBoundsMapper;
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
 * 商品spu积分设置实现类
 */
@Service
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsMapper,SpuBounds> implements ISpuBoundsService {
        
    @Resource
    SpuBoundsMapper spuBoundsMapper;

    /**
     * 商品spu积分设置列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuBoundsListVo>
     */
    @Override
    public PageResult<SpuBoundsListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuBounds> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        spuBoundsMapper.setSearch(queryWrapper, params, new String[]{
            "=:spuId@spu_id:long",
            "=:growBounds@grow_bounds:str",
            "=:buyBounds@buy_bounds:str",
            "=:work:int",
        });

        IPage<SpuBounds> iPage = spuBoundsMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuBoundsListVo> list = new LinkedList<>();
        for(SpuBounds item : iPage.getRecords()) {
            SpuBoundsListVo vo = new SpuBoundsListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品spu积分设置详情
     *
     * @param id 主键参数
     * @return SpuBounds
     */
    @Override
    public SpuBoundsDetailVo detail(Long id) {
        SpuBounds model = spuBoundsMapper.selectOne(
                new QueryWrapper<SpuBounds>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuBoundsDetailVo vo = new SpuBoundsDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品spu积分设置新增
     *
     * @param spuBoundsParam 参数
     */
    @Override
    public void add(SpuBoundsParam spuBoundsParam) {
        SpuBounds model = new SpuBounds();
        model.setSpuId(spuBoundsParam.getSpuId());
        model.setGrowBounds(spuBoundsParam.getGrowBounds());
        model.setBuyBounds(spuBoundsParam.getBuyBounds());
        model.setWork(spuBoundsParam.getWork());
        spuBoundsMapper.insert(model);
    }

    /**
     * 商品spu积分设置编辑
     *
     * @param spuBoundsParam 参数
     */
    @Override
    public void edit(SpuBoundsParam spuBoundsParam) {
        SpuBounds model = spuBoundsMapper.selectOne(
                new QueryWrapper<SpuBounds>()
                    .eq("id",  spuBoundsParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(spuBoundsParam.getId());
        model.setSpuId(spuBoundsParam.getSpuId());
        model.setGrowBounds(spuBoundsParam.getGrowBounds());
        model.setBuyBounds(spuBoundsParam.getBuyBounds());
        model.setWork(spuBoundsParam.getWork());
        spuBoundsMapper.updateById(model);
    }

    /**
     * 商品spu积分设置删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuBounds model = spuBoundsMapper.selectOne(
                new QueryWrapper<SpuBounds>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuBoundsMapper.delete(new QueryWrapper<SpuBounds>().eq("id", id));
    }

}
