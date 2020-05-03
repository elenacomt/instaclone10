package com.example.instaclone10;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("slaohKTBgws8Sf47ohOwNo5XE8926RPlCo6i3D1Wz")
                // if defined
                .clientKey("NcfiUBqIyHmKs5bM0mU0Rks39fDZ9peYKISKrtPb")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
