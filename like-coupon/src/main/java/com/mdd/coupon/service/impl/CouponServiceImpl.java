package com.mdd.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.utils.StringUtil;
import com.mdd.common.utils.ToolsUtil;
import com.mdd.coupon.service.ICouponService;
import com.mdd.common.validate.PageParam;
import com.mdd.coupon.validate.CouponParam;
import com.mdd.coupon.vo.CouponListVo;
import com.mdd.coupon.vo.CouponDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.coupon.entity.Coupon;
import com.mdd.coupon.mapper.CouponMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 优惠券信息实现类
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper,Coupon> implements ICouponService {
        
    @Resource
    CouponMapper couponMapper;

    /**
     * 优惠券信息列表
     *
     * @param params 搜索参数
     * @return PageResult<CouponListVo>
     */
    @Override
    public PageResult<CouponListVo> list(Map<String, Object> params) {
        final Object pageNo = params.get("pageNo");
        final Object pageSize = params.get("pageSize");
        params.remove(pageNo);
        params.remove(pageSize);

        Integer page  = Integer.valueOf(pageNo.toString());
        Integer limit = Integer.valueOf(pageSize.toString());

        QueryWrapper<Coupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        //String模糊搜索
        final Object key = params.get("key");
        final Object value = params.get("value");
        if(key != null && value != null) {
            String strKey = String.valueOf(key);
            String strValue = String.valueOf(value);
            queryWrapper.like(strKey,strValue);
        }
        params.remove(key);
        params.remove(value);

        Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            final Object val = next.getValue();
            final String k = next.getKey();

            if(val instanceof ArrayList) {
                ArrayList arr = (ArrayList) val;
                for (Object obj : arr) {
                    queryWrapper.and((q) -> {q.eq(StringUtil.toUnderScoreCase(k),obj);});
                }
            }
        }

        IPage<Coupon> iPage = couponMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<CouponListVo> list = new LinkedList<>();
        for(Coupon item : iPage.getRecords()) {
            CouponListVo vo = new CouponListVo();
            BeanUtils.copyProperties(item, vo);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            vo.setStartTime(simpleDateFormat.format(item.getStartTime()));
//            vo.setEndTime(simpleDateFormat.format(item.getEndTime()));
//            vo.setEnableStartTime(simpleDateFormat.format(item.getEnableStartTime()));
//            vo.setEnableEndTime(simpleDateFormat.format(item.getEnableEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 优惠券信息详情
     *
     * @param code 优惠码
     * @return Coupon
     */
    @Override
    public CouponDetailVo detail(String code) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("code", code)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        CouponDetailVo vo = new CouponDetailVo();
        BeanUtils.copyProperties(model, vo);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        vo.setStartTime(simpleDateFormat.format(model.getStartTime()));
//        vo.setEndTime(simpleDateFormat.format(model.getEndTime()));
//        vo.setEnableStartTime(simpleDateFormat.format(model.getEnableStartTime()));
//        vo.setEnableEndTime(simpleDateFormat.format(model.getEnableEndTime()));
        return vo;
    }

    /**
     * 优惠券信息新增
     *
     * @param couponParam 参数
     */
    @Override
    public void add(CouponParam couponParam) {
        Coupon model = new Coupon();
        BeanUtils.copyProperties(couponParam,model);
        model.setCode(ToolsUtil.makeUUID());
        model.setUseCount(0);
        model.setReceiveCount(0);
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        couponMapper.insert(model);
    }

    /**
     * 优惠券信息编辑
     *
     * @param couponParam 参数
     */
    @Override
    public void edit(CouponParam couponParam) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("id",  couponParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");



        model.setCouponImg(couponParam.getCouponImg());
        model.setCouponName(couponParam.getCouponName());
        model.setAmount(couponParam.getAmount());
        model.setPublishCountType(couponParam.getPublishCountType());
        model.setPublishCount(couponParam.getPublishCount());
        model.setUseCount(couponParam.getUseCount());
        model.setReceiveCount(couponParam.getReceiveCount());
        model.setConditionType(couponParam.getConditionType());
        model.setConditionMoney(couponParam.getConditionMoney());
        model.setUseTimeType(couponParam.getUseTimeType());
        model.setUseTimeStart(couponParam.getUseTimeStart());
        model.setUseTimeEnd(couponParam.getEnableEndTime());
        model.setUseTime(couponParam.getUseTime());
        model.setGetType(couponParam.getGetType());
        model.setGetCountType(couponParam.getGetCountType());
        model.setGetCount(couponParam.getGetCount());
        model.setEnableStartTime(couponParam.getEnableStartTime());
        model.setEnableEndTime(couponParam.getEnableEndTime());
        model.setUseType(couponParam.getUseType());
        model.setMemberLevel(couponParam.getMemberLevel());
        model.setNote(couponParam.getNote());
        model.setUpdateTime(new Date());
        couponMapper.updateById(model);
    }

    /**
     * 优惠券信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Coupon model = couponMapper.selectOne(
                new QueryWrapper<Coupon>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        couponMapper.delete(new QueryWrapper<Coupon>().eq("id", id));
    }

}
