package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsSkuLadderParam;
import com.mdd.coupon.vo.SmsSkuLadderListVo;
import com.mdd.coupon.vo.SmsSkuLadderDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品阶梯价格服务接口类
 */
public interface ISmsSkuLadderService {

    /**
     * 商品阶梯价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSkuLadderVo>
     */
    PageResult<SmsSkuLadderListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品阶梯价格详情
     *
     * @param id 主键ID
     * @return SmsSkuLadder
     */
    SmsSkuLadderDetailVo detail(Long id);

    /**
     * 商品阶梯价格新增
     *
     * @param smsSkuLadderParam 参数
     */
    void add(SmsSkuLadderParam smsSkuLadderParam);

    /**
     * 商品阶梯价格编辑
     *
     * @param smsSkuLadderParam 参数
     */
    void edit(SmsSkuLadderParam smsSkuLadderParam);

    /**
     * 商品阶梯价格删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
