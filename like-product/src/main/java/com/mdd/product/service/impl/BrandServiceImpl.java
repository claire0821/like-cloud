package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.product.service.IBrandService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.BrandParam;
import com.mdd.product.vo.BrandListVo;
import com.mdd.product.vo.BrandDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.Brand;
import com.mdd.product.mapper.BrandMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 品牌实现类
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
        
    @Resource
    BrandMapper brandMapper;

    /**
     * 品牌列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsBrandListVo>
     */
    @Override
    public PageResult<BrandListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "brand_id"));

        brandMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:logo:str",
            "=:descript:str",
            "=:showStatus@show_status:long",
            "=:firstLetter@first_letter:str",
            "=:sort:long",
        });

        IPage<Brand> iPage = brandMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<BrandListVo> list = new LinkedList<>();
        for(Brand item : iPage.getRecords()) {
            BrandListVo vo = new BrandListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setLogo(item.getLogo());
//            vo.setLogo(UrlUtil.toAbsoluteUrl(item.getLogo()));
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
    public BrandDetailVo detail(Long id) {
        Brand model = brandMapper.selectOne(
                new QueryWrapper<Brand>()
                    .eq("brand_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        BrandDetailVo vo = new BrandDetailVo();
        BeanUtils.copyProperties(model, vo);
//        vo.setLogo(UrlUtil.toAbsoluteUrl(model.getLogo()));
        return vo;
    }

    /**
     * 品牌新增
     *
     * @param brandParam 参数
     */
    @Override
    public void add(BrandParam brandParam) {
        Brand model = new Brand();
        model.setName(brandParam.getName());
//        model.setLogo(UrlUtil.toRelativeUrl(pmsBrandParam.getLogo()));
        model.setLogo(brandParam.getLogo());
        model.setDescript(brandParam.getDescript());
        model.setShowStatus(brandParam.getShowStatus());
        model.setFirstLetter(brandParam.getFirstLetter());
        model.setSort(brandParam.getSort());
        brandMapper.insert(model);
    }

    /**
     * 品牌编辑
     *
     * @param brandParam 参数
     */
    @Override
    public void edit(BrandParam brandParam) {
        Brand model = brandMapper.selectOne(
                new QueryWrapper<Brand>()
                    .eq("brand_id",  brandParam.getBrandId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setBrandId(brandParam.getBrandId());
        model.setName(brandParam.getName());
//        model.setLogo(UrlUtil.toRelativeUrl(pmsBrandParam.getLogo()));
        model.setLogo(brandParam.getLogo());
        model.setDescript(brandParam.getDescript());
        model.setShowStatus(brandParam.getShowStatus());
        model.setFirstLetter(brandParam.getFirstLetter());
        model.setSort(brandParam.getSort());
        brandMapper.updateById(model);
    }

    /**
     * 品牌删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Brand model = brandMapper.selectOne(
                new QueryWrapper<Brand>()
                    .eq("brand_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        brandMapper.delete(new QueryWrapper<Brand>().eq("brand_id", id));
    }

}