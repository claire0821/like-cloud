package com.mdd.coupon.service;

import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsMemberPriceParam;
import com.mdd.coupon.vo.SmsMemberPriceListVo;
import com.mdd.coupon.vo.SmsMemberPriceDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品会员价格服务接口类
 */
public interface ISmsMemberPriceService {

    /**
     * 商品会员价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsMemberPriceVo>
     */
    PageResult<SmsMemberPriceListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品会员价格详情
     *
     * @param id 主键ID
     * @return SmsMemberPrice
     */
    SmsMemberPriceDetailVo detail(Long id);

    /**
     * 商品会员价格新增
     *
     * @param smsMemberPriceParam 参数
     */
    void add(SmsMemberPriceParam smsMemberPriceParam);

    /**
     * 商品会员价格编辑
     *
     * @param smsMemberPriceParam 参数
     */
    void edit(SmsMemberPriceParam smsMemberPriceParam);

    /**
     * 商品会员价格删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
