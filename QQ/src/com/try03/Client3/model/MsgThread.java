package com.try03.Client3.model;

import com.try03.Client3.tools.ChatManger;
import com.try03.Client3.tools.ListManger;
import com.try03.Client3.view.Chat;
import com.try03.common.QqMessage;
import java.io.IOException;

/**
 * Created by ztc on 15-11-7.
 */
public class MsgThread extends Thread {

    public void run() {
        try {
            while (true) {
                QqMessage m = (QqMessage) ClientUser.getOIS().readObject();
                if (m.getType().equals("sendMsg")) {
                    System.out.println(m.getSender() + " say to " + m.getGetter() + " with " + m.getMsg());
                    //已经打开聊天界面
                    if (ChatManger.Getchat(m.getGetter() + "_" + m.getSender()) != null) {
                        Chat ch = ChatManger.Getchat(m.getGetter() + "_" + m.getSender());
                        ch.ShowMsg(m.getSender() + " : " + m.getMsg());
                    } else {
                        Chat ch = new Chat(m.getGetter(), m.getSender());
                        ChatManger.Addchat(m.getGetter() + "_" + m.getSender(), ch);
                        ch.ShowMsg(m.getSender() + " : " + m.getMsg());
                    }

                } else if (m.getType().equals("getFriend")) {
                    //好友列表
                    Object[] l = m.getFriList();
                    System.out.println(m.getType() + "  " + l.length);
                    ListManger.getMainL().changeList(l);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
