package com.try01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by ztc on 15-10-31.
 */
public class Server {
    static ServerSocket server;
    static Socket s;
    public Server(){
        try {
            System.out.println("启动服务器！");
            //监听本地9999端口
            server = new ServerSocket(9999);
            //等待客户端链接创建Socket对象
            s = server.accept();
        }catch(IOException e){
            System.out.println("输入输出错误!"+e);
        }
    }
    public void listen(){
        try {
            System.out.println("Listening!......");
            //从Socket中获得输入流
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader br=new BufferedReader(in);
            //读取输入流中的一行并输出
            System.out.println(br.readLine());
        }catch(IOException e){
            System.out.println("输入输出错误！"+e);
        }
    }
    public static void send(String msg)
    {
        try {
        	//从Socket中获得实时刷新输出流，
            PrintWriter out=new PrintWriter(s.getOutputStream(),true);
            //向输出流中写入一行数据
            out.println("Server:"+msg);
        }catch(IOException e){
            System.out.println("send输入输出错误！"+e);
        }
    }
    public static void main(String[] args){
        Server se=new Server();
        String msg="";
        Scanner cin=new Scanner(System.in);
        //输入“#” 表示停止
        while(!msg.equals("#")) {
        	//接收信息
            se.listen();
            System.out.print("输入信息：");
            msg=cin.nextLine();
            //发送信息
            se.send(msg);
        }
    }
}
