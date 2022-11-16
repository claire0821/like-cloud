package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.coupon.service.ISeckillSessionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SeckillSessionParam;
import com.mdd.coupon.vo.SeckillSessionListVo;
import com.mdd.coupon.vo.SeckillSessionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SeckillSession;
import com.mdd.coupon.mapper.SeckillSessionMapper;
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
 * 秒杀活动场次实现类
 */
@Service
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper,SeckillSession> implements ISeckillSessionService {
        
    @Resource
    SeckillSessionMapper seckillSessionMapper;

    /**
     * 秒杀活动场次列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SeckillSessionListVo>
     */
    @Override
    public PageResult<SeckillSessionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SeckillSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        seckillSessionMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:int",
        });

        IPage<SeckillSession> iPage = seckillSessionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SeckillSessionListVo> list = new LinkedList<>();
        for(SeckillSession item : iPage.getRecords()) {
            SeckillSessionListVo vo = new SeckillSessionListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            vo.setStartTime(simpleDateFormat.format(item.getStartTime()));
            vo.setEndTime(simpleDateFormat.format(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动场次详情
     *
     * @param id 主键参数
     * @return SeckillSession
     */
    @Override
    public SeckillSessionDetailVo detail(Long id) {
        SeckillSession model = seckillSessionMapper.selectOne(
                new QueryWrapper<SeckillSession>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SeckillSessionDetailVo vo = new SeckillSessionDetailVo();
        BeanUtils.copyProperties(model, vo);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        vo.setStartTime(simpleDateFormat.format(model.getStartTime()));
        vo.setEndTime(simpleDateFormat.format(model.getEndTime()));
        return vo;
    }

    /**
     * 秒杀活动场次新增
     *
     * @param seckillSessionParam 参数
     */
    @Override
    public void add(SeckillSessionParam seckillSessionParam) {
        SeckillSession model = new SeckillSession();
        model.setName(seckillSessionParam.getName());
        model.setStartTime(new Date());
        model.setEndTime(new Date());
        model.setStatus(seckillSessionParam.getStatus());
        model.setCreateTime(new Date());
        seckillSessionMapper.insert(model);
    }

    /**
     * 秒杀活动场次编辑
     *
     * @param seckillSessionParam 参数
     */
    @Override
    public void edit(SeckillSessionParam seckillSessionParam) {
        SeckillSession model = seckillSessionMapper.selectOne(
                new QueryWrapper<SeckillSession>()
                    .eq("id",  seckillSessionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(seckillSessionParam.getId());
        model.setName(seckillSessionParam.getName());
        model.setStatus(seckillSessionParam.getStatus());
        seckillSessionMapper.updateById(model);
    }

    /**
     * 秒杀活动场次删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SeckillSession model = seckillSessionMapper.selectOne(
                new QueryWrapper<SeckillSession>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        seckillSessionMapper.delete(new QueryWrapper<SeckillSession>().eq("id", id));
    }

}
