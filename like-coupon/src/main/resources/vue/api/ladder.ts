import request from '@/utils/request'

// 商品阶梯价格列表
export function ladderLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/ladder/list', params })
}

// 商品阶梯价格详情
export function ladderDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/ladder/detail', params })
}

// 商品阶梯价格新增
export function ladderAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/ladder/add', params })
}

// 商品阶梯价格编辑
export function ladderEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/ladder/edit', params })
}


// 商品阶梯价格批量删除
export function ladderDeleteBatch(params: any) {
    return request.post({ url: '/coupon/ladder/delBatch', params })
}
