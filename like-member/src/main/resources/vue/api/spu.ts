import request from '@/utils/request'

// 会员收藏的商品列表
export function spuLists(params?: Record<string, any>) {
    return request.get({ url: '/spu/list', params })
}

// 会员收藏的商品详情
export function spuDetail(params: Record<string, any>) {
    return request.get({ url: '/spu/detail', params })
}

// 会员收藏的商品新增
export function spuAdd(params: Record<string, any>) {
    return request.post({ url: '/spu/add', params })
}

// 会员收藏的商品编辑
export function spuEdit(params: Record<string, any>) {
    return request.post({ url: '/spu/edit', params })
}

// 会员收藏的商品删除
export function spuDelete(params: Record<string, any>) {
    return request.post({ url: '/spu/del', params })
}
