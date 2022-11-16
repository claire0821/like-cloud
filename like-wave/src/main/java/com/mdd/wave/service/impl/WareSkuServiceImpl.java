package com.mdd.wave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.wave.service.IWareSkuService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.validate.WareSkuParam;
import com.mdd.wave.vo.WareSkuListVo;
import com.mdd.wave.vo.WareSkuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.wave.entity.WareSku;
import com.mdd.wave.mapper.WareSkuMapper;
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
 * 商品库存实现类
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper,WareSku> implements IWareSkuService {
        
    @Resource
    WareSkuMapper wareSkuMapper;

    /**
     * 商品库存列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareSkuListVo>
     */
    @Override
    public PageResult<WareSkuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<WareSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        wareSkuMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:wareId@ware_id:long",
            "=:stock:int",
            "like:skuName@sku_name:str",
            "=:stockLocked@stock_locked:int",
        });

        IPage<WareSku> iPage = wareSkuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<WareSkuListVo> list = new LinkedList<>();
        for(WareSku item : iPage.getRecords()) {
            WareSkuListVo vo = new WareSkuListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品库存详情
     *
     * @param id 主键参数
     * @return WareSku
     */
    @Override
    public WareSkuDetailVo detail(Long id) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        WareSkuDetailVo vo = new WareSkuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品库存新增
     *
     * @param wareSkuParam 参数
     */
    @Override
    public void add(WareSkuParam wareSkuParam) {
        WareSku model = new WareSku();
        model.setSkuId(wareSkuParam.getSkuId());
        model.setWareId(wareSkuParam.getWareId());
        model.setStock(wareSkuParam.getStock());
        model.setSkuName(wareSkuParam.getSkuName());
        model.setStockLocked(wareSkuParam.getStockLocked());
        wareSkuMapper.insert(model);
    }

    /**
     * 商品库存编辑
     *
     * @param wareSkuParam 参数
     */
    @Override
    public void edit(WareSkuParam wareSkuParam) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id",  wareSkuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(wareSkuParam.getId());
        model.setSkuId(wareSkuParam.getSkuId());
        model.setWareId(wareSkuParam.getWareId());
        model.setStock(wareSkuParam.getStock());
        model.setSkuName(wareSkuParam.getSkuName());
        model.setStockLocked(wareSkuParam.getStockLocked());
        wareSkuMapper.updateById(model);
    }

    /**
     * 商品库存删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        WareSku model = wareSkuMapper.selectOne(
                new QueryWrapper<WareSku>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        wareSkuMapper.delete(new QueryWrapper<WareSku>().eq("id", id));
    }

}
