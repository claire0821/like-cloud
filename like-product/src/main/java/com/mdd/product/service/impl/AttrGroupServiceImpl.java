package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.product.entity.Attr;
import com.mdd.product.entity.AttrAttrgroupRelation;
import com.mdd.product.entity.ProductAttrValue;
import com.mdd.product.service.IAttrAttrgroupRelationService;
import com.mdd.product.service.IAttrGroupService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.service.IAttrService;
import com.mdd.product.service.IProductAttrValueService;
import com.mdd.product.validate.AttrGroupParam;
import com.mdd.product.vo.*;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.AttrGroup;
import com.mdd.product.mapper.AttrGroupMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 属性分组实现类
 */
@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements IAttrGroupService {
        
    @Resource
    AttrGroupMapper pmsAttrGroupMapper;
    @Autowired
    IAttrService iAttrService;
    @Autowired
    IAttrAttrgroupRelationService iAttrAttrgroupRelationService;
    @Autowired
    IProductAttrValueService iProductAttrValueService;
    /**
     * 属性分组列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsAttrGroupListVo>
     */
    @Override
    public PageResult<AttrGroupListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();


        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "attr_group_id"));
        final String key = params.get("key");
        if(key != null && key.length() > 0) {
            queryWrapper.eq("attr_group_id",key).or().like("attr_group_name",key);
        }

        pmsAttrGroupMapper.setSearch(queryWrapper, params, new String[]{
            "like:attrGroupName@attr_group_name:str",
            "=:sort:long",
            "=:descript:str",
            "=:icon:str",
            "=:catelogId@catelog_id:long",
        });

        IPage<AttrGroup> iPage = pmsAttrGroupMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<AttrGroupListVo> list = new LinkedList<>();
        for(AttrGroup item : iPage.getRecords()) {
            AttrGroupListVo vo = new AttrGroupListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    @Override
    public PageResult<AttrGroupListVo> list(PageParam pageParam, String key, Long catelogId) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<AttrGroup> queryWrapper = new QueryWrapper<AttrGroup>();
        if(!StringUtils.isEmpty(key)){
            queryWrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }
        if(catelogId != null && catelogId >= 0) {
            queryWrapper.eq("catelog_id",catelogId);
        }

        IPage<AttrGroup> iPage = pmsAttrGroupMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<AttrGroupListVo> list = new LinkedList<>();
        for(AttrGroup item : iPage.getRecords()) {
            AttrGroupListVo vo = new AttrGroupListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 属性分组详情
     *
     * @param id 主键参数
     * @return PmsAttrGroup
     */
    @Override
    public AttrGroupDetailVo detail(Long id) {
        AttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<AttrGroup>()
                    .eq("attr_group_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        AttrGroupDetailVo vo = new AttrGroupDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 属性分组新增
     *
     * @param attrGroupParam 参数
     */
    @Override
    public void add(AttrGroupParam attrGroupParam) {
        AttrGroup model = new AttrGroup();
        model.setAttrGroupName(attrGroupParam.getAttrGroupName());
        model.setSort(attrGroupParam.getSort());
        model.setDescript(attrGroupParam.getDescript());
        model.setIcon(attrGroupParam.getIcon());
        model.setCatelogId(attrGroupParam.getCatelogId());
        pmsAttrGroupMapper.insert(model);
    }

    /**
     * 属性分组编辑
     *
     * @param attrGroupParam 参数
     */
    @Override
    public void edit(AttrGroupParam attrGroupParam) {
        AttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<AttrGroup>()
                    .eq("attr_group_id",  attrGroupParam.getAttrGroupId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setAttrGroupId(attrGroupParam.getAttrGroupId());
        model.setAttrGroupName(attrGroupParam.getAttrGroupName());
        model.setSort(attrGroupParam.getSort());
        model.setDescript(attrGroupParam.getDescript());
        model.setIcon(attrGroupParam.getIcon());
        model.setCatelogId(attrGroupParam.getCatelogId());
        pmsAttrGroupMapper.updateById(model);
    }

    /**
     * 属性分组删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        AttrGroup model = pmsAttrGroupMapper.selectOne(
                new QueryWrapper<AttrGroup>()
                    .eq("attr_group_id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsAttrGroupMapper.delete(new QueryWrapper<AttrGroup>().eq("attr_group_id", id));
    }

    @Override
    public List<AttrGroupDetailVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        //1、查询分组信息
        final List<AttrGroup> attrGroups = this.list(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));
        //2、查询所有属性
        final List<AttrGroupDetailVo> collect = attrGroups.stream().map(group -> {
            AttrGroupDetailVo attrGroupDetailVo = new AttrGroupDetailVo();
            BeanUtils.copyProperties(group, attrGroupDetailVo);
            final List<Attr> relationAttr = iAttrService.getRelationAttr(attrGroupDetailVo.getAttrGroupId());
            attrGroupDetailVo.setAttrs(relationAttr);
            return attrGroupDetailVo;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
//        //1、查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
//        List<ProductDetaliVo.SpuItemAttrGroupVo> spuItemAttrGroupVoArrayList = new ArrayList<>();
//        //查到分类下的组信息
//        final List<AttrGroup> list = this.list(new QueryWrapper<AttrGroup>()
//                .eq("catelog_id", catalogId)
//                .eq("spu_id", spuId));
//
//        for (AttrGroup attrGroup : list) {
//            final List<AttrAttrgroupRelation> attrAttrgroupRelations = iAttrAttrgroupRelationService.list(new QueryWrapper<AttrAttrgroupRelation>()
//                    .eq("attr_group_id", attrGroup.getAttrGroupId()));
//
//            ProductDetaliVo.SpuItemAttrGroupVo spuItemAttrGroupVo = new ProductDetaliVo.SpuItemAttrGroupVo();
//            spuItemAttrGroupVo.setGroupName(attrGroup.getAttrGroupName());
//            List<Attr> attrList = new ArrayList<>();
//
//            //从组和属性关联中查到属性
//            for (AttrAttrgroupRelation attrAttrgroupRelation : attrAttrgroupRelations) {
//                final Attr attr = iAttrService.getById(attrAttrgroupRelation.getAttrId());
//                //查到产品下的属性
//                final ProductAttrValue one = iProductAttrValueService.getOne(new QueryWrapper<ProductAttrValue>()
//                        .eq("spu_id", spuId).eq("attr_id", attr.getAttrId()));
//                if(one != null) {
//                    attrList.add(attr);
//                }
//            }
//            //产品有的属性才添加
//            if(attrList.size() > 0) {
//                spuItemAttrGroupVo.setAttrs(attrList);
//                spuItemAttrGroupVoArrayList.add(spuItemAttrGroupVo);
//            }
//
//        }
//
//        List<ProductDetaliVo.SpuItemAttrGroupVo> vos = this.getAttrGroupWithAttrsBySpuId(spuId,catalogId);

        //1、查出当前spu对应的所有属性的分组信息以及当前分组下的所有属性对应的值
        List<SpuItemAttrGroupVo> spuItemAttrGroupVoArrayList = this.getBaseMapper().getAttrGroupWithAttrsBySpuId(spuId,catalogId);
        return spuItemAttrGroupVoArrayList;
    }

}
