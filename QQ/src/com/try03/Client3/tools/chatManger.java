package com.try03.Client3.tools;

import com.try03.Client3.view.chat;

import java.util.HashMap;

/**
 * Created by ztc on 15-11-7.
 */
public class chatManger {
    public static HashMap<String,chat> hm=new HashMap<String,chat>();
    public static void Addchat(String uid_fid,chat c){
        hm.put(uid_fid,c);
    }
    public static chat Getchat(String uid_fid){
        return hm.get(uid_fid);
    }
    public static void Delchat(String uid_fid){
        hm.remove(uid_fid);
    }
}
