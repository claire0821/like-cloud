import request from '@/utils/request'

// 积分变化历史记录列表
export function historyLists(params?: Record<string, any>) {
    return request.get({ url: '/history/list', params })
}

// 积分变化历史记录详情
export function historyDetail(params: Record<string, any>) {
    return request.get({ url: '/history/detail', params })
}

// 积分变化历史记录新增
export function historyAdd(params: Record<string, any>) {
    return request.post({ url: '/history/add', params })
}

// 积分变化历史记录编辑
export function historyEdit(params: Record<string, any>) {
    return request.post({ url: '/history/edit', params })
}

// 积分变化历史记录删除
export function historyDelete(params: Record<string, any>) {
    return request.post({ url: '/history/del', params })
}
