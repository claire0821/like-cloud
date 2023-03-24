//package com.mdd.admin;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.mdd.admin.config.AdminConfig;
//import com.mdd.admin.service.system.ISystemAuthAdminService;
//import com.mdd.admin.service.system.ISystemAuthPermService;
//import com.mdd.common.constant.OrderConstant;
//import com.mdd.common.core.AjaxResult;
//import com.mdd.common.enums.HttpEnum;
//import com.mdd.common.utils.RedisUtil;
//import com.mdd.common.utils.ToolsUtil;
//import com.mdd.common.vo.UserVo;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
///**
// * 拦截器
// */
////@Component
//public class LikeAdminInterceptor implements HandlerInterceptor {
//
//    @Resource
//    ISystemAuthAdminService iSystemAuthAdminService;
//
//    @Resource
//    ISystemAuthPermService iSystemAuthPermService;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
//        // 路由转权限
//        String prefix = "/api/";
//        String route = request.getRequestURI().replaceFirst(prefix, "");
//        String auths = route.replace("/", ":");
//
//        // 免登录接口
//        List<String> notLoginUri = Arrays.asList(AdminConfig.notLoginUri);
//        if (notLoginUri.contains(auths)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // Token是否为空
//        String token = request.getHeader("token");
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
//        UserVo user = RedisUtil.getUser(token);
//        // 管理员信息缓存
//        if (user.getType().equals(OrderConstant.OperateManTypeEnum.ADMINISTRATORS.getCode())) {
//            if(!RedisUtil.hExists(AdminConfig.backstageManageKey, String.valueOf(user.getId()))) {
//                iSystemAuthAdminService.cacheAdminUserByUid(user.getId());
//            }
//
//            // 校验用户被删除
//            Map<String, String> map = ToolsUtil.jsonToMap(RedisUtil.hGet(AdminConfig.backstageManageKey, user.getId().toString()).toString());
//            if (map == null || map.get("isDelete").equals("1")) {
//                RedisUtil.del(token);
//                RedisUtil.hDel(AdminConfig.backstageManageKey, user.getId());
//                AjaxResult result = AjaxResult.failed(HttpEnum.TOKEN_INVALID.getCode(), HttpEnum.TOKEN_INVALID.getMsg());
//                response.getWriter().print(JSON.toJSONString(result));
//                return false;
//            }
//
//            // 校验用户被禁用
//            if (map.get("isDisable").equals("1")) {
//                AjaxResult result = AjaxResult.failed(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
//                response.getWriter().print(JSON.toJSONString(result));
//                return false;
//            }
//
//            // 校验角色权限是否存在
//            String roleId = map.get("role");
//            if (!RedisUtil.hExists(AdminConfig.backstageRolesKey, roleId)) {
//                iSystemAuthPermService.cacheRoleMenusByRoleId(Integer.parseInt(roleId));
//            }
//            // 写入本地线程
//            LikeAdminThreadLocal.put("adminId", user.getId());
//            LikeAdminThreadLocal.put("roleId", map.get("role"));
//            LikeAdminThreadLocal.put("username", map.get("username"));
//            LikeAdminThreadLocal.put("nickname", map.get("nickname"));
//
//            //TODO 验证是否有权限操作
////            String menus = RedisUtil.hGet(AdminConfig.backstageRolesKey, roleId).toString();
////            if (menus.equals("") || !Arrays.asList(menus.split(",")).contains(auths)) {
////                AjaxResult result = AjaxResult.failed(HttpEnum.NO_PERMISSION.getCode(), HttpEnum.NO_PERMISSION.getMsg());
////                response.getWriter().print(JSON.toJSONString(result));
////                return false;
////            }
//        }
//
//        // 令牌剩余30分钟自动续签
//        RedisUtil.renewalToken(token);
//
//        // 写入本地线程
//        LikeAdminThreadLocal.put("userId", user.getId());
//
//        // 免权限验证接口
//        List<String> notAuthUri = Arrays.asList(AdminConfig.notAuthUri);
//        if (notAuthUri.contains(auths)) {
//            return HandlerInterceptor.super.preHandle(request, response, handler);
//        }
//
//        // 验证通过继续操作
//        return HandlerInterceptor.super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LikeAdminThreadLocal.remove();
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
