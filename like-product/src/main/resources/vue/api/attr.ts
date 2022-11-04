import request from '@/utils/request'

// 商品属性列表
export function attrLists(params?: Record<string, any>) {
    return request.get({ url: '/attr/list', params })
}

// 商品属性详情
export function attrDetail(params: Record<string, any>) {
    return request.get({ url: '/attr/detail', params })
}

// 商品属性新增
export function attrAdd(params: Record<string, any>) {
    return request.post({ url: '/attr/add', params })
}

// 商品属性编辑
export function attrEdit(params: Record<string, any>) {
    return request.post({ url: '/attr/edit', params })
}

// 商品属性删除
export function attrDelete(params: Record<string, any>) {
    return request.post({ url: '/attr/del', params })
}
