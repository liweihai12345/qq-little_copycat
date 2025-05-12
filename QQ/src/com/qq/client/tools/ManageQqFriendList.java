package com.qq.client.tools;

import com.qq.client.view.QqFriendList;

import java.util.HashMap;
import java.util.Map;

/**

 * 管理好友、黑名单..界面类

 * */

public class ManageQqFriendList {

    public static Map map = new HashMap<String, QqFriendList>();


//把用用户列表类添加到map中

    public static void addQqFriendList(String userName, QqFriendList list) {

        map.put(userName, list);

    }


//根据登录用户取得该用户列表类

    public static QqFriendList getQqFriendList(String userName) {

        return (QqFriendList)map.get(userName);

    }

}
