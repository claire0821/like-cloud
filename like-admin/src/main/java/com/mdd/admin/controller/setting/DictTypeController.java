package com.mdd.admin.controller.setting;

import com.mdd.admin.service.setting.ISettingDictTypeService;
import com.mdd.common.validate.PageParam;
import com.mdd.admin.validate.setting.DictTypeParam;
import com.mdd.admin.vo.setting.SettingDictTypeVo;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.core.PageResult;
import com.mdd.common.validator.annotation.IDMust;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典类型配置管理
 */
@RestController("settingDictTypeController")
@RequestMapping("api/setting/dict/type")
public class DictTypeController {

    @Resource
    ISettingDictTypeService iSettingDictTypeService;

    /**
     * 字典类型所有
     *
     * @author fzr
     * @return Object
     */
    @GetMapping("/all")
    public AjaxResult all() {
        List<SettingDictTypeVo> list = iSettingDictTypeService.all();
        return AjaxResult.success(list);
    }

    /**
     * 字典类型列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return Object
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated PageParam pageParam,
                       @RequestParam Map<String, String> params) {
        PageResult<SettingDictTypeVo> list = iSettingDictTypeService.list(pageParam, params);
        return AjaxResult.success(list);
    }

    /**
     * 字典类型详情
     *
     * @author fzr
     * @param id 主键
     * @return Object
     */
    @GetMapping("/detail")
    public AjaxResult detail(@Validated @IDMust() @RequestParam("id") Integer id) {
        SettingDictTypeVo vo = iSettingDictTypeService.detail(id);
        return AjaxResult.success(vo);
    }

    /**
     * 字典类型新增
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/add")
    public AjaxResult add(@Validated(value = DictTypeParam.create.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.add(dictTypeParam);
        return AjaxResult.success();
    }

    /**
     * 字典类型编辑
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/edit")
    public AjaxResult edit(@Validated(value = DictTypeParam.update.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.edit(dictTypeParam);
        return AjaxResult.success();
    }

    /**
     * 字典类型删除
     *
     * @author fzr
     * @param dictTypeParam 参数
     * @return Object
     */
    @PostMapping("/del")
    public AjaxResult del(@Validated(value = DictTypeParam.delete.class) @RequestBody DictTypeParam dictTypeParam) {
        iSettingDictTypeService.del(dictTypeParam.getIds());
        return AjaxResult.success();
    }

}
