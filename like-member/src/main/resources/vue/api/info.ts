import request from '@/utils/request'

// 会员统计信息列表
export function infoLists(params?: Record<string, any>) {
    return request.get({ url: '/info/list', params })
}

// 会员统计信息详情
export function infoDetail(params: Record<string, any>) {
    return request.get({ url: '/info/detail', params })
}

// 会员统计信息新增
export function infoAdd(params: Record<string, any>) {
    return request.post({ url: '/info/add', params })
}

// 会员统计信息编辑
export function infoEdit(params: Record<string, any>) {
    return request.post({ url: '/info/edit', params })
}

// 会员统计信息删除
export function infoDelete(params: Record<string, any>) {
    return request.post({ url: '/info/del', params })
}
