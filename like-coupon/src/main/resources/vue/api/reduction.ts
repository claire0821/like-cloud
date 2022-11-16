import request from '@/utils/request'

// 商品满减信息列表
export function reductionLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/reduction/list', params })
}

// 商品满减信息详情
export function reductionDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/reduction/detail', params })
}

// 商品满减信息新增
export function reductionAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/reduction/add', params })
}

// 商品满减信息编辑
export function reductionEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/reduction/edit', params })
}


// 商品满减信息批量删除
export function reductionDeleteBatch(params: any) {
    return request.post({ url: '/coupon/reduction/delBatch', params })
}
