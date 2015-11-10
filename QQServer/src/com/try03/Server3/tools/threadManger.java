package com.try03.Server3.tools;

import java.util.HashMap;
import java.util.Set;

import com.try03.Server3.model.*;
import com.try03.Server3.view.display;
import com.try03.common.Message;

/**
 * Created by ztc on 15-11-4.
 */
public class threadManger {
    public static HashMap<String,myThread> bank=new HashMap<String,myThread>();
    public static void addThread(String s,myThread my){
        bank.put(s,my);
    }
    public static myThread getThread(String s){
        return bank.get(s);
    }
    public static void delThread(String s){
        bank.remove(s);
    }
    public static void friendAlert(){
        Set<String> set=bank.keySet();
        Object[] l=set.toArray();
        display.di.showOnline(l);
        for(int i=0;i<l.length;i++){
            myThread mt=bank.get(l[i].toString());
            mt.sendList();
        }
    }
}
