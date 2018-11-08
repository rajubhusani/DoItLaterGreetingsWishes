package com.vsshv.doitlater

import com.vsshv.doitlater.services.FBServiceImpl

public class Injection{

    companion object {
        private var mFirebaseImpl: FBServiceImpl? = null
        fun initialize() {
            mFirebaseImpl = FBServiceImpl()
        }

        fun getImplObject(): FBServiceImpl{
            return mFirebaseImpl!!
        }
    }

}