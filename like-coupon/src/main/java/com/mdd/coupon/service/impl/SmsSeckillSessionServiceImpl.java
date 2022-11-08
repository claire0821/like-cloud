package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.coupon.service.ISmsSeckillSessionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.SmsSeckillSessionParam;
import com.mdd.coupon.vo.SmsSeckillSessionListVo;
import com.mdd.coupon.vo.SmsSeckillSessionDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.SmsSeckillSession;
import com.mdd.coupon.mapper.SmsSeckillSessionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 秒杀活动场次实现类
 */
@Service
public class SmsSeckillSessionServiceImpl implements ISmsSeckillSessionService {
        
    @Resource
    SmsSeckillSessionMapper smsSeckillSessionMapper;

    /**
     * 秒杀活动场次列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SmsSeckillSessionListVo>
     */
    @Override
    public PageResult<SmsSeckillSessionListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SmsSeckillSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        smsSeckillSessionMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "datetime:startTimeStart-startTimeEnd@start_time:str",
            "datetime:endTimeStart-endTimeEnd@end_time:str",
            "=:status:int",
        });

        IPage<SmsSeckillSession> iPage = smsSeckillSessionMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SmsSeckillSessionListVo> list = new LinkedList<>();
        for(SmsSeckillSession item : iPage.getRecords()) {
            SmsSeckillSessionListVo vo = new SmsSeckillSessionListVo();
            BeanUtils.copyProperties(item, vo);
//            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
//            vo.setStartTime(TimeUtil.timestampToDate(item.getStartTime()));
//            vo.setEndTime(TimeUtil.timestampToDate(item.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 秒杀活动场次详情
     *
     * @param id 主键参数
     * @return SmsSeckillSession
     */
    @Override
    public SmsSeckillSessionDetailVo detail(Long id) {
        SmsSeckillSession model = smsSeckillSessionMapper.selectOne(
                new QueryWrapper<SmsSeckillSession>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        SmsSeckillSessionDetailVo vo = new SmsSeckillSessionDetailVo();
        BeanUtils.copyProperties(model, vo);
//        vo.setStartTime(TimeUtil.timestampToDate(model.getStartTime()));
//        vo.setEndTime(TimeUtil.timestampToDate(model.getEndTime()));
        return vo;
    }

    /**
     * 秒杀活动场次新增
     *
     * @param smsSeckillSessionParam 参数
     */
    @Override
    public void add(SmsSeckillSessionParam smsSeckillSessionParam) {
        SmsSeckillSession model = new SmsSeckillSession();
        model.setName(smsSeckillSessionParam.getName());
//        model.setStartTime(System.currentTimeMillis() / 1000);
//        model.setEndTime(System.currentTimeMillis() / 1000);
        model.setStatus(smsSeckillSessionParam.getStatus());
//        model.setCreateTime(System.currentTimeMillis() / 1000);
        smsSeckillSessionMapper.insert(model);
    }

    /**
     * 秒杀活动场次编辑
     *
     * @param smsSeckillSessionParam 参数
     */
    @Override
    public void edit(SmsSeckillSessionParam smsSeckillSessionParam) {
        SmsSeckillSession model = smsSeckillSessionMapper.selectOne(
                new QueryWrapper<SmsSeckillSession>()
                    .eq("id",  smsSeckillSessionParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(smsSeckillSessionParam.getId());
        model.setName(smsSeckillSessionParam.getName());
        model.setStatus(smsSeckillSessionParam.getStatus());
        smsSeckillSessionMapper.updateById(model);
    }

    /**
     * 秒杀活动场次删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        SmsSeckillSession model = smsSeckillSessionMapper.selectOne(
                new QueryWrapper<SmsSeckillSession>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        smsSeckillSessionMapper.delete(new QueryWrapper<SmsSeckillSession>().eq("id", id));
    }

}
