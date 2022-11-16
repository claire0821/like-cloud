import request from '@/utils/request'

// 优惠券与产品关联列表
export function relationLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/relation/list', params })
}

// 优惠券与产品关联详情
export function relationDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/relation/detail', params })
}

// 优惠券与产品关联新增
export function relationAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/relation/add', params })
}

// 优惠券与产品关联编辑
export function relationEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/relation/edit', params })
}


// 优惠券与产品关联批量删除
export function relationDeleteBatch(params: any) {
    return request.post({ url: '/coupon/relation/delBatch', params })
}
