package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.IMemberPriceService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.MemberPriceParam;
import com.mdd.coupon.vo.MemberPriceListVo;
import com.mdd.coupon.vo.MemberPriceDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.MemberPrice;
import com.mdd.coupon.mapper.MemberPriceMapper;
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
public class MemberPriceServiceImpl extends ServiceImpl<MemberPriceMapper,MemberPrice> implements IMemberPriceService {
        
    @Resource
    MemberPriceMapper memberPriceMapper;

    /**
     * 商品会员价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberPriceListVo>
     */
    @Override
    public PageResult<MemberPriceListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberPrice> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberPriceMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:memberLevelId@member_level_id:long",
            "like:memberLevelName@member_level_name:str",
            "=:memberPrice@member_price:str",
            "=:addOther@add_other:int",
        });

        IPage<MemberPrice> iPage = memberPriceMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberPriceListVo> list = new LinkedList<>();
        for(MemberPrice item : iPage.getRecords()) {
            MemberPriceListVo vo = new MemberPriceListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品会员价格详情
     *
     * @param id 主键参数
     * @return MemberPrice
     */
    @Override
    public MemberPriceDetailVo detail(Long id) {
        MemberPrice model = memberPriceMapper.selectOne(
                new QueryWrapper<MemberPrice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberPriceDetailVo vo = new MemberPriceDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品会员价格新增
     *
     * @param memberPriceParam 参数
     */
    @Override
    public void add(MemberPriceParam memberPriceParam) {
        MemberPrice model = new MemberPrice();
        model.setSkuId(memberPriceParam.getSkuId());
        model.setMemberLevelId(memberPriceParam.getMemberLevelId());
        model.setMemberLevelName(memberPriceParam.getMemberLevelName());
        model.setMemberPrice(memberPriceParam.getMemberPrice());
        model.setAddOther(memberPriceParam.getAddOther());
        memberPriceMapper.insert(model);
    }

    /**
     * 商品会员价格编辑
     *
     * @param memberPriceParam 参数
     */
    @Override
    public void edit(MemberPriceParam memberPriceParam) {
        MemberPrice model = memberPriceMapper.selectOne(
                new QueryWrapper<MemberPrice>()
                    .eq("id",  memberPriceParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberPriceParam.getId());
        model.setSkuId(memberPriceParam.getSkuId());
        model.setMemberLevelId(memberPriceParam.getMemberLevelId());
        model.setMemberLevelName(memberPriceParam.getMemberLevelName());
        model.setMemberPrice(memberPriceParam.getMemberPrice());
        model.setAddOther(memberPriceParam.getAddOther());
        memberPriceMapper.updateById(model);
    }

    /**
     * 商品会员价格删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberPrice model = memberPriceMapper.selectOne(
                new QueryWrapper<MemberPrice>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberPriceMapper.delete(new QueryWrapper<MemberPrice>().eq("id", id));
    }

}
