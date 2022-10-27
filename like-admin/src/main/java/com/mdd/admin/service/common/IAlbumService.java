package com.mdd.admin.service.common;

import com.alibaba.fastjson.JSONArray;
import com.mdd.admin.validate.common.AlbumParam;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.vo.album.AlbumVo;
import com.mdd.common.core.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 相册服务接口类
 */
public interface IAlbumService {

    /**
     * 文件列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 其他搜索参数
     * @return PageResult<AlbumVo>
     */
    PageResult<AlbumVo> albumList(PageParam pageParam, Map<String, String> params);

    /**
     * 文件重命名
     *
     * @param id 文件ID
     * @param name 文件名称
     */
    void albumRename(Integer id, String name);

    /**
     * 文件移动
     *
     * @author fzr
     * @param ids 文件ID
     * @param cid 类目ID
     */
    void albumMove(List<Integer> ids, Integer cid);

    /**
     * 文件新增
     *
     * @author fzr
     * @param params 文件信息参数
     */
    Integer albumAdd(Map<String, String> params);

    /**
     * 文件删除
     *
     * @author fzr
     * @param ids 文件ID
     */
    void albumDel(List<Integer> ids);

    /**
     * 分类列表
     *
     * @author fzr
     * @param params 搜索参数
     * @return JSONArray
     */
    JSONArray cateList(Map<String, String> params);

    /**
     * 分类新增
     */
    void cateAdd(AlbumParam albumParam);

    /**
     * 分类编辑
     *
     * @author fzr
     * @param id 分类ID
     * @param name 分类名称
     */
    void cateRename(Integer id, String name);

    /**
     * 分类删除
     *
     * @author fzr
     * @param id 分类ID
     */
    void cateDel(Integer id);

}
