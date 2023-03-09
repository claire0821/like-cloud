package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.coupon.service.ICouponCategoryRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponCategoryRelationParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.CouponCategoryRelation;
import com.mdd.coupon.mapper.CouponCategoryRelationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 优惠券分类关联实现类
 */
@Service
public class CouponCategoryRelationServiceImpl extends ServiceImpl<CouponCategoryRelationMapper, CouponCategoryRelation> implements ICouponCategoryRelationService {
        
    @Resource
    CouponCategoryRelationMapper couponCategoryRelationMapper;

    /**
     * 优惠券分类关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponSpuCategoryRelationListVo>
     */
    @Override
    public PageResult<CouponSpuCategoryRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<CouponCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        couponCategoryRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:categoryId@category_id:long",
            "like:categoryName@category_name:str",
        });

        IPage<CouponCategoryRelation> iPage = couponCategoryRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponSpuCategoryRelationListVo> list = new LinkedList<>();
        for(CouponCategoryRelation item : iPage.getRecords()) {
            CouponSpuCategoryRelationListVo vo = new CouponSpuCategoryRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券分类关联详情
     *
     * @param id 主键参数
     * @return CouponSpuCategoryRelation
     */
    @Override
    public CouponCategoryRelationDetailVo detail(Long id) {
        CouponCategoryRelation model = couponCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponCategoryRelationDetailVo vo = new CouponCategoryRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券分类关联新增
     *
     * @param couponCategoryRelationParam 参数
     */
    @Override
    public void add(CouponCategoryRelationParam couponCategoryRelationParam) {
        CouponCategoryRelation model = new CouponCategoryRelation();
        model.setCouponId(couponCategoryRelationParam.getCouponId());
        model.setCategoryId(couponCategoryRelationParam.getCategoryId());
        model.setCategoryName(couponCategoryRelationParam.getCategoryName());
        couponCategoryRelationMapper.insert(model);
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param couponCategoryRelationParam 参数
     */
    @Override
    public void edit(CouponCategoryRelationParam couponCategoryRelationParam) {
        CouponCategoryRelation model = couponCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponCategoryRelation>()
                    .eq("id",  couponCategoryRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(couponCategoryRelationParam.getId());
        model.setCouponId(couponCategoryRelationParam.getCouponId());
        model.setCategoryId(couponCategoryRelationParam.getCategoryId());
        model.setCategoryName(couponCategoryRelationParam.getCategoryName());
        couponCategoryRelationMapper.updateById(model);
    }

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CouponCategoryRelation model = couponCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponCategoryRelationMapper.delete(new QueryWrapper<CouponCategoryRelation>().eq("id", id));
    }

}
