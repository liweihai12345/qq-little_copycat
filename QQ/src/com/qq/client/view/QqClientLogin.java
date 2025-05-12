package com.qq.client.view;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

/**
 * QQ客户端登入窗口
 */

public class QqClientLogin extends JFrame implements ActionListener {

//定义北部组件

    JLabel jbl1;


//定义中部组件:其中中部组件有一个选项卡的窗口管理,有三个JPanel,一个文本框,一个密码框,4个JLabel,一个清除号码按钮,两个多选框

    JTabbedPane jtp;//选项卡窗口

    JPanel jp2;//QQ号码

    JPanel jp3;//手机号码

    JPanel jp4;//电子邮件

    JLabel jp2_jpl1;//QQ号码

    JLabel jp2_jpl2;//QQ密码

    JLabel jp2_jpl3;//忘记密码

    JLabel jp2_jpl4;//申请密码保护

    JButton jp2_jb1;//清除号码

    JTextField jp2_jtf;//文本框

    JPasswordField jp2_jpf;//密码框

    JCheckBox jp2_jcb1;//隐身登入

    JCheckBox jp2_jcb2;//记住密码


//定义南部组件

    JPanel jp1;

    JButton jp1_jb1;//登入按钮

    JButton jp1_jb2;//取消按钮

    JButton jp1_jb3;//注册向导按钮


    public static void main(String[] args) {


        QqClientLogin qqClientLogin = new QqClientLogin();


    }


    public QqClientLogin() {
        //处理北部的组件
        jbl1 = new JLabel(new ImageIcon("image/beibu.gif"));

        //处理中部的组件
        jp2 = new JPanel(new GridLayout(3, 3));//把中部分成三行三列

        jp2_jpl1 = new JLabel("QQ号码", JLabel.CENTER);

        jp2_jpl2 = new JLabel("QQ密码", JLabel.CENTER);

        jp2_jpl3 = new JLabel("忘记密码", JLabel.CENTER);

        jp2_jpl3.setForeground(Color.blue);//把忘记密码设为蓝色

        jp2_jpl4 = new JLabel("申请密码保护", JLabel.CENTER);

        jp2_jb1 = new JButton(new ImageIcon("image/clear.png"));

        jp2_jtf = new JTextField();

        jp2_jpf = new JPasswordField();

        jp2_jcb1 = new JCheckBox("隐身登入");

        jp2_jcb2 = new JCheckBox("记住密码");


        //把控件按照顺序加入到jp2
        jp2.add(jp2_jpl1);

        jp2.add(jp2_jtf);

        jp2.add(jp2_jb1);

        jp2.add(jp2_jpl2);

        jp2.add(jp2_jpf);

        jp2.add(jp2_jpl3);

        jp2.add(jp2_jcb1);

        jp2.add(jp2_jcb2);

        jp2.add(jp2_jpl4);


        //创建选项卡窗口,把三个JPanel放进去,最后把选项卡放进JFrame中

        jtp = new JTabbedPane();

        jtp.add("QQ号码", jp2);

        jp3 = new JPanel();

        jtp.add("手机号码", jp3);

        jp4 = new JPanel();

        jtp.add("电子邮件", jp4);


        //处理南部的组件

        jp1 = new JPanel();

        jp1_jb1 = new JButton(new ImageIcon("image/dengru.png"));

        jp1_jb1.addActionListener(this);

        jp1_jb2 = new JButton(new ImageIcon("image/quxiao.png"));

        jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.png"));


        //把三个按钮放进jp1中

        jp1.add(jp1_jb1);

        jp1.add(jp1_jb2);

        jp1.add(jp1_jb3);


        //把定义好的组件放入到JFrame中

        this.add(jbl1, "North");//放入北部组件

        this.add(jp1, "South");//放入南部组件

        this.add(jtp, "Center");//放入中部组件


        //定义JFrame的一些属性

        this.setSize(350, 240);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setIconImage(new ImageIcon("image/qq.png").getImage());

        this.setResizable(false);//不允许放大，改变窗口大小等

        this.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //用户点击登录按钮

        if (e.getSource() == jp1_jb1) {

            User user = new User();

            user.setName(jp2_jtf.getText().trim());

            user.setPassWord(new String(jp2_jpf.getPassword()).trim());

            QqClientUser qqClientUser = new QqClientUser();

            //判断是否登录成功

            if (qqClientUser.checkUser(user)) {


                try {

                    //把创建好友列表提前

                    QqFriendList qqList = new QqFriendList(jp2_jtf.getText());

                    ManageQqFriendList.addQqFriendList(user.getName(), qqList);


                    //发送一个要求返回在线好友的请求包

                    ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.

                            getClientConServerThread(user.getName()).client.getOutputStream());

                    Message message = new Message();

                    message.setSender(user.getName());

                    message.setMesType(MessageType.message_get_onLineFriend);


                    //发送

                    oos.writeObject(message);

                } catch (Exception e1) {

                    e1.printStackTrace();

                }


                this.dispose();

            } else {

                JOptionPane.showMessageDialog(this, "QQ号码或者密码错误");

            }

        }

    }


}

