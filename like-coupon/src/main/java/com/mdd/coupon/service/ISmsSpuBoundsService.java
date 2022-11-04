package com.mdd.coupon.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.coupon.validate.SmsSpuBoundsParam;
import com.mdd.coupon.vo.SmsSpuBoundsListVo;
import com.mdd.coupon.vo.SmsSpuBoundsDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品spu积分设置服务接口类
 */
public interface ISmsSpuBoundsService {

    /**
     * 商品spu积分设置列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSpuBoundsVo>
     */
    PageResult<SmsSpuBoundsListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品spu积分设置详情
     *
     * @param id 主键ID
     * @return SmsSpuBounds
     */
    SmsSpuBoundsDetailVo detail(Long id);

    /**
     * 商品spu积分设置新增
     *
     * @param smsSpuBoundsParam 参数
     */
    void add(SmsSpuBoundsParam smsSpuBoundsParam);

    /**
     * 商品spu积分设置编辑
     *
     * @param smsSpuBoundsParam 参数
     */
    void edit(SmsSpuBoundsParam smsSpuBoundsParam);

    /**
     * 商品spu积分设置删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
