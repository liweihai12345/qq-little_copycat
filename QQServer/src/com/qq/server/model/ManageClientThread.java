package com.qq.server.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 这是一个管理服务器和客户端保持通讯的线程类
 */

public class ManageClientThread {

    public static Map map = new HashMap<String, SerConClientThread>();


//向map中添加一个线程

    public static void addCilentThread(String userName, SerConClientThread sc) {

        map.put(userName, sc);

    }


//根据userName取得一个线程

    public static SerConClientThread getCilentThread(String userName) {

        return (SerConClientThread) map.get(userName);

    }


//返回当前在线的人的情况

    public static String getAllOnLineUser() {

//使用迭代器完成

        Iterator it = map.keySet().iterator();


        String res = "";

        while (it.hasNext()) {

            res += it.next().toString() + " ";

        }


        return res;

    }

}

