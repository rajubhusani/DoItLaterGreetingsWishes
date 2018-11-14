package com.vsshv.doitlater.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vsshv.doitlater.R
import com.vsshv.doitlater.utils.PreferenceHelper
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        val prefHelper: PreferenceHelper? = PreferenceHelper.getInstance(this)
        val isLogged: Boolean = prefHelper!!.getBoolanValue("iSlogged", false)

        Timer("SettingUp", false).schedule(1000) {
            if(!isLogged){
                moveToPage()
            }else{
                moveToHome()
            }
        }

    }

    private fun moveToPage(){
        val intent = Intent(this, LaunchActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveToHome(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}