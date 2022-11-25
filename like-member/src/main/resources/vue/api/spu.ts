import request from '@/utils/request'

// 会员收藏的商品列表
export function spuLists(params?: Record<string, any>) {
    return request.get({ url: '/member/spu/list', params })
}

// 会员收藏的商品详情
export function spuDetail(params: Record<string, any>) {
    return request.get({ url: '/member/spu/detail', params })
}

// 会员收藏的商品新增
export function spuAdd(params: Record<string, any>) {
    return request.post({ url: '/member/spu/add', params })
}

// 会员收藏的商品编辑
export function spuEdit(params: Record<string, any>) {
    return request.post({ url: '/member/spu/edit', params })
}


// 会员收藏的商品批量删除
export function spuDeleteBatch(params: any) {
    return request.post({ url: '/member/spu/delBatch', params })
}
