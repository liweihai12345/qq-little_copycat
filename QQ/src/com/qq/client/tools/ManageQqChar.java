package com.qq.client.tools;

import com.qq.client.view.QqChar;

import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个管理用户聊天界面的类
 */

public class ManageQqChar {

    public static Map map = new HashMap<String, QqChar>();


//把用户聊天界面Qqchar添加到map中

    public static void addQqChar(String loginAndFriend, QqChar qqchar) {

        map.put(loginAndFriend, qqchar);

    }


//根据登入用户和发送用户取得该聊天界面

    public static QqChar getQqChar(String loginAndFriend) {

        return (QqChar) map.get(loginAndFriend);

    }

}
