package com.try03.Client3.tools;

import com.try03.Client3.view.Chat;

import java.util.HashMap;

/**
 * Created by ztc on 15-11-7.
 */
public class ChatManger {
    public static HashMap<String, Chat> hm = new HashMap<String, Chat>();

    public static void Addchat(String uid_fid, Chat c) {
        hm.put(uid_fid, c);
    }

    public static Chat Getchat(String uid_fid) {
        return hm.get(uid_fid);
    }

    public static void Delchat(String uid_fid) {
        hm.remove(uid_fid);
    }
}
