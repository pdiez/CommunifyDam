package com.communifydam.app.communify;

import android.app.Application;

/**
 * Created by pedro on 18/02/2018.
 */

public class CommunifyApp extends Application {

    private boolean modoDebug = true;
    private String user = "communifydam@gmail.com";
    private String pwd = "123456";


    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }


    public boolean getModoDebug() {
        return modoDebug;
    }
}
