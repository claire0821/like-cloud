import request from '@/utils/request'

// 会员列表
export function memberLists(params?: Record<string, any>) {
    return request.get({ url: '/member/member/list', params })
}

// 会员详情
export function memberDetail(params: Record<string, any>) {
    return request.get({ url: '/member/member/detail', params })
}

// 会员新增
export function memberAdd(params: Record<string, any>) {
    return request.post({ url: '/member/member/add', params })
}

// 会员编辑
export function memberEdit(params: Record<string, any>) {
    return request.post({ url: '/member/member/edit', params })
}


// 会员批量删除
export function memberDeleteBatch(params: any) {
    return request.post({ url: '/member/member/delBatch', params })
}
