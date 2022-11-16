import request from '@/utils/request'

// 商品spu积分设置列表
export function boundsLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/bounds/list', params })
}

// 商品spu积分设置详情
export function boundsDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/bounds/detail', params })
}

// 商品spu积分设置新增
export function boundsAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/bounds/add', params })
}

// 商品spu积分设置编辑
export function boundsEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/bounds/edit', params })
}


// 商品spu积分设置批量删除
export function boundsDeleteBatch(params: any) {
    return request.post({ url: '/coupon/bounds/delBatch', params })
}
