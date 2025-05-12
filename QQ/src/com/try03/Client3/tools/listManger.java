package com.try03.Client3.tools;

import com.try03.Client3.view.List;

/**
 * Created by ztc on 15-11-8.
 */
public class ListManger {
    private static List mainL;

    public static void setMainL(List L) {
        mainL = L;
    }

    public static List getMainL() {
        return mainL;
    }
}
