package com.kanyun.kanyun_management.userManage.userManageDao;

import com.kanyun.kanyun_management.Model.KyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface IUserManageDao {
    //查询所有用户
    @Select("select * from ky_users where `delete`=0 ")
    ArrayList<KyUser> queryAllUser();

    //#{}会先替换为？，然后加数据引号替换问号 防止不加引号导致的sql注入问题
    @Select("select * from ky_users where user_name  like #{likeWord} and `delete`=0")
    ArrayList<KyUser> queryByLikeWord(String likeWord);

    //判断用户名是否存在
    @Select("select * from ky_users where user_name =#{name}")
    KyUser CheckNameIsExist(String name);

    //按用户id查询
    @Select("select * from ky_users where user_id =#{userId} and`delete`=0")
    KyUser queryUserById(String userId);

    //获取没有发布权限的用户
    @Select("SELECT * FROM ky_users WHERE (user_state = 2 OR user_state = 3 OR user_state = 6 OR user_state = 7) AND `delete`=0 and user_name like #{likeWord} ")
    ArrayList<KyUser> queryNoRights(String likeWord);

    //获取有发布权限的用户
    @Select("SELECT * FROM ky_users WHERE user_state != 2 AND user_state != 3 AND user_state != 6 AND user_state != 7 AND `delete`=0 and user_name like #{likeWord}")
    ArrayList<KyUser> queryRights(String likeWord);

    //获取没有评论权限的用户
    @Select("SELECT * FROM ky_users WHERE (user_state = 4 OR user_state = 5 OR user_state = 6 OR user_state = 7) AND `delete`=0 and user_name like #{likeWord} ")
    ArrayList<KyUser> queryNoCommentRights(String likeWord);

    //获取有发布评论权限的用户
    @Select("SELECT * FROM ky_users WHERE user_state != 4 AND user_state != 5 AND user_state != 6 AND user_state != 7 AND `delete`=0 and user_name like #{likeWord}")
    ArrayList<KyUser> queryCommentRights(String likeWord);

    //添加用户
    @Insert("insert into ky_users(user_id,user_name,user_password,user_email," +
            "user_registration_time,user_role,user_telephone_number,user_nickname) " +
            "value (null,#{userName},#{userPassword},#{userEmail},#{userRegistrationTime}," +
            "#{userRole},#{userTelephoneNumber},#{userNickname})")
    Integer addUser(KyUser user);

    //更新用户状态
    @Update("update ky_users set user_state=#{userState},version=version+1 where user_id=#{userId} and version=#{version}")
    Integer setUserState(KyUser user);

    //通过id逻辑删除用户
    @Update("update ky_users set `delete`=1 ,version=version+1 where user_id=#{userId} and version=#{version}")
    Integer deleteUserById(KyUser user);




}
