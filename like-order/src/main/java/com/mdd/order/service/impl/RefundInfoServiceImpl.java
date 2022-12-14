package com.mdd.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.order.service.IRefundInfoService;
import com.mdd.common.validate.PageParam;
import com.mdd.order.validate.RefundInfoParam;
import com.mdd.order.vo.RefundInfoListVo;
import com.mdd.order.vo.RefundInfoDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.order.entity.RefundInfo;
import com.mdd.order.mapper.RefundInfoMapper;
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
 * 退款信息实现类
 */
@Service
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoMapper,RefundInfo> implements IRefundInfoService {
        
    @Resource
    RefundInfoMapper refundInfoMapper;

    /**
     * 退款信息列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<RefundInfoListVo>
     */
    @Override
    public PageResult<RefundInfoListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<RefundInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        refundInfoMapper.setSearch(queryWrapper, params, new String[]{
            "=:orderReturnId@order_return_id:long",
            "=:refund:str",
            "=:refundSn@refund_sn:str",
            "=:refundStatus@refund_status:int",
            "=:refundChannel@refund_channel:int",
            "=:refundContent@refund_content:str",
        });

        IPage<RefundInfo> iPage = refundInfoMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<RefundInfoListVo> list = new LinkedList<>();
        for(RefundInfo item : iPage.getRecords()) {
            RefundInfoListVo vo = new RefundInfoListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 退款信息详情
     *
     * @param id 主键参数
     * @return RefundInfo
     */
    @Override
    public RefundInfoDetailVo detail(Long id) {
        RefundInfo model = refundInfoMapper.selectOne(
                new QueryWrapper<RefundInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        RefundInfoDetailVo vo = new RefundInfoDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 退款信息新增
     *
     * @param refundInfoParam 参数
     */
    @Override
    public void add(RefundInfoParam refundInfoParam) {
        RefundInfo model = new RefundInfo();
        model.setOrderReturnId(refundInfoParam.getOrderReturnId());
        model.setRefund(refundInfoParam.getRefund());
        model.setRefundSn(refundInfoParam.getRefundSn());
        model.setRefundStatus(refundInfoParam.getRefundStatus());
        model.setRefundChannel(refundInfoParam.getRefundChannel());
        model.setRefundContent(refundInfoParam.getRefundContent());
        refundInfoMapper.insert(model);
    }

    /**
     * 退款信息编辑
     *
     * @param refundInfoParam 参数
     */
    @Override
    public void edit(RefundInfoParam refundInfoParam) {
        RefundInfo model = refundInfoMapper.selectOne(
                new QueryWrapper<RefundInfo>()
                    .eq("id",  refundInfoParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(refundInfoParam.getId());
        model.setOrderReturnId(refundInfoParam.getOrderReturnId());
        model.setRefund(refundInfoParam.getRefund());
        model.setRefundSn(refundInfoParam.getRefundSn());
        model.setRefundStatus(refundInfoParam.getRefundStatus());
        model.setRefundChannel(refundInfoParam.getRefundChannel());
        model.setRefundContent(refundInfoParam.getRefundContent());
        refundInfoMapper.updateById(model);
    }

    /**
     * 退款信息删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        RefundInfo model = refundInfoMapper.selectOne(
                new QueryWrapper<RefundInfo>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        refundInfoMapper.delete(new QueryWrapper<RefundInfo>().eq("id", id));
    }

}
