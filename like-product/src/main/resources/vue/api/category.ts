import request from '@/utils/request'

// 商品三级分类列表
export function categoryLists(params?: Record<string, any>) {
    return request.get({ url: '/category/list', params })
}

// 商品三级分类详情
export function categoryDetail(params: Record<string, any>) {
    return request.get({ url: '/category/detail', params })
}

// 商品三级分类新增
export function categoryAdd(params: Record<string, any>) {
    return request.post({ url: '/category/add', params })
}

// 商品三级分类编辑
export function categoryEdit(params: Record<string, any>) {
    return request.post({ url: '/category/edit', params })
}

// 商品三级分类删除
export function categoryDelete(params: Record<string, any>) {
    return request.post({ url: '/category/del', params })
}
