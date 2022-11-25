import request from '@/utils/request'

// 会员登录记录列表
export function logLists(params?: Record<string, any>) {
    return request.get({ url: '/member/log/list', params })
}

// 会员登录记录详情
export function logDetail(params: Record<string, any>) {
    return request.get({ url: '/member/log/detail', params })
}

// 会员登录记录新增
export function logAdd(params: Record<string, any>) {
    return request.post({ url: '/member/log/add', params })
}

// 会员登录记录编辑
export function logEdit(params: Record<string, any>) {
    return request.post({ url: '/member/log/edit', params })
}


// 会员登录记录批量删除
export function logDeleteBatch(params: any) {
    return request.post({ url: '/member/log/delBatch', params })
}
