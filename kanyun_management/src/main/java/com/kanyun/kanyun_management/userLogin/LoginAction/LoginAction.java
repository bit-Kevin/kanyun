package com.kanyun.kanyun_management.userLogin.LoginAction;

import com.kanyun.kanyun_management.userLogin.LoginModel.KyUser;
import com.kanyun.kanyun_management.userLogin.LoginService.serviceImp.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin//用于解决前端访问跨域的问题
@Controller
@RequestMapping("/manage")
public class LoginAction {
    @Autowired
    LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    public  String login(KyUser user){
        System.out.println("接收到的前端数据:"+user);
        return  loginService.VerifyLogin(user);
    }
}
