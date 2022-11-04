package com.mdd.product.service;

import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSpuInfoDescParam;
import com.mdd.product.vo.PmsSpuInfoDescListVo;
import com.mdd.product.vo.PmsSpuInfoDescDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * spu信息介绍服务接口类
 */
public interface IPmsSpuInfoDescService {

    /**
     * spu信息介绍列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuInfoDescVo>
     */
    PageResult<PmsSpuInfoDescListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu信息介绍详情
     *
     * @param id 主键ID
     * @return PmsSpuInfoDesc
     */
    PmsSpuInfoDescDetailVo detail(Long id);

    /**
     * spu信息介绍新增
     *
     * @param pmsSpuInfoDescParam 参数
     */
    void add(PmsSpuInfoDescParam pmsSpuInfoDescParam);

    /**
     * spu信息介绍编辑
     *
     * @param pmsSpuInfoDescParam 参数
     */
    void edit(PmsSpuInfoDescParam pmsSpuInfoDescParam);

    /**
     * spu信息介绍删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
