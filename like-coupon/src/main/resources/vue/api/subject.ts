import request from '@/utils/request'

// 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】列表
export function subjectLists(params?: Record<string, any>) {
    return request.get({ url: '/coupon/subject/list', params })
}

// 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】详情
export function subjectDetail(params: Record<string, any>) {
    return request.get({ url: '/coupon/subject/detail', params })
}

// 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】新增
export function subjectAdd(params: Record<string, any>) {
    return request.post({ url: '/coupon/subject/add', params })
}

// 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】编辑
export function subjectEdit(params: Record<string, any>) {
    return request.post({ url: '/coupon/subject/edit', params })
}


// 首页专题【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】批量删除
export function subjectDeleteBatch(params: any) {
    return request.post({ url: '/coupon/subject/delBatch', params })
}
