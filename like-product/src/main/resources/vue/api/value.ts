import request from '@/utils/request'

// spu属性值列表
export function valueLists(params?: Record<string, any>) {
    return request.get({ url: '/value/list', params })
}

// spu属性值详情
export function valueDetail(params: Record<string, any>) {
    return request.get({ url: '/value/detail', params })
}

// spu属性值新增
export function valueAdd(params: Record<string, any>) {
    return request.post({ url: '/value/add', params })
}

// spu属性值编辑
export function valueEdit(params: Record<string, any>) {
    return request.post({ url: '/value/edit', params })
}

// spu属性值删除
export function valueDelete(params: Record<string, any>) {
    return request.post({ url: '/value/del', params })
}
