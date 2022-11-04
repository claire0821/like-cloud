package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISmsMemberPriceService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsMemberPriceParam;
import com.mdd.coupon.vo.SmsMemberPriceListVo;
import com.mdd.coupon.vo.SmsMemberPriceDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsMemberPrice;
import com.mdd.coupon.mapper.SmsMemberPriceMapper;
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
 * 商品会员价格实现类
 */
@Service
public class SmsMemberPriceServiceImpl implements ISmsMemberPriceService {
        
    @Resource
    SmsMemberPriceMapper smsMemberPriceMapper;

    /**
     * 商品会员价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsMemberPriceListVo>
     */
    @Override
    public PageResult<SmsMemberPriceListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsMemberPrice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsMemberPriceMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:memberLevelId@member_level_id:long",
            "like:memberLevelName@member_level_name:str",
            "=:memberPrice@member_price:str",
            "=:addOther@add_other:int",
        });

        IPage<SmsMemberPrice> iPage = smsMemberPriceMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsMemberPriceListVo> list = new LinkedList<>();
        for(SmsMemberPrice item : iPage.getRecords()) {
            SmsMemberPriceListVo vo = new SmsMemberPriceListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品会员价格详情
     *
     * @param id 主键参数
     * @return SmsMemberPrice
     */
    @Override
    public SmsMemberPriceDetailVo detail(Long id) {
        SmsMemberPrice model = smsMemberPriceMapper.selectOne(
                new QueryWrapper<SmsMemberPrice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsMemberPriceDetailVo vo = new SmsMemberPriceDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品会员价格新增
     *
     * @param smsMemberPriceParam 参数
     */
    @Override
    public void add(SmsMemberPriceParam smsMemberPriceParam) {
        SmsMemberPrice model = new SmsMemberPrice();
        model.setSkuId(smsMemberPriceParam.getSkuId());
        model.setMemberLevelId(smsMemberPriceParam.getMemberLevelId());
        model.setMemberLevelName(smsMemberPriceParam.getMemberLevelName());
        model.setMemberPrice(smsMemberPriceParam.getMemberPrice());
        model.setAddOther(smsMemberPriceParam.getAddOther());
        smsMemberPriceMapper.insert(model);
    }

    /**
     * 商品会员价格编辑
     *
     * @param smsMemberPriceParam 参数
     */
    @Override
    public void edit(SmsMemberPriceParam smsMemberPriceParam) {
        SmsMemberPrice model = smsMemberPriceMapper.selectOne(
                new QueryWrapper<SmsMemberPrice>()
                    .eq("id",  smsMemberPriceParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsMemberPriceParam.getId());
        model.setSkuId(smsMemberPriceParam.getSkuId());
        model.setMemberLevelId(smsMemberPriceParam.getMemberLevelId());
        model.setMemberLevelName(smsMemberPriceParam.getMemberLevelName());
        model.setMemberPrice(smsMemberPriceParam.getMemberPrice());
        model.setAddOther(smsMemberPriceParam.getAddOther());
        smsMemberPriceMapper.updateById(model);
    }

    /**
     * 商品会员价格删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsMemberPrice model = smsMemberPriceMapper.selectOne(
                new QueryWrapper<SmsMemberPrice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsMemberPriceMapper.delete(new QueryWrapper<SmsMemberPrice>().eq("id", id));
    }

}
