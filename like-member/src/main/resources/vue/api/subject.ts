import request from '@/utils/request'

// 会员收藏的专题活动列表
export function subjectLists(params?: Record<string, any>) {
    return request.get({ url: '/member/subject/list', params })
}

// 会员收藏的专题活动详情
export function subjectDetail(params: Record<string, any>) {
    return request.get({ url: '/member/subject/detail', params })
}

// 会员收藏的专题活动新增
export function subjectAdd(params: Record<string, any>) {
    return request.post({ url: '/member/subject/add', params })
}

// 会员收藏的专题活动编辑
export function subjectEdit(params: Record<string, any>) {
    return request.post({ url: '/member/subject/edit', params })
}


// 会员收藏的专题活动批量删除
export function subjectDeleteBatch(params: any) {
    return request.post({ url: '/member/subject/delBatch', params })
}
