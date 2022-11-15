package com.mdd.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.entity.CommentReplay;
import com.mdd.product.validate.CommentReplayParam;
import com.mdd.product.vo.CommentReplayListVo;
import com.mdd.product.vo.CommentReplayDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 商品评价回复关系服务接口类
 */
public interface ICommentReplayService extends IService<CommentReplay> {

    /**
     * 商品评价回复关系列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CommentReplayVo>
     */
    PageResult<CommentReplayListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 商品评价回复关系详情
     *
     * @param id 主键ID
     * @return CommentReplay
     */
    CommentReplayDetailVo detail(Long id);

    /**
     * 商品评价回复关系新增
     *
     * @param commentReplayParam 参数
     */
    void add(CommentReplayParam commentReplayParam);

    /**
     * 商品评价回复关系编辑
     *
     * @param commentReplayParam 参数
     */
    void edit(CommentReplayParam commentReplayParam);

    /**
     * 商品评价回复关系删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
