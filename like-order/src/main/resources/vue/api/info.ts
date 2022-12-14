import request from '@/utils/request'

// 退款信息列表
export function infoLists(params?: Record<string, any>) {
    return request.get({ url: '/order/info/list', params })
}

// 退款信息详情
export function infoDetail(params: Record<string, any>) {
    return request.get({ url: '/order/info/detail', params })
}

// 退款信息新增
export function infoAdd(params: Record<string, any>) {
    return request.post({ url: '/order/info/add', params })
}

// 退款信息编辑
export function infoEdit(params: Record<string, any>) {
    return request.post({ url: '/order/info/edit', params })
}


// 退款信息批量删除
export function infoDeleteBatch(params: any) {
    return request.post({ url: '/order/info/delBatch', params })
}
