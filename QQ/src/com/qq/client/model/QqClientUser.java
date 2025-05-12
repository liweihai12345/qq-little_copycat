package com.qq.client.model;

import com.qq.common.User;

/**

 * 这是QQ客户端,发送用户名和密码

 *

 * */

public class QqClientUser {

//调用客户端连接服务器后台的方法,返回true为成功登录,false为登入失败

    public boolean checkUser(User user) {

        return new QqClientConService().sendLoginInfoToServer(user);

    }

}

