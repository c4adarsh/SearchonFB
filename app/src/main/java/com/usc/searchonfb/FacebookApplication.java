package com.usc.searchonfb;

import android.app.Application;

import com.usc.searchonfb.Dagger.Component.DaggerNetComponent;
import com.usc.searchonfb.Dagger.Component.NetComponent;
import com.usc.searchonfb.Dagger.Module.AppModule;
import com.usc.searchonfb.Dagger.Module.NetModule;

/**
 * Created by adarsh on 4/15/2017.
 */

public class FacebookApplication  extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://adarsh.us-west-2.elasticbeanstalk.com/index/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}