package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsBrandService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsBrandParam;
import com.mdd.product.vo.PmsBrandListVo;
import com.mdd.product.vo.PmsBrandDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsBrand;
import com.mdd.product.mapper.PmsBrandMapper;
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
 * 品牌实现类
 */
@Service
public class PmsBrandServiceImpl implements IPmsBrandService {
        
    @Resource
    PmsBrandMapper pmsBrandMapper;

    /**
     * 品牌列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsBrandListVo>
     */
    @Override
    public PageResult<PmsBrandListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "brand_id"));

        pmsBrandMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:logo:str",
            "=:descript:str",
            "=:showStatus@show_status:long",
            "=:firstLetter@first_letter:str",
            "=:sort:long",
        });

        IPage<PmsBrand> iPage = pmsBrandMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsBrandListVo> list = new LinkedList<>();
        for(PmsBrand item : iPage.getRecords()) {
            PmsBrandListVo vo = new PmsBrandListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setLogo(UrlUtil.toAbsoluteUrl(item.getLogo()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 品牌详情
     *
     * @param id 主键参数
     * @return PmsBrand
     */
    @Override
    public PmsBrandDetailVo detail(Long id) {
        PmsBrand model = pmsBrandMapper.selectOne(
                new QueryWrapper<PmsBrand>()
                    .eq("brand_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsBrandDetailVo vo = new PmsBrandDetailVo();
        BeanUtils.copyProperties(model, vo);
        vo.setLogo(UrlUtil.toAbsoluteUrl(model.getLogo()));
        return vo;
    }

    /**
     * 品牌新增
     *
     * @param pmsBrandParam 参数
     */
    @Override
    public void add(PmsBrandParam pmsBrandParam) {
        PmsBrand model = new PmsBrand();
        model.setName(pmsBrandParam.getName());
        model.setLogo(UrlUtil.toRelativeUrl(pmsBrandParam.getLogo()));
        model.setDescript(pmsBrandParam.getDescript());
        model.setShowStatus(pmsBrandParam.getShowStatus());
        model.setFirstLetter(pmsBrandParam.getFirstLetter());
        model.setSort(pmsBrandParam.getSort());
        pmsBrandMapper.insert(model);
    }

    /**
     * 品牌编辑
     *
     * @param pmsBrandParam 参数
     */
    @Override
    public void edit(PmsBrandParam pmsBrandParam) {
        PmsBrand model = pmsBrandMapper.selectOne(
                new QueryWrapper<PmsBrand>()
                    .eq("brand_id",  pmsBrandParam.getBrandId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setBrandId(pmsBrandParam.getBrandId());
        model.setName(pmsBrandParam.getName());
        model.setLogo(UrlUtil.toRelativeUrl(pmsBrandParam.getLogo()));
        model.setDescript(pmsBrandParam.getDescript());
        model.setShowStatus(pmsBrandParam.getShowStatus());
        model.setFirstLetter(pmsBrandParam.getFirstLetter());
        model.setSort(pmsBrandParam.getSort());
        pmsBrandMapper.updateById(model);
    }

    /**
     * 品牌删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsBrand model = pmsBrandMapper.selectOne(
                new QueryWrapper<PmsBrand>()
                    .eq("brand_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsBrandMapper.delete(new QueryWrapper<PmsBrand>().eq("brand_id", id));
    }

}
