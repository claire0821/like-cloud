import request from '@/utils/request'

// 商品评价列表
export function commentLists(params?: Record<string, any>) {
    return request.get({ url: '/comment/list', params })
}

// 商品评价详情
export function commentDetail(params: Record<string, any>) {
    return request.get({ url: '/comment/detail', params })
}

// 商品评价新增
export function commentAdd(params: Record<string, any>) {
    return request.post({ url: '/comment/add', params })
}

// 商品评价编辑
export function commentEdit(params: Record<string, any>) {
    return request.post({ url: '/comment/edit', params })
}

// 商品评价删除
export function commentDelete(params: Record<string, any>) {
    return request.post({ url: '/comment/del', params })
}
