package com.vsshv.doitlater.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.vsshv.doitlater.R
import com.vsshv.doitlater.ui.common.FButton

class LaunchFragment: Fragment(){

    @BindView(R.id.login)
    lateinit var mLogin: FButton

    @BindView(R.id.signup)
    lateinit var mSignup: FButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_launch, container, false)

        ButterKnife.bind(this, view)

        mLogin.setOnClickListener { moveToLogin() }

        mSignup.setOnClickListener { moveToSignup() }

        return view
    }

    private fun moveToLogin(){
        val fragment: Fragment = LoginFragment()
        val fragmentMgr : FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentMgr.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment, "Login")
        fragmentTransaction.addToBackStack("Login")
        fragmentTransaction.commit()
    }

    private fun moveToSignup(){
        val fragment: Fragment = RegisterFragment()
        val fragmentMgr : FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentMgr.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment, "Signup")
        fragmentTransaction.addToBackStack("Signup")
        fragmentTransaction.commit()
    }
}