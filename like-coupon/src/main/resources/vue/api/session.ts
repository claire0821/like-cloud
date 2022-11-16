import request from '@/utils/request'

// 秒杀活动场次列表
export function sessionLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/session/list', params })
}

// 秒杀活动场次详情
export function sessionDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/session/detail', params })
}

// 秒杀活动场次新增
export function sessionAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/session/add', params })
}

// 秒杀活动场次编辑
export function sessionEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/session/edit', params })
}


// 秒杀活动场次批量删除
export function sessionDeleteBatch(params: any) {
    return request.post({ url: '/coupon/session/delBatch', params })
}
