package com.mdd.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.ware.feign.ProductFeignService;
import com.mdd.ware.service.IWareSkuService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.validate.WareSkuParam;
import com.mdd.ware.vo.SkuHasStockVo;
import com.mdd.ware.vo.WareSkuListVo;
import com.mdd.ware.vo.WareSkuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.WareSku;
import com.mdd.ware.mapper.WareSkuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品库存实现类
 */
@Service
public class WareSkuServiceImpl extends ServiceImpl<WareSkuMapper,WareSku> implements IWareSkuService {
        
    @Resource
    WareSkuMapper wareSkuMapper;
    @Autowired
    ProductFeignService productFeignService;

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

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1、判断如果还没有这个库存记录新增
        final List<WareSku> wareSkus = wareSkuMapper.selectList(new QueryWrapper<WareSku>()
                .eq("sku_id", skuId).eq("ware_id", wareId));
        if(wareSkus == null || wareSkus.size() <= 0) {
            WareSku wareSku = new WareSku();
            wareSku.setSkuId(skuId);
            wareSku.setStock(skuNum);
            wareSku.setWareId(wareId);
            wareSku.setStockLocked(0);
            //TODO 远程查询sku的名字，如果失败，整个事务无需回滚
            //1、自己catch异常
            //TODO 还可以用什么办法让异常出现以后不回滚？高级
            try{
                final LinkedHashMap detail = (LinkedHashMap) productFeignService.detail(skuId);
                final Integer code = (Integer) detail.get("code");
                if(code == 0) {
                    final LinkedHashMap data = (LinkedHashMap) detail.get("data");
                    final String skuName = (String) data.get("skuName");
                    wareSku.setSkuName(skuName);
                }
            } catch (Exception e) {

            }
            wareSkuMapper.insert(wareSku);
        } else {
            for (WareSku skus : wareSkus) {
                skus.setStock(skus.getStock() + skuNum);
                wareSkuMapper.updateById(skus);
            }
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {
        final List<SkuHasStockVo> collect = skuIds.stream().map(item -> {
            final WareSku byId = this.getById(item);
            SkuHasStockVo skuHasStockVo = new SkuHasStockVo();
            skuHasStockVo.setSkuId(item);
            skuHasStockVo.setHasStock(byId.getStock() - byId.getStockLocked() > 0 ? true : false);
            return skuHasStockVo;
        }).collect(Collectors.toList());
        return collect;
    }

}
