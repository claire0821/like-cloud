import request from '@/utils/request'

// 商品评价回复关系列表
export function replayLists(params?: Record<string, any>) {
    return request.get({ url: '/replay/list', params })
}

// 商品评价回复关系详情
export function replayDetail(params: Record<string, any>) {
    return request.get({ url: '/replay/detail', params })
}

// 商品评价回复关系新增
export function replayAdd(params: Record<string, any>) {
    return request.post({ url: '/replay/add', params })
}

// 商品评价回复关系编辑
export function replayEdit(params: Record<string, any>) {
    return request.post({ url: '/replay/edit', params })
}

// 商品评价回复关系删除
export function replayDelete(params: Record<string, any>) {
    return request.post({ url: '/replay/del', params })
}
