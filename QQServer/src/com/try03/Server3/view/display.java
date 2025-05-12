package com.try03.Server3.view;

import com.try03.Server3.model.MyServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ztc on 15-11-2.
 */
public class Display extends JFrame implements ActionListener {

    JTabbedPane jtp;
    JPanel jp, jp1;      //jp1 在线人
    JButton jb1, jb2;
    JScrollPane jsp, jsp1;       //jsp1在线用户
    JTextArea jta;
    JTextField jtf;
    JLabel jl;
    public static Display di;

    public static void main(String[] args) {
        di = new Display();
    }

    public Display() {
        jp = new JPanel();
        jb1 = new JButton("启动服务器");
        jb1.addActionListener(this);
        jb2 = new JButton("停止服务器");
        jb2.addActionListener(this);
        jtf = new JTextField(10);
        jl = new JLabel("Port:");
        jp.add(jl);
        jp.add(jtf);
        jp.add(jb1);
        jp.add(jb2);

        jta = new JTextArea();
        jsp = new JScrollPane(jta);
        jta.setText("LOG!\r\n");
        jta.setEditable(false);
        jsp.setAutoscrolls(true);
        //在线用户面板
        jp1 = new JPanel();
        jsp1 = new JScrollPane(jp1);

        jtp = new JTabbedPane();
        jtp.add(jsp, "LOG");
        jtp.add(jsp1, "在线用户");

        this.add(jp, "North");
        this.add(jtp, "Center");
        this.setSize(400, 400);
        this.setLocation(500, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showLog(String log) {
        jta.append(log + "\r\n");
    }

    public void showOnline(Object[] user) {
        jp1.setLayout(new GridLayout(user.length, 2, 2, 2));
        jp1.removeAll();
        for (int i = 0; i < user.length; i++) {
            jp1.add(new JLabel(user[i].toString(), JLabel.LEFT));
            JLabel out = new JLabel("强制下线", JLabel.RIGHT);
            out.setForeground(Color.BLUE);
            jp1.add(out);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyServer service = null;
        if (e.getSource() == jb1) {
            //参数为端口
            service = new MyServer(jtf.getText());
            service.start();
        } else if (e.getSource() == jb2) {
            service.mystop();
            System.out.println("按下按钮");
        }
    }
}
