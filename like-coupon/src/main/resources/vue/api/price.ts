import request from '@/utils/request'

// 商品会员价格列表
export function priceLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/price/list', params })
}

// 商品会员价格详情
export function priceDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/price/detail', params })
}

// 商品会员价格新增
export function priceAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/price/add', params })
}

// 商品会员价格编辑
export function priceEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/price/edit', params })
}


// 商品会员价格批量删除
export function priceDeleteBatch(params: any) {
    return request.post({ url: '/coupon/price/delBatch', params })
}
