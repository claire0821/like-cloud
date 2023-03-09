package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.vo.SeckillSessionWithSkusVo;
import com.mdd.common.vo.SeckillSkuVo;
import com.mdd.coupon.entity.SeckillSkuRelation;
import com.mdd.coupon.service.ISeckillSessionService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.service.ISeckillSkuRelationService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 秒杀活动场次实现类
 */
@Service
public class SeckillSessionServiceImpl extends ServiceImpl<SeckillSessionMapper,SeckillSession> implements ISeckillSessionService {
        
    @Resource
    SeckillSessionMapper seckillSessionMapper;
    @Autowired
    ISeckillSkuRelationService iSeckillSkuRelationService;
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


    /**
     * 当前时间
     * @return
     */
    private String startTime() {
        LocalDate now = LocalDate.now();
        LocalTime min = LocalTime.MIN;
        LocalDateTime start = LocalDateTime.of(now, min);

        //格式化时间
        String startFormat = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return startFormat;
    }

    /**
     * 结束时间
     * @return
     */
    private String endTime() {
        LocalDate now = LocalDate.now();
        LocalDate plus = now.plusDays(2);
        LocalTime max = LocalTime.MAX;
        LocalDateTime end = LocalDateTime.of(plus, max);

        //格式化时间
        String endFormat = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return endFormat;
    }

    @Override
    public List<SeckillSessionWithSkusVo> getLates3DaySession() {
        //计算最近三天
        //查出这三天参与秒杀活动的商品
        List<SeckillSession> list = this.baseMapper.selectList(new QueryWrapper<SeckillSession>()
                .between("start_time", startTime(), endTime()));

        if (list != null && list.size() > 0) {
            List<SeckillSessionWithSkusVo> collect = list.stream().map(session -> {
                Long id = session.getId();
                //查出sms_seckill_sku_relation表中关联的skuId
                List<SeckillSkuRelation> relationSkus = iSeckillSkuRelationService.list(new QueryWrapper<SeckillSkuRelation>()
                        .eq("promotion_session_id", id));
                SeckillSessionWithSkusVo seckillSessionWithSkusVo = new SeckillSessionWithSkusVo();
                BeanUtils.copyProperties(session,seckillSessionWithSkusVo);
                List<SeckillSkuVo> seckillSkuVos = new ArrayList<>();
                for (SeckillSkuRelation skus : relationSkus) {
                    SeckillSkuVo seckillSkuVo = new SeckillSkuVo();
                    BeanUtils.copyProperties(skus,seckillSkuVo);
                    seckillSkuVos.add(seckillSkuVo);
                }

                seckillSessionWithSkusVo.setRelationSkus(seckillSkuVos);
                return seckillSessionWithSkusVo;
            }).collect(Collectors.toList());
            return collect;
        }

        return null;
    }

}
