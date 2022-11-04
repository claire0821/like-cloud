package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsCommentReplayService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.product.validate.PmsCommentReplayParam;
import com.mdd.product.vo.PmsCommentReplayListVo;
import com.mdd.product.vo.PmsCommentReplayDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsCommentReplay;
import com.mdd.product.mapper.PmsCommentReplayMapper;
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
public class PmsCommentReplayServiceImpl implements IPmsCommentReplayService {
        
    @Resource
    PmsCommentReplayMapper pmsCommentReplayMapper;

    /**
     * 商品评价回复关系列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsCommentReplayListVo>
     */
    @Override
    public PageResult<PmsCommentReplayListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsCommentReplay> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsCommentReplayMapper.setSearch(queryWrapper, params, new String[]{
            "=:commentId@comment_id:long",
            "=:replyId@reply_id:long",
        });

        IPage<PmsCommentReplay> iPage = pmsCommentReplayMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsCommentReplayListVo> list = new LinkedList<>();
        for(PmsCommentReplay item : iPage.getRecords()) {
            PmsCommentReplayListVo vo = new PmsCommentReplayListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品评价回复关系详情
     *
     * @param id 主键参数
     * @return PmsCommentReplay
     */
    @Override
    public PmsCommentReplayDetailVo detail(Long id) {
        PmsCommentReplay model = pmsCommentReplayMapper.selectOne(
                new QueryWrapper<PmsCommentReplay>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsCommentReplayDetailVo vo = new PmsCommentReplayDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品评价回复关系新增
     *
     * @param pmsCommentReplayParam 参数
     */
    @Override
    public void add(PmsCommentReplayParam pmsCommentReplayParam) {
        PmsCommentReplay model = new PmsCommentReplay();
        model.setCommentId(pmsCommentReplayParam.getCommentId());
        model.setReplyId(pmsCommentReplayParam.getReplyId());
        pmsCommentReplayMapper.insert(model);
    }

    /**
     * 商品评价回复关系编辑
     *
     * @param pmsCommentReplayParam 参数
     */
    @Override
    public void edit(PmsCommentReplayParam pmsCommentReplayParam) {
        PmsCommentReplay model = pmsCommentReplayMapper.selectOne(
                new QueryWrapper<PmsCommentReplay>()
                    .eq("id",  pmsCommentReplayParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsCommentReplayParam.getId());
        model.setCommentId(pmsCommentReplayParam.getCommentId());
        model.setReplyId(pmsCommentReplayParam.getReplyId());
        pmsCommentReplayMapper.updateById(model);
    }

    /**
     * 商品评价回复关系删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsCommentReplay model = pmsCommentReplayMapper.selectOne(
                new QueryWrapper<PmsCommentReplay>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsCommentReplayMapper.delete(new QueryWrapper<PmsCommentReplay>().eq("id", id));
    }

}
