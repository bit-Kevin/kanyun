package com.kanyun.kanyun_management.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class KyUser {

  private long userId;
  private String userIp;
  private String userName;
  private String userPassword;
  private String userEmail;
  private String userProfilePhoto;
  private java.sql.Timestamp userRegistrationTime;
  private Date userBirthday;
  private long userRole;
  private String userTelephoneNumber;
  private String userNickname;
  private long xxx1;
  private String xxx2;
  private String xxx3;
  private long delete;
  private long version;
  private long userState;

}
