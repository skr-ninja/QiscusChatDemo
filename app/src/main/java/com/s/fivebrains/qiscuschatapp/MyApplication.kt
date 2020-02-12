package com.s.fivebrains.qiscuschatapp

import androidx.multidex.MultiDexApplication
import com.qiscus.jupuk.Jupuk
import com.qiscus.mychatui.BuildConfig
import com.qiscus.mychatui.util.PushNotificationUtil
import com.qiscus.nirmana.Nirmana
import com.qiscus.sdk.chat.core.QiscusCore
import com.qiscus.sdk.chat.core.data.model.NotificationListener
import com.vanniktech.emoji.EmojiManager
import com.vanniktech.emoji.one.EmojiOneProvider

class MyApplication : MultiDexApplication() {
    // var myApp: MyApp? = null

    override fun onCreate() {
        super.onCreate()
       // myApp= MyApp.getInstance()

        //myApp?.onCreate(this)

        Nirmana.init(this)
        QiscusCore.setup(this, BuildConfig.QISCUS_SDK_APP_ID)

        QiscusCore.getChatConfig()
            .enableDebugMode(true)
            .setNotificationListener(NotificationListener { context, qiscusComment ->
                PushNotificationUtil.showNotification(
                    context,
                    qiscusComment
                )
            })
            .setEnableFcmPushNotification(true)
        initEmoji()
        Jupuk.init(this)
    }

    private fun initEmoji() {
        EmojiManager.install(EmojiOneProvider())
    }

}