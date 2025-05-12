package com.qq.client.tools;

import com.qq.client.view.QqChar;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

/**

 * 这是客户端和服务器保持通讯的线程

 * */

public class ClientConServerThread implements Runnable{


    public Socket client;


    public ClientConServerThread(Socket client) {

        this.client = client;

    }


    @Override

    public void run() {

//不停的读取从服务器发来的消息

        while(true) {

            try {

                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

                Message message = (Message) ois.readObject();


//判断发来的消息包是否为普通消息包,或者是返回在线好友的包

                if(message.getMesType() == MessageType.message_common_mes) {

//把从服务器发来的消息显示在聊天界面:1.从管理聊天窗口的类中取得该窗口 2.调用显示方法.

                    QqChar qqChar = ManageQqChar.getQqChar(message.getGetter()+" "+message.getSender());

                    qqChar.showMessage(message);

                } else if(message.getMesType() == MessageType.message_ret_onLineFriend) {

                    String getter = message.getGetter();

//修改响应的好友列表

                    QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);


//更新在线好友

                    if(qqFriendList != null) {

                        qqFriendList.updateFriend(message);

                    }


                }



            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }


}


