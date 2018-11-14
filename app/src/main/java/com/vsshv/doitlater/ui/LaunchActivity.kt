package com.vsshv.doitlater.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.vsshv.doitlater.R

class LaunchActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launch)

        supportActionBar?.hide()

        ButterKnife.bind(this)

        loadLaunch()
    }

    private fun loadLaunch(){
        val fragment: Fragment = LaunchFragment()
        val fragmentMgr : FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentMgr.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment, "Launcher")

        fragmentTransaction.commit()
    }
}