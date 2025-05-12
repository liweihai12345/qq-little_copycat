package com.try03.common;

import java.io.Serializable;

/**
 * Created by ztc on 15-11-2.
 */
public class QqUser implements Serializable {
    private String userName;
    private String passWard;

    public QqUser(){

    }

    public String getPassWard() {
        return passWard;
    }

    public void setPassWard(String passWard) {
        this.passWard = passWard;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
