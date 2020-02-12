package com.qiscus.mychatui;


import android.app.Application;

import com.qiscus.jupuk.Jupuk;
import com.qiscus.mychatui.util.PushNotificationUtil;
import com.qiscus.nirmana.Nirmana;
import com.qiscus.sdk.chat.core.QiscusCore;
import com.vanniktech.emoji.EmojiManager;
import com.vanniktech.emoji.one.EmojiOneProvider;

public class MyApp
{
    private AppComponent component;
    private  static MyApp  instance;


    //private constructor.
    private MyApp(){

        System.out.println("============hi");
        if (instance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static MyApp getInstance() {
        if(instance!=null){
            instance=new MyApp();

        }
        return instance;
    }
    public void onCreate(Application application){
         instance = this;
         component = new AppComponent(application);

        Nirmana.init(application);
        QiscusCore.setup(application, BuildConfig.QISCUS_SDK_APP_ID);

        QiscusCore.getChatConfig()
                .enableDebugMode(true)
                .setNotificationListener(PushNotificationUtil::showNotification)
                .setEnableFcmPushNotification(true);
        initEmoji();
        Jupuk.init(application);
    }

    private void initEmoji() {
        EmojiManager.install(new EmojiOneProvider());
    }

    public AppComponent getComponent() {
        return component;
    }
 }