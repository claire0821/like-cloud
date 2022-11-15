package com.mdd.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.product.service.ISpuCommentService;
import com.mdd.common.validate.PageParam;
import com.mdd.product.validate.SpuCommentParam;
import com.mdd.product.vo.SpuCommentListVo;
import com.mdd.product.vo.SpuCommentDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.product.entity.SpuComment;
import com.mdd.product.mapper.SpuCommentMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.common.config.GlobalConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品评价实现类
 */
@Service
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentMapper,SpuComment> implements ISpuCommentService {
        
    @Resource
    SpuCommentMapper spuCommentMapper;

    /**
     * 商品评价列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SpuCommentListVo>
     */
    @Override
    public PageResult<SpuCommentListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SpuComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        spuCommentMapper.setSearch(queryWrapper, params, new String[]{
            "=:skuId@sku_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
            "like:memberNickName@member_nick_name:str",
            "=:star:int",
            "=:memberIp@member_ip:str",
            "=:showStatus@show_status:int",
            "=:spuAttributes@spu_attributes:str",
            "=:likesCount@likes_count:int",
            "=:replyCount@reply_count:int",
            "=:resources:str",
            "=:content:str",
            "=:memberIcon@member_icon:str",
            "=:commentType@comment_type:int",
        });

        IPage<SpuComment> iPage = spuCommentMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SpuCommentListVo> list = new LinkedList<>();
        for(SpuComment item : iPage.getRecords()) {
            SpuCommentListVo vo = new SpuCommentListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 商品评价详情
     *
     * @param id 主键参数
     * @return SpuComment
     */
    @Override
    public SpuCommentDetailVo detail(Long id) {
        SpuComment model = spuCommentMapper.selectOne(
                new QueryWrapper<SpuComment>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SpuCommentDetailVo vo = new SpuCommentDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 商品评价新增
     *
     * @param spuCommentParam 参数
     */
    @Override
    public void add(SpuCommentParam spuCommentParam) {
        SpuComment model = new SpuComment();
        model.setSkuId(spuCommentParam.getSkuId());
        model.setSpuId(spuCommentParam.getSpuId());
        model.setSpuName(spuCommentParam.getSpuName());
        model.setMemberNickName(spuCommentParam.getMemberNickName());
        model.setStar(spuCommentParam.getStar());
        model.setMemberIp(spuCommentParam.getMemberIp());
        model.setCreateTime(new Date());
        model.setShowStatus(spuCommentParam.getShowStatus());
        model.setSpuAttributes(spuCommentParam.getSpuAttributes());
        model.setLikesCount(spuCommentParam.getLikesCount());
        model.setReplyCount(spuCommentParam.getReplyCount());
        model.setResources(spuCommentParam.getResources());
        model.setContent(spuCommentParam.getContent());
        model.setMemberIcon(spuCommentParam.getMemberIcon());
        model.setCommentType(spuCommentParam.getCommentType());
        spuCommentMapper.insert(model);
    }

    /**
     * 商品评价编辑
     *
     * @param spuCommentParam 参数
     */
    @Override
    public void edit(SpuCommentParam spuCommentParam) {
        SpuComment model = spuCommentMapper.selectOne(
                new QueryWrapper<SpuComment>()
                    .eq("id",  spuCommentParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(spuCommentParam.getId());
        model.setSkuId(spuCommentParam.getSkuId());
        model.setSpuId(spuCommentParam.getSpuId());
        model.setSpuName(spuCommentParam.getSpuName());
        model.setMemberNickName(spuCommentParam.getMemberNickName());
        model.setStar(spuCommentParam.getStar());
        model.setMemberIp(spuCommentParam.getMemberIp());
        model.setShowStatus(spuCommentParam.getShowStatus());
        model.setSpuAttributes(spuCommentParam.getSpuAttributes());
        model.setLikesCount(spuCommentParam.getLikesCount());
        model.setReplyCount(spuCommentParam.getReplyCount());
        model.setResources(spuCommentParam.getResources());
        model.setContent(spuCommentParam.getContent());
        model.setMemberIcon(spuCommentParam.getMemberIcon());
        model.setCommentType(spuCommentParam.getCommentType());
        spuCommentMapper.updateById(model);
    }

    /**
     * 商品评价删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SpuComment model = spuCommentMapper.selectOne(
                new QueryWrapper<SpuComment>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        spuCommentMapper.delete(new QueryWrapper<SpuComment>().eq("id", id));
    }

}
