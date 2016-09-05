package com.felink.guihubcode.utils;

import com.felink.guihubcode.bean.UserBean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/8/2.
 */
public class SaveBeanToFile {

    public static void saveUser(){
        UserBean bean = new UserBean(0,"linbin",true);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            out.writeObject(bean);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getUser(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("cache.txt"));
            UserBean bean = (UserBean) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
