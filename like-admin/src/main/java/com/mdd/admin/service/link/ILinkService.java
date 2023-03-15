package com.mdd.admin.service.link;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mdd.common.validate.PageParam;
import com.mdd.common.entity.Link;
import com.mdd.admin.validate.LinkParam;
import com.mdd.admin.vo.link.LinkListVo;
import com.mdd.admin.vo.link.LinkDetailVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 导航链接服务接口类
 */
public interface ILinkService extends IService<Link> {

    /**
     * 导航链接列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LinkVo>
     */
    PageResult<LinkListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 导航链接列表
     *
     * @return PageResult<LinkVo>
     */
    List<LinkListVo> listAll();
    /**
     * 导航链接详情
     *
     * @param id 主键ID
     * @return Link
     */
    LinkDetailVo detail(Long id);

    /**
     * 导航链接新增
     *
     * @param linkParam 参数
     */
    void add(LinkParam linkParam);

    /**
     * 导航链接编辑
     *
     * @param linkParam 参数
     */
    void edit(LinkParam linkParam);

    /**
     * 导航链接删除
     *
     * @param id 主键ID
     */
    void del(Long id);

    void delete(String path, String name, String image);
}
