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
import java.net.Socket;

/**
 * Created by ztc on 15-11-1.
 */
public class Client2 extends JFrame implements ActionListener,KeyListener{

    JTextArea jta=null;
    JTextField jtf=null;
    JButton jb=null;
    JPanel jp1=null;
    JScrollPane jsp=null;
    Socket cl=null;
    public static void main(String[] args){
        Client2 c2=new Client2();
    }
    public Client2(){

        jta=new JTextArea();
        //  参数是宽度
        jtf=new JTextField(15);
        jb=new JButton("Send!");
        //添加动作监听
        jb.addActionListener(this);
        jp1=new JPanel();
        //参数为要加滚动条的组件,返回的为加完滚动条的组件
        jsp=new JScrollPane(jta);
        //添加键盘监听
        jtf.addKeyListener(this);
        jp1.add(jtf);
        jp1.add(jb);
        this.add(jsp,"Center");
        this.add(jp1,"South");

        this.setTitle("Client");
        this.setSize(300,300);
        this.setLocation(500,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            cl=new Socket("192.168.16.100",9999);
            InputStreamReader in=new InputStreamReader(cl.getInputStream(),"UTF-8");
            BufferedReader br=new BufferedReader(in);
            while(true)
            {
                String msg=br.readLine();
                jta.append(msg+"\r\n");
                if(msg.equals("bye"))
                {
                    cl.close();
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void actionPerformed(ActionEvent a){
        try {
            if (a.getSource() == jb) {
                PrintWriter pw = new PrintWriter(cl.getOutputStream(),true);
                pw.println("Client:\t" + jtf.getText());
                jta.append("Client:\t" + jtf.getText() + "\r\n");
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
    //回车发送
    public void keyPressed(KeyEvent k){
        try {
            if (k.getKeyCode() == KeyEvent.VK_ENTER) {
                PrintWriter pw = new PrintWriter(cl.getOutputStream(),true);
                pw.println("Client:\t" + jtf.getText());
                jta.append("Client:\t" + jtf.getText() + "\r\n");
                jtf.setText("");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
