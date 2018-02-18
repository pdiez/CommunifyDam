package com.communifydam.app.communify;

import android.app.Application;

/**
 * Created by pedro on 18/02/2018.
 */

public class CommunifyApp extends Application {

    private boolean modoDebug = false;
    private String user = "";
    private String pwd = "";


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
