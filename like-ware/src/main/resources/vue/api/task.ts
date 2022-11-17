import request from '@/utils/request'

// 库存工作单列表
export function taskLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/task/list', params })
}

// 库存工作单详情
export function taskDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/task/detail', params })
}

// 库存工作单新增
export function taskAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/task/add', params })
}

// 库存工作单编辑
export function taskEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/task/edit', params })
}


// 库存工作单批量删除
export function taskDeleteBatch(params: any) {
    return request.post({ url: '/wave/task/delBatch', params })
}
