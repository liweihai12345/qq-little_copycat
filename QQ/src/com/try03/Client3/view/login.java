package com.try03.Client3.view;

import com.try03.Client3.model.MsgThread;
import com.try03.Client3.model.clientUser;
import com.try03.Client3.tools.listManger;
import com.try03.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

/**
 * Created by ztc on 15-11-2.
 */
public class login extends JFrame implements ActionListener,KeyListener{
    //北部
    JLabel jl;
    //中部
    JTabbedPane jtp;
    JPanel jtp_jp1,jtp_jp2,jtp_jp3;
    JLabel jtp_jp1_jl1,jtp_jp1_jl2,jtp_jp1_jl3,jtp_jp2_jl1,jtp_jp2_jl2;
    JCheckBox jtp_jp1_jcb1,jtp_jp1_jcb2;
    JTextField jtf,jtf2,jtf3;       //ip,port
    JPasswordField jpf;
    //南部
    JPanel jp;
    JButton jp_jb1,jp_jb2;
    public static void main(String[] args){
        login lo=new login();
    }

    public login(){
        //北部
        URL touUrl=login.class.getResource("/image/tou.gif");
        jl=new JLabel(new ImageIcon(touUrl));
        //中部
        jtp=new JTabbedPane();
        jtp_jp1=new JPanel();
        jtp_jp2=new JPanel();
        jtp_jp3=new JPanel();
        jtp_jp1_jl1=new JLabel("账号",JLabel.CENTER);
        jtp_jp1_jl2=new JLabel("密码",JLabel.CENTER);
        jtp_jp1_jl3=new JLabel("忘记密码",JLabel.CENTER);
        jtp_jp1_jl3.setForeground(Color.blue);
        jtp_jp1_jcb1=new JCheckBox("隐身登陆");
        jtp_jp1_jcb2=new JCheckBox("记住密码");

        jtf=new JTextField();
        jpf=new JPasswordField();
        jpf.addKeyListener(this);

        jtp_jp1.setLayout(new GridLayout(3,3));
        //第一行
        jtp_jp1.add(jtp_jp1_jl1);
        jtp_jp1.add(jtf);
        jtp_jp1.add(new JLabel());
        //第二行
        jtp_jp1.add(jtp_jp1_jl2);
        jtp_jp1.add(jpf);
        jtp_jp1.add(jtp_jp1_jl3);
        //第三行
        jtp_jp1.add(new JLabel());
        jtp_jp1.add(jtp_jp1_jcb1);
        jtp_jp1.add(jtp_jp1_jcb2);

        //服务器
        jtp_jp2_jl1=new JLabel("IP:",JLabel.CENTER);
        jtp_jp2_jl2=new JLabel("Port:",JLabel.CENTER);
        jtf2=new JTextField();
        jtf2.setText("127.0.0.1");
        jtf3=new JTextField();
        jtf3.setText("8888");
        jtp_jp2.setLayout(new GridLayout(2,2));
        jtp_jp2.add(jtp_jp2_jl1);
        jtp_jp2.add(jtf2);
        jtp_jp2.add(jtp_jp2_jl2);
        jtp_jp2.add(jtf3);
        //JTabbedPane
        jtp.add("QQ号",jtp_jp1);
        jtp.add("服务器",jtp_jp2);
        jtp.add("邮箱",jtp_jp3);

        //南部
        jp=new JPanel();
        URL quxiaoUrl=login.class.getResource("/image/quxiao.gif");
        URL dengluUrl=login.class.getResource("/image/denglu.gif");
        jp_jb1=new JButton(new ImageIcon(dengluUrl));
        jp_jb1.addActionListener(this);
        jp_jb2=new JButton(new ImageIcon(quxiaoUrl));
        jp.add(jp_jb1);
        jp.add(jp_jb2);


        //初始化
        this.add(jl,"North");
        this.add(jtp,"Center");
        this.add(jp,"South");
        URL qqUrl=login.class.getResource("/image/qq.gif");
        this.setIconImage((new ImageIcon(qqUrl)).getImage());
        this.setTitle("Login");
        this.setSize(350,200);
        this.setResizable(false);
        this.setLocation(500,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        User u=new User();
        //trim（）去除前导和后导空格
        u.setUserName(jtf.getText().trim());
        u.setPassWard(new String(jpf.getPassword()));

        if(jtf2.getText().equals("")||jtf3.getText().equals(""))
            JOptionPane.showMessageDialog(this,"输入服务器地址和端口！");
        else if(new clientUser().checkLogin(u,jtf2.getText(),jtf3.getText())){
            list mainL=new list(u.getUserName());
            listManger.setMainL(mainL);
            new MsgThread().start();
            this.dispose();
        }else
            JOptionPane.showMessageDialog(this,"登陆失败！");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
            User u = new User();
            //trim（）去除前导和后导空格
            u.setUserName(jtf.getText().trim());
            u.setPassWard(new String(jpf.getPassword()));
            if(jtf2.getText().equals("")||jtf3.getText().equals(""))
                JOptionPane.showMessageDialog(this,"输入服务器地址和端口！");
            else if (new clientUser().checkLogin(u,jtf2.getText(),jtf3.getText())) {
                list mainL=new list(u.getUserName());
                listManger.setMainL(mainL);
                new MsgThread().start();
                this.dispose();
            } else
                JOptionPane.showMessageDialog(this, "登陆失败！");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
