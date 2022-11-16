import request from '@/utils/request'

// 仓库信息列表
export function infoLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/info/list', params })
}

// 仓库信息详情
export function infoDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/info/detail', params })
}

// 仓库信息新增
export function infoAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/info/add', params })
}

// 仓库信息编辑
export function infoEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/info/edit', params })
}


// 仓库信息批量删除
export function infoDeleteBatch(params: any) {
    return request.post({ url: '/wave/info/delBatch', params })
}
