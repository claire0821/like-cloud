import request from '@/utils/request'

// 会员收货地址列表
export function addressLists(params?: Record<string, any>) {
    return request.get({ url: '/member/address/list', params })
}

// 会员收货地址详情
export function addressDetail(params: Record<string, any>) {
    return request.get({ url: '/member/address/detail', params })
}

// 会员收货地址新增
export function addressAdd(params: Record<string, any>) {
    return request.post({ url: '/member/address/add', params })
}

// 会员收货地址编辑
export function addressEdit(params: Record<string, any>) {
    return request.post({ url: '/member/address/edit', params })
}


// 会员收货地址批量删除
export function addressDeleteBatch(params: any) {
    return request.post({ url: '/member/address/delBatch', params })
}
