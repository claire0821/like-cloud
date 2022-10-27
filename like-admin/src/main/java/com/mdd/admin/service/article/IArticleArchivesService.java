package com.mdd.admin.service.article;

import com.mdd.admin.validate.article.ArticleParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.article.ArticleDetailVo;
import com.mdd.admin.vo.article.ArticleListVo;
import com.mdd.common.core.PageResult;

import java.util.Map;

/**
 * 文章服务接口类
 */
public interface IArticleArchivesService {

    /**
     * 文章列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<ArticleListVo>
     */
    PageResult<ArticleListVo> list(PageParam pageParam, Map<String, String> params);

    /**
     * 文章详情
     *
     * @author fzr
     * @param id 主键ID
     */
    ArticleDetailVo detail(Integer id);

    /**
     * 文章新增
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    void add(ArticleParam articleParam);

    /**
     * 文章编辑
     *
     * @author fzr
     * @param articleParam 文章参数
     */
    void edit(ArticleParam articleParam);

    /**
     * 文章删除
     *
     * @author fzr
     * @param id 文章主键
     */
    void del(Integer id);

    /**
     * 文章状态
     *
     * @author fzr
     * @param id 文章主键
     */
    void change(Integer id);

}
