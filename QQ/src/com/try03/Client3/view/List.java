package com.try03.Client3.view;

import com.try03.Client3.model.ClientUser;
import com.try03.Client3.tools.ChatManger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Created by ztc on 15-11-2.
 */
public class List extends JFrame implements ActionListener, MouseListener {

    /* 卡片布局 */
    String userid, friendid;
    CardLayout cl;
    // Card 1
    JScrollPane c1_jsp;
    JPanel c1, c1_jp1, c1_jp2;
    JButton c1_jb1, c1_jb2, c1_jb3;
    JLabel[] c1_jl;
    //Card 2
    JScrollPane c2_jsp;
    JPanel c2, c2_jp1, c2_jp2;
    JButton c2_jb1, c2_jb2, c2_jb3;
    JLabel[] c2_jl;
    //Card 3
    JScrollPane c3_jsp;
    JPanel c3, c3_jp1, c3_jp2;
    JButton c3_jb1, c3_jb2, c3_jb3;
    JLabel[] c3_jl;

    public static void main(String[] args) {
        List li = new List("ni");
    }

    public List(final String userId) {
        this.userid = userId;
        //this.friendid=friendId;
        //Card 1
        c1 = new JPanel();
        c1_jp1 = new JPanel();
        c1_jp2 = new JPanel();
        c1_jb1 = new JButton("我的好友");
        c1_jb1.addActionListener(this);
        c1_jb2 = new JButton("陌生人");
        c1_jb2.addActionListener(this);
        c1_jb3 = new JButton("黑名单");
        c1_jb3.addActionListener(this);
        c1_jl = new JLabel[50];
        //列表
        c1_jp1.setLayout(new GridLayout(50, 1, 4, 4));
        for (int i = 0; i < 50; i++) {
            URL mmUrl = List.class.getResource("/image/mm.jpg");
            c1_jl[i] = new JLabel((i + 1) + "", new ImageIcon(mmUrl), JLabel.LEFT);
            c1_jl[i].addMouseListener(this);
            c1_jl[i].setEnabled(false);
            c1_jp1.add(c1_jl[i]);
        }
        c1_jsp = new JScrollPane(c1_jp1);
        //底部
        c1_jp2.setLayout(new GridLayout(2, 1));
        c1_jp2.add(c1_jb2);
        c1_jp2.add(c1_jb3);
        //组织组件
        c1.setLayout(new BorderLayout());
        c1.add(c1_jb1, "North");
        c1.add(c1_jsp, "Center");
        c1.add(c1_jp2, "South");
        //Card 2 +++++++++++++++++++++++++++++++++++
        c2 = new JPanel();
        c2_jp1 = new JPanel();
        c2_jp2 = new JPanel();
        c2_jb1 = new JButton("我的好友");
        c2_jb1.addActionListener(this);
        c2_jb2 = new JButton("陌生人");
        c2_jb2.addActionListener(this);
        c2_jb3 = new JButton("黑名单");
        c2_jb3.addActionListener(this);
        c2_jl = new JLabel[20];
        //列表
        c2_jp1.setLayout(new GridLayout(20, 1, 4, 4));
        for (int i = 0; i < 20; i++) {
            URL mmUrl = List.class.getResource("/image/mm.jpg");
            c2_jl[i] = new JLabel("陌生人" + (i + 1), new ImageIcon(mmUrl), JLabel.LEFT);
            c2_jp1.add(c2_jl[i]);
            c2_jl[i].addMouseListener(this);
        }
        c2_jsp = new JScrollPane(c2_jp1);
        //顶部
        c2_jp2.setLayout(new GridLayout(2, 1));
        c2_jp2.add(c2_jb1);
        c2_jp2.add(c2_jb2);
        //组织组件
        c2.setLayout(new BorderLayout());
        c2.add(c2_jp2, "North");
        c2.add(c2_jsp, "Center");
        c2.add(c2_jb3, "South");
        //Card 3++++++++++++++++++++++++++++++++++++++++
        c3 = new JPanel();
        c3_jp1 = new JPanel();
        c3_jp2 = new JPanel();
        c3_jb1 = new JButton("我的好友");
        c3_jb1.addActionListener(this);
        c3_jb2 = new JButton("陌生人");
        c3_jb2.addActionListener(this);
        c3_jb3 = new JButton("黑名单");
        c3_jb3.addActionListener(this);
        c3_jl = new JLabel[10];
        //列表
        c3_jp1.setLayout(new GridLayout(2, 1, 4, 4));
        for (int i = 0; i < 2; i++) {
            URL qqUrl = List.class.getResource("/image/qq.gif");
            c3_jl[i] = new JLabel("黑名单" + (i + 1), new ImageIcon(qqUrl), JLabel.LEFT);
            c3_jp1.add(c3_jl[i]);
            c3_jl[i].addMouseListener(this);
        }
        c3_jsp = new JScrollPane(c3_jp1);
        //顶部
        c3_jp2.setLayout(new GridLayout(3, 1));
        c3_jp2.add(c3_jb1);
        c3_jp2.add(c3_jb2);
        c3_jp2.add(c3_jb3);
        //组织组件
        c3.setLayout(new BorderLayout());
        c3.add(c3_jp2, "North");
        c3.add(c3_jsp, "Center");


        cl = new CardLayout();
        this.setLayout(cl);
        this.add(c1, "1");
        this.add(c2, "2");
        this.add(c3, "3");
        this.setTitle(userId);
        this.setLocation(500, 150);
        this.setSize(240, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL qqUrl = List.class.getResource("/image/qq.gif");
        this.setIconImage((new ImageIcon(qqUrl)).getImage());
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(userId + "  LoginOut!");
                ClientUser.LoginOut(userId);
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

    public void changeList(Object[] l) {
        for (int i = 0; i < c1_jl.length; i++) {
            int sign = 0;
            for (int j = 0; j < l.length; j++) {
                if (c1_jl[i].getText().equals(l[j].toString())) {
                    sign = 1;
                    break;
                }
            }
            if (sign == 1)
                c1_jl[i].setEnabled(true);
            else
                c1_jl[i].setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == c1_jb1 || a.getSource() == c2_jb1 || a.getSource() == c3_jb1) {
            cl.show(this.getContentPane(), "1");
        } else if (a.getSource() == c1_jb2 || a.getSource() == c2_jb2 || a.getSource() == c3_jb2) {
            cl.show(this.getContentPane(), "2");
        } else if (a.getSource() == c1_jb3 || a.getSource() == c2_jb3 || a.getSource() == c3_jb3) {
            cl.show(this.getContentPane(), "3");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && ((JLabel) e.getSource()).isEnabled()) {
            Chat ch = new Chat(this.userid, ((JLabel) e.getSource()).getText());
            ChatManger.Addchat(this.userid + "_" + ((JLabel) e.getSource()).getText(), ch);
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
        ((JLabel) e.getSource()).setForeground(Color.red);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JLabel) e.getSource()).setForeground(Color.black);
    }
}
