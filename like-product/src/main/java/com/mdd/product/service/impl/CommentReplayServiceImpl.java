package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ICommentReplayService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.CommentReplayParam;
import com.mdd.product.vo.CommentReplayListVo;
import com.mdd.product.vo.CommentReplayDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.CommentReplay;
import com.mdd.product.mapper.CommentReplayMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商品评价回复关系实现类
 */
@Service
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayMapper,CommentReplay> implements ICommentReplayService {
        
    @Resource
    CommentReplayMapper commentReplayMapper;

    /**
     * 商品评价回复关系列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<CommentReplayListVo>
     */
    @Override
    public PageResult<CommentReplayListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<CommentReplay> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        commentReplayMapper.setSearch(queryWrapper, params, new String[]{
            "=:commentId@comment_id:long",
            "=:replyId@reply_id:long",
        });

        IPage<CommentReplay> iPage = commentReplayMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CommentReplayListVo> list = new LinkedList<>();
        for(CommentReplay item : iPage.getRecords()) {
            CommentReplayListVo vo = new CommentReplayListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品评价回复关系详情
     *
     * @param id 主键参数
     * @return CommentReplay
     */
    @Override
    public CommentReplayDetailVo detail(Long id) {
        CommentReplay model = commentReplayMapper.selectOne(
                new QueryWrapper<CommentReplay>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CommentReplayDetailVo vo = new CommentReplayDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品评价回复关系新增
     *
     * @param commentReplayParam 参数
     */
    @Override
    public void add(CommentReplayParam commentReplayParam) {
        CommentReplay model = new CommentReplay();
        model.setCommentId(commentReplayParam.getCommentId());
        model.setReplyId(commentReplayParam.getReplyId());
        commentReplayMapper.insert(model);
    }

    /**
     * 商品评价回复关系编辑
     *
     * @param commentReplayParam 参数
     */
    @Override
    public void edit(CommentReplayParam commentReplayParam) {
        CommentReplay model = commentReplayMapper.selectOne(
                new QueryWrapper<CommentReplay>()
                    .eq("id",  commentReplayParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(commentReplayParam.getId());
        model.setCommentId(commentReplayParam.getCommentId());
        model.setReplyId(commentReplayParam.getReplyId());
        commentReplayMapper.updateById(model);
    }

    /**
     * 商品评价回复关系删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        CommentReplay model = commentReplayMapper.selectOne(
                new QueryWrapper<CommentReplay>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        commentReplayMapper.delete(new QueryWrapper<CommentReplay>().eq("id", id));
    }

}
