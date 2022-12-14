package com.mdd.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.member.service.IMemberReceiveAddressService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberReceiveAddressParam;
import com.mdd.member.vo.MemberReceiveAddressListVo;
import com.mdd.member.vo.MemberReceiveAddressDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.MemberReceiveAddress;
import com.mdd.member.mapper.MemberReceiveAddressMapper;
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
 * 会员收货地址实现类
 */
@Service
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressMapper,MemberReceiveAddress> implements IMemberReceiveAddressService {
        
    @Resource
    MemberReceiveAddressMapper memberReceiveAddressMapper;

    /**
     * 会员收货地址列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberReceiveAddressListVo>
     */
    @Override
    public PageResult<MemberReceiveAddressListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<MemberReceiveAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberReceiveAddressMapper.setSearch(queryWrapper, params, new String[]{
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

        IPage<MemberReceiveAddress> iPage = memberReceiveAddressMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberReceiveAddressListVo> list = new LinkedList<>();
        for(MemberReceiveAddress item : iPage.getRecords()) {
            MemberReceiveAddressListVo vo = new MemberReceiveAddressListVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员收货地址详情
     *
     * @param id 主键参数
     * @return MemberReceiveAddress
     */
    @Override
    public MemberReceiveAddressDetailVo detail(Long id) {
        MemberReceiveAddress model = memberReceiveAddressMapper.selectOne(
                new QueryWrapper<MemberReceiveAddress>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberReceiveAddressDetailVo vo = new MemberReceiveAddressDetailVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

    /**
     * 会员收货地址新增
     *
     * @param memberReceiveAddressParam 参数
     */
    @Override
    public void add(MemberReceiveAddressParam memberReceiveAddressParam) {
        MemberReceiveAddress model = new MemberReceiveAddress();
        model.setMemberId(memberReceiveAddressParam.getMemberId());
        model.setName(memberReceiveAddressParam.getName());
        model.setPhone(memberReceiveAddressParam.getPhone());
        model.setPostCode(memberReceiveAddressParam.getPostCode());
        model.setProvince(memberReceiveAddressParam.getProvince());
        model.setCity(memberReceiveAddressParam.getCity());
        model.setRegion(memberReceiveAddressParam.getRegion());
        model.setDetailAddress(memberReceiveAddressParam.getDetailAddress());
        model.setAreacode(memberReceiveAddressParam.getAreacode());
        model.setDefaultStatus(memberReceiveAddressParam.getDefaultStatus());
        memberReceiveAddressMapper.insert(model);
    }

    /**
     * 会员收货地址编辑
     *
     * @param memberReceiveAddressParam 参数
     */
    @Override
    public void edit(MemberReceiveAddressParam memberReceiveAddressParam) {
        MemberReceiveAddress model = memberReceiveAddressMapper.selectOne(
                new QueryWrapper<MemberReceiveAddress>()
                    .eq("id",  memberReceiveAddressParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberReceiveAddressParam.getId());
        model.setMemberId(memberReceiveAddressParam.getMemberId());
        model.setName(memberReceiveAddressParam.getName());
        model.setPhone(memberReceiveAddressParam.getPhone());
        model.setPostCode(memberReceiveAddressParam.getPostCode());
        model.setProvince(memberReceiveAddressParam.getProvince());
        model.setCity(memberReceiveAddressParam.getCity());
        model.setRegion(memberReceiveAddressParam.getRegion());
        model.setDetailAddress(memberReceiveAddressParam.getDetailAddress());
        model.setAreacode(memberReceiveAddressParam.getAreacode());
        model.setDefaultStatus(memberReceiveAddressParam.getDefaultStatus());
        memberReceiveAddressMapper.updateById(model);
    }

    /**
     * 会员收货地址删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        MemberReceiveAddress model = memberReceiveAddressMapper.selectOne(
                new QueryWrapper<MemberReceiveAddress>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberReceiveAddressMapper.delete(new QueryWrapper<MemberReceiveAddress>().eq("id", id));
    }

    @Override
    public List<MemberReceiveAddressVo> listByMember(Long memberId) {
        List<MemberReceiveAddress> model = memberReceiveAddressMapper.selectList(
                new QueryWrapper<MemberReceiveAddress>()
                        .eq("member_id", memberId));

        List<MemberReceiveAddressVo> list = new LinkedList<>();
        for(MemberReceiveAddress item : model) {
            MemberReceiveAddressVo vo = new MemberReceiveAddressVo();
            BeanUtils.copyProperties(item, vo);
            list.add(vo);
        }
        return list;
    }

    @Override
    public MemberReceiveAddressVo getDefaultAddress(Long memberId) {
        MemberReceiveAddress model = memberReceiveAddressMapper.selectOne(
                new QueryWrapper<MemberReceiveAddress>()
                        .eq("member_id", memberId)
                        .eq("default_status",1));
        if(model == null) {
            model = memberReceiveAddressMapper.selectOne(
                    new QueryWrapper<MemberReceiveAddress>()
                            .eq("member_id", memberId));
        }
        MemberReceiveAddressVo vo = new MemberReceiveAddressVo();
        BeanUtils.copyProperties(model, vo);
        return vo;
    }

}
