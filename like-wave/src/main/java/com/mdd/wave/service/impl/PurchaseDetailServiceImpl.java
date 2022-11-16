package com.mdd.wave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.wave.service.IPurchaseDetailService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.validate.PurchaseDetailParam;
import com.mdd.wave.vo.PurchaseDetailListVo;
import com.mdd.wave.vo.PurchaseDetailDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.wave.entity.PurchaseDetail;
import com.mdd.wave.mapper.PurchaseDetailMapper;
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
 * 【请填写功能名称】实现类
 */
@Service
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailMapper,PurchaseDetail> implements IPurchaseDetailService {
        
    @Resource
    PurchaseDetailMapper purchaseDetailMapper;

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PurchaseDetailListVo>
     */
    @Override
    public PageResult<PurchaseDetailListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PurchaseDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        purchaseDetailMapper.setSearch(queryWrapper, params, new String[]{
            "=:purchaseId@purchase_id:long",
            "=:skuId@sku_id:long",
            "=:skuNum@sku_num:int",
            "=:skuPrice@sku_price:str",
            "=:wareId@ware_id:long",
            "=:status:int",
        });

        IPage<PurchaseDetail> iPage = purchaseDetailMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PurchaseDetailListVo> list = new LinkedList<>();
        for(PurchaseDetail item : iPage.getRecords()) {
            PurchaseDetailListVo vo = new PurchaseDetailListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键参数
     * @return PurchaseDetail
     */
    @Override
    public PurchaseDetailDetailVo detail(Long id) {
        PurchaseDetail model = purchaseDetailMapper.selectOne(
                new QueryWrapper<PurchaseDetail>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PurchaseDetailDetailVo vo = new PurchaseDetailDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 【请填写功能名称】新增
     *
     * @param purchaseDetailParam 参数
     */
    @Override
    public void add(PurchaseDetailParam purchaseDetailParam) {
        PurchaseDetail model = new PurchaseDetail();
        model.setPurchaseId(purchaseDetailParam.getPurchaseId());
        model.setSkuId(purchaseDetailParam.getSkuId());
        model.setSkuNum(purchaseDetailParam.getSkuNum());
        model.setSkuPrice(purchaseDetailParam.getSkuPrice());
        model.setWareId(purchaseDetailParam.getWareId());
        model.setStatus(purchaseDetailParam.getStatus());
        purchaseDetailMapper.insert(model);
    }

    /**
     * 【请填写功能名称】编辑
     *
     * @param purchaseDetailParam 参数
     */
    @Override
    public void edit(PurchaseDetailParam purchaseDetailParam) {
        PurchaseDetail model = purchaseDetailMapper.selectOne(
                new QueryWrapper<PurchaseDetail>()
                    .eq("id",  purchaseDetailParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(purchaseDetailParam.getId());
        model.setPurchaseId(purchaseDetailParam.getPurchaseId());
        model.setSkuId(purchaseDetailParam.getSkuId());
        model.setSkuNum(purchaseDetailParam.getSkuNum());
        model.setSkuPrice(purchaseDetailParam.getSkuPrice());
        model.setWareId(purchaseDetailParam.getWareId());
        model.setStatus(purchaseDetailParam.getStatus());
        purchaseDetailMapper.updateById(model);
    }

    /**
     * 【请填写功能名称】删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PurchaseDetail model = purchaseDetailMapper.selectOne(
                new QueryWrapper<PurchaseDetail>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        purchaseDetailMapper.delete(new QueryWrapper<PurchaseDetail>().eq("id", id));
    }

}
