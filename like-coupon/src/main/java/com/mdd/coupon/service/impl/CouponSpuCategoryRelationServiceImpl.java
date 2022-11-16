package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ICouponSpuCategoryRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponSpuCategoryRelationParam;
import com.mdd.coupon.vo.CouponSpuCategoryRelationListVo;
import com.mdd.coupon.vo.CouponSpuCategoryRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.CouponSpuCategoryRelation;
import com.mdd.coupon.mapper.CouponSpuCategoryRelationMapper;
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
 * 优惠券分类关联实现类
 */
@Service
public class CouponSpuCategoryRelationServiceImpl extends ServiceImpl<CouponSpuCategoryRelationMapper,CouponSpuCategoryRelation> implements ICouponSpuCategoryRelationService {
        
    @Resource
    CouponSpuCategoryRelationMapper couponSpuCategoryRelationMapper;

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

        QueryWrapper<CouponSpuCategoryRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        couponSpuCategoryRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:categoryId@category_id:long",
            "like:categoryName@category_name:str",
        });

        IPage<CouponSpuCategoryRelation> iPage = couponSpuCategoryRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponSpuCategoryRelationListVo> list = new LinkedList<>();
        for(CouponSpuCategoryRelation item : iPage.getRecords()) {
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
    public CouponSpuCategoryRelationDetailVo detail(Long id) {
        CouponSpuCategoryRelation model = couponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponSpuCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponSpuCategoryRelationDetailVo vo = new CouponSpuCategoryRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券分类关联新增
     *
     * @param couponSpuCategoryRelationParam 参数
     */
    @Override
    public void add(CouponSpuCategoryRelationParam couponSpuCategoryRelationParam) {
        CouponSpuCategoryRelation model = new CouponSpuCategoryRelation();
        model.setCouponId(couponSpuCategoryRelationParam.getCouponId());
        model.setCategoryId(couponSpuCategoryRelationParam.getCategoryId());
        model.setCategoryName(couponSpuCategoryRelationParam.getCategoryName());
        couponSpuCategoryRelationMapper.insert(model);
    }

    /**
     * 优惠券分类关联编辑
     *
     * @param couponSpuCategoryRelationParam 参数
     */
    @Override
    public void edit(CouponSpuCategoryRelationParam couponSpuCategoryRelationParam) {
        CouponSpuCategoryRelation model = couponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponSpuCategoryRelation>()
                    .eq("id",  couponSpuCategoryRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(couponSpuCategoryRelationParam.getId());
        model.setCouponId(couponSpuCategoryRelationParam.getCouponId());
        model.setCategoryId(couponSpuCategoryRelationParam.getCategoryId());
        model.setCategoryName(couponSpuCategoryRelationParam.getCategoryName());
        couponSpuCategoryRelationMapper.updateById(model);
    }

    /**
     * 优惠券分类关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CouponSpuCategoryRelation model = couponSpuCategoryRelationMapper.selectOne(
                new QueryWrapper<CouponSpuCategoryRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponSpuCategoryRelationMapper.delete(new QueryWrapper<CouponSpuCategoryRelation>().eq("id", id));
    }

}
