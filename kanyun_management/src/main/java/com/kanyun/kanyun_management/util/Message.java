package com.kanyun.kanyun_management.util;

import com.kanyun.kanyun_management.userLogin.LoginModel.KyUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private  String message;
    private  int state;
    private KyUser user;
}
