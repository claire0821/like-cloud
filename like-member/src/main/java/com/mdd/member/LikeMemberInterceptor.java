//package com.mdd.member;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.mdd.common.config.GlobalConfig;
//import com.mdd.common.constant.MemberConstant;
//import com.mdd.common.core.AjaxResult;
//import com.mdd.common.entity.user.User;
//import com.mdd.common.enums.HttpEnum;
//import com.mdd.common.mapper.user.UserMapper;
//import com.mdd.common.utils.RedisUtil;
//import com.mdd.common.utils.StringUtil;
//import com.mdd.common.vo.UserVo;
//import com.mdd.member.entity.Member;
//import com.mdd.member.service.IMemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 拦截器
// */
////@Component
//public class LikeMemberInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    IMemberService iMemberService;
//
//    @Override
//    public boolean preHandle(@NonNull HttpServletRequest request, HttpServletResponse response, @NonNull Object handler) throws Exception {
//        // 404拦截
//        response.setContentType("application/json;charset=utf-8");
//        if (response.getStatus() == 404) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.REQUEST_404_ERROR.getCode(), HttpEnum.REQUEST_404_ERROR.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // 判断请求接口
//        if (!(handler instanceof HandlerMethod)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // 免登录接口
//        String token = request.getHeader("token");
////        token = RedisUtil.getToken(token);
//        List<String> notLoginUri = Arrays.asList(GlobalConfig.notLoginUri);
//        if (notLoginUri.contains(request.getRequestURI())) {
//            if (StringUtil.isNotEmpty(token)) {
//                UserVo user = RedisUtil.getUser(token);
//                if (user != null) {
//                    LikeMemberThreadLocal.put("userId", user.getId());
//                }
//            }
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // Token是否为空
//        if (StringUtils.isBlank(token)) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_EMPTY.getCode(), HttpEnum.TOKEN_EMPTY.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // Token是否过期
//        if (!RedisUtil.existsToken(token)) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // 用户信息缓存
//        UserVo user = RedisUtil.getUser(token);
//        final Member member = iMemberService.getById(user.getId());
////        User user = userMapper.selectOne(new QueryWrapper<User>()
////                .select("id,sn,username,nickname,mobile,is_disable,is_delete")
////                .eq("id", userId)
////                .last("limit 1"));
//
//        //TODO 校验用户被删除
////        if (user.getIsDelete() == 1) {
////            AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
////            response.getWriter().print(JSON.toJSONString(result));
////            return false;
////        }
//
//        // 校验用户被禁用
//        if (member.getStatus() != MemberConstant.MemberStateEnum.MEMBER_STATE_TYPE_ENABLE.getCode()) {
//            AjaxResult result = AjaxResult.failed(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
//            response.getWriter().print(JSON.toJSONString(result));
//            return false;
//        }
//
//        // 令牌剩余30分钟自动续签
//        if (RedisUtil.ttl(token) < 1800) {
//            RedisUtil.expire(token, 7200L);
//        }
//
//        // 写入本地线程
//        LikeMemberThreadLocal.put("userId", member.getId());
////        LikeMemberThreadLocal.put("userSn", user.getSn());
////        LikeMemberThreadLocal.put("username", user.getUsername());
////        LikeMemberThreadLocal.put("nickname", user.getNickname());
////        LikeMemberThreadLocal.put("mobile", user.getMobile());
//
//        // 验证通过继续操作
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) throws Exception {
//        LikeMemberThreadLocal.remove();
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//
//}
