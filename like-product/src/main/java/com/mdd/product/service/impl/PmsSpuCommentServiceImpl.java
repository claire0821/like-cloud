package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.IPmsSpuCommentService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.PmsSpuCommentParam;
import com.mdd.product.vo.PmsSpuCommentListVo;
import com.mdd.product.vo.PmsSpuCommentDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.PmsSpuComment;
import com.mdd.product.mapper.PmsSpuCommentMapper;
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
 * 商品评价实现类
 */
@Service
public class PmsSpuCommentServiceImpl implements IPmsSpuCommentService {
        
    @Resource
    PmsSpuCommentMapper pmsSpuCommentMapper;

    /**
     * 商品评价列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<PmsSpuCommentListVo>
     */
    @Override
    public PageResult<PmsSpuCommentListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<PmsSpuComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        pmsSpuCommentMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
            "like:memberNickName@member_nick_name:str",
            "=:star:int",
            "=:memberIp@member_ip:str",
            "=:showStatus@show_status:int",
            "=:spuAttributes@spu_attributes:str",
            "=:likesCount@likes_count:long",
            "=:replyCount@reply_count:long",
            "=:resources:str",
            "=:content:str",
            "=:memberIcon@member_icon:str",
            "=:commentType@comment_type:long",
        });

        IPage<PmsSpuComment> iPage = pmsSpuCommentMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<PmsSpuCommentListVo> list = new LinkedList<>();
        for(PmsSpuComment item : iPage.getRecords()) {
            PmsSpuCommentListVo vo = new PmsSpuCommentListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品评价详情
     *
     * @param id 主键参数
     * @return PmsSpuComment
     */
    @Override
    public PmsSpuCommentDetailVo detail(Long id) {
        PmsSpuComment model = pmsSpuCommentMapper.selectOne(
                new QueryWrapper<PmsSpuComment>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        PmsSpuCommentDetailVo vo = new PmsSpuCommentDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品评价新增
     *
     * @param pmsSpuCommentParam 参数
     */
    @Override
    public void add(PmsSpuCommentParam pmsSpuCommentParam) {
        PmsSpuComment model = new PmsSpuComment();
        model.setSkuId(pmsSpuCommentParam.getSkuId());
        model.setSpuId(pmsSpuCommentParam.getSpuId());
        model.setSpuName(pmsSpuCommentParam.getSpuName());
        model.setMemberNickName(pmsSpuCommentParam.getMemberNickName());
        model.setStar(pmsSpuCommentParam.getStar());
        model.setMemberIp(pmsSpuCommentParam.getMemberIp());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setShowStatus(pmsSpuCommentParam.getShowStatus());
        model.setSpuAttributes(pmsSpuCommentParam.getSpuAttributes());
        model.setLikesCount(pmsSpuCommentParam.getLikesCount());
        model.setReplyCount(pmsSpuCommentParam.getReplyCount());
        model.setResources(pmsSpuCommentParam.getResources());
        model.setContent(pmsSpuCommentParam.getContent());
        model.setMemberIcon(pmsSpuCommentParam.getMemberIcon());
        model.setCommentType(pmsSpuCommentParam.getCommentType());
        pmsSpuCommentMapper.insert(model);
    }

    /**
     * 商品评价编辑
     *
     * @param pmsSpuCommentParam 参数
     */
    @Override
    public void edit(PmsSpuCommentParam pmsSpuCommentParam) {
        PmsSpuComment model = pmsSpuCommentMapper.selectOne(
                new QueryWrapper<PmsSpuComment>()
                    .eq("id",  pmsSpuCommentParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(pmsSpuCommentParam.getId());
        model.setSkuId(pmsSpuCommentParam.getSkuId());
        model.setSpuId(pmsSpuCommentParam.getSpuId());
        model.setSpuName(pmsSpuCommentParam.getSpuName());
        model.setMemberNickName(pmsSpuCommentParam.getMemberNickName());
        model.setStar(pmsSpuCommentParam.getStar());
        model.setMemberIp(pmsSpuCommentParam.getMemberIp());
        model.setShowStatus(pmsSpuCommentParam.getShowStatus());
        model.setSpuAttributes(pmsSpuCommentParam.getSpuAttributes());
        model.setLikesCount(pmsSpuCommentParam.getLikesCount());
        model.setReplyCount(pmsSpuCommentParam.getReplyCount());
        model.setResources(pmsSpuCommentParam.getResources());
        model.setContent(pmsSpuCommentParam.getContent());
        model.setMemberIcon(pmsSpuCommentParam.getMemberIcon());
        model.setCommentType(pmsSpuCommentParam.getCommentType());
        pmsSpuCommentMapper.updateById(model);
    }

    /**
     * 商品评价删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        PmsSpuComment model = pmsSpuCommentMapper.selectOne(
                new QueryWrapper<PmsSpuComment>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        pmsSpuCommentMapper.delete(new QueryWrapper<PmsSpuComment>().eq("id", id));
    }

}
