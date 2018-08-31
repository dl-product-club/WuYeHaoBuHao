package com.datanese.wuye.rest;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.alibaba.fastjson.JSON;
import com.datanese.wuye.Constant;
import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.dto.ResultDTO;
import com.datanese.wuye.dto.UserDTO;
import com.datanese.wuye.exception.SessionExpiredException;
import com.datanese.wuye.exception.UserNotExistException;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.service.UserService;

import com.datanese.wuye.session.SessionEntity;
import com.datanese.wuye.util.JsonUtils;
import com.datanese.wuye.util.SnowflakeIdWorker;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private static boolean MOCK_WECHAT = false;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登陆接口
     */
    @GetMapping("/login/{code}")
    public String login(@PathVariable("code") String code) {
        if (StringUtils.isBlank(code)) {
            return "empty jscode";
        }

        try {
            //获取openid, session_key
            WxMaJscode2SessionResult session = null;
            if (MOCK_WECHAT) {
                session = new WxMaJscode2SessionResult();
                session.setSessionKey("test_session_key_" + SnowflakeIdWorker.nextId());
                session.setOpenid("openID_" + SnowflakeIdWorker.nextId());
            } else {
                session = this.wxService.getUserService().getSessionInfo(code);
            }
            String sessionKey = session.getSessionKey();
            String openId = session.getOpenid();
            logger.info(session.getSessionKey());
            logger.info(session.getOpenid());

            //生成系统自有sessionId
            String sessionId = "sessionId" + SnowflakeIdWorker.nextId();
            SessionEntity se = new SessionEntity();
            se.setSessionKey(sessionKey);
            se.setOpenId(openId);
            redisTemplate.opsForValue().set(sessionId, se);
            se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
            return sessionId;
//            return JsonUtils.toJson(sessionId);
        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
            return e.toString();
        }
    }


    /**
     * <pre>
     * 创建或者更新用户信息
     * </pre>
     */
    @PostMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(@RequestHeader HttpHeaders headers, String signature, String rawData, String encryptedData, String iv) throws Exception {
        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }


        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }

        String sessionKey = se.getSessionKey();
        String openId = se.getOpenId();
        WxMaUserInfo userInfo = null;
        if (MOCK_WECHAT) {
            userInfo = WxMaUserInfo.fromJson(rawData);
        } else {
            // 用户信息校验
            if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                throw new SessionExpiredException();
            }
            // 解密用户信息
            userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        }
        //创建或者更新用户信息
        long userId = userService.createOrUpdate(userInfo);
        se = new SessionEntity();
        se.setUserId(userId);
        se.setSessionKey(sessionKey);
        se.setOpenId(openId);

        redisTemplate.opsForValue().set(sessionId, se);
        return userId+"";
    }


    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @GetMapping("/info")
    public String info(@RequestHeader HttpHeaders headers, String sessionKey, String signature, String rawData, String encryptedData, String iv) throws Exception {
        // 用户信息校验
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return "user check failed";
        }

        // 解密用户信息
        WxMaUserInfo userInfo = this.wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        return JsonUtils.toJson(userInfo);
    }


//	@GetMapping("/getUsers")
//	public List<UserPO> getUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
//        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
//		List<UserPO> users=userMapper.getAll();
//		return users;
//	}


    @GetMapping("/currentUser")
    public UserDTO getCurrentUser(@RequestHeader HttpHeaders headers) throws Exception{

        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }


        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }
        if (se.getUserId() <= 0) {
            throw new UserNotExistException();
        }

        UserDTO resultDTO = new UserDTO();
        UserPO user = userService.getUser(se.getUserId());
        if(user==null){
            throw new UserNotExistException();
        }
        UserDTO userDTO= JSON.parseObject(JSON.toJSONString(user),UserDTO.class);
        return userDTO;
    }

    @PutMapping("/setUserDefaultCommunity/{communityId}")
    public void setUserDefaultCommunity(@RequestHeader HttpHeaders headers, @PathVariable("communityId") int communityId) throws  Exception{
        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }
        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }
        if (se.getUserId() <= 0) {
            throw new UserNotExistException();
        }
        userService.setUserDefaultCommunity(se.getUserId(), communityId);
    }

    @GetMapping("/userDefaultCommunity/")
    public CommunityDTO getUserDefaultCommunity(@RequestHeader HttpHeaders headers) throws Exception{
        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }


        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }
        if (se.getUserId() <= 0) {
            throw new UserNotExistException();
        }

        //需要校验
        CommunityDTO dto = userService.getUserDefaultCommunity(se.getUserId());
        return dto;
    }


    @PostMapping("/clearMyself/")
    public ResultDTO clearMyself(@RequestHeader HttpHeaders headers) throws Exception{
        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }


        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }


        if (se.getUserId() > 0) {
            //清空数据库

        }
        //清空缓存
        redisTemplate.delete(sessionId);

        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setResult(Constant.RESPONSE_RESULT_OK);
        return resultDTO;
    }

}