package com.mdd.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.entity.SkuLadder;
import com.mdd.coupon.validate.SkuLadderParam;
import com.mdd.coupon.vo.SkuLadderListVo;
import com.mdd.coupon.vo.SkuLadderDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品阶梯价格服务接口类
 */
public interface ISkuLadderService extends IService<SkuLadder> {

    /**
     * 商品阶梯价格列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SkuLadderVo>
     */
    PageResult<SkuLadderListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品阶梯价格详情
     *
     * @param id 主键ID
     * @return SkuLadder
     */
    SkuLadderDetailVo detail(Long id);

    /**
     * 商品阶梯价格新增
     *
     * @param skuLadderParam 参数
     */
    void add(SkuLadderParam skuLadderParam);

    /**
     * 商品阶梯价格编辑
     *
     * @param skuLadderParam 参数
     */
    void edit(SkuLadderParam skuLadderParam);

    /**
     * 商品阶梯价格删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
