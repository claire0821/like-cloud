package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderReturnReasonService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderReturnReasonParam;
import com.mdd.order.vo.OrderReturnReasonListVo;
import com.mdd.order.vo.OrderReturnReasonDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderReturnReason;
import com.mdd.order.mapper.OrderReturnReasonMapper;
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
 * 退货原因实现类
 */
@Service
public class OrderReturnReasonServiceImpl extends ServiceImpl<OrderReturnReasonMapper,OrderReturnReason> implements IOrderReturnReasonService {
        
    @Resource
    OrderReturnReasonMapper orderReturnReasonMapper;

    /**
     * 退货原因列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderReturnReasonListVo>
     */
    @Override
    public PageResult<OrderReturnReasonListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<OrderReturnReason> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        orderReturnReasonMapper.setSearch(queryWrapper, params, new String[]{
            "like:name:str",
            "=:sort:int",
            "=:status:int",
        });

        IPage<OrderReturnReason> iPage = orderReturnReasonMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderReturnReasonListVo> list = new LinkedList<>();
        for(OrderReturnReason item : iPage.getRecords()) {
            OrderReturnReasonListVo vo = new OrderReturnReasonListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 退货原因详情
     *
     * @param id 主键参数
     * @return OrderReturnReason
     */
    @Override
    public OrderReturnReasonDetailVo detail(Long id) {
        OrderReturnReason model = orderReturnReasonMapper.selectOne(
                new QueryWrapper<OrderReturnReason>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderReturnReasonDetailVo vo = new OrderReturnReasonDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 退货原因新增
     *
     * @param orderReturnReasonParam 参数
     */
    @Override
    public void add(OrderReturnReasonParam orderReturnReasonParam) {
        OrderReturnReason model = new OrderReturnReason();
        model.setName(orderReturnReasonParam.getName());
        model.setSort(orderReturnReasonParam.getSort());
        model.setStatus(orderReturnReasonParam.getStatus());
        model.setCreateTime(new Date());
        orderReturnReasonMapper.insert(model);
    }

    /**
     * 退货原因编辑
     *
     * @param orderReturnReasonParam 参数
     */
    @Override
    public void edit(OrderReturnReasonParam orderReturnReasonParam) {
        OrderReturnReason model = orderReturnReasonMapper.selectOne(
                new QueryWrapper<OrderReturnReason>()
                    .eq("id",  orderReturnReasonParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderReturnReasonParam.getId());
        model.setName(orderReturnReasonParam.getName());
        model.setSort(orderReturnReasonParam.getSort());
        model.setStatus(orderReturnReasonParam.getStatus());
        orderReturnReasonMapper.updateById(model);
    }

    /**
     * 退货原因删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        OrderReturnReason model = orderReturnReasonMapper.selectOne(
                new QueryWrapper<OrderReturnReason>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderReturnReasonMapper.delete(new QueryWrapper<OrderReturnReason>().eq("id", id));
    }

}
