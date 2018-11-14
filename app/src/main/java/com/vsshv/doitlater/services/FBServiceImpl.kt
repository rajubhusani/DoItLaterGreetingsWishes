package com.vsshv.doitlater.services

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.vsshv.doitlater.R
import com.vsshv.doitlater.R.id.password
import com.vsshv.doitlater.models.UserModel

class FBServiceImpl: FirebaseServiceProvider(){

    private val TAG = "FBServiceImpl"

    override fun forgotUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserInFirebase(email: String, password: String, phone: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEvents() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun uploadMedia() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserInFirebase(user: FirebaseUser?) {
        var values:  HashMap<String, String?> = HashMap<String, String?>()
        values.put("email", user?.email)
        values.put("displayName", user?.displayName)
        values.put("phoneNumber", user?.phoneNumber)
        values.put("providerId", user?.providerId)

        getUserDatabaseRef().child(user!!.uid).setValue(values)
    }

    override fun updateUserInFirebase(user: UserModel?, fbUser: FirebaseUser?) {
        var values:  HashMap<String, String?> = HashMap<String, String?>()
        values.put("email", user?.emailId)
        values.put("displayName", user?.firstName+" "+user?.lastName)
        values.put("phoneNumber", user?.mobileNumber)
        values.put("providerId", fbUser?.providerId)

        getUserDatabaseRef().child(fbUser!!.uid).setValue(values)
    }
}