import request from '@/utils/request'

// 会员登录记录列表
export function logLists(params?: Record<string, any>) {
    return request.get({ url: '/log/list', params })
}

// 会员登录记录详情
export function logDetail(params: Record<string, any>) {
    return request.get({ url: '/log/detail', params })
}

// 会员登录记录新增
export function logAdd(params: Record<string, any>) {
    return request.post({ url: '/log/add', params })
}

// 会员登录记录编辑
export function logEdit(params: Record<string, any>) {
    return request.post({ url: '/log/edit', params })
}

// 会员登录记录删除
export function logDelete(params: Record<string, any>) {
    return request.post({ url: '/log/del', params })
}
