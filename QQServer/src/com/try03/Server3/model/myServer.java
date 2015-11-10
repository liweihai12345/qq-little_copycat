package com.try03.Server3.model;

import com.try03.Server3.tools.threadManger;
import com.try03.common.Message;
import com.try03.common.User;
import com.try03.Server3.view.display;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by ztc on 15-11-2.
 */
public class myServer extends Thread{
    private ServerSocket ss;
    private volatile  boolean sign=true;
    public myServer(String port){
        System.out.println("监听"+port+"端口。。。。");
        try {
            ss = new ServerSocket(Integer.parseInt(port));
            display.di.showLog("服务器在 "+port+" 端口监听----");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void mystop(){
        this.sign=false;
    }
    public void run(){
        try {
            while(true) {
                Socket s = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User u = (User) ois.readObject();
                System.out.println("收到UserName:" + u.getUserName() + " PassWard:" + u.getPassWard());
                Message m = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                if (u.getPassWard().equals(" ")) {
                    display.di.showLog("用户名:"+u.getUserName() + " 登陆成功！密码:" + u.getPassWard());
                    m.setType("success");
                    oos.writeObject(m);
                    //启动一个线程来服务
                    myThread mythread=new myThread(s,oos,ois);
                    mythread.start();
                    threadManger.addThread(u.getUserName(),mythread);
                    threadManger.friendAlert();
                } else {
                    display.di.showLog("用户名:"+u.getUserName() + " 登陆失败！密码:" + u.getPassWard());
                    m.setType("failed");
                    oos.writeObject(m);
                    oos.close();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
