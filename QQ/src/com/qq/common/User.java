package com.qq.common;

import java.io.Serializable;

/**
 * 用户类
 */

public class User implements Serializable {

    private String name;//用户名


    private String passWord;//密码


    public String getName() {

        return name;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getPassWord() {

        return passWord;

    }

    public void setPassWord(String passWord) {

        this.passWord = passWord;

    }


}