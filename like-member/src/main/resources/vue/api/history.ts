import request from '@/utils/request'

// 积分变化历史记录列表
export function historyLists(params?: Record<string, any>) {
    return request.get({ url: '/member/history/list', params })
}

// 积分变化历史记录详情
export function historyDetail(params: Record<string, any>) {
    return request.get({ url: '/member/history/detail', params })
}

// 积分变化历史记录新增
export function historyAdd(params: Record<string, any>) {
    return request.post({ url: '/member/history/add', params })
}

// 积分变化历史记录编辑
export function historyEdit(params: Record<string, any>) {
    return request.post({ url: '/member/history/edit', params })
}


// 积分变化历史记录批量删除
export function historyDeleteBatch(params: any) {
    return request.post({ url: '/member/history/delBatch', params })
}
