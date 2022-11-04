package com.mdd.product.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsCommentReplayParam;
import com.mdd.product.vo.PmsCommentReplayListVo;
import com.mdd.product.vo.PmsCommentReplayDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品评价回复关系服务接口类
 */
public interface IPmsCommentReplayService {

    /**
     * 商品评价回复关系列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCommentReplayVo>
     */
    PageResult<PmsCommentReplayListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品评价回复关系详情
     *
     * @param id 主键ID
     * @return PmsCommentReplay
     */
    PmsCommentReplayDetailVo detail(Long id);

    /**
     * 商品评价回复关系新增
     *
     * @param pmsCommentReplayParam 参数
     */
    void add(PmsCommentReplayParam pmsCommentReplayParam);

    /**
     * 商品评价回复关系编辑
     *
     * @param pmsCommentReplayParam 参数
     */
    void edit(PmsCommentReplayParam pmsCommentReplayParam);

    /**
     * 商品评价回复关系删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
