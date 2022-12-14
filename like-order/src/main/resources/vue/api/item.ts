import request from '@/utils/request'

// 订单项信息列表
export function itemLists(params?: Record<string, any>) {
    return request.get({ url: '/order/item/list', params })
}

// 订单项信息详情
export function itemDetail(params: Record<string, any>) {
    return request.get({ url: '/order/item/detail', params })
}

// 订单项信息新增
export function itemAdd(params: Record<string, any>) {
    return request.post({ url: '/order/item/add', params })
}

// 订单项信息编辑
export function itemEdit(params: Record<string, any>) {
    return request.post({ url: '/order/item/edit', params })
}


// 订单项信息批量删除
export function itemDeleteBatch(params: any) {
    return request.post({ url: '/order/item/delBatch', params })
}
