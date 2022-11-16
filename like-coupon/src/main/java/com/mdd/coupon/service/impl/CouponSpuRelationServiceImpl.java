package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ICouponSpuRelationService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponSpuRelationParam;
import com.mdd.coupon.vo.CouponSpuRelationListVo;
import com.mdd.coupon.vo.CouponSpuRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.CouponSpuRelation;
import com.mdd.coupon.mapper.CouponSpuRelationMapper;
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
 * 优惠券与产品关联实现类
 */
@Service
public class CouponSpuRelationServiceImpl extends ServiceImpl<CouponSpuRelationMapper,CouponSpuRelation> implements ICouponSpuRelationService {
        
    @Resource
    CouponSpuRelationMapper couponSpuRelationMapper;

    /**
     * 优惠券与产品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CouponSpuRelationListVo>
     */
    @Override
    public PageResult<CouponSpuRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<CouponSpuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        couponSpuRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
        });

        IPage<CouponSpuRelation> iPage = couponSpuRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponSpuRelationListVo> list = new LinkedList<>();
        for(CouponSpuRelation item : iPage.getRecords()) {
            CouponSpuRelationListVo vo = new CouponSpuRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键参数
     * @return CouponSpuRelation
     */
    @Override
    public CouponSpuRelationDetailVo detail(Long id) {
        CouponSpuRelation model = couponSpuRelationMapper.selectOne(
                new QueryWrapper<CouponSpuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponSpuRelationDetailVo vo = new CouponSpuRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券与产品关联新增
     *
     * @param couponSpuRelationParam 参数
     */
    @Override
    public void add(CouponSpuRelationParam couponSpuRelationParam) {
        CouponSpuRelation model = new CouponSpuRelation();
        model.setCouponId(couponSpuRelationParam.getCouponId());
        model.setSpuId(couponSpuRelationParam.getSpuId());
        model.setSpuName(couponSpuRelationParam.getSpuName());
        couponSpuRelationMapper.insert(model);
    }

    /**
     * 优惠券与产品关联编辑
     *
     * @param couponSpuRelationParam 参数
     */
    @Override
    public void edit(CouponSpuRelationParam couponSpuRelationParam) {
        CouponSpuRelation model = couponSpuRelationMapper.selectOne(
                new QueryWrapper<CouponSpuRelation>()
                    .eq("id",  couponSpuRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(couponSpuRelationParam.getId());
        model.setCouponId(couponSpuRelationParam.getCouponId());
        model.setSpuId(couponSpuRelationParam.getSpuId());
        model.setSpuName(couponSpuRelationParam.getSpuName());
        couponSpuRelationMapper.updateById(model);
    }

    /**
     * 优惠券与产品关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CouponSpuRelation model = couponSpuRelationMapper.selectOne(
                new QueryWrapper<CouponSpuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponSpuRelationMapper.delete(new QueryWrapper<CouponSpuRelation>().eq("id", id));
    }

}
