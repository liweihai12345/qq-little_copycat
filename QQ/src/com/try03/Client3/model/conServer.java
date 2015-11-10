package com.try03.Client3.model;

import com.try03.common.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ztc on 15-11-2.
 */
public class conServer {
    private Socket s;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    public boolean sendLoginInfo(Object o,String ip,String port){
        boolean sign=false;
        try {
            s=new Socket(ip,Integer.parseInt(port));
            oos=new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);
            ois=new ObjectInputStream(s.getInputStream());
            Message m=(Message)ois.readObject();
            if(m.getType().equals("success")){
                sign=true;
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public Socket getS() {
        return s;
    }
    public ObjectInputStream getOIS(){
        return this.ois;
    }
    public ObjectOutputStream getOOS(){
        return this.oos;
    }
    public void sendObject(Object o){
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
