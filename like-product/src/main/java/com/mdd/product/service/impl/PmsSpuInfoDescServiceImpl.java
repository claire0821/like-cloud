package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSpuInfoDescService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSpuInfoDescParam;
import com.mdd.product.vo.PmsSpuInfoDescListVo;
import com.mdd.product.vo.PmsSpuInfoDescDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSpuInfoDesc;
import com.mdd.product.mapper.PmsSpuInfoDescMapper;
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
public class PmsSpuInfoDescServiceImpl implements IPmsSpuInfoDescService {
        
    @Resource
    PmsSpuInfoDescMapper pmsSpuInfoDescMapper;

    /**
     * spu信息介绍列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuInfoDescListVo>
     */
    @Override
    public PageResult<PmsSpuInfoDescListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSpuInfoDesc> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("spu_id");

        pmsSpuInfoDescMapper.setSearch(queryWrapper, params, new String[]{
            "=:decript:str",
        });

        IPage<PmsSpuInfoDesc> iPage = pmsSpuInfoDescMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSpuInfoDescListVo> list = new LinkedList<>();
        for(PmsSpuInfoDesc item : iPage.getRecords()) {
            PmsSpuInfoDescListVo vo = new PmsSpuInfoDescListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * spu信息介绍详情
     *
     * @param id 主键参数
     * @return PmsSpuInfoDesc
     */
    @Override
    public PmsSpuInfoDescDetailVo detail(Long id) {
        PmsSpuInfoDesc model = pmsSpuInfoDescMapper.selectOne(
                new QueryWrapper<PmsSpuInfoDesc>()
                    .eq("spu_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSpuInfoDescDetailVo vo = new PmsSpuInfoDescDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * spu信息介绍新增
     *
     * @param pmsSpuInfoDescParam 参数
     */
    @Override
    public void add(PmsSpuInfoDescParam pmsSpuInfoDescParam) {
        PmsSpuInfoDesc model = new PmsSpuInfoDesc();
        model.setDecript(pmsSpuInfoDescParam.getDecript());
        pmsSpuInfoDescMapper.insert(model);
    }

    /**
     * spu信息介绍编辑
     *
     * @param pmsSpuInfoDescParam 参数
     */
    @Override
    public void edit(PmsSpuInfoDescParam pmsSpuInfoDescParam) {
        PmsSpuInfoDesc model = pmsSpuInfoDescMapper.selectOne(
                new QueryWrapper<PmsSpuInfoDesc>()
                    .eq("spu_id",  pmsSpuInfoDescParam.getSpuId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setSpuId(pmsSpuInfoDescParam.getSpuId());
        model.setDecript(pmsSpuInfoDescParam.getDecript());
        pmsSpuInfoDescMapper.updateById(model);
    }

    /**
     * spu信息介绍删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSpuInfoDesc model = pmsSpuInfoDescMapper.selectOne(
                new QueryWrapper<PmsSpuInfoDesc>()
                    .eq("spu_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSpuInfoDescMapper.delete(new QueryWrapper<PmsSpuInfoDesc>().eq("spu_id", id));
    }

}
