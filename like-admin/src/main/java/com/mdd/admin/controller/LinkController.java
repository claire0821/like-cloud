package com.mdd.admin.controller;

import com.mdd.common.config.aop.Log;
import com.mdd.admin.service.link.ILinkService;
import com.mdd.admin.validate.LinkParam;
import com.mdd.admin.vo.link.LinkListVo;
import com.mdd.admin.vo.link.LinkDetailVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 导航链接管理
 */
@RestController
@RequestMapping("api/admin/link")
public class LinkController {

    @Resource
    ILinkService iLinkService;

    /**
     * 导航链接列表
     *
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult<List<LinkListVo>> list() {
        List<LinkListVo> list = iLinkService.listAll();
        return AjaxResult.success(list);
    }
    /**
     * 导航链接详情
     *
     * @param id 主键ID
     * @return Object
     */
    @GetMapping("/detail")
    public Object detail(@Validated @IDMust()  @RequestParam("id") Long id) {
        LinkDetailVo detail = iLinkService.detail(id);
        return AjaxResult.success(detail);
    }

    /**
     * 导航链接新增
     *
     * @param linkParam 参数
     * @return Object
     */
    @Log(title = "导航链接新增")
    @PostMapping("/add")
    public Object add(@Validated(value = LinkParam.create.class) @RequestBody LinkParam linkParam) {
        iLinkService.add(linkParam);
        return AjaxResult.success();
    }

    /**
     * 导航链接编辑
     *
     * @param linkParam 参数
     * @return Object
     */
    @Log(title = "导航链接编辑")
    @PostMapping("/edit")
    public Object edit(@Validated(value = LinkParam.update.class) @RequestBody LinkParam linkParam) {
        iLinkService.edit(linkParam);
        return AjaxResult.success();
    }


    /**
     * 导航链接批量删除
     *
     * @param ids 参数
     * @return Object
     */
    @Log(title = "导航链接批量删除")
    @PostMapping("/delBatch")
    public Object delBatch(@RequestBody Integer[] ids) {
        iLinkService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }


    /**
     * 根据path和name删除
     *
     * @param linkParam 参数
     * @return Object
     */
    @Log(title = "根据path和name删除")
    @PostMapping("/delete")
    public Object delete(@Validated(value = LinkParam.delete.class) @RequestBody LinkParam linkParam) {
        iLinkService.delete(linkParam.getPath(),linkParam.getName(),linkParam.getImage());
        return AjaxResult.success();
    }
}
