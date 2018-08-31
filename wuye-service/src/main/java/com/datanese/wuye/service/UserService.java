package com.datanese.wuye.service;

import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.mapper.CommunityMapper;
import com.datanese.wuye.mapper.UserMapper;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.po.WeixinAccountPO;
import com.datanese.wuye.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bing.a.qian on 9/8/2017.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserPO getUser(Long id) {
        UserPO user=userMapper.getOne(id);
        return user;
    }

    public long createOrUpdate(WxMaUserInfo user) {
        //check if user exists
        long userId;
        String weixinId = user.getOpenId();
        UserPO userPO = userMapper.getUserByWeixinId(weixinId);
        if (userPO == null) {
            userId=subscribe(user);
        }else{
            update(user,userPO.getId());
            userId=userPO.getId();
        }
        return userId;
    }
    public void update(WxMaUserInfo user,long userId){
        WeixinAccountPO weixinAccountPO=new WeixinAccountPO();
        {
            weixinAccountPO.setUserId(userId);
            weixinAccountPO.setOpenid(user.getOpenId());
            weixinAccountPO.setNickname(user.getNickName());
            weixinAccountPO.setCity(user.getCity());
            weixinAccountPO.setProvince(user.getProvince());
            weixinAccountPO.setAvatarUrl(user.getAvatarUrl());
            weixinAccountPO.setCountry(user.getCountry());
            weixinAccountPO.setGender(user.getGender());
        }
        userMapper.updateWeixinAccount(weixinAccountPO);
        UserPO userPO=new UserPO();
        {
            userPO.setId(userId);
            userPO.setName(user.getNickName());
            userPO.setAvatarUrl(user.getAvatarUrl());
            userPO.setGender(user.getGender());
            userPO.setWeixinId(user.getOpenId());
        }
        userMapper.updateUser(userPO);
        return ;
    }


    public long subscribe(WxMaUserInfo user) {
            //create new account and user
            Long userId=SnowflakeIdWorker.nextId();
            WeixinAccountPO weixinAccountPO=new WeixinAccountPO();
            {
                weixinAccountPO.setUserId(userId);
                weixinAccountPO.setOpenid(user.getOpenId());
                weixinAccountPO.setNickname(user.getNickName());
                weixinAccountPO.setCity(user.getCity());
                weixinAccountPO.setProvince(user.getProvince());
                weixinAccountPO.setAvatarUrl(user.getAvatarUrl());
                weixinAccountPO.setCountry(user.getCountry());
                weixinAccountPO.setGender(user.getGender());

            }
            UserPO userPO=new UserPO();
            {
                userPO.setId(userId);
                userPO.setName(user.getNickName());
                userPO.setAvatarUrl(user.getAvatarUrl());
                userPO.setGender(user.getGender());
                userPO.setWeixinId(user.getOpenId());
            }
            userMapper.insertWeixinAccount(weixinAccountPO);
            userMapper.insertUser(userPO);
            return userId;
    }

    public CommunityDTO getUserDefaultCommunity(long userId) {
        return userMapper.getUserDefaultCommunity(userId);
    }
    public void setUserDefaultCommunity(long userId, int communityId) {
        userMapper.setUserDefaultCommunity(userId,communityId);
    }
}
