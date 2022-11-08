package com.mdd.member.service;

import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.UmsMemberCollectSpuParam;
import com.mdd.member.vo.UmsMemberCollectSpuListVo;
import com.mdd.member.vo.UmsMemberCollectSpuDetailVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 会员收藏的商品服务接口类
 */
public interface IUmsMemberCollectSpuService {

    /**
     * 会员收藏的商品列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<UmsMemberCollectSpuVo>
     */
    PageResult<UmsMemberCollectSpuListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 会员收藏的商品详情
     *
     * @param id 主键ID
     * @return UmsMemberCollectSpu
     */
    UmsMemberCollectSpuDetailVo detail(Long id);

    /**
     * 会员收藏的商品新增
     *
     * @param umsMemberCollectSpuParam 参数
     */
    void add(UmsMemberCollectSpuParam umsMemberCollectSpuParam);

    /**
     * 会员收藏的商品编辑
     *
     * @param umsMemberCollectSpuParam 参数
     */
    void edit(UmsMemberCollectSpuParam umsMemberCollectSpuParam);

    /**
     * 会员收藏的商品删除
     *
     * @param id 主键ID
     */
    void del(Long id);

}
