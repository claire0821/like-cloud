package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.SpuComment;
import com.mdd.product.validate.SpuCommentParam;
import com.mdd.product.vo.SpuCommentListVo;
import com.mdd.product.vo.SpuCommentDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品评价服务接口类
 */
public interface ISpuCommentService extends IService<SpuComment> {

    /**
     * 商品评价列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuCommentVo>
     */
    PageResult<SpuCommentListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品评价详情
     *
     * @param id 主键ID
     * @return SpuComment
     */
    SpuCommentDetailVo detail(Long id);

    /**
     * 商品评价新增
     *
     * @param spuCommentParam 参数
     */
    void add(SpuCommentParam spuCommentParam);

    /**
     * 商品评价编辑
     *
     * @param spuCommentParam 参数
     */
    void edit(SpuCommentParam spuCommentParam);

    /**
     * 商品评价删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
