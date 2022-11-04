package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IUmsMemberCollectSpuService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberCollectSpuParam;
import com.mdd.member.vo.UmsMemberCollectSpuListVo;
import com.mdd.member.vo.UmsMemberCollectSpuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberCollectSpu;
import com.mdd.member.mapper.UmsMemberCollectSpuMapper;
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
 * 会员收藏的商品实现类
 */
@Service
public class UmsMemberCollectSpuServiceImpl implements IUmsMemberCollectSpuService {
        
    @Resource
    UmsMemberCollectSpuMapper umsMemberCollectSpuMapper;

    /**
     * 会员收藏的商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberCollectSpuListVo>
     */
    @Override
    public PageResult<UmsMemberCollectSpuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberCollectSpu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberCollectSpuMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
            "=:spuImg@spu_img:str",
        });

        IPage<UmsMemberCollectSpu> iPage = umsMemberCollectSpuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberCollectSpuListVo> list = new LinkedList<>();
        for(UmsMemberCollectSpu item : iPage.getRecords()) {
            UmsMemberCollectSpuListVo vo = new UmsMemberCollectSpuListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收藏的商品详情
     *
     * @param id 主键参数
     * @return UmsMemberCollectSpu
     */
    @Override
    public UmsMemberCollectSpuDetailVo detail(Long id) {
        UmsMemberCollectSpu model = umsMemberCollectSpuMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberCollectSpuDetailVo vo = new UmsMemberCollectSpuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收藏的商品新增
     *
     * @param umsMemberCollectSpuParam 参数
     */
    @Override
    public void add(UmsMemberCollectSpuParam umsMemberCollectSpuParam) {
        UmsMemberCollectSpu model = new UmsMemberCollectSpu();
        model.setMemberId(umsMemberCollectSpuParam.getMemberId());
        model.setSpuId(umsMemberCollectSpuParam.getSpuId());
        model.setSpuName(umsMemberCollectSpuParam.getSpuName());
        model.setSpuImg(umsMemberCollectSpuParam.getSpuImg());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        umsMemberCollectSpuMapper.insert(model);
    }

    /**
     * 会员收藏的商品编辑
     *
     * @param umsMemberCollectSpuParam 参数
     */
    @Override
    public void edit(UmsMemberCollectSpuParam umsMemberCollectSpuParam) {
        UmsMemberCollectSpu model = umsMemberCollectSpuMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSpu>()
                    .eq("id",  umsMemberCollectSpuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberCollectSpuParam.getId());
        model.setMemberId(umsMemberCollectSpuParam.getMemberId());
        model.setSpuId(umsMemberCollectSpuParam.getSpuId());
        model.setSpuName(umsMemberCollectSpuParam.getSpuName());
        model.setSpuImg(umsMemberCollectSpuParam.getSpuImg());
        umsMemberCollectSpuMapper.updateById(model);
    }

    /**
     * 会员收藏的商品删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberCollectSpu model = umsMemberCollectSpuMapper.selectOne(
                new QueryWrapper<UmsMemberCollectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberCollectSpuMapper.delete(new QueryWrapper<UmsMemberCollectSpu>().eq("id", id));
    }

}
