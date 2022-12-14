package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderSettingService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderSettingParam;
import com.mdd.order.vo.OrderSettingListVo;
import com.mdd.order.vo.OrderSettingDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderSetting;
import com.mdd.order.mapper.OrderSettingMapper;
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
 * 订单配置信息实现类
 */
@Service
public class OrderSettingServiceImpl extends ServiceImpl<OrderSettingMapper,OrderSetting> implements IOrderSettingService {
        
    @Resource
    OrderSettingMapper orderSettingMapper;

    /**
     * 订单配置信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderSettingListVo>
     */
    @Override
    public PageResult<OrderSettingListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<OrderSetting> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderSettingMapper.setSearch(queryWrapper, params, new String[]{
            "=:flashOrderOvertime@flash_order_overtime:int",
            "=:normalOrderOvertime@normal_order_overtime:int",
            "=:confirmOvertime@confirm_overtime:int",
            "=:finishOvertime@finish_overtime:int",
            "=:commentOvertime@comment_overtime:int",
            "=:memberLevel@member_level:int",
        });

        IPage<OrderSetting> iPage = orderSettingMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderSettingListVo> list = new LinkedList<>();
        for(OrderSetting item : iPage.getRecords()) {
            OrderSettingListVo vo = new OrderSettingListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单配置信息详情
     *
     * @param id 主键参数
     * @return OrderSetting
     */
    @Override
    public OrderSettingDetailVo detail(Long id) {
        OrderSetting model = orderSettingMapper.selectOne(
                new QueryWrapper<OrderSetting>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderSettingDetailVo vo = new OrderSettingDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单配置信息新增
     *
     * @param orderSettingParam 参数
     */
    @Override
    public void add(OrderSettingParam orderSettingParam) {
        OrderSetting model = new OrderSetting();
        model.setFlashOrderOvertime(orderSettingParam.getFlashOrderOvertime());
        model.setNormalOrderOvertime(orderSettingParam.getNormalOrderOvertime());
        model.setConfirmOvertime(orderSettingParam.getConfirmOvertime());
        model.setFinishOvertime(orderSettingParam.getFinishOvertime());
        model.setCommentOvertime(orderSettingParam.getCommentOvertime());
        model.setMemberLevel(orderSettingParam.getMemberLevel());
        orderSettingMapper.insert(model);
    }

    /**
     * 订单配置信息编辑
     *
     * @param orderSettingParam 参数
     */
    @Override
    public void edit(OrderSettingParam orderSettingParam) {
        OrderSetting model = orderSettingMapper.selectOne(
                new QueryWrapper<OrderSetting>()
                    .eq("id",  orderSettingParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderSettingParam.getId());
        model.setFlashOrderOvertime(orderSettingParam.getFlashOrderOvertime());
        model.setNormalOrderOvertime(orderSettingParam.getNormalOrderOvertime());
        model.setConfirmOvertime(orderSettingParam.getConfirmOvertime());
        model.setFinishOvertime(orderSettingParam.getFinishOvertime());
        model.setCommentOvertime(orderSettingParam.getCommentOvertime());
        model.setMemberLevel(orderSettingParam.getMemberLevel());
        orderSettingMapper.updateById(model);
    }

    /**
     * 订单配置信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        OrderSetting model = orderSettingMapper.selectOne(
                new QueryWrapper<OrderSetting>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderSettingMapper.delete(new QueryWrapper<OrderSetting>().eq("id", id));
    }

}
