package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SpuInfoDesc;
import com.mdd.product.validate.SpuInfoDescParam;
import com.mdd.product.vo.SpuInfoDescListVo;
import com.mdd.product.vo.SpuInfoDescDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * spu信息介绍服务接口类
 */
public interface ISpuInfoDescService extends IService<SpuInfoDesc> {

    /**
     * spu信息介绍列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuInfoDescVo>
     */
    PageResult<SpuInfoDescListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu信息介绍详情
     *
     * @param id 主键ID
     * @return SpuInfoDesc
     */
    SpuInfoDescDetailVo detail(Long id);

    /**
     * spu信息介绍新增
     *
     * @param spuInfoDescParam 参数
     */
    void add(SpuInfoDescParam spuInfoDescParam);

    /**
     * spu信息介绍编辑
     *
     * @param spuInfoDescParam 参数
     */
    void edit(SpuInfoDescParam spuInfoDescParam);

    /**
     * spu信息介绍删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
