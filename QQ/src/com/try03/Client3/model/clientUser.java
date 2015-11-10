package com.try03.Client3.model;

import com.try03.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by ztc on 15-11-2.
 */
public class clientUser {
    private static conServer con;
    public boolean checkLogin(Object o,String ip,String port){
        con= new conServer();
        return con.sendLoginInfo(o,ip,port);
    }

    public static Socket getSocket(){
        return con.getS();
    }
    public static ObjectInputStream getOIS(){
        return con.getOIS();
    }
    public static ObjectOutputStream getOOS(){
        return con.getOOS();
    }
    public static void LoginOut(String userId){
        Message m=new Message();
        m.setType("LoginOut");
        m.setSender(userId);
        con.sendObject(m);
    }
}
