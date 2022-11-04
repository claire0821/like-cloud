import request from '@/utils/request'

// 会员收货地址列表
export function addressLists(params?: Record<string, any>) {
    return request.get({ url: '/address/list', params })
}

// 会员收货地址详情
export function addressDetail(params: Record<string, any>) {
    return request.get({ url: '/address/detail', params })
}

// 会员收货地址新增
export function addressAdd(params: Record<string, any>) {
    return request.post({ url: '/address/add', params })
}

// 会员收货地址编辑
export function addressEdit(params: Record<string, any>) {
    return request.post({ url: '/address/edit', params })
}

// 会员收货地址删除
export function addressDelete(params: Record<string, any>) {
    return request.post({ url: '/address/del', params })
}
