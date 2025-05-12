package com.try03.Server3.tools;

import com.try03.Server3.model.MyThread;
import com.try03.Server3.view.Display;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by ztc on 15-11-4.
 */
public class threadManger {
    public static HashMap<String, MyThread> bank = new HashMap<String, MyThread>();


    public static void addThread(String s, MyThread my) {
        bank.put(s, my);
    }

    public static MyThread getThread(String s) {
        return bank.get(s);
    }

    public static void delThread(String s) {
        bank.remove(s);
    }

    public static void friendAlert() {
        Set<String> set = bank.keySet();
        Object[] l = set.toArray();
        Display.di.showOnline(l);
        for (int i = 0; i < l.length; i++) {
            MyThread mt = bank.get(l[i].toString());
            mt.sendList();
        }
    }
}
