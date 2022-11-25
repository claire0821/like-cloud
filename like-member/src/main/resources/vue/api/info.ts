import request from '@/utils/request'

// 会员统计信息列表
export function infoLists(params?: Record<string, any>) {
    return request.get({ url: '/member/info/list', params })
}

// 会员统计信息详情
export function infoDetail(params: Record<string, any>) {
    return request.get({ url: '/member/info/detail', params })
}

// 会员统计信息新增
export function infoAdd(params: Record<string, any>) {
    return request.post({ url: '/member/info/add', params })
}

// 会员统计信息编辑
export function infoEdit(params: Record<string, any>) {
    return request.post({ url: '/member/info/edit', params })
}


// 会员统计信息批量删除
export function infoDeleteBatch(params: any) {
    return request.post({ url: '/member/info/delBatch', params })
}
