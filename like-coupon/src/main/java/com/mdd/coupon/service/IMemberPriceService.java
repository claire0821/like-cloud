package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.MemberPrice;
import com.mdd.coupon.validate.MemberPriceParam;
import com.mdd.coupon.vo.MemberPriceListVo;
import com.mdd.coupon.vo.MemberPriceDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品会员价格服务接口类
 */
public interface IMemberPriceService extends IService<MemberPrice> {

    /**
     * 商品会员价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberPriceVo>
     */
    PageResult<MemberPriceListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品会员价格详情
     *
     * @param id 主键ID
     * @return MemberPrice
     */
    MemberPriceDetailVo detail(Long id);

    /**
     * 商品会员价格新增
     *
     * @param memberPriceParam 参数
     */
    void add(MemberPriceParam memberPriceParam);

    /**
     * 商品会员价格编辑
     *
     * @param memberPriceParam 参数
     */
    void edit(MemberPriceParam memberPriceParam);

    /**
     * 商品会员价格删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
