import request from '@/utils/request'

// 订单配置信息列表
export function settingLists(params?: Record<string, any>) {
    return request.get({ url: '/order/setting/list', params })
}

// 订单配置信息详情
export function settingDetail(params: Record<string, any>) {
    return request.get({ url: '/order/setting/detail', params })
}

// 订单配置信息新增
export function settingAdd(params: Record<string, any>) {
    return request.post({ url: '/order/setting/add', params })
}

// 订单配置信息编辑
export function settingEdit(params: Record<string, any>) {
    return request.post({ url: '/order/setting/edit', params })
}


// 订单配置信息批量删除
export function settingDeleteBatch(params: any) {
    return request.post({ url: '/order/setting/delBatch', params })
}
