package com.try02;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ztc on 15-11-1.
 */
public class Server2 extends JFrame implements ActionListener,KeyListener{
    JTextArea jta=null;
    JTextField jtf=null;
    JButton jb=null;
    JPanel jp1=null;
    JScrollPane jsp=null;
    Socket s=null;
    public static void main(String[] args){
        Server2 s2=new Server2();
    }

    public Server2(){

        jta=new JTextArea();
        //  参数是宽度
        jtf=new JTextField(15);
        jb=new JButton("Send!");
        //给按钮添加动作监听
        jb.addActionListener(this);
        jp1=new JPanel();
        //参数为要加滚动条的组件,返回的为加完滚动条的组件
        jsp=new JScrollPane(jta);
        //给输入框加键盘监听
        jtf.addKeyListener(this);
        jp1.add(jtf);
        jp1.add(jb);
        this.add(jsp,"Center");
        this.add(jp1,"South");

        this.setTitle("Server");
        this.setSize(300,300);
        this.setLocation(500,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{
            //启动服务器监听 9999 端口
            ServerSocket ser=new ServerSocket(9999);
            s=ser.accept();
            //获得实时刷新的输入流
            InputStreamReader in=new InputStreamReader(s.getInputStream(),"UTF-8");
            BufferedReader br=new BufferedReader(in);
            //不断读取
            while(true)
            {
                String msg=br.readLine();
                jta.append(msg+"\r\n");
                if(msg.equals("bye"))
                {
                    ser.close();
                    break;
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent arg0){
        try{
            if(arg0.getSource()==jb) {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                pw.println("Server:\t" + jtf.getText());
                jta.append("Server:\t" + jtf.getText()+"\r\n");
                jtf.setText("");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void keyTyped(KeyEvent k){
    }
    public void keyReleased(KeyEvent k){
    }
    //按下回车发送
    public void keyPressed(KeyEvent k){
        try {
            if (k.getKeyCode() == KeyEvent.VK_ENTER) {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                pw.println("Server:\t" + jtf.getText());
                jta.append("Server:\t" + jtf.getText() + "\r\n");
                jtf.setText("");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
