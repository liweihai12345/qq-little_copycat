package com.try03.Client3.model;

import com.try03.common.QqMessage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ztc on 15-11-2.
 */
public class ClientUser {
    private static ConServer con;

    public boolean checkLogin(Object o, String ip, String port) {
        con = new ConServer();
        return con.sendLoginInfo(o, ip, port);
    }

    public static Socket getSocket() {
        return con.getS();
    }

    public static ObjectInputStream getOIS() {
        return con.getOIS();
    }

    public static ObjectOutputStream getOOS() {
        return con.getOOS();
    }

    public static void LoginOut(String userId) {
        QqMessage m = new QqMessage();
        m.setType("LoginOut");
        m.setSender(userId);
        con.sendObject(m);
    }
}
