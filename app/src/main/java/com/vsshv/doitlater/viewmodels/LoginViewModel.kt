package com.vsshv.doitlater.viewmodels

import android.arch.lifecycle.ViewModel
import com.vsshv.doitlater.models.UserModel

class LoginViewModel: ViewModel(){

    public fun validateLogin(): Boolean{

        return false
    }

    public fun doLogin(): UserModel {

        var model: UserModel = UserModel()

        return model
    }

    public fun updateUserPreferences(model: UserModel){

    }
}