package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SpuImages;
import com.mdd.product.validate.SpuImagesParam;
import com.mdd.product.vo.SpuImagesListVo;
import com.mdd.product.vo.SpuImagesDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * spu图片服务接口类
 */
public interface ISpuImagesService extends IService<SpuImages> {

    /**
     * spu图片列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuImagesVo>
     */
    PageResult<SpuImagesListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * spu图片详情
     *
     * @param id 主键ID
     * @return SpuImages
     */
    SpuImagesDetailVo detail(Long id);

    /**
     * spu图片新增
     *
     * @param spuImagesParam 参数
     */
    void add(SpuImagesParam spuImagesParam);

    /**
     * spu图片编辑
     *
     * @param spuImagesParam 参数
     */
    void edit(SpuImagesParam spuImagesParam);

    /**
     * spu图片删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void saveImages(Long id, List<String> images);

    /**
     * 查找spu图片根据img_sort升序排序
     * @param spuId
     * @return
     */
    List<SpuImages> getImagesSort(Long spuId);
}
