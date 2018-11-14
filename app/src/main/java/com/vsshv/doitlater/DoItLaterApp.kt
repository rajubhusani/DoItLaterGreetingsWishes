package com.vsshv.doitlater

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

public class DoItLaterApp: Application(){

    override fun onCreate() {
        super.onCreate()
        Injection.initialize()
        FacebookSdk.setApplicationId("2180209652240608")
        FacebookSdk.sdkInitialize(this)
        AppEventsLogger.activateApp(this)
    }
}