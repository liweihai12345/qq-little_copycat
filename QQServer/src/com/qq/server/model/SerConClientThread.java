package com.qq.server.model;

import com.qq.common.Message;
import com.qq.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

/**

 * 是服务器和某个客户端的通信线程

 * */

public class SerConClientThread extends Thread{

    Socket client;



    public SerConClientThread(Socket client) {

        this.client = client;

    }


//让该线程通知其他用户

    public void notifyOther(String userName) {

//得到所有在线的人的线程

        Map map = ManageClientThread.map;

        Iterator it = map.keySet().iterator();


        Message message = new Message();

        message.setMesType(MessageType.message_ret_onLineFriend);

        message.setContext(userName);

        while(it.hasNext()) {

//取得在线人的用户名

            String onLineUser = it.next().toString();

            try {

                ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getCilentThread(onLineUser).client.getOutputStream());

                message.setGetter(onLineUser);

                oos.writeObject(message);

            } catch (Exception e) {

                e.printStackTrace();

            }


        }


    }

    @Override

    public void run() {

        while(true) {

            try {

                ObjectInputStream ois=new ObjectInputStream(client.getInputStream());

                Message message = (Message)ois.readObject();

//对从客户端传来的消息进行类型判断,然后做处理

                if(message.getMesType()==MessageType.message_common_mes) {

                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getCilentThread(message.getGetter()).client.getOutputStream());

                    oos.writeObject(message);

                } else if(message.getMesType()==MessageType.message_get_onLineFriend) {

//把在服务器的好友给该客户端返回

                    String res = ManageClientThread.getAllOnLineUser();

                    Message m = new Message();

                    m.setMesType(MessageType.message_ret_onLineFriend);

                    m.setContext(res);

                    m.setGetter(message.getSender());

                    ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

                    oos.writeObject(m);

                }



            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }



}
