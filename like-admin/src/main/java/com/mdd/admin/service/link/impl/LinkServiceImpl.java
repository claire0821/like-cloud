package com.mdd.admin.service.link.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.admin.service.link.ILinkService;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.LinkParam;
import com.mdd.admin.vo.link.LinkListVo;
import com.mdd.admin.vo.link.LinkDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.Link;
import com.mdd.common.mapper.LinkMapper;
import com.mdd.common.utils.UrlUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 导航链接实现类
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper,Link> implements ILinkService {

    @Resource
    LinkMapper linkMapper;

    /**
     * 导航链接列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LinkListVo>
     */
    @Override
    public PageResult<LinkListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        linkMapper.setSearch(queryWrapper, params, new String[]{
                "=:linkType@link_type:int",
                "like:linkName@link_name:str",
                "=:linkPath@link_path:str",
                "=:linkIcon@link_icon:str",
        });

        IPage<Link> iPage = linkMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<LinkListVo> list = new LinkedList<>();
        for(Link item : iPage.getRecords()) {
            LinkListVo vo = new LinkListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setImage(UrlUtil.toAbsoluteUrl(item.getImage()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 导航链接列表
     *
     * @return List<LinkListVo>
     */
    @Override
    public List<LinkListVo> listAll() {
        QueryWrapper<Link> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        final List<Link> links = linkMapper.selectList(queryWrapper);
        List<LinkListVo> list = new LinkedList<>();
        for(Link item : links) {
            LinkListVo vo = new LinkListVo();
            BeanUtils.copyProperties(item, vo);
            vo.setImage(UrlUtil.toAbsoluteUrl(item.getImage()));
            list.add(vo);
        }
        return list;
    }
    /**
     * 导航链接详情
     *
     * @param id 主键参数
     * @return Link
     */
    @Override
    public LinkDetailVo detail(Long id) {
        Link model = linkMapper.selectOne(
                new QueryWrapper<Link>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        LinkDetailVo vo = new LinkDetailVo();
        BeanUtils.copyProperties(model, vo);
        vo.setImage(UrlUtil.toAbsoluteUrl(model.getImage()));
        return vo;
    }

    /**
     * 导航链接新增
     *
     * @param linkParam 参数
     */
    @Override
    public void add(LinkParam linkParam) {
        Link model = linkMapper.selectOne(
                new QueryWrapper<Link>()
                        .eq("name", linkParam.getName())
                        .eq("path",linkParam.getPath())
                        .last("limit 1"));

        if(model != null) {
            linkMapper.deleteById(model.getId());
        }

        model = new Link();
        model.setType(linkParam.getType());
        model.setName(linkParam.getName());
        model.setPath(linkParam.getPath());
        model.setImage(UrlUtil.toRelativeUrl(linkParam.getImage()));
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        linkMapper.insert(model);
    }

    /**
     * 导航链接编辑
     *
     * @param linkParam 参数
     */
    @Override
    public void edit(LinkParam linkParam) {
        Link model = linkMapper.selectOne(
                new QueryWrapper<Link>()
                        .eq("id",  linkParam.getId())
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(linkParam.getId());
        model.setType(linkParam.getType());
        model.setName(linkParam.getName());
        model.setPath(linkParam.getPath());
        model.setImage(linkParam.getImage());
        model.setUpdateTime(new Date());
        linkMapper.updateById(model);
    }

    /**
     * 导航链接删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Link model = linkMapper.selectOne(
                new QueryWrapper<Link>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        linkMapper.delete(new QueryWrapper<Link>().eq("id", id));
    }

    @Override
    public void delete(String path, String name, String image) {
        final QueryWrapper<Link> linkQueryWrapper = new QueryWrapper<>();
        if(path != null) {
            linkQueryWrapper.eq("path", path);
        }
        if(name != null) {
            linkQueryWrapper.eq("name", name);
        }
        if(image != null) {
            linkQueryWrapper.eq("image", image);
        }
        linkQueryWrapper.last("limit 1");
        Link model = linkMapper.selectOne(linkQueryWrapper);
        if(model != null) {
            linkMapper.deleteById(model.getId());
        }
    }
}
