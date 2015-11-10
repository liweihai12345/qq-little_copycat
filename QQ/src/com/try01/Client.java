package com.try01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by ztc on 15-10-31.
 */
public class Client {
    static Socket client;
    public Client(){
        try {
        	//链接 本机的 9999 端口
            client = new Socket("192.168.16.100", 9999);
        }catch(UnknownHostException e){
            System.out.println("没找到主机！"+e);
        }catch(IOException e){
            System.out.println("Client输入输出错误！" + e);
        }
    }

    public static void send(String msg)
    {
        try {
        	//获得实时刷新的输出流
            PrintWriter out=new PrintWriter(client.getOutputStream(),true);
            out.println("Client:"+msg);
            //out.close();
        }catch(IOException e){
            System.out.println("send输入输出错误！"+e);
        }
    }

    public static void listen(){
        try {
            System.out.println("Listening!......");
            //获得输入流
            InputStreamReader in = new InputStreamReader(client.getInputStream());
            BufferedReader br = new BufferedReader(in);
            //读取一行并打印出来
            System.out.println(br.readLine());
        }catch(IOException e){
            System.out.println("Listen ERRO!"+e);
        }
    }
    public static void main(String[] args){
        String msg="";
        Client cl=new Client();
        Scanner cin=new Scanner(System.in);
        while(!msg.equals("#")){
            //cl=new Client();
            System.out.print("输入信息：");
            msg=cin.nextLine();
            cl.send(msg);
            cl.listen();
        }
    }
}
