package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsCouponSpuCategoryRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsCouponSpuCategoryRelationParam;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsCouponSpuCategoryRelation;
import com.mdd.coupon.mapper.SmsCouponSpuCategoryRelationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 优惠券分类关联实现类
 */
@Service
public class SmsCouponSpuCategoryRelationServiceImpl implements ISmsCouponSpuCategoryRelationService {
        
    @Resource
    SmsCouponSpuCategoryRelationMapper smsCouponSpuCategoryRelationMapper;

    /**
     * 优惠券分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponSpuCategoryRelationListVo>
     */
    @Override
    public PageResult<SmsCouponSpuCategoryRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsCouponSpuCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsCouponSpuCategoryRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:categoryId@category_id:long",
            "like:categoryName@category_name:str",
        });

        IPage<SmsCouponSpuCategoryRelation> iPage = smsCouponSpuCategoryRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsCouponSpuCategoryRelationListVo> list = new LinkedList<>();
        for(SmsCouponSpuCategoryRelation item : iPage.getRecords()) {
            SmsCouponSpuCategoryRelationListVo vo = new SmsCouponSpuCategoryRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券分类关联详情
     *
     * @param id 主键参数
     * @return SmsCouponSpuCategoryRelation
     */
    @Override
    public SmsCouponSpuCategoryRelationDetailVo detail(Long id) {
        SmsCouponSpuCategoryRelation model = smsCouponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsCouponSpuCategoryRelationDetailVo vo = new SmsCouponSpuCategoryRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券分类关联新增
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     */
    @Override
    public void add(SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam) {
        SmsCouponSpuCategoryRelation model = new SmsCouponSpuCategoryRelation();
        model.setCouponId(smsCouponSpuCategoryRelationParam.getCouponId());
        model.setCategoryId(smsCouponSpuCategoryRelationParam.getCategoryId());
        model.setCategoryName(smsCouponSpuCategoryRelationParam.getCategoryName());
        smsCouponSpuCategoryRelationMapper.insert(model);
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param smsCouponSpuCategoryRelationParam 参数
     */
    @Override
    public void edit(SmsCouponSpuCategoryRelationParam smsCouponSpuCategoryRelationParam) {
        SmsCouponSpuCategoryRelation model = smsCouponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuCategoryRelation>()
                    .eq("id",  smsCouponSpuCategoryRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsCouponSpuCategoryRelationParam.getId());
        model.setCouponId(smsCouponSpuCategoryRelationParam.getCouponId());
        model.setCategoryId(smsCouponSpuCategoryRelationParam.getCategoryId());
        model.setCategoryName(smsCouponSpuCategoryRelationParam.getCategoryName());
        smsCouponSpuCategoryRelationMapper.updateById(model);
    }

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsCouponSpuCategoryRelation model = smsCouponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsCouponSpuCategoryRelationMapper.delete(new QueryWrapper<SmsCouponSpuCategoryRelation>().eq("id", id));
    }

}
