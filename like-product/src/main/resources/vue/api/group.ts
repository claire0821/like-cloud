import request from '@/utils/request'

// 属性分组列表
export function groupLists(params?: Record<string, any>) {
    return request.get({ url: '/group/list', params })
}

// 属性分组详情
export function groupDetail(params: Record<string, any>) {
    return request.get({ url: '/group/detail', params })
}

// 属性分组新增
export function groupAdd(params: Record<string, any>) {
    return request.post({ url: '/group/add', params })
}

// 属性分组编辑
export function groupEdit(params: Record<string, any>) {
    return request.post({ url: '/group/edit', params })
}

// 属性分组删除
export function groupDelete(params: Record<string, any>) {
    return request.post({ url: '/group/del', params })
}
