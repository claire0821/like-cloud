import request from '@/utils/request'

// 商品库存列表
export function skuLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/sku/list', params })
}

// 商品库存详情
export function skuDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/sku/detail', params })
}

// 商品库存新增
export function skuAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/sku/add', params })
}

// 商品库存编辑
export function skuEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/sku/edit', params })
}


// 商品库存批量删除
export function skuDeleteBatch(params: any) {
    return request.post({ url: '/wave/sku/delBatch', params })
}
