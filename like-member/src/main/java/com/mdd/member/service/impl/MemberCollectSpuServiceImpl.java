package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.member.service.IMemberCollectSpuService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberCollectSpuParam;
import com.mdd.member.vo.MemberCollectSpuListVo;
import com.mdd.member.vo.MemberCollectSpuDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberCollectSpu;
import com.mdd.member.mapper.MemberCollectSpuMapper;
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
 * 会员收藏的商品实现类
 */
@Service
public class MemberCollectSpuServiceImpl extends ServiceImpl<MemberCollectSpuMapper,MemberCollectSpu> implements IMemberCollectSpuService {
        
    @Resource
    MemberCollectSpuMapper memberCollectSpuMapper;

    /**
     * 会员收藏的商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberCollectSpuListVo>
     */
    @Override
    public PageResult<MemberCollectSpuListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberCollectSpu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberCollectSpuMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
            "=:spuImg@spu_img:str",
        });

        IPage<MemberCollectSpu> iPage = memberCollectSpuMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberCollectSpuListVo> list = new LinkedList<>();
        for(MemberCollectSpu item : iPage.getRecords()) {
            MemberCollectSpuListVo vo = new MemberCollectSpuListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收藏的商品详情
     *
     * @param id 主键参数
     * @return MemberCollectSpu
     */
    @Override
    public MemberCollectSpuDetailVo detail(Long id) {
        MemberCollectSpu model = memberCollectSpuMapper.selectOne(
                new QueryWrapper<MemberCollectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberCollectSpuDetailVo vo = new MemberCollectSpuDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收藏的商品新增
     *
     * @param memberCollectSpuParam 参数
     */
    @Override
    public void add(MemberCollectSpuParam memberCollectSpuParam) {
        MemberCollectSpu model = new MemberCollectSpu();
        model.setMemberId(memberCollectSpuParam.getMemberId());
        model.setSpuId(memberCollectSpuParam.getSpuId());
        model.setSpuName(memberCollectSpuParam.getSpuName());
        model.setSpuImg(memberCollectSpuParam.getSpuImg());
        model.setCreateTime(new Date());
        memberCollectSpuMapper.insert(model);
    }

    /**
     * 会员收藏的商品编辑
     *
     * @param memberCollectSpuParam 参数
     */
    @Override
    public void edit(MemberCollectSpuParam memberCollectSpuParam) {
        MemberCollectSpu model = memberCollectSpuMapper.selectOne(
                new QueryWrapper<MemberCollectSpu>()
                    .eq("id",  memberCollectSpuParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberCollectSpuParam.getId());
        model.setMemberId(memberCollectSpuParam.getMemberId());
        model.setSpuId(memberCollectSpuParam.getSpuId());
        model.setSpuName(memberCollectSpuParam.getSpuName());
        model.setSpuImg(memberCollectSpuParam.getSpuImg());
        memberCollectSpuMapper.updateById(model);
    }

    /**
     * 会员收藏的商品删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberCollectSpu model = memberCollectSpuMapper.selectOne(
                new QueryWrapper<MemberCollectSpu>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberCollectSpuMapper.delete(new QueryWrapper<MemberCollectSpu>().eq("id", id));
    }

}
