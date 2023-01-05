package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.common.vo.MemberReceiveAddressVo;
import com.mdd.member.entity.MemberReceiveAddress;
import com.mdd.member.validate.MemberReceiveAddressParam;
import com.mdd.member.vo.MemberReceiveAddressListVo;
import com.mdd.member.vo.MemberReceiveAddressDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 会员收货地址服务接口类
 */
public interface IMemberReceiveAddressService extends IService<MemberReceiveAddress> {

    /**
     * 会员收货地址列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberReceiveAddressVo>
     */
    PageResult<MemberReceiveAddressListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收货地址详情
     *
     * @param id 主键ID
     * @return MemberReceiveAddress
     */
    MemberReceiveAddressVo detail(Long id);

    /**
     * 会员收货地址新增
     *
     * @param memberReceiveAddressParam 参数
     */
    void add(MemberReceiveAddressParam memberReceiveAddressParam);

    /**
     * 会员收货地址编辑
     *
     * @param memberReceiveAddressParam 参数
     */
    void edit(MemberReceiveAddressParam memberReceiveAddressParam);

    /**
     * 会员收货地址删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    List<MemberReceiveAddressVo> listByMember();

    MemberReceiveAddressVo getDefaultAddress();

    void setDefaultAddress(Long id);
}
