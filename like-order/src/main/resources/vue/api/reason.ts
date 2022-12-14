import request from '@/utils/request'

// 退货原因列表
export function reasonLists(params?: Record<string, any>) {
    return request.get({ url: '/order/reason/list', params })
}

// 退货原因详情
export function reasonDetail(params: Record<string, any>) {
    return request.get({ url: '/order/reason/detail', params })
}

// 退货原因新增
export function reasonAdd(params: Record<string, any>) {
    return request.post({ url: '/order/reason/add', params })
}

// 退货原因编辑
export function reasonEdit(params: Record<string, any>) {
    return request.post({ url: '/order/reason/edit', params })
}


// 退货原因批量删除
export function reasonDeleteBatch(params: any) {
    return request.post({ url: '/order/reason/delBatch', params })
}
