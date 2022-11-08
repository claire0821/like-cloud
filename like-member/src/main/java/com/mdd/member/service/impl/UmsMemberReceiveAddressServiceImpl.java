package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.member.service.IUmsMemberReceiveAddressService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.UmsMemberReceiveAddressParam;
import com.mdd.member.vo.UmsMemberReceiveAddressListVo;
import com.mdd.member.vo.UmsMemberReceiveAddressDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.UmsMemberReceiveAddress;
import com.mdd.member.mapper.UmsMemberReceiveAddressMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 会员收货地址实现类
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements IUmsMemberReceiveAddressService {
        
    @Resource
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    /**
     * 会员收货地址列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberReceiveAddressListVo>
     */
    @Override
    public PageResult<UmsMemberReceiveAddressListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<UmsMemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        umsMemberReceiveAddressMapper.setSearch(queryWrapper, params, new String[]{
            "=:memberId@member_id:long",
            "like:name:str",
            "=:phone:str",
            "=:postCode@post_code:str",
            "=:province:str",
            "=:city:str",
            "=:region:str",
            "=:detailAddress@detail_address:str",
            "=:areacode:str",
            "=:defaultStatus@default_status:int",
        });

        IPage<UmsMemberReceiveAddress> iPage = umsMemberReceiveAddressMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<UmsMemberReceiveAddressListVo> list = new LinkedList<>();
        for(UmsMemberReceiveAddress item : iPage.getRecords()) {
            UmsMemberReceiveAddressListVo vo = new UmsMemberReceiveAddressListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收货地址详情
     *
     * @param id 主键参数
     * @return UmsMemberReceiveAddress
     */
    @Override
    public UmsMemberReceiveAddressDetailVo detail(Long id) {
        UmsMemberReceiveAddress model = umsMemberReceiveAddressMapper.selectOne(
                new QueryWrapper<UmsMemberReceiveAddress>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        UmsMemberReceiveAddressDetailVo vo = new UmsMemberReceiveAddressDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收货地址新增
     *
     * @param umsMemberReceiveAddressParam 参数
     */
    @Override
    public void add(UmsMemberReceiveAddressParam umsMemberReceiveAddressParam) {
        UmsMemberReceiveAddress model = new UmsMemberReceiveAddress();
        model.setMemberId(umsMemberReceiveAddressParam.getMemberId());
        model.setName(umsMemberReceiveAddressParam.getName());
        model.setPhone(umsMemberReceiveAddressParam.getPhone());
        model.setPostCode(umsMemberReceiveAddressParam.getPostCode());
        model.setProvince(umsMemberReceiveAddressParam.getProvince());
        model.setCity(umsMemberReceiveAddressParam.getCity());
        model.setRegion(umsMemberReceiveAddressParam.getRegion());
        model.setDetailAddress(umsMemberReceiveAddressParam.getDetailAddress());
        model.setAreacode(umsMemberReceiveAddressParam.getAreacode());
        model.setDefaultStatus(umsMemberReceiveAddressParam.getDefaultStatus());
        umsMemberReceiveAddressMapper.insert(model);
    }

    /**
     * 会员收货地址编辑
     *
     * @param umsMemberReceiveAddressParam 参数
     */
    @Override
    public void edit(UmsMemberReceiveAddressParam umsMemberReceiveAddressParam) {
        UmsMemberReceiveAddress model = umsMemberReceiveAddressMapper.selectOne(
                new QueryWrapper<UmsMemberReceiveAddress>()
                    .eq("id",  umsMemberReceiveAddressParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(umsMemberReceiveAddressParam.getId());
        model.setMemberId(umsMemberReceiveAddressParam.getMemberId());
        model.setName(umsMemberReceiveAddressParam.getName());
        model.setPhone(umsMemberReceiveAddressParam.getPhone());
        model.setPostCode(umsMemberReceiveAddressParam.getPostCode());
        model.setProvince(umsMemberReceiveAddressParam.getProvince());
        model.setCity(umsMemberReceiveAddressParam.getCity());
        model.setRegion(umsMemberReceiveAddressParam.getRegion());
        model.setDetailAddress(umsMemberReceiveAddressParam.getDetailAddress());
        model.setAreacode(umsMemberReceiveAddressParam.getAreacode());
        model.setDefaultStatus(umsMemberReceiveAddressParam.getDefaultStatus());
        umsMemberReceiveAddressMapper.updateById(model);
    }

    /**
     * 会员收货地址删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        UmsMemberReceiveAddress model = umsMemberReceiveAddressMapper.selectOne(
                new QueryWrapper<UmsMemberReceiveAddress>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        umsMemberReceiveAddressMapper.delete(new QueryWrapper<UmsMemberReceiveAddress>().eq("id", id));
    }

}
