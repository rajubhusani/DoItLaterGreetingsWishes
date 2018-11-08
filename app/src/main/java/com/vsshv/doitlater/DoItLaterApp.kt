package com.vsshv.doitlater

import android.app.Application

public class DoItLaterApp: Application(){

    override fun onCreate() {
        super.onCreate()
        Injection.initialize()
    }
}