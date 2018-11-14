package com.vsshv.doitlater.services

import android.app.Activity
import com.google.firebase.auth.FirebaseUser
import com.vsshv.doitlater.models.UserModel

interface UserService {

    fun forgotUser()

    fun updateUserInFirebase(email: String, password: String, phone: String)

    fun updateUserInFirebase(user: FirebaseUser?)

    fun updateUserInFirebase(user: UserModel?, fbUser: FirebaseUser?)
}