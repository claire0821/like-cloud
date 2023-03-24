//package com.mdd.common.config.feign;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.stereotype.Component;
//
///**
// * @program: server
// * @description: feign请求拦截器
// * @author: Claire
// * @create: 2023-03-17 17:07
// **/
//@Component
//public class FeignRequestInterceptor implements RequestInterceptor
//{
//    @Override
//    public void apply(RequestTemplate requestTemplate)
//    {
//        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
//        if (StringUtils.isNotNull(httpServletRequest))
//        {
//            Map<String, String> headers = ServletUtils.getHeaders(httpServletRequest);
//            // 传递用户信息请求头，防止丢失
//            String userId = headers.get(CacheConstants.DETAILS_USER_ID);
//            if (StringUtils.isNotEmpty(userId))
//            {
//                requestTemplate.header(CacheConstants.DETAILS_USER_ID, userId);
//            }
//            String userName = headers.get(CacheConstants.DETAILS_USERNAME);
//            if (StringUtils.isNotEmpty(userName))
//            {
//                requestTemplate.header(CacheConstants.DETAILS_USERNAME, userName);
//            }
//        }
//    }
//}