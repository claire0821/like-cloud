package com.mdd.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.entity.RefundInfo;
import com.mdd.order.validate.RefundInfoParam;
import com.mdd.order.vo.RefundInfoListVo;
import com.mdd.order.vo.RefundInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 退款信息服务接口类
 */
public interface IRefundInfoService extends IService<RefundInfo> {

    /**
     * 退款信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<RefundInfoVo>
     */
    PageResult<RefundInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 退款信息详情
     *
     * @param id 主键ID
     * @return RefundInfo
     */
    RefundInfoDetailVo detail(Long id);

    /**
     * 退款信息新增
     *
     * @param refundInfoParam 参数
     */
    void add(RefundInfoParam refundInfoParam);

    /**
     * 退款信息编辑
     *
     * @param refundInfoParam 参数
     */
    void edit(RefundInfoParam refundInfoParam);

    /**
     * 退款信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
