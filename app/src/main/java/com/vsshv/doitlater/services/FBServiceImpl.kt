package com.vsshv.doitlater.services

import android.app.Activity
import android.util.Log
import android.widget.Toast

class FBServiceImpl: FirebaseServiceProvider(){

    private val TAG = "FBServiceImpl"

    override fun registerUser(ctx: Activity, email: String, password: String) {
        val auth = getFirebaseAuth()

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx) {task->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(ctx, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun loginUser(ctx: Activity, email: String, password: String) {
        val auth = getFirebaseAuth()
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ctx) {task->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                      //  updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(ctx, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                       // updateUI(null)
                    }

                }
    }

    override fun forgotUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateUserInFirebase(ctx: Activity, email: String, password: String, phone: String) {
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

    override fun googleSignIn() {

    }

    override fun facebookSignIn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}