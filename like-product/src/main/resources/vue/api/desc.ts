import request from '@/utils/request'

// spu信息介绍列表
export function descLists(params?: Record<string, any>) {
    return request.get({ url: '/desc/list', params })
}

// spu信息介绍详情
export function descDetail(params: Record<string, any>) {
    return request.get({ url: '/desc/detail', params })
}

// spu信息介绍新增
export function descAdd(params: Record<string, any>) {
    return request.post({ url: '/desc/add', params })
}

// spu信息介绍编辑
export function descEdit(params: Record<string, any>) {
    return request.post({ url: '/desc/edit', params })
}

// spu信息介绍删除
export function descDelete(params: Record<string, any>) {
    return request.post({ url: '/desc/del', params })
}
