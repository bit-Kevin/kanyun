package com.kanyun.kanyun_management.userLogin.LoginService.serviceImp;

import com.alibaba.fastjson.JSON;
import com.kanyun.kanyun_management.userLogin.LoginDao.ILoginDao;
import com.kanyun.kanyun_management.userLogin.LoginModel.KyUser;
import com.kanyun.kanyun_management.userLogin.LoginService.ILoginService;
import com.kanyun.kanyun_management.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    ILoginDao loginDao;

    public String VerifyLogin(KyUser user) {
        Message msg = new Message();
        KyUser result = loginDao.VerifyUser(user);
        System.out.println("账号验证结果"+result);
        if (result!=null){
            msg.setMessage("登录成功");
            msg.setState(200);
            msg.setUser(result);
            String s = JSON.toJSONString(msg);
            return s ;
        }
        msg.setMessage("登录失败");
        msg.setState(403);
        String s = JSON.toJSONString(msg);
        return s;
    }
}
