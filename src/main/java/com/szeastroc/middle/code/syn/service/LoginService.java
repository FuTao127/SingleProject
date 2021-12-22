package com.szeastroc.middle.code.syn.service;

import com.szeastroc.middle.code.syn.dto.LoginDTO;
import com.szeastroc.middle.code.syn.model.Userinfo;


public interface LoginService {
    /**
     * 用户登录
     * @param loginDTO
     * @return
     */
    String login(LoginDTO loginDTO);

    Userinfo verifyToken();
}
