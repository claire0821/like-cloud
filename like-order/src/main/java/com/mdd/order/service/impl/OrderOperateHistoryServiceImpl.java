package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderOperateHistoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderOperateHistoryParam;
import com.mdd.order.vo.OrderOperateHistoryListVo;
import com.mdd.order.vo.OrderOperateHistoryDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderOperateHistory;
import com.mdd.order.mapper.OrderOperateHistoryMapper;
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
 * 订单操作历史记录实现类
 */
@Service
public class OrderOperateHistoryServiceImpl extends ServiceImpl<OrderOperateHistoryMapper,OrderOperateHistory> implements IOrderOperateHistoryService {
        
    @Resource
    OrderOperateHistoryMapper orderOperateHistoryMapper;

    /**
     * 订单操作历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderOperateHistoryListVo>
     */
    @Override
    public PageResult<OrderOperateHistoryListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<OrderOperateHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderOperateHistoryMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderId@order_id:long",
            "=:operateMan@operate_man:str",
            "=:orderStatus@order_status:int",
            "=:note:str",
        });

        IPage<OrderOperateHistory> iPage = orderOperateHistoryMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderOperateHistoryListVo> list = new LinkedList<>();
        for(OrderOperateHistory item : iPage.getRecords()) {
            OrderOperateHistoryListVo vo = new OrderOperateHistoryListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单操作历史记录详情
     *
     * @param id 主键参数
     * @return OrderOperateHistory
     */
    @Override
    public OrderOperateHistoryDetailVo detail(Long id) {
        OrderOperateHistory model = orderOperateHistoryMapper.selectOne(
                new QueryWrapper<OrderOperateHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderOperateHistoryDetailVo vo = new OrderOperateHistoryDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单操作历史记录新增
     *
     * @param orderOperateHistoryParam 参数
     */
    @Override
    public void add(OrderOperateHistoryParam orderOperateHistoryParam) {
        OrderOperateHistory model = new OrderOperateHistory();
        model.setOrderId(orderOperateHistoryParam.getOrderId());
        model.setOperateMan(orderOperateHistoryParam.getOperateMan());
        model.setCreateTime(new Date());
        model.setOrderStatus(orderOperateHistoryParam.getOrderStatus());
        model.setNote(orderOperateHistoryParam.getNote());
        orderOperateHistoryMapper.insert(model);
    }

    /**
     * 订单操作历史记录编辑
     *
     * @param orderOperateHistoryParam 参数
     */
    @Override
    public void edit(OrderOperateHistoryParam orderOperateHistoryParam) {
        OrderOperateHistory model = orderOperateHistoryMapper.selectOne(
                new QueryWrapper<OrderOperateHistory>()
                    .eq("id",  orderOperateHistoryParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderOperateHistoryParam.getId());
        model.setOrderId(orderOperateHistoryParam.getOrderId());
        model.setOperateMan(orderOperateHistoryParam.getOperateMan());
        model.setOrderStatus(orderOperateHistoryParam.getOrderStatus());
        model.setNote(orderOperateHistoryParam.getNote());
        orderOperateHistoryMapper.updateById(model);
    }

    /**
     * 订单操作历史记录删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        OrderOperateHistory model = orderOperateHistoryMapper.selectOne(
                new QueryWrapper<OrderOperateHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderOperateHistoryMapper.delete(new QueryWrapper<OrderOperateHistory>().eq("id", id));
    }

}
