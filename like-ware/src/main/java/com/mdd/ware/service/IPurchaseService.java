package com.mdd.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.entity.Purchase;
import com.mdd.ware.validate.PurchaseParam;
import com.mdd.ware.vo.MergeVo;
import com.mdd.ware.vo.PurchaseDoneVo;
import com.mdd.ware.vo.PurchaseListVo;
import com.mdd.ware.vo.PurchaseDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 采购信息服务接口类
 */
public interface IPurchaseService extends IService<Purchase> {

    /**
     * 采购信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PurchaseVo>
     */
    PageResult<PurchaseListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 采购信息详情
     *
     * @param id 主键ID
     * @return Purchase
     */
    PurchaseDetailVo detail(Long id);

    /**
     * 采购信息新增
     *
     * @param purchaseParam 参数
     */
    void add(PurchaseParam purchaseParam);

    /**
     * 采购信息编辑
     *
     * @param purchaseParam 参数
     */
    void edit(PurchaseParam purchaseParam);

    /**
     * 采购信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void finish(PurchaseDoneVo doneVo);

    List<Purchase> queryUnreceivePurchase();

    void allot(Long id, Long assigneeId, String assigneeName);
}
