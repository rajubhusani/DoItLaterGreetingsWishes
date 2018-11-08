package com.vsshv.doitlater.services

import android.app.Activity

interface UserService {

    fun registerUser(ctx: Activity, email: String, password: String)

    fun loginUser(ctx: Activity, email: String, password: String)

    fun forgotUser()

    fun updateUserInFirebase(ctx: Activity, email: String, password: String, phone: String)
}