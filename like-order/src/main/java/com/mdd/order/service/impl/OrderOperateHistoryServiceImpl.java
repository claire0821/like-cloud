package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.constant.OrderConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.feign.AuthFeignService;
import com.mdd.common.vo.MemberVo;
import com.mdd.common.vo.UserVo;
import com.mdd.order.feign.IMemberFeignService;
import com.mdd.order.service.IOrderOperateHistoryService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderOperateHistoryParam;
import com.mdd.order.vo.OrderOperateHistoryVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderOperateHistory;
import com.mdd.order.mapper.OrderOperateHistoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    IMemberFeignService iMemberFeignService;
    @Autowired
    AuthFeignService authFeignService;

    /**
     * 订单操作历史记录列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderOperateHistoryListVo>
     */
    @Override
    public PageResult<OrderOperateHistoryVo> list(PageParam pageParam, Map<String, String> params) {
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

        List<OrderOperateHistoryVo> list = new LinkedList<>();
        for(OrderOperateHistory item : iPage.getRecords()) {
            OrderOperateHistoryVo vo = new OrderOperateHistoryVo();
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
    public OrderOperateHistoryVo detail(Long id) {
        OrderOperateHistory model = orderOperateHistoryMapper.selectOne(
                new QueryWrapper<OrderOperateHistory>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderOperateHistoryVo vo = new OrderOperateHistoryVo();
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
        model.setOrderSn(orderOperateHistoryParam.getOrderSn());
        model.setOperateManType(orderOperateHistoryParam.getOperateManType());
        model.setOperateManId(orderOperateHistoryParam.getOperateManId());
        model.setCreateTime(new Date());
        model.setOrderStatus(orderOperateHistoryParam.getOrderStatus());
        model.setNote(orderOperateHistoryParam.getNote());
        model.setActionType(orderOperateHistoryParam.getActionType());
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
        model.setOrderSn(orderOperateHistoryParam.getOrderSn());
        model.setOperateManType(orderOperateHistoryParam.getOperateManType());
        model.setOperateManId(orderOperateHistoryParam.getOperateManId());
        model.setOrderStatus(orderOperateHistoryParam.getOrderStatus());
        model.setActionType(orderOperateHistoryParam.getActionType());
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

    /**
     * 根据订单编号查找
     * @param orderSn 订单编号
     * @return
     */
    @Override
    public List<OrderOperateHistoryVo> listByOrder(Long orderSn) {
        final List<OrderOperateHistory> orderOperateHistories = this.baseMapper.selectList(new QueryWrapper<OrderOperateHistory>()
                .eq("order_sn", orderSn).orderByAsc("create_time"));
        List<OrderOperateHistoryVo> list = new ArrayList<>();
        for (OrderOperateHistory orderOperateHistory : orderOperateHistories) {
            OrderOperateHistoryVo orderOperateHistoryVo = new OrderOperateHistoryVo();
            BeanUtils.copyProperties(orderOperateHistory,orderOperateHistoryVo);

            //TODO 其他类型用户名获取
            if(orderOperateHistoryVo.getOperateManType().equals(OrderConstant.OperateManTypeEnum.USER.getCode())) {
                AjaxResult<MemberVo> result = iMemberFeignService.detailById(orderOperateHistoryVo.getOperateManId());
                if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                    MemberVo memberVo = result.getData();
                    orderOperateHistoryVo.setOperateMan(memberVo.getUsername());
                }
            } else if(orderOperateHistoryVo.getOperateManType().equals(OrderConstant.OperateManTypeEnum.ADMINISTRATORS)) {

            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderOperateHistoryVo.setCreateTime(simpleDateFormat.format(orderOperateHistory.getCreateTime()));
            list.add(orderOperateHistoryVo);
        }
        return list;
    }

    /**
     * 添加订单操作记录
     * @param orderSn 订单编号
     * @param actionType 操作类型
     * @param note 备注
     */
    @Override
    public void add(String orderSn, Integer actionType, String note) {
        OrderOperateHistory orderOperateHistory = new OrderOperateHistory();
        orderOperateHistory.setOrderSn(orderSn);
        final UserVo data = authFeignService.getUserInfo().getData();
        orderOperateHistory.setOperateManType(data.getType().getCode());
        if(!orderOperateHistory.getOperateManType().equals(OrderConstant.OperateManTypeEnum.SYSTEM.getCode())) {
            orderOperateHistory.setOperateManId(data.getId());
        }
        orderOperateHistory.setActionType(actionType);
        orderOperateHistory.setNote(note);
        orderOperateHistory.setCreateTime(new Date());
        this.save(orderOperateHistory);
    }

}
