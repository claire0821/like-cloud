import request from '@/utils/request'

// 会员等级列表
export function levelLists(params?: Record<string, any>) {
    return request.get({ url: '/level/list', params })
}

// 会员等级详情
export function levelDetail(params: Record<string, any>) {
    return request.get({ url: '/level/detail', params })
}

// 会员等级新增
export function levelAdd(params: Record<string, any>) {
    return request.post({ url: '/level/add', params })
}

// 会员等级编辑
export function levelEdit(params: Record<string, any>) {
    return request.post({ url: '/level/edit', params })
}

// 会员等级删除
export function levelDelete(params: Record<string, any>) {
    return request.post({ url: '/level/del', params })
}
