package com.mdd.ware.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.ware.entity.Purchase;
import com.mdd.ware.service.IPurchaseService;
import com.mdd.ware.validate.PurchaseParam;
import com.mdd.common.validate.PageParam;
import com.mdd.ware.vo.MergeVo;
import com.mdd.ware.vo.PurchaseDoneVo;
import com.mdd.ware.vo.PurchaseListVo;
import com.mdd.ware.vo.PurchaseDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDLongMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 采购信息管理
 */
@RestController
@RequestMapping("api/ware/purchase")
public class PurchaseController {

    @Resource
    IPurchaseService iPurchaseService;

    /**
     * 采购信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public Object list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<PurchaseListVo> list = iPurchaseService.list(pageParam, params);
        return AjaxResult.success(list);
    }
    /**
     * 采购信息详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDLongMust() @RequestParam("id") Long id) {
        PurchaseDetailVo detail = iPurchaseService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 采购信息新增
     *
     * @param purchaseParam 参数
     * @return Object
     */
    @Log(title = "采购信息新增")
    @PostMapping("/add")
    public Object add(@Validated(value = PurchaseParam.create.class) @RequestBody PurchaseParam purchaseParam) {
        iPurchaseService.add(purchaseParam);
        return AjaxResult.success();
    }

    /**
     * 采购信息编辑
     *
     * @param purchaseParam 参数
     * @return Object
     */
    @Log(title = "采购信息编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = PurchaseParam.update.class) @RequestBody PurchaseParam purchaseParam) {
        iPurchaseService.edit(purchaseParam);
        return AjaxResult.success();
    }


    /**
     * 采购信息批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "采购信息批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Long[] ids) {
        iPurchaseService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }

    @Log(title = "合并到采购单")
    @PostMapping("/merge")
    public Object merge(@RequestBody MergeVo mergeVo){
        iPurchaseService.mergePurchase(mergeVo);
        return AjaxResult.success();
    }

    /**
     * 领取采购单
     * @return
     */
    @Log(title = "领取采购单")
    @PostMapping("/received")
    public Object received(@RequestBody List<Long> ids){
        iPurchaseService.received(ids);
        return AjaxResult.success();
    }

    /**
     * 完成采购单
     * @param doneVo
     * @return
     */
    @Log(title = "完成采购单")
    @PostMapping("/finish")
    public Object finish(@RequestBody PurchaseDoneVo doneVo){
        iPurchaseService.finish(doneVo);
        return AjaxResult.success();
    }

    /**
     * 未领取的采购单
     * @return
     */
    @Log(title = "未领取的采购单")
    @GetMapping("/unreceive/list")
    public Object unreceivelist(){
        List<Purchase> list= iPurchaseService.queryUnreceivePurchase();
        return AjaxResult.success(list);
    }

    @Log(title = "分配采购单")
    @GetMapping("/allot")
    public Object allot(@RequestParam("id") Long id,
                        @RequestParam("assigneeId") Long assigneeId,
                        @RequestParam("assigneeName") String assigneeName) {
        iPurchaseService.allot(id,assigneeId,assigneeName);
        return AjaxResult.success();
    }
}
