package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISpuInfoDescService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuInfoDescParam;
import com.mdd.product.vo.SpuInfoDescListVo;
import com.mdd.product.vo.SpuInfoDescDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SpuInfoDesc;
import com.mdd.product.mapper.SpuInfoDescMapper;
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
 * spu信息介绍实现类
 */
@Service
public class SpuInfoDescServiceImpl extends ServiceImpl<SpuInfoDescMapper,SpuInfoDesc> implements ISpuInfoDescService {
        
    @Resource
    SpuInfoDescMapper spuInfoDescMapper;

    /**
     * spu信息介绍列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuInfoDescListVo>
     */
    @Override
    public PageResult<SpuInfoDescListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuInfoDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("spu_id");

        spuInfoDescMapper.setSearch(queryWrapper, params, new String[]{
            "=:decript:str",
        });

        IPage<SpuInfoDesc> iPage = spuInfoDescMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuInfoDescListVo> list = new LinkedList<>();
        for(SpuInfoDesc item : iPage.getRecords()) {
            SpuInfoDescListVo vo = new SpuInfoDescListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu信息介绍详情
     *
     * @param id 主键参数
     * @return SpuInfoDesc
     */
    @Override
    public SpuInfoDescDetailVo detail(Long id) {
        SpuInfoDesc model = spuInfoDescMapper.selectOne(
                new QueryWrapper<SpuInfoDesc>()
                    .eq("spu_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuInfoDescDetailVo vo = new SpuInfoDescDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu信息介绍新增
     *
     * @param spuInfoDescParam 参数
     */
    @Override
    public void add(SpuInfoDescParam spuInfoDescParam) {
        SpuInfoDesc model = new SpuInfoDesc();
        model.setDecript(spuInfoDescParam.getDecript());
        spuInfoDescMapper.insert(model);
    }

    /**
     * spu信息介绍编辑
     *
     * @param spuInfoDescParam 参数
     */
    @Override
    public void edit(SpuInfoDescParam spuInfoDescParam) {
        SpuInfoDesc model = spuInfoDescMapper.selectOne(
                new QueryWrapper<SpuInfoDesc>()
                    .eq("spu_id",  spuInfoDescParam.getSpuId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setSpuId(spuInfoDescParam.getSpuId());
        model.setDecript(spuInfoDescParam.getDecript());
        spuInfoDescMapper.updateById(model);
    }

    /**
     * spu信息介绍删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuInfoDesc model = spuInfoDescMapper.selectOne(
                new QueryWrapper<SpuInfoDesc>()
                    .eq("spu_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuInfoDescMapper.delete(new QueryWrapper<SpuInfoDesc>().eq("spu_id", id));
    }

}
