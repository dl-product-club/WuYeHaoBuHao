package com.datanese.wuye.service;

import com.datanese.wuye.dto.WeixinAccountDTO;
import com.datanese.wuye.po.AccountPO;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.mapper.UserMapper;
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


    public void update(UserPO user, Long id) {
        userMapper.update(user);
    }

    public void delete(Long id) {
        userMapper.delete(id);
    }

    public void subscribe(WeixinAccountDTO user) {
        //check if user exists
        String weixinId=user.getOpenid();
        AccountPO accountPO=userMapper.getUserAccountByWeixinId(weixinId);
        if(accountPO==null){
            //create new account and user
            Long newAccountId=SnowflakeIdWorker.nextId();
            accountPO=new AccountPO();
            {
                accountPO.setAccountId(newAccountId);
                accountPO.setWeixinId(weixinId);
            }
            UserPO userPO=new UserPO();
            {
                userPO.setAccountId(newAccountId);
                userPO.setAccountName(user.getNickname());
                userPO.setCity(user.getCity());
                userPO.setProvince(user.getProvince());
                userPO.setCountry(user.getCountry());
                if(user.getSex()==1){
                    userPO.setGender('M');
                }else if(user.getSex()==2){
                    userPO.setGender('F');
                }else{
                    userPO.setGender('U');
                }

            }
            userMapper.insertAccount(accountPO);
            userMapper.insertUser(userPO);
        }else{
            //update user
        }

    }
}
