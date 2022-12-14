package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IOrderItemService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.OrderItemParam;
import com.mdd.order.vo.OrderItemListVo;
import com.mdd.order.vo.OrderItemDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.OrderItem;
import com.mdd.order.mapper.OrderItemMapper;
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
 * 订单项信息实现类
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper,OrderItem> implements IOrderItemService {
        
    @Resource
    OrderItemMapper orderItemMapper;

    /**
     * 订单项信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderItemListVo>
     */
    @Override
    public PageResult<OrderItemListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<OrderItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderItemMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderId@order_id:long",
            "=:orderSn@order_sn:str",
            "=:spuId@spu_id:long",
            "like:spuName@spu_name:str",
            "=:spuPic@spu_pic:str",
            "=:spuBrand@spu_brand:str",
            "=:categoryId@category_id:long",
            "=:skuId@sku_id:long",
            "like:skuName@sku_name:str",
            "=:skuPic@sku_pic:str",
            "=:skuPrice@sku_price:str",
            "=:skuQuantity@sku_quantity:int",
            "=:skuAttrsVals@sku_attrs_vals:str",
            "=:promotionAmount@promotion_amount:str",
            "=:couponAmount@coupon_amount:str",
            "=:integrationAmount@integration_amount:str",
            "=:realAmount@real_amount:str",
            "=:giftIntegration@gift_integration:int",
            "=:giftGrowth@gift_growth:int",
        });

        IPage<OrderItem> iPage = orderItemMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderItemListVo> list = new LinkedList<>();
        for(OrderItem item : iPage.getRecords()) {
            OrderItemListVo vo = new OrderItemListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单项信息详情
     *
     * @param id 主键参数
     * @return OrderItem
     */
    @Override
    public OrderItemDetailVo detail(Long id) {
        OrderItem model = orderItemMapper.selectOne(
                new QueryWrapper<OrderItem>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderItemDetailVo vo = new OrderItemDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单项信息新增
     *
     * @param orderItemParam 参数
     */
    @Override
    public void add(OrderItemParam orderItemParam) {
        OrderItem model = new OrderItem();
        model.setOrderId(orderItemParam.getOrderId());
        model.setOrderSn(orderItemParam.getOrderSn());
        model.setSpuId(orderItemParam.getSpuId());
        model.setSpuName(orderItemParam.getSpuName());
        model.setSpuPic(orderItemParam.getSpuPic());
        model.setSpuBrand(orderItemParam.getSpuBrand());
        model.setCategoryId(orderItemParam.getCategoryId());
        model.setSkuId(orderItemParam.getSkuId());
        model.setSkuName(orderItemParam.getSkuName());
        model.setSkuPic(orderItemParam.getSkuPic());
        model.setSkuPrice(orderItemParam.getSkuPrice());
        model.setSkuQuantity(orderItemParam.getSkuQuantity());
        model.setSkuAttrsVals(orderItemParam.getSkuAttrsVals());
        model.setPromotionAmount(orderItemParam.getPromotionAmount());
        model.setCouponAmount(orderItemParam.getCouponAmount());
        model.setIntegrationAmount(orderItemParam.getIntegrationAmount());
        model.setRealAmount(orderItemParam.getRealAmount());
        model.setGiftIntegration(orderItemParam.getGiftIntegration());
        model.setGiftGrowth(orderItemParam.getGiftGrowth());
        orderItemMapper.insert(model);
    }

    /**
     * 订单项信息编辑
     *
     * @param orderItemParam 参数
     */
    @Override
    public void edit(OrderItemParam orderItemParam) {
        OrderItem model = orderItemMapper.selectOne(
                new QueryWrapper<OrderItem>()
                    .eq("id",  orderItemParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderItemParam.getId());
        model.setOrderId(orderItemParam.getOrderId());
        model.setOrderSn(orderItemParam.getOrderSn());
        model.setSpuId(orderItemParam.getSpuId());
        model.setSpuName(orderItemParam.getSpuName());
        model.setSpuPic(orderItemParam.getSpuPic());
        model.setSpuBrand(orderItemParam.getSpuBrand());
        model.setCategoryId(orderItemParam.getCategoryId());
        model.setSkuId(orderItemParam.getSkuId());
        model.setSkuName(orderItemParam.getSkuName());
        model.setSkuPic(orderItemParam.getSkuPic());
        model.setSkuPrice(orderItemParam.getSkuPrice());
        model.setSkuQuantity(orderItemParam.getSkuQuantity());
        model.setSkuAttrsVals(orderItemParam.getSkuAttrsVals());
        model.setPromotionAmount(orderItemParam.getPromotionAmount());
        model.setCouponAmount(orderItemParam.getCouponAmount());
        model.setIntegrationAmount(orderItemParam.getIntegrationAmount());
        model.setRealAmount(orderItemParam.getRealAmount());
        model.setGiftIntegration(orderItemParam.getGiftIntegration());
        model.setGiftGrowth(orderItemParam.getGiftGrowth());
        orderItemMapper.updateById(model);
    }

    /**
     * 订单项信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        OrderItem model = orderItemMapper.selectOne(
                new QueryWrapper<OrderItem>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderItemMapper.delete(new QueryWrapper<OrderItem>().eq("id", id));
    }

}
