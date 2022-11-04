package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsCouponSpuRelationService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsCouponSpuRelationParam;
import com.mdd.coupon.vo.SmsCouponSpuRelationListVo;
import com.mdd.coupon.vo.SmsCouponSpuRelationDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsCouponSpuRelation;
import com.mdd.coupon.mapper.SmsCouponSpuRelationMapper;
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
public class SmsCouponSpuRelationServiceImpl implements ISmsCouponSpuRelationService {
        
    @Resource
    SmsCouponSpuRelationMapper smsCouponSpuRelationMapper;

    /**
     * 优惠券与产品关联列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsCouponSpuRelationListVo>
     */
    @Override
    public PageResult<SmsCouponSpuRelationListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsCouponSpuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsCouponSpuRelationMapper.setSearch(queryWrapper, params, new String[]{
            "=:couponId@coupon_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
        });

        IPage<SmsCouponSpuRelation> iPage = smsCouponSpuRelationMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsCouponSpuRelationListVo> list = new LinkedList<>();
        for(SmsCouponSpuRelation item : iPage.getRecords()) {
            SmsCouponSpuRelationListVo vo = new SmsCouponSpuRelationListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券与产品关联详情
     *
     * @param id 主键参数
     * @return SmsCouponSpuRelation
     */
    @Override
    public SmsCouponSpuRelationDetailVo detail(Long id) {
        SmsCouponSpuRelation model = smsCouponSpuRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsCouponSpuRelationDetailVo vo = new SmsCouponSpuRelationDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 优惠券与产品关联新增
     *
     * @param smsCouponSpuRelationParam 参数
     */
    @Override
    public void add(SmsCouponSpuRelationParam smsCouponSpuRelationParam) {
        SmsCouponSpuRelation model = new SmsCouponSpuRelation();
        model.setCouponId(smsCouponSpuRelationParam.getCouponId());
        model.setSpuId(smsCouponSpuRelationParam.getSpuId());
        model.setSpuName(smsCouponSpuRelationParam.getSpuName());
        smsCouponSpuRelationMapper.insert(model);
    }

    /**
     * 优惠券与产品关联编辑
     *
     * @param smsCouponSpuRelationParam 参数
     */
    @Override
    public void edit(SmsCouponSpuRelationParam smsCouponSpuRelationParam) {
        SmsCouponSpuRelation model = smsCouponSpuRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuRelation>()
                    .eq("id",  smsCouponSpuRelationParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsCouponSpuRelationParam.getId());
        model.setCouponId(smsCouponSpuRelationParam.getCouponId());
        model.setSpuId(smsCouponSpuRelationParam.getSpuId());
        model.setSpuName(smsCouponSpuRelationParam.getSpuName());
        smsCouponSpuRelationMapper.updateById(model);
    }

    /**
     * 优惠券与产品关联删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsCouponSpuRelation model = smsCouponSpuRelationMapper.selectOne(
                new QueryWrapper<SmsCouponSpuRelation>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsCouponSpuRelationMapper.delete(new QueryWrapper<SmsCouponSpuRelation>().eq("id", id));
    }

}
