import request from '@/utils/request'

// 专题商品列表
export function spuLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/spu/list', params })
}

// 专题商品详情
export function spuDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/spu/detail', params })
}

// 专题商品新增
export function spuAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/spu/add', params })
}

// 专题商品编辑
export function spuEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/spu/edit', params })
}


// 专题商品批量删除
export function spuDeleteBatch(params: any) {
    return request.post({ url: '/coupon/spu/delBatch', params })
}
