package com.try03.common;

import java.io.Serializable;

/**
 * Created by ztc on 15-11-2.
 */
public class Message implements Serializable {
    private String type;

    private String sender;
    private String getter;
    private String msg;
    private Object[] friList;

    public Object[] getFriList() {
        return friList;
    }

    public void setFriList(Object[] friList) {
        this.friList = friList;
    }
    public Message(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
