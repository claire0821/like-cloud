package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SpuBounds;
import com.mdd.coupon.validate.SpuBoundsParam;
import com.mdd.coupon.vo.SpuBoundsListVo;
import com.mdd.coupon.vo.SpuBoundsDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品spu积分设置服务接口类
 */
public interface ISpuBoundsService extends IService<SpuBounds> {

    /**
     * 商品spu积分设置列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuBoundsVo>
     */
    PageResult<SpuBoundsListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品spu积分设置详情
     *
     * @param id 主键ID
     * @return SpuBounds
     */
    SpuBoundsDetailVo detail(Long id);

    /**
     * 商品spu积分设置新增
     *
     * @param spuBoundsParam 参数
     */
    void add(SpuBoundsParam spuBoundsParam);

    /**
     * 商品spu积分设置编辑
     *
     * @param spuBoundsParam 参数
     */
    void edit(SpuBoundsParam spuBoundsParam);

    /**
     * 商品spu积分设置删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
