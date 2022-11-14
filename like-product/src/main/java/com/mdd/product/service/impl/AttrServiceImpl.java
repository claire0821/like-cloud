package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.constant.ProductConstant;
import com.mdd.product.controller.CategoryController;
import com.mdd.product.entity.AttrAttrgroupRelation;
import com.mdd.product.entity.AttrGroup;
import com.mdd.product.entity.Category;
import com.mdd.product.mapper.AttrAttrgroupRelationMapper;
import com.mdd.product.mapper.AttrGroupMapper;
import com.mdd.product.mapper.CategoryMapper;
import com.mdd.product.service.IAttrAttrgroupRelationService;
import com.mdd.product.service.IAttrService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.service.ICategoryService;
import com.mdd.product.validate.AttrParam;
import com.mdd.product.vo.*;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.Attr;
import com.mdd.product.mapper.AttrMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品属性实现类
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper,Attr> implements IAttrService {

    @Resource
    AttrMapper attrMapper;

    @Autowired
    AttrAttrgroupRelationMapper attrAttrgroupRelationMapper;

    @Autowired
    AttrGroupMapper attrGroupMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ICategoryService iCategoryService;

    @Autowired
    IAttrAttrgroupRelationService iAttrAttrgroupRelationService;
    /**
     * 商品属性列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<AttrListVo>
     */
    @Override
    public PageResult<AttrListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();
        final Integer attrType = Integer.valueOf(params.get("attrType"));

        QueryWrapper<Attr> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("attr_id")
                .eq("attr_type", attrType);

        attrMapper.setSearch(queryWrapper, params, new String[]{
                "like:attrName@attr_name:str",
                "=:searchType@search_type:int",
                "=:icon:str",
                "=:valueSelect@value_select:str",
                "=:attrType@attr_type:int",
                "=:enable:long",
                "=:catelogId@catelog_id:long",
                "=:showDesc@show_desc:int",
        });

        IPage<Attr> iPage = attrMapper.selectPage(new Page<>(page, limit), queryWrapper);

        String attrGroupName = "";
        String catelogName = "";
        if(ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() == attrType) {
            //1、设置分类和分组的名字
            final String attr_id = params.get("attr_id");

            final AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", attr_id));
            if(attrAttrgroupRelation != null && attrAttrgroupRelation.getAttrGroupId() != null) {
                final AttrGroup attrGroup = attrGroupMapper.selectById(attrAttrgroupRelation.getAttrGroupId());
                if(attrGroup != null) {
                    attrGroupName = attrGroup.getAttrGroupName();
                }
            }
            //2、设置分类信息
            final String catelog_id = params.get("catelog_id");

            if(catelog_id != null && catelog_id.length() > 0) {
                final Category category = categoryMapper.selectById(catelog_id);
                if(category != null) {
                    catelogName = category.getName();
                }
            }
        }

        List<AttrListVo> list = new LinkedList<>();
        for(Attr item : iPage.getRecords()) {
            AttrListVo vo = new AttrListVo();
            BeanUtils.copyProperties(item, vo);
            if(ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() == attrType) {
                if(attrGroupName.length() == 0) {
                    final AttrAttrgroupRelation attrAttrgroupRelation1 = attrAttrgroupRelationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", item.getAttrId()));
                    if(attrAttrgroupRelation1 != null && attrAttrgroupRelation1.getAttrGroupId() != null) {
                        final AttrGroup attrGroup = attrGroupMapper.selectById(attrAttrgroupRelation1.getAttrGroupId());
                        if(attrGroup != null) {
                            attrGroupName = attrGroup.getAttrGroupName();
                        }
                    }
                }
                vo.setGroupName(attrGroupName);
                if(catelogName.length() == 0) {
                    final Category category = categoryMapper.selectById(item.getCatelogId());
                    if(category != null) {
                        catelogName = category.getName();
                    }
                }
                vo.setCatelogName(catelogName);
            }

            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品属性详情
     *
     * @param id 主键参数
     * @return Attr
     */
    @Override
    public AttrDetailVo detail(Long id) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        AttrDetailVo vo = new AttrDetailVo();
        BeanUtils.copyProperties(model, vo);

        if(model.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {
            //1、设置分类和分组的名字
            final AttrAttrgroupRelation attrAttrgroupRelation = attrAttrgroupRelationMapper.selectOne(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_id", model.getAttrId()));
            if(attrAttrgroupRelation != null && attrAttrgroupRelation.getAttrGroupId() != null) {
                vo.setAttrGroupId(attrAttrgroupRelation.getAttrGroupId());
                final AttrGroup attrGroup = attrGroupMapper.selectById(attrAttrgroupRelation.getAttrGroupId());
                if(attrGroup != null) {
                    vo.setGroupName(attrGroup.getAttrGroupName());
                }
            }
        }

        //2、设置分类信息
        final Category category = categoryMapper.selectById(model.getCatelogId());
        if(category != null) {
            vo.setCatelogName(category.getName());
        }

        final Long[] catelogPath = iCategoryService.findCatelogPath(model.getCatelogId());
        if(catelogPath != null) {
            vo.setCatelogPath(catelogPath);
        }
        return vo;
    }

    /**
     * 商品属性新增
     *
     * @param attrParam 参数
     */
    @Override
    public void add(AttrParam attrParam) {
        Attr model = new Attr();
        model.setAttrName(attrParam.getAttrName());
        model.setSearchType(attrParam.getSearchType());
        model.setIcon(attrParam.getIcon());
        model.setValueSelect(attrParam.getValueSelect());
        model.setAttrType(attrParam.getAttrType());
        model.setEnable(attrParam.getEnable());
        model.setCatelogId(attrParam.getCatelogId());
        model.setShowDesc(attrParam.getShowDesc());
        attrMapper.insert(model);
    }

    /**
     * 商品属性编辑
     *
     * @param attrParam 参数
     */
    @Override
    public void edit(AttrParam attrParam) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id",  attrParam.getAttrId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setAttrId(attrParam.getAttrId());
        model.setAttrName(attrParam.getAttrName());
        model.setSearchType(attrParam.getSearchType());
        model.setIcon(attrParam.getIcon());
        model.setValueSelect(attrParam.getValueSelect());
        model.setAttrType(attrParam.getAttrType());
        model.setEnable(attrParam.getEnable());
        model.setCatelogId(attrParam.getCatelogId());
        model.setShowDesc(attrParam.getShowDesc());
        attrMapper.updateById(model);
    }

    /**
     * 商品属性删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Attr model = attrMapper.selectOne(
                new QueryWrapper<Attr>()
                        .eq("attr_id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        attrMapper.delete(new QueryWrapper<Attr>().eq("attr_id", id));
    }

    @Transactional
    @Override
    public void update(AttrDetailVo attrDetailVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attr, attrDetailVo);
        updateById(attr);
        //修改分组关联
        AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
        attrAttrgroupRelation.setAttrGroupId(attrDetailVo.getAttrGroupId());
        attrAttrgroupRelation.setAttrId(attrDetailVo.getAttrId());
        final Integer count = attrAttrgroupRelationMapper.selectCount(new QueryWrapper<AttrAttrgroupRelation>()
                .eq("attr_id", attrAttrgroupRelation.getAttrId()));
        if(count > 0) {
            attrAttrgroupRelationMapper.update(attrAttrgroupRelation,new UpdateWrapper<AttrAttrgroupRelation>()
            .eq("attr_id", attrAttrgroupRelation.getAttrId()));
        } else {
            attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
        }

    }

    @Transactional
    @Override
    public void save(AttrListVo attrListVo) {
        Attr attr = new Attr();
        BeanUtils.copyProperties(attrListVo, attr);
        //保存基本数据
        baseMapper.insert(attr);
        //保存关联数据
        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attrListVo.getAttrGroupId()!=null){
            AttrAttrgroupRelation attrAttrgroupRelation = new AttrAttrgroupRelation();
            attrAttrgroupRelation.setAttrGroupId(attrListVo.getAttrGroupId());
            attrAttrgroupRelation.setAttrId(attrListVo.getAttrId());
            attrAttrgroupRelationMapper.insert(attrAttrgroupRelation);
        }
    }

    //根据分组id查找关联的所有基本属性
    @Override
    public PageResult<AttrListVo> getRelationAttr(PageParam pageParam, Map<String, String> params, Long attrgroupId) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        List<AttrAttrgroupRelation> entities = attrAttrgroupRelationMapper.selectList(new QueryWrapper<AttrAttrgroupRelation>().eq("attr_group_id", attrgroupId));

        List<Long> attrIds = entities.stream().map((attr) -> {
            return attr.getAttrId();
        }).collect(Collectors.toList());

        if(attrIds == null || attrIds.size() == 0){
            return null;
        }
//        Collection<Attr> attrEntities = this.listByIds(attrIds);

        IPage<Attr> iPage = attrMapper.selectPage(new Page<>(page, limit), new QueryWrapper<Attr>().in("attr_id", attrIds));
        List<AttrListVo> list = new LinkedList<>();
        for(Attr item : iPage.getRecords()) {
            AttrListVo vo = new AttrListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);

    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationListVo[] vos) {
        List<AttrAttrgroupRelation> entities = Arrays.asList(vos).stream().map((item) -> {
            AttrAttrgroupRelation relationEntity = new AttrAttrgroupRelation();
            BeanUtils.copyProperties(item, relationEntity);
            return relationEntity;
        }).collect(Collectors.toList());
        iAttrAttrgroupRelationService.deleteBatchRelation(entities);
    }

    @Override
    public PageResult<AttrListVo> getNoRelationAttr(PageParam pageParam, Map<String, String> params, Long attrgroupId) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        //1、当前分组只能关联自己所属的分类里面的所有属性
        AttrGroup attrGroup = attrGroupMapper.selectById(attrgroupId);
        final Long catelogId = attrGroup.getCatelogId();
        //2、当前分组只能关联别的分组没有引用的属性
        //2.1)、当前分类下的其他分组
        final List<AttrGroup> groups = attrGroupMapper.selectList(new QueryWrapper<AttrGroup>().eq("catelog_id", catelogId));
        final List<Long> collect = groups.stream().map(item -> {
            return item.getAttrGroupId();
        }).collect(Collectors.toList());
        //2.2)、这些分组关联的属性
        final List<AttrAttrgroupRelation> attr_group_id = attrAttrgroupRelationMapper.selectList(new QueryWrapper<AttrAttrgroupRelation>().in("attr_group_id", collect));
        final List<Long> attrIds = attr_group_id.stream().map(item -> {
            return item.getAttrId();
        }).collect(Collectors.toList());
        //2.3)、从当前分类的所有属性中移除这些属性；
        final QueryWrapper<Attr> wrapper = new QueryWrapper<Attr>().eq("catelog_id", catelogId)
                .eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if(attrIds != null && attrIds.size() > 0) {
            wrapper.notIn("attr_id",attrIds);
        }
        String key = (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.eq("attr_id",key).or().like("attr_name",key);
            });
        }


        IPage<Attr> iPage = attrMapper.selectPage(new Page<>(page, limit), wrapper);
        List<AttrListVo> list = new LinkedList<>();
        for(Attr item : iPage.getRecords()) {
            AttrListVo vo = new AttrListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

}
