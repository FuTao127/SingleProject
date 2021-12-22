package com.szeastroc.middle.code.syn.mapper;

import com.szeastroc.middle.code.syn.model.Userinfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description:用户信息mapper
 * @Author: futao
 * @Date: 2021/12/14
 **/
@Repository
public interface UserInfoMapper {

    @Select("SELECT  *  from  t_sys_userinfo  where status = 1 and user_name = #{userName}")
    Userinfo findByAccount(@Param("userName") String userName);


    int update(Userinfo userinfo);

    Userinfo findById(@Param("userId") String userId);
}
