package com.qq.common;

import java.io.Serializable;

/**

 * 发送的消息的类

 * */

public class Message implements Serializable {

    private int mesType;//服务器返回的信息包：1代表用户验证成功,2代表用户验证失败,3代表其他信息包;

    private String sender;//发送消息的人

    private String getter;//接收消息的人

    private String context;//发送的消息

    private String time;//发送的时间


    public int getMesType() {

        return mesType;

    }



    public void setMesType(int mesType) {

        this.mesType = mesType;

    }



    public String getSender() {

        return sender;

    }



    public void setSender(String sender) {

        this.sender = sender;

    }



    public String getGetter() {

        return getter;

    }



    public void setGetter(String getter) {

        this.getter = getter;

    }



    public String getContext() {

        return context;

    }



    public void setContext(String context) {

        this.context = context;

    }



    public String getTime() {

        return time;

    }



    public void setTime(String time) {

        this.time = time;

    }



}


