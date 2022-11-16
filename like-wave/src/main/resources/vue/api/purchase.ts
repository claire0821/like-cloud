import request from '@/utils/request'

// 采购信息列表
export function purchaseLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/purchase/list', params })
}

// 采购信息详情
export function purchaseDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/purchase/detail', params })
}

// 采购信息新增
export function purchaseAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/purchase/add', params })
}

// 采购信息编辑
export function purchaseEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/purchase/edit', params })
}


// 采购信息批量删除
export function purchaseDeleteBatch(params: any) {
    return request.post({ url: '/wave/purchase/delBatch', params })
}
