package com.qq.client.view;

import com.qq.client.tools.ManageQqChar;
import com.qq.common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 我的好友列表(包括陌生人和黑名单)
 */

public class QqFriendList extends JFrame implements ActionListener, MouseListener {


//第一张卡片信息(我的好友列表)

    JPanel jphy1;//总panel

    JPanel jphy2;//中部的panel

    JPanel jphy3;//南部的panel，放两个button

    JButton jphy_jb1;//我的好友按钮,防在总panel的北部

    JButton jphy_jb2;//陌生人按钮,放在jphy3中

    JButton jphy_jb3;//黑名单按钮,放在jphy3中

    JScrollPane jsp1;//中部的滚动的pane

    JLabel jbls[];//中部在线好友的列表


//第二张卡片信息(陌生人列表)

    JPanel jpmsr1;//总panel

    JPanel jpmsr2;//中部的panel

    JPanel jpmsr3;//南部的panel，放两个button

    JButton jpmsr_jb1;//我的好友按钮,放在jpmsr3中

    JButton jpmsr_jb2;//陌生人按钮,放在jpmsr3中

    JButton jpmsr_jb3;//黑名单按钮,放在总的panel的南部

    JScrollPane jsp2;//中部的滚动的pane


    String userName;


//把整个JFrame变成卡片布局

    CardLayout c1;


    public static void main(String[] args) {

        QqFriendList qqFriendList = new QqFriendList("1");

    }

    public QqFriendList(String userName) {
        this.userName = userName;
        //处理第一张卡片
        jphy_jb1 = new JButton("我的好友");
        jphy_jb2 = new JButton("陌生人");
        jphy_jb2.addActionListener(this);
        jphy_jb3 = new JButton("黑名单");
        jphy1 = new JPanel(new BorderLayout());//总的JPanel
        jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));//中部的JPanel,假定有五十个好友
        //给jphy2这个panel中初始化50个好友
        jbls = new JLabel[50];
        for (int i = 0; i < jbls.length; i++) {
            jbls[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.png"), JLabel.LEFT);
            jbls[i].setEnabled(false);
            if (jbls[i].getText().equals(userName)) {
                jbls[i].setEnabled(true);
            }
            jbls[i].addMouseListener(this);
            jphy2.add(jbls[i]);
        }


        jphy3 = new JPanel(new GridLayout(2, 1));
        //把黑名单按钮和陌生人按钮加入到jpyh3中

        jphy3.add(jphy_jb2);

        jphy3.add(jphy_jb3);

        //把jphy2放入滚动的pane中

        jsp1 = new JScrollPane(jphy2);
        //把jphy_jb1,jsp1,jphy3分别放入jphy1中北，中，南三个位置

        jphy1.add(jphy_jb1, "North");

        jphy1.add(jsp1, "Center");

        jphy1.add(jphy3, "South");
        //处理第二张卡片
        jpmsr_jb1 = new JButton("我的好友");
        jpmsr_jb1.addActionListener(this);
        jpmsr_jb2 = new JButton("陌生人");
        jpmsr_jb3 = new JButton("黑名单");
        jpmsr1 = new JPanel(new BorderLayout());//总的JPanel
        jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));//中部的JPanel,假定有二十个陌生人
        //给jpmsr2这个panel中初始化20个陌生人
        JLabel jbls2[] = new JLabel[20];
        for (int i = 0; i < jbls2.length; i++) {
            jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("../image/mm.png"), JLabel.LEFT);
            jpmsr2.add(jbls2[i]);
        }

        jpmsr3 = new JPanel(new GridLayout(2, 1));
        //把我的好友按钮和陌生人按钮加入到jpmsr3中
        jpmsr3.add(jpmsr_jb1);
        jpmsr3.add(jpmsr_jb2);
        //把jpmsr1放入滚动的pane中
        jsp2 = new JScrollPane(jpmsr2);
        //把jpmsr3,jsp2,jpmsr_jb3分别放入jpmsr1中北，中，南三个位置
        jpmsr1.add(jpmsr3, "North");
        jpmsr1.add(jsp2, "Center");
        jpmsr1.add(jpmsr_jb3, "South");
        //把卡片放入JFrame中
        c1 = new CardLayout();
        this.setLayout(c1);
        this.add(jphy1, "1");
        this.add(jpmsr1, "2");
        //在窗口显示自己的name
        this.setTitle(userName);
        this.setSize(170, 450);
        this.setVisible(true);
    }

    //更新在线好友情况
    public void updateFriend(Message message) {
        String onLineFriend[] = message.getContext().split(" ");
        for (int i = 0; i < onLineFriend.length; i++) {

            jbls[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);

        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
//响应用户双击事件,并得到好友编号
        if (e.getClickCount() == 2) {
            String friendNo = ((JLabel) e.getSource()).getText();
            QqChar qqChar = new QqChar(userName, friendNo);
//把新开的聊天窗口加入到管理聊天窗口的map中
            ManageQqChar.addQqChar(this.userName + " " + friendNo, qqChar);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {


    }


    @Override
    public void mouseEntered(MouseEvent e) {

        //当鼠标移上去时,使好友头像变为红色
        JLabel j1 = (JLabel) e.getSource();
        j1.setForeground(Color.red);
    }


    @Override
    public void mouseExited(MouseEvent e) {

        //当把鼠标移走时,恢复成黑色
        JLabel j1 = (JLabel) e.getSource();
        j1.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //如果点击了陌生人按钮,就显示第二张卡片
        if (e.getSource() == jphy_jb2) {

            c1.show(this.getContentPane(), "2");

        } else if (e.getSource() == jpmsr_jb1) {

            c1.show(this.getContentPane(), "1");

        }

    }

}



