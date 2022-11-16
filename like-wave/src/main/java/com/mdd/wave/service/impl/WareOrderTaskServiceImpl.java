package com.mdd.wave.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.wave.service.IWareOrderTaskService;
import com.mdd.common.validate.PageParam;
import com.mdd.wave.validate.WareOrderTaskParam;
import com.mdd.wave.vo.WareOrderTaskListVo;
import com.mdd.wave.vo.WareOrderTaskDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.wave.entity.WareOrderTask;
import com.mdd.wave.mapper.WareOrderTaskMapper;
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
 * 库存工作单实现类
 */
@Service
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskMapper,WareOrderTask> implements IWareOrderTaskService {
        
    @Resource
    WareOrderTaskMapper wareOrderTaskMapper;

    /**
     * 库存工作单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<WareOrderTaskListVo>
     */
    @Override
    public PageResult<WareOrderTaskListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<WareOrderTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        wareOrderTaskMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderId@order_id:long",
            "=:orderSn@order_sn:str",
            "=:consignee:str",
            "=:consigneeTel@consignee_tel:str",
            "=:deliveryAddress@delivery_address:str",
            "=:orderComment@order_comment:str",
            "=:paymentWay@payment_way:int",
            "=:taskStatus@task_status:int",
            "=:orderBody@order_body:str",
            "=:trackingNo@tracking_no:str",
            "=:wareId@ware_id:long",
            "=:taskComment@task_comment:str",
        });

        IPage<WareOrderTask> iPage = wareOrderTaskMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<WareOrderTaskListVo> list = new LinkedList<>();
        for(WareOrderTask item : iPage.getRecords()) {
            WareOrderTaskListVo vo = new WareOrderTaskListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 库存工作单详情
     *
     * @param id 主键参数
     * @return WareOrderTask
     */
    @Override
    public WareOrderTaskDetailVo detail(Long id) {
        WareOrderTask model = wareOrderTaskMapper.selectOne(
                new QueryWrapper<WareOrderTask>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        WareOrderTaskDetailVo vo = new WareOrderTaskDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 库存工作单新增
     *
     * @param wareOrderTaskParam 参数
     */
    @Override
    public void add(WareOrderTaskParam wareOrderTaskParam) {
        WareOrderTask model = new WareOrderTask();
        model.setOrderId(wareOrderTaskParam.getOrderId());
        model.setOrderSn(wareOrderTaskParam.getOrderSn());
        model.setConsignee(wareOrderTaskParam.getConsignee());
        model.setConsigneeTel(wareOrderTaskParam.getConsigneeTel());
        model.setDeliveryAddress(wareOrderTaskParam.getDeliveryAddress());
        model.setOrderComment(wareOrderTaskParam.getOrderComment());
        model.setPaymentWay(wareOrderTaskParam.getPaymentWay());
        model.setTaskStatus(wareOrderTaskParam.getTaskStatus());
        model.setOrderBody(wareOrderTaskParam.getOrderBody());
        model.setTrackingNo(wareOrderTaskParam.getTrackingNo());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setWareId(wareOrderTaskParam.getWareId());
        model.setTaskComment(wareOrderTaskParam.getTaskComment());
        wareOrderTaskMapper.insert(model);
    }

    /**
     * 库存工作单编辑
     *
     * @param wareOrderTaskParam 参数
     */
    @Override
    public void edit(WareOrderTaskParam wareOrderTaskParam) {
        WareOrderTask model = wareOrderTaskMapper.selectOne(
                new QueryWrapper<WareOrderTask>()
                    .eq("id",  wareOrderTaskParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(wareOrderTaskParam.getId());
        model.setOrderId(wareOrderTaskParam.getOrderId());
        model.setOrderSn(wareOrderTaskParam.getOrderSn());
        model.setConsignee(wareOrderTaskParam.getConsignee());
        model.setConsigneeTel(wareOrderTaskParam.getConsigneeTel());
        model.setDeliveryAddress(wareOrderTaskParam.getDeliveryAddress());
        model.setOrderComment(wareOrderTaskParam.getOrderComment());
        model.setPaymentWay(wareOrderTaskParam.getPaymentWay());
        model.setTaskStatus(wareOrderTaskParam.getTaskStatus());
        model.setOrderBody(wareOrderTaskParam.getOrderBody());
        model.setTrackingNo(wareOrderTaskParam.getTrackingNo());
        model.setWareId(wareOrderTaskParam.getWareId());
        model.setTaskComment(wareOrderTaskParam.getTaskComment());
        wareOrderTaskMapper.updateById(model);
    }

    /**
     * 库存工作单删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        WareOrderTask model = wareOrderTaskMapper.selectOne(
                new QueryWrapper<WareOrderTask>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        wareOrderTaskMapper.delete(new QueryWrapper<WareOrderTask>().eq("id", id));
    }

}
