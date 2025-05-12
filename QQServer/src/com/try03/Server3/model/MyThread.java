package com.try03.Server3.model;

import com.try03.Server3.tools.threadManger;
import com.try03.Server3.view.Display;
import com.try03.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

/**
 * Created by ztc on 15-11-4.
 */
public class MyThread extends Thread {
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public MyThread(Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
        this.s = s;
        this.oos = oos;
        this.ois = ois;

    }

    public void sendList() {
        Set<String> set = threadManger.bank.keySet();
        Object[] list = set.toArray();
        System.out.println(list.length);
        Message l = new Message();
        l.setType("getFriend");
        l.setFriList(list);
        try {
            this.oos.writeObject(l);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            while (true) {
                Message m = (Message) ois.readObject();
                if (m.getType().equals("sendMsg")) {
                    System.out.println(s + m.getSender() + " 对 " + m.getGetter() + " 说：" + m.getMsg());
                    Display.di.showLog(s.getInetAddress() + " : " + m.getSender() + " 对 " + m.getGetter() + " 说：" + m.getMsg());
                    //转发
                    MyThread fri = threadManger.getThread(m.getGetter());
                    fri.oos.writeObject(m);
                } else if (m.getType().equals("LoginOut")) {
                    Display.di.showLog(m.getSender() + "下线！");
                    threadManger.delThread(m.getSender());
                    threadManger.friendAlert();
                    System.out.println(m.getSender() + " loginOut!");
                }
                /*
                else if(m.getType().equals("getFriend")){
                    //请求好友列表
                    Set<String> set=threadManger.bank.keySet();
                    Object[] list=set.toArray();
                    System.out.println(list.length);
                    Message l=new Message();
                    l.setType("getFriend");
                    l.setFriList(list);
                    this.oos.writeObject(m);
                }
                */
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
