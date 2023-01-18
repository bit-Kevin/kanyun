package com.kanyun.kanyun_management.userLogin.LoginDao;


import com.kanyun.kanyun_management.Model.KyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ILoginDao {

    @Select("select * from ky_users where user_name = #{userName} and user_password =#{userPassword}")
    KyUser VerifyUser(KyUser user);
}
