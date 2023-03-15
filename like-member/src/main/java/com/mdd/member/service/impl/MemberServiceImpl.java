package com.mdd.member.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mdd.common.config.RedisConfig;
import com.mdd.common.constant.MemberConstant;
import com.mdd.common.core.AjaxResult;
import com.mdd.common.entity.user.User;
import com.mdd.common.entity.user.UserAuth;
import com.mdd.common.enums.HttpEnum;
import com.mdd.common.exception.LoginException;
import com.mdd.common.exception.OperateException;
import com.mdd.common.utils.*;
import com.mdd.common.validate.member.LoginParam;
import com.mdd.common.vo.MemberVo;
import com.mdd.member.LikeMemberThreadLocal;
import com.mdd.member.entity.MemberLevel;
import com.mdd.member.service.IMemberLevelService;
import com.mdd.member.service.IMemberService;
import com.mdd.common.validate.PageParam;
import com.mdd.member.validate.MemberParam;
import com.mdd.common.validate.member.RegParam;
import com.mdd.member.vo.MemberListVo;
import com.mdd.member.vo.MemberDetailVo;
import com.mdd.common.core.PageResult;
import com.mdd.member.entity.Member;
import com.mdd.member.mapper.MemberMapper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 会员实现类
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,Member> implements IMemberService {
        
    @Resource
    MemberMapper memberMapper;
    @Autowired
    IMemberLevelService iMemberLevelService;

    /**
     * 会员列表
     *
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<MemberListVo>
     */
    @Override
    public PageResult<MemberListVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        memberMapper.setSearch(queryWrapper, params, new String[]{
            "=:levelId@level_id:long",
            "like:username:str",
            "=:password:str",
            "like:nickname:str",
            "like:mobile:str",
            "=:email:str",
            "=:header:str",
            "=:gender:int",
            "=:birth:str",
            "=:city:str",
            "=:job:str",
            "=:sign:str",
            "=:sourceType@source_type:int",
            "=:integration:int",
            "=:growth:int",
            "=:status:int",
        });

        IPage<Member> iPage = memberMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<MemberListVo> list = new LinkedList<>();
        for(Member item : iPage.getRecords()) {
            MemberListVo vo = new MemberListVo();
            BeanUtils.copyProperties(item, vo);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vo.setCreateTime(simpleDateFormat.format(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 会员详情
     *
     * @return Member
     */
    @Override
    public MemberVo detail() {
        final Long userId = LikeMemberThreadLocal.getUserId();
        Member model = memberMapper.selectOne(
                new QueryWrapper<Member>()
                    .eq("id", userId)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberVo vo = new MemberVo();
        BeanUtils.copyProperties(model, vo);
        vo.setAvatar(UrlUtil.toAbsoluteUrl(model.getAvatar()));
        //TODO 距离升级还差
        vo.setNextLevelTips("距离升级还差2000");
        //TODO 未读消息
        vo.setNoticeNum(1);
        //TODO 余额
        vo.setMoney(10);
        //TODO 优惠券数量
        vo.setCoupon(1);
        //TODO 待付款
        vo.setWaitPay(1);
        //TODO 待发货
        vo.setWaitDelivery(1);
        //TODO 待收货
        vo.setWaitTake(1);
        //TODO 商品评价
        vo.setWaitComment(1);
        //TODO 退款/售后
        vo.setAfterSale(1);
        //等级名称
        final MemberLevel byId = iMemberLevelService.getById(vo.getLevelId());
        vo.setLevelName(byId.getName());
        return vo;
    }

    /**
     * 会员详情
     *
     * @return Member
     */
    @Override
    public MemberVo detail(Long id) {
        Member model = memberMapper.selectOne(
                new QueryWrapper<Member>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "数据不存在");

        MemberVo vo = new MemberVo();
        BeanUtils.copyProperties(model, vo);
        final MemberLevel byId = iMemberLevelService.getById(vo.getLevelId());
        vo.setLevelName(byId.getName());
        return vo;
    }

    /**
     * 会员新增
     *
     * @param memberParam 参数
     */
    @Override
    public void add(MemberParam memberParam) {
        Member model = new Member();
        model.setLevelId(memberParam.getLevelId());
        model.setUsername(memberParam.getUsername());
        model.setPassword(memberParam.getPassword());
        model.setNickname(memberParam.getNickname());
        model.setMobile(memberParam.getMobile());
        model.setEmail(memberParam.getEmail());
        model.setAvatar(memberParam.getAvatar());
        model.setGender(memberParam.getGender());
        model.setBirth(memberParam.getBirth());
        model.setCity(memberParam.getCity());
        model.setJob(memberParam.getJob());
        model.setSign(memberParam.getSign());
        model.setSourceType(memberParam.getSourceType());
        model.setIntegration(memberParam.getIntegration());
        model.setGrowth(memberParam.getGrowth());
        model.setStatus(memberParam.getStatus());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        memberMapper.insert(model);
    }

    /**
     * 会员编辑
     *
     * @param memberParam 参数
     */
    @Override
    public void edit(MemberParam memberParam) {
        Member model = memberMapper.selectOne(
                new QueryWrapper<Member>()
                    .eq("id",  memberParam.getId())
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        model.setId(memberParam.getId());
        model.setLevelId(memberParam.getLevelId());
        model.setUsername(memberParam.getUsername());
        model.setPassword(memberParam.getPassword());
        model.setNickname(memberParam.getNickname());
        model.setMobile(memberParam.getMobile());
        model.setEmail(memberParam.getEmail());
        model.setAvatar(memberParam.getAvatar());
        model.setGender(memberParam.getGender());
        model.setBirth(memberParam.getBirth());
        model.setCity(memberParam.getCity());
        model.setJob(memberParam.getJob());
        model.setSign(memberParam.getSign());
        model.setSourceType(memberParam.getSourceType());
        model.setIntegration(memberParam.getIntegration());
        model.setGrowth(memberParam.getGrowth());
        model.setStatus(memberParam.getStatus());
        memberMapper.updateById(model);
    }

    /**
     * 会员删除
     *
     * @param id 主键ID
     */
    @Override
    public void del(Long id) {
        Member model = memberMapper.selectOne(
                new QueryWrapper<Member>()
                    .eq("id", id)
                    .last("limit 1"));

        Assert.notNull(model, "数据不存在!");

        memberMapper.delete(new QueryWrapper<Member>().eq("id", id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegParam regParam) {
        Member member = new Member();
        //生成唯一会员编码
        member.setSn(RandomUtil.randomStringWithoutStr(8,"0123456789"));

        //设置默认等级
        MemberLevel levelEntity = iMemberLevelService.getDefaultLevel();
        member.setLevelId(levelEntity.getId());

        //设置其它的默认信息
        //检查用户名和手机号是否唯一。感知异常，异常机制
        //TODO 检查手机唯一
        //checkPhoneUnique(regParam.getPhone());
        checkUserNameUnique(regParam.getUsername());

        member.setNickname(regParam.getUsername());
        member.setUsername(regParam.getUsername());

        String salt = ToolsUtil.randomString(5);
        String pwd  = ToolsUtil.makeMd5(regParam.getPassword()+salt);
        member.setPassword(pwd);
        member.setSalt(salt);

        member.setAvatar("/api/static/default_avatar.png");
        member.setSourceType(regParam.getClient());
        //member.setMobile(regParam.getPhone());
        member.setGender(0);
        member.setStatus(MemberConstant.MemberStateEnum.MEMBER_STATE_TYPE_ENABLE.getCode());
        member.setCreateTime(new Date());
        member.setUpdateTime(new Date());
        //保存数据
        this.baseMapper.insert(member);
    }

    @Override
    public void checkPhoneUnique(String phone) throws LoginException {
        Integer phoneCount = this.baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", phone));
        if (phoneCount > 0) {
            throw new LoginException(HttpEnum.SMAE_MOBILE.getCode(), HttpEnum.SMAE_MOBILE.getMsg());
        }
    }

    @Override
    public void checkUserNameUnique(String userName) throws LoginException {
        Integer usernameCount = this.baseMapper.selectCount(new QueryWrapper<Member>().eq("username", userName));
        if (usernameCount > 0) {
            throw new LoginException(HttpEnum.SAME_USERNAME.getCode(), HttpEnum.SAME_USERNAME.getMsg());
        }
    }

    @Override
    public MemberVo login(LoginParam loginParam) {
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(loginParam.getScene(), "scene参数缺失!");
        switch (loginParam.getScene()) {
            case "mnp":
                //map = mnpLogin(loginParam);
                break;
            case "mobile":
                //map = mobileLogin(params);
                break;
            case "account":
                return accountLogin(loginParam);
        }
        throw new LoginException(HttpEnum.LOGIN_ERROR.getCode(), HttpEnum.LOGIN_ERROR.getMsg());
    }

    @Override
    public MemberDetailVo center(Long userId) {
        final Member byId = this.getById(userId);
        MemberDetailVo memberDetailVo = new MemberDetailVo();
        BeanUtils.copyProperties(byId,memberDetailVo);
        return memberDetailVo;
    }


    /**
     * 账号登录
     *
     * @author Claire
     * @param loginParam 参数
     * @return Map<String, Object>
     */
    public MemberVo accountLogin(LoginParam loginParam) {
        if((loginParam.getUsername() == null || loginParam.getUsername().length() == 0)) {
            throw new LoginException(HttpEnum.ACCOUNT_MOBILE_EMPTY.getCode(), HttpEnum.ACCOUNT_MOBILE_EMPTY.getMsg());
        }

        Member member = this.baseMapper.selectOne(new QueryWrapper<Member>().eq("username", loginParam.getUsername()).or()
                .eq("mobile",loginParam.getMobile()));

        if(member == null) {
            throw new LoginException(HttpEnum.LOGIN_ACCOUNT_NOT_EXIST.getCode(), HttpEnum.LOGIN_ACCOUNT_NOT_EXIST.getMsg());
        }
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(member, "账号不存在!");

        String pwd = ToolsUtil.makeMd5(loginParam.getPassword() + member.getSalt());
        com.baomidou.mybatisplus.core.toolkit.Assert.isFalse(!pwd.equals(member.getPassword()), "账号或密码错误!");
        com.baomidou.mybatisplus.core.toolkit.Assert.isFalse(member.getStatus() != MemberConstant.MemberStateEnum.MEMBER_STATE_TYPE_ENABLE.getCode(), "账号已被禁用!");

        //TODO 更新登录信息
//        member.setLastLoginIp(IpUtil.getHostIp());
//        member.setLastLoginTime(System.currentTimeMillis() / 1000);
//        userMapper.updateById(user);

        MemberVo memberVo = new MemberVo();
        BeanUtils.copyProperties(member,memberVo);
        memberVo.setEmail("");
        memberVo.setMobile("");
        return memberVo;
    }

    /**
     * 编辑信息
     *
     * @author Claire
     * @param params 参数
     * @param userId 用户ID
     */
    @Override
    public void edit(Map<String, String> params, Long userId) {
        String field = params.getOrDefault("field", "").trim();
        String value =  params.getOrDefault("value", "").trim();

        switch (field) {
            case "avatar":
                Member avatarMember = new Member();
                avatarMember.setId(userId);
                avatarMember.setAvatar(UrlUtil.toRelativeUrl(value));
                avatarMember.setUpdateTime(new Date());
                this.updateById(avatarMember);
                break;
            case "username":
                Member usernameMember = memberMapper.selectOne(new QueryWrapper<Member>()
                        .select("id,username")
                        .eq("username", value)
                        .eq("is_delete", 0)
                        .last("limit 1"));

                if (StringUtil.isNotNull(usernameMember) && !usernameMember.getId().equals(userId)) {
                    throw new OperateException("账号已被使用!");
                }

                if (StringUtil.isNotNull(usernameMember) && usernameMember.getUsername().equals(value)) {
                    throw new OperateException("新账号与旧账号一致,修改失败!");
                }

                Member member = new Member();
                member.setId(userId);
                member.setUsername(value);
                member.setUpdateTime(new Date());
                memberMapper.updateById(member);
                break;
            case "nickname":
                Member nicknameMember = new Member();
                nicknameMember.setId(userId);
                nicknameMember.setNickname(value);
                nicknameMember.setUpdateTime(new Date());
                memberMapper.updateById(nicknameMember);
                break;
            case "sex":
                Member sexMember = new Member();
                sexMember.setId(userId);
                sexMember.setGender(Integer.parseInt(value));
                sexMember.setUpdateTime(new Date());
                memberMapper.updateById(sexMember);
                break;
            default:
                throw new OperateException("不被支持的类型");
        }
    }

    /**
     * 微信小程序登录
     *
     * @author fzr
     * @param loginParam 参数
     * @return Map<String, Object>
     */
    @Transactional
    public Map<String, Object> mnpLogin(LoginParam loginParam) {
//        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(loginParam.getCode(), "code参数缺失!");
//        String code      = loginParam.getCode();
//        String avatarUrl = params.getOrDefault("avatarUrl", "/api/static/default_avatar.png");
//        String nickName  = params.getOrDefault("nickName", "微信用户");
//        String gender    = params.getOrDefault("gender", "0");
//        Integer client   = Integer.parseInt(params.getOrDefault("client", "1"));
//
//        try {
//            WxMaService wxMaService = WeChatUtil.mnp();
//            WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
//            String openId = sessionResult.getOpenid();
//            String uniId = sessionResult.getUnionid();
//            String unionId = uniId == null ? "0" : uniId;
//
//            UserAuth userAuth = userAuthMapper.selectOne(new QueryWrapper<UserAuth>()
//                    .nested(wq->wq
//                            .eq("openid", openId).or()
//                            .eq("unionid", unionId)
//                    ).last("limit 1"));
//
//            User user = null;
//            Integer userId;
//            if (StringUtil.isNotNull(userAuth)) {
//                user = userMapper.selectOne(new QueryWrapper<User>()
//                        .eq("id", userAuth.getUserId())
//                        .eq("is_delete", 0)
//                        .last("limit 1"));
//            }
//
//            if (StringUtil.isNull(user)) {
//                Integer sn  = this.randMakeSn();
//                User model = new User();
//                model.setSn(sn);
//                model.setAvatar(avatarUrl);
//                model.setNickname(nickName.equals("") ? "用户"+sn : nickName);
//                model.setUsername("u"+sn);
//                model.setSex(Integer.parseInt(gender));
//                model.setChannel(client);
//                model.setLastLoginIp(IpUtil.getHostIp());
//                model.setLastLoginTime(System.currentTimeMillis() / 1000);
//                model.setCreateTime(System.currentTimeMillis() / 1000);
//                model.setUpdateTime(System.currentTimeMillis() / 1000);
//                userMapper.insert(model);
//                user = model;
//                userId = model.getId();
//
//                if (StringUtil.isNull(userAuth)) {
//                    UserAuth auth = new UserAuth();
//                    auth.setUserId(model.getId());
//                    auth.setOpenid(openId);
//                    auth.setUnionid(unionId.equals("0") ? "" : unionId);
//                    auth.setClient(client);
//                    auth.setCreateTime(System.currentTimeMillis() / 1000);
//                    auth.setUpdateTime(System.currentTimeMillis() / 1000);
//                    userAuthMapper.insert(auth);
//                }
//            } else {
//                // 更新微信标识
//                userId = user.getId();
//                if (StringUtil.isEmpty(userAuth.getUnionid()) && StringUtil.isNotEmpty(sessionResult.getUnionid())) {
//                    userAuth.setUnionid(sessionResult.getUnionid());
//                    userAuthMapper.updateById(userAuth);
//                }
//
//                // 更新用户信息
//                if (StringUtil.isEmpty(user.getAvatar()) && StringUtil.isNotEmpty(avatarUrl)) {
//                    user.setAvatar(avatarUrl);
//                    user.setNickname(nickName);
//                    user.setSex(Integer.parseInt(gender));
//                }
//
//                // 更新登录信息
//                user.setLastLoginIp(IpUtil.getHostIp());
//                user.setLastLoginTime(System.currentTimeMillis() / 1000);
//                userMapper.updateById(user);
//            }
//
//            String token = ToolsUtil.makeToken();
//            RedisUtil.set(FrontConfig.frontendTokenKey+token, userId, 7200);
//
//            String mobile = StringUtil.isNull(user.getMobile()) ? "" : user.getMobile();
//
//            Map<String, Object> response = new LinkedHashMap<>();
//            response.put("id", userId);
//            response.put("isBindMobile", !mobile.equals(""));
//            response.put("token", token);
//            return response;
//        } catch (WxErrorException e) {
//            throw new OperateException(e.getError().getErrorCode() + ", " + e.getError().getErrorMsg());
//        }
        return null;
    }


}
