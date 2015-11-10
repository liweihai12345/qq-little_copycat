package com.try03.Client3.model;

import com.try03.Client3.tools.chatManger;
import com.try03.Client3.tools.listManger;
import com.try03.Client3.view.chat;
import com.try03.Client3.view.login;
import com.try03.common.Message;

import java.io.IOException;

/**
 * Created by ztc on 15-11-7.
 */
public class MsgThread extends Thread{

    public void run(){
        try {
            while(true) {
                Message m = (Message)clientUser.getOIS().readObject();
                if(m.getType().equals("sendMsg")) {
                    System.out.println(m.getSender() + " say to " + m.getGetter() + " with " + m.getMsg());
                    //已经打开聊天界面
                    if(chatManger.Getchat(m.getGetter() + "_" + m.getSender())!=null) {
                        chat ch = chatManger.Getchat(m.getGetter() + "_" + m.getSender());
                        ch.ShowMsg(m.getSender() + " : " + m.getMsg());
                    }else{
                        chat ch=new chat(m.getGetter(),m.getSender());
                        chatManger.Addchat(m.getGetter()+"_"+m.getSender(),ch);
                        ch.ShowMsg(m.getSender() + " : " + m.getMsg());
                    }

                }else if(m.getType().equals("getFriend")){
                    //好友列表
                    Object[] l=m.getFriList();
                    System.out.println(m.getType()+"  "+l.length);
                    listManger.getMainL().changeList(l);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
