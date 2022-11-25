package com.mdd.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.entity.MemberCollectSpu;
import com.mdd.member.validate.MemberCollectSpuParam;
import com.mdd.member.vo.MemberCollectSpuListVo;
import com.mdd.member.vo.MemberCollectSpuDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员收藏的商品服务接口类
 */
public interface IMemberCollectSpuService extends IService<MemberCollectSpu> {

    /**
     * 会员收藏的商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberCollectSpuVo>
     */
    PageResult<MemberCollectSpuListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收藏的商品详情
     *
     * @param id 主键ID
     * @return MemberCollectSpu
     */
    MemberCollectSpuDetailVo detail(Long id);

    /**
     * 会员收藏的商品新增
     *
     * @param memberCollectSpuParam 参数
     */
    void add(MemberCollectSpuParam memberCollectSpuParam);

    /**
     * 会员收藏的商品编辑
     *
     * @param memberCollectSpuParam 参数
     */
    void edit(MemberCollectSpuParam memberCollectSpuParam);

    /**
     * 会员收藏的商品删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
