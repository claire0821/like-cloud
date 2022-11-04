import request from '@/utils/request'

// 品牌列表
export function brandLists(params?: Record<string, any>) {
    return request.get({ url: '/brand/list', params })
}

// 品牌详情
export function brandDetail(params: Record<string, any>) {
    return request.get({ url: '/brand/detail', params })
}

// 品牌新增
export function brandAdd(params: Record<string, any>) {
    return request.post({ url: '/brand/add', params })
}

// 品牌编辑
export function brandEdit(params: Record<string, any>) {
    return request.post({ url: '/brand/edit', params })
}

// 品牌删除
export function brandDelete(params: Record<string, any>) {
    return request.post({ url: '/brand/del', params })
}
