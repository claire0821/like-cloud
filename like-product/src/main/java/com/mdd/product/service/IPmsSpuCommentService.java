package com.mdd.product.service;

import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSpuCommentParam;
import com.mdd.product.vo.PmsSpuCommentListVo;
import com.mdd.product.vo.PmsSpuCommentDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品评价服务接口类
 */
public interface IPmsSpuCommentService {

    /**
     * 商品评价列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuCommentVo>
     */
    PageResult<PmsSpuCommentListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品评价详情
     *
     * @param id 主键ID
     * @return PmsSpuComment
     */
    PmsSpuCommentDetailVo detail(Long id);

    /**
     * 商品评价新增
     *
     * @param pmsSpuCommentParam 参数
     */
    void add(PmsSpuCommentParam pmsSpuCommentParam);

    /**
     * 商品评价编辑
     *
     * @param pmsSpuCommentParam 参数
     */
    void edit(PmsSpuCommentParam pmsSpuCommentParam);

    /**
     * 商品评价删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
