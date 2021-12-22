package com.szeastroc.middle.code.syn.service.impl;

import com.alibaba.fastjson.JSON;
import com.szeastroc.middle.code.syn.config.db.redis.RedisPrefix;
import com.szeastroc.middle.code.syn.constants.EastrocException;
import com.szeastroc.middle.code.syn.dto.LoginDTO;
import com.szeastroc.middle.code.syn.mapper.UserInfoMapper;
import com.szeastroc.middle.code.syn.model.Userinfo;
import com.szeastroc.middle.code.syn.service.LoginService;
import com.szeastroc.middle.code.syn.utils.ShiroSaltUtils;
import com.szeastroc.middle.code.syn.utils.ValidationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 产线实时导码登陆
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HttpServletRequest request;


    @Override
    public String login(LoginDTO loginDTO) {

        ValidationUtils.validate(loginDTO);

        Userinfo userinfo = userInfoMapper.findByAccount(loginDTO.getUserName().trim());
        if (userinfo == null) {
            throw new EastrocException("用户或密码错误");
        }

        String password = ShiroSaltUtils.generatePassword(loginDTO.getPassWord().trim(), userinfo.getSalt());
        if (!StringUtils.equals(password, userinfo.getPassWord())) {
            throw new EastrocException("用户或密码错误");
        }

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.boundValueOps(RedisPrefix.CURRENT_USER + token).set(JSON.toJSONString(userinfo),getSecondsNextEarlyMorning(), TimeUnit.SECONDS);

        userinfo.setLastLoginDate(new Date());
        userInfoMapper.update(userinfo);
        return token;
    }

    /**
     * 判断当前时间距离第二天凌晨的秒数
     *
     * @return 返回值单位为[s:秒]
     */
    public Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 验证用户token
     * @return 用户信息
     */
    @Override
    public Userinfo verifyToken(){
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            throw new EastrocException("invalid token");
        }
        String  userinfoStr = redisTemplate.opsForValue().get(RedisPrefix.CURRENT_USER+token);
        if (StringUtils.isEmpty(userinfoStr)) {
            throw new EastrocException("invalid token");
        }
        return JSON.parseObject(userinfoStr, Userinfo.class);
    }
}

