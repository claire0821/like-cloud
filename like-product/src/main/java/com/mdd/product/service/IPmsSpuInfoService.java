package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSpuInfoParam;
import com.mdd.product.vo.PmsSpuInfoListVo;
import com.mdd.product.vo.PmsSpuInfoDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * spu信息服务接口类
 */
public interface IPmsSpuInfoService {

    /**
     * spu信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuInfoVo>
     */
    PageResult<PmsSpuInfoListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu信息详情
     *
     * @param id 主键ID
     * @return PmsSpuInfo
     */
    PmsSpuInfoDetailVo detail(Long id);

    /**
     * spu信息新增
     *
     * @param pmsSpuInfoParam 参数
     */
    void add(PmsSpuInfoParam pmsSpuInfoParam);

    /**
     * spu信息编辑
     *
     * @param pmsSpuInfoParam 参数
     */
    void edit(PmsSpuInfoParam pmsSpuInfoParam);

    /**
     * spu信息删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
