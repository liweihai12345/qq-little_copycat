package com.qq.server.model;

import com.qq.common.Message;
import com.qq.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这是QQ服务器
 */

public class MyQqServer {

    public ServerSocket server;

    public static void main(String[] args) {

        new MyQqServer();

    }


    public MyQqServer() {

        try {

            System.out.println("在9999服务器监听.....");

            server = new ServerSocket(9999);


//阻塞,等待某个客户端来连接

            while (true) {

                Socket client = server.accept();


//读取从客户端发来的消息

                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

                User user = (User) ois.readObject();

                System.out.println("姓名是===" + user.getName() + "===密码是---" + user.getPassWord() + "---");


                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                Message message = new Message();

//只要密码是123456都让它登录成功

                if (user.getPassWord().equals("123456")) {

                    message.setMesType(1);

                    oos.writeObject(message);

//登入成功,开启一个线程

                    SerConClientThread sc = new SerConClientThread(client);

//把该线程加入到map中

                    ManageClientThread.addCilentThread(user.getName(), sc);

//启动线程

                    sc.start();


//通知其他在线用户

                    sc.notifyOther(user.getName());


                } else {

//登录不成功时

                    message.setMesType(2);

                    oos.writeObject(message);

                    client.close();

                }


            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}


