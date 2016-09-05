package com.felink.guihubcode.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/2.
 */
public class UserBean implements Serializable{
    private  static  final  long serialVersionUID = 1L;

    public int userId;
    public String userName;
    public boolean isMale;

    public UserBean(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }
}
