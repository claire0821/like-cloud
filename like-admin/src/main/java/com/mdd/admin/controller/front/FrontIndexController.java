package com.mdd.admin.controller.front;

import com.mdd.admin.service.front.IFrontIndexService;
import com.mdd.admin.vo.article.ArticleListVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validate.PageParam;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
/**
 * @program: server
 * @description: 主页管理
 * @author: Claire
 * @create: 2022-11-21 17:17
 **/



/**
 * 主页管理
 */
@RestController
@RequestMapping("/api/front")
public class FrontIndexController {

    @Resource
    IFrontIndexService iIndexService;

    /**
     * 首页
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> detail = iIndexService.index();
        return AjaxResult.success(detail);
    }

    /**
     * 装修
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/decorate")
    public Object decorate(@Validated @IDMust() @RequestParam("id") Integer id) {
        Map<String, Object> detail = iIndexService.decorate(id);
        return AjaxResult.success(detail);
    }

    /**
     * 配置
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/config")
    public AjaxResult<Map<String, Object>> config() {
        Map<String, Object> map = iIndexService.config();
        return AjaxResult.success(map);
    }

    /**
     * 协议
     *
     * @author fzr
     * @param type 类型 service=服务协议,privacy=隐私协议
     * @return Object
     */
    @GetMapping("/policy")
    public Object policy(@RequestParam String type) {
        Map<String, String> map = iIndexService.policy(type);
        return AjaxResult.success(map);
    }

    /**
     * 热搜
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/hotSearch")
    public Object hotSearch() {
        List<String> list = iIndexService.hotSearch();
        return AjaxResult.success(list);
    }

    /**
     * 搜索
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/search")
    public Object search(@Validated PageParam pageParam,
                         @RequestParam Map<String, String> params) {
        PageResult<ArticleListVo> list = iIndexService.search(pageParam, params);
        return AjaxResult.success(list);
    }

}
