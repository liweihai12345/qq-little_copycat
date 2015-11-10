package com.try03.Client3.view;

import com.try03.Client3.model.clientUser;
import com.try03.Client3.model.conServer;
import com.try03.Client3.tools.chatManger;
import com.try03.common.Message;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;

/**
 * Created by ztc on 15-11-2.
 */
public class chat extends JFrame implements ActionListener,KeyListener{
    String userid,friendid;
    JTextArea jta;
    JScrollPane jsp;
    JTextField jtf;
    JButton jb;
    JPanel jp;
    public static void main(String[] args){
        chat ch=new chat("ni","ta");
    }

    public chat(final String userId, final String friendId) {
        this.userid = userId;
        this.friendid = friendId;


        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jtf = new JTextField(25);
        jtf.addKeyListener(this);
        jb = new JButton("发送");
        jb.addActionListener(this);
        jp = new JPanel();
        jsp.setAutoscrolls(true);
        jta.setEditable(false);
        jp.add(jtf);
        jp.add(jb);

        this.add(jsp, "Center");
        this.add(jp, "South");

        this.setTitle(userId + " 正在和 " + friendId + " 聊天.");
        this.setSize(400, 300);
        this.setLocation(500, 300);
        this.setResizable(false);
        this.setVisible(true);
        URL qqUrl=chat.class.getResource("/image/qq.gif");
        this.setIconImage((new ImageIcon(qqUrl)).getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(userId+"_"+friendId+" is closing!");
                chatManger.Delchat(userId+"_"+friendId);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public void ShowMsg(String msg){
        jta.append(msg+"\r\n");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb) {
            jta.append(this.userid + " : " + jtf.getText() + "\r\n");
            Message m = new Message();
            m.setType("sendMsg");
            m.setSender(this.userid);
            m.setGetter(this.friendid);
            m.setMsg(jtf.getText());
            try {
                System.out.println(clientUser.getSocket()+"发送");
                clientUser.getOOS().writeObject(m);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            jta.append(this.userid + " : " + jtf.getText() + "\r\n");
            Message m = new Message();
            m.setType("sendMsg");
            m.setSender(this.userid);
            m.setGetter(this.friendid);
            m.setMsg(jtf.getText());
            try {
                System.out.println(clientUser.getSocket() + "发送");
                clientUser.getOOS().writeObject(m);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
