import request from '@/utils/request'

// 品牌分类关联列表
export function relationLists(params?: Record<string, any>) {
    return request.get({ url: '/relation/list', params })
}

// 品牌分类关联详情
export function relationDetail(params: Record<string, any>) {
    return request.get({ url: '/relation/detail', params })
}

// 品牌分类关联新增
export function relationAdd(params: Record<string, any>) {
    return request.post({ url: '/relation/add', params })
}

// 品牌分类关联编辑
export function relationEdit(params: Record<string, any>) {
    return request.post({ url: '/relation/edit', params })
}

// 品牌分类关联删除
export function relationDelete(params: Record<string, any>) {
    return request.post({ url: '/relation/del', params })
}
