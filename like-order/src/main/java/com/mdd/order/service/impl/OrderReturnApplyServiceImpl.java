package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderReturnApplyService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderReturnApplyParam;
import com.mdd.order.vo.OrderReturnApplyListVo;
import com.mdd.order.vo.OrderReturnApplyDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderReturnApply;
import com.mdd.order.mapper.OrderReturnApplyMapper;
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
 * 订单退货申请实现类
 */
@Service
public class OrderReturnApplyServiceImpl extends ServiceImpl<OrderReturnApplyMapper,OrderReturnApply> implements IOrderReturnApplyService {
        
    @Resource
    OrderReturnApplyMapper orderReturnApplyMapper;

    /**
     * 订单退货申请列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderReturnApplyListVo>
     */
    @Override
    public PageResult<OrderReturnApplyListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<OrderReturnApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderReturnApplyMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderId@order_id:long",
            "=:skuId@sku_id:long",
            "=:orderSn@order_sn:str",
            "like:memberUsername@member_username:str",
            "=:returnAmount@return_amount:str",
            "like:returnName@return_name:str",
            "=:returnPhone@return_phone:str",
            "=:status:int",
            "=:handleTime@handle_time:str",
            "=:skuImg@sku_img:str",
            "like:skuName@sku_name:str",
            "=:skuBrand@sku_brand:str",
            "=:skuAttrsVals@sku_attrs_vals:str",
            "=:skuCount@sku_count:int",
            "=:skuPrice@sku_price:str",
            "=:skuRealPrice@sku_real_price:str",
            "=:reason:str",
            "=:description述:str",
            "=:descPics@desc_pics:str",
            "=:handleNote@handle_note:str",
            "=:handleMan@handle_man:str",
            "=:receiveMan@receive_man:str",
            "=:receiveTime@receive_time:str",
            "=:receiveNote@receive_note:str",
            "=:receivePhone@receive_phone:str",
            "=:companyAddress@company_address:str",
        });

        IPage<OrderReturnApply> iPage = orderReturnApplyMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderReturnApplyListVo> list = new LinkedList<>();
        for(OrderReturnApply item : iPage.getRecords()) {
            OrderReturnApplyListVo vo = new OrderReturnApplyListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单退货申请详情
     *
     * @param id 主键参数
     * @return OrderReturnApply
     */
    @Override
    public OrderReturnApplyDetailVo detail(Long id) {
        OrderReturnApply model = orderReturnApplyMapper.selectOne(
                new QueryWrapper<OrderReturnApply>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderReturnApplyDetailVo vo = new OrderReturnApplyDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单退货申请新增
     *
     * @param orderReturnApplyParam 参数
     */
    @Override
    public void add(OrderReturnApplyParam orderReturnApplyParam) {
        OrderReturnApply model = new OrderReturnApply();
        model.setOrderId(orderReturnApplyParam.getOrderId());
        model.setSkuId(orderReturnApplyParam.getSkuId());
        model.setOrderSn(orderReturnApplyParam.getOrderSn());
        model.setCreateTime(new Date());
        model.setMemberUsername(orderReturnApplyParam.getMemberUsername());
        model.setReturnAmount(orderReturnApplyParam.getReturnAmount());
        model.setReturnName(orderReturnApplyParam.getReturnName());
        model.setReturnPhone(orderReturnApplyParam.getReturnPhone());
        model.setStatus(orderReturnApplyParam.getStatus());
        model.setHandleTime(orderReturnApplyParam.getHandleTime());
        model.setSkuImg(orderReturnApplyParam.getSkuImg());
        model.setSkuName(orderReturnApplyParam.getSkuName());
        model.setSkuBrand(orderReturnApplyParam.getSkuBrand());
        model.setSkuAttrsVals(orderReturnApplyParam.getSkuAttrsVals());
        model.setSkuCount(orderReturnApplyParam.getSkuCount());
        model.setSkuPrice(orderReturnApplyParam.getSkuPrice());
        model.setSkuRealPrice(orderReturnApplyParam.getSkuRealPrice());
        model.setReason(orderReturnApplyParam.getReason());
        model.setDescription述(orderReturnApplyParam.getDescription述());
        model.setDescPics(orderReturnApplyParam.getDescPics());
        model.setHandleNote(orderReturnApplyParam.getHandleNote());
        model.setHandleMan(orderReturnApplyParam.getHandleMan());
        model.setReceiveMan(orderReturnApplyParam.getReceiveMan());
        model.setReceiveTime(orderReturnApplyParam.getReceiveTime());
        model.setReceiveNote(orderReturnApplyParam.getReceiveNote());
        model.setReceivePhone(orderReturnApplyParam.getReceivePhone());
        model.setCompanyAddress(orderReturnApplyParam.getCompanyAddress());
        orderReturnApplyMapper.insert(model);
    }

    /**
     * 订单退货申请编辑
     *
     * @param orderReturnApplyParam 参数
     */
    @Override
    public void edit(OrderReturnApplyParam orderReturnApplyParam) {
        OrderReturnApply model = orderReturnApplyMapper.selectOne(
                new QueryWrapper<OrderReturnApply>()
                    .eq("id",  orderReturnApplyParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderReturnApplyParam.getId());
        model.setOrderId(orderReturnApplyParam.getOrderId());
        model.setSkuId(orderReturnApplyParam.getSkuId());
        model.setOrderSn(orderReturnApplyParam.getOrderSn());
        model.setMemberUsername(orderReturnApplyParam.getMemberUsername());
        model.setReturnAmount(orderReturnApplyParam.getReturnAmount());
        model.setReturnName(orderReturnApplyParam.getReturnName());
        model.setReturnPhone(orderReturnApplyParam.getReturnPhone());
        model.setStatus(orderReturnApplyParam.getStatus());
        model.setHandleTime(orderReturnApplyParam.getHandleTime());
        model.setSkuImg(orderReturnApplyParam.getSkuImg());
        model.setSkuName(orderReturnApplyParam.getSkuName());
        model.setSkuBrand(orderReturnApplyParam.getSkuBrand());
        model.setSkuAttrsVals(orderReturnApplyParam.getSkuAttrsVals());
        model.setSkuCount(orderReturnApplyParam.getSkuCount());
        model.setSkuPrice(orderReturnApplyParam.getSkuPrice());
        model.setSkuRealPrice(orderReturnApplyParam.getSkuRealPrice());
        model.setReason(orderReturnApplyParam.getReason());
        model.setDescription述(orderReturnApplyParam.getDescription述());
        model.setDescPics(orderReturnApplyParam.getDescPics());
        model.setHandleNote(orderReturnApplyParam.getHandleNote());
        model.setHandleMan(orderReturnApplyParam.getHandleMan());
        model.setReceiveMan(orderReturnApplyParam.getReceiveMan());
        model.setReceiveTime(orderReturnApplyParam.getReceiveTime());
        model.setReceiveNote(orderReturnApplyParam.getReceiveNote());
        model.setReceivePhone(orderReturnApplyParam.getReceivePhone());
        model.setCompanyAddress(orderReturnApplyParam.getCompanyAddress());
        orderReturnApplyMapper.updateById(model);
    }

    /**
     * 订单退货申请删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        OrderReturnApply model = orderReturnApplyMapper.selectOne(
                new QueryWrapper<OrderReturnApply>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderReturnApplyMapper.delete(new QueryWrapper<OrderReturnApply>().eq("id", id));
    }

}
