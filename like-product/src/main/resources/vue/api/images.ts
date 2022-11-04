import request from '@/utils/request'

// sku图片列表
export function imagesLists(params?: Record<string, any>) {
    return request.get({ url: '/images/list', params })
}

// sku图片详情
export function imagesDetail(params: Record<string, any>) {
    return request.get({ url: '/images/detail', params })
}

// sku图片新增
export function imagesAdd(params: Record<string, any>) {
    return request.post({ url: '/images/add', params })
}

// sku图片编辑
export function imagesEdit(params: Record<string, any>) {
    return request.post({ url: '/images/edit', params })
}

// sku图片删除
export function imagesDelete(params: Record<string, any>) {
    return request.post({ url: '/images/del', params })
}
