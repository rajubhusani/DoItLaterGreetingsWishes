package com.vsshv.doitlater.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vsshv.doitlater.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Timer("SettingUp", false).schedule(1000) {
            moveToHome()
        }

    }

    private fun moveToHome(){
        var intent: Intent = Intent(this, LaunchActivity::class.java)
        startActivity(intent)
        finish()
    }
}