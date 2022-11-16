import request from '@/utils/request'

// 优惠券领取历史记录列表
export function historyLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/history/list', params })
}

// 优惠券领取历史记录详情
export function historyDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/history/detail', params })
}

// 优惠券领取历史记录新增
export function historyAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/history/add', params })
}

// 优惠券领取历史记录编辑
export function historyEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/history/edit', params })
}


// 优惠券领取历史记录批量删除
export function historyDeleteBatch(params: any) {
    return request.post({ url: '/coupon/history/delBatch', params })
}
