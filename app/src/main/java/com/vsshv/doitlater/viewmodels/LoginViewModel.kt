package com.vsshv.doitlater.viewmodels

import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.vsshv.doitlater.models.UserModel
import com.vsshv.doitlater.services.FBServiceImpl

class LoginViewModel: ViewModel(){

    fun validateLogin(userName: String, password: String): Boolean{

        return false
    }

    fun updateUserPreferences(model: UserModel){

    }

    fun updateUserToFirebase(user: FirebaseUser?){
        FBServiceImpl().updateUserInFirebase(user)
    }
}