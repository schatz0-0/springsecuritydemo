package com.zime.consumerclient.mode;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class User {

    private String name;
    private int gender;
    private String nation;
    private Date birthday;
    private String id;
    private String email;
    private String qq;
    private String mobile;
    private String wechat;
    private String speciality;

}

