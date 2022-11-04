package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsSpuImagesParam;
import com.mdd.product.vo.PmsSpuImagesListVo;
import com.mdd.product.vo.PmsSpuImagesDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * spu图片服务接口类
 */
public interface IPmsSpuImagesService {

    /**
     * spu图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuImagesVo>
     */
    PageResult<PmsSpuImagesListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu图片详情
     *
     * @param id 主键ID
     * @return PmsSpuImages
     */
    PmsSpuImagesDetailVo detail(Long id);

    /**
     * spu图片新增
     *
     * @param pmsSpuImagesParam 参数
     */
    void add(PmsSpuImagesParam pmsSpuImagesParam);

    /**
     * spu图片编辑
     *
     * @param pmsSpuImagesParam 参数
     */
    void edit(PmsSpuImagesParam pmsSpuImagesParam);

    /**
     * spu图片删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
