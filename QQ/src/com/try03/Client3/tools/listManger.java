package com.try03.Client3.tools;

import com.try03.Client3.view.list;

/**
 * Created by ztc on 15-11-8.
 */
public class listManger {
    private static list mainL;
    public static void setMainL(list L){
        mainL=L;
    }
    public static list getMainL(){
        return mainL;
    }
}
