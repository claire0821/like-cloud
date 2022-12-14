import request from '@/utils/request'

// 订单退货申请列表
export function applyLists(params?: Record<string, any>) {
    return request.get({ url: '/order/apply/list', params })
}

// 订单退货申请详情
export function applyDetail(params: Record<string, any>) {
    return request.get({ url: '/order/apply/detail', params })
}

// 订单退货申请新增
export function applyAdd(params: Record<string, any>) {
    return request.post({ url: '/order/apply/add', params })
}

// 订单退货申请编辑
export function applyEdit(params: Record<string, any>) {
    return request.post({ url: '/order/apply/edit', params })
}


// 订单退货申请批量删除
export function applyDeleteBatch(params: any) {
    return request.post({ url: '/order/apply/delBatch', params })
}
