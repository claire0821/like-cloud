package com.mdd.admin.config.aop;

import com.alibaba.fastjson.JSON;
import com.mdd.common.config.aop.Log;
import com.mdd.common.config.aop.RequestType;
import com.mdd.common.entity.system.SystemLogOperate;
import com.mdd.common.feign.AuthFeignService;
import com.mdd.common.mapper.system.SystemLogOperateMapper;
import com.mdd.common.utils.IpUtil;
import com.mdd.common.utils.RequestUtil;
import com.mdd.common.vo.UserVo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    @Resource
    SystemLogOperateMapper systemLogOperateMapper;
    @Autowired
    AuthFeignService authFeignService;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    private Long beginTime = 0L;

    /**
     * 声明切面点拦截那些类
     */
    @Pointcut("@annotation(com.mdd.common.config.aop.Log)")
    private void pointCutMethodController() {}

    /**
     * 环绕通知前后增强
     */
    @Around(value = "pointCutMethodController()")
    public Object doAroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        this.beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = joinPoint.proceed();
        // 保存日志
        recordLog(joinPoint, null);
        // 返回结果
        return result;
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        recordLog(joinPoint, e);
    }

    /**
     * 记录日志信息
     *
     * @param joinPointObj joinPoint
     * @param e Exception 错误异常
     */
    private void recordLog(Object joinPointObj, final Exception e) {
        try {
            long endTime = System.currentTimeMillis();
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (requestAttributes != null) {
                // 取得请求对象
                HttpServletRequest request = requestAttributes.getRequest();

                // 获取当前的用户
                UserVo userInfo = authFeignService.getUserInfo().getData();
                final Long adminId = userInfo.getId();

                // 获取日志注解
                ProceedingJoinPoint joinPoint = (ProceedingJoinPoint) joinPointObj;
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                Method method = signature.getMethod();
                Log logAnnotation = method.getAnnotation(Log.class);

                // 方法名称
                String className = joinPoint.getTarget().getClass().getName();
                String methodName = joinPoint.getSignature().getName();

                // 获取请求参数
                String queryString = request.getQueryString();
                Object[] args = joinPoint.getArgs();
                String params = "";
                if (args.length > 0) {
                    if("POST".equals(request.getMethod())){
                        if (RequestType.File.equals(logAnnotation.requestType())){
                            //文件类型获取文件名称作为参数
                            StandardMultipartHttpServletRequest standardMultipartHttpServletRequest = (StandardMultipartHttpServletRequest) args[0];
                            //提取文件名
                            params = standardMultipartHttpServletRequest
                                    .getMultiFileMap()
                                    .values()
                                    .stream()
                                    .map(m -> m.stream()
                                            .map(MultipartFile::getOriginalFilename)
                                            .collect(Collectors.joining(",")))
                                    .collect(Collectors.joining(","));
                        } else {
                            params = JSON.toJSONString(args);
                        }

                    } else if("GET".equals(request.getMethod())){
                        params = queryString;
                    }
                }

                // 错误信息
                String error = "";
                int status = 1;
                if (e != null) {
                    error = e.getMessage();
                    status = 2; // 1=成功, 2=失败
                }

                // 数据库日志
                SystemLogOperate model = new SystemLogOperate();
                model.setAdminId(adminId);
                model.setTitle(logAnnotation.title());
                model.setIp(IpUtil.getIpAddress());
                model.setType(request.getMethod());
                model.setMethod(className + "." + methodName + "()");
                model.setUrl(RequestUtil.route());
                model.setArgs(params);
                model.setError(error);
                model.setStatus(status);
                model.setStartTime(this.beginTime / 1000);
                model.setEndTime(endTime / 1000);
                model.setTaskTime(endTime - this.beginTime);
                model.setCreateTime(System.currentTimeMillis() / 1000);
                systemLogOperateMapper.insert(model);
            }
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
    }

}
