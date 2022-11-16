import request from '@/utils/request'

// 秒杀商品通知订阅列表
export function noticeLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/notice/list', params })
}

// 秒杀商品通知订阅详情
export function noticeDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/notice/detail', params })
}

// 秒杀商品通知订阅新增
export function noticeAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/notice/add', params })
}

// 秒杀商品通知订阅编辑
export function noticeEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/notice/edit', params })
}


// 秒杀商品通知订阅批量删除
export function noticeDeleteBatch(params: any) {
    return request.post({ url: '/coupon/notice/delBatch', params })
}
