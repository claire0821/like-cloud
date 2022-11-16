import request from '@/utils/request'

// 优惠券信息列表
export function couponLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/coupon/list', params })
}

// 优惠券信息详情
export function couponDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/coupon/detail', params })
}

// 优惠券信息新增
export function couponAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/coupon/add', params })
}

// 优惠券信息编辑
export function couponEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/coupon/edit', params })
}


// 优惠券信息批量删除
export function couponDeleteBatch(params: any) {
    return request.post({ url: '/coupon/coupon/delBatch', params })
}
