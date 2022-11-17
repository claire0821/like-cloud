import request from '@/utils/request'

// 【请填写功能名称】列表
export function logLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/log/list', params })
}

// 【请填写功能名称】详情
export function logDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/log/detail', params })
}

// 【请填写功能名称】新增
export function logAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/log/add', params })
}

// 【请填写功能名称】编辑
export function logEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/log/edit', params })
}


// 【请填写功能名称】批量删除
export function logDeleteBatch(params: any) {
    return request.post({ url: '/wave/log/delBatch', params })
}
