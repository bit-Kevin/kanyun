package com.kanyun.kanyun_management.userManage.userManageService.userManageServiceImp;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kanyun.kanyun_management.Model.KyUser;
import com.kanyun.kanyun_management.userManage.userManageDao.IUserManageDao;
import com.kanyun.kanyun_management.userManage.userManageService.IUserManageService;
import com.kanyun.kanyun_management.util.PageModel;
import com.kanyun.kanyun_management.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserManageServiceImp implements IUserManageService {
    @Autowired
    IUserManageDao userManageDao;
    public String queryAllUser(PageModel<KyUser> pageModel) {
        //开启分页
         PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
         //返回结果是已经分页后的，但是其他数据需要交给page或者pageInfo处理后才有
        ArrayList<KyUser> results = userManageDao.queryAllUser();
        PageInfo<KyUser> pageInfo = new PageInfo<KyUser>(results);
        return pageDataPackaging(pageInfo,pageModel);
    }

    public String queryByLikeWord(PageModel<KyUser> pageModel, String likeWord) {
        //开启分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        //拼接关键字
        likeWord = "%"+likeWord+"%";
        //返回结果是已经分页后的，但是其他数据需要交给page或者pageInfo处理后才有
        ArrayList<KyUser> results = userManageDao.queryByLikeWord(likeWord);
        PageInfo<KyUser> pageInfo = new PageInfo<KyUser>(results);
        return pageDataPackaging(pageInfo,pageModel);
    }

    public String CheckNameIsExist(String name) {
        ResultMessage<KyUser> result = new ResultMessage<KyUser>();
        KyUser kyUser = userManageDao.CheckNameIsExist(name);
//        可用返回200
        if (kyUser==null)result.setState(200);
        else result.setState(400);
        return JSON.toJSONString(result);
    }

    public String addUser(KyUser user) {
        user.setUserRegistrationTime(new Timestamp(new Date().getTime()));
        ResultMessage<KyUser> msg = new ResultMessage<KyUser>();
        Integer result = userManageDao.addUser(user);
        if (result<=0) {
            msg.setMessage("数据库添加失败");
            msg.setState(400);
        }else {
            msg.setMessage("数据库添加成功");
            msg.setState(200);
        }
        return JSON.toJSONString(msg);

    }

    public String setState(Integer change ,String userId ) {
        ResultMessage<Object> resultMsg = new ResultMessage<Object>();
        //乐观锁机制
        int max =10 ;
        for (int i = 1; i <=max ; i++) {
            KyUser user = userManageDao.queryUserById(userId);
            if (user==null){
                resultMsg.setMessage("该用户不存在");
                resultMsg.setState(400);
                break;
            }
            user.setUserState(user.getUserState()+change);
            //执行修改
            Integer result = userManageDao.setUserState(user);
            if (result>0) {
                resultMsg.setMessage("修改成功");
                resultMsg.setState(200);
                break;
            }
            if (i==max){
                resultMsg.setMessage("修改失败");
                resultMsg.setState(400);
            }
        }

        return JSON.toJSONString(resultMsg);
    }

    public String deleteUserById(String userId) {
        ResultMessage<Object> resultMsg = new ResultMessage<Object>();
        //乐观锁机制
        int max =10 ;
        for (int i = 1; i <=max ; i++) {
            KyUser user = userManageDao.queryUserById(userId);
            if (user == null) {
                resultMsg.setMessage("该用户不存在");
                resultMsg.setState(400);
                break;
            }
            //执行修改
            Integer result = userManageDao.deleteUserById(user);
            if (result > 0) {
                resultMsg.setMessage("删除成功");
                resultMsg.setState(200);
                break;
            }
            if (i == max) {
                resultMsg.setMessage("删除失败");
                resultMsg.setState(400);
            }
        }
        return JSON.toJSONString(resultMsg);
    }

    public String queryUserByPostAndLikeWord(PageModel<KyUser> pageModel, Boolean state , String likeWord) {
        //模糊查询为空，就用通配符
        if (likeWord==null)likeWord ="%";
        else likeWord = "%"+likeWord+"%";
        ArrayList<KyUser> users;
        //开启分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        //返回结果是已经分页后的，但是其他数据需要交给page或者pageInfo处理后才有
        if (state){
           users =  userManageDao.queryNoRights(likeWord);
        }else users = userManageDao.queryRights(likeWord);

        PageInfo<KyUser> pageInfo = new PageInfo<KyUser>(users);
        //使用包装方法
        return pageDataPackaging(pageInfo,pageModel);
    }

    public String queryUserByCommentAndLikeWord(PageModel<KyUser> pageModel, Boolean state, String likeWord) {
        //模糊查询为空，就用通配符
        if (likeWord==null)likeWord ="%";
        else likeWord = "%"+likeWord+"%";
        ArrayList<KyUser> users;
        //开启分页
        PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize());
        //返回结果是已经分页后的，但是其他数据需要交给page或者pageInfo处理后才有
        if (state){
            users =  userManageDao.queryNoCommentRights(likeWord);
        }else users = userManageDao.queryCommentRights(likeWord);

        PageInfo<KyUser> pageInfo = new PageInfo<KyUser>(users);
        //使用包装方法
        return pageDataPackaging(pageInfo,pageModel);
    }

    /**
     * 使用泛型方法，对分页查询的数据封装到pageModel里。并JSON处理
     * @param pageInfo 处理后的分页数据
     * @param pageModel 发往前端的对象
     * @param <T> 根据对象参数决定
     * @return  pageModel的JSON对象
     */
    public <T> String  pageDataPackaging(PageInfo<T> pageInfo,PageModel<T> pageModel){
        if (pageInfo.getList()!=null){
            pageModel.setMessage("查询成功");
            pageModel.setState(200);
            pageModel.setData(pageInfo.getList());
            pageModel.setPages(pageInfo.getPages());
            pageModel.setTotal(pageInfo.getTotal());
            return JSON.toJSONString(pageModel);
        }
        pageModel.setMessage("查询失败");
        pageModel.setState(400);
        return JSON.toJSONString(pageModel);
    }

}
