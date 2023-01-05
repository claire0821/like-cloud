package com.mdd.order.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.OrderStatusEnum;
import com.mdd.common.constant.PayConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.BaseException;
import com.mdd.common.to.OrderTo;
import com.mdd.common.to.mq.SeckillOrderTo;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.vo.*;
import com.mdd.order.LikeOrderThreadLocal;
import com.mdd.order.entity.OrderItem;
import com.mdd.order.entity.PaymentInfo;
import com.mdd.order.feign.ICartFeignService;
import com.mdd.order.feign.IMemberFeignService;
import com.mdd.order.feign.IProductFeignService;
import com.mdd.order.feign.IWareFeignService;
import com.mdd.order.service.IOrderItemService;
import com.mdd.order.service.IOrderService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.service.IPaymentInfoService;
import com.mdd.order.to.OrderCreateTo;
import com.mdd.order.validate.OrderParam;
import com.mdd.order.vo.OrderListVo;
import com.mdd.order.vo.OrderDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.Order;
import com.mdd.order.mapper.OrderMapper;
import com.mdd.order.vo.OrderSubmitVo;
import com.mdd.order.vo.PayAsyncVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * 订单实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {
        
    @Resource
    OrderMapper orderMapper;
    @Autowired
    IMemberFeignService iMemberFeignService;
    @Autowired
    ICartFeignService iCartFeignService;
    @Autowired
    Executor executor;
    @Autowired
    IOrderItemService iOrderItemService;
    @Autowired
    IWareFeignService iWareFeignService;
    @Autowired
    IProductFeignService iProductFeignService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    IPaymentInfoService iPaymentInfoService;
    @Autowired
    private BestPayService bestPayService;

    /**
     * 订单列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<OrderListVo>
     */
    @Override
    public PageResult<OrderListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        orderMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "=:orderSn@order_sn:str",
            "=:couponId@coupon_id:long",
            "like:memberUsername@member_username:str",
            "=:totalAmount@total_amount:str",
            "=:payAmount@pay_amount:str",
            "=:freightAmount@freight_amount:str",
            "=:promotionAmount@promotion_amount:str",
            "=:integrationAmount@integration_amount:str",
            "=:couponAmount@coupon_amount:str",
            "=:discountAmount@discount_amount:str",
            "=:payType@pay_type:int",
            "=:sourceType@source_type:int",
            "=:status:int",
            "=:deliveryCompany@delivery_company:str",
            "=:deliverySn@delivery_sn:str",
            "=:autoConfirmDay@auto_confirm_day:int",
            "=:integration:int",
            "=:growth:int",
            "=:billType@bill_type:int",
            "=:billHeader@bill_header:str",
            "=:billContent@bill_content:str",
            "=:billReceiverPhone@bill_receiver_phone:str",
            "=:billReceiverEmail@bill_receiver_email:str",
            "like:receiverName@receiver_name:str",
            "=:receiverPhone@receiver_phone:str",
            "=:receiverPostCode@receiver_post_code:str",
            "=:receiverProvince@receiver_province:str",
            "=:receiverCity@receiver_city:str",
            "=:receiverRegion@receiver_region:str",
            "=:receiverDetailAddress@receiver_detail_address:str",
            "=:note:str",
            "=:confirmStatus@confirm_status:int",
            "=:deleteStatus@delete_status:int",
            "=:useIntegration@use_integration:int",
            "=:paymentTime@payment_time:str",
            "=:deliveryTime@delivery_time:str",
            "=:receiveTime@receive_time:str",
            "=:commentTime@comment_time:str",
            "=:modifyTime@modify_time:str",
        });

        IPage<Order> iPage = orderMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<OrderListVo> list = new LinkedList<>();
        for(Order item : iPage.getRecords()) {
            OrderListVo vo = new OrderListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 订单详情
     *
     * @param id 主键参数
     * @return Order
     */
    @Override
    public OrderDetailVo detail(Long id) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        OrderDetailVo vo = new OrderDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 订单新增
     *
     * @param orderParam 参数
     */
    @Override
    public void add(OrderParam orderParam) {
        Order model = new Order();
        model.setMemberId(orderParam.getMemberId());
        model.setOrderSn(orderParam.getOrderSn());
        model.setCouponId(orderParam.getCouponId());
        model.setCreateTime(new Date());
        model.setMemberUsername(orderParam.getMemberUsername());
        model.setTotalAmount(orderParam.getTotalAmount());
        model.setPayAmount(orderParam.getPayAmount());
        model.setFreightAmount(orderParam.getFreightAmount());
        model.setPromotionAmount(orderParam.getPromotionAmount());
        model.setIntegrationAmount(orderParam.getIntegrationAmount());
        model.setCouponAmount(orderParam.getCouponAmount());
        model.setDiscountAmount(orderParam.getDiscountAmount());
        model.setPayType(orderParam.getPayType());
        model.setSourceType(orderParam.getSourceType());
        model.setStatus(orderParam.getStatus());
        model.setDeliveryCompany(orderParam.getDeliveryCompany());
        model.setDeliverySn(orderParam.getDeliverySn());
        model.setAutoConfirmDay(orderParam.getAutoConfirmDay());
        model.setIntegration(orderParam.getIntegration());
        model.setGrowth(orderParam.getGrowth());
        model.setBillType(orderParam.getBillType());
        model.setBillHeader(orderParam.getBillHeader());
        model.setBillContent(orderParam.getBillContent());
        model.setBillReceiverPhone(orderParam.getBillReceiverPhone());
        model.setBillReceiverEmail(orderParam.getBillReceiverEmail());
        model.setReceiverName(orderParam.getReceiverName());
        model.setReceiverPhone(orderParam.getReceiverPhone());
        model.setReceiverPostCode(orderParam.getReceiverPostCode());
        model.setReceiverProvince(orderParam.getReceiverProvince());
        model.setReceiverCity(orderParam.getReceiverCity());
        model.setReceiverRegion(orderParam.getReceiverRegion());
        model.setReceiverDetailAddress(orderParam.getReceiverDetailAddress());
        model.setNote(orderParam.getNote());
        model.setConfirmStatus(orderParam.getConfirmStatus());
        model.setDeleteStatus(orderParam.getDeleteStatus());
        model.setUseIntegration(orderParam.getUseIntegration());
        model.setPaymentTime(orderParam.getPaymentTime());
        model.setDeliveryTime(orderParam.getDeliveryTime());
        model.setReceiveTime(orderParam.getReceiveTime());
        model.setCommentTime(orderParam.getCommentTime());
        model.setModifyTime(orderParam.getModifyTime());
        orderMapper.insert(model);
    }

    /**
     * 订单编辑
     *
     * @param orderParam 参数
     */
    @Override
    public void edit(OrderParam orderParam) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id",  orderParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(orderParam.getId());
        model.setMemberId(orderParam.getMemberId());
        model.setOrderSn(orderParam.getOrderSn());
        model.setCouponId(orderParam.getCouponId());
        model.setMemberUsername(orderParam.getMemberUsername());
        model.setTotalAmount(orderParam.getTotalAmount());
        model.setPayAmount(orderParam.getPayAmount());
        model.setFreightAmount(orderParam.getFreightAmount());
        model.setPromotionAmount(orderParam.getPromotionAmount());
        model.setIntegrationAmount(orderParam.getIntegrationAmount());
        model.setCouponAmount(orderParam.getCouponAmount());
        model.setDiscountAmount(orderParam.getDiscountAmount());
        model.setPayType(orderParam.getPayType());
        model.setSourceType(orderParam.getSourceType());
        model.setStatus(orderParam.getStatus());
        model.setDeliveryCompany(orderParam.getDeliveryCompany());
        model.setDeliverySn(orderParam.getDeliverySn());
        model.setAutoConfirmDay(orderParam.getAutoConfirmDay());
        model.setIntegration(orderParam.getIntegration());
        model.setGrowth(orderParam.getGrowth());
        model.setBillType(orderParam.getBillType());
        model.setBillHeader(orderParam.getBillHeader());
        model.setBillContent(orderParam.getBillContent());
        model.setBillReceiverPhone(orderParam.getBillReceiverPhone());
        model.setBillReceiverEmail(orderParam.getBillReceiverEmail());
        model.setReceiverName(orderParam.getReceiverName());
        model.setReceiverPhone(orderParam.getReceiverPhone());
        model.setReceiverPostCode(orderParam.getReceiverPostCode());
        model.setReceiverProvince(orderParam.getReceiverProvince());
        model.setReceiverCity(orderParam.getReceiverCity());
        model.setReceiverRegion(orderParam.getReceiverRegion());
        model.setReceiverDetailAddress(orderParam.getReceiverDetailAddress());
        model.setNote(orderParam.getNote());
        model.setConfirmStatus(orderParam.getConfirmStatus());
        model.setDeleteStatus(orderParam.getDeleteStatus());
        model.setUseIntegration(orderParam.getUseIntegration());
        model.setPaymentTime(orderParam.getPaymentTime());
        model.setDeliveryTime(orderParam.getDeliveryTime());
        model.setReceiveTime(orderParam.getReceiveTime());
        model.setCommentTime(orderParam.getCommentTime());
        model.setModifyTime(orderParam.getModifyTime());
        orderMapper.updateById(model);
    }

    /**
     * 订单删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Order model = orderMapper.selectOne(
                new QueryWrapper<Order>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        orderMapper.delete(new QueryWrapper<Order>().eq("id", id));
    }

    /**
     * 订单创建
     *
     */
    @Override
    public OrderCreateTo createOrder() {
        final Long userId = LikeOrderThreadLocal.getUserId();
        OrderCreateTo createTo = new OrderCreateTo();

        //1、生成订单号
        String orderSn = IdWorker.getTimeId();
        createTo.setOrderSn(orderSn);

        //获取当前线程请求头信息(解决Feign异步调用丢失请求头问题)
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        //收货人
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);

            AjaxResult<MemberReceiveAddressVo> result = iMemberFeignService.getDefaultAddress();
            if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                MemberReceiveAddressVo memberAddressMapData = result.getData();
                createTo.setAddress(memberAddressMapData);
            }
        }, executor);

        CompletableFuture<Void> memberFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            AjaxResult<MemberVo> result = iMemberFeignService.detail();
            if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                MemberVo memberVo = result.getData();
                createTo.setMember(memberVo);
            }
        }, executor);

        //购物车选中商品
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            createTo.setCartItems(getProduct());
            createTo.setProductCount(createTo.getCartItems().size());
        }, executor);

        //等到所有任务都完成
        try {
            CompletableFuture.allOf(memberAddressFuture,memberFuture,cartFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        createTo.setIntegralUse(true);
        //3、验价(计算价格、积分等信息)
        createTo.setTotalAmount(createTo.getCartItems().stream().map(CartItemVo::getSkuPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        createTo.setFreightAmount(BigDecimal.valueOf(8));
        createTo.setPayAmount(createTo.getTotalAmount());
        //订单备注
        createTo.setNote("");


        //防重令牌(防止表单重复提交)
        //为用户设置一个token，三十分钟过期时间（存在redis）
        String token = UUID.randomUUID().toString().replace("-", "");
        RedisUtil.set(GlobalConfig.OrderKey + userId,token,30, TimeUnit.MINUTES);
        createTo.setOrderToken(token);

        return createTo;
    }

    //提交订单
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void submitOrder(OrderSubmitVo orderSubmitVo) {
        //获取当前用户登录的信息
        final Long userId = LikeOrderThreadLocal.getUserId();

        //1、验证令牌是否合法【令牌的对比和删除必须保证原子性】
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        String orderToken = orderSubmitVo.getOrderToken();

        //通过lure脚本原子验证令牌和删除令牌
        Long result = RedisUtil.handler().execute(new DefaultRedisScript<Long>(script, Long.class),
                Arrays.asList(GlobalConfig.OrderKey + userId),
                orderToken);

        if (result == 0L) {
            //令牌验证失败
            throw new BaseException(HttpEnum.ORDER_ERROR.getCode(), HttpEnum.ORDER_ERROR.getMsg());
        }

        OrderCreateTo order = new OrderCreateTo();

        //1、订单号
        order.setOrderSn(orderSubmitVo.getOrderSn());

        //获取当前线程请求头信息(解决Feign异步调用丢失请求头问题)
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        //收货人
        CompletableFuture<Void> memberAddressFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            AjaxResult<MemberReceiveAddressVo> res = iMemberFeignService.getAddress(orderSubmitVo.getAddrId());
            if(res.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                MemberReceiveAddressVo memberAddressMapData = res.getData();
                order.setAddress(memberAddressMapData);
            }
        }, executor);

        //TODO 运费

        //用户信息
        CompletableFuture<Void> memberFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            AjaxResult<MemberVo> res = iMemberFeignService.detail();
            if(res.getCode().equals(HttpEnum.SUCCESS.getCode())) {
                MemberVo memberVo = res.getData();
                order.setMember(memberVo);
            }
        }, executor);

        //购物车选中商品
        CompletableFuture<Void> cartFuture = CompletableFuture.runAsync(() -> {
            //每一个线程都来共享之前的请求数据
            RequestContextHolder.setRequestAttributes(requestAttributes);
            order.setCartItems(getProduct());
        }, executor);


        //等到所有任务都完成
        try {
            CompletableFuture.allOf(memberAddressFuture,memberFuture,cartFuture).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        order.setIntegralUse(true);
        //3、验价(计算价格、积分等信息)
        order.setTotalAmount(order.getCartItems().stream().map(CartItemVo::getSkuPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        order.setFreightAmount(BigDecimal.valueOf(8));
        //订单备注
        order.setNote(orderSubmitVo.getNote());
        //订单初始状态
        order.setStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //保存到数据库
        saveOrder(order);

        //TODO 库存锁定
        WareSkuLockVo wareSkuLockVo = new WareSkuLockVo();
        wareSkuLockVo.setOrderSn(order.getOrderSn());
        wareSkuLockVo.setLocks(order.getCartItems());
        final AjaxResult<List<LockStockResult>> listAjaxResult = iWareFeignService.orderLockStock(wareSkuLockVo);
        return;

    }

    @Override
    public OrderVo getOrderByOrderSn(String orderSn) {
        final Order order = this.baseMapper.selectOne(new QueryWrapper<Order>().eq("order_sn", orderSn));
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order,orderVo);
        return orderVo;
    }

    @Override
    public void createSeckillOrder(SeckillOrderTo orderTo) {
        Order orderEntity = new Order();
        orderEntity.setOrderSn(orderTo.getOrderSn());
        orderEntity.setMemberId(orderTo.getMemberId());
        orderEntity.setCreateTime(new Date());
        BigDecimal totalPrice = orderTo.getSeckillPrice().multiply(BigDecimal.valueOf(orderTo.getNum()));
        orderEntity.setPayAmount(totalPrice);
        orderEntity.setStatus(OrderStatusEnum.CREATE_NEW.getCode());

        //保存订单
        this.save(orderEntity);

        //保存订单项信息
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderSn(orderTo.getOrderSn());
        orderItem.setRealAmount(totalPrice);

        orderItem.setSkuQuantity(orderTo.getNum());

        //保存商品的spu信息
        AjaxResult<ProductDetaliSpuVo> result = iProductFeignService.getDetialBySkuId(orderTo.getSkuId());
        if(result.getCode().equals(HttpEnum.SUCCESS)) {
            ProductDetaliSpuVo spuInfoData = result.getData();
            orderItem.setSpuId(spuInfoData.getSpuId());
            orderItem.setSpuName(spuInfoData.getSpuName());
            orderItem.setSpuBrand(spuInfoData.getBrandName());
            orderItem.setCategoryId(spuInfoData.getCatelogId());

            //保存订单项数据
            iOrderItemService.save(orderItem);
        }
    }

    /**
     * 保存订单所有数据
     * @param orderCreateTo
     */
    private void saveOrder(OrderCreateTo orderCreateTo) {
        Order order = new Order();
        order.setMemberId(orderCreateTo.getMember().getId());
        order.setMemberUsername(orderCreateTo.getMember().getUsername());
        order.setOrderSn(orderCreateTo.getOrderSn());
        //TODO 多张优惠券
        //order.setCouponId(1);

        order.setTotalAmount(orderCreateTo.getTotalAmount());
        order.setPayAmount(orderCreateTo.getPayAmount());
        order.setFreightAmount(orderCreateTo.getFreightAmount());
        order.setPromotionAmount(orderCreateTo.getPromotionAmount());
        order.setIntegrationAmount(orderCreateTo.getIntegrationAmount());
        order.setCouponAmount(orderCreateTo.getCouponAmount());
        order.setDiscountAmount(orderCreateTo.getDiscountAmount());

        order.setPayType(orderCreateTo.getPayType());
        order.setSourceType(orderCreateTo.getSourceType());
        order.setStatus(orderCreateTo.getStatus());
        //物流
        order.setDeliveryCompany(orderCreateTo.getDeliveryCompany());
        order.setDeliverySn(orderCreateTo.getDeliverySn());
        order.setDeliveryTime(null);
        order.setAutoConfirmDay(7);

        order.setIntegration(orderCreateTo.getIntegration());
        order.setGrowth(orderCreateTo.getGrowth());
        //TODO 发票
        order.setBillType(null);
        order.setBillHeader(null);
        order.setBillContent(null);
        order.setBillReceiverPhone(null);
        order.setBillReceiverEmail(null);

        //收货人
        order.setReceiverName(orderCreateTo.getAddress().getName());
        order.setReceiverPhone(orderCreateTo.getAddress().getPhone());
        order.setReceiverPostCode(orderCreateTo.getAddress().getPostCode());
        order.setReceiverProvince(orderCreateTo.getAddress().getProvince());
        order.setReceiverCity(orderCreateTo.getAddress().getCity());
        order.setReceiverRegion(orderCreateTo.getAddress().getRegion());
        order.setReceiverDetailAddress(orderCreateTo.getAddress().getDetailAddress());
        order.setReceiveTime(null);

        order.setNote(orderCreateTo.getNote());

        //确认收货状态[0->未确认；1->已确认]
        order.setConfirmStatus(0);
        //删除状态【0->未删除；1->已删除】
        order.setDeleteStatus(0);
        //支付时间
        order.setPaymentTime(null);
        //评论时间
        order.setCommentTime(null);

        order.setModifyTime(new Date());
        order.setCreateTime(new Date());
        //保存订单
        this.baseMapper.insert(order);

        //订单商品项
        final List<CartItemVo> cartItems = orderCreateTo.getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItemVo cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getId());
            orderItem.setOrderSn(order.getOrderSn());
            orderItem.setSpuId(cartItem.getSpuId());
            orderItem.setSpuName(cartItem.getSpuName());
            orderItem.setSpuPic(null);
            orderItem.setSpuBrand(cartItem.getBrandName());
            orderItem.setCategoryId(cartItem.getCategoryId());
            orderItem.setSkuId(cartItem.getSkuId());
            orderItem.setSkuName(cartItem.getSkuName());
            orderItem.setSkuPic(cartItem.getSkuPic());
            orderItem.setSkuPrice(cartItem.getSkuPrice());
            orderItem.setSkuQuantity(cartItem.getSkuQuantity());
            orderItem.setSkuAttrsVals(cartItem.getSaleValueStr());
            //TODO 优惠
            orderItem.setPromotionAmount(new BigDecimal("0.0"));
            orderItem.setCouponAmount(new BigDecimal("0.0"));
            orderItem.setIntegrationAmount(new BigDecimal("0.0"));
            //当前订单项的实际价格
            final BigDecimal orign = orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuQuantity()));
            final BigDecimal subtract = orign.subtract(orderItem.getPromotionAmount())
                    .subtract(orderItem.getPromotionAmount())
                    .subtract(orderItem.getIntegrationAmount());
            orderItem.setRealAmount(subtract);

            //TODO 积分
            orderItem.setGiftIntegration(orderItem.getSkuPrice().intValue());
            orderItem.setGiftGrowth(orderItem.getSkuPrice().intValue());
            orderItems.add(orderItem);
        }
        iOrderItemService.saveBatch(orderItems);

    }

    //查询购物车
    private List<CartItemVo> getProduct() {
        //购物车选中商品
        AjaxResult result = iCartFeignService.getCurrentCartItems();
        if(result.getCode().equals(HttpEnum.SUCCESS.getCode())) {
            List<CartItemVo> cartItemVos = (List<CartItemVo>) result.getData();
            return cartItemVos;
        }
        return null;
    }


    /**
     * 关闭订单
     * @param orderEntity
     */
    @Override
    public void closeOrder(Order orderEntity) {
        //关闭订单之前先查询一下数据库，判断此订单状态是否已支付
        Order orderInfo = this.getOne(new QueryWrapper<Order>().
                eq("order_sn",orderEntity.getOrderSn()));

        if (orderInfo.getStatus().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
            //代付款状态进行关单
            Order orderUpdate = new Order();
            orderUpdate.setId(orderInfo.getId());
            orderUpdate.setStatus(OrderStatusEnum.CANCLED.getCode());
            this.updateById(orderUpdate);

            // 发送消息给MQ
            OrderTo orderTo = new OrderTo();
            BeanUtils.copyProperties(orderInfo, orderTo);

            try {
                //TODO 确保每个消息发送成功，给每个消息做好日志记录，(给数据库保存每一个详细信息)保存每个消息的详细信息
                rabbitTemplate.convertAndSend("order-event-exchange", "order.release.other", orderTo);
            } catch (Exception e) {
                //TODO 定期扫描数据库，重新发送失败的消息
            }
        }
    }

    @Override
    public String handlePayResult(PayAsyncVo asyncVo) {
        //保存交易流水信息
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderSn(asyncVo.getOut_trade_no());
        paymentInfo.setAlipayTradeNo(asyncVo.getTrade_no());
        paymentInfo.setTotalAmount(new BigDecimal(asyncVo.getBuyer_pay_amount()));
        paymentInfo.setSubject(asyncVo.getBody());
        paymentInfo.setPaymentStatus(asyncVo.getTrade_status());
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setCallbackTime(asyncVo.getNotify_time());
        //添加到数据库中
        iPaymentInfoService.save(paymentInfo);

        //修改订单状态
        //获取当前状态
        String tradeStatus = asyncVo.getTrade_status();

        if (tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED")) {
            //支付成功状态
            String orderSn = asyncVo.getOut_trade_no(); //获取订单号
            updateOrderStatus(orderSn,OrderStatusEnum.PAYED.getCode(),PayConstant.ALIPAY);
        }

        return "success";
    }

    /**
     * 修改订单状态
     * @param orderSn
     * @param code
     */
    private void updateOrderStatus(String orderSn, Integer code,Integer payType) {
        this.update(new UpdateWrapper<Order>()
                .eq("order_sn",orderSn)
                .set("status",code)
                .set("modify_time",new Date())
                .set("pay_type",payType)
                .set("payment_time",new Date()));
    }

    /**
     * 微信异步通知结果
     * @param notifyData
     * @return
     */
    @Override
    public String asyncNotify(String notifyData) {

        //签名效验
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);


        //2.金额效验（从数据库查订单）
        final Order orderEntity = this.baseMapper.selectOne(new QueryWrapper<Order>().eq("order_sn", payResponse.getOrderId()));

        //如果查询出来的数据是null的话
        //比较严重(正常情况下是不会发生的)发出告警：钉钉、短信
        if (orderEntity == null) {
            //TODO 发出告警，钉钉，短信
            throw new RuntimeException("通过订单编号查询出来的结果是null");
        }

        //判断订单状态状态是否为已支付或者是已取消,如果不是订单状态不是已支付状态
        Integer status = orderEntity.getStatus();
        if (status.equals(OrderStatusEnum.PAYED.getCode()) || status.equals(OrderStatusEnum.CANCLED.getCode())) {
            throw new RuntimeException("该订单已失效,orderNo=" + payResponse.getOrderId());
        }

        /*//判断金额是否一致,Double类型比较大小，精度问题不好控制
        if (orderEntity.getPayAmount().compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0) {
            //TODO 告警
            throw new RuntimeException("异步通知中的金额和数据库里的不一致,orderNo=" + payResponse.getOrderId());
        }*/

        //3.修改订单支付状态
        //支付成功状态
        String orderSn = orderEntity.getOrderSn();
        this.updateOrderStatus(orderSn,OrderStatusEnum.PAYED.getCode(), PayConstant.WXPAY);

        //4.告诉微信不要再重复通知了
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }

}
