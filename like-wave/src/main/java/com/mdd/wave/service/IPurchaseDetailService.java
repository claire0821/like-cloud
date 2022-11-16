package com.mdd.wave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.entity.PurchaseDetail;
import com.mdd.wave.validate.PurchaseDetailParam;
import com.mdd.wave.vo.PurchaseDetailListVo;
import com.mdd.wave.vo.PurchaseDetailDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 【请填写功能名称】服务接口类
 */
public interface IPurchaseDetailService extends IService<PurchaseDetail> {

    /**
     * 【请填写功能名称】列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PurchaseDetailVo>
     */
    PageResult<PurchaseDetailListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 【请填写功能名称】详情
     *
     * @param id 主键ID
     * @return PurchaseDetail
     */
    PurchaseDetailDetailVo detail(Long id);

    /**
     * 【请填写功能名称】新增
     *
     * @param purchaseDetailParam 参数
     */
    void add(PurchaseDetailParam purchaseDetailParam);

    /**
     * 【请填写功能名称】编辑
     *
     * @param purchaseDetailParam 参数
     */
    void edit(PurchaseDetailParam purchaseDetailParam);

    /**
     * 【请填写功能名称】删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
