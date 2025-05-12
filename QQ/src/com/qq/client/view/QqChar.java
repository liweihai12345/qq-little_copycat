package com.qq.client.view;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * QQ聊天窗口
 */

public class QqChar extends JFrame implements ActionListener {


    JTextField jtf;//输入的文本框

    JTextArea jta;//文本区域

    JButton jb;//发送按钮

    JPanel jp;//装 文本框 和 发送按钮 的panel

    String friend;

    String userName;

    public static void main(String[] args) {

        QqChar qqChar = new QqChar("1", "2");

    }


    public QqChar(String userName, String friend) {

        this.userName = userName;

        this.friend = friend;

        jtf = new JTextField(15);

        jta = new JTextArea();

        jb = new JButton("发送");

        jb.addActionListener(this);

        jp = new JPanel();

        jp.add(jtf);

        jp.add(jb);


        this.add(jta, "Center");

        this.add(jp, "South");

        this.setTitle(userName + " 正在和 " + friend + " 聊天");

        this.setIconImage(new ImageIcon("image/qq.png").getImage());

        this.setSize(300, 200);

        this.setVisible(true);

    }


    public void showMessage(Message message) {

        String info = message.getSender() + " 对 " + message.getGetter() + " 说:" + message.getContext() + "\r\n";

        this.jta.append(info);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

//当点击发送按钮时,把消息发送到服务器端

        if (e.getSource() == jb) {

            Message message = new Message();

            message.setMesType(MessageType.message_common_mes);

            message.setSender(userName);

            message.setGetter(friend);

            message.setContext(jtf.getText());

            jtf.setText("");

            message.setTime(new Date().toString());

            try {

                ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(userName).client.getOutputStream());

                oos.writeObject(message);

            } catch (Exception e1) {

                e1.printStackTrace();

            }

        }

    }

}




