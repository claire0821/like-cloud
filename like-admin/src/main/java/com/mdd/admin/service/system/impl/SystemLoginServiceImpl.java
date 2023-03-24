package com.mdd.admin.service.system.impl;

import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.system.*;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.constant.OrderConstant;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemLogLogin;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemLogLoginMapper;
import com.mdd.common.utils.*;
import com.mdd.common.validate.user.LoginParam;
import com.mdd.common.vo.UserVo;
import nl.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统登录服务实现类
 */
@Service
public class SystemLoginServiceImpl implements ISystemLoginService {

    @Resource
    SystemLogLoginMapper systemLogLoginMapper;

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    @Resource
    ISystemAuthAdminService iSystemAuthAdminService;

    @Autowired
    ISystemAuthRoleService iSystemAuthRoleService;
    @Autowired
    ISystemAuthMenuService iSystemAuthMenuService;

    private static final Logger log = LoggerFactory.getLogger(SystemLoginServiceImpl.class);

    /**
     * 登录
     *
     * @author fzr
     * @param loginParam 登录参数
     * @return token
     */
    @Override
    public UserVo login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();

        SystemAuthAdmin sysAdmin = iSystemAuthAdminService.findByUsername(username);
        if (sysAdmin == null || sysAdmin.getIsDelete() == 1) {
            this.recordLoginLog(0L, loginParam.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        if (sysAdmin.getIsDisable() == 1) {
            this.recordLoginLog(sysAdmin.getId(), loginParam.getUsername(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_DISABLE_ERROR.getCode(), HttpEnum.LOGIN_DISABLE_ERROR.getMsg());
        }

        String newPWd = password + sysAdmin.getSalt();
        String md5Pwd = ToolsUtil.makeMd5(newPWd);
        if (!md5Pwd.equals(sysAdmin.getPassword())) {
            this.recordLoginLog(sysAdmin.getId(), loginParam.getUsername(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_ERROR.getCode(), HttpEnum.LOGIN_ACCOUNT_ERROR.getMsg());
        }

        try {
            // 非多处登录
//            String token = ToolsUtil.makeToken();
//            if (sysAdmin.getIsMultipoint() == 0) {
//                Set<Object> ts = RedisUtil.sGet(AdminConfig.backstageTokenSet + sysAdmin.getId());
//                for (Object t: ts) {
//                    RedisUtil.del(AdminConfig.backstageTokenKey+t.toString());
//                }
//                RedisUtil.del(AdminConfig.backstageTokenSet + sysAdmin.getId());
//                RedisUtil.sSet(AdminConfig.backstageTokenSet + sysAdmin.getId(), token);
//            }

            // 缓存登录信息
//            RedisUtil.set(AdminConfig.backstageTokenKey+token, sysAdmin.getId(), 7200);
//            iSystemAuthAdminService.cacheAdminUserByUid(sysAdmin.getId());

            // 返回登录信息
//            Map<String, Object> response = new LinkedHashMap<>();
//            response.put("token", token);

            // 更新登录信息
            sysAdmin.setLastLoginIp(IpUtil.getIpAddress());
            sysAdmin.setLastLoginTime(System.currentTimeMillis() / 1000);
            systemAuthAdminMapper.updateById(sysAdmin);

            // 记录登录日志
            this.recordLoginLog(sysAdmin.getId(), loginParam.getUsername(), "");
            //返回登录用户信息
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(sysAdmin,userVo);
            userVo.setType(OrderConstant.OperateManTypeEnum.ADMINISTRATORS);

            //TODO 多角色
            if(sysAdmin.getUsername().equals(GlobalConfig.SuperAdministrator)) {
                Set<String> roles = new TreeSet<>();
                roles.add(GlobalConfig.SuperAdministrator);
                userVo.setRoles(roles);
                userVo.setRole(0);
                //将角色权限存储到redis
                final ArrayList<String> objects = new ArrayList<>();
                objects.add("*");
                RedisUtil.setRolePermission(GlobalConfig.SuperAdministrator,objects);
            } else {
                final SystemAuthRoleVo detail = iSystemAuthRoleService.detail(sysAdmin.getRole());
                Set<String> roles = new TreeSet<>();
                roles.add(detail.getName());
                userVo.setRoles(roles);
                userVo.setRole(detail.getId());

                //将角色权限存储到redis
                final List<String> stringList = iSystemAuthMenuService.selectMenuPermsByRoleId(detail.getId());
                RedisUtil.setRolePermission(detail.getName(),stringList);
            }

            return userVo;
        } catch (Exception e) {
            Long adminId = StringUtil.isNotNull(sysAdmin.getId()) ? sysAdmin.getId() : 0;
            String error = StringUtil.isEmpty(e.getMessage()) ? "未知错误" : e.getMessage();
            this.recordLoginLog(adminId, loginParam.getUsername(), error);
            throw new OperateException(e.getMessage());
        }
    }

    /**
     * 退出
     *
     * @author fzr
     * @param token 令牌
     */
    @Override
    public void logout(String token) {
        RedisUtil.del(AdminConfig.backstageTokenKey + token);
    }

    /**
     * 记录登录日志
     */
    private void recordLoginLog(Long adminId, String username, String error) {
        try {
            HttpServletRequest request = Objects.requireNonNull(RequestUtil.handler());
            final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

            SystemLogLogin model = new SystemLogLogin();
            model.setAdminId(adminId);
            model.setUsername(username);
            model.setIp(IpUtil.getIpAddress());
            model.setOs(userAgent.getOperatingSystem().getName());
            model.setBrowser(userAgent.getBrowser().getName());
            model.setStatus(StringUtil.isEmpty(error) ? 1 : 0);
            model.setCreateTime(System.currentTimeMillis() / 1000);
            systemLogLoginMapper.insert(model);
        } catch (Exception e) {
            log.error("记录登录日志异常 {}" + e.getMessage());
        }
    }

}
