package com.kanyun.kanyun_management.userManage.userManageAction;

import com.kanyun.kanyun_management.Model.KyUser;
import com.kanyun.kanyun_management.userManage.userManageService.userManageServiceImp.UserManageServiceImp;
import com.kanyun.kanyun_management.util.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin//用于解决前端访问跨域的问题
@RestController
@RequestMapping("/userManage")
public class UserManageActive {
    @Autowired
    UserManageServiceImp userManageService;

    @RequestMapping("queryAllUser")
    public String queryAllUser(PageModel<KyUser> pageModel) {
        return userManageService.queryAllUser(pageModel);
    }

    @RequestMapping("/likeQuery")
    public String queryByLikeWord(PageModel<KyUser> pageModel, String likeWord, Boolean queryState, Boolean flag) {
        if (queryState == null) {
            //只进行关键词查询
            return userManageService.queryByLikeWord(pageModel, likeWord);
        } else {
            if (flag == null) {
                //权限关键词查询
                return userManageService.queryUserByPostAndLikeWord(pageModel, queryState, likeWord);
            } else {
                //评论权限查询
                return userManageService.queryUserByCommentAndLikeWord(pageModel, queryState, likeWord);
            }
        }
    }


    @RequestMapping("/nameIsExist/{name}")
    public String CheckNameIsExist(@PathVariable("name") String name) {
        return userManageService.CheckNameIsExist(name);
    }

    @RequestMapping("/addUser")
    public String addUser(KyUser user) {
        return userManageService.addUser(user);
    }

    @RequestMapping("/setUserState")
    public String setUserState(Integer change, String userId) {
        return userManageService.setState(change, userId);
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(String userId) {
        return userManageService.deleteUserById(userId);
    }

    @RequestMapping("queryByState")
    public String queryUserByState(Boolean queryState, PageModel<KyUser> pageModel) {
        if (queryState != null && pageModel != null) {
            return userManageService.queryUserByPostAndLikeWord(pageModel, queryState, null);
        }
        return null;
    }
    @RequestMapping("/queryByComment")
    public String queryUserByComment(Boolean queryState, PageModel<KyUser> pageModel) {
        System.out.println("前端提交的数据" + queryState + "--" + pageModel);
        if (queryState != null && pageModel != null) {
            return userManageService.queryUserByCommentAndLikeWord(pageModel, queryState, null);
        }
        return null;
    }
}
