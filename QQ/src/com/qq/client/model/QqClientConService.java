package com.qq.client.model;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 客户端连接服务器后台
 */

public class QqClientConService {

    public Socket client;


//判断是否成功登录,成功返回true,否则返回false

    public boolean sendLoginInfoToServer(Object o) {

        boolean isLogin = false;

        try {

//创建连接

            client = new Socket("localhost", 9999);

            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            oos.writeObject(o);


            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

            Message message = (Message) ois.readObject();

//登录成功的判断

            if (message.getMesType() == 1) {

                isLogin = true;

//登录成功,创建一个改客户端和服务器的线程

                ClientConServerThread ccst = new ClientConServerThread(client);

//把改线程添加到管理线程的map中

                ManageClientConServerThread.addClientConServerThread(((User) o).getName(), ccst);

//启动该线程

                new Thread(ccst).start();

            }


        } catch (Exception e) {

            e.printStackTrace();

        }

        return isLogin;

    }


}

