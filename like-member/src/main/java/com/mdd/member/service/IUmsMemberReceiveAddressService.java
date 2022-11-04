package com.mdd.member.service;

import com.mdd.admin.validate.common.PageParam;
import com.mdd.member.validate.UmsMemberReceiveAddressParam;
import com.mdd.member.vo.UmsMemberReceiveAddressListVo;
import com.mdd.member.vo.UmsMemberReceiveAddressDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员收货地址服务接口类
 */
public interface IUmsMemberReceiveAddressService {

    /**
     * 会员收货地址列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberReceiveAddressVo>
     */
    PageResult<UmsMemberReceiveAddressListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收货地址详情
     *
     * @param id 主键ID
     * @return UmsMemberReceiveAddress
     */
    UmsMemberReceiveAddressDetailVo detail(Long id);

    /**
     * 会员收货地址新增
     *
     * @param umsMemberReceiveAddressParam 参数
     */
    void add(UmsMemberReceiveAddressParam umsMemberReceiveAddressParam);

    /**
     * 会员收货地址编辑
     *
     * @param umsMemberReceiveAddressParam 参数
     */
    void edit(UmsMemberReceiveAddressParam umsMemberReceiveAddressParam);

    /**
     * 会员收货地址删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
