package com.vsshv.doitlater.viewmodels

import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import com.google.firebase.auth.FirebaseUser
import com.vsshv.doitlater.models.UserModel
import com.vsshv.doitlater.services.FBServiceImpl

class RegisterViewModel: ViewModel(){

    fun validateRegisterForm(userModel: UserModel): Boolean{

        if(TextUtils.isEmpty(userModel.password) || TextUtils.isEmpty(userModel.firstName)
            || TextUtils.isEmpty(userModel.lastName) || TextUtils.isEmpty(userModel.emailId) || TextUtils.isEmpty(userModel.mobileNumber)){
            return false
        }
        return true
    }

    fun updateUserPreferences(model: UserModel){

    }

    fun updateUserToFirebase(user: FirebaseUser?){
        FBServiceImpl().updateUserInFirebase(user)
    }

    fun updateUserToFirebase(user: UserModel?, fbUser: FirebaseUser?){
        FBServiceImpl().updateUserInFirebase(user, fbUser)
    }
}