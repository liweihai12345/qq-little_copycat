package com.qq.server.view;

import com.qq.server.model.MyQqServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 这是服务器端的控制界面,可以完成启动服务器,关闭服务器
 * <p>
 * 管理和监控用户
 */

public class MyServerFrame extends JFrame implements ActionListener {


    JPanel jp;

    JButton jb1;

    JButton jb2;

    MyQqServer myQqServer = null;

    public static void main(String[] args) {

        MyServerFrame mysf = new MyServerFrame();

    }


    public MyServerFrame() {

        jp = new JPanel();

        jb1 = new JButton("启动服务器");

        jb1.addActionListener(this);

        jb2 = new JButton("关闭服务器");

        jb2.addActionListener(this);

        jp.add(jb1);

        jp.add(jb2);

        this.add(jp);

        this.setSize(500, 400);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);


    }


    @Override

    public void actionPerformed(ActionEvent e) {

//开启服务器

        if (e.getSource() == jb1) {

            myQqServer = new MyQqServer();

        } else if (e.getSource() == jb2) {


            this.dispose();


        }


    }


}






