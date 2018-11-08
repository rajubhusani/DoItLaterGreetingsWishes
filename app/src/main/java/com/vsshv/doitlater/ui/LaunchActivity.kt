package com.vsshv.doitlater.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.vsshv.doitlater.R
import com.vsshv.doitlater.ui.common.CustomButton

class LaunchActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_launch)

        ButterKnife.bind(this)

        loadLaunch()
    }

    private fun loadLaunch(){
        var fragment: Fragment = LaunchFragment()
        var fragmentMgr : FragmentManager = supportFragmentManager
        var fragmentTransaction: FragmentTransaction = fragmentMgr!!.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment, "Launcher")

        fragmentTransaction.commit()
    }
}