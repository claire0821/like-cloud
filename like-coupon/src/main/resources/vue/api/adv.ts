import request from '@/utils/request'

// 首页轮播广告列表
export function advLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/adv/list', params })
}

// 首页轮播广告详情
export function advDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/adv/detail', params })
}

// 首页轮播广告新增
export function advAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/adv/add', params })
}

// 首页轮播广告编辑
export function advEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/adv/edit', params })
}


// 首页轮播广告批量删除
export function advDeleteBatch(params: any) {
    return request.post({ url: '/coupon/adv/delBatch', params })
}
