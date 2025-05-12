package com.qq.common;

/**

 * 定义包的总类

 * */

public interface MessageType {

//表明登入成功的包

    int message_succeed = 1;

//表明登入失败的包

    int message_login_fail = 2;

//表明普通的包

    int message_common_mes = 3;

//要求返回在线好友的包

    int message_get_onLineFriend = 4;

//返回在线好友的包

    int message_ret_onLineFriend = 5;

}








