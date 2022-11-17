package com.mdd.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.constant.WareConstant;
import com.mdd.common.exception.OperateException;
import com.mdd.ware.entity.PurchaseDetail;
import com.mdd.ware.service.IPurchaseDetailService;
import com.mdd.ware.service.IPurchaseService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.service.IWareSkuService;
import com.mdd.ware.validate.PurchaseParam;
import com.mdd.ware.vo.*;
import com.mdd.common.core.PageResult;
import com.mdd.ware.entity.Purchase;
import com.mdd.ware.mapper.PurchaseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 采购信息实现类
 */
@Service
public class PurchaseServiceImpl extends ServiceImpl<PurchaseMapper,Purchase> implements IPurchaseService {
        
    @Resource
    PurchaseMapper purchaseMapper;
    @Autowired
    IPurchaseDetailService iPurchaseDetailService;
    @Autowired
    IWareSkuService iWareSkuService;

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            vo.setUpdateTime(simpleDateFormat.format(item.getUpdateTime()));
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
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
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
        model.setUpdateTime(new Date());
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

    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId == null) {
            Purchase purchase = new Purchase();
            purchase.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            purchase.setCreateTime(new Date());
            purchase.setUpdateTime(new Date());
            this.save(purchase);
            purchaseId = purchase.getId();
        }

        //TODO 确认采购单状态是0,1才可以合并
        //更改状态
        final List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        final List<PurchaseDetail> collect = items.stream().map(item -> {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(item);
            purchaseDetail.setPurchaseId(finalPurchaseId);
            purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
            return purchaseDetail;
        }).collect(Collectors.toList());
        iPurchaseDetailService.updateBatchById(collect);

        //更新更新时间
        Purchase purchase = new Purchase();
        purchase.setId(purchaseId);
        purchase.setUpdateTime(new Date());
        this.updateById(purchase);
    }

    /**
     *
     * @param ids 采购单id
     */
    @Override
    public void received(List<Long> ids) {
        //1确认当前采购单是新建或者已分配状态
        final List<Purchase> collect = ids.stream().map(id -> {
            Purchase purchase = this.getById(id);
            return purchase;
        }).filter(item -> {
            if (item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode()
                    || item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                return true;
            }
            return false;
        }).map(item -> {
            item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
            item.setUpdateTime(new Date());
            return item;
        }).collect(Collectors.toList());
        //2改变采购单的状态
        this.updateBatchById(collect);
        //3改变采购项状态
        collect.forEach(item -> {
            List<PurchaseDetail> entities = iPurchaseDetailService.listDetailByPurchaseId(item.getId());
            final List<PurchaseDetail> collect1 = entities.stream().map(entity -> {
                PurchaseDetail purchaseDetail = new PurchaseDetail();
                purchaseDetail.setPurchaseId(entity.getId());
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return purchaseDetail;
            }).collect(Collectors.toList());
            iPurchaseDetailService.updateBatchById(collect1);
        });
    }

    @Transactional
    @Override
    public void finish(PurchaseDoneVo doneVo) {
        final Long id = doneVo.getId();

        //改变采购项状态
        //TODO 采购项表增加应采购数量、实际采购数量、采购失败原因
        Boolean flag = true;
        final List<PurchaseItemDoneVo> items = doneVo.getItems();
        List<PurchaseDetail> updates = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetail purchaseDetail = new PurchaseDetail();
            purchaseDetail.setId(item.getItemId());
            if(item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HASERROR.getCode()) {
                flag = false;
                purchaseDetail.setStatus(item.getStatus());
            } else {
                purchaseDetail.setStatus(WareConstant.PurchaseDetailStatusEnum.FINISH.getCode());
                //将成功采购发进行入库
                PurchaseDetail entity = iPurchaseDetailService.getById(item.getItemId());
                iWareSkuService.addStock(entity.getSkuId(), entity.getWareId(), entity.getSkuNum());
            }
            purchaseDetail.setId(item.getItemId());
            updates.add(purchaseDetail);
        }
        iPurchaseDetailService.updateBatchById(updates);

        //改变采购单状态
        Purchase purchase = new Purchase();
        purchase.setId(id);
        purchase.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.HASERROR.getCode());
        purchase.setUpdateTime(new Date());
        updateById(purchase);
    }

    @Override
    public List<Purchase> queryUnreceivePurchase() {
        final List<Purchase> list = this.list(new QueryWrapper<Purchase>()
                .eq("status", WareConstant.PurchaseStatusEnum.CREATED.getCode()).or().eq("status", WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()));
        return list;
    }

    //分配采购单
    @Override
    public void allot(Long id,Long assigneeId, String assigneeName) {
        final Purchase byId = this.getById(id);
        if(byId.getStatus().equals(WareConstant.PurchaseStatusEnum.CREATED.getCode()) || byId.getStatus().equals(WareConstant.PurchaseStatusEnum.ASSIGNED.getCode())) {
            byId.setAssigneeId(assigneeId);
            byId.setAssigneeName(assigneeName);
            byId.setStatus(WareConstant.PurchaseStatusEnum.ASSIGNED.getCode());
            byId.setUpdateTime(new Date());
            this.updateById(byId);
            return;
        }
        throw new OperateException("采购单分配失败!");
    }

}
