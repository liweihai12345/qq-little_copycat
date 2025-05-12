package com.qq.client.tools;

import java.util.HashMap;
import java.util.Map;

/**

 * 这是一个管理客户端和服务器保持通讯的线程类

 * */

public class ManageClientConServerThread {

    public static Map map = new HashMap<String, ClientConServerThread>();


//把线程添加到map中

    public static void addClientConServerThread(String userName, ClientConServerThread ccst) {

        map.put(userName, ccst);

    }


//根据用户名取得该线程

    public static ClientConServerThread getClientConServerThread(String userName) {

        return (ClientConServerThread)map.get(userName);

    }

}