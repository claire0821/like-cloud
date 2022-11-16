import request from '@/utils/request'

// 秒杀活动列表
export function promotionLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/promotion/list', params })
}

// 秒杀活动详情
export function promotionDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/promotion/detail', params })
}

// 秒杀活动新增
export function promotionAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/promotion/add', params })
}

// 秒杀活动编辑
export function promotionEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/promotion/edit', params })
}


// 秒杀活动批量删除
export function promotionDeleteBatch(params: any) {
    return request.post({ url: '/coupon/promotion/delBatch', params })
}
