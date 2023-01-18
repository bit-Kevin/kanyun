package com.kanyun.kanyun_management.userManage.userManageService;

import com.kanyun.kanyun_management.Model.KyUser;
import com.kanyun.kanyun_management.util.PageModel;

public interface IUserManageService {
    //查询并返回所有用户JSON数据
    String queryAllUser(PageModel<KyUser> pageModel);

    /**
     * 按关键字查询并返回JSON对象
     * @param pageModel 分页信息
     * @param likeWord 关键字
     * @return pageModel的JSON对象
     */
    String queryByLikeWord(PageModel<KyUser> pageModel, String likeWord);

    /**
     * 验证用户名是否存在
     * @param name 需要验证的名字
     * @return resultMessage json对象
     */
    String CheckNameIsExist(String name);

    /**
     * 添加用户
     * @param user 前端传入的数据
     * @return resultMessage json对象
     */
    String addUser(KyUser user);

    /**
     * 修改用户权限状态
     * @param
     * @return resultMessage json对象
     */
    String setState(Integer change, String userId);

    /**
     * 逻辑删除用户
     * @param userId 用户id
     * @return resultMessage json对象
     */
    String deleteUserById(String userId);

    /**
     * 通过用户发布权限来查询用户
     * @param pageModel 分页工具
     * @param state true是查询已限制的
     * @param likeWord 模糊查询关键字，可以为空
     * @return pageModel的JSON对象
     */
    String queryUserByPostAndLikeWord(PageModel<KyUser> pageModel, Boolean state, String likeWord);

    /**
     * 通过用户评论权限和关键字查询用户
     * @param pageModel 分页工具
     * @param state true是查询已限制的
     * @param likeWord 模糊查询关键字，可以为空
     * @return pageModel的JSON对象
     */
    String queryUserByCommentAndLikeWord(PageModel<KyUser> pageModel, Boolean state, String likeWord);
}
