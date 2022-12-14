import request from '@/utils/request'

// 订单操作历史记录列表
export function historyLists(params?: Record<string, any>) {
    return request.get({ url: '/order/history/list', params })
}

// 订单操作历史记录详情
export function historyDetail(params: Record<string, any>) {
    return request.get({ url: '/order/history/detail', params })
}

// 订单操作历史记录新增
export function historyAdd(params: Record<string, any>) {
    return request.post({ url: '/order/history/add', params })
}

// 订单操作历史记录编辑
export function historyEdit(params: Record<string, any>) {
    return request.post({ url: '/order/history/edit', params })
}


// 订单操作历史记录批量删除
export function historyDeleteBatch(params: any) {
    return request.post({ url: '/order/history/delBatch', params })
}
