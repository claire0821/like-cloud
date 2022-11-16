package com.mdd.wave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.wave.service.IPurchaseService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.validate.PurchaseParam;
import com.mdd.wave.vo.PurchaseListVo;
import com.mdd.wave.vo.PurchaseDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.wave.entity.Purchase;
import com.mdd.wave.mapper.PurchaseMapper;
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
 * 采购信息实现类
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper,Purchase> implements IPurchaseService {
        
    @Resource
    PurchaseMapper purchaseMapper;

    /**
     * 采购信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PurchaseListVo>
     */
    @Override
    public PageResult<PurchaseListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Purchase> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        purchaseMapper.setSearch(queryWrapper, params, new String[]{
            "=:assigneeId@assignee_id:long",
            "like:assigneeName@assignee_name:str",
            "=:phone:str",
            "=:priority:int",
            "=:status:int",
            "=:wareId@ware_id:long",
            "=:amount:str",
        });

        IPage<Purchase> iPage = purchaseMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PurchaseListVo> list = new LinkedList<>();
        for(Purchase item : iPage.getRecords()) {
            PurchaseListVo vo = new PurchaseListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(item.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 采购信息详情
     *
     * @param id 主键参数
     * @return Purchase
     */
    @Override
    public PurchaseDetailVo detail(Long id) {
        Purchase model = purchaseMapper.selectOne(
                new QueryWrapper<Purchase>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PurchaseDetailVo vo = new PurchaseDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 采购信息新增
     *
     * @param purchaseParam 参数
     */
    @Override
    public void add(PurchaseParam purchaseParam) {
        Purchase model = new Purchase();
        model.setAssigneeId(purchaseParam.getAssigneeId());
        model.setAssigneeName(purchaseParam.getAssigneeName());
        model.setPhone(purchaseParam.getPhone());
        model.setPriority(purchaseParam.getPriority());
        model.setStatus(purchaseParam.getStatus());
        model.setWareId(purchaseParam.getWareId());
        model.setAmount(purchaseParam.getAmount());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        purchaseMapper.insert(model);
    }

    /**
     * 采购信息编辑
     *
     * @param purchaseParam 参数
     */
    @Override
    public void edit(PurchaseParam purchaseParam) {
        Purchase model = purchaseMapper.selectOne(
                new QueryWrapper<Purchase>()
                    .eq("id",  purchaseParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(purchaseParam.getId());
        model.setAssigneeId(purchaseParam.getAssigneeId());
        model.setAssigneeName(purchaseParam.getAssigneeName());
        model.setPhone(purchaseParam.getPhone());
        model.setPriority(purchaseParam.getPriority());
        model.setStatus(purchaseParam.getStatus());
        model.setWareId(purchaseParam.getWareId());
        model.setAmount(purchaseParam.getAmount());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        purchaseMapper.updateById(model);
    }

    /**
     * 采购信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Purchase model = purchaseMapper.selectOne(
                new QueryWrapper<Purchase>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        purchaseMapper.delete(new QueryWrapper<Purchase>().eq("id", id));
    }

}
