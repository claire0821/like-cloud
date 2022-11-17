import request from '@/utils/request'

// 库存工作单列表
export function detailLists(params?: Record<string, any>) {
    return request.get({ url: '/wave/detail/list', params })
}

// 库存工作单详情
export function detailDetail(params: Record<string, any>) {
    return request.get({ url: '/wave/detail/detail', params })
}

// 库存工作单新增
export function detailAdd(params: Record<string, any>) {
    return request.post({ url: '/wave/detail/add', params })
}

// 库存工作单编辑
export function detailEdit(params: Record<string, any>) {
    return request.post({ url: '/wave/detail/edit', params })
}


// 库存工作单批量删除
export function detailDeleteBatch(params: any) {
    return request.post({ url: '/wave/detail/delBatch', params })
}
