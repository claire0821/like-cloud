package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISkuLadderService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SkuLadderParam;
import com.mdd.coupon.vo.SkuLadderListVo;
import com.mdd.coupon.vo.SkuLadderDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SkuLadder;
import com.mdd.coupon.mapper.SkuLadderMapper;
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
 * 商品阶梯价格实现类
 */
@Service
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderMapper,SkuLadder> implements ISkuLadderService {
        
    @Resource
    SkuLadderMapper skuLadderMapper;

    /**
     * 商品阶梯价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuLadderListVo>
     */
    @Override
    public PageResult<SkuLadderListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SkuLadder> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        skuLadderMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:fullCount@full_count:int",
            "=:discount:str",
            "=:price:str",
            "=:addOther@add_other:int",
        });

        IPage<SkuLadder> iPage = skuLadderMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SkuLadderListVo> list = new LinkedList<>();
        for(SkuLadder item : iPage.getRecords()) {
            SkuLadderListVo vo = new SkuLadderListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品阶梯价格详情
     *
     * @param id 主键参数
     * @return SkuLadder
     */
    @Override
    public SkuLadderDetailVo detail(Long id) {
        SkuLadder model = skuLadderMapper.selectOne(
                new QueryWrapper<SkuLadder>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SkuLadderDetailVo vo = new SkuLadderDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品阶梯价格新增
     *
     * @param skuLadderParam 参数
     */
    @Override
    public void add(SkuLadderParam skuLadderParam) {
        SkuLadder model = new SkuLadder();
        model.setSkuId(skuLadderParam.getSkuId());
        model.setFullCount(skuLadderParam.getFullCount());
        model.setDiscount(skuLadderParam.getDiscount());
        model.setPrice(skuLadderParam.getPrice());
        model.setAddOther(skuLadderParam.getAddOther());
        skuLadderMapper.insert(model);
    }

    /**
     * 商品阶梯价格编辑
     *
     * @param skuLadderParam 参数
     */
    @Override
    public void edit(SkuLadderParam skuLadderParam) {
        SkuLadder model = skuLadderMapper.selectOne(
                new QueryWrapper<SkuLadder>()
                    .eq("id",  skuLadderParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(skuLadderParam.getId());
        model.setSkuId(skuLadderParam.getSkuId());
        model.setFullCount(skuLadderParam.getFullCount());
        model.setDiscount(skuLadderParam.getDiscount());
        model.setPrice(skuLadderParam.getPrice());
        model.setAddOther(skuLadderParam.getAddOther());
        skuLadderMapper.updateById(model);
    }

    /**
     * 商品阶梯价格删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SkuLadder model = skuLadderMapper.selectOne(
                new QueryWrapper<SkuLadder>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        skuLadderMapper.delete(new QueryWrapper<SkuLadder>().eq("id", id));
    }

}
